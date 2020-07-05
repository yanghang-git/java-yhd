package com.yhd.controller.backend;

import com.mysql.jdbc.StringUtils;
import com.yhd.bean.Hint;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Admin;
import com.yhd.pojo.GoodsCatalog;
import com.yhd.service.backend.GoodsCatalogService;
import com.yhd.service.backend.impl.GoodsCatalogServiceImpl;
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
 * @Author 杨航
 * @Date 2020/7/2 10:18
 * @Since 1.8
 */
public class GoodsCatalogServlet extends HttpServlet {
	private GoodsCatalogService service;

	private void initService(HttpSession sess) {
		service = new GoodsCatalogServiceImpl((Connection) sess.getAttribute(ContentConstant.SESSION_CONNECTION)
				,DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods_catalog"));
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

	/**
	 * get all GoodsCatalog by upId, if upId is 0, be result all GoodsCatalog
	 */
	private void getCatalogAllByUpId(HttpServletRequest req, HttpServletResponse resp) {
		String upId = req.getParameter("upId");
		List<GoodsCatalog> list = service.getAllListByUpId(StringUtils.isNullOrEmpty(upId) ? 0 : Integer.parseInt(upId));
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

	/**
	 * get all GoodsCatalog by upId
	 */
	private void getCatalogByUpId(HttpServletRequest req, HttpServletResponse resp) {
		String upId = req.getParameter("upId");
		List<GoodsCatalog> list = service.getListByUpId(StringUtils.isNullOrEmpty(upId) ? 0 : Integer.parseInt(upId));
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

	/**
	 *  remove GoodsCatalog by catalog of id
	 */
	private void removeCatalogById(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("catalogId"));
		boolean flag = service.removeById(id);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "删除成功" : "删除失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	/**
	 * update GoodsCatalog name by catalog of id
	 */
	private void updateCatalogNameById(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("catalogId"));
		String name = req.getParameter("catalogName");
		boolean flag = service.updateCatalog(id, name);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "修改成功，新名称为：" + name : "修改失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	/**
	 * add one GoodsCatalog
	 */
	private void addCatalog(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("catalogName");
		int upId = Integer.parseInt(req.getParameter("catalogUpId"));
		boolean flag = service.addGoodsCatalog(new GoodsCatalog(0, name, upId));
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "添加成功，名称为：" + name : "添加失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	private void getCatalogAllByName(HttpServletRequest req, HttpServletResponse resp) {
		String catalogName = req.getParameter("catalogName");
		boolean catalogNameIsNullOrEmpty = StringUtils.isNullOrEmpty(catalogName);
		List<GoodsCatalog> list = catalogNameIsNullOrEmpty ? service.getAllListByUpId(0) : service.getListByCatalogName(catalogName);
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

}
