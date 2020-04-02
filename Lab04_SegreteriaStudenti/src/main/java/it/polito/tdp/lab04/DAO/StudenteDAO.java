package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudenteMatricola(Integer matricola) { 

		final String sql = "select * from studente where matricola=?";
		Studente s = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("CDS"));
			}

			conn.close();

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}

		return s;

	}

	
	public List<Corso> getCorsiStudente(Integer matricola){
		final String sql= "select c.`codins`, c.`crediti`, c.`nome`, c.`pd` from corso as c, iscrizione as i where c.`codins`=i.`codins` && i.`matricola`=?";
		List<Corso> corsi= new LinkedList<>();
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsi.add(c);
			}

			conn.close();

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}
		
		return corsi;
		
	}
	
		
	
	
	
}





