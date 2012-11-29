package main;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Terzista extends Utente{
	private int id;
	private String fax;
	private String telefono;
	private String citta;
	private String provincia;
	private String cap;
	private String indirizzo;
	private String ragioneSociale;
	private String pIva;
	private String email;
	
	public Terzista() {
		this.id = -1;
		this.fax = "";
		this.telefono = "";
		this.citta = "";
		this.provincia = "";
		this.cap = "";
		this.indirizzo = "";
		this.ragioneSociale = "";
		this.pIva = "";
		this.email = "";
	}
	
	public Terzista(int id, String fax, String tel, String citta, String prov, String cap, String ind, String rs, String pi, String email) {
		this.id = id;
		this.fax = fax;
		this.telefono = tel;
		this.citta = citta;
		this.provincia = prov;
		this.cap = cap;
		this.indirizzo = ind;
		this.ragioneSociale = rs;
		this.pIva = pi;
		this.email = email;
	}
	
	public Terzista(int id, String rs, String pi) {
		this.id = id;
		this.ragioneSociale = rs;
		this.pIva = pi;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
