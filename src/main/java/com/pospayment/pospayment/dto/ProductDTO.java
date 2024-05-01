package com.pospayment.pospayment.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private Double price;
    private String imageUUID;
    private Integer categoryId;
    private Integer companyId;
}
