package com.ntn.repository;

import com.ntn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
//    @Query("SELECT pr FROM Product pr ORDER BY pr.soldCount DESC LIMIT=:paramLimit")
//    List<Product> sellingProducts(@Param("paramLimit") Integer limit);

    @Procedure(name = "discountProducts")
    List<Product> discountProducts();

    @Procedure(name = "sellingProducts")
    List<Product> sellingProducts();

    @Procedure(name = "slowestSellingProducts")
    List<Product> slowestSellingProducts();

}
