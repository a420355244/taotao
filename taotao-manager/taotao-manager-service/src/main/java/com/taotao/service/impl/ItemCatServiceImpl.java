package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taotao.service.ItemCatService#queryTbItemCatListByParentId(long)
	 */
	@Override
	public List<TbItemCat> queryTbItemCatListByParentId(long parentId) {
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		Criteria tbItemCriteria = tbItemCatExample.createCriteria();
		tbItemCriteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
		return tbItemCatList;
	}

}
