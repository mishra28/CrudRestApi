package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.inter.InvoiceRepo;
import com.example.demo.model.Invoice;
import org.springframework.stereotype.Component;
@Component
public class InvoiceCrudService {
	@Autowired
	InvoiceRepo repository;
	public ResponseEntity<Invoice> validateMobileNo(Invoice invoice) {
		String regex = "^[6-9]\\d{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(invoice.getMobileno());
		if(matcher.matches())
		{
			//repo.save(invoice);
		    //return invoice ;
			return new ResponseEntity<>(repository.save(invoice), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	public void deleteFromDb(int aid) {
		Invoice invoices = repository.getOne(aid);
		
		repository.delete(invoices);
	}
	
	public List<Invoice> findAll() {
		return repository.findAll();
	}
	public ResponseEntity<Invoice> updateAll(int aid, Invoice invoice) {
		Optional<Invoice> invoices = repository.findById(aid);
		if(invoices.isPresent())
		{
			return validateMobileNo(invoice);
			//repo.save(invoice);
		    //return invoice ;
			//return new ResponseEntity<>(repo.save(invoice), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	public Optional<Invoice> findById(int aid) {
		return repository.findById(aid);
	}

}
