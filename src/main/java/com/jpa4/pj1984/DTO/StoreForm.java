package com.jpa4.pj1984.DTO;


import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.domain.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreForm {
    private Long store_id;
    private String store_title;
    private String user_no;
    private String store_crn;
    private String store_email;
    private Long store_phone_num;
    private String store_image_name;
    private String store_image_id;
    private String store_inside_image_name01;
    private String store_inside_image_id01;
    private String store_inside_image_name02;
    private String store_inside_image_id02;
    private String store_inside_image_name03;
    private String store_inside_image_id03;
    private String store_address;
    private Long bank_id;
    private StoreStatus storeStatus;
    private String store_operate_time;
    private String store_tag;
    private LocalDateTime createDate;
    private String bank_name;
    private String store_account;

    // DTO -> Entity
    public Store toEntity() {
        Store store = new Store();
        store.setStore_id(store_id);
        store.setStore_title(store_title);
        store.setStore_crn(store_crn);
        store.setStore_email(store_email);
        store.setStore_phone_num(store_phone_num);
        store.setStore_image_name(store_image_name);
        store.setStore_image_id(store_image_id);
        store.setStore_inside_image_name01(store_inside_image_name01);
        store.setStore_inside_image_id01(store_inside_image_id01);
        store.setStore_inside_image_name01(store_inside_image_name02);
        store.setStore_inside_image_id01(store_inside_image_id02);
        store.setStore_inside_image_name01(store_inside_image_name03);
        store.setStore_inside_image_id01(store_inside_image_id03);
        store.setStore_address(store_address);
        store.setStore_status(storeStatus);
        store.setStore_operate_time(store_operate_time);
        store.setStore_tag(store_tag);
        store.setBank_name(bank_name);
        store.setStore_account(store_account);
        return store;
    }

}
