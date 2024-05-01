package com.pospayment.pospayment.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String role;
    private Integer companyId;
}
