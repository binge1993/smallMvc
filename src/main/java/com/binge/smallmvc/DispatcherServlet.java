package com.binge.smallmvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.binge.smallmvc.bean.Data;
import com.binge.smallmvc.bean.Handler;
import com.binge.smallmvc.bean.Param;
import com.binge.smallmvc.helper.BeanHelper;
import com.binge.smallmvc.helper.ConfigHelper;
import com.binge.smallmvc.helper.ControllerHelper;
import com.binge.smallmvc.util.ArrayUtil;
import com.binge.smallmvc.util.CodecUtil;
import com.binge.smallmvc.util.JsonUtil;
import com.binge.smallmvc.util.ReflectionUtil;
import com.binge.smallmvc.util.StreamUtil;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 请求转发器
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 5162695042755691082L;

	@Override
    public void init(ServletConfig servletConfig) throws ServletException {
    	
        // 初始化相关 Helper 类
        HelperLoader.init();
        // 获取 ServletContext 对象（用于注册 Servlet）
        ServletContext servletContext = servletConfig.getServletContext();
        // 注册处理静态资源的默认 Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

    }

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取请求方法与请求路径
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		// 获取 Action 处理器
		Handler handler = ControllerHelper.getHandler(requestMethod,
				requestPath);
		if (handler == null) {
			return;
		}

		// 获取 Controller 类及其 Bean 实例
		Class<?> controllerClass = handler.getControllerClass();
		Object controllerBean = BeanHelper.getBean(controllerClass);
		// 创建请求参数对象
		Map<String, Object> paramMap = new HashMap<>();
		Enumeration<String> paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			paramMap.put(paramName, paramValue);
		}

		String body = CodecUtil
				.decodeURL(StreamUtil.getString(request.getInputStream()));
		handleParamBody(body, paramMap);

		Param param = new Param(paramMap);
		// 调用 Action 方法
		Method actionMethod = handler.getActionMethod();
		Object result = ReflectionUtil.invokeMethod(controllerBean,
				actionMethod, param);
		// 处理 Action 方法返回值
		handleRetrunData(request, response, result);

	}

	/**
	 * 处理 Action 的返回 Data
	 * 
	 * @param result
	 * @throws IOException
	 * @throws ServletException
	 */
	private void handleRetrunData(HttpServletRequest request,
			HttpServletResponse response, Object result)
			throws IOException, ServletException {
		// 返回 JSON 页面
		Data data = (Data) result;
		Object model = data.getModel();
		if (model == null) {
			return;
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String json = JsonUtil.toJson(model);
		writer.write(json);
		writer.flush();
		writer.close();
	}

	/**
	 * 处理请求参数
	 * 
	 * @param body
	 * @param paramMap
	 */
	private void handleParamBody(String body, Map<String, Object> paramMap) {
		if (StringUtils.isBlank(body)) {
			return;
		}

		String[] params = StringUtils.splitByWholeSeparator(body, "&");
		if (ArrayUtil.isEmpty(params)) {
			return;
		}

		for (String param : params) {
			String[] array = StringUtils.splitByWholeSeparator(param, "=");
			if (ArrayUtil.isEmpty(array) || array.length != 2) {
				continue;
			}

			String paramName = array[0];
			String paramValue = array[1];
			paramMap.put(paramName, paramValue);
		}
	}

}
