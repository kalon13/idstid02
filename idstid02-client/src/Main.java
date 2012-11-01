import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class Main {

	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    
	    // Ottieni lista
	    List<Materiale> lista = service.path("magazzinoterzista")
	    		.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Materiale>>(){});
	    System.out.println(lista);
	    
	    // Aggiunge un nuovo materiale
	    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
	    formData.add("descrizione", "pelle 4");
	    formData.add("costoUnitario", "66") ;
	    
	    String id = service.path("magazzinoterzista")
	    		.accept(MediaType.APPLICATION_JSON)
	    		.type(MediaType.APPLICATION_FORM_URLENCODED).put(String.class, formData);
	    
	    // Visualizza solo il nuovo materiale
    	Materiale materiale = service.path("magazzinoterzista").path(id)
	    		.accept(MediaType.APPLICATION_JSON).get(Materiale.class);
	    System.out.println(materiale);
	    
	    // Modifica il materiale
	    formData = new MultivaluedMapImpl();
	    formData.add("descrizione", "nuova pelle");
	    formData.add("costoUnitario", "58") ;
	    
	    service.path("magazzinoterzista").path(id)
		.accept(MediaType.APPLICATION_JSON).post(Materiale.class, formData);
	    
	    // Visualizza il materiale modificato
	    materiale = service.path("magazzinoterzista").path(id)
	    		.accept(MediaType.APPLICATION_JSON).get(Materiale.class);
	    System.out.println(materiale);
	    
	    // Elimina il materiale
	    service.path("magazzinoterzista").path(id)
		.accept(MediaType.APPLICATION_JSON).delete(String.class);
	    
	    // Visualizza di nuovo la lista
	    lista = service.path("magazzinoterzista")
	    		.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Materiale>>(){});
	    System.out.println(lista);
	    
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/idstid02-server").build();
	}

}
