package com.mad.service_customer;

import com.mad.service_customer.dto.CustomerRequestDTO;
import com.mad.service_customer.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCustomerApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerService customerService) {
		return args -> {

			customerService.save(new CustomerRequestDTO("C01", "Frida", "frida@free.fr"));
			customerService.save(new CustomerRequestDTO("C02", "Isabelle", "isabelle@belle.org"));
			customerService.save(new CustomerRequestDTO("C03", "Blondina", "blondina@free.fr"));
			customerService.save(new CustomerRequestDTO("C04", "Bruna", "bruna@belle.org"));
		};

	}
}
