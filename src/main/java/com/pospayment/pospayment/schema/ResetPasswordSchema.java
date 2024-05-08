package com.pospayment.pospayment.schema;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.sql.Timestamp;

@RedisHash("reset_password")
@Data
public class ResetPasswordSchema {
    private Integer id;
    private Timestamp exp;
}
