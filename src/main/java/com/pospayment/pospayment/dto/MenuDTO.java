package com.pospayment.pospayment.dto;

import com.pospayment.pospayment.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Data
public class MenuDTO {

    private String MenuName;
    private String MenuDescription;
    private HashMap<String, List<Product>> menuItems;

}
