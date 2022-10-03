package org.GenerationItaly.NotEatYet.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.GenerationItaly.NotEatYet.dao.IDaoIngrediente;
import org.GenerationItaly.NotEatYet.dao.IDaoPiatto;
import org.GenerationItaly.NotEatYet.model.Ingrediente;
import org.GenerationItaly.NotEatYet.model.Piatto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/piatti")
public class PiattiController {    

	 
	@Autowired 
	private IDaoPiatto daoP;
	
	@GetMapping
    public List<Piatto> piatti(){
        return daoP.piatti();
    }
	
	@GetMapping("/{id}")
	public Piatto piatto(@PathVariable int id) {
		return daoP.piatto(id);
	}
	
	@PostMapping  
	public boolean aggiungiPiatto(@RequestBody Piatto piatto) {  
		int idPiattoAggiunto = daoP.aggiungiPiatto(piatto);
		if(idPiattoAggiunto < 1) {
			System.out.println("ID PIATTO NON TROVATO CONTROLLER");
			return false;
		}
		
		List<Ingrediente> ingredienti = piatto.getIngredienti(); 
		
		for (Ingrediente ingrediente : ingredienti) {
			ingrediente.setIdPiatto(idPiattoAggiunto);
		}
		
		return daoP.setOrAddIngredients(ingredienti);  
	}     
	 
	@DeleteMapping("/{id}")   
	public boolean cancellaPiatto(@PathVariable int id) {
		return daoP.eliminaPiatto(id);
	}
	
	@PutMapping 
	public boolean modificaPiatto (@RequestBody Piatto piatto) {
		return  daoP.modificaPiatto(piatto) && daoP.setOrAddIngredients(piatto.getIngredienti());
	}
	
	@GetMapping("/ristoranti/{idRistorante}") 
	public List<Piatto> piatti(@PathVariable int idRistorante){
		return daoP.piatti(idRistorante);
	}

}