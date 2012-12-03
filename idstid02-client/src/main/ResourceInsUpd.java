package main;

import javax.ws.rs.core.MultivaluedMap;

import classResources.DDT;
import classResources.Extraconsumo;
import classResources.Fase;
import classResources.Fattura;
import classResources.LavorazioneTerzista;
import classResources.Materiale;
import classResources.MaterialeDaProdurre;
import classResources.Terzista;
import classResources.Utente;

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

                  else if (className == "classResources.Terzista"){
                  	Terzista m = (Terzista) classObj;
                    String id = String.valueOf(m.getId());
                    String email =  String.valueOf(m.getEmail());
                    String piva =  String.valueOf(m.getpIva());
                    String ragSoc =  String.valueOf(m.getRagioneSociale());
                    String indirizzo =  String.valueOf(m.getIndirizzo());
                    String cap =  String.valueOf(m.getCap());
                    String prov =  String.valueOf(m.getProvincia());
                    String citta =  String.valueOf(m.getCitta());
                    String tel =  String.valueOf(m.getTelefono());
                    String fax =  String.valueOf(m.getFax());
//                    String utenteId =  String.valueOf(GUI_Autenticazione.ID);
                    
                    formData.add("email", email);
                    formData.add("pIva", piva);
                    formData.add("ragSociale", ragSoc);
                    formData.add("indirizzo", indirizzo);
                    formData.add("cap", cap);
                    formData.add("provincia", prov);
                    formData.add("citta", citta);
                    formData.add("telefono", tel);
                    formData.add("fax", fax);
                  }
                  else if (className == "classResources.Utente"){
                	  Utente m = (Utente) classObj;
                	  String user=String.valueOf(m.getUser());
                	  String psw=String.valueOf(m.getPsw());
                	  String tipo=String.valueOf(m.getTipo());
                	  
                	  formData.add("user", user);
                	  formData.add("psw", psw);
                	  formData.add("tipo", tipo);
                  }
                  else if (className == "classResources.LavorazioneTerzista"){
                	  LavorazioneTerzista m = (LavorazioneTerzista) classObj;
                	  String prezzo=String.valueOf(m.getPrezzo());
                	  String capacita=String.valueOf(m.getCapProd());
                	  String lavorazId=String.valueOf(m.getLavorazioneID());
                	  String terzistaId=String.valueOf(m.getTerzistaID());
                	  
                	  formData.add("prezzo", prezzo);
                	  formData.add("capacitaProduzione", capacita);
                	  formData.add("Lavorazione_id", lavorazId);
                	  formData.add("Terzista_id", terzistaId);
                  }
                  else if (className == "classResources.Fase"){
                	  Fase m = (Fase) classObj;
                	  String nome=String.valueOf(m.getNome());
                	  String ordine=String.valueOf(m.getOrdine());
                	  
                	  formData.add("nome", nome);
                	  formData.add("ordine", ordine);
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
                         else if (className == "classResources.LavorazioneTerzista"){
                        	 LavorazioneTerzista m=(LavorazioneTerzista) classObj;
                        	 String prezzo = String.valueOf(m.getPrezzo());
                        	 String capacita = String.valueOf(m.getCapProd());
                        	 String lavorazId = String.valueOf(m.getLavorazioneID());
                        	 String terzistaId = String.valueOf(m.getTerzistaID());
                        	 formData.add("prezzo", prezzo);
                        	 formData.add("capacitaProduzione", capacita);
                        	 formData.add("Lavorazione_id", lavorazId);
                        	 formData.add("Terzista_id", terzistaId);
                         }
                         else if (className == "classResources.Fase"){
                        	 Fase m=(Fase) classObj;
                        	 String nome = String.valueOf(m.getNome());
                        	 String ordine = String.valueOf(m.getOrdine());
                        	 String lavId=String.valueOf(m.getLavTerzId());
                        	 formData.add("nome", nome);
                        	 formData.add("ordine", ordine);
                        	 formData.add("LavorazioneTerzista_id", lavId);
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
