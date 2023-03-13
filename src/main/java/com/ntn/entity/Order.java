package com.ntn.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`order`")
@Data
public class Order {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "received_date")
    private Date receivedDate;

    @Column(name = "order_status", columnDefinition = "ENUM('UNCONFIMRED', 'DELIVERY', 'DELIVERED', 'CANCELED', 'RETURNS') DEFAULT 'UNCONFIMRED'")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_price")
    private Float productPrice;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "phone", length = 15)
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public enum OrderStatus {
        UNCONFIMRED, DELIVERY, DELIVERED, CANCELED, RETURNS
    }
}
