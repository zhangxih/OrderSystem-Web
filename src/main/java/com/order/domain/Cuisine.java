package com.order.domain;

import java.io.Serializable;

public class Cuisine implements Serializable {
    private Integer id;
    private Integer merchantid;
    private String cuisinename;
    private String description;
    private Double unitprice;
    private String imgpath;

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
    }

    public String getCuisinename() {
        return cuisinename;
    }

    public void setCuisinename(String cuisinename) {
        this.cuisinename = cuisinename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "id=" + id +
                ", merchantid=" + merchantid +
                ", cuisinename='" + cuisinename + '\'' +
                ", description='" + description + '\'' +
                ", unitprice=" + unitprice +
                ", imgpath='" + imgpath + '\'' +
                '}';
    }
}
