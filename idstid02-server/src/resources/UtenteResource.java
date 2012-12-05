package resources;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import utils.DB;
import main.Utente;

@Path("/utente")
public class UtenteResource {
	
	public UtenteResource() {} // E' necessario anche se vuoto

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Utente> getListaBolla() {
		Statement statement = null;
		ResultSet result = null;
		List<Utente> listaUtente = new ArrayList<Utente>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.Utente;"
					);
			
			while(result.next()) {
				Utente m = new Utente(result.getInt(1), result.getString(2), result.getString(3),
											result.getInt(4));
				listaUtente.add(m);
			}
			statement.close();
			
			return listaUtente;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Utente getUtente(@PathParam("id") int id) {
		Statement statement = null;
		ResultSet result = null;
		Utente utente = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.utente WHERE id='" + id + "';"
					);
			
			while(result.next()) {
				utente = new Utente(result.getInt(1), result.getString(2), result.getString(3),
						result.getInt(4));
			}
			statement.close();
			
			return utente;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Path ("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUtente( @PathParam("id") int id,
								@FormParam("username") String username,
								@FormParam("password") String password,
								@FormParam("tipo") int tipo) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"UPDATE ProgIngSw.Utente SET username = '" + username +"'," +
					"password = " + password + ", tipo='" + tipo + "' WHERE id='" + id + "';"
					);
			statement.close();

			return String.valueOf(ok);
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	@DELETE
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUtente( @PathParam("id") int id) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"DELETE FROM ProgIngSw.Utente WHERE id='" + id + "';"
					);
			statement.close();

			return String.valueOf(ok);
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUtente( @FormParam("username") String username,
								@FormParam("password") String password,
								@FormParam("tipo") int tipo) {
		
		Statement statement = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO ProgIngSw.Utente(username, password, tipo) " +
					"VALUES('" + username + "', '" + password + "', '" + tipo + "');", 
					Statement.RETURN_GENERATED_KEYS);
			
			if(ok == 1) { // Inserimento ok
				result = statement.getGeneratedKeys();
		        if (result.next()){
		        	id = result.getInt(1);
		        }
		        result.close();
			}
			statement.close();

			return String.valueOf(id);
		
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
		
		
	}
	
}
