package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Display;
import com.jpa4.pj1984.domain.ProductFile;
import com.jpa4.pj1984.dto.DisplayDTO;
import com.jpa4.pj1984.dto.DisplayForm;
import com.jpa4.pj1984.repository.DisplayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DisplayService {
    private final DisplayRepository displayRepository;
    private final FileUploadService fileUploadService;

    // 상세
    public DisplayDTO findOne() {
        Display display = displayRepository.findById(1L).orElse(null);
        return new DisplayDTO(display);
    }

    // 수정
    public void updateOne(DisplayForm displayForm) throws IOException {
        Display display = displayRepository.findById(displayForm.getDisplayId()).orElse(null);

        if(displayForm.getAboutImg01() != null){
            if (display != null && !display.getAboutImg01Stored().isEmpty()){
                boolean deleteImg = fileUploadService.deleteFile(display.getAboutImg01Stored());
                if (deleteImg){
                    ProductFile aboutImg01 = fileUploadService.saveFile(displayForm.getAboutImg01());
                    display.setAboutImg01Org(aboutImg01.getOrgFileName());
                    display.setAboutImg01Stored(aboutImg01.getStoredFileName());
                }
            }
        }
        if(displayForm.getAboutImg02() != null){
            if (display != null && !display.getAboutImg02Stored().isEmpty()){
                boolean deleteImg = fileUploadService.deleteFile(display.getAboutImg02Stored());
                if (deleteImg){
                    ProductFile aboutImg02 = fileUploadService.saveFile(displayForm.getAboutImg02());
                    display.setAboutImg02Org(aboutImg02.getOrgFileName());
                    display.setAboutImg02Stored(aboutImg02.getStoredFileName());
                }
            }
        }
        // 저장
        display.setAboutText01(displayForm.getAboutText01());
        display.setAboutText02(displayForm.getAboutText02());
        display.setAboutText03(displayForm.getAboutText03());
        display.setAboutText04(displayForm.getAboutText04());
        display.setAboutText05(displayForm.getAboutText05());
        display.setAboutText06(displayForm.getAboutText06());
        display.setMainBook01(displayForm.getMainBook01());
        display.setMainBook02(displayForm.getMainBook02());
        display.setMainBook03(displayForm.getMainBook03());
        display.setMainBook04(displayForm.getMainBook04());
        display.setMainBook05(displayForm.getMainBook05());
        display.setMainStore01(displayForm.getMainStore01());
        display.setMainStore02(displayForm.getMainStore02());
        display.setMainStore03(displayForm.getMainStore03());
        display.setMainStore04(displayForm.getMainStore04());
        display.setMainStore05(displayForm.getMainStore05());
        System.out.println("전시관리 수정 처리");
    }
}
