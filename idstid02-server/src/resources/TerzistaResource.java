package resources;
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
import javax.ws.rs.*;
import utils.DB;

import main.Terzista;
import utils.DB;

@Path("/terzista")
public class TerzistaResource {
	
	 public TerzistaResource () {} // E' necessario anche se vuoto
	 
	 //aggiunto Giorgia
	 @GET
     @Produces(MediaType.APPLICATION_JSON)
     public List<Terzista> getListaTerzistao() {
             Statement statement = null;
             ResultSet result = null;
             List<Terzista> listaTerzista = new ArrayList<Terzista>();
             try {
                     statement = DB.instance.createStatement();   
                     result = statement.executeQuery(
                                             "SELECT * "+
                                             "FROM ProgIngSw.terzista;");
                     while(result.next()) {
                    	     Terzista t = new Terzista(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9), result.getString(10), result.getInt(11));
                             listaTerzista.add(t);
                     }
                     statement.close();
                     
                     return listaTerzista;
                     
             } catch (SQLException e) {
                     e.printStackTrace();
                     return null;
             }
     }

}
