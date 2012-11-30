

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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.MaterialeDaProdurre;
import utils.DB;

@Path("/matDaProdurre")
public class MaterialeDaProdurreResource {

        public MaterialeDaProdurreResource() {} // E' necessario anche se vuoto
               
        //aggiunto - con join
                @GET
                @Path ("/search1/{txtSearch1}")
                @Produces(MediaType.APPLICATION_JSON)
                public List<MaterialeDaProdurre> getListaMaterialeDaProdurre1(@PathParam("txtSearch1") String txtSearch1) {
                        Statement statement = null;
                        ResultSet result = null;
                        List<MaterialeDaProdurre> listaMaterialeDaProdurre = new ArrayList<MaterialeDaProdurre>();
                        try {
                                statement = DB.instance.createStatement();
                                result = statement.executeQuery(
                                                "SELECT quantita, numeroMorti, quantitaProdotta, quantitaSpedita, descrizione, costoUnitario, udm, materialidaprodurre.id" +
                                                " FROM progingsw.materialidaprodurre join materiale" +
                                                " on materiale.id = materialidaprodurre.Materiale_id WHERE Bolla_id =" + txtSearch1 + ";");
                                if(result != null){
                                        while(result.next()) {
                                        //1-quantita 2-numeromorti 3-quantitaprodotta 4-quantitaspedita 5-descrizione 6-costoUnitario 7-udm
                                                MaterialeDaProdurre m = new MaterialeDaProdurre(result.getInt(8), result.getDouble(1), result.getInt(2), result.getDouble(3), result.getDouble(4), result.getString(5), result.getDouble(6), result.getString(7));
                                                listaMaterialeDaProdurre.add(m);
                                        }
                                }
                                else return null;
                                statement.close();
                                               
                                return listaMaterialeDaProdurre;
                                               
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
                                                                        @FormParam("numeroMorti") int numeroMorti,
                                                                        @FormParam("quantitaProdotta") double quantitaProdotta,
                                                                        @FormParam("quantitaSpedita") double quantitaSpedita) {
                       
                        Statement statement = null;
                        int ok = -1;
                       
                        try {
                                statement = DB.instance.createStatement();
                                ok = statement.executeUpdate(
                                                "UPDATE ProgIngSw.materialidaprodurre SET numeroMorti = " + numeroMorti + ", quantitaProdotta=" + quantitaProdotta + ", quantitaSpedita=" + quantitaSpedita + " WHERE id=" + id + ";"
                                                );
                                statement.close();

                                return String.valueOf(ok);
                        } catch (SQLException e) {
                                e.printStackTrace();
                                return "-1";
                        }
                }
}

