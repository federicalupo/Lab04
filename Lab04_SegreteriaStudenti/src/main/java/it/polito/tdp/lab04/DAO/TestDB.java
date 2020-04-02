package it.polito.tdp.lab04.DAO;

import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		List<Corso> c= cdao.getTuttiICorsi();
		
		System.out.println("Tutti i corsi: " +c);
		
		StudenteDAO sdao= new StudenteDAO();
		System.out.println("Studente- "+sdao.getStudenteMatricola(169195));
		
		System.out.println("Studenti per corso- "+cdao.getStudentiIscrittiAlCorso(new Corso("09AQGNG",8,"Economia aziendale",1)));

		System.out.println("Corsi per studente- "+sdao.getCorsiStudente(169195));
		
		Studente s= new Studente(151528,"", "","");
		Corso cor= new Corso("09AQGNG",8,"Economia aziendale",1);
		
		System.out.println("Inserisci   "+ cdao.inscriviStudenteACorso(s, cor));
		
	}

}
