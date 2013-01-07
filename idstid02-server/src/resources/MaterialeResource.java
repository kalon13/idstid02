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

import main.Materiale;
import utils.DB;


@Path("/materiale")
public class MaterialeResource {
       
        public MaterialeResource() {} // E' necessario anche se vuoto
       
        //aggiunto
                @GET
                @Path ("{id}")
                @Produces(MediaType.APPLICATION_JSON)
                public List<Materiale> getListaMateriale(@PathParam("id") int id) {
                        Statement statement = null;
                        ResultSet result = null;
                        List<Materiale> listaMateriale = new ArrayList<Materiale>();
                        //TODO da aggiungere se si entra con un det profilo terzista: and terzista_id = "";
                        try {
                                statement = DB.instance.createStatement();
                                result = statement.executeQuery(
                                                "SELECT * " +
                                                " FROM progingsw.materiale" +
                                                " WHERE id=" + id + ";");
                                if(result != null){
                                 while(result.next()) {
                                         Materiale m = new Materiale(result.getInt(1), result.getString(2), result.getDouble(3), result.getString(4));
                                         listaMateriale.add(m);
                                 }
                                }
                                else return null;
                                statement.close();
                               
                                return listaMateriale;
                               
                        } catch (SQLException e) {
                                e.printStackTrace();
                                return null;
                        }
                }

}
