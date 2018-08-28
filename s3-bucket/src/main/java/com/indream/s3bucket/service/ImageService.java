package com.indream.s3bucket.service;

import javax.servlet.http.HttpServletRequest;

public interface ImageService {
	String getImage(String versionId, String userId);


	String setImage(String userId, String partName, HttpServletRequest request);
}
