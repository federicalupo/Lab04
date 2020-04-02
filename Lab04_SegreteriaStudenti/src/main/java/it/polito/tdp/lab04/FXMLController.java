/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FXMLController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxCorsi"
	private ComboBox<Corso> boxCorsi; // metto <Corso>!!! OGGETTO

	@FXML // fx:id="btnCercaIscritti"
	private Button btnCercaIscritti; // Value injected by FXMLLoader

	@FXML // fx:id="txtMatricola"
	private TextField txtMatricola; // Value injected by FXMLLoader

	@FXML // fx:id="btnSpunta"
	private Button btnSpunta; // Value injected by FXMLLoader

	@FXML // fx:id="txtNome"
	private TextField txtNome; // Value injected by FXMLLoader

	@FXML // fx:id="txtCognome"
	private TextField txtCognome; // Value injected by FXMLLoader

	@FXML // fx:id="btnCercaCorsi"
	private Button btnCercaCorsi; // Value injected by FXMLLoader

	@FXML // fx:id="btnIscrivi"
	private Button btnIscrivi; // Value injected by FXMLLoader

	@FXML // fx:id="txtRisultato"
	private TextArea txtRisultato; // Value injected by FXMLLoader

	@FXML // fx:id="btnReset"
	private Button btnReset; // Value injected by FXMLLoader

	@FXML
	void cercaCorsi(ActionEvent event) {
		this.txtRisultato.clear();

		try {
			Integer matricola = Integer.valueOf(this.txtMatricola.getText());

			if (this.model.getStudenteMatricola(matricola) != null) {
				List<Corso> corsi = this.model.getCorsiStudente(matricola);
				
				if(corsi.size()==0)
				{
					this.txtRisultato.setText("Studente non iscritto ad alcun corso");
					return;
				}

				for (Corso c : corsi) {
					this.txtRisultato.appendText(String.format("%-10s%-10s%-50s%s\n", c.getCodins(),
							c.getCrediti().toString(), c.getNome(), c.getPd().toString()));  //da rivedere

				}
			} else {
				this.txtRisultato.setText("Matricola non presente");
			}
		} catch (NumberFormatException nfe) {
			this.txtRisultato.setText("Inserisci matricola numerica"); // controllo di nuovo

		}

	}

	@FXML
	void cercaIscritti(ActionEvent event) {
		this.txtRisultato.clear();

		Corso c = this.boxCorsi.getValue();

		if (c != null && !c.getCodins().equals("")) {
			List<Studente> studenti = this.model.studentiPerCorso(c);
			
			if(studenti.size()==0) {
				this.txtRisultato.setText("Corso senza iscritti");
				return;
			}

			for (Studente s : studenti) {
				

				this.txtRisultato.appendText(String.format("%-10s%-25s%-25s%s\n", s.getMatricola().toString(),
						 s.getNome(),s.getCognome(), s.getCds())); // COURIER!!! :
				/*allinea a sinistra la matricola- lascia spazio
				 * allinea cognome- spazio
				 * nome- spazio
				 * cds-capo
				 */
				

			}
		} else {
			this.txtRisultato.setText("Nessun corso selezionato");
		}

	}

	@FXML
	void completamento(ActionEvent event) {
		try {

			Integer matricola = Integer.valueOf(this.txtMatricola.getText());
			Studente s = this.model.getStudenteMatricola(matricola);

			if (s != null) {
				this.txtNome.setText(s.getNome());
				this.txtCognome.setText(s.getCognome());
			} else {
				this.txtRisultato.setText("Matricola non presente");
			}

		} catch (NumberFormatException nfe) {
			this.txtRisultato.setText("Inserisci matricola numerica");

		}

	}

	@FXML
	void iscrizione(ActionEvent event) {

		// controllo che studente sia nel database
		try {

			Corso c = this.boxCorsi.getValue();
			int matricola = Integer.valueOf(this.txtMatricola.getText());

			Studente s = this.model.getStudenteMatricola(matricola);

			if (s != null && c!=null) {  //controllo anche che il corso sia selezionato

				boolean iscritto = this.model.inscriviStudenteACorso(s, c);

				if (iscritto) {
					this.txtRisultato.setText("Studente iscritto al corso!");
				} else {
					this.txtRisultato.setText("Studente già iscritto a questo corso");
				}

			} else {
				this.txtRisultato.setText("Studente non presente nel db o corso non selezionato");
			}

		} catch (NumberFormatException nfe) {
			this.txtRisultato.setText("Inserisci matricola numerica");

		}

	}

	@FXML
	void reset(ActionEvent event) {

		this.txtNome.clear();
		this.txtCognome.clear();
		this.txtMatricola.clear();
		this.txtRisultato.clear();

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSpunta != null : "fx:id=\"btnSpunta\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		this.boxCorsi.getItems().add(new Corso("", 0, "", 0));
		this.boxCorsi.getItems().addAll(this.model.getTuttiICorsi()); // chiamo toString

	}
}
