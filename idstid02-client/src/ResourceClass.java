import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class ResourceClass {
	

	public ResourceClass(){	}
		
		private static Client client = null;  
		public static WebResource service = null;
		
		private static void Config(){ 
		  ClientConfig config = new DefaultClientConfig();
		  client = Client.create(config);
	      service = client.resource(getBaseURI());
	  	}
		//per visualizzare un det dato passare nella path/id del dato stesso
		public static <T> List<T> getResources(final Class<T> clazz, String path) throws UniformInterfaceException { 
			   ParameterizedType genericType = new ParameterizedType() { 
	               @Override 
	               public Type[] getActualTypeArguments() { 
	                   return new Type[] {clazz}; 
	               } 

	               @Override 
	               public Type getRawType() { 
	                   return List.class; 
	               } 

	               @Override 
	               public Type getOwnerType() { 
	                   return List.class; 
	               } 
	           };
	           if (service == null && client == null ) Config();
	           GenericType<List<T>> type = new GenericType<List< T >>(genericType) {}; 
	           return service.path(path).accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(type);
	     } 
		
		//TODO: Aggiunge un nuova risorsa		
		public static <T> String addResources(String path, T classObj) throws UniformInterfaceException { 
			if (service == null && client == null ) Config();
			String className = classObj.getClass().getName(); 
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData = multValue(className, classObj);
		    String id = service.path(path)
		    		.accept(MediaType.APPLICATION_JSON)
		    		.type(MediaType.APPLICATION_FORM_URLENCODED).put(String.class, formData);
		    return id;
		} 
		
		//TODO: Modifico una risorsa		
		public static <T> void updResources(final Class<T> clazz, String path, String id, T classObj) throws UniformInterfaceException { 
			if (service == null && client == null ) Config();
			String className = clazz.getName();
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData = multValue(className, classObj);
			service.path(path).path(id).accept(MediaType.APPLICATION_JSON).post(clazz, formData);
		} 
		
		// Elimino una risorsa		
		public static void delResources(String path, String id) throws UniformInterfaceException { 
			if (service == null && client == null ) Config();
		    service.path(path).path(id).accept(MediaType.APPLICATION_JSON).delete(String.class);
		} 
		
		private static <T> MultivaluedMap<String, String> multValue(String className, T classObj){
		 MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		  if (className == "Materiale"){
				Materiale m = (Materiale) classObj;
				String cod = m.getCodice();
				String des = m.getDescrizione();
				String costUn =  String.valueOf(m.getCostoUnitario());
				formData.add("descrizione", des);
				formData.add("codiceArticolo", cod);
				formData.add("costoUnitario", costUn) ;
			}
			return formData;
	   }
		
	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/idstid02-server").build();
	}


}