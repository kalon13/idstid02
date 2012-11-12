package main;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DDT {
	private int id;
	private int numDoc;
	private String dataRicezione;
	private String dataInvio;
	private boolean flussoAzienda;
	
	public DDT(int id, int numDoc, String dataRicezione, String dataInvio,
			boolean flussoAzienda) {
		this.id = id;
		this.numDoc = numDoc;
		this.dataRicezione = dataRicezione;
		this.dataInvio = dataInvio;
		this.flussoAzienda = flussoAzienda;
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
}
