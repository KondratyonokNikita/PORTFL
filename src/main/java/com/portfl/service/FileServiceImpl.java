package com.portfl.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.FileUtils;
import com.portfl.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static com.portfl.constants.Constants.cloud.*;


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
        Map params= ObjectUtils.asMap(CLOUD_PUBLIC_ID,env.getProperty(CLOUD_NAME));
        File file = new File(CLOUD_FILE);
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        uploadResult=cloudinary.uploader().upload(file, params);
        return String.valueOf(uploadResult.get("secure_url"));
    }

    @Bean
    public Cloudinary createCloud(){
        return new Cloudinary(ObjectUtils.asMap(
                CLOUD_NAME, env.getProperty("cloud_name"),
                CLOUD_API_KEY, env.getProperty("cloud_api_key"),
                CLOUD_API_SECRET, env.getProperty("cloud_api_secret")));
    }
}
