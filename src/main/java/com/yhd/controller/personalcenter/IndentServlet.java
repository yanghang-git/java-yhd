package com.yhd.controller.personalcenter;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Indent;
import com.yhd.service.personalcenter.IndentService;
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
 * @date 2020/9/29 11:07
 * @since 1.8
 */
public class IndentServlet extends HttpServlet {
	private IndentService service;;

	private void initService(HttpSession session) {
		service = new IndentServiceImpl((Connection) session.getAttribute(ContentConstant.SESSION_CONNECTION)
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("indent"));
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

	private void getRecentlyIndentByUserName(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		Indent indent = service.getRecentlyIndentByUserName(id);
		WebUtils.sendValue(resp, JsonUtils.getJson(indent));
	}


	private void getIndentByUserIdList(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		List<Indent> list = service.getIndentByUserIdList(username);
		WebUtils.sendValue(resp, JsonUtils.getJson(list));
	}

	private void getAllByStatusIdAndUsername(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("statusId"));
		String username = req.getParameter("username");
		WebUtils.sendValue(resp, JsonUtils.getJson(service.getAllByStatusIdAndUsername(id, username)));
	}

	private void getIndentById(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("indentId");
		WebUtils.sendValue(resp, JsonUtils.getJson(service.getIndentById(id)));
	}
}
