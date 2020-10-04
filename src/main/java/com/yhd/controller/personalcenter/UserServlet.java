package com.yhd.controller.personalcenter;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.User;
import com.yhd.service.personalcenter.UserService;
import com.yhd.service.personalcenter.impl.UserServiceImpl;
import com.yhd.util.ContentConstant;
import com.yhd.util.JsonUtils;
import com.yhd.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

/**
 * @author 杨航
 * @date 2020/10/1 15:21
 * @since 1.8
 */
public class UserServlet extends HttpServlet {

	private UserService service;

	private void initService(HttpSession sess) {
		service = new UserServiceImpl((Connection) sess.getAttribute(ContentConstant.SESSION_CONNECTION)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("user"));
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}


	private void alterDefaultAddress(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("userId");
		int addressId = Integer.parseInt(req.getParameter("addressId"));
		service.alterDefaultAddress(addressId, userId);
	}


	private void getUserById(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");
		WebUtils.sendValue(resp, JsonUtils.getJson(service.getUserByName(name)));
	}

	private void updateUserInfo(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("userId");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String birthday = req.getParameter("birthday");
		LocalDate dateTime = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Date date = new Date(dateTime.atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli());
		service.updateUser(new User(userId, null, name, sex, date, null, null, null, null));
	}

	private void updateUserEmail(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String username = req.getParameter("userId");
		service.updateUser(new User(username, null, null, null, null, email, null, null, null));
	}

	private void updateUserPassword(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		service.updateUser(new User(userId, password, null, null, null, null, null, null, null));
	}

	private void updateUserPhone(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("userId");
		String phone = req.getParameter("phone");
		service.updateUser(new User(userId, null, null, null, null, null, phone, null, null));
	}

	private void checkUserPhoneIsExist(HttpServletRequest req, HttpServletResponse resp) {
		String phone = req.getParameter("phone");
		WebUtils.sendValue(resp, String.valueOf(service.checkUserPhoneIsExist(phone)));
	}

}
