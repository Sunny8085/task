package com.service.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.task.dto.InvoiceRequest;
import com.service.task.service.InvoiceService;

@RestController
@RequestMapping("api/v1/")
public class InvoiceController {
	
	@Autowired private InvoiceService taskSerice;
	
	@PostMapping("generate-bill")
	public String genrateInvoice(@RequestBody InvoiceRequest invoiceRequest){
		return taskSerice.genrateInvoice(invoiceRequest);
	}
	
}
