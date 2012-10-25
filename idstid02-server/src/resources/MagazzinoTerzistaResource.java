package resources;
import java.sql.Connection;
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
					"SELECT * FROM ProgIngSw.Materiale;");
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
	
	@GET
	@Path ("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Materiale getMagazzino(@PathParam("id") int id) {
		ResultSet result = null;
		try {
			Statement s = DB.instance.createStatement();
			result = s.executeQuery(
					"SELECT * FROM ProgIngSw.Materiale WHERE id='" + id + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(result.next()) {
				return new Materiale(result.getInt(1), result.getString(2),
											result.getString(3), result.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PUT
	@Path ("{id}/{costoUnitario}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getMagazzino(@PathParam("id") int id,
								@PathParam("costoUnitario") double costoUnitario) {
		ResultSet result = null;
		try {
			Statement s = DB.instance.createStatement();
			result = s.executeQuery(
					"UPDATE ProgIngSw.Materiale SET costoUnitario = " + costoUnitario + " WHERE id='" + id + "';");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
