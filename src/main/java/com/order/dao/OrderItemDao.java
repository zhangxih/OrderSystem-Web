package com.order.dao;

import com.order.domain.OrderItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemDao {

    //根据id查询所有订单项
    @Select("select * from orderitem where id=#{id}")
    public List<OrderItem> findById(Integer id);

    //根据订单id查询所有订单项
    @Select("select * from orderitem where orderid=#{id}")
    public List<OrderItem> findByOrderId(Integer id);

    //保存订单项
    @Insert("insert into orderitem(orderid,cuisineid,cuisinenum,price) values(#{orderid},#{cuisineid},#{cuisinenum},#{price})")
    public void saveOrderItem(OrderItem orderItem);

    //通过orderid删除订单项
    @Delete("delete from orderitem where orderid=#{id}")
    public void deleteByOrderId(Integer id);
}
