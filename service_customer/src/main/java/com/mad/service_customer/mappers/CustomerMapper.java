package com.mad.service_customer.mappers;

import com.mad.service_customer.dto.CustomerRequestDTO;
import com.mad.service_customer.dto.CustomerResponseDTO;
import com.mad.service_customer.entities.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);

    //Customer customerResponseDTOToCustomer(CustomerResponseDTO customerResponseDTO);
    //List<CustomerResponseDTO> listCustomersResponseDTO(List<Customer> customers);

}
