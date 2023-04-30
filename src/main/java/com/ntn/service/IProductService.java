package com.ntn.service;

import com.ntn.dto.QueryProductDTO;
import com.ntn.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> getListProducts();

    Page<Product> getListProductsPaging(Pageable pageable);

    Page<Product> getListProductsPaging(Pageable pageable, QueryProductDTO queryProductDTO);

    void createProducts(Product product);

    Product getProductById(Integer id);

    void deleteProduct(Integer id);

    void updateProduct(Product product);

    List<Product> sellingProducts(int limit);

    List<Product> discountProducts();

    List<Product> sellingProducts();

    List<Product> slowestSellingProducts();


}
