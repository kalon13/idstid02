package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Consumo {
	private int id;
	private int prodotto;
	private int matPrima;
	private double quantita;
	private String udm;
	private int idTerzista;
	
	public Consumo(){}
	
	public Consumo(int id, int prodotto, int  matPrima, Double quantita) {
		this.id = id;
		this.prodotto = prodotto;
		this.matPrima = matPrima;
		this.quantita = quantita;
	}
	
	public Consumo(int id, int prodotto, int  matPrima, Double quantita, int idTerzista) {
		this.id = id;
		this.prodotto = prodotto;
		this.matPrima = matPrima;
		this.quantita = quantita;
		this.idTerzista = idTerzista;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProdotto() {
		return prodotto;
	}

	public void setProdotto(int prodotto) {
		this.prodotto = prodotto;
	}

	public int getMatPrima() {
		return matPrima;
	}

	public void setMatPrima(int matPrima) {
		this.matPrima = matPrima;
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public String getUdm() {
		return udm;
	}

	public void setUdm(String udm) {
		this.udm = udm;
	}

	public int getIdTerzista() {
		return idTerzista;
	}

	public void setIdTerzista(int idTerzista) {
		this.idTerzista = idTerzista;
	}

}
