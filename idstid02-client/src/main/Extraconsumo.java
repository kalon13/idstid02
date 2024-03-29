package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Extraconsumo {
        private int id;
        private double quantita;
        private int giustificato;
        private String dataRichiesta;
        private int idMatTeo;
       
        public int getIdMatTeo() {
			return idMatTeo;
		}

		public void setIdMatTeo(int idMatTeo) {
			this.idMatTeo = idMatTeo;
		}

		public Extraconsumo(int id, double quantita, int giustificato, String dataRichiesta) {
                this.id = id;
                this.quantita = quantita;
                this.giustificato = giustificato;
                this.dataRichiesta = dataRichiesta;
        }
       
        public Extraconsumo() {
                this.id = -1;
                this.quantita = 0;
                this.giustificato = 0;
                this.dataRichiesta = "";
        }
       
        //aggiunto
        private String codiceArticolo;
        private String descrizione;
        private double qtaAttuale;
        private String udm;
        private double costo;
        //1-extraconsumo.id, 2-codiceArticolo, 3-descrizione, 4-materialiteorici.quantita AS QtaAttuale
        //5-extraconsumo.quantita AS QtaRichiesta, 6-udm, 7-giustificato, 8-dataRichiesta
        public Extraconsumo(int id, String codiceArticolo, String descrizione, double qtaAttuale, double quantita, String udm, int giustificato, String dataRichiesta, double costo) {
                this.id = id;
                this.quantita = quantita;
                this.giustificato = giustificato;
                this.dataRichiesta = dataRichiesta;
                this.codiceArticolo = codiceArticolo;
                this.descrizione = descrizione;
                this.qtaAttuale = qtaAttuale;
                this.udm = udm;
                this.setCosto(costo);
        }

        public String getCodiceArticolo() {
                return codiceArticolo;
        }

        public void setCodiceArticolo(String codiceArticolo) {
                this.codiceArticolo = codiceArticolo;
        }

        public String getDescrizione() {
                return descrizione;
        }

        public void setDescrizione(String descrizione) {
                this.descrizione = descrizione;
        }

        public double getQtaAttuale() {
                return qtaAttuale;
        }

        public void setQtaAttuale(double qtaAttuale) {
                this.qtaAttuale = qtaAttuale;
        }

        public String getUdm() {
                return udm;
        }

        public void setUdm(String udm) {
                this.udm = udm;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public double getQuantita() {
                return quantita;
        }

        public void setQuantita(double quantita) {
                this.quantita = quantita;
        }

        public int getGiustificato() {
                return giustificato;
        }

        public void setGiustificato(int giustificato) {
                this.giustificato = giustificato;
        }

        public String getDataRichiesta() {
                return dataRichiesta;
        }

        public void setDataRichiesta(String dataRichiesta) {
                this.dataRichiesta = dataRichiesta;
        }

        public double getCosto() {
                return costo;
        }

        public void setCosto(double costo) {
                this.costo = costo;
        }
}

