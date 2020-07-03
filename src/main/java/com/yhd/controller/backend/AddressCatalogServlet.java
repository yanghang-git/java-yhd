package com.yhd.controller.backend;

import com.mysql.jdbc.StringUtils;
import com.yhd.bean.Hint;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.AddressCatalog;
import com.yhd.pojo.Admin;
import com.yhd.service.backend.AddressCatalogService;
import com.yhd.service.backend.impl.AddressCatalogServiceImpl;
import com.yhd.util.ConnectionFactory;
import com.yhd.util.ContentConstant;
import com.yhd.util.JsonUtils;
import com.yhd.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/2 10:18
 * @Since 1.8
 */
public class AddressCatalogServlet extends HttpServlet {
	private AddressCatalogService service;
	@Override
	public void init() throws ServletException {
		super.init();
		service = new AddressCatalogServiceImpl(ConnectionFactory.INSTANCE.create(ConnectionFactory.MYSQL_TOMCAT_CONN)
			, DaoFlyweightPatternFactory.getInstance().getDaoImpl("address_catalog"));
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}

	/**
	 * get all addressCatalog by upId, if upId is 0, be result all addressCatalog
	 */
	private void getCatalogAllByUpId(HttpServletRequest req, HttpServletResponse resp) {
		String upId = req.getParameter("upId");
		List<AddressCatalog> list = service.getAllListByUpId(StringUtils.isNullOrEmpty(upId) ? 0 : Integer.parseInt(upId));
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

	/**
	 * get all addressCatalog by upId
	 */
	private void getCatalogByUpId(HttpServletRequest req, HttpServletResponse resp) {
		String upId = req.getParameter("upId");
		List<AddressCatalog> list = service.getListByUpId(StringUtils.isNullOrEmpty(upId) ? 0 : Integer.parseInt(upId));
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

	/**
	 *  remove AddressCatalog by catalog of id
	 */
	private void removeCatalogById(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("catalogId"));
		boolean flag = service.removeById(id);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "删除成功" : "删除失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	/**
	 * update AddressCatalog name by catalog of id
	 */
	private void updateCatalogNameById(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("catalogId"));
		String name = req.getParameter("catalogName");
		boolean flag = service.updateCatalogNameById(id, name);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "修改成功，新名称为：" + name : "修改失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	/**
	 * add one AddressCatalog
	 */
	private void addCatalog(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("catalogName");
		int upId = Integer.parseInt(req.getParameter("catalogUpId"));
		boolean flag = service.addAddressCatalog(new AddressCatalog(0, name, upId));
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "添加成功，名称为：" + name : "添加失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	/**
	 * get all addressCatalog by upId, if upId is null, be result all addressCatalog
	 */
	private void getCatalogAllByName(HttpServletRequest req, HttpServletResponse resp) {
		String catalogName = req.getParameter("catalogName");
		boolean catalogNameIsNullOrEmpty = StringUtils.isNullOrEmpty(catalogName);
		List<AddressCatalog> list = catalogNameIsNullOrEmpty ? service.getAllListByUpId(0) : service.getListByCatalogName(catalogName);
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

}
