package com.indream.s3bucket.util;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.amazonaws.auth.AWSCredentials;

public class Utility {

	public static AWSCredentials getCredentials() {
		AWSCredentials a = new AWSCredentials() {

			@Override
			public String getAWSSecretKey() {
				return System.getProperty("secretkey");

			}

			@Override
			public String getAWSAccessKeyId() {
				return System.getProperty("accesskey");
			}
		};

		return a;
	}

	public static InputStream createFile(String userId, String partName, HttpServletRequest request) throws Exception {

		Part part = request.getPart(partName);
		InputStream inputStream = part.getInputStream();

		return inputStream;
	}

}
