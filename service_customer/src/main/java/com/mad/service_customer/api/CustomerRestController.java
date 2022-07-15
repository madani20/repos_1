package com.mad.service_customer.api;

import com.mad.service_customer.dto.CustomerRequestDTO;
import com.mad.service_customer.dto.CustomerResponseDTO;
import com.mad.service_customer.services.CustomerService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable String id) throws Exception {
        try{
            return ResponseEntity.ok(customerService.getCustomer(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }
}
