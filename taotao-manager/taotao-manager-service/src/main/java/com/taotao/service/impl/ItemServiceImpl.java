package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(long itemId) {

		// TbItem item = itemMapper.selectByPrimaryKey(itemId);
		// 添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> itemList = itemMapper.selectByExample(example);
		// 返回界面对象
		EUDataGridResult euDataGridResult = new EUDataGridResult();
		euDataGridResult.setRows(itemList);
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
		euDataGridResult.setTotal(pageInfo.getTotal());
		return euDataGridResult;
	}

	@Override
	public TaotaoResult saveItem(TbItem item, String desc, String itemParams) throws Exception {
		// 保存商品
		Date date = new Date();
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);
		TaotaoResult result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		result = insertItemParamItem(itemId, itemParams);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		return result;
	}

	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		// 保存商品描述
		TbItemDesc tbItemDesc = new TbItemDesc();
		// 获得一个商品id
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		// 插入数据
		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
	}

	private TaotaoResult insertItemParamItem(Long itemId, String itemParams) {
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParams);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}

}
