package resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import main.Lavorazione;
import main.LavorazioneTerzista;
import main.Terzista;
import utils.DB;

@Path("/lavorazioneterzista/")
public class LavorazioneTerzistaResource {
	
	public LavorazioneTerzistaResource() {}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LavorazioneTerzista> getLavorazione() {
		
		List<LavorazioneTerzista> lavorazione=new ArrayList<LavorazioneTerzista>();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery("SELECT * FROM ProgIngSw.lavorazioneterzista;");
			while(result.next()) {
				LavorazioneTerzista m = new LavorazioneTerzista(result.getInt(1), result.getDouble(2), result.getDouble(3), 
						result.getFloat(4), result.getInt(5), result.getInt(6), result.getInt(7));
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
	@Path ("{lavorazioneid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LavorazioneTerzista> getLavorazioneID
	(@PathParam("lavorazioneid") int id) {
		Statement statement = null;
		ResultSet result = null;
		List<LavorazioneTerzista> lavTerzista = new ArrayList<LavorazioneTerzista>();
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.lavorazioneterzista WHERE Lavorazione_id='" + id + "';"
					);
			
			while(result.next()) {
				LavorazioneTerzista m = new LavorazioneTerzista(result.getInt(1), result.getDouble(2), result.getDouble(3), 
						result.getFloat(4), result.getInt(5), result.getInt(6), result.getInt(7));
				lavTerzista.add(m);
			}
			statement.close();
			
			return lavTerzista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("idTerzista/{terzistaid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LavorazioneTerzista> getTerzistaID
	(@PathParam("terzistaid") int id) {
		Statement statement = null;
		ResultSet result = null;
		List<LavorazioneTerzista> lavTerzista = new ArrayList<LavorazioneTerzista>();
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM ProgIngSw.lavorazioneterzista WHERE Terzista_id='" + id + "';"
					);
			
			while(result.next()) {
				LavorazioneTerzista m = new LavorazioneTerzista(result.getInt(1), result.getDouble(2), result.getDouble(3), 
						result.getFloat(4), result.getInt(5), result.getInt(6), result.getInt(7));
				lavTerzista.add(m);
			}
			statement.close();
			
			return lavTerzista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertLavorazione(@DefaultValue("0") @FormParam("prezzo") double prezzo,
			@DefaultValue("0") @FormParam("qualita") double qualita,
			@DefaultValue("0") @FormParam("capacitaProduzione") float capacitaProduzione,
			@DefaultValue("0") @FormParam("numeroVotazioni") int numeroVotazioni,
			@DefaultValue("-1") @FormParam("Lavorazione_id") int Lavorazione_id,
			@DefaultValue("-1") @FormParam("Terzista_id") int Terzista_id) {
		
		Statement statement = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		float quality=(float) qualita;
//		DecimalFormat dec=new DecimalFormat();
//		dec.setMaximumFractionDigits(2);
//		dec.setMaximumIntegerDigits(10);
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO ProgIngSw.lavorazioneterzista (prezzo,qualita,capacitaProduzione,numeroVotazioni,Lavorazione_id,Terzista_id) " +
					"VALUES ('" + prezzo + "', '" + quality + "', '" + capacitaProduzione + "', '" + numeroVotazioni + "', '" + Lavorazione_id + "', '" + Terzista_id + "');", 
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
    @Produces(MediaType.APPLICATION_JSON)
    public String updateLavorazTerz(@PathParam("id") int id,
                                @FormParam("prezzo") double prezzo,
                                @FormParam("capacitaProduzione") float capacitaProduzione,
                                @FormParam("Lavorazione_id") int lavorazId,
                                @FormParam("Terzista_id") int terzistaId) {
           
            Statement statement = null;
            int ok = -1;
           
            try {
                    statement = DB.instance.createStatement();
                    ok = statement.executeUpdate(
                                    "UPDATE progingsw.lavorazioneterzista SET prezzo ='" + prezzo + "', capacitaProduzione ='" + capacitaProduzione + "' WHERE id='" + id + "';");
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
	public String deleteLavorazione( @PathParam("id") int id) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"DELETE FROM progingsw.lavorazioneterzista WHERE id='" + id + "';");
			statement.close();

			return String.valueOf(ok);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
}
