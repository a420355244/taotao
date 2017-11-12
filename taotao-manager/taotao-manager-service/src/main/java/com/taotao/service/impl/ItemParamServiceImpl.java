package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public TaotaoResult queryItemParam(Long itemCatId) {
		TbItemParamExample tbItemParamExample = new TbItemParamExample();
		Criteria criteria = tbItemParamExample.createCriteria();
		criteria.andItemCatIdEqualTo(itemCatId);
		List<TbItemParam> itemParam = itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
		if (itemParam == null || itemParam.size() == 0) {
			return TaotaoResult.ok();
		}
		return TaotaoResult.ok(itemParam.get(0));
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		// 补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		// 插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();

	}

	@Override
	public EUDataGridResult listItemParam(Integer page, Integer rows) {
		EUDataGridResult euDataGridResult = new EUDataGridResult();
		//查询商品描述
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> itemParamList = itemParamMapper.selectByExampleWithBLOBs(example);
		euDataGridResult.setRows(itemParamList);
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(itemParamList);
		euDataGridResult.setTotal(pageInfo.getTotal());
		return euDataGridResult;
	}

}
