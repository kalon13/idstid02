package main;

public class Bolla {
	private int id;
	private String data;
	private int stato;
	//TODO: aggiungere terzista_Id
	//TODO: aggiungere lavorazione_Id
	
	public Bolla(int id, String data, int stato) {
		this.id = id;
		this.data = data;
		this.stato = stato;
	}
	
	public Bolla() {
		this.id = -1;
		this.data = "";
		this.stato = 0;
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

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}
}
