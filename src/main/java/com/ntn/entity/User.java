package com.ntn.entity;

import com.ntn.model.CustomID;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Data
public class User {
    @Column(name = "id", length = 15)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NShoes")
    @GenericGenerator(name = "NShoes", strategy = "com.ntn.model.CustomID", parameters = {
            @Parameter(name = CustomID.INCREMENT_PARAM, value = "1"),
            @Parameter(name = CustomID.VALUE_PREFIX_PARAMETER, value = "NShoes-"),
            @Parameter(name = CustomID.NUMBER_FORMAT_PARAMETER, value = "%05d")
    })
    private String userId;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "fullname", length = 50)
    private String fullName;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "status")
//    @Column(name = "status", columnDefinition = "ENUM('NOT_ACTIVE', 'ACTIVE')")
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus status;

    @Column(name = "avatar_url", length = 250)
    private String avatarUrl;

    @OneToMany(mappedBy = "user")
    private List<ShippingInfo> shippingInfos;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Rate> rates;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;

    public enum AccountStatus {
        NOT_ACTIVE, ACTIVE
    }

}
