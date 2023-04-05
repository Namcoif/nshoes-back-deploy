package com.ntn.controller;

import com.ntn.dto.ProductDTO;
import com.ntn.dto.ShippingInfoDTO;
import com.ntn.entity.Product;
import com.ntn.entity.ShippingInfo;
import com.ntn.service.IShippingInfoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/shipping")
public class ShippingInfoController {

    @Autowired
    private IShippingInfoService shippingInfoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<?> findShoppingInfoByUserId(@PathVariable String userId){
        try {
            List<ShippingInfo> shippingInfos = shippingInfoService.findShippingInfoByUserId(userId);
            List<ShippingInfoDTO> shippingInfoDTOS = modelMapper.map(shippingInfos, new TypeToken<List<ShippingInfoDTO>>() {
            }.getType());
            return ResponseEntity.ok(shippingInfoDTOS);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    @GetMapping("/{userId}/default")
    public ResponseEntity<?> findShippingInfoDefaultByUserId(@PathVariable String userId){
        try {
            ShippingInfo shippingInfo = shippingInfoService.findShippingInfoDefaultByUserId(userId);
            ShippingInfoDTO shippingInfoDTO = modelMapper.map(shippingInfo, ShippingInfoDTO.class);
            return ResponseEntity.ok(shippingInfoDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> createShippingInfo(@RequestBody ShippingInfoDTO shippingInfoDTO){
        try {
            ShippingInfo shippingInfo = modelMapper.map(shippingInfoDTO,ShippingInfo.class);
            shippingInfoService.createShippingInfo(shippingInfo);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully added shipping information!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShippingInfo(@RequestBody ShippingInfoDTO shippingInfoDTO){
        try {
            ShippingInfo shippingInfo = modelMapper.map(shippingInfoDTO,ShippingInfo.class);
            shippingInfoService.createShippingInfo(shippingInfo);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated shipping information!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }
}
