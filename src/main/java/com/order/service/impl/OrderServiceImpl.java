package com.order.service.impl;

import com.order.dao.CuisineDao;
import com.order.dao.OrderDao;
import com.order.dao.OrderItemDao;
import com.order.domain.Cuisine;
import com.order.domain.Order;
import com.order.domain.OrderItem;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private CuisineDao cuisineDao;

    @Override
    public void addOrder(Order order) {
        order.setStatus("等待商家接单");
        orderDao.saveOrder(order);
        Integer orderid = order.getId();
        for(OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrderid(orderid);
            orderItemDao.saveOrderItem(orderItem);
        }
    }

    @Override
    public void takeOrder(Order order) {
        order.setStatus("商家已接单");
        orderDao.setOrder(order);
    }

    @Override
    public void finishOrder(Order order) {
        order.setStatus("商家完成订单，等待用户收货");
        orderDao.setOrder(order);
    }

    @Override
    public void confirmReceipt(Order order) {
        order.setStatus("用户确认收货，订单完成");
        orderDao.setOrder(order);
    }

    @Override
    public void cancelOrder(Order order) {
        order.setStatus("订单已取消");
        orderDao.setOrder(order);
    }

    @Override
    public List<Order> findAllByCustomerId(Integer id) {
        List<Order> orders = orderDao.findByCustomerId(id);
        for (Order order : orders) {
            order.setOrderItems(orderItemDao.findByOrderId(order.getId()));
            for(OrderItem orderItem : order.getOrderItems())
                orderItem.setCuisine(cuisineDao.findById(orderItem.getCuisineid()).get(0));
        }
        return orders;
    }

    @Override
    public List<Order> findAllByMerchantId(Integer id) {
        List<Order> orders = orderDao.findByMerchantId(id);
        for (Order order : orders) {
            order.setOrderItems(orderItemDao.findByOrderId(order.getId()));
            for(OrderItem orderItem : order.getOrderItems()) {
                List<Cuisine> cuisines = cuisineDao.findById(orderItem.getCuisineid());
                if(cuisines.size()!=1)
                    cuisines = null;
                else
                    orderItem.setCuisine(cuisines.get(0));
            }

        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        Order order = orderDao.findById(id).get(0);
        order.setOrderItems(orderItemDao.findByOrderId(order.getId()));
        for(OrderItem orderItem : order.getOrderItems()) {
            List<Cuisine> cuisines = cuisineDao.findById(orderItem.getCuisineid());
            if(cuisines.size()!=1)
                cuisines = null;
            else
                orderItem.setCuisine(cuisines.get(0));
        }
        return order;
    }

    @Override
    public void deleteByCustomerId(Integer id) {
        List<Order> orders = orderDao.findByCustomerId(id);
        for(Order order : orders)
            orderItemDao.deleteByOrderId(order.getId());
        orderDao.deleteByCustomerId(id);
    }

    @Override
    public void deleteById(Integer id) {
        orderDao.deleteById(id);
        orderItemDao.deleteByOrderId(id);
    }

    @Override
    public void setOrder(Order order) {
        orderDao.setOrder(order);
    }
}
