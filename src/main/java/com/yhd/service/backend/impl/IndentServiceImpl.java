package com.yhd.service.backend.impl;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.GoodsDao;
import com.yhd.dao.IndentDao;
import com.yhd.dao.impl.GoodsDaoImpl;
import com.yhd.pojo.Goods;
import com.yhd.pojo.Indent;
import com.yhd.service.backend.IndentService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/4 17:31
 * @Since 1.8
 */
public class IndentServiceImpl implements IndentService {
	private Connection conn;
	private IndentDao dao;

	public IndentServiceImpl(Connection conn, IndentDao dao) {
		this.conn = conn;
		this.dao = dao;
	}


	/**
	 * get Indents By StatusId、GoodsName、UserId、Id。 if is null be not join check queue
	 * @param statusId status of id
	 * @param goodsName  goods of name
	 * @param userId   user of id
	 * @param id       my id
	 * @return indent instance gather List
	 */
	@Override
	public List<Indent> getListByStatusAndGoodsIdAndUserIdAndId(int statusId, String goodsName, String userId, String id, int pageSize, int pageCount) {
		GoodsDao goods = DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods");
		List<Goods> goodsList = goods.getListByCatalogIdAndGoodsName(conn, 0, goodsName);
		List<Integer> goodsId = new ArrayList(goodsList.size());
		goodsList.forEach((value) -> goodsId.add(value.getId()));
		return dao.getListByStatusAndGoodsIdAndUserIdAndId(conn, statusId, goodsId, userId, id, pageSize, pageCount);
	}

	/**
	 * update indent of goodsType, buyNumber, totalPrice by indent of id
	 * @param goodsType  goods of style and kind
	 * @param buyNumber  goods of buy number
	 * @param totalPrice total price
	 * @param indentId   indent of id
	 * @return true: update success or false: update fail
	 */
	@Override
	public boolean updateIndentGoodsTypeAndBuyNumberAndTotalPriceById(String goodsType, int buyNumber, BigDecimal totalPrice, String indentId) {
		return dao.updateIndentGoodsTypeAndBuyNumberAndTotalPriceById(conn, goodsType, buyNumber, totalPrice, indentId);
	}



	/**
	 * remove Indent by id
	 * @param id Indent id
	 * @return true: remove success or false: remove fail
	 */
	@Override
	public boolean removeById(String id) {
		return dao.removeById(conn, id);
	}
}
