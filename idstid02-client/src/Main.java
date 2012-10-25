import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    // Get XML
	    //List<Materiale> lista = (List<Materiale>) service.path("magazzinoterzista").accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Materiale>>(){});
	    String ris =  service.path("magazzinoterzista").accept(MediaType.APPLICATION_JSON).get(String.class);
	    System.out.println(ris);
	    
	    boolean a = service.path("magazzinoterzista").path("1").path("13").accept(MediaType.APPLICATION_JSON).put(Boolean.class);
	    System.out.println(a);
	    
	    String ris2 = service.path("magazzinoterzista").path("1").accept(MediaType.APPLICATION_JSON).get(String.class);
	    System.out.println(ris2);
	}
	
	  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/idstid02-server").build();
	  }

}
