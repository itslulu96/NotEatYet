package org.GenerationItaly.NotEatYet.dao;

import java.util.List;

import org.GenerationItaly.NotEatYet.model.Ristorante;

public interface IDaoRistorante {
	
	//List<Piatto>piatti(int id);
	List<Ristorante> ristoranti();
	boolean modificaRistorante(Ristorante ristorante);
	int aggiungiRistorante(Ristorante ristorante, String email, String password);
	Ristorante ristorante(int id);
	int login(String email, String password);
	
}