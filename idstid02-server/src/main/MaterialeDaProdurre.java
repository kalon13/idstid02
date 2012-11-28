package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MaterialeDaProdurre {
	private int id;
	private double quantita;
	private double quantitaProdotta;
	private double quantitaSpedita;
	private int numeroMorti;
    private int id_bolla;
    private int id_materiale;
    
	public MaterialeDaProdurre() {
		this.id = -1;
		this.id_bolla = -1;
		this.id_materiale = -1;
		this.quantita = 0;
		this.quantitaProdotta = 0;
		this.quantitaSpedita = 0;
		this.numeroMorti = 0;
	}
	
	public MaterialeDaProdurre(int id, double quantita, int numeroMorti, double quantitaProdotta, double quantitaSpedita, int id_bolla, int id_materiale) {
		this.id = id;
		this.quantita = quantita;
		this.quantitaProdotta = quantitaProdotta;
		this.quantitaSpedita = quantitaSpedita;
		this.id_bolla = id_bolla;
		this.id_materiale = id_materiale;
		this.numeroMorti = numeroMorti;
	}
	
	public MaterialeDaProdurre(int id, double quantita, int numeroMorti, double quantitaProdotta, double quantitaSpedita) {
		this.id = id;
		this.quantita = quantita;
		this.quantitaProdotta = quantitaProdotta;
		this.quantitaSpedita = quantitaSpedita;
		this.numeroMorti = numeroMorti;
	}
	
	private String descrizione;
	private double costoUnitario;
	private String udm;
	//1-quantita 2-numeromorti 3-quantitaprodotta 4-quantitaspedita 5-descrizione 6-costoUnitario
	public MaterialeDaProdurre(int id, double quantita, int numeroMorti, double quantitaProdotta, double quantitaSpedita, String descrizione, double costoUnitario, String udm) {
		this.id = id;
		this.quantita = quantita;
		this.quantitaProdotta = quantitaProdotta;
		this.quantitaSpedita = quantitaSpedita;
		this.numeroMorti = numeroMorti;
		this.descrizione = descrizione;
		this.costoUnitario = costoUnitario;
		this.udm = udm;
	}
	
	public String getUdm() {
		return udm;
	}

	public void setUdm(String udm) {
		this.udm = udm;
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
	public double getQuantitaProdotta() {
		return quantitaProdotta;
	}
	public void setQuantitaProdotta(double quantitaProdotta) {
		this.quantitaProdotta = quantitaProdotta;
	}
	public double getQuantitaSpedita() {
		return quantitaSpedita;
	}
	public void setQuantitaSpedita(double quantitaSpedita) {
		this.quantitaSpedita = quantitaSpedita;
	}
	public int getNumeroMorti() {
		return numeroMorti;
	}
	public void setNumeroMorti(int numeroMorti) {
		this.numeroMorti = numeroMorti;
	}
	public int getId_bolla() {
		return id_bolla;
	}
	public void setId_bolla(int id_bolla) {
		this.id_bolla = id_bolla;
	}
	public int getId_materiale() {
		return id_materiale;
	}
	public void setId_materiale(int id_materiale) {
		this.id_materiale = id_materiale;
	}
}
