package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.result.EUDataGridResult;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		//添加查询条件
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
		//查询商品列表
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> itemList = itemMapper.selectByExample(example);
		//返回界面对象
		EUDataGridResult euDataGridResult = new EUDataGridResult();
		euDataGridResult.setRows(itemList);
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
		euDataGridResult.setTotal(pageInfo.getTotal());
		return euDataGridResult;
	}

}
