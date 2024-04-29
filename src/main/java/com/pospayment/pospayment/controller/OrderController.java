package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Order;
import com.pospayment.pospayment.service.MenuService;
import com.pospayment.pospayment.service.OrderService;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestHeader String Authorization, @RequestBody Order order) {
        orderService.saveOrder(userService.getCompany(jwtToken.getUsername(Authorization)),order);
        return ResponseEntity.ok("Order created successfully");
    }

    @PostMapping("/changeStatus")
    public ResponseEntity changeStatusOrder(@RequestParam String uuid, @RequestParam String status) {
        orderService.changeStatus(uuid, status);
        return ResponseEntity.ok("Order status changed successfully");
    }

    @GetMapping("/get-all")
    public String getAllOrders(@RequestHeader String Authorization) {
        jwtToken.validateToken(Authorization);
        Company company = userService.getCompany(jwtToken.getUsername(Authorization));
        return orderService.getAllOrders(company);
    }

}
