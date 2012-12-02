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

import main.Fattura;
import main.Fattura_Lavorazione;

@Path("/fatturazione")
public class FatturazioneResource {
	
	public FatturazioneResource() {} // E' necessario anche se vuoto

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fattura> getListaFattura() {
		Statement statement = null;
		ResultSet result = null;
		List<Fattura> listaFattura = new ArrayList<Fattura>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.fattura;"
					);
			
			while(result.next()) {
				Fattura f = new Fattura(result.getInt(1), result.getInt(2),
											result.getString(3), result.getDouble(4));
				listaFattura.add(f);
			}
			statement.close();
			
			return listaFattura;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//1^ tabella 
	@GET
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Fattura getFattura(@PathParam("id") int id) {
		Statement statement = null;
		Statement statement1 = null;
		ResultSet result = null;
		ResultSet result1 = null;
		Fattura f = null;
		Fattura_Lavorazione fattLavorazione = null;
		List<Fattura_Lavorazione> lsFattLav = new ArrayList<Fattura_Lavorazione>();
		
		
		try {
			statement = DB.instance.createStatement();
			statement1 = DB.instance.createStatement();
			//fattura
			result = statement.executeQuery(
					"select * from progingsw.fattura where id='" + id + "';");
		
			//Bolla Fattura
			result1 = statement1.executeQuery(
						"select fatturabolla.Fattura_id, nome, fatturabolla.importo, fatturabolla.Bolla_id, lavorazioneterzista.Terzista_id from progingsw.lavorazione JOIN " +
						"(progingsw.lavorazioneterzista JOIN(progingsw.fatturabolla join" +
						" progingsw.bolla on bolla.id = Bolla_id) ON lavorazioneterzista.Terzista_id" +
						" = bolla.Terzista_id) ON lavorazione.id = bolla.Lavorazione_id where " +
						"bolla.Lavorazione_id = lavorazioneterzista.Lavorazione_id " +
						"and fatturabolla.Fattura_id='" + id + "';"
					);
			
			while(result1.next()) {
				//int id, int numFattura, String dataEmissione, double importo
				fattLavorazione = new Fattura_Lavorazione(result1.getInt(1), result1.getString(2), 
						result1.getDouble(3),result1.getInt(4),result1.getInt(5));
				lsFattLav.add(fattLavorazione);
			}
			while(result.next()) {
				 f = new Fattura(result.getInt(1), result.getInt(2),
						result.getString(3), result.getDouble(4), lsFattLav);
			
		    }
			statement.close();
			statement1.close();
			
			return f;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DELETE
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteFattura( @PathParam("id") int id) {
		
		Statement statement = null;
		Statement statement2 = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			statement2 = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"DELETE FROM ProgIngSw.fattura WHERE id='" + id + "';"
					);
			ok = statement2.executeUpdate(
					"DELETE FROM ProgIngSw.fatturabolla WHERE Fattura_id='" + id + "';"
					);
			statement.close();
			statement2.close();
			
			return String.valueOf(ok);
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertFattura(  @FormParam("dataEmissione") String dataEmissione,
									@FormParam("importo") double importo,
									@FormParam("Terzista_id") int Terzista_id){
		
		Statement statement = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO ProgIngSw.fattura(dataEmissione, importo, Terzista_id) " +
					"VALUES('" + dataEmissione + "', '" + importo + "', '" + Terzista_id + "');", 
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
	@PUT
	@Path("/Bolla")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertFattura(  @FormParam("idBolla") int idBolla,
									@FormParam("idFatt") int idFatt) {
		
		Statement statement = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO ProgIngSw.fatturaBolla(Bolla_id, Fattura_id) " +
					"VALUES('" + idBolla + "', '" + idFatt + "');", 
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
