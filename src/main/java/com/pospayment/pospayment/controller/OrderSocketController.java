package com.pospayment.pospayment.controller;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.service.OrderService;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.JwtToken;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class OrderSocketController {

    private String SOCKETHOST = "localhost";

    private int SOCKETPORT = 9092;

    private SocketIOServer server;

    private JwtToken jwtToken;

    private OrderService orderService;

    private UserService userService;

    public OrderSocketController(JwtToken jwtToken, OrderService orderService, UserService userService) {
        this.jwtToken = jwtToken;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(SOCKETHOST);
        config.setPort(SOCKETPORT);
        server = new SocketIOServer(config);
        server.start();


        server.addEventListener("getOrders", String.class, (client, data, ackRequest) -> {
            Company company = userService.getCompany(jwtToken.getUsername(data));
            server.getBroadcastOperations().sendEvent("getOrders", orderService.getAllOrders(company));
        });

        return server;
    }

    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }


}
