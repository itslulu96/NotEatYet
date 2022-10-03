package org.GenerationItaly.NotEatYet.controller;

import org.GenerationItaly.NotEatYet.dao.IDaoRistorante;
import org.GenerationItaly.NotEatYet.model.Ristorante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

 
public class IndexController {  
	
	@Autowired        
	private IDaoRistorante daoR;                               
               
	@GetMapping("/")     
	public String index() {            
		return "index";      
	}  
	   
	@GetMapping(value = "/errore") 
	public String error() {    
		return "errore.html"; 
	}    
	
	//Prova riuscita, ma non riesco a rendirizzare la pagina 
	/* 
	@GetMapping(value = "/pannello/{id}")
	public String pannello(Model model, @PathVariable int id) {
		return "pannello-controllo";   
	}
	*/ 
	   
	@GetMapping(value = "/aggiungiRistorante")
	public String aggiungiRist() {    
		return "aggiungiRistorante";   
	}     
	    
	@GetMapping(value = "/pannello") 
	public String pannello() {       
		return "pannello-controllo";   
	}
	/*
	@RequestMapping(value = "/pannello/{id}", method = RequestMethod.GET)
	public ModelAndView pannello(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("pannello-controllo");
		Ristorante ristorante = daoR.ristorante(id);
		mv.addObject("ristorante", ristorante); 
		return mv; 
	} 
	*/ 
	
	 
	
} 
 
 
