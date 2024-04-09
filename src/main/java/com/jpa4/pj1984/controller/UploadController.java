package com.jpa4.pj1984.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class UploadController {
    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles){
        for (MultipartFile uploadFile: uploadFiles){
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

        }
    }
}
