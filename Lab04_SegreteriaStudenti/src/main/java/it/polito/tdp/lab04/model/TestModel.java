package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
	System.out.println("Corsi: "+model.getTuttiICorsi());
		System.out.println("Studente: "+model.getStudenteMatricola(169195));
		System.out.println("StudentiCorso: "+model.studentiPerCorso(new Corso("09AQGNG",8,"Economia aziendale",1)));
	}

}
