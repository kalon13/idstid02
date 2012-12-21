package classResources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Paia {
	private int id;
	private int paia36;
	private int paia37;
	private int paia38;
	private int paia39;
	private int paia40;
	private int paia41;
	private int paia42;
	private int idMatDaProd;
	private String descrizione;
	private int quantita;
	
    public Paia() {
    }

	public Paia(int id, int idMatDaProd, String descrizione, int paia36, int paia37, int paia38, int paia39, int paia40, int paia41, int paia42, int quantita) {
		this.id = id;
		this.idMatDaProd = idMatDaProd;
		this.descrizione = descrizione;
		this.paia36 = paia36;
		this.paia37 = paia37;
		this.paia38 = paia38;
		this.paia39 = paia39;
		this.paia40 = paia40;
		this.paia41 = paia41;
		this.paia42 = paia42;
		this.quantita = quantita;
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

	public int getPaia36() {
		return paia36;
	}

	public void setPaia36(int paia36) {
		this.paia36 = paia36;
	}

	public int getPaia37() {
		return paia37;
	}

	public void setPaia37(int paia37) {
		this.paia37 = paia37;
	}

	public int getPaia38() {
		return paia38;
	}

	public void setPaia38(int paia38) {
		this.paia38 = paia38;
	}

	public int getPaia39() {
		return paia39;
	}

	public void setPaia39(int paia39) {
		this.paia39 = paia39;
	}

	public int getPaia40() {
		return paia40;
	}

	public void setPaia40(int paia40) {
		this.paia40 = paia40;
	}

	public int getPaia41() {
		return paia41;
	}

	public void setPaia41(int paia41) {
		this.paia41 = paia41;
	}

	public int getPaia42() {
		return paia42;
	}

	public void setPaia42(int paia42) {
		this.paia42 = paia42;
	}

	public int getIdMatDaProd() {
		return idMatDaProd;
	}

	public void setIdMatDaProd(int idMatDaProd) {
		this.idMatDaProd = idMatDaProd;
	}
}