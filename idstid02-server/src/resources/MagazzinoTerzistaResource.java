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
		//TODO da aggiungere se si entra con un det profilo terzista: and terzista_id = "";
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT materiale.id, codiceArticolo, descrizione, costoUnitario, quantita, Terzista_id, " +
						"materialeterzista.id FROM progingsw.materialeterzista JOIN progingsw.materiale ON" +
						" materiale.id = materiale_id ;");
			//int id, String codice, String descrizione, double costoUnitario, int id_terzista
			while(result.next()) {
				Materiale m = new Materiale(result.getInt(1), result.getString(2),
											result.getString(3), result.getDouble(4), result.getDouble(5), result.getInt(6), result.getInt(7));
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
	@Path ("/search/{txtSearch}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Materiale> getMateriale1(@PathParam("txtSearch") String txtSearch) {
		Statement statement = null;
		ResultSet result = null;
		List<Materiale> listaMateriali = new ArrayList<Materiale>();
		//TODO da aggiungere se si entra con un det profilo terzista: and terzista_id = "";
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
					"SELECT materiale.id, codiceArticolo, descrizione, costoUnitario, quantita, Terzista_id," +
					" materialeterzista.id FROM progingsw.materialeterzista JOIN progingsw.materiale ON" +
					" materiale.id = Materiale_id WHERE materiale.descrizione LIKE '%" + txtSearch + "%';");
			 while(result.next()) {
				Materiale m = new Materiale(result.getInt(1), result.getString(2),
						result.getString(3), result.getDouble(4), result.getDouble(5), result.getInt(6), result.getInt(7));
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
	@Path ("/materiale/{idMateriale}")
	@Produces(MediaType.APPLICATION_JSON)
	public Materiale getMateriale1(@PathParam("idMateriale") int idMateriale) {
		Statement statement = null;
		ResultSet result = null;
		Materiale materiale = null;
		//TODO da aggiungere se si entra con un det profilo terzista: and terzista_id = "";
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
					"SELECT materiale.id, codiceArticolo, descrizione, costoUnitario, quantita, Terzista_id," +
					" materialeterzista.id FROM progingsw.materialeterzista JOIN progingsw.materiale ON" +
					" materiale.id = Materiale_id WHERE materiale.id='" + idMateriale + "';");
			if(result != null){
			 while(result.next()) {
				materiale = new Materiale(result.getInt(1), result.getString(2),
						result.getString(3), result.getDouble(4), result.getDouble(5), result.getInt(6), result.getInt(7));
			  }
			}
			else return null;
			statement.close();
			
			return materiale;
			
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
		//TODO da aggiungere se si entra con un det profilo terzista: and terzista_id = "";
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
					"SELECT materiale.id, codiceArticolo, descrizione, costoUnitario, quantita, Terzista_id," +
					" materialeterzista.id FROM progingsw.materialeterzista JOIN progingsw.materiale ON" +
					" materiale.id = Materiale_id WHERE materialeterzista.id='" + id + "';");
			
			while(result.next()) {
				materiale = new Materiale(result.getInt(1), result.getString(2),
						result.getString(3), result.getDouble(4), result.getDouble(5), result.getInt(6), result.getInt(7));
			}
			statement.close();
			
			return materiale;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("{idMat}/{terzista}")
	@Produces(MediaType.APPLICATION_JSON)
	public Materiale getMaterialeTerzista(@PathParam("idMat") int idMat,
										  @PathParam("terzista") int terzista) {
		Statement statement = null;
		ResultSet result = null;
		Materiale materiale = null;
		//TODO da aggiungere se si entra con un det profilo terzista: and terzista_id = "";
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
					"SELECT materiale.id, codiceArticolo, descrizione, costoUnitario, quantita, Terzista_id," +
					" materialeterzista.id FROM progingsw.materialeterzista JOIN progingsw.materiale ON" +
					" materiale.id = Materiale_id WHERE materialeterzista.materiale_id='" + idMat + "' " +
							"and materialeterzista.terzista_id='" + terzista + "';");
			
			while(result.next()) {
				materiale = new Materiale(result.getInt(1), result.getString(2),
						result.getString(3), result.getDouble(4), result.getDouble(5), result.getInt(6), result.getInt(7));
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
								@DefaultValue("0") @FormParam("quantita") double quantita) {
		
		Statement statement = null;
		int ok = -1;
		//TODO da aggiungere se si entra con un det profilo terzista: and terzista_id = "";
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"UPDATE progingsw.materialeterzista SET " +
					"quantita = " + quantita + " WHERE id='" + id + "';"
					);
			System.out.println(id+quantita);
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
					"DELETE FROM progingsw.materialeterzista WHERE id='" + id + "';"
					);
			statement.close();

			return String.valueOf(ok);
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	//registro il DDT inserendo i nuovi materiali in materiali dei terzisti	
		@PUT
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.APPLICATION_JSON)
		public String insertMateriale(  @FormParam("Terzista_id") int Terzista_id,
								  @FormParam("Materiale_id") int Materiale_id,
								  @FormParam("quantita") double quantita) {
			
			Statement statement = null;
			ResultSet result = null;
			int ok = -1;
			int id = -1;
			try {
				statement = DB.instance.createStatement();
				ok = statement.executeUpdate(
						"INSERT INTO progingsw.materialeterzista (quantita, Terzista_id, Materiale_id) " +
						"VALUES('" + quantita + "', '" + Terzista_id + "', '" + Materiale_id + "');", 
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
