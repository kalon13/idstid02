import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.UnmarshalException;

import classResources.Materiale;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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
		//per visualizzare tutti i dati della select
		public static <T> List<T> getResources(final Class<T> clazz, String path) throws UniformInterfaceException{ 
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
	       try{
	           if (service == null && client == null ) Config();
	           GenericType<List<T>> type = new GenericType<List< T >>(genericType) {};
	           return service.path(path).accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(type);
	       	  }
	       catch(UniformInterfaceException ex){
	    	   final int status = ex.getResponse().getStatus();

	            if (404 == status)
	            {
	             JOptionPane.showMessageDialog(null, "Problema di connessione!", "Attenzione", 0);
	            }
	            else if(204 == status)
	            {
	            	
	            }
	           return null;
	           }
	     } 
		//per visualizzare un det dato passare nella path/id del dato stesso
		public static <T> T getResource(final Class<T> clazz, String path) throws UniformInterfaceException {
		 T t =null;
		 try{  
		      if (service == null && client == null ) Config();
			   t = service.path(path).accept(MediaType.APPLICATION_JSON).get(clazz);
			}
			   catch (UniformInterfaceException ex)
		        {
		            final int status = ex.getResponse().getStatus();

		            if (404 == status)
		            {
		             JOptionPane.showMessageDialog(null, "Problema di connessione!", "Attenzione", 0);
		            }
		            else if(204 == status)
		            {
		            	t = null;
		            }
		        }
		      return t;
	     } 
	
		//TODO: Aggiunge un nuova risorsa		
		public static <T> String addResources(String path, T classObj) throws UniformInterfaceException { 
			if (service == null && client == null ) Config();
			String className = classObj.getClass().getName(); 
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData = multValueIns(className, classObj, path);
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
			formData = multValueUpd(className, classObj, path);
			System.out.print(id);
			service.path(path).path(id).accept(MediaType.APPLICATION_JSON).post(clazz, formData);
		} 
		
		// Elimino una risorsa		
		public static void delResources(String path, String id) throws UniformInterfaceException { 
			if (service == null && client == null ) Config();
		    service.path(path).path(id).accept(MediaType.APPLICATION_JSON).delete(String.class);
		} 
		
		private static <T> MultivaluedMap<String, String> multValueUpd(String className, T classObj, String path){
		 MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		  if (className == "classResources.Materiale" && path.equals(Global._URLMag)){
				Materiale m = (Materiale) classObj;
				String quantita =  String.valueOf(m.getQuantita());
				formData.add("quantita", quantita) ;
				System.out.print(quantita);
			}
		 return formData;
	   }
	   private static <T> MultivaluedMap<String, String> multValueIns(String className, T classObj, String path){
			 MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			 if (className == "classResources.Materiale" && path.equals(Global._URLMag)){
				 Materiale m = (Materiale) classObj;
					String id = String.valueOf(m.getId());
					String id_terzista =  String.valueOf(m.getId_terzista());
					String quantita =  String.valueOf(m.getQuantita());
					formData.add("Materiale_id", id);
					formData.add("Terzista_id", id_terzista);
					formData.add("quantita", quantita);
				}
			 	return formData;
		   }
		
	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/idstid02-server").build();
	}


}