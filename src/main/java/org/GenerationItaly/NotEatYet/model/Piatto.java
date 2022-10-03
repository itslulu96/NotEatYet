package org.GenerationItaly.NotEatYet.model;

import java.util.ArrayList;
import java.util.List;

import org.GenerationItaly.NotEatYet.util.IMappablePro;



public class Piatto implements IMappablePro{

	private static final List<String> ALLERGENI = new ArrayList<String>();
	
	static {
		ALLERGENI.add("crostecei");
	    ALLERGENI.add("uova");
	    ALLERGENI.add("pesce");
	    ALLERGENI.add("arachidi");
	    ALLERGENI.add("soia");
	    ALLERGENI.add("latte");
	    ALLERGENI.add("frutta a guscio");
	    ALLERGENI.add("sedano");
	    ALLERGENI.add("senape");
	}
	
	private int id;
	private String nome;
	private double prezzo;
	private String categoria;
	private List<Ingrediente> ingredienti;
	private String vegano;
	private String vegetariano;
	private int idRistorante;
	


	public Piatto(int id, String nome, double prezzo, String categoria, List<Ingrediente> ingredienti, String vegano,
			String vegetariano, int idRistorante) {
		super();
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.categoria = categoria;
		this.ingredienti = ingredienti;
		this.vegano = vegano;
		this.vegetariano = vegetariano;
		this.idRistorante = idRistorante;
	}

	public Piatto() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setIdRistorante(int idRistorante) {
		this.idRistorante = idRistorante;
	}
	
	public int getIdRistorante() {
		return idRistorante;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	


	public String getVegano() {
		return vegano;
	}

	public void setVegano(String vegano) {
		this.vegano = vegano;
	}

	public String getVegetariano() {
		return vegetariano;
	}

	public void setVegetariano(String vegetariano) {
		this.vegetariano = vegetariano;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public static List<String> getAllergeni() {
		return ALLERGENI;
	}
	
	public boolean containsAllergeni(Piatto piatto) {
		List<Ingrediente> ingredienti = piatto.getIngredienti();
		for(Ingrediente i : ingredienti) {
			if(ALLERGENI.contains(i.getNome())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "{ id : " + id + ", nome : " + nome + ", prezzo : " + prezzo + ", categoria : " + categoria
				+ ", ingredienti : " + ingredienti + ", vegano : " + vegano + ", vegetariano : " + vegetariano + " }";
	}

	

	
}