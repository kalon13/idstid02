package classResources;

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
	
	public DDT(int id, int numDoc, String dataRicezione, String dataInvio,int idTerzista,
			boolean flussoAzienda) {
		this.id = id;
		this.numDoc = numDoc;
		this.dataRicezione = dataRicezione;
		this.dataInvio = dataInvio;
		this.idTerzista = idTerzista;
		this.flussoAzienda = flussoAzienda;
	}

	public DDT(int id, int numDoc, String dataRicezione, String dataInvio,int idTerzista,
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
}
