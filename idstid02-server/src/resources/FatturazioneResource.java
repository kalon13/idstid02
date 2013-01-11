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

import com.sun.tools.xjc.generator.bean.ImplStructureStrategy.Result;

import utils.DB;

import main.Autenticazione;
import main.Fattura;
import main.Fattura_Lavorazione;
import main.Messaggio;
import main.Sessione;

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
						"SELECT * FROM progingsw.fattura;"
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
		Statement stQntPr = null;
		ResultSet result = null;
		ResultSet result1 = null;
		ResultSet rsQntPr = null;
		Fattura f = null;
		Double qntProd = 0.0;
		Fattura_Lavorazione fattLavorazione = null;
		List<Fattura_Lavorazione> lsFattLav = new ArrayList<Fattura_Lavorazione>();
		
		try {
			statement = DB.instance.createStatement();
			statement1 = DB.instance.createStatement();
			stQntPr = DB.instance.createStatement();
			//fattura
			result = statement.executeQuery(
					"select * from progingsw.fattura where id='" + id + "';");
			
			//Bolla Fattura
			result1 = statement1.executeQuery(
						"SELECT fatturabolla.Fattura_id, nome, fatturabolla.importo, fatturabolla.Bolla_id, lavorazioneterzista.Terzista_id, bolla.Numero, lavorazioneterzista.prezzo FROM progingsw.lavorazione JOIN " +
						" (progingsw.lavorazioneterzista JOIN (progingsw.fatturabolla JOIN" +
						" progingsw.bolla on bolla.id = Bolla_id) ON lavorazioneterzista.Terzista_id" +
						" = bolla.LavorazioneTerzista_id) ON lavorazione.id = bolla.Lavorazione_id where" +
						" bolla.Lavorazione_id = lavorazioneterzista.Lavorazione_id " +
						" and fatturabolla.Fattura_id='" + id + "';"
					);
				
			while(result1.next()) {
				//int id, int numFattura, String dataEmissione, double importo
				fattLavorazione = new Fattura_Lavorazione(result1.getInt(1), result1.getString(2), 
						result1.getDouble(3),result1.getInt(4),result1.getInt(5));
				fattLavorazione.setCodBolla(result1.getString(6));
				fattLavorazione.setCostoUnit(result1.getDouble(7));
				
				//Calcolo della quantita prodotta tramite l'id bolla 
				qntProd = 0.0;
				rsQntPr = stQntPr.executeQuery(
						"SELECT codiceArticolo, quantitaProdotta FROM progingsw.bolla join progingsw.materialidaprodurre"+ 
						" on bolla.id = Bolla_id join progingsw.materiale on materiale.id = Materiale_id where bolla.id = '"+result1.getInt(4)+"';"
					);
			 while(rsQntPr.next()) {
				fattLavorazione.setCodProdotto(rsQntPr.getString(1));
				fattLavorazione.setQntProd(rsQntPr.getDouble(2));
			  }
			 lsFattLav.add(fattLavorazione);
			}
			while(result.next()) {
				if(lsFattLav.isEmpty()) lsFattLav = null;
				 f = new Fattura(result.getInt(1), result.getInt(2),
						result.getString(3), result.getDouble(4), lsFattLav);
			
		    }
			statement.close();
			statement1.close();
			stQntPr.close();
			
			return f;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("/terzista/{idTerz}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fattura> getListaFatturaTerz(@PathParam("idTerz") int idTerz){
		Statement statement = null;
		ResultSet result = null;
		List<Fattura> listaFattura = new ArrayList<Fattura>();
		
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT * FROM progingsw.fattura where Terzista_id = '"+idTerz+"';"
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
	

	@GET
	@Path ("/chkFattBol/{idBolla}")
	@Produces(MediaType.APPLICATION_JSON)
	public Fattura_Lavorazione getFattBoll(@PathParam("idBolla") int idBolla){
		Statement statement = null;
		ResultSet result = null;
		Statement statementSt = null;
		ResultSet resultSt = null;
		int stato = 0;
		Fattura_Lavorazione chkBolFat = new Fattura_Lavorazione();
		chkBolFat.setFatt(false);
		chkBolFat.setIdBolla(idBolla);
		
		try { System.out.print(idBolla);
				statement = DB.instance.createStatement();
				result = statement.executeQuery(
							"SELECT * FROM progingsw.fatturabolla where Bolla_id = '"+idBolla+"' LIMIT 1;"
						);
				result.last();
				int numberRow = result.getRow();
				statement.close();
				if(numberRow != 0) {
					//Vedo se la bolla � chiusa
//					statementSt = DB.instance.createStatement();
//					resultSt = statementSt.executeQuery(
//								"SELECT stato FROM progingsw.bolla where id = '"+idBolla+"';"
//							);
//					while(resultSt.next()) {
//						stato = resultSt.getInt(0);
//					}
//					if(stato == 3)
					chkBolFat.setFatt(true);
					System.out.print("ciao2");
			}
//			statementSt.close();
			
			
			} catch (SQLException e) {
						e.printStackTrace();
					}
		return chkBolFat;
	}
	
	@GET
	@Path ("/ImpFattBol/{idBolla}")
	@Produces(MediaType.APPLICATION_JSON)
	public Fattura_Lavorazione getfatturaTerzBol(@PathParam("idBolla") int idBolla)
	{
		Statement stPrezzo = null;
		Statement stQntPr = null;
		ResultSet rsPrezzo = null;
		ResultSet rsQntPr = null;
		double importo = 0.0;
		double prezzoLav=0.0;
		double qntProd = 0.0;
		Fattura_Lavorazione  fattLav = new Fattura_Lavorazione();
		
		try {
			stPrezzo = DB.instance.createStatement();
			stQntPr = DB.instance.createStatement();
			rsPrezzo = stPrezzo.executeQuery(
						"SELECT prezzo FROM progingsw.bolla join progingsw.lavorazioneterzista" +
						" on LavorazioneTerzista_id = bolla.Terzista_id where bolla.id = '"+idBolla+"';"
					);
			
			while(rsPrezzo.next()) {
				prezzoLav = rsPrezzo.getDouble(1);
			}
			
			rsQntPr = stQntPr.executeQuery(
					"SELECT quantitaProdotta FROM progingsw.bolla join progingsw.materialidaprodurre"+ 
					" on bolla.id = Bolla_id where bolla.id = '"+idBolla+"';"
				);
			
			while(rsQntPr.next()) {
				qntProd += rsQntPr.getDouble(1);				
			}
			
			importo += qntProd * prezzoLav;
			
			stPrezzo.close();
			stQntPr.close();

			fattLav.setTotImp2Bol(importo);
			fattLav.setIdBolla(idBolla);
			fattLav.setQntProd(qntProd);
			fattLav.setCostoUnit(prezzoLav);
			return fattLav;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertFattura(@FormParam("dataEmissione") String dataEmissione,
			    				 @FormParam("importo") double importo,
								 @DefaultValue("-1") @FormParam("Terzista_id") int Terzista_id)  {
		Statement statement = null;
		ResultSet result = null;
		
		int ok = -1;
		int id = -1;
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO progingsw.fattura(dataEmissione, importo, Terzista_id) " +
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
	@Path("/BollaFatt")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertFatturaBol(@DefaultValue("0") @FormParam("Fattura_id") int fattura_id,
								 	@DefaultValue("0.0") @FormParam("importo") double importo,
								 	@DefaultValue("0") @FormParam("Bolla_id") int bolla_id)  
	{
		Statement statement = null;
		ResultSet result = null;
		
		int ok = -1;
		int id = -1;
		try {
			System.out.print( fattura_id + "', '" + importo + "', '" + bolla_id);
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"INSERT INTO progingsw.fatturabolla(Fattura_id, importo, Bolla_id) " +
					"VALUES('" + fattura_id + "', '" + importo + "', '" + bolla_id + "');", 
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
	@Path ("/notification")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fattura> updateFattura( @PathParam("sid") String sid) {
		
		Statement statement = null;
        ResultSet result = null;
        List<Fattura> listaFatture = new ArrayList<Fattura>();
    	Sessione s = Autenticazione.getSession(sid);
    	
    	if(s != null) {
            try {
            	statement = DB.instance.createStatement();
	                
	                if(s.getUtente().getTipo() < 5) {
	                
	                	result = statement.executeQuery(
	                			"SELECT * FROM progingsw.fattura;");
	                }
	                
	                if(result != null){
						while(result.next()) {
							Fattura fattura = new Fattura(result.getInt(1), result.getInt(2),
									result.getString(3), result.getDouble(4));
							listaFatture.add(fattura);
						}
	                }
	                statement.close();
                   
            } catch (SQLException e) {
            	e.printStackTrace();
                
            }
    	}
    	return listaFatture;
	}
	
}
