package com.pospayment.pospayment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PospaymentApplication {

	public static void main(String[] args) {
		log.info("Starting POS Payment Application");
		SpringApplication.run(PospaymentApplication.class, args);
	}

}
