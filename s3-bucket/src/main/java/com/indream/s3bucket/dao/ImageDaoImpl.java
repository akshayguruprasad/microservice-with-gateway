package com.indream.s3bucket.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Component
public class ImageDaoImpl implements ImageDao {

	private static AmazonS3 client;
	private static final String BUCKET_NAME = "temps3-image";

	@PostConstruct
	public void afterPropertiesSet() {
		client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials("AKIAI2HO4PWYHWFDAK6Q", "oW3lw+hY4Esc3KH/nW5r+JUAZpZ7OMngZ2SiAePK")))
				.withRegion(Regions.AP_SOUTH_1).build();

	}

	@Override
	public String getImage(String versionId, String userId) {
		try {
			GetObjectRequest getObjectRequest = new GetObjectRequest(BUCKET_NAME, userId, versionId);
			S3Object s3Object = client.getObject(getObjectRequest);
			S3ObjectInputStream inputStream = s3Object.getObjectContent();
			int c = -1;
			System.out.println(inputStream.available());
			List<Byte> dataByte = new ArrayList<Byte>();
			while ((c = inputStream.read()) != -1) {
				dataByte.add((byte) c);
			}
			System.out.println(dataByte.size());
			byte[] data = new byte[dataByte.size()];
			for (int j = 0; j < data.length; j++) {
				data[j] = dataByte.get(j);
			}
			String d = Base64.getEncoder().encodeToString(data);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("---------------");
			throw new RuntimeException("Failed to read the contents of the file");
		}

	}

	@Override
	public String setImage(InputStream image, String userId) {
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentType("image/jpeg");
		try {
			metaData.setContentLength(image.available());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			PutObjectRequest putObjectRequest;
			ObjectMetadata objectMeta = new ObjectMetadata();
			objectMeta.setContentType("image/jpeg");

			putObjectRequest = new PutObjectRequest(BUCKET_NAME, userId, image, objectMeta);
			PutObjectResult res = client.putObject(putObjectRequest);
			System.out.println("Version id for the file : " + res.getVersionId());
			return res.getVersionId();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.out.println("------------");
			throw new RuntimeException("Failed to save the contents of the file");
		}

	}

}
