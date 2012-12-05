package main;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.crypto.Data;

import classResources.Bolla;
import classResources.DDT;
import classResources.Extraconsumo;
import classResources.Fattura;
import classResources.Materiale;
import classResources.MaterialeDaProdurre;

import com.sun.jersey.core.util.MultivaluedMapImpl;


public class ResourceInsUpd {
	public ResourceInsUpd() {}
	protected static <T> MultivaluedMap<String, String> multValueUpd(String className, T classObj, String path){
		 MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		  if (className == "classResources.Materiale"){
				Materiale m = (Materiale) classObj;
				String quantita =  String.valueOf(m.getQuantita());
				formData.add("quantita", quantita);
			}
		  else if (className == "classResources.DDT"){
				formData.add("registrato", "1");
			}
		  else if (className == "classResources.MaterialeDaProdurre") {
			  MaterialeDaProdurre m = (MaterialeDaProdurre) classObj;
			  String quantitaProdotta =  String.valueOf(m.getQuantitaProdotta());
			  String quantitaSpedita =  String.valueOf(m.getQuantitaSpedita());
			  String numeroMorti =  String.valueOf(m.getNumeroMorti());
			  formData.add("quantitaProdotta", quantitaProdotta);
			  formData.add("quantitaSpedita", quantitaSpedita);
			  formData.add("numeroMorti", numeroMorti);
		  }
		  else if (className == "classResources.Extraconsumo") {
			  Extraconsumo ext = (Extraconsumo) classObj;
			  String quantita =  String.valueOf(ext.getQuantita());
			  String giustificato =  String.valueOf(ext.isGiustificato());
			  String dataRichiesta =  String.valueOf(ext.getDataRichiesta());
			  formData.add("quantita", quantita);
			  formData.add("giustificato", giustificato);
			  formData.add("dataRichiesta", dataRichiesta);
		  }
		  else if (className == "classResources.Bolla") { //Giorgia
			  Bolla b = (Bolla) classObj;
			  String stato = String.valueOf(b.getStato());
			  String terzista_id = String.valueOf(b.getTerzista_id());
			  formData.add("stato", stato);
			  formData.add("Terzista_id", terzista_id); //maiuscola!
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
			 else if (className == "classResources.Fattura" && path == Global._URLFatt){
				 	Fattura fat = (Fattura) classObj;
				 	String dt = fat.getDataEmissione();
				 	String idT = String.valueOf(fat.getIdTerz());
				 	String imp =  String.valueOf(fat.getImporto());
				 	formData.add("dataEmissione", dt);
				 	formData.add("importo", imp);
				 	formData.add("Terzista_id", idT);
			 	}
			 else if (className == "classResources.Fattura" && path == Global._URLFatt+"/Bolla"){
				 	Fattura fat = (Fattura) classObj;
				 	String idF = String.valueOf(fat.getId());
				 	String idB = String.valueOf(fat.getIdBolla());
				 	formData.add("Fattura_id", idF);
				 	formData.add("Bolla_id", idB);
			 }
			 return formData;
    	}
}