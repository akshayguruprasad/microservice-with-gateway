package com.indream.s3bucket.dao;

import java.io.InputStream;

public interface ImageDao {

	String getImage(String versionId, String userId);

	String setImage(InputStream file, String userId);
}
