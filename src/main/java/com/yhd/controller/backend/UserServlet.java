package com.yhd.controller.backend;

import com.yhd.bean.Page;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Admin;
import com.yhd.bean.Hint;
import com.yhd.pojo.User;
import com.yhd.service.backend.UserService;
import com.yhd.service.backend.impl.UserServiceImpl;
import com.yhd.util.ConnectionFactory;
import com.yhd.util.ContentConstant;
import com.yhd.util.JsonUtils;
import com.yhd.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/30 18:34
 * @Since 1.8
 */
public class UserServlet extends HttpServlet {

	private UserService service;
	private int eachPageNumber;
	private int maxPagination;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserServiceImpl(ConnectionFactory.INSTANCE.create(ConnectionFactory.MYSQL_TOMCAT_CONN)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("user"));
		ServletContext context = this.getServletContext();
		eachPageNumber = Integer.parseInt(context.getInitParameter("pagingNumberEachPage"));
		maxPagination = Integer.parseInt(context.getInitParameter("pagingMaxPagination"));

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
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
	 * The way to use ajax invoke
	 * 		load page of user data
	 */
	private void getAllByIdList(HttpServletRequest req, HttpServletResponse resp) {
		String account = req.getParameter("account");
		int currPageNo = Integer.parseInt(req.getParameter("currPageNo"));  // 显示第几页
		long userCount = service.getUserCount(account); // 用户个数
		List<User> list = service.getAllByIdList(account, currPageNo, eachPageNumber);
		Page<User> pages =new Page<>(userCount, currPageNo, eachPageNumber, maxPagination, list);
		WebUtils.sendValue(resp, JsonUtils.getJson(pages));
	}

	/**
	 * The way to use ajax invoke
	 * 		to user freeze by userId
	 */
	private void freeze(HttpServletRequest req, HttpServletResponse resp) {
		String account = req.getParameter("account");
		boolean freeze = service.freeze(account);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), account + "\t冻结" + (freeze ? "成功" : "失败"));
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	/**
	 * The way to use ajax invoke
	 * 		to user unfreeze by userId
	 */
	private void unfreeze(HttpServletRequest req, HttpServletResponse resp) {
		String account = req.getParameter("account");
		boolean freeze = service.unfreeze(account);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), account + "\t解冻" + (freeze ? "成功" : "失败"));
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}
}
