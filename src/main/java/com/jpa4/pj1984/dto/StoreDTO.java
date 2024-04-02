package com.jpa4.pj1984.dto;


import com.jpa4.pj1984.domain.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    private Long storeId;
    private String storeTitle;
    private String storeOwner;
    private String storeCrn;
    private String storeText;
    private Long storePhoneNum;
    private String storeImageName;
    private String storeImageId;
    private String storeInsideImageName01;
    private String storeInsideImageId01;
    private String storeInsideImageName02;
    private String storeInsideImageId02;
    private String storeInsideImageName03;
    private String storeInsideImageId03;
    private String storeAddress;
    private String storeOneReview;
    private String storeReview;
    private StoreStatus storeStatus;
    private String storeOperateTime;
    private String storeTag;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String storeBankName;
    private String storeAccount;

    // sampleDTO 생성자의 매개변수는 entity
    // Entity -> DTO

    public StoreDTO(Store store) {
        this.storeId = store.getStoreId();
        this.storeTitle = store.getStoreTitle();
        this.storeOwner = store.getStoreOwner();
        this.storeCrn = store.getStoreCrn();
        this.storeText = store.getStoreText();
        this.storePhoneNum = store.getStorePhoneNum();
        this.storeImageName = store.getStoreImageName();
        this.storeImageId = store.getStoreImageId();
        this.storeInsideImageName01 = store.getStoreInsideImageName01();
        this.storeInsideImageId01 = store.getStoreInsideImageId01();
        this.storeInsideImageName02 = store.getStoreInsideImageName02();
        this.storeInsideImageId02 = store.getStoreInsideImageId02();
        this.storeInsideImageName03 = store.getStoreInsideImageName03();
        this.storeInsideImageId03 = store.getStoreInsideImageId03();
        this.storeAddress = store.getStoreAddress();
        this.storeOneReview = store.getStoreOneReview();
        this.storeReview = store.getStoreReview();
        this.storeStatus = store.getStoreStatus();
        this.storeOperateTime = store.getStoreOperateTime();
        this.storeTag = store.getStoreTag();
        this.createDate = store.getCreateDate();
        this.lastModifiedDate = store.getLastModifiedDate();
        this.storeBankName = store.getStoreBankName();
        this.storeAccount = store.getStoreAccount();

    }
}
