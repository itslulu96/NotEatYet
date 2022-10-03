package org.GenerationItaly.NotEatYet.model;

import org.GenerationItaly.NotEatYet.util.IMappablePro;

public class Ristorante implements IMappablePro{ 

	private int id;
	private String ragione_sociale;
	private String p_iva;
	private String regione;
	private String citta;
	private String via;
	private int n_civico;
	private String email;
	private String password; 
	
	public Ristorante(int id, String ragione_sociale, String p_iva, String regione, String citta, String via,
			int n_civico) {
		super();
		this.id = id;
		this.ragione_sociale = ragione_sociale;
		this.p_iva = p_iva;
		this.regione = regione;
		this.citta = citta;
		this.via = via;
		this.n_civico = n_civico;
	}
	
	public Ristorante(int id, String ragione_sociale, String p_iva, String regione, String citta, String via,
			int n_civico, String email, String password) {
		super();
		this.id = id;
		this.ragione_sociale = ragione_sociale;
		this.p_iva = p_iva;
		this.regione = regione;
		this.citta = citta;
		this.via = via;
		this.n_civico = n_civico;
		this.email = email;
		this.password = password;
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ristorante() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRagione_sociale() {
		return ragione_sociale;
	}
	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}
	public String getP_iva() {
		return p_iva;
	}
	public void setP_iva(String p_iva) {
		this.p_iva = p_iva;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public int getN_civico() {
		return n_civico;
	}
	public void setN_civico(int n_civico) {
		this.n_civico = n_civico;
	}
	@Override
	public String toString() {
		return "{ id : " + id + ", ragione_sociale : " + ragione_sociale + ", p_iva : " + p_iva + ", regione : "
				+ regione + ", citta : " + citta + ", via : " + via + ", n_civico : " + n_civico + " }";
	}
	
}

