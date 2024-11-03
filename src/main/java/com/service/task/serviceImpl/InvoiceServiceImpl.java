package com.service.task.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.task.dto.InvoiceRequest;
import com.service.task.service.InvoiceService;
import com.service.task.util.PDFGeneration;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired private PDFGeneration pdfGeneration;
	
	@Override
	public String genrateInvoice(InvoiceRequest invoiceRequest) {
		return pdfGeneration.generateInvoicePdf(invoiceRequest);
	}

}
