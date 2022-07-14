package com.mad.service_billing.repositories;

import com.mad.service_billing.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    List<Invoice> findByCustomerId(String customerId);

}
