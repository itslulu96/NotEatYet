package org.GenerationItaly.NotEatYet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.GenerationItaly.NotEatYet.model.Ingrediente;

public interface IDaoIngrediente {
	List<Ingrediente> ingredienti();
	Ingrediente ingrediente (int id);
	boolean aggiungiIngrediente (Ingrediente ingrediente);
	boolean modificaIngrediente (Ingrediente ingrediente);
	boolean eliminaIngrediente (int id);
	//public ResultSet getIntFromQuery(int id) throws SQLException;
	List<Ingrediente> ingredienti(int idPiatto);   
}