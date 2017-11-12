package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemParam;
import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;

@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/item/param/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult queryItemParam(@PathVariable(value = "itemCatId") Long itemCatId) {
		TaotaoResult result = itemParamService.queryItemParam(itemCatId);
		return result;
	}

	@RequestMapping("/item/param/save/{cid}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable Long cid, String paramData) {
		// 创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}

	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDataGridResult listItemParam(Integer page, Integer rows) {
		EUDataGridResult result = itemParamService.listItemParam(page, rows);
		return result;
	}
}
