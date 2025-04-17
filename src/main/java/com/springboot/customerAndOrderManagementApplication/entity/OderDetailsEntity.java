package com.springboot.customerAndOrderManagementApplication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "oder_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OderDetailsEntity {

    @Id
    @Column(name = "oder_details_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int oderDetailsId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "qty", nullable = false)
    private double qty;

    @Column(name = "unit_discount", nullable = false)
    private double unitDiscount;

    @Column(name = "total", nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "oder_id", nullable = false)
    private OderEntity oderEntity;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity itemEntity;


    //oder entity eka requet walin ewanne nathi nisa
    //for loop ekat

    public OderDetailsEntity(String itemName, double qty, double unitDiscount, double total, OderEntity oderEntity, ItemEntity itemEntity) {
        this.itemName = itemName;
        this.qty = qty;
        this.unitDiscount = unitDiscount;
        this.total = total;
        this.oderEntity = oderEntity;
        this.itemEntity = itemEntity;
    }
}
