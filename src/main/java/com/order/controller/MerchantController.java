package com.order.controller;

import com.order.domain.Customer;
import com.order.domain.Merchant;
import com.order.domain.Order;
import com.order.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    //注册
    @RequestMapping("/register")
    public String register(Merchant merchant, Model model,HttpSession session){
        try {
            merchantService.register(merchant);
            model.addAttribute("merchant",merchant);
        }catch (Exception e){
            model.addAttribute("errMsg",e.getMessage());
            model.addAttribute("merchant",null);
        }
        return "success";
    }

    //登录
    @RequestMapping("/login")
    public String login(Integer id,String password,Model model){
        Merchant merchant = merchantService.login(id, password);
        model.addAttribute("merchant", merchant);
        return "success";
    }

//    退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "success";
    }

    //设置账号信息
    @RequestMapping("/setAccount")
    public String setAccount(Merchant merchant, HttpServletRequest request){
        merchantService.setAccount(merchant);
        request.setAttribute("merchant",merchant);
        return "success";
    }

    //通过id获取账号信息
    @RequestMapping("/getAccount")
    public @ResponseBody Merchant getAccount(Integer id,HttpServletRequest request){
        Merchant merchant = merchantService.findById(id);
        request.setAttribute("merchant",merchant);
        return merchant;
    }

    //获取所有商家简介
    @RequestMapping("/getAll")
    public @ResponseBody List<Merchant> getAll(){
        List<Merchant> merchants = merchantService.findAll();
        return merchants;
    }

    //上传商家图片
    @RequestMapping("/uploadImg")
    public String uploadImg(@RequestParam("id") int id, MultipartFile upload) throws Exception{
        Merchant merchant = merchantService.findById(id);
        String path = "D:\\imgresources";
        String filename = upload.getOriginalFilename();
        //把文件的名称设置成唯一值,uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"_"+filename;
        upload.transferTo(new File(path,filename));
        merchant.setImgpath(filename);
        merchantService.setMerchantImg(merchant);
        return "success";
    }


}
