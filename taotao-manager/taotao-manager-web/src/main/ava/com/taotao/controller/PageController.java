package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.EUDataGridResult;
import com.taotao.service.ItemService;

@Controller
public class PageController {
	@Autowired
	private ItemService itemService;

	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/")
	public String showIndex() {
		return "index";
	}

	/**
	 * 跳转到各个页面
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}")
	public String showPage(@PathVariable(value = "page") String page) {
		return page;
	}

	/**
	 * 展示商品列表
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/item/list")
	@ResponseBody
	public EUDataGridResult listItem(int page, int rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
}
