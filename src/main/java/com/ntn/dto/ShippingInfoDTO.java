package com.ntn.dto;

import lombok.Data;

@Data
public class ShippingInfoDTO {

    private Integer id;

    private String address;

    private String phoneNumber;

    private Byte onDefault;

    private String userId;

}
