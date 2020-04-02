package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);

			}

			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {

		final String sql = "select s.matricola, s.cognome, s.nome, s.cds from studente as s,  iscrizione as i where s.matricola=i.matricola  && i.codins=?";
		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("CDS"));
				studenti.add(s);
			}

			conn.close();

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}

		return studenti;

	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// no final?? giustO???
		String sql = "select count(*) c from iscrizione i,  studente s where i.`matricola`=s.`matricola` && i.`codins`=? && s.`matricola`=?";
		boolean presente = false;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, studente.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) { 
				if (rs.getInt("c") > 0) {
					presente = true;
				}

			}

			st.close();

			if (!presente) {

				sql = "insert into iscrizione(matricola, codins) values (?,?)";
				PreparedStatement st1 = conn.prepareStatement(sql);
				st1.setInt(1, studente.getMatricola());
				st1.setString(2, corso.getCodins());

				Integer righe = st1.executeUpdate();
				if (righe > 0)
					return true;
				else
					return false; //serve??

			}

			conn.close();

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}

		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
