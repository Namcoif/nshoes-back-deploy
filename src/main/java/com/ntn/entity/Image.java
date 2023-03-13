package com.ntn.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "image_url")
@Data
public class Image {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url", length = 500)
    private String url;

    @ManyToMany(mappedBy = "productImgUrls")
    private List<Product> urls_product_img;
}
