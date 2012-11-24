package main;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Autenticazione {
	private static List<Sessione> sessioni;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static boolean isValid(Sessione sessione) {
		for(Sessione s: sessioni) {
			if(s.getSessionID().equals(sessione.getSessionID())) {
				return true;
			}
		}
		return false;
	}
	
	public static Sessione generateSession(Utente utente) {
		try {
			double random = Math.random();
			Date timestamp = new Date();
			
			String togenerate = utente.getUser() + utente.getTipo() + df.format(timestamp) + String.valueOf(random);
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			String sessionid = md.digest(togenerate.getBytes()).toString();
			
			Sessione s = new Sessione(utente, timestamp, sessionid);
			sessioni.add(s);
			return s;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void destroySession(Sessione sessione) {
		Sessione toremove = null;
		for(Sessione s: sessioni) {
			if(s.getSessionID().equals(sessione.getSessionID())) {
				toremove = s;
				break;
			}
		}
		if(toremove != null) {
			sessioni.remove(toremove);
		}
	}
}
