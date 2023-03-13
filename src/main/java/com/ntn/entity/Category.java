package com.ntn.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name", length = 50)
    private String categoryName;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private CategoryStatus categoryStatus;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public enum CategoryStatus {
        NOT_ACTIVE, ACTIVE;

        public static CategoryStatus toEnum(String categoryStatus) {
            for (CategoryStatus catStatus : CategoryStatus.values()) {
                if (catStatus.toString().equals(categoryStatus))
                    return catStatus;
            }
            return null;
        }

    }
}
