package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.result.EUDataGridResult;

public interface ItemService {
	/**
	 * 根据商品id获取商品
	 * 
	 * @param itemId
	 * @return
	 */
	TbItem getItemById(long itemId);

	/**
	 * 分页查询商品信息
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getItemList(int page, int rows);

	/**
	 * 添加商品
	 * 
	 * @param item
	 * @param desc
	 * @param itemParams
	 */
	void saveItem(TbItem item, String desc, String itemParams);
}
