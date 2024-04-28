package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Order;
import com.pospayment.pospayment.repository.OrderRepo;
import com.pospayment.pospayment.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private JsonConverter jsonConverter;

    public void saveOrder(Order order) {
        orderRepo.save(order);
    }


    public void changeStatus(String uuid, String status) {
        Order order = orderRepo.findByUuid(uuid);

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

    public String getAllOrders(Integer companyId) {
        return jsonConverter.convertToJson(orderRepo.findByCompanyID(companyId));
    }
}
