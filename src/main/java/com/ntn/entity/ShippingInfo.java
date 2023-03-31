package com.ntn.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shipping_info")
@Data
public class ShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "phone", length = 15)
    private String phoneNumber;

    @Column(name = "on_default", columnDefinition = "tinyint default 0")
    private Byte onDefault;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
