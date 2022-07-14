package com.mad.service_billing.mappers;

import com.mad.service_billing.entities.Invoice;
import com.mad.service_billing.dto.InvoiceRequestDTO;
import com.mad.service_billing.dto.InvoiceResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice invoiceRequestDTOToInvoice (InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO invoiceToInvoiceResponseDTO(Invoice invoice);
}