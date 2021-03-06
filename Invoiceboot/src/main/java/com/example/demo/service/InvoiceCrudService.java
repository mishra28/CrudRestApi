package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.entity.Invoice;
import com.example.demo.model.responseDto.InvoiceResponseDto;

public interface InvoiceCrudService {
	public ResponseEntity<Invoice> validateMobileNo(Invoice invoice);

	public void deleteFromDb(int aid);

	public List<InvoiceResponseDto> findAllCustomer();

	public ResponseEntity<Invoice> updateAll(int aid, Invoice invoice);

	public Optional<Invoice> findById(int aid);
}
