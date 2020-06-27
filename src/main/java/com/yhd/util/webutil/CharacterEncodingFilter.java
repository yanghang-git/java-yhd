package com.yhd.util.webutil;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author 杨航
 * @Date 2020/6/26 17:43
 * @Since 1.8
 */
public class CharacterEncodingFilter implements Filter {

	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter) throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		resp.setContentType("text/html;charset=" + encoding);
		filter.doFilter(req, resp);
	}

	@Override
	public void destroy() {
	}
}
