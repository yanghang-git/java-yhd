package com.yhd.logger;

import com.yhd.controller.backend.AddressCatalogServlet;
import com.yhd.pojo.*;
import com.yhd.service.backend.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 假设一个场景。
 * 		无法修改源代码。 为后端增加日志功能
 * 		采用设计模式中的 动态代理  Proxy  具体使用JDK 自带的
 * @Author 杨航
 * @Date 2020/7/12 13:45
 * @Since 1.8
 */
public class LoggerServiceProxy<T> {
	// proxy of object
	private T target;
	// each proxy method of de handler
	private LoggerInvocationHandler loggerHandler;
	// slf4j of log instance
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public LoggerServiceProxy(T target) {
		this.target = target;
		loggerHandler = new LoggerInvocationHandler();
	}

	/**
	 * return proxy instance
	 * @return proxy instance
	 */
	public T getProxyInstance() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces()
				, loggerHandler);
	}

	/**
	 * each proxy method of de handler
	 */
	private class LoggerInvocationHandler implements InvocationHandler {
		/**
		 * specific of proxy method
		 * @param proxy proxy of object
		 * @param method proxy of method
		 * @param args proxy of params
		 * @return proxy object of return data
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = method.invoke(target, args);
			disposeRecordMsg(method.getName(), args, result);
			return result;
		}

		/**
		 * print log by logback
		 * @param msg log msg
		 */
		private void record(String msg) {
			logger.info(msg);
		}

		/**
		 * generate log msg
		 * @param methodName proxy object method of name
		 * @param args proxy object of params
		 * @param result log msg
		 */
		private void disposeRecordMsg(String methodName, Object[] args, Object result) {
			StringBuilder sb = new StringBuilder();
			if (target instanceof AdminService) {
				sb.append("管理员：");
				switch (methodName) {
					case "login":
						sb.append("---------------------------登入-> ").append(args[0]).append(result == null ? "登入失败" : "登入成功");
						break;
					case "addAdmin":
						Admin admin = (Admin) args[0];
						sb.append("注册->").append(admin.getId()).append("；权限： ").append(admin.getUserPower() ? "用户、" : "").append(admin.getGoodsPower() ? "商品、" : "")
							.append(admin.getActivityPower()? "活动、" : "").append(admin.getIndentPower() ? "订单、" : "").append(admin.getAddressPower() ? "地址、" : "");
						break;
					case "logOut":
						sb.append("----------------------------退出-> ").append(args[0]);
						break;
				}
			} else if (target instanceof ActivitySlideshowService) {
				sb.append("活动-轮播图管理：");
				switch (methodName) {
					case "addSlideshow":
						sb.append("添加-> ").append("编号").append(((ActivitySlideshow)args[0]).getId()).append((boolean)result ? " 添加成功" : " 添加失败");
						break;
					case "removeById":
						sb.append("删除->").append("编号").append(args[0]).append((boolean)result ? " 删除成功" : " 删除失败");
						break;
					case "updateById":
						sb.append("修改->").append("编号").append(((ActivitySlideshow)args[0]).getId()).append((boolean)result ? " 修改成功" : " 修改失败");
						break;
					default:
						sb.append("查询");
				}
			} else if (target instanceof AddressCatalogService) {
				sb.append("地址-目录管理：");
				switch (methodName) {
					case "addAddressCatalog":
						AddressCatalog catalog = (AddressCatalog)args[0];
						sb.append("添加->").append(" 地址名称").append(catalog.getName()).append((boolean)result ? " 添加成功" : " 添加失败");
						break;
					case "removeById":
						sb.append("删除->").append("编号").append(args[0]).append((boolean)result ? " 删除成功" : " 删除失败");
						break;
					case "updateCatalogNameById":
						sb.append("修改->").append("编号").append(args[0]).append(" 地址名称修改为").append(args[1]).append((boolean)result ? " 修改成功" : " 修改失败");
						break;
					default:
						sb.append("查询");
				}
			} else if (target instanceof GoodsCatalogService) {
				sb.append("商品-目录管理：");
				switch (methodName) {
					case "addGoodsCatalog":
						GoodsCatalog catalog = (GoodsCatalog)args[0];
						sb.append("添加->").append(" 商品目录名称").append(catalog.getName()).append((boolean)result ? " 添加成功" : " 添加失败");
						break;
					case "removeById":
						sb.append("删除->").append("编号").append(args[0]).append((boolean)result ? " 删除成功" : " 删除失败");
						break;
					case "updateCatalog":
						sb.append("修改->").append("编号").append(args[0]).append(" 商品目录名称修改为").append(args[1]).append((boolean)result ? " 修改成功" : " 修改失败");
						break;
					default:
						sb.append("查询");
				}
			} else if (target instanceof GoodsService) {
				sb.append("商品管理：");
				switch (methodName) {
					case "addGoods":
						Goods addGoods = (Goods)args[0];
						sb.append("添加->").append(" 商品名称").append(addGoods.getName()).append((boolean)result ? " 添加成功" : " 添加失败");
						break;
					case "removeById":
						sb.append("删除->").append("编号").append(args[0]).append((boolean)result ? " 删除成功" : " 删除失败");
						break;
					case "updateById":
						Goods updateGoods = (Goods)args[0];
						sb.append("修改->").append("编号").append(updateGoods.getId()).append((boolean)result ? " 修改成功" : " 修改失败");
						break;
					default:
						sb.append("查询");
				}
			} else if (target instanceof IndentService) {
				sb.append("订单管理：");
				switch (methodName) {
					case "removeById":
						sb.append("删除->").append("编号").append(args[0]).append((boolean)result ? " 删除成功" : " 删除失败");
						break;
					case "updateIndentGoodsTypeAndBuyNumberAndTotalPriceById":
						sb.append("修改->").append("编号").append(args[3]).append((boolean)result ? " 修改成功" : " 修改失败");
						break;
					default:
						sb.append("查询");
				}
			} else if (target instanceof IndentStatusService) {
				sb.append("订单-状态管理：");
				switch (methodName) {
					case "addStatues":
						IndentStatus addStatus = (IndentStatus)args[0];
						sb.append("添加->").append(" 状态名称").append(addStatus.getStatusName()).append((boolean)result ? " 添加成功" : " 添加失败");
						break;
					case "removeById":
						sb.append("删除->").append("编号").append(args[0]).append((boolean)result ? " 删除成功" : " 删除失败");
						break;
					case "updateStatues":
						IndentStatus updateStatus = (IndentStatus)args[0];
						sb.append("修改->").append("编号").append(updateStatus.getId()).append(" 状态名称修改为").append(updateStatus.getStatusName()).append((boolean)result ? " 修改成功" : " 修改失败");
						break;
					default:
						sb.append("查询");
				}
			} else if (target instanceof UserService) {
				sb.append("用户管理：");
				switch (methodName) {
					case "freeze":
						sb.append("冻结->").append("账号").append(args[0]).append((boolean)result ? " 冻结成功" : " 冻结失败");
						break;
					case "unfreeze":
						sb.append("解冻->").append("账号").append(args[0]).append((boolean)result ? " 解冻成功" : " 解冻失败");
						break;
					default:
						sb.append("查询");
				}
			}
			record(sb.toString());
		}
	}
}
