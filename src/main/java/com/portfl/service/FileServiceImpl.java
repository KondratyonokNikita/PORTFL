package com.portfl.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by User on 15.04.2017.
 */

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private Environment env;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String fileUpload(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> iterator = request.getFileNames();
        String name=new String();
        while(iterator.hasNext()){
            name=iterator.next();
        }
        MultipartFile multipartFile = request.getFile(name);
        Map uploadResult;
        Map params= ObjectUtils.asMap("public_id",env.getProperty("cloud_name"));
        File file = new File("temp.jpg");
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        uploadResult=cloudinary.uploader().upload(file, params);
        return String.valueOf(uploadResult.get("secure_url"));
    }

    @Bean
    public Cloudinary createCloud(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", env.getProperty("cloud_name"),
                "api_key", env.getProperty("cloud_api_key"),
                "api_secret", env.getProperty("cloud_api_secret")));
    }
}
