package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Display;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayForm {
    private Long displayId;
    private MultipartFile aboutImg01;

    private String aboutText01;
    private String aboutText02;
    private String aboutText03;
    private String aboutText04;

    private MultipartFile aboutImg02;

    private String aboutText05;
    private String aboutText06;

    private String mainStore01;
    private String mainStore02;
    private String mainStore03;
    private String mainStore04;
    private String mainStore05;

    private String mainBook01;
    private String mainBook02;
    private String mainBook03;
    private String mainBook04;
    private String mainBook05;

    //DTO -> Entity
    public Display toEntity(){
        Display display = new Display();
        display.setDisplayId(displayId);
        display.setAboutImg01Org(display.getAboutImg01Org());
        display.setAboutImg01Stored(display.getAboutImg01Stored());
        display.setAboutText01(aboutText01);
        display.setAboutText02(aboutText02);
        display.setAboutText03(aboutText03);
        display.setAboutText04(aboutText04);

        display.setAboutImg02Org(display.getAboutImg02Org());
        display.setAboutImg02Stored(display.getAboutImg02Stored());

        display.setAboutText05(aboutText05);
        display.setAboutText06(aboutText06);

        display.setMainStore01(mainStore01);
        display.setMainStore02(mainStore02);
        display.setMainStore03(mainStore03);
        display.setMainStore04(mainStore04);
        display.setMainStore05(mainStore05);

        display.setMainBook01(mainBook01);
        display.setMainBook02(mainBook02);
        display.setMainBook03(mainBook03);
        display.setMainBook04(mainBook04);
        display.setMainBook05(mainBook05);
        return display;
    }
}
