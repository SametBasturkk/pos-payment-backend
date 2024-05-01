package com.pospayment.pospayment.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Integer id;
    private String orderDetails;
    private String price;
    private Integer companyId;
    private Integer menuId;
    private Integer status;
}
