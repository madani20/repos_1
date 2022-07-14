package com.mad.service_billing.services;

import com.mad.service_billing.clientRestopenfeign.CustomerRestClient;
import com.mad.service_billing.dto.InvoiceRequestDTO;
import com.mad.service_billing.dto.InvoiceResponseDTO;
import com.mad.service_billing.entities.Customer;
import com.mad.service_billing.entities.Invoice;
import com.mad.service_billing.exception.CustomerNotFoundException;
import com.mad.service_billing.mappers.InvoiceMapper;
import com.mad.service_billing.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }
    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        /*
        Vérification de  l'intégrité référentielle Invoice / Customer
         */
        Customer customer=null;
        try{
              customer=customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        }
        catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found");
        }
        Invoice invoice=invoiceMapper.invoiceRequestDTOToInvoice(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice saveInvoice=invoiceRepository.save(invoice);
        invoice.setCustomer(customer);
        return invoiceMapper.invoiceToInvoiceResponseDTO(saveInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice=invoiceRepository.findById(invoiceId).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.invoiceToInvoiceResponseDTO(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices=invoiceRepository.findByCustomerId(customerId);
        invoices.forEach(invoice -> {
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        });
        return invoices.stream()
                .map(invoice -> invoiceMapper.invoiceToInvoiceResponseDTO(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices=invoiceRepository.findAll();
        invoices.forEach(invoice -> {
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        });

        return invoiceRepository.findAll()
                .stream()
                .map(invoice -> invoiceMapper.invoiceToInvoiceResponseDTO(invoice))
                .collect(Collectors.toList());
    }

}
