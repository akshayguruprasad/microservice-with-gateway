package com.indream.s3bucket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indream.s3bucket.service.ImageService;

@RestController
public class S3ImageController {

	@Autowired
	private ImageService imageService;

	@GetMapping("/getImage/{versionId}/{userId}")
	public String getImage(@PathVariable String versionId, @PathVariable String userId) {
		String content = imageService.getImage(versionId, userId);
		return content;
	}

	@PostMapping("/setImage/{partName}/{userId}")
	public String saveImage(@PathVariable String userId, @PathVariable String partName, HttpServletRequest request) {
		return imageService.setImage(userId, partName, request);
	}

}
