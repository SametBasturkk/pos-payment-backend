package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.model.Order;
import com.pospayment.pospayment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public void createOrder(Order order) {
        orderService.saveOrder(order);
    }

    @PostMapping("/delete")
    public void deleteOrder(String id) {
        orderService.deleteOrder(id);
    }

    @PostMapping("/deactive")
    public void deactiveOrder(String id) {
        orderService.deactiveOrder(id);
    }

}
