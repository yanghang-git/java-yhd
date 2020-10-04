package com.yhd.service.personalcenter;

import com.yhd.pojo.Goods;

/**
 * @author 杨航
 * @date 2020/9/30 9:55
 * @since 1.8
 */
public interface GoodsService {
	/**
	 * get goods by goods of id
	 * @param goodsId goods of id
	 * @return goods instance
	 */
	Goods getGoodsById(int goodsId);
}
