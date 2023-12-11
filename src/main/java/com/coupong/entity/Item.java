package com.coupong.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String rid;

    private String name;

    private Integer price;

    private Integer remains;

    private Integer orderCount;

    private Integer status;

    private String info;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Item() {}

    public Item(Long id, String rid, String name
            , Integer price, Integer remains, Integer orderCount, Integer status, String info, String imageUrl) {
        this.id = id;
        this.rid = rid;
        this.name = name;
        this.price = price;
        this.remains = remains;
        this.orderCount = orderCount;
        this.status = status;
        this.info = info;
        this.imageUrl = imageUrl;
    }

    public void order(Integer quantity) {
        this.remains -= quantity;
        this.status = 1;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getRid() {
        return rid;
    }

    public String getName() {
        return name;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getInfo() {
        return info;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getRemains() {
        return remains;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Category getCategory() {
        return category;
    }
}
