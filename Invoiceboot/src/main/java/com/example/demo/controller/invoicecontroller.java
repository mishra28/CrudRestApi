package com.example.demo.controller;


import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.inter.InvoiceRepo;

import com.example.demo.model.Invoice;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
public class invoicecontroller 
{
	
	@Autowired
	InvoiceRepo repository;
	
	@RequestMapping("/")
	public String home() 
	{
		return "home.jsp";
	}
	
	@DeleteMapping("/invoice/{aid}")
	public String DeleteInvoice(@PathVariable int aid)
	{
		Invoice invoices = repository.getOne(aid);
		
		repository.delete(invoices);
		
		return "deleted";
	}
	

	@PostMapping("/invoice")
	public ResponseEntity<Invoice> AddInvoice(@Valid @RequestBody Invoice invoice)
	{
		String regex = "^[6-9]\\d{9}$";
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(invoice.getMobileno());
        if(matcher.matches())
		{
        	//repo.save(invoice);
		    //return invoice ;
        	return new ResponseEntity<>(repository.save(invoice), HttpStatus.OK);
		}
         //return null;
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/invoice")
	public List<Invoice> GetInvoice()
	{
		//ModelAndView mv= new ModelAndView("showinvoice.jsp");
		//Invoice invoice = repo.findById(aid).orElse(new Invoice());
		//mv.addObject(invoice);
		//return mv;
		return repository.findAll();
	}
	
	@PutMapping("/invoice/{aid}")
	public ResponseEntity<Invoice>UpdateInvoice(@PathVariable("aid")int aid,@Valid @RequestBody Invoice invoice)
	{
		Optional<Invoice> invoices = repository.findById(aid);
		if(invoices.isPresent())
		{
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
			//repo.save(invoice);
		    //return invoice ;
			//return new ResponseEntity<>(repo.save(invoice), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@RequestMapping("/invoice/{aid}")
	public Optional<Invoice> GetInvoice(@PathVariable("aid") int aid)
	{
		
		return repository.findById(aid);
	}

}
