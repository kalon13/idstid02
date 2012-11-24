package main;

public class Autenticazione {
	private static Sessione sessione;

	public Autenticazione() {
		sessione = null;
	}
	
	public static Sessione getSessione() {
		return sessione;
	}

	public static void setSessione(Sessione session) {
		sessione = session;
	}
	
}
