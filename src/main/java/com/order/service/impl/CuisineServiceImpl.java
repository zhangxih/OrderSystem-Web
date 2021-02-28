package com.order.service.impl;

import com.order.dao.CuisineDao;
import com.order.domain.Cuisine;
import com.order.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cuisineService")
public class CuisineServiceImpl implements CuisineService {
    @Autowired
    private CuisineDao cuisineDao;

    @Override
    public void saveCuisine(Cuisine cuisine) {
        cuisineDao.saveCuisine(cuisine);
    }

    @Override
    public void updateCuisine(Cuisine cuisine) {
        cuisineDao.updateCuisine(cuisine);
    }

    @Override
    public void deleteById(Integer id) {
        cuisineDao.deleteById(id);
    }

    @Override
    public void deleteByMerchantId(Integer id) {
        cuisineDao.deleteByMerchantId(id);
    }

    @Override
    public Cuisine findById(Integer id) {
        return cuisineDao.findById(id).get(0);
    }

    @Override
    public List<Cuisine> findByMerchantId(Integer id) {
        return cuisineDao.findByMerchantId(id);
    }

    @Override
    public void updateCuisineImg(Cuisine cuisine) {
        cuisineDao.updateCuisineImg(cuisine);
    }
}
