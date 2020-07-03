package com.yhd.controller.backend;

import com.yhd.bean.Hint;
import com.yhd.bean.Page;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Admin;
import com.yhd.pojo.Goods;
import com.yhd.pojo.GoodsCatalog;
import com.yhd.pojo.User;
import com.yhd.service.backend.GoodsCatalogService;
import com.yhd.service.backend.GoodsService;
import com.yhd.service.backend.impl.GoodsCatalogServiceImpl;
import com.yhd.service.backend.impl.GoodsServiceImpl;
import com.yhd.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/3 13:44
 * @Since 1.8
 */
public class GoodsServlet extends HttpServlet {
	private GoodsService service;
	private int eachPageNumber;
	private int maxPagination;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new GoodsServiceImpl(ConnectionFactory.INSTANCE.create(ConnectionFactory.MYSQL_TOMCAT_CONN)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods"));
		ServletContext context = this.getServletContext();
		eachPageNumber = Integer.parseInt(context.getInitParameter("pagingNumberEachPage"));
		maxPagination = Integer.parseInt(context.getInitParameter("pagingMaxPagination"));
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
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
	 * get goods list by catalog of id and goods of name
	 */
	private void getGoodsAllByCatalogIdAndGoodsName(HttpServletRequest req, HttpServletResponse resp) {
		int catalogId = Integer.parseInt(req.getParameter("catalogUpId"));
		String goodsName = req.getParameter("goodsName");
		int currPageNo = Integer.parseInt(req.getParameter("currPageNo"));  // 显示第几页
		ArrayList<Integer> catalogAllId = getCatalogAllId(catalogId);
		ArrayList<Goods> goodsList = new ArrayList<>();
		for (Integer integer : catalogAllId) {
			goodsList.addAll(service.getGoodsByCatalogIdAndGoodsName(integer, goodsName));
		}
		Page<Goods> pages =new Page<>(20, currPageNo, eachPageNumber, maxPagination, goodsList);
		WebUtils.sendValue(resp, JsonUtils.getJson(pages));
	}

	private void addGoods(HttpServletRequest req, HttpServletResponse resp) {
		String primaryImage = req.getParameter("primaryImage");
		String name = req.getParameter("name");
		int catalogId = Integer.parseInt(req.getParameter("catalogId"));
		int number = Integer.parseInt(req.getParameter("number"));
		BigDecimal price = new BigDecimal(req.getParameter("price"));
		String style = req.getParameter("style");
		String kind = req.getParameter("kind");
		String detailImage = req.getParameter("detailImage");
		String detailText = req.getParameter("detailText");
		boolean flag = service.addGoods(new Goods(0, name, price, style, kind, detailText, primaryImage, detailImage, number, catalogId));
		Hint hint = new Hint(((Admin)req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId()
				, flag ? "添加成功，商品名称为：" + name : "添加失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}


	/**
	 * get goods catalog up id by catalog id
	 * @param catalogId  catalog up id
	 * @return goods catalog id
	 */
	private ArrayList<Integer> getCatalogAllId(int catalogId) {
		ArrayList<Integer> resultList = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionFactory.INSTANCE.create(ConnectionFactory.MYSQL_TOMCAT_CONN);
			GoodsCatalogService catalogService = new GoodsCatalogServiceImpl(conn
					, DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods_catalog"));
			List<GoodsCatalog> catalogs = catalogService.getAllListByUpId(catalogId);
			if (catalogs.size() == 0) {
				GoodsCatalog goods = catalogService.getGoodsById(catalogId);
				ArrayList<GoodsCatalog> tempList = new ArrayList<>();
				tempList.add(goods);
				catalogs = tempList;
			}
			for (GoodsCatalog catalog : catalogs) {
				if (catalog.getCatalogs() != null && catalog.getCatalogs().size() > 0) {
					resultList.addAll(getCatalogAllId(catalog.getId()));
				}
			}
			for (GoodsCatalog catalog : catalogs) {
				resultList.add(catalog.getId());
			}
		} finally {
			JDBCUtils.closeAll(conn, null, null);
		}
		return resultList;
	}
}
