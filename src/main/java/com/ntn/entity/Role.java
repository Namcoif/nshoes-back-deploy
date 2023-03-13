package com.ntn.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role", columnDefinition = "ENUM('CUSTOMER','ADMIN','MANAGER') DEFAULT 'CUSTOMER'")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public enum RoleEnum {
        CUSTOMER, ADMIN, MANAGER;

        public static RoleEnum toEnum(String role) {
            for (RoleEnum item : RoleEnum.values()) {
                if (item.toString().equals(role)) return item;
            }
            return null;
        }
    }
}
