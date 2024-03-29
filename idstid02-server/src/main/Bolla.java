package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bolla {
	private int id;
	private String codice;
	private String data;
	private String url;
	private int stato;
	private int valutata;
	private int terzistaId;
	private int lavorazioneTerzistaId;
	private String nomeLavorazione; //Giorgia
	private String ragSociale; //Giorgia

	public Bolla() {

	}

    public Bolla(int id, String codice, int stato, String data, String nomeLavorazione) {
        this.id = id;
        this.codice =codice;
        this.data = data;
        this.stato = stato;
        this.nomeLavorazione = nomeLavorazione;
    }
    
    //Aggiunto Marco, per l'immagine
    public Bolla(int id, String url) {
        this.id = id;
        this.url = url;
    }
    
    public Bolla(int id, String codice, int stato, String data, int terzista_id, int lavorazione_id, String nomeLavorazione, String ragSociale) {
        this.id = id;
        this.codice =codice;
        this.data = data;
        this.stato = stato;
        this.nomeLavorazione = nomeLavorazione;
        this.terzistaId = terzista_id;
        this.lavorazioneTerzistaId = lavorazione_id;
        this.ragSociale = ragSociale;
    }
    
    public String getImageUrl() {
        return url;
    }
    
    public String getNomeLavorazione() {
        return nomeLavorazione;
    }

    public void setImageUrl(String url) {
        this.url = url;
    }
    
    public void setNomeLavorazione(String nomeLavorazione) {
        this.nomeLavorazione = nomeLavorazione;
    }

    public String getRagSociale() {
        return ragSociale;
    }

    public void setRagSociale(String ragSociale) {
        this.ragSociale = ragSociale;
	}

	public Bolla(int id, String codice, int stato, String data) {
		this.id = id;
		this.codice =codice;
		this.data = data;
		this.stato = stato;
	}

	public Bolla(int id, String codice, String data, int terzId, int lavorazTerzId) {
		this.id = id;
		this.codice =codice;
		this.data = data;
		this.terzistaId = terzId;
		this.lavorazioneTerzistaId = lavorazTerzId;
	}

	public Bolla(int id, String codice, int stato, String data, int valutata, int terzId, int lavorazTerzId) {
		this.id = id;
		this.codice =codice;
		this.stato=stato;
		this.data = data;
		this.valutata = valutata;
		this.terzistaId = terzId;
		this.lavorazioneTerzistaId = lavorazTerzId;
	}
	
	//Aggiunto Marco
	public Bolla(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getTerzistaId() {
		return terzistaId;
	}
	
	public void setTerzistaId(int terzistaId){
		this.terzistaId = terzistaId;
	}

	public int getLavorazioneTerzistaId() {
		return lavorazioneTerzistaId;
	}
	
	public void setLavorazioneTerzistaId(int lavorazioneTerzistaId){
		this.lavorazioneTerzistaId = lavorazioneTerzistaId;
	}

	public int getValutata() {
		return valutata;
	}

	public void setValutata(int valutata){
		this.valutata = valutata;
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

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public boolean equals(Object bolla) {
		try {
			Bolla b = (Bolla) bolla;
			if(this.getId() == b.getId())
				return true;
		}
		catch(Exception e) {
			return false;
		}
		return false;
	}
}