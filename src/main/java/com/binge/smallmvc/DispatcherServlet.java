package com.binge.smallmvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.binge.smallmvc.bean.Data;
import com.binge.smallmvc.bean.Handler;
import com.binge.smallmvc.bean.Param;
import com.binge.smallmvc.helper.BeanHelper;
import com.binge.smallmvc.helper.ConfigHelper;
import com.binge.smallmvc.helper.ControllerHelper;
import com.binge.smallmvc.helper.RequestHelper;
import com.binge.smallmvc.helper.ServletHelper;
import com.binge.smallmvc.helper.UploadHelper;
import com.binge.smallmvc.util.JsonUtil;
import com.binge.smallmvc.util.ReflectionUtil;
import com.binge.smallmvc.wrapper.RepeatedlyReadRequestWrapper;

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
		ServletRegistration defaultServlet = servletContext
				.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 使用可重复读流的request
		RepeatedlyReadRequestWrapper requestWrapper = new RepeatedlyReadRequestWrapper(
				request);

		// 初始化 Servlet 助手类
		ServletHelper.init(requestWrapper, response);
		try {

			// 获取请求方法与请求路径
			String requestMethod = requestWrapper.getMethod().toLowerCase();
			String requestPath = requestWrapper.getPathInfo();
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
			Param param;
			if (UploadHelper.isMultipart(requestWrapper)) {
				param = UploadHelper.createParam(requestWrapper);
			} else {
				param = RequestHelper.createParam(requestWrapper);
			}

			Object result;
			Method actionMethod = handler.getActionMethod();
			if (param.isEmpty()) {
				result = ReflectionUtil.invokeMethod(controllerBean,
						actionMethod);
			} else {
				result = ReflectionUtil.invokeMethod(controllerBean,
						actionMethod, param);
			}
			// 处理 Action 方法返回值
			handleRetrunData(requestWrapper, response, result);

		} finally {
			// 销毁 Servlet 助手类
			ServletHelper.destroy();
		}

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

}
