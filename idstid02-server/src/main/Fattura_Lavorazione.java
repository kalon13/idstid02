package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fattura_Lavorazione {
        private String nomeLavorazione;
        private double prezzoLavorazione;
        private int idFattura;
        private int idBolla;
        private int idTerzista;
        private boolean isFatt;
        private String CodBolla;
        
        public Fattura_Lavorazione() {
        }
       
        public Fattura_Lavorazione(String nomeLavorazione, double prezzoLavorazione) {
                this.nomeLavorazione = nomeLavorazione;
                this.prezzoLavorazione = prezzoLavorazione;
        }
       
        public Fattura_Lavorazione(int idFattura,String nomeLavorazione, double prezzoLavorazione,
                        int idBolla, int idTerzista) {
                this.idFattura = idFattura;
                this.nomeLavorazione = nomeLavorazione;
                this.prezzoLavorazione = prezzoLavorazione;
                this.idBolla = idBolla;
                this.idTerzista = idTerzista;
        }
       
        public String getNomeLavorazione() {
                return nomeLavorazione;
        }

        public void setNomeLavorazione(String nomeLavorazione) {
                this.nomeLavorazione = nomeLavorazione;
        }

        public double getPrezzoLavorazione() {
                return prezzoLavorazione;
        }

        public void setPrezzoLavorazione(double prezzoLavorazione) {
                this.prezzoLavorazione = prezzoLavorazione;
        }

        public int getIdBolla() {
                return idBolla;
        }

        public void setIdBolla(int idBolla) {
                this.idBolla = idBolla;
        }

        public int getIdTerzista() {
                return idTerzista;
        }

        public void setIdTerzista(int idTerzista) {
                this.idTerzista = idTerzista;
        }

        public int getIdFattura() {
                return idFattura;
        }

        public void setIdFattura(int idFattura) {
                this.idFattura = idFattura;
        }

		public boolean isFatt() {
			return isFatt;
		}

		public void setFatt(boolean isFatt) {
			this.isFatt = isFatt;
		}

		public String getCodBolla() {
			return CodBolla;
		}

		public void setCodBolla(String codBolla) {
			CodBolla = codBolla;
		}

}
