package classResources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Messaggio {
        private int id;
        private String data;
        private String testo;
        private boolean letto;
        private String bolla_Id; //aggiunto
        //TODO: aggiungere utente_Id
        //TODO: aggiungere bolla_Id
       
        public Messaggio(int id, String data, String testo, boolean letto) {
                this.id = id;
                this.data = data;
                this.testo = testo;
                this.letto = letto;
        }
       
        //aggiunto
        public Messaggio(int id, String data, String testo, boolean letto, String bolla_Id) {
                this.id = id;
                this.data = data;
                this.testo = testo;
                this.letto = letto;
        }
        //aggiunto
        public String getBolla_Id() {
                return bolla_Id;
        }
        //aggiunto
        public void setBolla_Id(String bolla_Id) {
                this.bolla_Id = bolla_Id;
        }
       
        public Messaggio() {
                this.id = -1;
                this.data = "";
                this.testo = "";
                this.letto = false;
        }

        public int getId() {
                return id;
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

        public String getTesto() {
                return testo;
        }

        public void setTesto(String testo) {
                this.testo = testo;
        }

        public boolean isLetto() {
                return letto;
        }

        public void setLetto(boolean letto) {
                this.letto = letto;
        }
        
        @Override
        public boolean equals(Object messaggio) {
        	try {
        		Messaggio m = (Messaggio) messaggio;
        		if(this.getId() == m.getId()) {
        			return true;
        		}
        	}
        	catch(Exception e) {
        		return false;
        	}
        	return false;
        }
}

