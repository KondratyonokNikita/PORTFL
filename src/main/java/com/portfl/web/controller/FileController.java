package com.portfl.web.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.portfl.constants.Constants;
import com.portfl.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.invoke.empty.Empty;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by User on 14.04.2017.
 */

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/photo")
    public String photo() {
        return Constants.Views.PHOTO_PAGE;
    }

    @RequestMapping(value = "/loadFile", method = RequestMethod.POST)
    public @ResponseBody String loadFile(MultipartHttpServletRequest request) throws IOException {
        return fileService.fileUpload(request);
    }


}
