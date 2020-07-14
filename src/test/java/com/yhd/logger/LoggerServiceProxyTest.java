package com.yhd.logger;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.service.backend.AdminService;
import com.yhd.service.backend.impl.AdminServiceImpl;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/7/12 15:21
 * @Since 1.8
 */
public class LoggerServiceProxyTest {

	@Test
	public void LoggerAdminService() {
		AdminService service = new LoggerServiceProxy<>(new AdminServiceImpl(ConnectionFactory.INSTANCE.create()
				, DaoFlyweightPatternFactory.getInstance().getDaoImpl("admin"))).getProxyInstance();
		service.login("Yoyo", "yoyo123");
	}
}
