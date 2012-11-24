package main;


import javax.xml.bind.annotation.XmlRootElement;

import classResources.Um;

@XmlRootElement
public class Materiale {
	private int id;
	private String descrizione;
	private double costoUnitario;
	private String codice;
	private double quantita;
	private Um um;
	
	public Materiale() {
		this.id = -1;
		this.codice = "0";
		this.descrizione = "";
		this.costoUnitario = 0;
	}
	
	public Materiale(int id, String codice, String descrizione) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costoUnitario = 0;
		this.um = Um.UNIT;
		
	}

	public Materiale(int id, String codice, String descrizione, double costoUnitario) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costoUnitario = costoUnitario;
		this.um = Um.UNIT;
	}
	
	public Materiale(int id, String codice, String descrizione, double costoUnitario, Um um) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costoUnitario = costoUnitario;
		this.um = um;
	}
	
	public Materiale(int id, String codice, String descrizione, double costoUnitario,double quantita) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costoUnitario = costoUnitario;
		this.quantita = quantita;
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

	public double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
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
	
	@Override
	public String toString(){
		return id + " " + codice + " " + descrizione + " " + costoUnitario + um.toString();
	}
}
