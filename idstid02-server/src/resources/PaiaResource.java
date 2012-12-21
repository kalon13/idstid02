package resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.Paia;
import utils.DB;

@Path("/paia")
public class PaiaResource {
	
	public PaiaResource() {} // E' necessario anche se vuoto

	@GET
    @Path ("/idBolla/{idBolla}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paia> getListaPaia(@PathParam("idBolla") int idBolla) {
            Statement statement = null;
            ResultSet result = null;
            List<Paia> listaPaia = new ArrayList<Paia>();
            try {
                    statement = DB.instance.createStatement();
                    result = statement.executeQuery(
                                    "SELECT paia.id, idMatDaProd, descrizione, paia36, paia37, paia38, paia39, paia40, paia41, paia42, quantita FROM progingsw.paia JOIN progingsw.materialidaprodurre JOIN progingsw.materiale " +
                                    " ON materialidaprodurre.id = paia.idMatDaProd and materialidaprodurre.Materiale_id = materiale.id" +
                                    " WHERE Bolla_id ='" + idBolla + "';");
                    if(result != null){
                     while(result.next()) {
                    	 Paia p = new Paia(result.getInt(1), result.getInt(2), result.getString(3), result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8), result.getInt(9), result.getInt(10), result.getInt(11));
                         listaPaia.add(p);
                     	}
                    }
                    else return null;
                    statement.close();
                   
                    return listaPaia;
                   
            } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
            }
    }
}
