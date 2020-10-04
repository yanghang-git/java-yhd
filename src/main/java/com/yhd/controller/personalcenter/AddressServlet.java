package com.yhd.controller.personalcenter;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Address;
import com.yhd.service.personalcenter.AddressService;
import com.yhd.service.personalcenter.impl.AddressServiceImpl;
import com.yhd.service.personalcenter.impl.IndentServiceImpl;
import com.yhd.util.ContentConstant;
import com.yhd.util.JsonUtils;
import com.yhd.util.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/30 9:37
 * @since 1.8
 */
public class AddressServlet extends HttpServlet {
	private AddressService service;

	private void initService(HttpSession session) {
		service = new AddressServiceImpl((Connection) session.getAttribute(ContentConstant.SESSION_CONNECTION)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("address"));
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

	private void getAddressDetailByAddressId(HttpServletRequest req, HttpServletResponse resp) {
		int addressId = Integer.parseInt(req.getParameter("addressId"));
		String address = service.getAddressDetailByAddressId(addressId);
		WebUtils.sendValue(resp, address);
	}

	private void getAddressListByUsername(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		List<Address> list = service.getAddressListByUsername(username);
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

	private void addAddress(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("userId");
		String username = req.getParameter("username");
		String phone = req.getParameter("phone");
		int city = Integer.parseInt(req.getParameter("city"));
		int county = Integer.parseInt(req.getParameter("county"));
		int street = Integer.parseInt(req.getParameter("street"));
		String detail = req.getParameter("detail");
		service.addAddress(new Address(0, userId, username, phone, city, county, street, detail));
	}

	private void removeAddressById(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("addressId"));
		service.removeAddress(id);
	}

	private void getAddressById(HttpServletRequest req, HttpServletResponse resp) {
		int addressId = Integer.parseInt(req.getParameter("addressId"));
		WebUtils.sendValue(resp, JsonUtils.getJson(service.getAddressById(addressId)));
	}

	private void updateAddress(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		String userId = req.getParameter("userId");
		String username = req.getParameter("username");
		String phone = req.getParameter("phone");
		int city = Integer.parseInt(req.getParameter("city"));
		int county = Integer.parseInt(req.getParameter("county"));
		int street = Integer.parseInt(req.getParameter("street"));
		String detail = req.getParameter("detail");
		service.updateAddress(new Address(id, userId, username, phone, city, county, street, detail));
	}
}
