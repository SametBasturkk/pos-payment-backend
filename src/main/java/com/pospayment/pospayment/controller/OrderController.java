package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.model.Order;
import com.pospayment.pospayment.service.OrderService;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private JwtToken jwtToken;

    private UserService userService;


    public OrderController(OrderService orderService, JwtToken jwtToken, UserService userService) {
        this.orderService = orderService;
        this.jwtToken = jwtToken;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestHeader String Authorization, @RequestBody Order order) {
        orderService.saveOrder(userService.getCompany(jwtToken.getUsername(Authorization)),order);
        return ResponseEntity.ok("Order created successfully");
    }

    @PostMapping("/changeStatus")
    public ResponseEntity changeStatusOrder(@RequestParam String id, @RequestParam String status) {
        orderService.changeStatus(id, status);
        return ResponseEntity.ok("Order status changed successfully");
    }

    /*
    @GetMapping("/get-all")
    public List<OrderDTO> getAllOrders(@RequestHeader String Authorization) {
        jwtToken.validateToken(Authorization);
        Company company = userService.getCompany(jwtToken.getUsername(Authorization));
        return orderService.getAllOrders(company);
    }
     */





}
