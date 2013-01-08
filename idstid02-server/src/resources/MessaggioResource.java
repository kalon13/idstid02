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
import utils.DB;

import main.Autenticazione;
import main.Messaggio;
import main.Sessione;

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
        
        @POST
        @Path("/insert")
    	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    	@Produces(MediaType.APPLICATION_JSON)
        public String sendMessage(@FormParam("sid") String sid, 
        							@FormParam("utenteid") int uid,
        							@FormParam("bollaid") int bid,
        							@FormParam("message") String message) {
            Statement statement = null;
            ResultSet result = null;
            int ok = -1;
            int id = -1;
           
            try {
                statement = DB.instance.createStatement();
                ok = statement.executeUpdate(
                                "INSERT INTO progingsw.messaggio(Utente_id, Bolla_id, testo, data, letto) " +
                                "VALUES('" + uid + "', '" + bid + "', '" + bid + "', NOW(), '0');",
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
        
        @POST
        @Path("/notification")
    	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    	@Produces(MediaType.APPLICATION_JSON)
        public List<Messaggio>getNewMessage(@FormParam("sid") String sid,
											@FormParam("terzistaid") int tid) {
            Statement statement = null;
            ResultSet result = null;
            List<Messaggio> listaMessaggio = new ArrayList<Messaggio>();
        	Sessione s = Autenticazione.getSession(sid);
        	
        	if(s != null) {
	            try {
	            	statement = DB.instance.createStatement();
		                
		                if(s.getUtente().getTipo() < 5) {
		                
		                	result = statement.executeQuery(
		                                "SELECT id, data, testo, letto, Bolla_id" +
		                                " FROM progingsw.Messaggio JOIN progingsw.Utente ON Messaggio.Utente_id=Utente.id " +
		                                " WHERE Utente.tipo='5' AND Messaggio.letto='0';");
		                }
		                else {
		                	result = statement.executeQuery(
	                                "SELECT id, data, testo, letto, Bolla_id" +
	                                " FROM progingsw.Messaggio JOIN progingsw.Bolla ON Messaggio.Bolla_id=Bolla.id " +
	                                " WHERE Bolla.Terzista_id='" + tid + "' " +
	                                	" AND Messaggio.Utente_id<>'" + s.getUtente().getUserId() + "' " +
	                                	" AND Messaggio.letto='0';");
		                }
		                
		                if(result != null){
							while(result.next()) {
							         Messaggio m = new Messaggio(result.getInt(1), result.getString(2),
							                        result.getString(3), result.getBoolean(4), result.getString(5));
							        listaMessaggio.add(m);
							}
		                }
		                statement.close();
	                   
	            } catch (SQLException e) {
	            	e.printStackTrace();
	                
	            }
        	}
        	return listaMessaggio;
        }
       
}
