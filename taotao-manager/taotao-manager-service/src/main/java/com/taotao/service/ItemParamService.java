package com.taotao.service;

import com.taotao.pojo.TbItemParam;
import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;

public interface ItemParamService {
	TaotaoResult queryItemParam(Long itemCatId);

	TaotaoResult insertItemParam(TbItemParam itemParam);
	
	EUDataGridResult listItemParam(Integer page, Integer rows);
}
