package utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.org.apache.bcel.internal.generic.ICONST;

import main.*;
import utils.*;

public class Notification {
	private static boolean instanced = false;
	
	private static boolean STOP = false;
	
	public static void start(Sessione sessione){
		if(instanced == false) {
			new Thread(new SearchNewMessage(sessione)).start();
			instanced = true;
		}
	}
	
	public static void stop() {
		STOP = true;
	}
	
	/**
	 * Cerca nuove notifiche.
	 * Se l'utente è un OPERATORE:
	 *  - Cerca nuove fatture
	 *  - Cerca nuove DDT
	 *  - Cerca nuovi messaggi di una bolla
	 *  
	 * Se l'utente è un TERZISTA:
	 *  - Cerca nuove bolle assegnate
	 *  - Cerca nuove DDT
	 *  - Cerca nuovi messaggi di una bolla
	 * 
	 * @param Sessione sessione dell'utente
	 */
	private static class SearchNewMessage implements Runnable {
		
		private Sessione sessione = null;
		private Terzista terzista = null;
		private List<Bolla> bolleAssegnate = new ArrayList<Bolla>();
		private List<Messaggio> messaggiNonLetti = new ArrayList<Messaggio>();
		private List<DDT> ddtArrivate = new ArrayList<DDT>();
		private List<Fattura> nuoveFatture = new ArrayList<Fattura>();
		
		
		public SearchNewMessage(Sessione sessione) {
			this.sessione = sessione;
			if(sessione.getTipoUtente() == 5) {
				terzista = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+
						Autenticazione.getSessione().getUtente().getUserId());
			}
		}
		
		/**
		 * Controlla se nelle nuove notifiche ci sono notifiche non lette
		 * @param vecchia lista
		 * @param nuova lista
		 * @return il numero di nuovi elementi
		 */
		private <T> int checkNew(List<T> vecchia, List<T> nuova) {
			int result = 0;
        	for(T t1 : nuova) {
        		boolean trovato = false;
        		for(T t2 : vecchia) {
        			if(t1.equals(t2)){
        				trovato = true;
        			}
        		}
        		if(!trovato) {
        			result++;
        		}
        	}
        	return result;
		}
		
		private <T> List<T> getNewResource(final Class<T> clazz, String url, MultivaluedMap<String, String> param) {
            ParameterizedType genericType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                        return new Type[] {clazz};
                }
                @Override
    			public Type getOwnerType() {
    				return List.class;
    			}
    			@Override
    			public Type getRawType() {
    				return List.class;
    			}
            };
			GenericType<List<T>> type = new GenericType<List< T >>(genericType) {};
			List<T> result = ResourceClass
         		   .getService()
         		   .path(url).path("notification")
         		   .accept(MediaType.APPLICATION_JSON)
         		   .post(type, param);
			
			return result;
		}
		
		@Override
		public void run() {
			while(!STOP) {
				int cntBolle = 0;
				int cntMessaggi = 0;
				int cntDDT = 0;
				int cntFatture = 0;
				
				MultivaluedMap<String, String> param = new MultivaluedMapImpl();
                param.add("sid", sessione.getSessionID());
				if(terzista != null) { /////////// TERZISTA
					param.add("terzistaid", String.valueOf(terzista.getId()));
					
					// Ottieni nuove bolle
	                List<Bolla> bolle = getNewResource(Bolla.class, Global._URLBolla, param);
                	cntBolle = checkNew(bolleAssegnate, bolle);
                	bolleAssegnate = bolle;
	                
	                // Ottieni nuove ddt
	                List<DDT> ddt = getNewResource(DDT.class, Global._URLddt, param);
                	cntDDT = checkNew(ddtArrivate, ddt);
                	ddtArrivate = ddt;
	                
	                // Ottieni nuovi messaggi
	                List<Messaggio> messaggi = getNewResource(Messaggio.class, Global._URLMess, param);
                	cntMessaggi = checkNew(messaggiNonLetti, messaggi);
                	messaggiNonLetti = messaggi;
	                
				}
				else { /////////// OPERATORE
					param.add("terzistaid", "0");
					
	                // Ottieni nuove ddt
	                List<DDT> ddt = getNewResource(DDT.class, Global._URLddt, param);
                	cntDDT = checkNew(ddtArrivate, ddt);
                	ddtArrivate = ddt;
                	
	                // Ottieni nuove fatture
	                List<Fattura> fatture = getNewResource(Fattura.class, Global._URLFatt, param);
                	cntFatture = checkNew(nuoveFatture, fatture);
                	nuoveFatture = fatture;
					
	                // Ottieni nuovi messaggi
	                List<Messaggio> messaggi = getNewResource(Messaggio.class, Global._URLMess, param);
                	cntMessaggi = checkNew(messaggiNonLetti, messaggi);
                	messaggiNonLetti = messaggi;
				}
				
				if( (cntBolle > 0) | (cntDDT > 0) | (cntFatture > 0) | (cntMessaggi > 0)) {
					String messaggio = "Notifiche non lette:\n";
					if (cntBolle>0){
						messaggio += "-> " + cntBolle + " nuove bolle assegnate!\n";
					}
					if (cntDDT>0){
						messaggio += "-> " + cntDDT + " DDT in arrivo!\n";
					}
					if (cntFatture>0){
						messaggio += "-> " + cntFatture + " nuove fatture!\n";
					}
					if (cntMessaggi>0){
						messaggio += "-> " + cntMessaggi + " messaggi!\n";
					}
							
					JOptionPane.showMessageDialog(null, messaggio, "Notifiche", JOptionPane.INFORMATION_MESSAGE);
				}
				
				// Sveglia ogni
				try { Thread.sleep(1000*10);}
				catch (InterruptedException e) {e.printStackTrace();}
			}
		}
		
	}
}
