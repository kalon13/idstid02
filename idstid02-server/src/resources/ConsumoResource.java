package resources;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import utils.DB;

import main.Consumo;
import main.Materiale;

@Path("/consumo")
public class ConsumoResource {
	
	public ConsumoResource() {} // E' necessario anche se vuoto

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consumo> getListaMateriali() {
		Statement statement = null;
		ResultSet result = null;
		List<Consumo> listaConsumo = new ArrayList<Consumo>();
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
						"SELECT id, prodotto, materiaPrima, quantita, udm " +
						"FROM progingsw.consumo");
			//int id, String codice, String descrizione, double costoUnitario, int id_terzista
			while(result.next()) {
				Consumo c = new Consumo(result.getInt(1), result.getInt(2),
											result.getInt(3), result.getDouble(4));
				listaConsumo.add(c);
			}
			statement.close();
			
			return listaConsumo;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path ("{idPr}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consumo> getConsumo(@PathParam("idPr") int idPr) {
		Statement statement = null;
		ResultSet result = null;
		List<Consumo> listaConsumo = new ArrayList<Consumo>();
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
					"SELECT id, prodotto, materiaPrima, quantita, udm " +
					"FROM progingsw.consumo WHERE prodotto = "+idPr);
			 while(result.next()) {
				 Consumo c = new Consumo(result.getInt(1), result.getInt(2),
							result.getInt(3), result.getDouble(4));
				 listaConsumo.add(c);
			  }
			statement.close();
			if(listaConsumo.size() == 0)
				listaConsumo = null;
			return listaConsumo;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
