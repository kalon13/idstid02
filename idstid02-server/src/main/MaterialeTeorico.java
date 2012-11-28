package main;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MaterialeTeorico {
	private int id;
	private double quantita;
    private int id_bolla;
    private int id_materiale;
    
    
	public MaterialeTeorico() {
		this.id = -1;
		this.quantita = 0;
	}

	public MaterialeTeorico(int id, double quantita, int id_bolla, int id_materiale) {
		this.id = id;
		this.quantita = quantita;
		this.id_bolla = id_bolla;
		this.id_materiale = id_materiale;
	}
	
	//aggiunto
	private String descrizione;
	private double costoUnitario;
	private String udm;
	public MaterialeTeorico(String descrizione, double costoUnitario, double quantita, int id_bolla, String udm) {
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.costoUnitario = costoUnitario;
		this.id_bolla = id_bolla;
		this.udm = udm;
	}

	public String getUdm() {
		return udm;
	}


	public void setUdm(String udm) {
		this.udm = udm;
	}


	//aggiunto
	public String getDescrizione() {
		return descrizione;
	}

	//aggiunto
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	//aggiunto
	public double getCostoUnitario() {
		return costoUnitario;
	}

	//aggiunto
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
