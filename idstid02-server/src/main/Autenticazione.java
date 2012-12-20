package main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.security.MessageDigest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Autenticazione {
	private static List<Sessione> sessioni = new ArrayList<Sessione>();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static boolean isValid(String sid) {
		for(Sessione s: sessioni) {
			if(s.getSessionID().equals(sid)) {
				return true;
			}
		}
		return false;
	}
	
	public static Sessione generateSession(Utente utente) {
		double random = Math.random();
		Date timestamp = new Date();
		
		String togenerate = utente.getUser() + utente.getTipo() + df.format(timestamp) + String.valueOf(random);
		
		String sessionid = getMD5Sum(togenerate);
		
		Sessione s = new Sessione(utente, timestamp, sessionid);

		sessioni.add(s);
		return s;
	}
	
	public static void destroySession(String sid) {
		Sessione toremove = null;
		for(Sessione s: sessioni) {
			if(s.getSessionID().equals(sid)) {
				toremove = s;
				break;
			}
		}
		if(toremove != null) {
			sessioni.remove(toremove);
		}
	}
	
	public static String getMD5Sum(String password) {
		try {
			MessageDigest md  = MessageDigest.getInstance("MD5");
			byte[] message = md.digest(password.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < message.length; i++) {
			    sb.append(toHexString(message[i]));
			}
			return sb.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getMD5Sum(char[] password) {
		String psw = new String(password);
		return getMD5Sum(psw);
	}
	
	private static String toHexString(byte b) {
		String s = Integer.toHexString(0xff & b);
		if (s.length() < 2) {
			s = "0" + s;
		}
		return s;
	}
}
