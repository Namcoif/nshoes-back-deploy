package com.ntn.repository;

import com.ntn.entity.Product;
import com.ntn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
//    @Query("SELECT pr FROM Product pr ORDER BY pr.soldCount DESC LIMIT=:paramLimit")
//    List<Product> sellingProducts(@Param("paramLimit") Integer limit);
}
