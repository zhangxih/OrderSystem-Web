package com.order.dao;

import com.order.domain.Customer;
import com.order.domain.Merchant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantDao {

    //添加账户
    @Insert("insert into merchant (id,name,password,address,merchantdescription,businesshours,imgpath) " +
            "values(#{id},#{name},#{password},#{address},#{merchantdescription},#{businesshours},#{imgpath})")
    public void saveMerchant(Merchant merchant);

    //设置账户
    @Update("update merchant set name=#{name}, password=#{password}, address=#{address}, " +
            "merchantdescription=#{merchantdescription}, businesshours=#{businesshours} where id=#{id}")
    public void updateMerchant(Merchant merchant);

    //通过id查询账户
    @Select("select * from merchant where id=#{id}")
    public List<Merchant> findById(Integer id);

    //查询所有账户
    @Select("select * from merchant")
    public List<Merchant> findAll();

    //设置商家图片
    @Update("update merchant set imgpath=#{imgpath} where id=#{id}")
    public void updateMerchantImg(Merchant merchant);
}
