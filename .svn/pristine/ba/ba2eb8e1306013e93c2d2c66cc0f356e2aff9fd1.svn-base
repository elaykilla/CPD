package services;


import play.Logger;
import play.Play;
import play.exceptions.ConfigurationException;
import java.io.File;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AWSHelper {

	public static final String AWS_S3_BUCKET = "aws.s3.bucket";
	public static final String AWS_ACCESS_KEY = "aws.access.key";
	public static final String AWS_SECRET_KEY = "aws.secret.key";
	public static AmazonS3 amazonS3;

	public static String s3Bucket;

	public static void init() {
		String accessKey = Play.configuration.getProperty(AWS_ACCESS_KEY);
		String secretKey = Play.configuration.getProperty(AWS_SECRET_KEY);
		s3Bucket = Play.configuration.getProperty(AWS_S3_BUCKET);
		if (!Play.configuration.containsKey(AWS_ACCESS_KEY)) {
			throw new ConfigurationException(
					"Bad configuration for s3: no access key");
		} else if (!Play.configuration.containsKey(AWS_SECRET_KEY)) {
			throw new ConfigurationException(
					"Bad configuration for s3: no secret key");
		} else if (!Play.configuration.containsKey(AWS_S3_BUCKET)) {
			throw new ConfigurationException(
					"Bad configuration for s3: no s3 bucket");
		}
		if ((accessKey != null) && (secretKey != null)) {
			AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,
					secretKey);
			amazonS3 = new AmazonS3Client(awsCredentials);
			// amazonS3.createBucket(s3Bucket);
//			Logger.info("Using S3 Bucket: " + s3Bucket);
		}

	}

	public static void saveFile(String name, File file) {
		init();

		PutObjectRequest putObjectRequest = new PutObjectRequest(s3Bucket,
				name, file);
		putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead); // public
																			// for
																			// all
		amazonS3.putObject(putObjectRequest); // upload file

	}

	public static void deleteFile(String name) {
		init();
		
		amazonS3.deleteObject(s3Bucket, name);
//		Logger.info("Using S3 Bucket: deleting" + name);


	}

	public static String getUrl(String name) {
		init();
 		return "https://s3.amazonaws.com/" + s3Bucket + "/" + name;
	}
}
