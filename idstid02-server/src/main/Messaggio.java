package main;

public class Messaggio {
	private int id;
	private String data;
	private String testo;
	private boolean letto;
	//TODO: aggiungere utente_Id
	//TODO: aggiungere bolla_Id
	
	public Messaggio(int id, String data, String testo, boolean letto) {
		this.id = id;
		this.data = data;
		this.testo = testo;
		this.letto = letto;
	}
	
	public Messaggio() {
		this.id = -1;
		this.data = "";
		this.testo = "";
		this.letto = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public boolean isLetto() {
		return letto;
	}

	public void setLetto(boolean letto) {
		this.letto = letto;
	}
}
