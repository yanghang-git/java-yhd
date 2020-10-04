package com.yhd.controller.personalcenter;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.logger.LoggerServiceProxy;
import com.yhd.service.personalcenter.GoodsService;
import com.yhd.service.personalcenter.impl.GoodsServiceImpl;
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
 * @date 2020/9/30 9:58
 * @since 1.8
 */
public class GoodsServlet extends HttpServlet {
	private GoodsService service;

	private void initService(HttpSession sess) {
		service = new GoodsServiceImpl((Connection) sess.getAttribute(ContentConstant.SESSION_CONNECTION)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods"));
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		if(service == null) initService(req.getSession());
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

	private void getGoodsById(HttpServletRequest req, HttpServletResponse resp) {
		int goods = Integer.parseInt(req.getParameter("goodsId"));
		WebUtils.sendValue(resp, JsonUtils.getJson(service.getGoodsById(goods)));
	}
}
