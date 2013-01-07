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
import main.Autenticazione;
import main.Bolla;

@Path("/bolla")
public class BollaResource {
       
        public BollaResource() {} // E' necessario anche se vuoto

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Bolla> getListaBolla() {
                Statement statement = null;
                ResultSet result = null;
                List<Bolla> listaBolla = new ArrayList<Bolla>();
               
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery(
                                                "SELECT * FROM progingsw.bolla;"
                                        );
                       
                        while(result.next()) {
                                Bolla m = new Bolla(result.getInt(1), result.getString(2), result.getInt(3),
                                                                                        result.getString(4));
                                listaBolla.add(m);
                        }
                        statement.close();
                       
                        return listaBolla;
                       
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
        
        @GET
        @Path ("/valuta/")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Bolla> getListaBollaValutaz() {
                Statement statement = null;
                ResultSet result = null;
                List<Bolla> listaBolla1 = new ArrayList<Bolla>();
               
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery("SELECT * FROM progingsw.bolla WHERE (stato='3' OR stato='4') AND valutata='0';");
                       
                        while(result.next()) {
                                Bolla m1 = new Bolla(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4),
                                		result.getInt(5), result.getInt(6), result.getInt(7));
                                listaBolla1.add(m1);
                        }
                        statement.close();
                       
                        return listaBolla1;
                       
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
       
        @GET
        @Path ("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Bolla getBolla(@PathParam("id") int id) {
                Statement statement = null;
                ResultSet result = null;
                Bolla bolla = null;
               
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery(
                                                "SELECT * FROM progingsw.bolla WHERE id='" + id + "';"
                                        );
                       
                        while(result.next()) {
                                bolla = new Bolla(result.getInt(1), result.getString(2), result.getInt(3),
                                                result.getString(4));
                        }
                        statement.close();
                       
                        return bolla;
                       
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
        
        @GET
        @Path ("/image/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Bolla getBollaImage(@PathParam("id") int id) {
                Statement statement = null;
                ResultSet result = null;
                Bolla bolla = null;
               
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery(
                                                "SELECT * FROM progingsw.bolla WHERE id='" + id + "';"
                                        );
                       
                        while(result.next()) {
                                bolla = new Bolla(result.getInt(1), result.getString(8));
                        }
                        statement.close();
                       
                        return bolla;
                       
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
       
        @GET
        @Path ("/search/{id_terzista}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Bolla> getListaBolla2(@PathParam("id_terzista") int id_terzista) {
                Statement statement = null;
                ResultSet result = null;
                List<Bolla> listaBolla = new ArrayList<Bolla>();
               
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery(
                                                "SELECT * FROM progingsw.bolla WHERE Terzista_id = " + id_terzista + ";"
                                        );
                       
                        while(result.next()) {
                                Bolla m = new Bolla(result.getInt(1), result.getString(2), result.getInt(3),
                                                                                        result.getString(4));
                                listaBolla.add(m);
                        }
                        statement.close();
                       
                        return listaBolla;
                       
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
        
      //aggiunto Giorgia
        @GET
        @Path ("/terzista/{id_terzista}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Bolla> getListaBolla1(@PathParam("id_terzista") int id_terzista) {
                Statement statement = null;
                ResultSet result = null;
                List<Bolla> listaBolla = new ArrayList<Bolla>();

                try {
                       statement = DB.instance.createStatement();
                       result = statement.executeQuery(
                                                "SELECT * FROM progingsw.Bolla join progingsw.lavorazione join " +
                                                "progingsw.lavorazioneterzista on lavorazione.id = lavorazioneterzista.Lavorazione_id"+ 
                                                " and bolla.Lavorazione_id = lavorazioneterzista.id WHERE bolla.LavorazioneTerzista_id = '" + id_terzista + "';"
                                        );
                       
                        while(result.next()) {
                        	//int id, String codice, int stato, String data, String nomeLavorazione
                                Bolla m = new Bolla(result.getInt(1), result.getString(2), result.getInt(3),
                                                                                        result.getString(4), result.getString(9));
                                listaBolla.add(m);
                        }
                        statement.close();
                       
                        return listaBolla;
                       
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
       
        //aggiunto Giorgia
        @GET
        @Path ("/statoCM/{stato}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Bolla> getListaBollaStato(@PathParam("stato") int stato){
                Statement statement = null;
                ResultSet result = null;
                List<Bolla> listaBolla = new ArrayList<Bolla>();
               
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery(
                        		"SELECT bolla.id, codice, stato, data, bolla.Terzista_id, Lavorazione_id, nome, ragSociale " +
                        		" FROM progingsw.Bolla JOIN progingsw.lavorazione JOIN progingsw.terzista JOIN progingsw.lavorazioneterzista " +
                        		" ON lavorazione.id = lavorazioneterzista.Lavorazione_id " +
                        		" AND bolla.LavorazioneTerzista_id = lavorazioneterzista.id " +
                        		" AND bolla.Terzista_id = terzista.id WHERE stato ='" + stato + "';"
                                        );
                               
                        while(result.next()) {
                        	//int id, String codice, int stato, String data, int terzista_id, int lavorazione_id, String nomeLavorazione, String ragSociale
                                Bolla m = new Bolla(result.getInt(1), result.getString(2), result.getInt(3),
                                                result.getString(4), result.getInt(5), result.getInt(6), result.getString(7), result.getString(8));
                                listaBolla.add(m);
                        }
                        statement.close();
                               
                        return listaBolla;
                               
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        
        //Aggiunto Marco
        @GET
        @Path ("/check/{id_lavorazterzista}/{id_terzista}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Bolla> getListaBolla2(@PathParam("id_lavorazterzista") int id_lavorazterzista,
        		@PathParam("id_terzista") int id_terzista) {
                Statement statement = null;
                ResultSet result = null;
                List<Bolla> listaBolla = new ArrayList<Bolla>();
               
                try {
                        statement = DB.instance.createStatement();
                        result = statement.executeQuery(
                                                "SELECT * FROM progingsw.bolla WHERE LavorazioneTerzista_id = " + id_lavorazterzista + " AND Terzista_id = " + id_terzista + ";"
                                        );
                       
                        while(result.next()) {
                                Bolla m = new Bolla(result.getInt(1), result.getString(2), result.getInt(3),
                                                                                        result.getString(4));
                                listaBolla.add(m);
                        }
                        statement.close();
                       
                        return listaBolla;
                       
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
                       
        @POST
        @Path ("{id}")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public String updateBolla( @PathParam("id") int id,
        							@DefaultValue("") @FormParam("descrizione") String descrizione,
        							@DefaultValue("0") @FormParam("costoUnitario") double costoUnitario) {
               
                Statement statement = null;
                int ok = -1;
               
                try {
                        statement = DB.instance.createStatement();
                        ok = statement.executeUpdate(
                                        "UPDATE progingsw.Bolla SET descrizione = '" + descrizione +"'," +
                                        "costoUnitario = " + costoUnitario + " WHERE id='" + id + "';"
                                        );
                        statement.close();

                        return String.valueOf(ok);
                } catch (SQLException e) {
                        e.printStackTrace();
                        return "-1";
                }
        }
        
      //aggiunto Giorgia
        @POST
        @Path ("/stato/{id}")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public String updateBolla1( @PathParam("id") int id,
                                 	@FormParam("Terzista_id") int terzista_id,
                                 	@FormParam("stato") int stato) {
               	
                Statement statement = null;
                int ok = -1;
               
                try {
                        statement = DB.instance.createStatement();
                        ok = statement.executeUpdate(
                                        "UPDATE progingsw.Bolla SET stato = " + stato + ", Terzista_id = " + terzista_id + " WHERE id = " + id + ";"
                                        );
                        statement.close();

                        return String.valueOf(ok);
                } catch (SQLException e) {
                        e.printStackTrace();
                        return "-1";
                }
        }
        
        //aggiunto Marco
        @POST
        @Path ("/valuta/{id}")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public String updateBolla2( @PathParam("id") int id,
        							@FormParam("stato") int stato,
        							@FormParam("Terzista_id") int terzista_id) {
               
                Statement statement = null;
                int ok = -1;
               
                try {
                        statement = DB.instance.createStatement();
                        ok = statement.executeUpdate(
                                        "UPDATE progingsw.bolla SET valutata = '1' WHERE id = '" + id + "';"
                        );
                        
                        statement.close();

                        return String.valueOf(ok);
                        
                } catch (SQLException e) {
                        e.printStackTrace();
                        return "-1";
                }
        }
        
        //aggiunto Marco
        @POST
        @Path ("/riassegna/{terzista_id}")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public String updateBolla3(@PathParam("terzista_id") int terzista_id) {
               
                Statement statement = null;
                int ok = -1;
               
                try {
                        statement = DB.instance.createStatement();
                        ok = statement.executeUpdate(
                                        "UPDATE progingsw.bolla SET stato='0', Terzista_id='-1', LavorazioneTerzista_id='-1' WHERE Terzista_id = '" + terzista_id + "' AND stato='1';"
                        );
                        
                        statement.close();

                        return String.valueOf(ok);
                        
                } catch (SQLException e) {
                        e.printStackTrace();
                        return "-1";
                }
        }

       
        @DELETE
        @Path ("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public String deleteBolla( @PathParam("id") int id) {
               
                Statement statement = null;
                int ok = -1;
               
                try {
                        statement = DB.instance.createStatement();
                        ok = statement.executeUpdate(
                                        "DELETE FROM progingsw.Bolla WHERE id='" + id + "';"
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
        public String insertBolla(  @FormParam("descrizione") String descrizione,
                                    @FormParam("costoUnitario") double costoUnitario) {
               
                Statement statement = null;
                ResultSet result = null;
                int ok = -1;
                int id = -1;
               
                try {
                        statement = DB.instance.createStatement();
                        ok = statement.executeUpdate(
                                        "INSERT INTO progingsw.Bolla(descrizione, costoUnitario) " +
                                        "VALUES('" + descrizione + "', '" + costoUnitario + "');",
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
        @Path ("/notification")
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
        public List<Bolla> getNew(@FormParam("sid") String sessionid,
        							@FormParam("terzistaid") int terzistaid) {
            Statement statement = null;
            ResultSet result = null;
            List<Bolla> listaBolla = new ArrayList<Bolla>();
        	
        	if(Autenticazione.isValid(sessionid)) {
	            try {
	                statement = DB.instance.createStatement();
	                result = statement.executeQuery(
	                                        " SELECT id FROM progingsw.bolla" +
	                                        " WHERE stato='1'" +
	                                        " AND Terzista_id='" + terzistaid + "';"
	                         );
	               
	                if(result != null) {
		                while(result.next()) {
		                        Bolla m = new Bolla(result.getInt(1));
		                        listaBolla.add(m);
		                }
	                }
	                statement.close();
	            } catch (SQLException e) {
	                    e.printStackTrace();
	            }
        	}
        	return listaBolla;
        }

        
        

       
}

