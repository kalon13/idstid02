package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Paia {
	private int id;
	private int idMatDaProd;
	private String descrizione;
	private int quantita;
	private int nScarpa;
	private int paia;
	
    public Paia() {
    }

    public Paia(int id, int idMatDaProd, String descrizione, int nScarpa, int paia) {
		this.id = id;
		this.idMatDaProd = idMatDaProd;
		this.descrizione = descrizione;
		this.paia = paia;
		this.nScarpa = nScarpa;
	}

	public int getPaia() {
		return paia;
	}

	public void setPaia(int paia) {
		this.paia = paia;
	}

	public int getnScarpa() {
		return nScarpa;
	}

	public void setnScarpa(int nScarpa) {
		this.nScarpa = nScarpa;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMatDaProd() {
		return idMatDaProd;
	}

	public void setIdMatDaProd(int idMatDaProd) {
		this.idMatDaProd = idMatDaProd;
	}
}