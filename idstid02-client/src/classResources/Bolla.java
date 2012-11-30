package classResources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bolla {
        private int id;
        private String codice;
        private String data;
        private int stato;
        private Terzista terzista;
        private Lavorazione lavorazione;
        //TODO: aggiungere terzista_Id
        //TODO: aggiungere lavorazione_Id
       
        public Bolla(int id, String codice, int stato, String data) {
                this.id = id;
                this.codice =codice;
                this.data = data;
                this.stato = stato;
        }
       
        public Bolla() {
                this.id = -1;
                this.data = "";
                this.stato = 0;
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

        public int getStato() {
                return stato;
        }

        public void setStato(int stato) {
                this.stato = stato;
        }

        public Terzista getTerzista() {
                return terzista;
        }

        public void setTerzista(Terzista terzista) {
                this.terzista = terzista;
        }

        public Lavorazione getLavorazione() {
                return lavorazione;
        }

        public void setLavorazione(Lavorazione lavorazione) {
                this.lavorazione = lavorazione;
        }

        public String getCodice() {
                return codice;
        }

        public void setCodice(String codice) {
                this.codice = codice;
        }
}
