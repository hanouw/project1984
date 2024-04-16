package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Banner;
import com.jpa4.pj1984.domain.BannerStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerDTO {
    private Long bannerId;
    private String bannerTitle;
    private String bannerDetail;
    private String bannerOrder;
    private String bannerLink;
    private String bannerImgOrg;
    private String bannerImgStored;
    private BannerStatus bannerStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    //Entity -> DTO
    public BannerDTO(Banner banner){
        this.bannerId = banner.getBannerId();
        this.bannerTitle = banner.getBannerTitle();
        this.bannerDetail = banner.getBannerDetail();
        this.bannerOrder = banner.getBannerOrder();
        this.bannerLink = banner.getBannerLink();
        this.bannerImgOrg = banner.getBannerImgOrg();
        this.bannerImgStored = banner.getBannerImgStored();
        this.bannerStatus = banner.getBannerStatus();
        this.createDate = banner.getCreateDate();
        this.lastModifiedDate = banner.getLastModifiedDate();
    }

}
