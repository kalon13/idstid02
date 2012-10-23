package resources;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import utils.DB;

import main.Materiale;

@Path("/magazzinoterzista")
public class MagazzinoTerzistaResource {
	
	public MagazzinoTerzistaResource() {
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Materiale> getMagazzino() {
		ResultSet result = null;
		try {
			Statement s = DB.instance.createStatement();
			result = s.executeQuery(
					"SELECT * FROM Materiale;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<Materiale> lista = new ArrayList<Materiale>();
		
		try {
			while(result.next()) {
				Materiale m = new Materiale(result.getInt(1), result.getString(2),
											result.getString(3), result.getDouble(4));
				lista.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
}
