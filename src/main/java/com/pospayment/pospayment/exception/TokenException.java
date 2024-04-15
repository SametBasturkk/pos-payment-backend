package com.pospayment.pospayment.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenException extends RuntimeException {
    public TokenException(String message, Exception e) {
        super(message);
        log.error(message, e);
    }
}
