package com.order.service;

import com.order.domain.Customer;
import com.order.domain.Merchant;

import java.util.List;

public interface MerchantService {
    //添加账户
    public void register(Merchant merchant);

    //修改账户信息
    public void setAccount(Merchant merchant);

    //通过id获取账号信息
    public Merchant findById(Integer id);

    //登录
    public Merchant login(Integer id,String password);

    //获取所有商家简要信息
    public List<Merchant> findAll();

    //设置商家图片
    public void setMerchantImg(Merchant merchant);
}
