package com.springboot.customerAndOrderManagementApplication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "oders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OderEntity {

    @Id
    @Column(name = "oder_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int oderId;

    @Column(name = "customer_name",length = 100,nullable = false)
    private String customerName;

    @Column(name = "employee_name",length = 100,nullable = false)
    private String employeeName;

    @Column(name = "date",length = 30,nullable = false)
    private Date data;

    @Column(name = "gross_amount",nullable = false)
    private double grossAmount;

    @Column(name = "total_discount",nullable = false)
    private double totalDiscount;

    @Column(name = "net_amount",nullable = false)
    private double netAmount;

    @Column(name = "gift_voucher",nullable = false)
    private double giftVoucher;

    @Column(name = "cash",nullable = false)
    private double cash;

    @Column(name = "tatal_charge",nullable = false)
    private double totalCharge;

    @Column(name = "no_of_item",nullable = false)
    private int noOfItem;

    @Column(name = "oder_active_state",columnDefinition = "TINYINT default 1")
    private boolean oderActiveState;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy="oderEntity")
    private Set<OderDetailsEntity> oderDetailsEntitySet;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private EmployeeEntity employeeEntity;



//for loop eken karaddi



    public OderEntity(String customerName, String employeeName, Date data, double grossAmount, double totalDiscount, double netAmount, double giftVoucher, double cash, double totalCharge, int noOfItem, CustomerEntity customerEntity, EmployeeEntity employeeEntity) {
        this.customerName = customerName;
        this.employeeName = employeeName;
        this.data = data;
        this.grossAmount = grossAmount;
        this.totalDiscount = totalDiscount;
        this.netAmount = netAmount;
        this.giftVoucher = giftVoucher;
        this.cash = cash;
        this.totalCharge = totalCharge;
        this.noOfItem = noOfItem;
        this.customerEntity = customerEntity;
        this.employeeEntity = employeeEntity;
    }




}
