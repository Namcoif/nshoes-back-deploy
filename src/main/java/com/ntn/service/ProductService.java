package com.ntn.service;

import com.ntn.dto.QueryProductDTO;
import com.ntn.entity.Product;
import com.ntn.repository.IProductRepository;
import com.ntn.specification.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getListProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getListProductsPaging(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getListProductsPaging(Pageable pageable, QueryProductDTO queryProductDTO) {
        Specification<Product> where = ProductSpecifications.searchProducts(queryProductDTO);
        return productRepository.findAll(where, pageable);
    }

    @Override
    public void createProducts(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Product product) {
//        Product product0 = getProductById(productDTO.getId());
//        Product product = new Product(product0.getProductId(), productDTO.getProductName(), productDTO.getOriginalPrice(), productDTO.getPromotionPrice(), product0.getProductUrl(), product0.getCreatedDate(), product0.getAmount(), product0.getDescription(), product0.getProductStatus(), product0.getSoldCount(), product0.getProductId());

//        product.setProductId(product.getProductId());
        productRepository.save(product);
    }

    @Override
    public List<Product> sellingProducts(int limit) {
        return entityManager.createQuery("SELECT pr FROM Product pr ORDER BY pr.soldCount DESC",Product.class).setMaxResults(limit).getResultList();
    }


}
