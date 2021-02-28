package com.order.controller;

import com.order.domain.Cuisine;
import com.order.domain.Merchant;
import com.order.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cuisine")
public class CuisineController {

    @Autowired
    private CuisineService cuisineService;

    //添加菜品
    @RequestMapping("/addCuisine")
    public String addCuisine(@RequestParam("merchantid") Integer merchantid,
                            @RequestParam("cuisinename") String cuisinename,@RequestParam("description") String description
            ,@RequestParam("unitprice") Double unitprice,MultipartFile upload) throws Exception{
        Cuisine cuisine = new Cuisine();
        cuisine.setCuisinename(cuisinename);
        cuisine.setDescription(description);
        cuisine.setMerchantid(merchantid);
        cuisine.setUnitprice(unitprice);
        String path = "D:\\imgresources";
        String filename = upload.getOriginalFilename();
        //把文件的名称设置成唯一值,uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"_"+filename;
        upload.transferTo(new File(path,filename));
        cuisine.setImgpath(filename);
        cuisineService.saveCuisine(cuisine);
        return "success";
    }

    //通过菜品id查询菜品信息
    @RequestMapping("/findById")
    public @ResponseBody Cuisine findById(Integer id, Model model){
        Cuisine cuisine = cuisineService.findById(id);
        model.addAttribute("cuisine",cuisine);
        return cuisine;
    }

    //通过商家id查询菜品
    @RequestMapping("/findByMerchantId")
    public @ResponseBody List<Cuisine> findByMerchantId(Integer id){
        List<Cuisine> cuisines = cuisineService.findByMerchantId(id);
        return cuisines;
    }

    //修改菜品信息
    @RequestMapping("/setCuisine")
    public String setCuisine(Cuisine cuisine,Model model){
        cuisineService.updateCuisine(cuisine);
        model.addAttribute("cuisine",cuisine);
        return "success";
    }

    //通过菜品id删除菜品
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        cuisineService.deleteById(id);
        return "success";
    }

    //通过商家id删除菜品
    @RequestMapping("/deleteByMerchantId")
    public String deleteByMerchantId(Integer id){
        cuisineService.deleteByMerchantId(id);
        return "success";
    }

    //上传菜品图片
    @RequestMapping("/uploadImg")
    public String uploadImg(@RequestParam("id") int id, MultipartFile upload) throws Exception{
        Cuisine cuisine = cuisineService.findById(id);
        String path = "D:\\imgresources";
        String filename = upload.getOriginalFilename();
        //把文件的名称设置成唯一值,uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"_"+filename;
        upload.transferTo(new File(path,filename));
        cuisine.setImgpath(filename);
        cuisineService.updateCuisine(cuisine);
        return "success";
    }
}
