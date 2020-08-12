package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.inter.InvoiceRepo;

import com.example.demo.model.Invoice;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class invoicecontroller 
{
	
	@Autowired
	InvoiceRepo repo;
	
	@RequestMapping("/")
	public String home() 
	{
		return "home.jsp";
	}
	
	@RequestMapping("/addinvoice")
	public String addinvoice(Invoice invoice)
	{
		repo.save(invoice);
		return "home.jsp";
	}
	
	@RequestMapping("/getinvoice")
	public ModelAndView getinvoice(@RequestParam int aid)
	{
		ModelAndView mv= new ModelAndView("showinvoice.jsp");
		Invoice invoice = repo.findById(aid).orElse(new Invoice());
		mv.addObject(invoice);
		return mv;
	}

}
