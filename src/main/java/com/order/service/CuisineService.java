package com.order.service;

import com.order.domain.Cuisine;

import java.util.List;

public interface CuisineService {
    //添加菜品
    public void saveCuisine(Cuisine cuisine);

    //修改菜品信息
    public void updateCuisine(Cuisine cuisine);

    //通过菜品id删除菜品
    public void deleteById(Integer id);

    //通过商家id删除菜品
    public void deleteByMerchantId(Integer id);

    //通过菜品id获取菜品
    public Cuisine findById(Integer id);

    //通过商家id获取菜品
    public List<Cuisine> findByMerchantId(Integer id);

    //上传菜品图片
    public void updateCuisineImg(Cuisine cuisine);
}
