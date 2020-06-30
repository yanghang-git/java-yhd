package com.yhd.controller.backend;

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

	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserServiceImpl(ConnectionFactory.INSTANCE.create()
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("user"));
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
	 * the way to use request and response invoke
	 * 		load page of user data
	 */
	private void getAllByIdList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		req.setAttribute("userList", service.getAllByIdList(account));
		req.getRequestDispatcher(req.getContextPath() + "/backend/user/freezemanage.jsp").forward(req, resp);
	}

	/**
	 * The way to use ajax invoke
	 * 		load page of user data
	 */
	private void getAllByIdListAjax(HttpServletRequest req, HttpServletResponse resp) {
		String account = req.getParameter("account");
		List<User> list = service.getAllByIdList(account);
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

	/**
	 * The way to use ajax invoke
	 * 		to user freeze by userId
	 */
	private void freeze(HttpServletRequest req, HttpServletResponse resp) {
		String account = req.getParameter("account");
		boolean freeze = service.freeze(account);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), freeze ? "冻结成功" : "冻结失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	/**
	 * The way to use ajax invoke
	 * 		to user unfreeze by userId
	 */
	private void unfreeze(HttpServletRequest req, HttpServletResponse resp) {
		String account = req.getParameter("account");
		boolean freeze = service.unfreeze(account);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), freeze ? "解冻成功" : "解冻失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}
}
