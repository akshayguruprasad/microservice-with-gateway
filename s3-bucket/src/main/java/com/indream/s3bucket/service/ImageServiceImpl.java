package com.indream.s3bucket.service;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indream.s3bucket.dao.ImageDao;
import com.indream.s3bucket.util.Utility;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;

	@Override
	public String getImage(String versionId, String userId) {
		String fileContents = imageDao.getImage(versionId, userId);
		return fileContents;

	}

	@Override
	public String setImage(String userId, String partName, HttpServletRequest request) {

		InputStream fileToSave = null;
		try {
			fileToSave = Utility.createFile(userId, partName, request);
			String responseVersionId = imageDao.setImage(fileToSave, userId);
			return responseVersionId;
		} catch (Exception e) {
			throw new RuntimeException("failed to save the file");
		}

	}

}
