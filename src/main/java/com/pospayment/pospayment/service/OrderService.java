package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Order;
import com.pospayment.pospayment.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

    public void deleteOrder(String id) {
        orderRepo.deleteById(id);
    }

    public void deactiveOrder(String id) {
        Order order = orderRepo.findById(id).get();
        order.setIsActive(false);
        orderRepo.save(order);
    }

}
