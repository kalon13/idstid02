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
                                    "SELECT paia.id, idMatDaProd, descrizione, nScarpa, paia.paia FROM progingsw.paia  " +
                                    " JOIN materialidaprodurre JOIN materiale" +
                                    " ON materialidaprodurre.id = paia.idMatDaProd AND materialidaprodurre.Materiale_id = materiale.id WHERE Bolla_id ='" + idBolla + "';");
                    if(result != null){
                     while(result.next()) {
                    	 //id idMatDaProd descr nScarpa paia
                    	 Paia p = new Paia(result.getInt(1), result.getInt(2), result.getString(3), result.getInt(4), result.getInt(5));
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
