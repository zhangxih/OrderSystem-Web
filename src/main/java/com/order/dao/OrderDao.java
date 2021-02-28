package com.order.dao;

import com.order.domain.Order;
import org.apache.ibatis.annotations.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    //通过customerid查询订单
    @Select("select * from orders where customerid=#{id}")
    public List<Order> findByCustomerId(Integer id);

    //通过merchantid查询订单
    @Select("select * from orders where merchantid=#{id}")
    public List<Order> findByMerchantId(Integer id);

    //通过id查询订单
    @Select("select * from orders where id=#{id}")
    public List<Order> findById(Integer id);

    //保存订单
    @Options(useGeneratedKeys = true ,keyProperty = "id")
    @Insert("insert into orders (customerid,merchantid,time,totalprice,status) values (#{customerid},#{merchantid},#{time},#{totalprice},#{status})")
    public void saveOrder(Order order);

    //根据id删除订单
    @Delete("delete from orders where id=#{id}")
    public void deleteById(Integer id);

    //根据用户id删除订单
    @Delete("delete from orders where customerid=#{id}")
    public void deleteByCustomerId(Integer id);

    //修改订单信息
    @Update("update orders set customerid=#{customerid}, merchantid=#{merchantid}, time=#{time}, totalprice=#{totalprice}, status=#{status}" +
            "where id=#{id}")
    public void setOrder(Order order);

}
