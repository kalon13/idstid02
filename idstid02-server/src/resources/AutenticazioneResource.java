package resources;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.DB;
import main.Autenticazione;
import main.Sessione;
import main.Terzista;
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
		Sessione sessione = new Sessione();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.utente WHERE user='" + username + "' AND psw='" + password + "' LIMIT 1;"
			);
			
			while(result.next()) {
				utente = new Utente(result.getInt(1), result.getString(2), 
						result.getString(3),result.getInt(4));
			}
			statement.close();
			if(utente != null) {
				sessione = Autenticazione.generateSession(utente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sessione;
	}
	
	@POST
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(@FormParam("sid") String sid) {
		Autenticazione.destroySession(sid);
		return "OK";
	}
	

}
