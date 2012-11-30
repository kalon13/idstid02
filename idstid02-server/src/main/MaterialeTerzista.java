
package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MaterialeTerzista {
        private int id;
        private int id_materiale;
        private double quantita;
        private int id_terzista;
       
        public MaterialeTerzista() {
        }
       
        //Materiale Terzista
        public MaterialeTerzista(int id,int id_materiale, double quantita, int id_terzista) {
                this.id = id;
                this.id_materiale = id_materiale;
                this.quantita = quantita;
                this.id_terzista = id_terzista;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        @Override
        public String toString(){
                return id + " " + id_materiale + " " + quantita + " " + id_terzista;
        }

        public double getQuantita() {
                return quantita;
        }

        public void setQuantita(double quantita) {
                this.quantita = quantita;
        }

        public int getTerzista_id() {
                return id_terzista;
        }

        public void setTerzista_id(int terzista_id) {
                this.id_terzista = terzista_id;
        }

        public int getId_materiale() {
                return id_materiale;
        }

        public void setId_materiale(int id_materiale) {
                this.id_materiale = id_materiale;
        }
}
