package com.ntn.service;

import com.ntn.entity.ShippingInfo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShippingInfoService {
    List<ShippingInfo> findShippingInfoByUserId(String userId);
    ShippingInfo findShippingInfoDefaultByUserId(String userId);

    void createShippingInfo(ShippingInfo shippingInfo);
    void updateShippingInfo(ShippingInfo shippingInfo);
}
