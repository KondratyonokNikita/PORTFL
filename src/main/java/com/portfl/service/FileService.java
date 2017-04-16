package com.portfl.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

/**
 * Created by User on 14.04.2017.
 */
public interface FileService {
    String fileUpload(MultipartHttpServletRequest request) throws IOException;
}
