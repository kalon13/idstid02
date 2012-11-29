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

import main.Materiale;
import main.Messaggio;
import utils.DB;

@Path("/messaggio")
public class MessaggioResource {
        
        public MessaggioResource () {} // E' necessario anche se vuoto
        
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Messaggio> getListaMessaggio() {
                Statement statement = null;
                ResultSet result = null;
                List<Messaggio> listaMessaggio = new ArrayList<Messaggio>();
                try {
                        statement = DB.instance.createStatement();
                        //int id, String data, String testo, boolean letto
                        result = statement.executeQuery(
                                                "SELECT id, data, testo, letto "+
                                                "FROM progingsw.Messaggio;");
                        while(result.next()) {
                                Messaggio m = new Messaggio(result.getInt(1), result.getString(2),
                                                                                        result.getString(3), result.getBoolean(4));
                                listaMessaggio.add(m);
                        }
                        statement.close();
                        
                        return listaMessaggio;
                        
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
                
                //aggiunto
                @GET
                @Path ("/search/{txtSearch}")
                @Produces(MediaType.APPLICATION_JSON)
                public List<Messaggio> getListaMessaggio1(@PathParam("txtSearch") String txtSearch) {
                        Statement statement = null;
                        ResultSet result = null;
                        List<Messaggio> listaMessaggio = new ArrayList<Messaggio>();
                        //TODO da aggiungere se si entra con un det profilo terzista: and terzista_id = "";
                        try {
                                statement = DB.instance.createStatement();
                                result = statement.executeQuery(
                                                "SELECT id, data, testo, letto, Bolla_id" +
                                                " FROM progingsw.Messaggio" +
                                                " WHERE Bolla_id=" + txtSearch + ";");
                                if(result != null){
                                 while(result.next()) {
                                         Messaggio m = new Messaggio(result.getInt(1), result.getString(2),
                                                        result.getString(3), result.getBoolean(4), result.getString(5));
                                        listaMessaggio.add(m); 
                                 }
                                }
                                else return null;
                                statement.close();
                                
                                return listaMessaggio;
                                
                        } catch (SQLException e) {
                                e.printStackTrace();
                                return null;
                        }
                }
        
}