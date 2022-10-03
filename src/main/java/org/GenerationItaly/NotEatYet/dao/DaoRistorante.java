package org.GenerationItaly.NotEatYet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.GenerationItaly.NotEatYet.model.Ristorante;
import org.GenerationItaly.NotEatYet.util.BasicDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
@Repository
public class DaoRistorante extends BasicDao implements IDaoRistorante{

	public DaoRistorante(@Value("${db.address}")String dbAddress,
			 @Value("${db.user}")String username,
			 @Value("${db.psw}")String password) { 
		super(dbAddress,username,password);
}
	@Override
	public boolean modificaRistorante(Ristorante ristorante) {
		return isExecute("UPDATE ristoranti SET ragione_sociale = ?, p_iva = ?, regione = ?, citta = ?, via = ?, n_civico = ? WHERE id = ?",
						 ristorante.getRagione_sociale(), ristorante.getP_iva(), ristorante.getRegione(), ristorante.getCitta(), ristorante.getVia(), ristorante.getN_civico(), ristorante.getId());
	}

	@Override
	public int aggiungiRistorante(Ristorante ristorante, String email, String password) {  
		int idNuovoRistorante = insertAndGetId("INSERT INTO ristoranti (ragione_sociale, p_iva, regione, citta, via, n_civico) VALUES (?,?,?,?,?,?)",
		 		ristorante.getRagione_sociale(), ristorante.getP_iva(), ristorante.getRegione(), ristorante.getCitta(), ristorante.getVia(), ristorante.getN_civico());
		 execute("INSERT INTO anagrafeRistoranti(email, passw, idRistorante) VALUES (?,?,?)", email, password, idNuovoRistorante);
		 return idNuovoRistorante;
	}
	  
	@Override  
	public int login(String email, String password) {  
		try {
			ResultSet rs = executeQueryWithResult("SELECT idRistorante FROM anagrafeRistoranti WHERE email = ? AND passw = ?", email, password);
			return rs.next() ? rs.getInt(1) : -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return -1;
	}
	
	@Override
	public List<Ristorante> ristoranti() {
		return listFromQuery("SELECT * FROM ristoranti", Ristorante.class);
	}
	/*
	@Override
	public List<Piatto> piatti(int id) {
		return listFromQuery("SELECT * FROM piatti WHERE idristorante = ?", Piatto.class, id);
	}
	*/
	
	@Override
	public Ristorante ristorante(int id) {
		return objectFromQuery("SELECT * FROM ristoranti WHERE id= ?",Ristorante.class,id);
	}

}