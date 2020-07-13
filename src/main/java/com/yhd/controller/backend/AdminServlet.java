package com.yhd.controller.backend;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.logger.LoggerServiceProxy;
import com.yhd.pojo.Admin;
import com.yhd.bean.Hint;
import com.yhd.service.backend.AdminService;
import com.yhd.service.backend.impl.AdminServiceImpl;
import com.yhd.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.Arrays;

/**
 * @Author 杨航
 * @Date 2020/6/29 15:17
 * @Since 1.8
 */
public class AdminServlet extends HttpServlet {
	private AdminService service;
	private int maxFailTimes;		// max may number of fail
	private int failFreezeDay;		// max fail later freeze of day

	private void initService(HttpSession sess) {
		service = new LoggerServiceProxy<>(new AdminServiceImpl((Connection) sess.getAttribute(ContentConstant.SESSION_CONNECTION)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("admin"))).getProxyInstance();
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		maxFailTimes = Integer.parseInt(config.getInitParameter("maxFailTimes")) - 1;
		failFreezeDay = Integer.parseInt(config.getInitParameter("failFreezeDay"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		if (service == null) initService(req.getSession());
		String methodName = req.getParameter(ContentConstant.CONTENT_METHOD_NAME);
		try {
			this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class).invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}

	/**
	 *  Admin login of Method
	 *  	if login fail exceed maxFailTimes, failFreezeDay within will cannot login
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Admin admin = service.login(username, password);
		String url = "backend/index.jsp";
		if(admin == null) {
			url = "/backendlogin.jsp";
			if (checkLoginTimes(req, resp)) return;
		} else {
			req.getSession().setAttribute(ContentConstant.SESSION_ADMIN, admin);
			req.getSession().removeAttribute(String.valueOf(InetAddress.getLocalHost()));
		}
		resp.sendRedirect(req.getContextPath() + url);
	}

	/**
	 * Admin log out of method
	 */
	private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		service.logOut(((Admin)req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId());
		req.getSession().removeAttribute(ContentConstant.SESSION_ADMIN);
		resp.sendRedirect(req.getContextPath() + "/backendlogin.jsp");
	}

	/**
	 * add Admin of method
	 */
	private void addAdmin(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String[] powers = req.getParameterValues("power");
		Admin admin = new Admin();
		admin.setId(id);
		admin.setPassword(password);
		Class<? extends Admin> adminClass = admin.getClass();
		for (String power : powers) {
			try {
				Method method = adminClass.getMethod(JDBCUtils.firstAddSet(power + "Power"), boolean.class);
				method.invoke(admin, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean flag = service.addAdmin(admin);
		Hint hint = new Hint(((Admin)req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "添加成功，账号为：" + admin.getId() : "添加失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	private void containsId(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		if (service.containsId(id)) {
			WebUtils.sendValue(resp, JsonUtils.getJson(new Hint(((Admin)req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId()
					, "账号已存在")));
		}
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
			cookie.setMaxAge((int) WebUtils.getDayMillisecond(this.failFreezeDay));
			resp.addCookie(cookie);
			WebUtils.sendValue(resp, "<h1>您在" + this.failFreezeDay + "天内无法进行登入</h1>");
			return true;
		}
		req.setAttribute(ContentConstant.PAGE_HINT, new Hint(title, "用户名或密码错误"));
		return false;
	}


}
