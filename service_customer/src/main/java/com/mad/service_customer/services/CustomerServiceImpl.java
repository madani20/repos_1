package com.mad.service_customer.services;

import com.mad.service_customer.dto.CustomerRequestDTO;
import com.mad.service_customer.dto.CustomerResponseDTO;
import com.mad.service_customer.entities.Customer;
import com.mad.service_customer.mappers.CustomerMapper;
import com.mad.service_customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        //CustomerResponseDTO customerResponseDTO=customerMapper.customerRequestDTOToCustomerResponseDTO(customerRequestDTO);
        Customer customer=customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer saveCustomer=customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO=customerMapper.customerToCustomerResponseDTO(saveCustomer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO getCustomer(String id){
        Customer customer=customerRepository.findById(id).get();
        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer=customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer updatedCustomer=customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> customerMapper.customerToCustomerResponseDTO(customer))
                .collect(Collectors.toList());
    }
}
