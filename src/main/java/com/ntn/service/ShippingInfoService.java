package com.ntn.service;

import com.ntn.entity.ShippingInfo;
import com.ntn.repository.IShippingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingInfoService implements IShippingInfoService{

    @Autowired
    private IShippingInfoRepository shippingInfoRepository;

    @Override
    public List<ShippingInfo> findShippingInfoByUserId(String userId) {
        return shippingInfoRepository.findShippingInfoByUserId((userId));
    }

    @Override
    public void createShippingInfo(ShippingInfo shippingInfo) {
        shippingInfoRepository.save(shippingInfo);
    }

    @Override
    public void updateShippingInfo(ShippingInfo shippingInfo) {
        shippingInfoRepository.save(shippingInfo);
    }
}
