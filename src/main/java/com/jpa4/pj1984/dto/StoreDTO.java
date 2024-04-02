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

    // sampleDTO 생성자의 매개변수는 entity


}
