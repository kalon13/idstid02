package resources;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import main.DDT;
import main.Fattura;
import main.Fattura_Lavorazione;
import main.Materiale;
import main.Um;

@Path("/DDT")
public class DDTResource {
	
	public DDTResource() {} // E' necessario anche se vuoto

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DDT> getListaDDT() {
		Statement statement = null;
		ResultSet result = null;
		List<DDT> listaDDT = new ArrayList<DDT>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.ddt where registrato <> 1 and flussoAzienda = 1 ORDER BY dataInvio;"
					);
			
			while(result.next()) {
				//int id, int numDoc, String dataRicezione, String dataInvio,boolean flussoAzienda
				DDT ddt = new DDT(result.getInt(1), result.getInt(2), result.getString(3),
											result.getString(4),result.getInt(5), result.getBoolean(6), result.getBoolean(7));
				listaDDT.add(ddt);
			}
			statement.close();
			
			return listaDDT;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("/Terzista/{idTerz}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DDT> getListaDDTTerz(@PathParam("idTerz") int idTerz) {
		Statement statement = null;
		ResultSet result = null;
		List<DDT> listaDDT = new ArrayList<DDT>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.ddt where Terzista_id = "+idTerz+" ORDER BY dataInvio;"
					);
			
			while(result.next()) {
				//int id, int numDoc, String dataRicezione, String dataInvio,boolean flussoAzienda
				DDT ddt = new DDT(result.getInt(1), result.getInt(2), result.getString(3),
											result.getString(4),result.getInt(5), result.getBoolean(6), result.getBoolean(7));
				listaDDT.add(ddt);
			}
			statement.close();
			
			return listaDDT;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	 }
	
	@GET
	@Path ("/DDTnnReg/{idTerz}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DDT> getListaDDTNNReg(@PathParam("idTerz") int idTerz) {
		Statement statement = null;
		ResultSet result = null;
		List<DDT> listaDDT = new ArrayList<DDT>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
					"SELECT * FROM progingsw.ddt where registrato <> 1 and flussoAzienda = 1 and Terzista_id = "+idTerz+" ORDER BY dataInvio DESC;"
					);
			
			while(result.next()) {
				//int id, int numDoc, String dataRicezione, String dataInvio,boolean flussoAzienda
				DDT ddt = new DDT(result.getInt(1), result.getInt(2), result.getString(3),
											result.getString(4),result.getInt(5), result.getBoolean(6), result.getBoolean(7));
				listaDDT.add(ddt);
			}
			statement.close();
			
			return listaDDT;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	 }
	@GET
	@Path ("/allDDT")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DDT> getListaDDTAll() {
		Statement statement = null;
		ResultSet result = null;
		List<DDT> listaDDT = new ArrayList<DDT>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.ddt where registrato <> 1 ORDER BY dataInvio;"
					);
			
			while(result.next()) {
				//int id, int numDoc, String dataRicezione, String dataInvio,boolean flussoAzienda
				DDT ddt = new DDT(result.getInt(1), result.getInt(2), result.getString(3),
											result.getString(4),result.getInt(5), result.getBoolean(6), result.getBoolean(7));
				listaDDT.add(ddt);
			}
			statement.close();
			
			return listaDDT;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DDT getFattura(@PathParam("id") int id) {
		Statement statement = null;
		Statement statement1 = null;
		ResultSet result = null;
		ResultSet result1 = null;
		DDT ddt = null;
		Materiale matDDT = null;
		List<Materiale> lsMatDDT = new ArrayList<Materiale>();
		
		
		try {
			statement = DB.instance.createStatement();
			statement1 = DB.instance.createStatement();
			result = statement.executeQuery(
					"select * from progingsw.ddt where id='" + id + "';");
		
			result1 = statement1.executeQuery(
						"SELECT materiale.id, codiceArticolo, descrizione, costoUnitario," +
						" quantita, udm FROM progingsw.materiale join (progingsw.ddt join progingsw.ddtmateriale" +
						" on ddt.id = DDT_id) on  Materiale_id = materiale.id where DDT_id ='" + id + "';"
					);
			
			while(result1.next()) {
				//int id, String codice, String descrizione, double costoUnitario,double quantita, String um
				matDDT = new Materiale(result1.getInt(1), result1.getString(2), 
						result1.getString(3),result1.getDouble(4),result1.getDouble(5), result1.getString(6));
				lsMatDDT.add(matDDT);
			}
			while(result.next()) {
				 ddt = new DDT(result.getInt(1), result.getInt(2), result.getString(3),
							result.getString(4),result.getInt(5), result.getBoolean(6), lsMatDDT);
			
		    }
			statement.close();
			statement1.close();
			
			return ddt;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//registro il DDT aggiornando le qnt dei materiali dei terzisti
	@POST
	@Path ("{idDDT}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateDDT( @PathParam("idDDT") int idDDT,
								@DefaultValue("1") @FormParam("registrato") String registrato) {		
		Statement statement = null;
		int ok = -1;
		Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String todayDt = sdf.format(today);
        System.out.print(todayDt);		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"UPDATE progingsw.ddt SET dataRicezione = '" + todayDt + "',  registrato = 1 WHERE id='" + idDDT + "';"
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
	public String insertDDT(  @FormParam("Terzista_id") int Terzista_id,
							  @FormParam("dataInvio") String dataInvio,
							  @FormParam("flussoAzienda") Boolean flussoAzienda,
							  @FormParam("registrato") Boolean registrato) {
		
		Statement statement = null;
		ResultSet result = null;
		int ok = -1;
		int id = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO progingsw.DDT(Terzista_id, dataInvio, flussoAzienda, registrato) " +
					"VALUES('" + Terzista_id + "', '" + dataInvio + "', '" + 0 + "', '" + 0+ "');", 
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
