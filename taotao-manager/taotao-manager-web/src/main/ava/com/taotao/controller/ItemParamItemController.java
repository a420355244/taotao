package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;

@Controller
public class ItemParamItemController {
	@Autowired
	private ItemParamItemService itemParamItemService;

	@RequestMapping(value = "/item/param/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String string = itemParamItemService.getItemParamItemByItemId(itemId);
		model.addAttribute("itemParam", string);
		return "item";
	}
}
