package com.order.controller;

import com.order.domain.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    //下单
    @RequestMapping("/addOrder")
    public String addOrder(@RequestBody Order order){
        System.out.println(order);
        order.setTime(new Date());
        orderService.addOrder(order);
        return "success";
    }

    //商家接单
    @RequestMapping("/takeOrder")
    public String takeOrder(Integer id){
        Order order = orderService.findById(id);
        orderService.takeOrder(order);
        return "success";
    }

    //商家完成订单
    @RequestMapping("/finishOrder")
    public String finishOrder(Integer id){
        System.out.println(id);
        Order order = orderService.findById(id);
        orderService.finishOrder(order);
        return "success";
    }

    //用户确认收货
    @RequestMapping("/confirmReceipt")
    public String confirmReceipt(Integer id){
        Order order = orderService.findById(id);
        orderService.confirmReceipt(order);
        return "success";
    }

    //用户取消订单
    @RequestMapping("/cancelOrder")
    public String cancelOrder(Order order){
        orderService.cancelOrder(order);
        return "success";
    }

    //通过用户id查询所有订单
    @RequestMapping("/getAllByCustomerId")
    public @ResponseBody List<Order> getAllByCustomerId(Integer id, Model model){
        List<Order> orders = orderService.findAllByCustomerId(id);
        model.addAttribute("orders",orders);
        return orders;
    }

    //通过商家id查询所有订单
    @RequestMapping("/getAllByMerchantId")
    public @ResponseBody List<Order> getAllByMerchantId(Integer id){
        List<Order> orders = orderService.findAllByMerchantId(id);
        return orders;
    }

    //通过订单id查询订单
    @RequestMapping("/getById")
    public @ResponseBody Order getById(Integer id,Model model){
        Order order = orderService.findById(id);
        model.addAttribute("order",order);
        return order;
    }

    //通过用户id删除订单
    @RequestMapping("/deleteByCustomerId")
    public String deleteByCustomerId(Integer id,Model model){
        orderService.deleteByCustomerId(id);
        model.addAttribute("orders",null);
        return "success";
    }

    //通过订单id删除订单
    @RequestMapping("/deleteById")
    public String deleteById(Integer id,Model model){
        orderService.deleteById(id);
        model.addAttribute("order",null);
        return "success";
    }

    //向商家端推送订单信息
    @RequestMapping(value = "/serverPush", produces = "text/event-stream;charset=UTF-8")
    public void serverPush(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie[] cookies = request.getCookies();
        String id = "";
        for(Cookie cookie : cookies)
            if(cookie.getName().equals("id"))
                id=cookie.getValue();
        int merchantid = Integer.parseInt(id);
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        while (true) {
            List<Order> orders = orderService.findAllByMerchantId(merchantid);
            try {
                Thread.sleep(1000);
                PrintWriter pw = response.getWriter();
                for(Order order : orders) {
                    if (order.getStatus().equals("等待商家接单")) {
                        System.out.println(order.getStatus());
                        System.out.println("执行了");
                        pw.write("data:1" + "\n\n");
                        pw.flush();
                    }else {
                        System.out.println(order.getStatus());
                        System.out.println("else执行了");
                        /*pw.write("data:2" + "\n\n");
                        pw.flush();*/
                    }
                }
                if (pw.checkError()) {
                    System.out.println("客户端断开连接");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
