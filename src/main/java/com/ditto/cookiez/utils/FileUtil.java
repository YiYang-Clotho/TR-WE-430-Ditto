package com.ditto.cookiez.utils;

import com.ditto.cookiez.config.AwsClient;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Zhihao Liang
 * @date 2020/9/29 11:30
 * @email s3798366@student.rmit.edu.au
 */
public class FileUtil {
    public static final String PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/images/";
    public static final String STEP_PREFIX = "stepImg-";
    public static final String COVER = "coverImg";

    public static void fileUpload(byte[] file, String filePath, String fileName) throws IOException {
        //target dir
        File targetfile = new File(filePath);
        if (!targetfile.exists()) {
            targetfile.mkdirs();
        }
        //binary stream input
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String uploadAvatarToAws(MultipartFile file, int userId) throws IOException {
        String fileType = getFileType(Objects.requireNonNull(file.getOriginalFilename()));
        File tempFile = createTempFileForImg(file);
        String url = AwsClient.uploadToS3(tempFile, getAvatarRelativePath(userId, fileType));
        tempFile.delete();
        return url;
    }

    private static File createTempFileForImg(MultipartFile file) throws IOException {
        String fileType = getFileType(Objects.requireNonNull(file.getOriginalFilename()));
        File tempFile = new File(PATH+"temp." + fileType);
        file.transferTo(tempFile);
        return tempFile;
    }

    public static String uploadStepImgToAws(MultipartFile file, int recipeId, int stepOrder) throws IOException {
        String fileType = getFileType(Objects.requireNonNull(file.getOriginalFilename()));
        File tempFile = createTempFileForImg(file);
        String url = AwsClient.uploadToS3(tempFile, getRecipeStepRelativePath(recipeId, stepOrder, fileType));
        tempFile.delete();
        return url;
    }

    public static String uploadCoverToAws(MultipartFile file, int recipeId) throws IOException {
        String fileType = getFileType(Objects.requireNonNull(file.getOriginalFilename()));
        File tempFile = createTempFileForImg(file);
        String url = AwsClient.uploadToS3(tempFile, getRecipeCoverRelativePath(recipeId, fileType));
        tempFile.delete();
        return url;
    }

    public static String getFileTypeHasDot(String fileName) {
//        include "."
        int lastIndexOf = fileName.lastIndexOf(".");
        return fileName.substring(lastIndexOf);
    }

    public static String getFileType(String fileName) {
//       not include "."
        int lastIndexOf = fileName.lastIndexOf(".");
        return fileName.substring(lastIndexOf + 1);
    }

    // expired
    public static String getRecipeDirRelativePath(int recipeId) {
        return "images/recipes/" + recipeId + "/";
    }

    public static String getRecipeStepRelativePath(int recipeId, int order, String imgType) {
        return String.format("images/recipes/%d/step-%d.%s", recipeId, order, imgType);
    }

    public static String getRecipeCoverRelativePath(int recipeId, String imgType) {
        return String.format("images/recipes/%d/cover.%s", recipeId, imgType);
    }

    public static String getAvatarRelativePath(int userId, String imgType) {
        return String.format("avatar/%d/avatar.%s", userId, imgType);
    }

    public static String getAwsUrl(String bucketName) {
        return String.format("https://%s.s3.amazonaws.com", bucketName);
    }


}
