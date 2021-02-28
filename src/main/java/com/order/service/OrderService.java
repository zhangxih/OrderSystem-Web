package com.order.service;

import com.order.domain.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    //下单
    public void addOrder(Order order);

    //商家接单
    public void takeOrder(Order order);

    //商家完成订单等待收货
    public void finishOrder(Order order);

    //用户确认收货
    public void confirmReceipt(Order order);

    //用户取消订单
    public void cancelOrder(Order order);

    //通过用户id查询所有订单
    public List<Order> findAllByCustomerId(Integer id);

    //通过商家id查询所有订单
    public List<Order> findAllByMerchantId(Integer id);

    //通过订单id查询订单
    public Order findById(Integer id);

    //通过用户id删除订单
    public void deleteByCustomerId(Integer id);

    //通过订单id删除订单
    public void deleteById(Integer id);

    //修改订单信息
    public void setOrder(Order order);
}
