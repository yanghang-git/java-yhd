package com.yhd.util.webutil;

import com.yhd.pojo.Admin;
import com.yhd.util.ContentConstant;
import com.yhd.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

/**
 * @Author 杨航
 * @Date 2020/6/29 14:30
 * @Since 1.8
 */
public class AdminLoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
		String localhost = String.valueOf(InetAddress.getLocalHost());
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		for (int i = 0; i < cookies.length; i++) {
			if (localhost.equals(cookies[i].getName())) {
				long time = Long.parseLong(cookies[i].getValue());
				long now = System.currentTimeMillis();
				String date = WebUtils.calculateTwoTimeGap(now, time);
				WebUtils.sendValue(resp, "<h1>还需要：" + date + "</h1>");
				return;
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
