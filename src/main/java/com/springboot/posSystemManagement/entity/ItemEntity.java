package com.springboot.posSystemManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name",length = 100,nullable = false)
    private String itemName;

    @Column(name = "item_qty",nullable = false)
    private double itemQty;

    @Column(name = "item_supply_price",nullable = false)
    private double itemSupplyPrice;

    @Column(name = "item_sell_price",nullable = false)
    private double itemSellPrice;

    @Column(name = "item_discount",nullable = false)
    private double itemDiscount;

    @Column(name = "discount_with_percentage",nullable = false)
    private double discountWithPercentage;

    @Column(name = "item_active_state",columnDefinition = "TINYINT default 1")
    private boolean itemActiveState;

    @OneToMany(mappedBy="itemEntity")
    private Set<OderDetailsEntity> oderDetailsEntitySet;

    //for loop eken data pass karan nisa
    public ItemEntity(String itemName, double itemQty, double itemSupplyPrice, double itemSellPrice, double itemDiscount, double discountWithPercentage) {
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemSupplyPrice = itemSupplyPrice;
        this.itemSellPrice = itemSellPrice;
        this.itemDiscount = itemDiscount;
        this.discountWithPercentage = discountWithPercentage;
    }
}
