package com.pospayment.pospayment.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MenuDTO {
    private Integer id;
    private String MenuName;
    private String MenuDescription;
    private HashMap<String, List<ProductDTO>> menuItems;
    private List<CategoryDTO> categories;
    private Integer companyId;
}
