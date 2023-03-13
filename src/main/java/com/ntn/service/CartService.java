package com.ntn.service;

import com.ntn.dto.CartDTO;
import com.ntn.dto.QueryCartDTO;
import com.ntn.dto.QueryOrderDTO;
import com.ntn.entity.Cart;
import com.ntn.repository.ICartRepository;
import com.ntn.specification.CartSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Cart> getListCarts() {
        return cartRepository.findAll();
    }

    @Override
    public void addProductToCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCartById(Integer id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Page<Cart> getListCartsByUserId(Pageable pageable, String userId) {

        Specification where = CartSpecification.getListCartsByUserId(userId);

        return cartRepository.findAll(where, pageable);

    }

    @Override
    public Page<Cart> getListCartsByProductId(Pageable pageable, Integer productId) {
        Specification where = CartSpecification.getListCartsByProductId(productId);

        return cartRepository.findAll(where, pageable);
    }

    @Override
    public Page<Cart> searchCarts(Pageable pageable, QueryCartDTO queryCartDTO) {

            Specification where = CartSpecification.searchCarts(queryCartDTO);

            Page<Cart> cartPage = null;

            cartPage = cartRepository.findAll(where, pageable);

            return cartPage;

    }

    @Override
    public void buyProduct(Integer id) {

    }
}
