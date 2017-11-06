package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 查询商品列表
	 * @param parentId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/item/cat/list")
	@ResponseBody
	public List listItemCat(@RequestParam(value="id", defaultValue="0") Long parentId){
		List<Object> resultList = new ArrayList<>();
		List<TbItemCat> tbItemCats = itemCatService.queryTbItemCatListByParentId(parentId);
		for (TbItemCat tbItemCat : tbItemCats) {
			Map<String, Object> node = new HashMap<>();
			node.put("id", tbItemCat.getId());
			node.put("text", tbItemCat.getName());
			//如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
			node.put("state", tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
}
