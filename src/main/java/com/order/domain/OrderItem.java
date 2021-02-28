package com.order.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private Integer id;
    private Integer orderid;
    private Integer cuisineid;
    private Integer cuisinenum;
    private Double price;
    private Cuisine cuisine;

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCuisineid() {
        return cuisineid;
    }

    public void setCuisineid(Integer cuisineid) {
        this.cuisineid = cuisineid;
    }

    public Integer getCuisinenum() {
        return cuisinenum;
    }

    public void setCuisinenum(Integer cuisinenum) {
        this.cuisinenum = cuisinenum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderid=" + orderid +
                ", cuisineid=" + cuisineid +
                ", cuisinenum=" + cuisinenum +
                ", price=" + price +
                ", cuisine=" + cuisine +
                '}';
    }
}
