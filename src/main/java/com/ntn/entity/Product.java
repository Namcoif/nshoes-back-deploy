package com.ntn.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "product_name", length = 255)
    private String productName;

    @Column(name = "original_price", nullable = false)
    private Float originalPrice;

    @Column(name = "promotion_price")
    private Float promotionPrice;

    @Column(name = "create_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus productStatus;

    @Column(name = "sold_count")
    private Integer soldCount;

    @OneToMany(mappedBy = "product")
    private List<Cart> carts;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    private List<Rate> productRates;
    @ManyToMany
    @JoinTable(name = "size_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "size_id"))
    @JsonManagedReference
    private List<Size> productSizes;
    @ManyToMany
    @JoinTable(name = "product_img_url", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "img_url_id"))
    @JsonManagedReference
    private List<Image> productImgUrls;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                '}';
    }

    public enum ProductStatus {
        STOP_SELLING, ON_SELLING
    }
}
