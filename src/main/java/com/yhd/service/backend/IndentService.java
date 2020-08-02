package com.yhd.service.backend;

import com.yhd.pojo.Indent;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 13:42
 * @Since 1.8
 */
public interface IndentService {

	/**
	 * get Indents By StatusId、GoodsName、UserId、Id。 if is null be not join check queue
	 * @param statusId status of id
	 * @param goodsName goods of name
	 * @param userId user of id
	 * @param id my id
	 * @return indent instance gather List
	 */
	List<Indent> getListByStatusAndGoodsIdAndUserIdAndId(int statusId, String goodsName, String userId, String id, int pageSize, int pageCount);
	/**
	 * get Indents count By StatusId、GoodsName、UserId、Id。 if is null be not join check queue
	 * @param statusId status of id
	 * @param goodsName goods of name
	 * @param userId user of id
	 * @param id my id
	 * @return indent count
	 */
	long getCountByStatusAndGoodsIdAndUserIdAndId(int statusId, String goodsName, String userId, String id);

	/**
	 *  update indent of goodsType, buyNumber, totalPrice by indent of id
	 * @param goodsType goods of style and kind
	 * @param buyNumber goods of buy number
	 * @param totalPrice total price
	 * @param indentId indent of id
	 * @return  true: update success or false: update fail

	 */
	boolean updateIndentGoodsTypeAndBuyNumberAndTotalPriceById(String goodsType, int buyNumber, BigDecimal totalPrice, String indentId);

	/**
	 * Indent shipment from 确认订单 to 已发货
	 * @param id Indent of id
	 * @return true: shipment success or false: shipment fail
	 */
	boolean shipments(String id);

	/**
	 * get Indent by id
	 * @param id indent of id
	 * @return indent instance
	 */
	Indent getIndentById(String id);

	/**
	 * remove Indent by id
	 * @param id Indent id
	 * @return true: remove success or false: remove fail
	 */
	boolean removeById(String id);
}
