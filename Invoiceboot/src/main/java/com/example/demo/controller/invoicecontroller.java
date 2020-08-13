package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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
@RestController
public class invoicecontroller 
{
	
	@Autowired
	InvoiceRepo repo;
	
	@RequestMapping("/")
	public String home() 
	{
		return "home.jsp";
	}
	
	@DeleteMapping("/invoice/{aid}")
	public String deleteInvoice(@PathVariable int aid)
	{
		Invoice a = repo.getOne(aid);
		
		repo.delete(a);
		
		return "deleted";
	}
	

	@PostMapping("/invoice")
	public Invoice addinvoice(Invoice invoice)
	{
		repo.save(invoice);
		return invoice ;
	}
	
	@GetMapping("/invoice")
	public List<Invoice> getinvoice()
	{
		//ModelAndView mv= new ModelAndView("showinvoice.jsp");
		//Invoice invoice = repo.findById(aid).orElse(new Invoice());
		//mv.addObject(invoice);
		//return mv;
		return repo.findAll();
	}
	
	@PutMapping("/invoice")
	public Invoice updateinvoice(Invoice invoice)
	{
		//Optional<Invoice> aa = repo.findById(aid);
		
		//if(aa.isPresent())
		//{
			repo.save(invoice);
		    return invoice ;
		//}
		
	}
	@RequestMapping("/invoice/{aid}")
	public Optional<Invoice> getinvoice(@PathVariable("aid") int aid)
	{
		
		return repo.findById(aid);
	}

}
