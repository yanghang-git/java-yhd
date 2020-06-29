package com.yhd.controller.backend;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Admin;
import com.yhd.pojo.Hint;
import com.yhd.service.backend.AdminService;
import com.yhd.service.backend.impl.AdminServiceImpl;
import com.yhd.util.ConnectionFactory;
import com.yhd.util.ContentConstant;
import com.yhd.util.WebUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author 杨航
 * @Date 2020/6/29 15:17
 * @Since 1.8
 */
public class AdminServlet extends HttpServlet {
	private AdminService service;
	private int maxFailTimes;		// max may number of fail
	private int failFreezeDay;		// max fail later freeze of day

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		service  = new AdminServiceImpl(ConnectionFactory.INSTANCE.create()
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("admin"));
		maxFailTimes = Integer.parseInt(config.getInitParameter("maxFailTimes")) - 1;
		failFreezeDay = Integer.parseInt(config.getInitParameter("failFreezeDay"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodName = req.getParameter(ContentConstant.CONTENT_METHOD_NAME);
		try {
			this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class).invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 *  Admin login of Method
	 *  	if login fail exceed three times, one day within will cannot login
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Admin admin = service.login(username, password);
		String url = "WEB-INF/backend/index.jsp";
		if(admin == null) {
			url = "/backendlogin.jsp";
			if (checkLoginTimes(req, resp)) return;
		} else {
			req.getSession().removeAttribute(String.valueOf(InetAddress.getLocalHost()));
		}
		req.getRequestDispatcher(req.getContextPath() + url).forward(req, resp);
	}

	/**
	 *	check login fail times and accordingly dispose
	 * @return whether freeze
	 */
	private boolean checkLoginTimes(HttpServletRequest req, HttpServletResponse resp) throws UnknownHostException {
		HttpSession session = req.getSession();
		String localhost = String.valueOf(InetAddress.getLocalHost());
		Integer failTimes = (Integer) session.getAttribute(localhost);
		String title;
		if (failTimes == null) {
			session.setAttribute(localhost, 1);
			title = "您还有" + maxFailTimes + "次机会";
		} else if (failTimes < this.maxFailTimes) {
			session.setAttribute(localhost, failTimes + 1);
			title = "您还有" + (maxFailTimes - failTimes) + "次机会";
		} else {
			session.removeAttribute(localhost);
			Cookie cookie = new Cookie(localhost, (WebUtils.getDayMillisecond(this.failFreezeDay) + System.currentTimeMillis()) + "");
			cookie.setMaxAge(60 * 60 * 12);
			resp.addCookie(cookie);
			WebUtils.sendValue(resp, "<h1>您在" + this.failFreezeDay + "天内无法进行登入</h1>");
			return true;
		}
		req.setAttribute(ContentConstant.PAGE_HINT, new Hint(title, "用户名或密码错误"));
		return false;
	}


}
