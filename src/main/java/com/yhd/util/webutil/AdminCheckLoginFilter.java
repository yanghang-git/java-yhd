package com.yhd.util.webutil;

import com.yhd.util.ContentConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 杨航
 * @Date 2020/6/30 20:03
 * @Since 1.8
 */
public class AdminCheckLoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		if (null == ((HttpServletRequest)servletRequest).getSession().getAttribute(ContentConstant.SESSION_ADMIN)) {
			((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/backendlogin.jsp");
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {

	}
}
