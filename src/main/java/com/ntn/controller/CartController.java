package com.ntn.controller;

import com.ntn.dto.CartDTO;
import com.ntn.dto.QueryCartDTO;
import com.ntn.entity.Cart;
import com.ntn.service.ICartService;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/carts")
public class CartController {

    static JSONObject message = new JSONObject();
    @Autowired
    private ICartService cartService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CartDTO> getListCarts() {
        List<Cart> carts = cartService.getListCarts();
        return modelMapper.map(carts, new TypeToken<List<CartDTO>>() {
        }.getType());
    }

//    @GetMapping(value = "/paging")
//    public ResponseEntity<?> getListCartsByUserId(Pageable pageable, @RequestBody(required = false) QueryCartDTO queryCartDTO) {
//        try {
//            Page<Cart> cartPage = null;
//            if (queryCartDTO != null) {
//                cartPage = cartService.getListCartsByUserId(pageable, queryCartDTO.getUserId());
//            } else {
//                cartPage = cartService.getListCartsByUserId(pageable, null);
//            }
//            List<CartDTO> cartDTOList = modelMapper.map(cartPage.getContent(), new TypeToken<List<CartDTO>>() {
//            }.getType());
//
//            Page<CartDTO> cartDTOPage = new PageImpl<>(cartDTOList, pageable, cartPage.getTotalElements());
//
//            return ResponseEntity.ok(cartDTOPage);
//        } catch (Exception ignored) {
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ignored.toString());
//        }
//    }

    @GetMapping(value = "/paging")
    public ResponseEntity<?> searchCarts(Pageable pageable, @PathParam("query") QueryCartDTO queryCartDTO) {
//        if (userId==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing UserID!");
        try {
            Page<Cart> cartPage = cartService.searchCarts(pageable, queryCartDTO);
            List<CartDTO> cartDTOList = modelMapper.map(cartPage.getContent(), new TypeToken<List<CartDTO>>() {
            }.getType());

            Page<CartDTO> cartDTOPage = new PageImpl<>(cartDTOList, pageable, cartPage.getTotalElements());
            return ResponseEntity.status(HttpStatus.OK).body(cartDTOPage);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    @PostMapping(value = "/remove/{id}")
    public ResponseEntity<?> deleteCartById(@PathVariable Integer id) throws JSONException {
        message = new JSONObject();
        try {
            message.put("resultText", "Product removed from cart successfully!");
            cartService.removeProductFromCartById(id);
            return ResponseEntity.ok(message.toString());
        } catch (Exception e) {
            message.put("resultText", "Remove product FAIL!");
            return ResponseEntity.ok(message.toString());
        }
    }

    @PostMapping(value = "/add-to-card")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDTO cartDTO) throws JSONException {
//        public ResponseEntity<?> addProductToCart(@RequestBody CartDTO cartDTO, @PathParam("userId") String userId) throws JSONException {

        message = new JSONObject();

        try {
            message.put("resultText", "Add product successfully!");

            Cart cart = modelMapper.map(cartDTO, Cart.class);

            cartService.addProductToCart(cart);

            return ResponseEntity.status(HttpStatus.OK).body(message.toString());

        } catch (Exception e) {
            message.put("resultText", "Add product FAIL!");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

//    @GetMapping(value = "/{username}")
//    public ResponseEntity<?> getListCartsByUserId(@PathVariable String username, @PathParam("userId") String userId, Pageable pageable){
//        try {
//            Page<Cart> cartPage= cartService.getListCartsByUserId(pageable, userId);
//
//            List<CartDTO> cartDTOs= modelMapper.map(cartPage.getContent(),new TypeToken<List<CartDTO>>() {}.getType());
//
//            Page<CartDTO> cartDTOPage = new PageImpl<>(cartDTOs, pageable, cartPage.getTotalElements());
//
//            return ResponseEntity.status(HttpStatus.OK).body(cartDTOPage);
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
//        }
//    }
}
