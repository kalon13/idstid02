package utils;


import javax.ws.rs.core.MultivaluedMap;

import main.Bolla;
import main.Consumo;
import main.DDT;
import main.Extraconsumo;
import main.Fase;
import main.Fattura;
import main.Fattura_Lavorazione;
import main.LavorazioneTerzista;
import main.Materiale;
import main.MaterialeDDT;
import main.MaterialeDaProdurre;
import main.Terzista;
import main.Utente;


import com.sun.jersey.core.util.MultivaluedMapImpl;


public class ResourceInsUpd {
	
        public ResourceInsUpd() {}
        
        protected static <T> MultivaluedMap<String, String> multValueUpd(String className, T classObj, String path){
                 MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
                  if (className == "main.Materiale"){
                                Materiale m = (Materiale) classObj;
                                String quantita =  String.valueOf(m.getQuantita());
                                formData.add("quantita", quantita) ;
                  }
                  else if (className == "main.DDT"){
                                formData.add("registrato", "1") ;
                  }
                  else if (className == "main.MaterialeDaProdurre" && path == Global._URLMatDaProdurre) {
                      MaterialeDaProdurre m = (MaterialeDaProdurre) classObj;
                      String quantitaProdotta =  String.valueOf(m.getQuantitaProdotta());
                      String quantitaSpedita =  String.valueOf(m.getQuantitaSpedita());
                      String numeroMorti =  String.valueOf(m.getNumeroMorti());
                      formData.add("quantitaProdotta", quantitaProdotta);
                      formData.add("quantitaSpedita", quantitaSpedita);
                      formData.add("numeroMorti", numeroMorti);
                  }
                  else if (className == "main.MaterialeDaProdurre" && path == Global._URLMatDaProdSped) {
                      MaterialeDaProdurre m = (MaterialeDaProdurre) classObj;
                      String quantitaSpedita =  String.valueOf(m.getQuantitaSpedita());
                      formData.add("quantitaSpedita", quantitaSpedita);
                  }
                  else if (className == "main.Extraconsumo") {
                      Extraconsumo ext = (Extraconsumo) classObj;
                      String quantita =  String.valueOf(ext.getQuantita());
                      String giustificato =  String.valueOf(ext.getGiustificato());
                      String dataRichiesta =  String.valueOf(ext.getDataRichiesta());
                      formData.add("quantita", quantita);
                      formData.add("giustificato", giustificato);
                      formData.add("dataRichiesta", dataRichiesta);
                  }
                  else if (className == "main.Bolla") { //Giorgia
                      Bolla b = (Bolla) classObj;
                      String stato = String.valueOf(b.getStato());
                      String terzista_id = String.valueOf(b.getTerzistaId());
                      formData.add("stato", stato);
                      formData.add("Terzista_id", terzista_id); //maiuscola!
                  }
                  else if (className == "main.Terzista"){
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
                  else if (className == "main.Utente"){
                	  Utente m = (Utente) classObj;
                	  String user=String.valueOf(m.getUser());
                	  String psw=String.valueOf(m.getPsw());
                	  String tipo=String.valueOf(m.getTipo());
                	  
                	  formData.add("user", user);
                	  formData.add("psw", psw);
                	  formData.add("tipo", tipo);
                  }
                  else if (className == "main.LavorazioneTerzista"){
                	  LavorazioneTerzista m = (LavorazioneTerzista) classObj;
                	  String prezzo=String.valueOf(m.getPrezzo());
                	  String capacita=String.valueOf(m.getCapProd());
                	  String lavorazId=String.valueOf(m.getLavorazioneID());
                	  String terzistaId=String.valueOf(m.getTerzistaID());
                	  String qualita=String.valueOf(m.getQualita());
                	  String numVotazioni=String.valueOf(m.getNumVotaz());
                	  
                	  formData.add("prezzo", prezzo);
                	  formData.add("capacitaProduzione", capacita);
                	  formData.add("Lavorazione_id", lavorazId);
                	  formData.add("Terzista_id", terzistaId);
                	  formData.add("qualita", qualita);
                	  formData.add("numeroVotazioni", numVotazioni);
                  }
                  else if (className == "main.Fase"){
                	  Fase m = (Fase) classObj;
                	  String nome=String.valueOf(m.getNome());
                	  String ordine=String.valueOf(m.getOrdine());
                	  
                	  formData.add("nome", nome);
                	  formData.add("ordine", ordine);
                  }
                  //fai l'upd di magaz terz
                  else if (className == "main.Consumo"){
                	  Consumo m = (Consumo) classObj;
                	  String idMatPrima=String.valueOf(m.getMatPrima());
                	  String idTer=String.valueOf(m.getIdTerzista());
                	  String qnt=String.valueOf(m.getQuantita());
                	  
                	  formData.add("Materiale_id", idMatPrima);
                	  formData.add("Terzista_id", idTer);
                	  formData.add("quantita", qnt);
                  }
                  
                  
                 return formData;
        }
       
    protected static <T> MultivaluedMap<String, String> multValueIns(String className, T classObj, String path){
    					 MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
                         if (className == "main.Materiale"){
                                 Materiale m = (Materiale) classObj;
                                 String id = String.valueOf(m.getId());
                                 String id_terzista =  String.valueOf(m.getId_terzista());
                                 String quantita =  String.valueOf(m.getQuantita());
                                 formData.add("Materiale_id", id);
                                 formData.add("Terzista_id", id_terzista);
                                 formData.add("quantita", quantita);
                         }
                         else if (className == "main.DDT"){
                             DDT ddt = (DDT) classObj;
                             String id_terzista =  String.valueOf(ddt.getIdTerzista());
                             String dtInvio =  String.valueOf(ddt.getDataInvio());
                             String flAz =  String.valueOf(ddt.isFlussoAzienda());
                             formData.add("Terzista_id", id_terzista);
                             formData.add("dataInvio", dtInvio);
                             formData.add("flussoAzienda", flAz);
                             formData.add("registrato", "0") ;
                         }
                         else if (className == "main.MaterialeDDT"){
                        	 MaterialeDDT matddt = (MaterialeDDT) classObj;
                             String id_mat =  String.valueOf(matddt.getId_materiale());
                             String qnt =  String.valueOf(matddt.getQuantita());
                             String id_ddt =  String.valueOf(matddt.getid_DDT());
                             String Terzista_id =  String.valueOf(matddt.getIdTerzista());
                             formData.add("Materiale_id", id_mat);
                             formData.add("quantita", qnt);
                             formData.add("DDT_id", id_ddt);
                             formData.add("Terzista_id", Terzista_id);
                         }
                         else if (className == "main.LavorazioneTerzista"){
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
                         else if (className == "main.Fase"){
                        	 Fase m=(Fase) classObj;
                        	 String nome = String.valueOf(m.getNome());
                        	 String ordine = String.valueOf(m.getOrdine());
                        	 String lavId=String.valueOf(m.getLavTerzId());
                        	 formData.add("nome", nome);
                        	 formData.add("ordine", ordine);
                        	 formData.add("LavorazioneTerzista_id", lavId);
                         }
                         else if (className == "main.Fattura"){
                             Fattura fat = (Fattura) classObj;
                             String dt = fat.getDataEmissione();
                             String idT = String.valueOf(fat.getIdTerz());
                             String imp =  String.valueOf(fat.getImporto());
//                             System.out.println("ciao"+idT);
                             formData.add("dataEmissione", dt);
                             formData.add("importo", imp);
                             formData.add("Terzista_id", idT);
                         }
                         else if (className == "main.Fattura_Lavorazione"){
                             Fattura_Lavorazione fat = (Fattura_Lavorazione) classObj;
                             String idF = String.valueOf(fat.getIdFattura());
                             String idB = String.valueOf(fat.getIdBolla());
                             String imp = String.valueOf(fat.getTotImp2Bol());
//                             System.out.print(idF+"e"+idB+"f"+imp);
                             formData.add("Fattura_id", idF);
                             formData.add("Bolla_id", idB);
                             formData.add("importo", imp);
                         }
                         else if (className == "main.Extraconsumo"){
                             Extraconsumo ext = (Extraconsumo) classObj;
                             String idMatTeo = String.valueOf(ext.getIdMatTeo());
                             String quantita = String.valueOf(ext.getQuantita());
                             String giustificato = String.valueOf(ext.getGiustificato());
                             String dataRichiesta = String.valueOf(ext.getDataRichiesta());
                             formData.add("MaterialiTeorici_id", idMatTeo);
                             formData.add("quantita", quantita);
                             formData.add("giustificato", giustificato);
                             formData.add("dataRichiesta", dataRichiesta);
                         }
                         return formData;
     }
    
}
