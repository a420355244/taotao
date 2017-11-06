package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	public Map uploadPicture(MultipartFile uploadFile); 
}
