package com.yhd.controller.personalcenter;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.logger.LoggerServiceProxy;
import com.yhd.pojo.GoodsComment;
import com.yhd.service.backend.GoodsCatalogService;
import com.yhd.service.backend.impl.GoodsCatalogServiceImpl;
import com.yhd.service.personalcenter.GoodsCommentService;
import com.yhd.service.personalcenter.impl.GoodsCommentServiceImpl;
import com.yhd.util.ContentConstant;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 杨航
 * @date 2020/10/4 17:45
 * @since 1.8
 */
public class GoodsCommentServlet extends HttpServlet {
	private GoodsCommentService service;

	private void initService(HttpSession sess) {
		service = new GoodsCommentServiceImpl((Connection) sess.getAttribute(ContentConstant.SESSION_CONNECTION)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods_comment"));
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}

	private void addComment(HttpServletRequest req, HttpServletResponse resp) {
		int goodsId = Integer.parseInt(req.getParameter("goodsId"));
		int level = Integer.parseInt(req.getParameter("level"));
		String username = req.getParameter("username");
		String comment = req.getParameter("comment");
		String indentId = req.getParameter("indentId");
		service.addGoodsComment(indentId, new GoodsComment(username, goodsId, level, comment, new Timestamp(new Date().getTime())));
	}


}
