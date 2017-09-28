package com.binge.smallmvc;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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

}
