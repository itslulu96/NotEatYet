package org.GenerationItaly.NotEatYet.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.GenerationItaly.NotEatYet.dao.IDaoIngrediente;
import org.GenerationItaly.NotEatYet.model.Ingrediente;
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
@RequestMapping("/ingredienti")
public class IngredientiController { 
 
	@Autowired    
	private IDaoIngrediente daoI;                                    
	 
	@GetMapping 
	public List<Ingrediente> ingredienti(){
		return daoI.ingredienti();
	}
	
	@GetMapping("/{id}") 
	public Ingrediente ingrediente(@PathVariable int id) {
		return daoI.ingrediente(id);
	}
	
	/*@GetMapping("/idPiatto")
	public int idPiatto(@PathVariable int id) throws SQLException {
		ResultSet rs = daoI.getIntFromQuery(id);
		int idPiatto = ((Number) rs.getObject(1)).intValue();
		return idPiatto;
	}
	*/
	
	@PostMapping
	public boolean aggiungiIngrediente (@RequestBody Ingrediente ingrediente) {
		return daoI.aggiungiIngrediente(ingrediente);
	}
	
	@DeleteMapping("/{id}")
	public boolean cancellaIngrediente (@PathVariable int id) {
		return daoI.eliminaIngrediente(id);
	}
	
	@PutMapping
	public boolean modificaIngrediente (@RequestBody Ingrediente ingrediente) {
		return daoI.modificaIngrediente(ingrediente);
	}
	 
	// AGGIUNTA CODICE SUSANNA
	@GetMapping("/piatti/{id}")
	public List<Ingrediente> ingredienti(@PathVariable int id) {
		return daoI.ingredienti(id); 
	}
}










