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

import main.Extraconsumo;
import utils.DB;

@Path("/extraconsumo")
public class ExtraconsumoResource {
       
        public ExtraconsumoResource() {} // E' necessario anche se vuoto
       
        @GET
        @Path ("/idBolla/{idBolla}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Extraconsumo> getListaExtraconsumo(@PathParam("idBolla") int idBolla) {
                Statement statement = null;
                ResultSet result = null;
                List<Extraconsumo> listaExtraconsumo = new ArrayList<Extraconsumo>();
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery(
                                        "SELECT extraconsumo.id, codiceArticolo, descrizione, materialiteorici.quantita AS QtaAttuale, extraconsumo.quantita AS QtaRichiesta, udm, giustificato, dataRichiesta, costoUnitario " +
                                        " FROM progingsw.materiale join progingsw.materialiteorici join progingsw.extraconsumo " +
                                        " ON materiale.id = Materiale_Id AND materialiteorici.id = extraconsumo.MaterialiTeorici_id " +
                                        " WHERE Bolla_id ='" + idBolla + "';");
                        if(result != null){
                                while(result.next()) {
                                    //1-extraconsumo.id, 2-codiceArticolo, 3-descrizione, 4-materialiteorici.quantita AS QtaAttuale, 5-extraconsumo.quantita AS QtaRichiesta, 6-udm, 7-giustificato, 8-dataRichiesta
                                        Extraconsumo m = new Extraconsumo(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4), result.getDouble(5), result.getString(6), result.getInt(7), result.getString(8), result.getDouble(9));
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
                                        "UPDATE progingsw.extraconsumo SET quantita = " + quantita + ", giustificato=" + giustificato + ", dataRichiesta ='" + dataRichiesta + "' WHERE id=" + id + ";"
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
		public String insertExtraconsumo(@FormParam("MaterialiTeorici_id") int MaterialiTeorici_id,
								  @FormParam("quantita") double quantita,
								  @FormParam("giustificato") int giustificato,
								  @FormParam("dataRichiesta") String dataRichiesta) {
			
			Statement statement = null;
			ResultSet result = null;
			int ok = -1;
			int id = -1;
			try {
				statement = DB.instance.createStatement();
				ok = statement.executeUpdate(
						"INSERT INTO progingsw.extraconsumo (MaterialiTeorici_id, quantita, giustificato, dataRichiesta) " +
						"VALUES('" + MaterialiTeorici_id + "', '" + quantita + "', '" + giustificato + "', '" + dataRichiesta + "');", 
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
