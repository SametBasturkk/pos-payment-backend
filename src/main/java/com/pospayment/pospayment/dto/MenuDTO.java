package com.pospayment.pospayment.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MenuDTO {
    private String MenuName;
    private String MenuDescription;
    private HashMap<String, List<ProductDTO>> menuItems;
    private Integer companyId;
}
