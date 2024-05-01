package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Order;
import com.pospayment.pospayment.repository.OrderRepo;
import com.pospayment.pospayment.util.Converter;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepo orderRepo;

    private Converter converter;

    public OrderService(OrderRepo orderRepo, Converter converter) {
        this.orderRepo = orderRepo;
        this.converter = converter;
    }

    public void saveOrder(Company company,Order order) {
        order.setCompany(company);
        orderRepo.save(order);
    }


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void changeStatus(String id, String status) {
        Order order = orderRepo.findById(id).get();

        switch (status) {
            case "0":
                order.setStatus(Order.STATUS_PENDING);
                break;
            case "2":
                order.setStatus(Order.STATUS_COMPLETED);
                break;
            case "1":
                order.setStatus(Order.STATUS_PROCESSING);
                break;
            case "3":
                order.setStatus(Order.STATUS_CANCELLED);
                break;
            default:
                order.setStatus(Order.STATUS_PENDING);
                break;
        }

        orderRepo.save(order);
    }

    public String getAllOrders(Company company) {
        return converter.convertToJson(orderRepo.findByCompany(company));
    }
}
