package com.example.littlekitchen.controller;

import com.example.littlekitchen.Utils.QiniuCloudUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/littlekitchen")
public class UploadController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/uploadImage")
    public String uploadImage(MultipartFile file){
        if(Objects.isNull(file) || file.isEmpty()){
            logger.info("上传图片失败");
            return null;
        }
        String url = null;
        try{
            byte[] bytes = file.getBytes();
             url = QiniuCloudUtil.put64image(bytes, String.valueOf(new Date().getTime()));
             logger.info("imgurl" + url);
        }catch(Exception e){
            e.printStackTrace();
        }

        return url;
    }
}
