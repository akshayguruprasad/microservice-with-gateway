package com.indream.s3bucket.util;

import com.amazonaws.auth.AWSCredentials;

public class CredientialS3 implements AWSCredentials {

	@Override
	public String getAWSAccessKeyId() {

		return "accesskey";
	}

	@Override
	public String getAWSSecretKey() {
		return "secret-key";
	}

}
