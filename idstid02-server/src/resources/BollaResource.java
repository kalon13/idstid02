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
import main.Bolla;

@Path("/bolla")
public class BollaResource {
	
	public BollaResource() {} // E' necessario anche se vuoto

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bolla> getListaBolla() {
		Statement statement = null;
		ResultSet result = null;
		List<Bolla> listaBolla = new ArrayList<Bolla>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.Bolla;"
					);
			
			while(result.next()) {
				Bolla m = new Bolla(result.getInt(1), result.getString(2), result.getInt(3),
											result.getString(4));
				listaBolla.add(m);
			}
			statement.close();
			
			return listaBolla;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bolla getBolla(@PathParam("id") int id) {
		Statement statement = null;
		ResultSet result = null;
		Bolla bolla = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.bolla WHERE id='" + id + "';"
					);
			
			while(result.next()) {
				bolla = new Bolla(result.getInt(1), result.getString(2), result.getInt(3),
						result.getString(4));
			}
			statement.close();
			
			return bolla;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Path ("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateBolla( @PathParam("id") int id,
								@DefaultValue("") @FormParam("descrizione") String descrizione,
								@DefaultValue("0") @FormParam("costoUnitario") double costoUnitario) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"UPDATE ProgIngSw.Bolla SET descrizione = '" + descrizione +"'," +
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
	public String deleteBolla( @PathParam("id") int id) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"DELETE FROM ProgIngSw.Bolla WHERE id='" + id + "';"
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
	public String insertBolla(  @FormParam("descrizione") String descrizione,
									@FormParam("costoUnitario") double costoUnitario) {
		
		Statement statement = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO ProgIngSw.Bolla(descrizione, costoUnitario) " +
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
