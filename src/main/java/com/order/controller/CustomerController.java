package com.order.controller;

import com.order.domain.Customer;
import com.order.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //注册
    @RequestMapping("/register")
    public String register(Customer customer, Model model){
        /*try {
            customerService.register(customer);
            model.addAttribute("customer", customer);
        }catch (Exception e){
            model.addAttribute("errMsg",e.getMessage());
            model.addAttribute("customer",null);
        }*/
        customerService.register(customer);
        return "success";
    }

    //登录
    @RequestMapping("/login")
    public String login(Integer id,String password,Model model){
        Customer customer = customerService.login(id, password);
        model.addAttribute("customer", customer);
        return "success";
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "success";
    }

    //设置账号信息
    @RequestMapping("/setAccount")
    public String setAccount(Customer customer,HttpServletRequest request){
        customerService.setAccount(customer);
        request.setAttribute("customer",customer);
        return "success";
    }

    //通过id获取账号信息
    @RequestMapping("/getAccount")
    public @ResponseBody Customer getAccount(Integer id){
        Customer customer = customerService.findById(id);
//        request.setAttribute("customer",customer);
        return customer;
    }

}
