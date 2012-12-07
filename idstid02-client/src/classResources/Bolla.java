package classResources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bolla {
        private int id;
        private String codice;
        private String data;
        private int stato;
//        private Terzista terzista;
//        private Lavorazione lavorazione;
        private int valutata;
        private int terzistaId;
        private int lavorazioneTerzistaId;

        
        public Bolla() {

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

        public int getId() {
                return id;
        }
        
        public int getTerzistaId() {
            return terzistaId;
        }
        
        public int getLavorazioneTerzistaId() {
            return lavorazioneTerzistaId;
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

//        public Terzista getTerzista() {
//                return terzista;
//        }
//
//        public void setTerzista(Terzista terzista) {
//                this.terzista = terzista;
//        }
//
//        public Lavorazione getLavorazione() {
//                return lavorazione;
//        }
//
//        public void setLavorazione(Lavorazione lavorazione) {
//                this.lavorazione = lavorazione;
//        }

        public String getCodice() {
                return codice;
        }

        public void setCodice(String codice) {
                this.codice = codice;
        }
}
