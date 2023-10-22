package com.hf.minio.service;

import cn.hutool.core.util.StrUtil;
import com.hf.minio.config.MinIOConfig;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.hf.minio.constant.MinIOConstant.ARTWORK_BUCKET_NAME;
import static com.hf.minio.constant.MinIOConstant.ARTWORK_FOLDER;

@Component
public class MinIOService {

    @Autowired
    private MinIOConfig minIOCnfig;

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-Name}")
    private String bucketName;


//    public String updateShareLink(String objectName) {
//        String newLink = generateNewSignedUrl(bucketName, objectName);
//        return newLink;
//    }

    private String getFileName(String url) {
        String fileName = null;
        try {
            URL parsedUrl = new URL(url);
            fileName = new File(parsedUrl.getPath()).getName();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    public String refreshPresignedUrl(String bucketName, String objectName, String oldPresignedUrl) {
        try {
            String newPresignedUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(7, TimeUnit.DAYS)
                    .build());
            return oldPresignedUrl.replaceAll("(?<=\\?)Expires=\\d+", newPresignedUrl.substring(newPresignedUrl.lastIndexOf("=") + 1));
        } catch (MinioException e) {
            throw new RuntimeException("Failed to refresh presigned URL", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to refresh presigned URL", e);
        }
    }

    public String uploadFile(String bucketName, String folder, MultipartFile file) throws Exception {

        String uuid = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filename = uuid + suffix;
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .stream(file.getInputStream(), file.getInputStream().available(), -1)
                .contentType(file.getContentType())
                .build());
        return bucketName + "/" + folder + "/" + filename;
    }

    public InputStream downloadFile(String bucketName, String filename) throws Exception {

        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .build());
    }

    public String generateNewSignedUrl(String bucketName, String folder, String objectName) {
        if (!StrUtil.hasBlank(folder)) {
            List<String> list = Arrays.asList(folder, objectName);
            objectName = StrUtil.join("/",list);
        }
        try {
            String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(7, TimeUnit.DAYS)
                    .build());
            return url;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> paths2Links(List<String> paths) {
        List<String> links = new ArrayList<>();
        for (String url : paths) {
            String link = this.path2Link(url);
            links.add(link);
        }
        return links;
    }

    public String path2Link(String path) {
        return this.generateNewSignedUrl(ARTWORK_BUCKET_NAME, ARTWORK_FOLDER, path);
    }

    public String path2Link(String path, String bucketName) {
        return this.generateNewSignedUrl(bucketName, null, path);
    }

    public String path2Link(String path,String bucketName, String folder) {
        return this.generateNewSignedUrl(bucketName, folder, path);
    }

    public String generatePresignedUrl(String bucketName, String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(7, TimeUnit.DAYS)
                    .build());
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkObjectExists(StatObjectArgs args) {
        try {
            minioClient.statObject(args);
            return true;
        } catch (MinioException e) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
