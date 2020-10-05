package com.ditto.cookiez.utils;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Zhihao Liang
 * @date 2020/9/29 11:30
 * @email s3798366@student.rmit.edu.au
 */
public class FileUtil {
   public static final String PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/images/";
    public static void fileupload(byte[] file,String filePath,String fileName) throws IOException {
        //target dir
        File targetfile = new File(filePath);
        if(!targetfile.exists()) {
            targetfile.mkdirs();
        }

        //binary stream input
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    public  static  String getFileType(String fileName){
//        include "."
        int lastIndexOf = fileName.lastIndexOf(".");
        return fileName.substring(lastIndexOf);
    }

}
