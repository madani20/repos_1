package com.mad.service_customer.api;

import com.mad.service_customer.dto.CustomerRequestDTO;
import com.mad.service_customer.dto.CustomerResponseDTO;
import com.mad.service_customer.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
         this.customerService = customerService;
    }
    @GetMapping("/customers")
    public List<CustomerResponseDTO> allCustomers(){
        return customerService.listCustomers();

    }
    @GetMapping("/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id) throws Exception {
        return customerService.getCustomer(id);
    }
    @PostMapping("/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }
}
