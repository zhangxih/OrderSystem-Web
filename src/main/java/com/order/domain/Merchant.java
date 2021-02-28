package com.order.domain;

import java.io.Serializable;
import java.util.List;

public class Merchant implements Serializable {
    private Integer id;
    private String password;
    private String name;
    private String address;
    private String merchantdescription;
    private String businesshours;
    private List<Cuisine> cuisines;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMerchantdescription() {
        return merchantdescription;
    }

    public void setMerchantdescription(String merchantdescription) {
        this.merchantdescription = merchantdescription;
    }

    public String getBusinesshours() {
        return businesshours;
    }

    public void setBusinesshours(String businesshours) {
        this.businesshours = businesshours;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", merchantdescription='" + merchantdescription + '\'' +
                ", businesshours='" + businesshours + '\'' +
                ", cuisines=" + cuisines +
                ", imgpath='" + imgpath + '\'' +
                '}';
    }
}
