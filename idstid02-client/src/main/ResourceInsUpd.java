package main;
import javax.ws.rs.core.MultivaluedMap;

import classResources.DDT;
import classResources.Materiale;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class ResourceInsUpd {
	public ResourceInsUpd() {}
	protected static <T> MultivaluedMap<String, String> multValueUpd(String className, T classObj, String path){
		 MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		  if (className == "classResources.Materiale"){
				Materiale m = (Materiale) classObj;
				String quantita =  String.valueOf(m.getQuantita());
				formData.add("quantita", quantita) ;
			}
		  else if (className == "classResources.DDT"){
				formData.add("registrato", "1") ;
			}
		  
		 return formData;
	   }
	
    protected static <T> MultivaluedMap<String, String> multValueIns(String className, T classObj, String path){
			 MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			 if (className == "classResources.Materiale"){
				 Materiale m = (Materiale) classObj;
					String id = String.valueOf(m.getId());
					String id_terzista =  String.valueOf(m.getId_terzista());
					String quantita =  String.valueOf(m.getQuantita());
					formData.add("Materiale_id", id);
					formData.add("Terzista_id", id_terzista);
					formData.add("quantita", quantita);
				}
			 else if (className == "classResources.DDT"){
				    DDT ddt = (DDT) classObj;
				    String id_terzista =  String.valueOf(ddt.getIdTerzista());
					String dtInvio =  String.valueOf(ddt.getDataInvio());
					String flAz =  String.valueOf(ddt.isFlussoAzienda());
					formData.add("Terzista_id", id_terzista);
					formData.add("dataInvio", dtInvio);
					formData.add("flussoAzienda", flAz);
					formData.add("registrato", "0") ;
				}
			 	return formData;
		   }
}