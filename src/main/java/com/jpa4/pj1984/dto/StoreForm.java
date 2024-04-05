package com.jpa4.pj1984.dto;


import com.jpa4.pj1984.domain.ProductFile;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.domain.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class StoreForm { // 뿌려주는
    private Long storeId;
    private String storeLoginId;
    private String storePassword;
    private String storeTitle;
    private String storeEmail;
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
    // 새로 등록 or 수정
    private MultipartFile newStoreImage;
    private MultipartFile newStoreImage01;
    private MultipartFile newStoreImage02;
    private MultipartFile newStoreImage03;


    // Form -> Entity
    public Store toEntity() {
        Store store = new Store();
        store.setStoreId(storeId);
        store.setStoreLoginId(storeLoginId);
        store.setStorePassword(storePassword);
        store.setStoreTitle(storeTitle);
        store.setStoreOwner(storeOwner);
        store.setStoreCrn(storeCrn);
        store.setStoreEmail(storeEmail);
        store.setStoreText(storeText);
        store.setStorePhoneNum(storePhoneNum);
        store.setStoreAddress(storeAddress);
        store.setStoreOneReview(storeOneReview);
        store.setStoreReview(storeReview);
        store.setStoreStatus(storeStatus);
        store.setStoreOperateTime(storeOperateTime);
        store.setStoreTag(storeTag);
        store.setStoreAccount(storeAccount);
        store.setStoreBankName(storeBankName);
        return store;
    }
    public Store toSignupEntity() {
        Store storeSignup = new Store();
        storeSignup.setStoreLoginId(storeLoginId);
        storeSignup.setStorePassword(storePassword);
        storeSignup.setStoreTitle(storeTitle);
        storeSignup.setStoreOwner(storeOwner);
        storeSignup.setStoreCrn(storeCrn);
        storeSignup.setStoreEmail(storeEmail);
        storeSignup.setStorePhoneNum(storePhoneNum);
        storeSignup.setStoreStatus(storeStatus);
        storeSignup.setStoreStatus(StoreStatus.STORE);
        return storeSignup;
    }
}
