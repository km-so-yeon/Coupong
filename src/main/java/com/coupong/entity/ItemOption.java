package com.coupong.entity;

import com.coupong.constant.ItemOptionStatus;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
public class ItemOption {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private String info;

    @Enumerated(EnumType.STRING)
    private ItemOptionStatus status;

    private Integer price;

    private Integer remains;

    public ItemOption() {}

    public ItemOption(Long id, Item item, String info, ItemOptionStatus status, Integer price, Integer remains) {
        this.id = id;
        this.item = item;
        this.info = info;
        this.status = status;
        this.price = price;
        this.remains = remains;
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public String getInfo() {
        return info;
    }

    public ItemOptionStatus getStatus() {
        return status;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getRemains() {
        return remains;
    }
}
