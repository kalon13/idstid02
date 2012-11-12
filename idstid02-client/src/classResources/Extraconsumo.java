package classResources;

public class Extraconsumo {
	private int id;
	private double quantita;
	private boolean giustificato;
	private String dataRichiesta;
	
	public Extraconsumo(int id, double quantita, boolean giustificato,
			String dataRichiesta) {
		this.id = id;
		this.quantita = quantita;
		this.giustificato = giustificato;
		this.dataRichiesta = dataRichiesta;
	}
	
	public Extraconsumo() {
		this.id = -1;
		this.quantita = 0;
		this.giustificato = false;
		this.dataRichiesta = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public boolean isGiustificato() {
		return giustificato;
	}

	public void setGiustificato(boolean giustificato) {
		this.giustificato = giustificato;
	}

	public String getDataRichiesta() {
		return dataRichiesta;
	}

	public void setDataRichiesta(String dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}
}
