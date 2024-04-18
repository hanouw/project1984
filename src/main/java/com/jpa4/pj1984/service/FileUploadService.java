package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.ProductFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class FileUploadService {

    // 실제 파일 저장될 경로
    @Value("${file.dir}") // application.yml 파일에 설정한 값을 가져와주는 어노테이션
    private String fileDir;

    // 살제 파일 한개 저장 (실제 저장처리)
    public ProductFile saveFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) { // 파일이 비어있으면 (폼에서 파일 안올렸을때를 대비)
            return null;
        }
        // 파일명 수정
        String orgFileName = multipartFile.getOriginalFilename(); // 원본이름
        String storedFileName = makeFileName(orgFileName); // 서버에 저장할 이미지파일의 이름을 생성

        // 실제 파일 저장 명령!
        multipartFile.transferTo(new File(getPath(storedFileName)));
        return new ProductFile(orgFileName, storedFileName);
    }
    // 파일경로 만들기 메서드
    public String getPath(String fileName) {
        return fileDir + fileName;
    }
    // 저장할 파일명 생성
    private String makeFileName(String orgFileName) {
        String ext = extractExt(orgFileName); // .jpg, .png
        String uuid = UUID.randomUUID().toString(); // 43fw3-43wgfs-6gfgd5-gdfsg43
        return uuid + ext;
    }
    // 파일 확장자명 추출
    private String extractExt(String orgFileName) {
        int idx = orgFileName.lastIndexOf(".");
        return orgFileName.substring(idx);
    }

    // 파일 삭제
    public boolean deleteFile(String storedFileName) throws IOException{
        File file = new File(getPath(storedFileName));
        System.out.println("storedFileName가 삭제됨 = " + storedFileName);
        if (file.exists()){
            return file.delete();
        }
        return false;
    }



}
