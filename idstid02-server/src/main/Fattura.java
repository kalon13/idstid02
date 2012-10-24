package main;

public class Fattura {
	private int id;
	private int numFattura;
	private String dataEmissione;
	private double importo;
	//TODO: aggiungere terzista_Utente_Id
	//TODO: aggiungere terzista_Id
	
	public Fattura(int id, int numFattura, String dataEmissione, double importo) {
		this.id = id;
		this.numFattura = numFattura;
		this.dataEmissione = dataEmissione;
		this.importo = importo;
	}
	
	public Fattura() {
		this.id = -1;
		this.numFattura = -1;
		this.dataEmissione = "";
		this.importo = 0;
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
}
