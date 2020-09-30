package com.ditto.cookiez.config;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.TransferManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * @author Zhihao Liang
 * @date 2020/9/30 16:32
 * @email s3798366@student.rmit.edu.au
 */
public class AwsClient {
    static AmazonS3 s3;
    static TransferManager tx;
    private static String AWS_ACCESS_KEY = "ASIATYM2HCSNYIWUR34D";
    private static String AWS_SECRET_KEY = "QqKGnPmATt5slfe7Aot1PEL731XyzOkg3mkoleOO";
    static final String bucketName = "cookiez-img";

    static {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();

    }

    /**
     * @param @param  tempFile 目标文件
     * @param @param  remoteFileName 文件名
     * @param @return
     * @param @throws IOException    设定文件
     * @return String    返回类型
     * @throws
     * @Title: uploadToS3
     * @Description: 将文件上传至S3上并且返回url
     */
    public static String uploadToS3(File tempFile, String remoteFileName) throws IOException {
        try {
            //上传文件
            s3.putObject(new PutObjectRequest(bucketName, remoteFileName, tempFile).withCannedAcl(CannedAccessControlList.PublicRead));
            //获取一个request
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                    bucketName, remoteFileName);
            //生成公用的url
            URL url = s3.generatePresignedUrl(urlRequest);
            System.out.println("=========URL=================" + url + "============URL=============");
            return url.toString();
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
        return null;
    }

    /**
     * @param @param  remoteFileName
     * @param @throws IOException    设定文件
     * @return S3ObjectInputStream    返回类型  数据流
     * @throws
     * @Title: getContentFromS3
     * @Description: 获取文件2进制流
     */
    public static S3ObjectInputStream getContentFromS3(String remoteFileName) throws IOException {
        try {
            GetObjectRequest request = new GetObjectRequest(bucketName, remoteFileName);
            S3Object object = s3.getObject(request);
            S3ObjectInputStream inputStream = object.getObjectContent();
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param @param  remoteFileName 文件名
     * @param @param  path 下载的路径
     * @param @throws IOException    设定文件
     * @return void    返回类型
     * @throws
     * @Title: downFromS3
     * @Description: 将文件下载到本地路径
     */
    public static void downFromS3(String remoteFileName, String path) throws IOException {
        try {
            GetObjectRequest request = new GetObjectRequest(bucketName, remoteFileName);
            s3.getObject(request, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param @param  remoteFileName 文件名
     * @param @return
     * @param @throws IOException    设定文件
     * @return String    返回类型
     * @throws
     * @Title: getUrlFromS3
     * @Description: 获取文件的url
     */
    public static String getUrlFromS3(String remoteFileName) throws IOException {
        try {
            GeneratePresignedUrlRequest httpRequest = new GeneratePresignedUrlRequest(bucketName, remoteFileName);
            String url = s3.generatePresignedUrl(httpRequest).toString();//临时链接
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证s3上是否存在名称为bucketName的Bucket
     *
     * @param s3
     * @param bucketName
     * @return
     */
    public static boolean checkBucketExists(AmazonS3 s3, String bucketName) {
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket bucket : buckets) {
            if (Objects.equals(bucket.getName(), bucketName)) {
                return true;
            }
        }
        return false;
    }

    public static void delFromS3(String remoteFileName) throws IOException {
        try {
            s3.deleteObject(bucketName, remoteFileName);
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        String key = "redisinfo";
//        File tempFile = new File("C:\\Users\\guosen\\Desktop\\redis.txt");
//        uploadToS3(tempFile, key);//上传文件
//        String url = getUrlFromS3(key);//获取文件的url
//        System.out.println(url);
   AwsClient.uploadToS3(new File("D:\\1.txt"),"image/1.txt");

//    	delFromS3(key);//删除文件
    }
}
