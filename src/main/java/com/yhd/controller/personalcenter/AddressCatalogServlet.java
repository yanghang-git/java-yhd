package com.yhd.controller.personalcenter;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.service.personalcenter.AddressCatalogService;
import com.yhd.service.personalcenter.impl.AddressCatalogServiceImpl;
import com.yhd.util.ContentConstant;
import com.yhd.util.JsonUtils;
import com.yhd.util.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

/**
 * @author 杨航
 * @date 2020/9/30 15:06
 * @since 1.8
 */
public class AddressCatalogServlet extends HttpServlet {
	private AddressCatalogService service;

	private void initService(HttpSession sess) {
		service = new AddressCatalogServiceImpl((Connection) sess.getAttribute(ContentConstant.SESSION_CONNECTION)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("address_catalog"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		if(service == null) initService(req.getSession());
		String methodName = req.getParameter(ContentConstant.CONTENT_METHOD_NAME);
		try {
			this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class).invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void getCatalogByUpId(HttpServletRequest req, HttpServletResponse resp) {
		int upId = Integer.parseInt(req.getParameter("upId"));
		WebUtils.sendValue(resp, JsonUtils.getJson(service.getCatalogByUpId(upId)));
	}

	private void getCatalogById(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		WebUtils.sendValue(resp, JsonUtils.getJson(service.getCatalogById(id)));
	}
}
