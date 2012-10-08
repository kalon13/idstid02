import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Materiale {
	private String descrizione;
	private double quantita;
	private int codice;
	private Um um;
	
	private Materiale(String descrizione) {
		this.codice = 0;
		this.descrizione = descrizione;
		this.quantita = 0;
		this.um = Um.UNIT;
	}
	
	private Materiale(int codice, String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.quantita = 0;
		this.um = Um.UNIT;
		
	}

	private Materiale(int codice, String descrizione, double quantita) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.um = Um.UNIT;
	}
	
	private Materiale(int codice, String descrizione, double quantita, Um um) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.um = um;
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

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public Um getUm() {
		return um;
	}

	public void setUm(Um um) {
		this.um = um;
	}
}
