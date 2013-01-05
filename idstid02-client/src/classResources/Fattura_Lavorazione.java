package classResources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fattura_Lavorazione {
        private String nomeLavorazione;
        private double prezzoLavorazione;
        private int idFattura;
        private int idBolla;
        private int idTerzista;
        private boolean isFatt;
        private String codBolla;
        private double costoUnit;
        private double qntProd;
        private double totImp2Bol;
        private String codProdotto;
        private String udm;
        private String descProdotto;

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
			return codBolla;
		}

		public void setCodBolla(String codBolla) {
			this.codBolla = codBolla;
		}

		public double getCostoUnit() {
			return costoUnit;
		}

		public void setCostoUnit(double costoUnit) {
			this.costoUnit = costoUnit;
		}

		public double getQntProd() {
			return qntProd;
		}

		public void setQntProd(double qntProd) {
			this.qntProd = qntProd;
		}

		public double getTotImp2Bol() {
			return totImp2Bol;
		}

		public void setTotImp2Bol(double totImp2Bol) {
			this.totImp2Bol = totImp2Bol;
		}

		public String getCodProdotto() {
			return codProdotto;
		}

		public void setCodProdotto(String codProdotto) {
			this.codProdotto = codProdotto;
		}

		public String getUdm() {
			return udm;
		}

		public void setUdm(String udm) {
			this.udm = udm;
		}

		public String getDescProdotto() {
			return descProdotto;
		}

		public void setDescProdotto(String descProdotto) {
			this.descProdotto = descProdotto;
		}

}
