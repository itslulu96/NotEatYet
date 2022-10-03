package org.GenerationItaly.NotEatYet.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.GenerationItaly.NotEatYet.model.Ingrediente;
import org.GenerationItaly.NotEatYet.model.Piatto;
import org.GenerationItaly.NotEatYet.util.BasicDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoPiatto extends BasicDao implements IDaoPiatto{

	public DaoPiatto(@Value("${db.address}")String dbAddress,
					 @Value("${db.user}")String username,
					 @Value("${db.psw}")String password) {
		super(dbAddress,username,password);
	}
	
	@Override
	public List<Piatto> piatti() {
		List<Piatto> piatti = listFromQuery("SELECT * FROM piatti",Piatto.class);
		List<Ingrediente> ingredienti;
		for(int i=0; i<piatti.size(); i++) {
			int idPiatto=piatti.get(i).getId();
			if(piatti.get(i).getId()== idPiatto) {
				 ingredienti= listFromQuery("SELECT * FROM ingredienti WHERE idpiatto= ?",Ingrediente.class,idPiatto);
				 piatti.get(i).setIngredienti(ingredienti);
			}	
		}
		return piatti;
    }

	@Override
	public Piatto piatto(int id) {
		Piatto piatto = objectFromQuery("SELECT * FROM piatti WHERE id = ?", Piatto.class, id);
		List<Ingrediente>ingredienti= listFromQuery("SELECT * FROM ingredienti WHERE idpiatto= ?",Ingrediente.class,id);
		piatto.setIngredienti(ingredienti);
		return piatto;
	} 

	@Override
	public int aggiungiPiatto(Piatto piatto) {
		int id = insertAndGetId("INSERT INTO piatti (nome, prezzo, categoria, vegano, vegetariano, idristorante) VALUES (?, ?, ?, ?, ?, ?)", 
					piatto.getNome(),piatto.getPrezzo(), piatto.getCategoria(), piatto.getVegano(), piatto.getVegetariano(), piatto.getIdRistorante());
		System.out.println(id == 0? "ID NON TROVATO DAO" : "DAO TROVATO ID " + id);
		return id;
	}

	@Override
	public boolean modificaPiatto(Piatto piatto) {
		return isExecute("UPDATE piatti SET nome = ?, prezzo = ?, categoria = ?, vegano = ?, vegetariano = ? WHERE id = ?",
				piatto.getNome(), piatto.getPrezzo(), piatto.getCategoria(), piatto.getVegano(), piatto.getVegetariano(), piatto.getId());
	} 

	@Override
	public boolean eliminaPiatto(int id) {
		execute("DELETE FROM ingredienti WHERE idPiatto = ?", id);
		return isExecute("DELETE FROM piatti WHERE id = ?", id);
	}
	
	@Override
	public List<Piatto> piatti(int idRistorante) {
		List<Piatto> piatti = listFromQuery("SELECT * FROM piatti WHERE idristorante=?",Piatto.class, idRistorante);
		List<Ingrediente> ingredienti;
		for(int i=0; i<piatti.size(); i++) {
			int idPiatto=piatti.get(i).getId();
			 ingredienti= listFromQuery("SELECT * FROM ingredienti WHERE idpiatto= ?",Ingrediente.class,idPiatto);
			 piatti.get(i).setIngredienti(ingredienti);
		}
		return piatti;
	}
	
	// tutti gli ingredienti che passi alla funzione devono avere lo stesso idPiatto, importante!!!!
	@Override
	public boolean setOrAddIngredients(List<Ingrediente> ingredienti) {
		if(ingredienti.size() == 0) {
			return false; 
		}
		
		System.out.println("sono nella funzione setOrAdd");
		
		int idPiatto = ingredienti.get(0).getIdPiatto();
		
		execute("DELETE FROM ingredienti WHERE idPiatto = ?", idPiatto);
		
		boolean result = true;
		for(Ingrediente ingrediente : ingredienti) {
			// result = result && execute -> result parte che è true, se una volta questa operazione fallisce allora 
			System.out.println(ingrediente.toString());
			System.out.println("QUERY = INSERT INTO ingredienti (nome, idPiatto) VALUES (" + ingrediente.getNome() + "," + idPiatto + ")" );
			if(!isExecute("INSERT INTO ingredienti (nome, idPiatto) VALUES (?, ?)", ingrediente.getNome(), idPiatto)) {
				result = false;
			} 
		}
		System.out.println(result? "INGREDIENTI AGGIUNTI CORRETTAMENTE" : "NON SONO RIUSCITO AD AGGIUNGERE INGREDIENTI");
		return result;
	}
	
}