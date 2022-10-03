package org.GenerationItaly.NotEatYet.model;

import org.GenerationItaly.NotEatYet.util.IMappablePro;

public class Ingrediente implements IMappablePro{                                    
  
	private int id;      
	private String nome;        
	private int idPiatto;          
 
	public Ingrediente(int id, String nome,int idPiatto) {           
		super();    
		this.id = id;              
		this.nome = nome;  
		this.idPiatto=idPiatto;
 
	}    
 
  
	public int getIdPiatto() {
		return idPiatto;
	}

	public void setIdPiatto(int idPiatto) {
		this.idPiatto = idPiatto;
	}

	public int getId() {
		return id;
	} 

	public void setId(int id) {     
		this.id = id;
	} 

	public Ingrediente() { 
		super();
	}

	public String getNome() {
		return nome;
	} 

	public void setNome(String nome) {  
		this.nome = nome;         
	} 
                         
	@Override  
	public String toString() {  
		return "{ id : " + id + ", nome : " + nome + ", idPiatto : " + idPiatto + "}";
	}
  
 


}