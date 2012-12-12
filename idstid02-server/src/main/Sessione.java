package main;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sessione {
        private Utente utente;
        private Terzista terzista;
        private String sessionID;
        private Date timestamp;
       
        public Sessione() {
                utente = null;
                terzista = null;
                sessionID = "";
        }
       
        public Sessione(Utente utente, Date timestamp, String sessionid) {
                this.utente = utente;
                this.timestamp = timestamp;
                this.sessionID = sessionid;
        }
       
        public Utente getUtente() {
                return utente;
        }
        public void setUtente(Utente utente) {
                this.utente = utente;
        }
        public String getSessionID() {
                return sessionID;
        }
        public void setSessionID(String sessionID) {
                this.sessionID = sessionID;
        }

        public Date getTimestamp() {
                return timestamp;
        }

        public void setTimestamp(Date timestamp) {
                this.timestamp = timestamp;
        }

		public Terzista getTerzista() {
			return terzista;
		}

		public void setTerzista(Terzista terzista) {
			this.terzista = terzista;
		}
}
