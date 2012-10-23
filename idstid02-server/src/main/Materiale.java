package main;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Materiale {
	private int id;
	private String descrizione;
	private double quantita;
	private String codice;
	private Um um;
	
	public Materiale() {
		this.id = -1;
		this.codice = "0";
		this.descrizione = "";
		this.quantita = 0;
	}
	
	public Materiale(int id, String codice, String descrizione) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.quantita = 0;
		this.um = Um.UNIT;
		
	}

	public Materiale(int id, String codice, String descrizione, double quantita) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.um = Um.UNIT;
	}
	
	public Materiale(int id, String codice, String descrizione, double quantita, Um um) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.um = um;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Um getUm() {
		return um;
	}

	public void setUm(Um um) {
		this.um = um;
	}
}
