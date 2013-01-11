package classResources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MaterialeDDT {
        private int id;
        private int id_materiale;
        private double quantita;
        private int id_DDT;
        private int idTerzista;
       
        public MaterialeDDT() {
        }
       
        public MaterialeDDT(int id_DDT, int id_materiale, double quantita, int idTerzista) {
            this.id_DDT = id_DDT;
            this.id_materiale = id_materiale;
            this.quantita = quantita;
            this.setIdTerzista(idTerzista);
    }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        @Override
        public String toString(){
                return id + " " + id_materiale + " " + quantita + " " + id_DDT;
        }

        public double getQuantita() {
                return quantita;
        }

        public void setQuantita(double quantita) {
                this.quantita = quantita;
        }

        public int getid_DDT() {
                return id_DDT;
        }

        public void setid_DDT(int id_DDT) {
                this.id_DDT = id_DDT;
        }

        public int getId_materiale() {
                return id_materiale;
        }

        public void setId_materiale(int id_materiale) {
                this.id_materiale = id_materiale;
        }

		public int getIdTerzista() {
			return idTerzista;
		}

		public void setIdTerzista(int idTerzista) {
			this.idTerzista = idTerzista;
		}
}
