package com.service.task.service;

import com.service.task.dto.InvoiceRequest;

public interface InvoiceService {

	String genrateInvoice(InvoiceRequest invoiceRequest);

}
