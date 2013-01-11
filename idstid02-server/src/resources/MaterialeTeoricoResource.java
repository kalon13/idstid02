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

import main.MaterialeTeorico;
import utils.DB;


@Path("/matTeo")
public class MaterialeTeoricoResource {

        public MaterialeTeoricoResource() {} // E' necessario anche se vuoto
       
        @GET
        @Path ("/search/{txtSearch}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<MaterialeTeorico> getListaMaterialeTeorico(@PathParam("txtSearch") int txtSearch) {
                Statement statement = null;
                ResultSet result = null;
                List<MaterialeTeorico> listaMaterialeTeorico = new ArrayList<MaterialeTeorico>();
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery(
                                        "SELECT materialiteorici.id, descrizione, costoUnitario, quantita, Bolla_id, udm " +
                                        " FROM progingsw.materialiteorici join progingsw.materiale" +
                                        " ON materiale.id = Materiale_Id WHERE Bolla_id =" + txtSearch + ";");
                        if(result != null){
                         while(result.next()) {
                                //1-id 2-codicearticolo 3-descrizione 4-costounitario 5-id 6-quantita 7-udm 8-bolla_id 9-materiale_id
                                //1-id 3-descrizione 4-costounitario 6-quantita 8-bolla_id
                                MaterialeTeorico m = new MaterialeTeorico(result.getInt(1), result.getString(2), result.getDouble(3), result.getDouble(4), result.getInt(5), result.getString(6));
                                listaMaterialeTeorico.add(m);
                         }
                        }
                        else return null;
                        statement.close();
                       
                        return listaMaterialeTeorico;
                       
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
}

