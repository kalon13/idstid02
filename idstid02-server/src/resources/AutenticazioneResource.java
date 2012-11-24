package resources;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import utils.DB;
import main.Autenticazione;
import main.Sessione;
import main.Utente;

@Path("/autenticazione")
public class AutenticazioneResource {
	
	public AutenticazioneResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String ciao() {
		return "ciao";
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Sessione login(@FormParam("username") String username,
							 @FormParam("password") String password) {
		Statement statement = null;
		ResultSet result = null;
		Utente utente = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.utente WHERE username='" + username + "' AND password='" + password + "';"
			);
			
			while(result.next()) {
				utente = new Utente(result.getInt(1), result.getString(2), 
						result.getString(3),result.getInt(4));
			}
			statement.close();
			return Autenticazione.generateSession(utente);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(Sessione sessione) {
		Autenticazione.destroySession(sessione);
		return "OK";
	}
	

}
