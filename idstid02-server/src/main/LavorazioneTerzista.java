package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LavorazioneTerzista {

	        private int id;
	        private Double prezzo;
	        private Double qualita;
	        private float capacitaProduzione;
	        private int numVotazioni;
	        private int lavorazioneID;
	        private int terzistaID;
	       
	        public LavorazioneTerzista() {
	                	                
	        }
	       
	        public LavorazioneTerzista(int id, Double prezzo, Double qualita, float capProd, int numVotaz, int lavorazioneID, int terzistaID ) {
	                this.id = id;
	                this.prezzo=prezzo;
	                this.qualita=qualita;
	                this.capacitaProduzione=capProd;
	                this.numVotazioni=numVotaz;
	                this.lavorazioneID=lavorazioneID;
	                this.terzistaID=terzistaID;
	        }
	        
	        public LavorazioneTerzista(Double prezzo, Double qualita, float capProd, int numVotaz, int lavorazioneID, int terzistaID ) {
                this.prezzo=prezzo;
                this.qualita=qualita;
                this.capacitaProduzione=capProd;
                this.numVotazioni=numVotaz;
                this.lavorazioneID=lavorazioneID;
                this.terzistaID=terzistaID; 
	        }
	        
	        public LavorazioneTerzista(Double prezzo, float capProd, int lavorazioneID, int terzistaID) {
                this.prezzo=prezzo;
                this.capacitaProduzione=capProd;
                this.lavorazioneID=lavorazioneID;
                this.terzistaID=terzistaID; 
	        }
	       
	        public int getID() {
	                return id;
	        }

	        public Double getPrezzo() {
                return prezzo;
	        }
	        
	        public Double getQualita() {
                return qualita;
	        }
	        
	        public float getCapProd() {
                return capacitaProduzione;
	        }
	        
	        public int getNumVotaz() {
                return numVotazioni;
	        }
	        
	        public int getLavorazioneID() {
                return lavorazioneID;
	        }
	        
	        public int getTerzistaID() {
                return terzistaID;
	        }
	        
	        public void setID (int id) {
	                this.id = id;
	        }

	        public void setPrezzo(Double prezzo) {
                this.prezzo = prezzo;
	        }
	        
	        public void setQualita(Double qualita) {
                this.qualita = qualita;
	        }
	        
	        public void setCapProd(float capProd) {
                this.capacitaProduzione = capProd;
	        }
	        
	        public void setNumVotaz(int numVotaz) {
                this.numVotazioni=numVotaz;
	        }
	        
	        public void setLavorazioneID(int lavID) {
                this.lavorazioneID=lavID;
	        }
	        
	        public void setTerzistaID(int terzID) {
                this.terzistaID=terzID;
	        }

}
