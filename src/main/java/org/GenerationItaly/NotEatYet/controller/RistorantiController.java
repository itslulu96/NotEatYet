package org.GenerationItaly.NotEatYet.controller;

import java.util.List;
import java.util.Map;

import org.GenerationItaly.NotEatYet.dao.IDaoRistorante;
import org.GenerationItaly.NotEatYet.model.Ristorante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ristoranti")
public class RistorantiController {
	
	@Autowired
	private IDaoRistorante daoR;
	
	@PostMapping
	public int aggiungiRistorante (@RequestBody Ristorante ristorante) {
		System.out.println("Email: " + ristorante.getEmail());
		return daoR.aggiungiRistorante(ristorante, ristorante.getEmail(), ristorante.getPassword());
	}
	
	@PutMapping
	public boolean modificaRistorante (@RequestBody Ristorante ristorante) {
		return daoR.modificaRistorante(ristorante);
	}   
	
	@GetMapping
	public List<Ristorante> ristoranti(){ 
		return daoR.ristoranti();
	}
	
	@GetMapping("/{id}")
	public Ristorante ristorante(@PathVariable int id) {
		return daoR.ristorante(id);
	}
	
	@PostMapping("/login")
	public int loginRistorante(@RequestBody Map<String, String> json) {
		return daoR.login(json.get("email"), json.get("password"));
	}
	
	
	/*
	@GetMapping("/{id}")
    public List<Piatto> piatti(@PathVariable int id){
        return daoR.piatti(id);
    }
    */
}
