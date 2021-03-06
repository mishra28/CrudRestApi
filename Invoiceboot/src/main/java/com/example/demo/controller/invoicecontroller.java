package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.Invoice;
import com.example.demo.model.responseDto.InvoiceResponseDto;
import com.example.demo.service.InvoiceCrudServiceImpl;

@RestController
public class InvoiceController {
	@Autowired
	InvoiceCrudServiceImpl invoiceCrudService;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@DeleteMapping("/invoice/{aid}")
	public String DeleteInvoice(@PathVariable int aid) {
		invoiceCrudService.deleteFromDb(aid);

		return "deleted";
	}

	@PostMapping("/invoice")
	public ResponseEntity<Invoice> AddInvoice(@Valid @RequestBody Invoice invoice) {
		return invoiceCrudService.validateMobileNo(invoice);
	}

	@GetMapping("/invoice")
	public List<InvoiceResponseDto> GetInvoice() {
		return invoiceCrudService.findAllCustomer();
	}

	@PutMapping("/invoice/{aid}")
	public ResponseEntity<Invoice> UpdateInvoice(@PathVariable("aid") int aid, @Valid @RequestBody Invoice invoice) {
		return invoiceCrudService.updateAll(aid, invoice);
	}

	@RequestMapping("/invoice/{aid}")
	public Optional<Invoice> GetInvoice(@PathVariable("aid") int aid) {

		return invoiceCrudService.findById(aid);
	}

}
