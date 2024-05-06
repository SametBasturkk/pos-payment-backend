package com.pospayment.pospayment.util;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResetPasswordSchema {
    private Integer id;
    private Timestamp exp;
}
