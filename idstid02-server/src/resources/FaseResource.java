package resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;

import main.Fase;
import main.LavorazioneTerzista;
import utils.DB;

@Path("/fase")
public class FaseResource {
	
	public FaseResource() {}
	
	@GET
	@Path ("{lavorazioneid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fase> getFase(
			@PathParam("lavorazioneid") int id) {
		Statement statement = null;
		ResultSet result = null;
		List<Fase> fase = new ArrayList<Fase>();
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.fase WHERE LavorazioneTerzista_id='" + id + "';"
					);
			
			while(result.next()) {
				Fase m = new Fase(result.getInt(1), result.getString(2), result.getInt(3), 
						result.getInt(4));
				fase.add(m);
			}
			statement.close();
			
			return fase;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertFase(@DefaultValue("___") @FormParam("nome") String nome,
			@DefaultValue("0") @FormParam("ordine") int ordine,
			@DefaultValue("-1") @FormParam("LavorazioneTerzista_id") int LavorazioneTerzista_id) {
		
		Statement statement = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		short order;
		
		try {
			//Visto che nel DB il campo e' Smallint
			order=(short) ordine;
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO progingsw.fase (nome, ordine, LavorazioneTerzista_id) VALUES ('" + nome + "', '" + order + "', '" + LavorazioneTerzista_id + "');", 
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
	
	@POST
	@Path ("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)	//Nei @FormParam ci vanno gli stessi nomi dei campi sul db
	public String updateFase(@PathParam("id") int id,
								@DefaultValue("___") @FormParam("nome") String nome,
								@DefaultValue("0") @FormParam("ordine") int ordine)
								{
		
		Statement statement = null;
		int ok = -1;
		short order=(short) ordine;
		
		try {
			statement = DB.instance.createStatement();
			
			ok = statement.executeUpdate("UPDATE progingsw.fase SET nome = '" + nome + "', ordine = '" + order + "' WHERE id='" + id + "';");
			
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
	public String deleteFase( @PathParam("id") int id) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"DELETE FROM progingsw.fase WHERE id='" + id + "';"
					);
			statement.close();

			return String.valueOf(ok);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	@DELETE
	@Path ("/search/{lavorazTerzista_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteFase1( @PathParam("lavorazTerzista_id") int lavorazTerzista_id) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"DELETE FROM progingsw.fase WHERE LavorazioneTerzista_id='" + lavorazTerzista_id + "';"
					);
			statement.close();

			return String.valueOf(ok);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
}
