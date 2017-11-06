package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;

@Controller
public class PictureController {
	@Autowired
	private PictureService pictureService;
	@RequestMapping(value = "/pic/upload")
	@ResponseBody
	public Map uploadPciture(MultipartFile uploadFile){
		Map resultMap = pictureService.uploadPicture(uploadFile);
		return resultMap;
	}
}
