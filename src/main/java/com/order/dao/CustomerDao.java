package com.order.dao;

import com.order.domain.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao {

    //添加账户
    @Insert("insert into customer (id,name,password,address,balance) values(#{id},#{name},#{password},#{address},#{balance})")
    public void saveCustomer(Customer customer);

    //设置账户
    @Update("update customer set name=#{name}, password=#{password}, address=#{address}, balance=#{balance} where id=#{id}")
    public void updateCustomer(Customer customer);

    //通过id查询账户
    @Select("select * from customer where id=#{id}")
    public List<Customer> findById(Integer id);

}
