package com.order.service;

import com.order.domain.Customer;

public interface CustomerService {
    //添加账户
    public void register(Customer customer);

    //修改账户信息
    public void setAccount(Customer customer);

    //通过id获取账号信息
    public Customer findById(Integer id);

    //登录
    public Customer login(Integer id,String password);
}
