package com.order.service.impl;

import com.order.dao.MerchantDao;
import com.order.domain.Customer;
import com.order.domain.Merchant;
import com.order.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantDao merchantDao;

    @Override
    public void register(Merchant merchant) {
        if(merchantDao.findById(merchant.getId()).size()!=0)
            throw new RuntimeException("账号已被他人注册");
        merchantDao.saveMerchant(merchant);
    }

    @Override
    public void setAccount(Merchant merchant) {
        merchantDao.updateMerchant(merchant);
    }

    @Override
    public Merchant findById(Integer id) {
        List<Merchant> merchants = merchantDao.findById(id);
        if(merchants.size()!=1)
            throw new RuntimeException("查询错误");
        return merchants.get(0);
    }

    @Override
    public Merchant login(Integer id, String password) {
        Merchant merchant = merchantDao.findById(id).get(0);
        if(merchant==null)
            throw new RuntimeException("账号错误");
        else if(!merchant.getPassword().equals(password))
            throw new  RuntimeException("密码错误");
        return merchant;
    }

    @Override
    public List<Merchant> findAll() {
        return merchantDao.findAll();
    }

    @Override
    public void setMerchantImg(Merchant merchant) {
        merchantDao.updateMerchantImg(merchant);
    }
}
