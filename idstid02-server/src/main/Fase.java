package main;

public class Fase {
	private int id;
	private String nome;
	private int ordine; //in che ordine vanno eseguite le fasi!
	//TODO: aggiungere lavorazioneTerzista_Id
	
	public Fase(int id, String nome, int ordine) {
		this.id = id;
		this.nome = nome;
		this.ordine = ordine;
	}
	
	public Fase() {
		this.id = -1;
		this.nome = "";
		this.ordine = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getOrdine() {
		return ordine;
	}

	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}
}
