package org.GenerationItaly.NotEatYet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.GenerationItaly.NotEatYet.model.Ingrediente;
import org.GenerationItaly.NotEatYet.model.Piatto;

public interface IDaoPiatto {
	List<Piatto> piatti();
	Piatto piatto(int id);
	int aggiungiPiatto(Piatto piatto);
	boolean modificaPiatto(Piatto piatto);
	boolean eliminaPiatto(int id);
	List<Piatto> piatti(int idRistorante);
	boolean setOrAddIngredients(List<Ingrediente> ingredienti);
}