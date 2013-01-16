package main;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DDT {
	private int id;
	private int numDoc;
	private String dataRicezione;
	private String dataInvio;
	private int idTerzista;
	private boolean flussoAzienda;
	private List<Materiale> ddtMateriale;
	private boolean registrato;
	
	public DDT(int id, int numDoc, String  dataInvio, String dataRicezione,int idTerzista,
			boolean flussoAzienda, boolean registrato) {
		this.id = id;
		this.numDoc = numDoc;
		this.dataRicezione = dataRicezione;
		this.dataInvio = dataInvio;
		this.idTerzista = idTerzista;
		this.flussoAzienda = flussoAzienda;
		this.registrato = registrato;
		this.setDdtMateriale(ddtMateriale);
	}
	
	public DDT(String dataInvio, int idTerzista,
			boolean flussoAzienda, boolean registrato) {
		this.dataInvio = dataInvio;
		this.idTerzista = idTerzista;
		this.flussoAzienda = flussoAzienda;
		this.registrato = registrato;
	}
	
	public DDT(int id, int numDoc, String dataInvio, String dataRicezione,int idTerzista,
			boolean flussoAzienda, List<Materiale> ddtMateriale) {
		this.id = id;
		this.numDoc = numDoc;
		this.dataRicezione = dataRicezione;
		this.dataInvio = dataInvio;
		this.idTerzista = idTerzista;
		this.flussoAzienda = flussoAzienda;
		this.setDdtMateriale(ddtMateriale);
	}
	public DDT() {
		this.id = -1;
		this.numDoc = 0;
		this.dataRicezione = "";
		this.dataInvio = "";
		this.flussoAzienda = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(int numDoc) {
		this.numDoc = numDoc;
	}

	public String getDataRicezione() {
		return dataRicezione;
	}

	public void setDataRicezione(String dataRicezione) {
		this.dataRicezione = dataRicezione;
	}

	public String getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(String dataInvio) {
		this.dataInvio = dataInvio;
	}

	public boolean isFlussoAzienda() {
		return flussoAzienda;
	}

	public void setFlussoAzienda(boolean flussoAzienda) {
		this.flussoAzienda = flussoAzienda;
	}

	public int getIdTerzista() {
		return idTerzista;
	}

	public void setIdTerzista(int idTerzista) {
		this.idTerzista = idTerzista;
	}

	public List<Materiale> getDdtMateriale() {
		return ddtMateriale;
	}

	public void setDdtMateriale(List<Materiale> ddtMateriale) {
		this.ddtMateriale = ddtMateriale;
	}

	public boolean isRegistrato() {
		return registrato;
	}

	public void setRegistrato(boolean registrato) {
		this.registrato = registrato;
	}
	
    @Override
    public boolean equals(Object ddt) {
    	try {
    		DDT d = (DDT) ddt;
    		if(this.getId() == d.getId()) {
    			return true;
    		}
    	}
    	catch(Exception e) {
    		return false;
    	}
    	return false;
    }
}
