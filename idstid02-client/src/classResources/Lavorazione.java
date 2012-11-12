package classResources;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Lavorazione {
	private String nome;
	private int id;
	
	public Lavorazione(String nome, int id) {
		this.nome = nome;
		this.id = id;
	}
	
	public Lavorazione() {
		this.nome = "";
		this.id = -1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
