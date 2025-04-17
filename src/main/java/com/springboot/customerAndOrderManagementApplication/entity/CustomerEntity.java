package com.springboot.customerAndOrderManagementApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name",length = 100,nullable = false)
    private String customerName;

    @Column(name = "customer_address",length = 100,nullable = false)
    private String customerAddress;

    @Column(name = "customer_nic",length = 100,nullable = false)
    private String customerNIC;

    @Column(name = "customer_salary",nullable = false)
    private double customerSalary;

    @Column(name = "customer_active_state",columnDefinition = "TINYINT default 1")
    private boolean customerActiveState;

    @OneToMany(mappedBy="customerEntity")
    private Set<OderEntity> oderEntitySet;

    //model mapper /map strct oni na meka
//    public CustomerEntity(String customerName, String customerAddress, String customerNIC, double customerSalary) {
//        this.customerName = customerName;
//        this.customerAddress = customerAddress;
//        this.customerNIC = customerNIC;
//        this.customerSalary = customerSalary;
//    }

}
