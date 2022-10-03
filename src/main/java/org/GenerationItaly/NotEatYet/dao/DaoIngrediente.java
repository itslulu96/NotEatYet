package org.GenerationItaly.NotEatYet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.GenerationItaly.NotEatYet.model.Ingrediente;
import org.GenerationItaly.NotEatYet.util.BasicDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoIngrediente extends BasicDao implements IDaoIngrediente{

	public DaoIngrediente(@Value("${db.address}")String dbAddress,
			 @Value("${db.user}")String username,
			 @Value("${db.psw}")String password) {
			 super(dbAddress,username,password);
	}

	@Override
	public List<Ingrediente> ingredienti() {
		return listFromQuery("SELECT * FROM ingredienti", Ingrediente.class);
	}

	@Override
	public Ingrediente ingrediente(int id) {
		
		return objectFromQuery("SELECT * FROM ingredienti WHERE id = ?", Ingrediente.class,id);
	}

	@Override
	public boolean aggiungiIngrediente(Ingrediente ingrediente) {
		return isExecute("INSERT INTO ingredienti (nome) VALUES (?)",
				ingrediente.getNome());
	} 
 
	@Override
	public boolean modificaIngrediente(Ingrediente ingrediente) {
		return isExecute("UPDATE ingredienti SET nome = ? WHERE id = ?",ingrediente.getNome(),ingrediente.getId());
	}

	@Override
	public boolean eliminaIngrediente(int id) { 
		return isExecute ("DELETE FROM ingredienti WHERE id = ?", id);
	}

	@Override
	public List<Ingrediente> ingredienti(int idPiatto) {
		return listFromQuery("SELECT * FROM ingredienti WHERE idpiatto = ?", Ingrediente.class, idPiatto);
	}
	
	/*
	public ResultSet getIntFromQuery(int id) throws SQLException {
		return executeQueryWithResult("SELECT idPiatto FROM ingredienti WHERE id = ?", id);
	} 
	*/

}