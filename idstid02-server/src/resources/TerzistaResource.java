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
import main.Terzista;

@Path("/terzista/") //Gli altri senza la / finale
public class TerzistaResource {
	
	public TerzistaResource() {} // E' necessario anche se vuoto
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Terzista> getListaTerzista() {
		Statement statement = null;
		ResultSet result = null;
		List<Terzista> listaTerzista = new ArrayList<Terzista>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.terzista where id>0;"
					);
			
			while(result.next()) {
				Terzista m = new Terzista(result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4), result.getString(5),
						result.getString(6), result.getString(7), result.getString(8), 
						result.getString(9), result.getString(10), result.getInt(11));
				listaTerzista.add(m);
			}
			statement.close();
			
			return listaTerzista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Terzista getTerzista(@PathParam("id") int id) {
		Statement statement = null;
		ResultSet result = null;
		Terzista terzista = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.terzista WHERE id='" + id + "';"
					);
			
			while(result.next()) {
				terzista = new Terzista(result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4), result.getString(5),
						result.getString(6), result.getString(7), result.getString(8), 
						result.getString(9), result.getString(10));
			}
			statement.close();
			
			return terzista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("utenteId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Terzista getTerzistaUtente(@PathParam("id") int id) {
		Statement statement = null;
		ResultSet result = null;
		Terzista terzista = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.terzista WHERE Utente_id='" + id + "';"
					);
			while(result.next()) {
				terzista = new Terzista(result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4), result.getString(5),
						result.getString(6), result.getString(7), result.getString(8), 
						result.getString(9), result.getString(10));
			}
			statement.close();
			
			return terzista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@POST
	@Path ("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)	//Nei @FormParam ci vanno gli stessi nomi dei campi sul db
	public String updateTerzista(@PathParam("id") int id,
								@DefaultValue("_@_.it") @FormParam("email") String email,
								@DefaultValue("0000000000000000") @FormParam("pIva") String pIva,
								@DefaultValue("00000.srl") @FormParam("ragSociale") String ragSoc,
								@DefaultValue("") @FormParam("indirizzo") String indirizzo,
								@DefaultValue("00000") @FormParam("cap") String cap,
								@DefaultValue("") @FormParam("provincia") String provincia,
								@DefaultValue("") @FormParam("citta") String citta,
								@DefaultValue("") @FormParam("telefono") String telefono,
								@DefaultValue("") @FormParam("fax") String fax) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			
			//Per i numeri solo + num + ?
			ok = statement.executeUpdate("UPDATE progingsw.terzista SET email = '" + email + "', pIva = '" + pIva + "', ragSociale = '" + ragSoc + "', indirizzo = '" + indirizzo + "', cap = '" + cap + "', provincia = '" + provincia + "', citta = '" + citta + "', telefono = '" + telefono + "', fax = '" + fax + "' WHERE id='" + id + "';");
			
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
	public String deleteTerzista(@PathParam("id") int id) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"DELETE FROM progingsw.terzista WHERE id='" + id + "';"
					);
			statement.close();

			return String.valueOf(ok);
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
//	@PUT
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public String insertMateriale(  @FormParam("descrizione") String descrizione,
//									@FormParam("costoUnitario") double costoUnitario) {
//		
//		Statement statement = null;
//		ResultSet result = null;
//		int ok = -1;
//		int id = -1;
//		
//		try {
//			statement = DB.instance.createStatement();
//			ok = statement.executeUpdate(
//					"INSERT INTO progingsw.Materiale(descrizione, costoUnitario) " +
//					"VALUES('" + descrizione + "', '" + costoUnitario + "');", 
//					Statement.RETURN_GENERATED_KEYS);
//			
//			if(ok == 1) { // Inserimento ok
//				result = statement.getGeneratedKeys();
//		        if (result.next()){
//		        	id = result.getInt(1);
//		        }
//		        result.close();
//			}
//			statement.close();
//
//			return String.valueOf(id);
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return "-1";
//		}
//		
//		
//	}
	
}


//result.getInt(1), result.getString(10),
//result.getString(9), result.getString(8), result.getString(7),
//result.getString(6), result.getString(5), result.getString(4), 
//result.getString(3), result.getString(2));