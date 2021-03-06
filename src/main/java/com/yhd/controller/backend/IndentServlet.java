package com.yhd.controller.backend;

import com.yhd.bean.Hint;
import com.yhd.bean.Page;
import com.yhd.dao.AddressDao;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.logger.LoggerServiceProxy;
import com.yhd.pojo.Admin;
import com.yhd.pojo.Indent;
import com.yhd.service.backend.IndentService;
import com.yhd.service.backend.impl.IndentServiceImpl;
import com.yhd.util.ContentConstant;
import com.yhd.util.JsonUtils;
import com.yhd.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/4 17:29
 * @Since 1.8
 */
public class IndentServlet extends HttpServlet {
	private IndentService service;
	private int eachPageNumber;
	private int maxPagination;

	private void initService(HttpSession session) {
		service = new LoggerServiceProxy<>(new IndentServiceImpl((Connection) session.getAttribute(ContentConstant.SESSION_CONNECTION)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("indent"))).getProxyInstance();
	}
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = this.getServletContext();
		eachPageNumber = Integer.parseInt(context.getInitParameter("pagingNumberEachPage"));
		maxPagination = Integer.parseInt(context.getInitParameter("pagingMaxPagination"));
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

	/**
	 * get indents by Status of id and Goods of name and user of id and indent of id, if is empty be no join check queue
	 */
	private void getListByStatusAndGoodsIdAndUserIdAndId (HttpServletRequest req, HttpServletResponse resp) {
		int statusId = Integer.parseInt(req.getParameter("statusId"));
		String goodsName = req.getParameter("goodsName");
		String userId = req.getParameter("userId");
		String id = req.getParameter("indentId");
		int currPageNo = Integer.parseInt(req.getParameter("currPageNo"));  // 显示第几页
		List<Indent> list = service.getListByStatusAndGoodsIdAndUserIdAndId(statusId, goodsName, userId, id, currPageNo, eachPageNumber);
		Page<Indent> pages = new Page<>(service.getCountByStatusAndGoodsIdAndUserIdAndId(statusId, goodsName, userId, id)
				, currPageNo, eachPageNumber, maxPagination, list);
		WebUtils.sendValue(resp, JsonUtils.getJson(pages));
	}

	private void shipments(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		boolean flag = service.shipments(id);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId()
				, flag ? "发货成功" : "发货失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	private void showIndentInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String id = req.getParameter("id");
		req.setAttribute("indent", service.getIndentById(id));
		req.getRequestDispatcher(req.getContextPath() + "/backend/indent/showinfo.jsp").forward(req, resp);
	}

	private AddressDao dao;
	private void initAddressDao() {
		dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("address");
	}
	/**
	 * !!! this method not should appear at this class center
	 * 		get address detail by address of id
	 */
	private void getAddressDetailByAddressId(HttpServletRequest req, HttpServletResponse resp) {
		if(dao == null) initAddressDao();
		int addressId = Integer.parseInt(req.getParameter("addressId"));
		String address = dao.getAddressDetailById((Connection) req.getSession().getAttribute(ContentConstant.SESSION_CONNECTION), addressId);
		WebUtils.sendValue(resp, address);
	}
}
