package com.service.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> genrateInvoice(@RequestBody InvoiceRequest invoiceRequest){
		String invoice = taskSerice.genrateInvoice(invoiceRequest);
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}
	
}
