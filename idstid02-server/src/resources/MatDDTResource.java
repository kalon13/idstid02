package resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.DB;



@Path("/MaterialeDDT")
public class MatDDTResource {
	
	public MatDDTResource() {} 
	
	/***** Inserisco i materiale nel ddt e scalo dal magazzino terzista ****/
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertMatDDT( @FormParam("DDT_id") int DDT_id,
							  @FormParam("Materiale_id") int Materiale_id,
							  @FormParam("quantita") double quantita,
							  @FormParam("Terzista_id") int Terzista_id) {
		
		Statement statement = null;
		Statement updMagTer = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		
		try {
			statement = DB.instance.createStatement();
			updMagTer = DB.instance.createStatement();
			ok = updMagTer.executeUpdate("UPDATE progingsw.materialeterzista SET quantita = (quantita - " +quantita+") " +
					"WHERE Terzista_id ="+ Terzista_id +" and Materiale_id = "+Materiale_id);
			ok = statement.executeUpdate(
					"INSERT INTO progingsw.ddtmateriale(DDT_id, Materiale_id, quantita) " +
					"VALUES(" + DDT_id + ", " + Materiale_id + ", '" + quantita + "');", 
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
