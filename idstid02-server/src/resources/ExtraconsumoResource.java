package resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.Extraconsumo;
import main.MaterialeDaProdurre;
import utils.DB;

@Path("/extraconsumo")
public class ExtraconsumoResource {
	
	public ExtraconsumoResource() {} // E' necessario anche se vuoto
	
	@GET
	@Path ("/search/{txtSearch}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Extraconsumo> getListaExtraconsumo(@PathParam("txtSearch") String txtSearch) {
		Statement statement = null;
		ResultSet result = null;
		List<Extraconsumo> listaExtraconsumo = new ArrayList<Extraconsumo>();
		try {
			statement = DB.instance.createStatement();
			result = statement.executeQuery(
					"SELECT extraconsumo.id, codiceArticolo, descrizione, materialiteorici.quantita AS QtaAttuale, extraconsumo.quantita AS QtaRichiesta, udm, giustificato, dataRichiesta " +
					" FROM ProgIngSw.materiale join ProgIngSw.materialiteorici join ProgIngSw.extraconsumo " +
					" ON materiale.id = Materiale_Id AND materialiteorici.id = extraconsumo.MaterialiTeorici_id " +
					" WHERE Bolla_id =" + txtSearch + ";");
			if(result != null){
				while(result.next()) {
				    //1-extraconsumo.id, 2-codiceArticolo, 3-descrizione, 4-materialiteorici.quantita AS QtaAttuale, 5-extraconsumo.quantita AS QtaRichiesta, 6-udm, 7-giustificato, 8-dataRichiesta
					Extraconsumo m = new Extraconsumo(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4), result.getDouble(5), result.getString(6), result.getBoolean(7), result.getString(8));
					listaExtraconsumo.add(m); 
				}
			}
			else return null;
				statement.close();
							
			return listaExtraconsumo;
							
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
	}
	
	@POST
	@Path ("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateMaterialeDaProdurre(@PathParam("id") int id,
								@FormParam("quantita") double quantita,
								@FormParam("giustificato") boolean giustificato,
								@FormParam("dataRichiesta") String dataRichiesta) {
		
		Statement statement = null;
		int ok = -1;
		
		try {
			statement = DB.instance.createStatement();
			ok = statement.executeUpdate(
					"UPDATE ProgIngSw.extraconsumo SET quantita = " + quantita + ", giustificato=" + giustificato + ", dataRichiesta ='" + dataRichiesta + "' WHERE id=" + id + ";"
					);
			statement.close();
			return String.valueOf(ok);
		} catch (SQLException e) {
			e.printStackTrace();
			return "-1";
		}
	}
}
