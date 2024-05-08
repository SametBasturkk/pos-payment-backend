package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.schema.ResetPasswordSchema;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepo extends CrudRepository<ResetPasswordSchema, String> {
}
