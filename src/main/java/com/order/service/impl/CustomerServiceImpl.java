package com.order.service.impl;

import com.order.dao.CustomerDao;
import com.order.domain.Customer;
import com.order.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public void register(Customer customer) {
        if(customerDao.findById(customer.getId()).size()!=0)
            throw new RuntimeException("账号已被他人注册");
        customerDao.saveCustomer(customer);
    }

    @Override
    public void setAccount(Customer customer) {
        Customer cus = customerDao.findById(customer.getId()).get(0);
        cus.setAddress(customer.getAddress());
        cus.setBalance(customer.getBalance());
        cus.setName(customer.getName());
        customerDao.updateCustomer(cus);
    }

    @Override
    public Customer findById(Integer id) {
        List<Customer> customers = customerDao.findById(id);
        if(customers.size()!=1)
            throw new RuntimeException("查询错误");
        return customers.get(0);
    }

    @Override
    public Customer login(Integer id, String password) {
        Customer customer = customerDao.findById(id).get(0);
        if(customer==null)
            throw new RuntimeException("账号错误");
        else if(!customer.getPassword().equals(password))
            throw new  RuntimeException("密码错误");
        return customer;
    }
}
