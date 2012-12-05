package classResources;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fattura {
	private int id;
	private int numFattura;
	private String dataEmissione;
	private double importo;
	private int idTerz;
	private List<Fattura_Lavorazione> fattLavorazione;
	private int idBolla;
	//TODO: aggiungere terzista_Utente_Id
	//TODO: aggiungere terzista_Id

	public Fattura() {
		this.id = -1;
		this.numFattura = -1;
		this.dataEmissione = "";
		this.importo = 0;
	}
		
	public Fattura(String dataEmissione, double importo, int idTerz) {
		this.dataEmissione = dataEmissione;
		this.importo = importo;
		this.idTerz = idTerz;
	}
	public Fattura(int id, int numFattura, String dataEmissione, double importo) {
		this.id = id;
		this.numFattura = numFattura;
		this.dataEmissione = dataEmissione;
		this.importo = importo;
	}
	
	public Fattura(int id,int numFattura, String dataEmissione, double importo, List<Fattura_Lavorazione> fattLavorazione) {
		this.id = id;
		this.numFattura = numFattura;
		this.dataEmissione = dataEmissione;
		this.importo = importo;
		this.setFattLavorazione(fattLavorazione);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumFattura() {
		return numFattura;
	}

	public void setNumFattura(int numFattura) {
		this.numFattura = numFattura;
	}

	public String getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(String dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public List<Fattura_Lavorazione> getFattLavorazione() {
		return fattLavorazione;
	}

	public void setFattLavorazione(List<Fattura_Lavorazione> fattLavorazione2) {
		this.fattLavorazione = fattLavorazione2;
	}

	public int getIdTerz() {
		return idTerz;
	}

	public void setIdTerz(int idTerz) {
		this.idTerz = idTerz;
	}

	public int getIdBolla() {
		return idBolla;
	}

	public void setIdBolla(int idBolla) {
		this.idBolla = idBolla;
	}
}
