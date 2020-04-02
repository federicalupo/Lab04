package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;

	public Model() {
		corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}

	public List<Corso> getTuttiICorsi() {

		return this.corsoDao.getTuttiICorsi();

	}

	public Studente getStudenteMatricola(Integer matricola) {

		return this.studenteDao.getStudenteMatricola(matricola);

	}

	public List<Studente> studentiPerCorso(Corso corso) {

		return this.corsoDao.getStudentiIscrittiAlCorso(corso);

	}

	public List<Corso> getCorsiStudente(Integer matricola) {

		return this.studenteDao.getCorsiStudente(matricola);
	}

	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {

		return this.corsoDao.inscriviStudenteACorso(studente, corso);

	}

}
