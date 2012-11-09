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

import main.Materiale;

@Path("/magazzinoterzista")
public class MagazzinoTerzistaResource {
	
	public MagazzinoTerzistaResource() {} // E' necessario anche se vuoto

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Materiale> getListaMateriali() {
		Statement statement = null;
		ResultSet result = null;
		List<Materiale> listaMateriali = new ArrayList<Materiale>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.Materiale;"
					);
			
			while(result.next()) {
				Materiale m = new Materiale(result.getInt(1), result.getString(2),
											result.getString(3), result.getDouble(4));
				listaMateriali.add(m);
			}
			statement.close();
			
			return listaMateriali;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Materiale getMateriale(@PathParam("id") int id) {
		Statement statement = null;
		ResultSet result = null;
		Materiale materiale = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.Materiale WHERE id='" + id + "';"
					);
			
			while(result.next()) {
				materiale = new Materiale(result.getInt(1), result.getString(2),
											result.getString(3), result.getDouble(4));
			}
			statement.close();
			
			return materiale;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Path ("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateMateriale( @PathParam("id") int id,
								@DefaultValue("") @FormParam("descrizione") String descrizione,
								@DefaultValue("0") @FormParam("costoUnitario") double costoUnitario) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"UPDATE ProgIngSw.Materiale SET descrizione = '" + descrizione +"'," +
					"costoUnitario = " + costoUnitario + " WHERE id='" + id + "';"
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
	public String deleteMateriale( @PathParam("id") int id) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"DELETE FROM ProgIngSw.Materiale WHERE id='" + id + "';"
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
	public String insertMateriale(  @FormParam("descrizione") String descrizione,
									@FormParam("costoUnitario") double costoUnitario) {
		
		Statement statement = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO ProgIngSw.Materiale(descrizione, costoUnitario) " +
					"VALUES('" + descrizione + "', '" + costoUnitario + "');", 
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
