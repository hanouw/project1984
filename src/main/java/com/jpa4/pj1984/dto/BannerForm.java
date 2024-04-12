package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Banner;
import com.jpa4.pj1984.domain.BannerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerForm {
    private Long bannerId;
    private String bannerTitle;
    private String bannerDetail;
    private String bannerOrder;
    private String bannerLink;
    // 배너이미지 파일
    private MultipartFile bannerImg;
    private BannerStatus bannerStatus;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime createDate;

    //DTO -> Entity
    public Banner toEntity(){
        Banner banner = new Banner();
        banner.setBannerId(bannerId);
        banner.setBannerTitle(bannerTitle);
        banner.setBannerDetail(bannerDetail);
        banner.setBannerOrder(bannerOrder);
        banner.setBannerLink(bannerLink);
        banner.setBannerImgOrg(bannerImg.getOriginalFilename());
        banner.setBannerImgStored(bannerImg.getName());
        banner.setBannerStatus(bannerStatus);
        return banner;
    }
}
