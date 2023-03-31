package com.ntn.service;

import com.ntn.entity.ShippingInfo;

import java.util.List;

public interface IShippingInfoService {
    List<ShippingInfo> findShippingInfoByUserId(String userId);
    void createShippingInfo(ShippingInfo shippingInfo);
    void updateShippingInfo(ShippingInfo shippingInfo);
}
