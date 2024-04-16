package com.jpa4.pj1984.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Banner extends TimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerId;

    @Column(nullable = false)
    private String bannerTitle;
    @Column(nullable = false)
    private String bannerDetail;
    @Column(nullable = false)
    private String bannerOrder;
    @Column(nullable = false)
    private String bannerLink;
    @Column(nullable = false)
    private String bannerImgOrg;
    @Column(nullable = false)
    private String bannerImgStored;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BannerStatus bannerStatus;
}
