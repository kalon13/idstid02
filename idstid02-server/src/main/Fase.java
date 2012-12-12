package main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fase {
        private int id;
        private String nome;
        private int ordine; //in che ordine vanno eseguite le fasi!
        private int lavTerzId;
       
        public Fase(int id, String nome, int ordine, int lavTerzId) {
                this.id = id;
                this.nome = nome;
                this.ordine = ordine;
                this.lavTerzId = lavTerzId;
        }
        
        public Fase(String nome, int ordine, int lavTerzId) {
            this.nome = nome;
            this.ordine = ordine;
            this.lavTerzId = lavTerzId;
        }
        
        public Fase(String nome, int ordine) {
            this.nome = nome;
            this.ordine = ordine;
        }
       
        public Fase() {

        }

        public int getLavTerzId(){
        	return lavTerzId;
        }
        
        public void setLavTerzId(int lavTerzId){
        	this.lavTerzId = lavTerzId;
        }
        
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public int getOrdine() {
                return ordine;
        }

        public void setOrdine(int ordine) {
                this.ordine = ordine;
        }
}
