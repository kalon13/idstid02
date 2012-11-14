package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Materiale {
	private int id;
	private String descrizione;
	private double costoUnitario;
	private String codice;
	private double quantita;
	private Um um;
    private int id_terzista;
    private int id_matTerz;
    
	public Materiale() {
		this.id = -1;
		this.codice = "0";
		this.descrizione = "";
		this.costoUnitario = 0;
	}

	//Materiale Terzista
	public Materiale(int id, double quantita, int id_terzista) {
		this.id = id;
		this.quantita = quantita;
		this.id_terzista = id_terzista;
	}
	//Materiale Terzista
	public Materiale(int id_matTerz, double quantita) {
		this.setId_matTerz(id_matTerz);
		this.quantita = quantita;
	}

	public Materiale(int id, String codice, String descrizione) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costoUnitario = 0;
		this.um = Um.UNIT;
		
	}

	public Materiale(int id, String codice, String descrizione, double costoUnitario, double quantita, int id_terzista, int id_matTerz) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costoUnitario = costoUnitario;
		this.quantita = quantita;
		this.id_terzista = id_terzista;
		this.setId_matTerz(id_matTerz);
	}
	
	public Materiale(int id, String codice, String descrizione, double costoUnitario, Um um, int id_terzista) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costoUnitario = costoUnitario;
		this.um = um;
		this.id_terzista = id_terzista;
	}
	
	public Materiale(int id, String codice, String descrizione, double costoUnitario,double quantita) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costoUnitario = costoUnitario;
		this.setQuantita(quantita);
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
	public int getId_terzista() {
		return id_terzista;
	}

	public void setId_terzista(int id_terzista) {
		this.id_terzista = id_terzista;
	}


	@Override
	public String toString(){
		return id + " " + codice + " " + descrizione + " " + costoUnitario + " " + quantita + " "+ id_terzista + um.toString();
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public int getId_matTerz() {
		return id_matTerz;
	}

	public void setId_matTerz(int id_matTerz) {
		this.id_matTerz = id_matTerz;
	}
}
