package com.yhd.controller.backend;

import com.yhd.bean.Hint;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.logger.LoggerServiceProxy;
import com.yhd.pojo.ActivitySlideshow;
import com.yhd.pojo.Admin;
import com.yhd.service.backend.ActivitySlideshowService;
import com.yhd.service.backend.impl.ActivitySlideshowServiceImpl;
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
 * @Date 2020/7/1 15:15
 * @Since 1.8
 */
public class ActivitySlideshowServlet extends HttpServlet {
	private ActivitySlideshowService service;
	private void initService(HttpSession sess) {
		service = new LoggerServiceProxy<>(new ActivitySlideshowServiceImpl((Connection) sess.getAttribute(ContentConstant.SESSION_CONNECTION)
				,DaoFlyweightPatternFactory.getInstance().getDaoImpl("activity_slideshow"))).getProxyInstance();

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
	 * load slide show data use of method
	 */
	private void getAll(HttpServletRequest req, HttpServletResponse resp) {
		List<ActivitySlideshow> allList = service.getAllList();
		WebUtils.sendValue(resp, JsonUtils.getJson(allList));
	}

	private void addSlideshow(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		String image = req.getParameter("image");
		String font = req.getParameter("font");
		String url = req.getParameter("url");
		boolean flag = service.addSlideshow(new ActivitySlideshow(id, image, font, url));
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "添加成功,序号为：" + id : "添加失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	private void updateSlideshow(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		String image = req.getParameter("image");
		String font = req.getParameter("font");
		String url = req.getParameter("url");
		boolean flag = service.updateById(new ActivitySlideshow(id, image, font, url));
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "修改成功,序号为：" + id : "修改失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}

	private void removeSlideshow(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean flag = service.removeById(id);
		Hint hint = new Hint(((Admin) req.getSession().getAttribute(ContentConstant.SESSION_ADMIN)).getId(), flag ? "删除成功,序号为：" + id : "删除失败");
		WebUtils.sendValue(resp, JsonUtils.getJson(hint));
	}
}
