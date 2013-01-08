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

import main.Lavorazione;

@Path("/lavorazione")
public class LavorazioneResource {
	
	public LavorazioneResource() {} // E' necessario anche se vuoto

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lavorazione> getLavorazione() {
		
		List<Lavorazione> lavorazione=new ArrayList<Lavorazione>();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery("SELECT * FROM progingsw.lavorazione WHERE id>0;");
			while(result.next()) {
				Lavorazione m = new Lavorazione(result.getString(2), result.getInt(1));
				lavorazione.add(m);
			}
			statement.close();
			return lavorazione;
		}
		catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	
	@GET
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lavorazione getLavorazione(@PathParam("id") int id) {
		Statement statement = null;
		ResultSet result = null;
		Lavorazione lavorazione = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.Lavorazione WHERE id='" + id + "';"
					);
			
			while(result.next()) {
				lavorazione = new Lavorazione(result.getString(2), result.getInt(1));
			}
			statement.close();
			
			return lavorazione;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
