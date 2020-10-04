package com.yhd.controller.personalcenter;

import com.yhd.cache.CacheFactory;
import com.yhd.service.personalcenter.IndentStatusService;
import com.yhd.service.personalcenter.impl.IndentStatusServiceImpl;
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
 * @date 2020/9/29 16:07
 * @since 1.8
 */
public class IndentStatusServlet extends HttpServlet {
	private IndentStatusService service;

	private void initService(HttpSession sess) {
		service = new IndentStatusServiceImpl((Connection) sess.getAttribute(ContentConstant.SESSION_CONNECTION)
				, CacheFactory.IndentStatusCacheProxy.create());
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


	private void getIndentStatusById(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("statusId"));
		WebUtils.sendValue(resp, service.getIndentStatusNameById(id));
	}

	private void getAllList(HttpServletRequest req, HttpServletResponse resp) {
		WebUtils.sendValue(resp, JsonUtils.getJson(service.getAllList()));
	}
}
