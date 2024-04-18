package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.ProductFile;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.domain.StoreReview;
import com.jpa4.pj1984.domain.StoreStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO { // 가져오는
    private Long storeId;
    private String storeLoginId;
    private String storeTitle;
    private String storeOwner;
    private String storeCrn;
    private String storeText;
    private String storePhoneNum;
    private String storeAddress;
    private String storeOneReview;
    private String storeReview;
    private StoreStatus storeStatus;
    private String storeOperateTime;
    private String storeTag;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String storeAccount;
    private String storeBankName;
    // 찢어야 함
    private String storeImageOrigin;
    private String storeImageStored;
    private String storeImageOrigin01;
    private String storeImageStored01;
    private String storeImageOrigin02;
    private String storeImageStored02;
    private String storeImageOrigin03;
    private String storeImageStored03;

    private List<StoreReview> storeUserReviews;

    // sampleDTO 생성자의 매개변수는 entity
    // Entity -> DTO
    // 생성자 : Store 엔티티 -> StoreForm으로 변경해 화면에 전달
    public StoreDTO(Store store) {
        this.storeId = store.getStoreId();
        this.storeLoginId = store.getStoreLoginId();
        this.storeTitle = store.getStoreTitle();
        this.storeOwner = store.getStoreOwner();
        this.storeCrn = store.getStoreCrn();
        this.storeText = store.getStoreText();
        this.storePhoneNum = store.getStorePhoneNum();
        this.storeAddress = store.getStoreAddress();
        this.storeOneReview = store.getStoreOneReview();
        this.storeReview = store.getStoreReview();
        this.storeStatus = store.getStoreStatus();
        this.storeOperateTime = store.getStoreOperateTime();
        this.storeTag = store.getStoreTag();
        this.createDate = store.getCreateDate();
        this.lastModifiedDate = store.getLastModifiedDate();
        this.storeAccount = store.getStoreAccount();
        this.storeBankName = store.getStoreBankName();
        this.storeImageOrigin = store.getStoreImageOrigin();
        this.storeImageStored = store.getStoreImageStored();
        this.storeImageOrigin01 = store.getStoreImageOrigin01();
        this.storeImageStored01 = store.getStoreImageStored01();
        this.storeImageOrigin02 = store.getStoreImageOrigin02();
        this.storeImageStored02 = store.getStoreImageStored02();
        this.storeImageOrigin03 = store.getStoreImageOrigin03();
        this.storeImageStored03 = store.getStoreImageStored03();

        this.storeUserReviews = store.getStoreUserReviews();
    }
}
