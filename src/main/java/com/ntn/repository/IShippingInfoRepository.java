package com.ntn.repository;

import com.ntn.entity.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShippingInfoRepository extends JpaRepository<ShippingInfo,Integer> {
    @Query("SELECT si FROM ShippingInfo si WHERE si.user.userId=:userId")
    List<ShippingInfo> findShippingInfoByUserId(@Param("userId") String userId);

    @Query("SELECT si FROM ShippingInfo si WHERE si.user.userId=:userId AND si.onDefault=1")
    ShippingInfo findShippingInfoDefaultByUserId(@Param("userId") String userId);
}
