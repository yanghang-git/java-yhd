package com.yhd.cache;

import com.yhd.dao.DaoFlyweightPatternFactory;

/**
 * @Author 杨航
 * @Date 2020/7/18 15:43
 * @Since 1.8
 */
public enum CacheFactory {
	IndentStatusCacheProxy {
		@Override
		public <E> E create() {
			return (E) new IndentStatusCacheProxy(DaoFlyweightPatternFactory.getInstance().getDaoImpl("indent_status"));
		}
	};
	public abstract <E> E create();
}
