package com.ntn.service;


import com.ntn.dto.QueryCartDTO;
import com.ntn.dto.QueryOrderDTO;
import com.ntn.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICartService {
    List<Cart> getListCarts();

    void addProductToCart(Cart cart);

    void removeProductFromCartById(Integer id);

    Page<Cart> getListCartsByUserId(Pageable pageable, String userId);

    Page<Cart> getListCartsByProductId(Pageable pageable, Integer productId);

    Page<Cart> searchCarts(Pageable pageable, QueryCartDTO queryCartDTO);

    void buyProduct(Integer id);
}
