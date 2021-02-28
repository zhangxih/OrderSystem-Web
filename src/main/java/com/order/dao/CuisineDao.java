package com.order.dao;

import com.order.domain.Cuisine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineDao {

    //根据id查询菜品信息
    @Select("select * from cuisine where id=#{id}")
    public List<Cuisine> findById(Integer id);

    //根据商家id查询所有菜品信息
    @Select("select * from cuisine where merchantid=#{id}")
    public List<Cuisine> findByMerchantId(Integer id);

    //根据菜名查询
    @Select("select * from cuisine where cuisinename=#{name}")
    public List<Cuisine> findByCuisineName(String name);

    //添加菜品
    @Insert("insert into cuisine (cuisinename,description,unitprice,merchantid,imgpath) values(#{cuisinename},#{description},#{unitprice},#{merchantid},#{imgpath})")
    public void saveCuisine(Cuisine cuisine);

    //修改菜品信息
    @Update("update cuisine set cuisinename=#{cuisinename}, description=#{description}, unitprice=#{unitprice}, imgpath=#{imgpath} where id=#{id}")
    public void updateCuisine(Cuisine cuisine);

    //根据菜品id删除菜品
    @Delete("delete from cuisine where id=#{id}")
    public void deleteById(Integer id);

    //根据商家id删除菜品
    @Delete("delete from cuisine where merchantid=#{id}")
    public void deleteByMerchantId(Integer id);

    //修改菜品图片
    @Update("update cuisine set imgpath=#{imgpath}")
    public void updateCuisineImg(Cuisine cuisine);

}
