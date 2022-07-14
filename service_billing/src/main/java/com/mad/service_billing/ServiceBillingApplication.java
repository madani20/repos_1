package com.mad.service_billing;

import com.mad.service_billing.dto.InvoiceRequestDTO;
import com.mad.service_billing.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class ServiceBillingApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServiceBillingApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(InvoiceService invoiceService) {
		return args -> {
			invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(450000), "C01"));
			invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98800), "C01"));
			invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(12000), "C02"));
		};
	}
}
