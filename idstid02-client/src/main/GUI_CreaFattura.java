package main;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import classResources.*;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class GUI_CreaFattura {

        JFrame frmCreazioneFattura;
        private JTextField txtImpTot;
        private JTable tblMatP;
        private JTable tblMatEx;
        private int idTerzista = -1;
        private DefaultListModel dfmLs;
        //id bolle della lista da fatturare
        private ArrayList<String> _idB = new ArrayList<String>();
        private HashMap<String,Double> mapIngImp = new HashMap<String,Double>();
        //id bolle della lista da fatturare
        private ArrayList<String> _idFB = new ArrayList<String>();
        private double ImpFattBol = 0.0; 
        
       
        public GUI_CreaFattura() {
        	 Terzista terzista = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+Autenticazione.getSessione().getUtente().getUserId());
        	 idTerzista =  terzista.getId();
        	 initialize();
            }
        
        private void initialize() {
                frmCreazioneFattura = new JFrame();
                frmCreazioneFattura.setResizable(false);
                frmCreazioneFattura.setTitle("Creazione Fattura");
                frmCreazioneFattura.setBounds(100, 100, 914, 475);
                frmCreazioneFattura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmCreazioneFattura.getContentPane().setLayout(null);
               
                JLabel lblBolle = new JLabel("Bolle:");
                lblBolle.setBounds(10, 11, 46, 14);
                frmCreazioneFattura.getContentPane().add(lblBolle);
               
                JLabel lblDaFatturare = new JLabel("Da Fatturare:");
                lblDaFatturare.setBounds(322, 11, 115, 14);
                frmCreazioneFattura.getContentPane().add(lblDaFatturare);
               
                JLabel lblImportoTotale = new JLabel("Importo Totale con IVA al 21%:");
                lblImportoTotale.setBounds(567, 36, 152, 14);
                frmCreazioneFattura.getContentPane().add(lblImportoTotale);
               
                JPanel panel = new JPanel();
                panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
                panel.setForeground(Color.BLACK);
                panel.setBounds(10, 139, 860, 260);
                frmCreazioneFattura.getContentPane().add(panel);
                panel.setLayout(null);
               
                JLabel lblDettagliBollaSelezionata = new JLabel("Dettagli Bolla Selezionata:");
                lblDettagliBollaSelezionata.setBounds(10, 11, 181, 14);
                panel.add(lblDettagliBollaSelezionata);
               
                JLabel lblMaterialiProdotti = new JLabel("Materiali Prodotti:");
                lblMaterialiProdotti.setBounds(10, 25, 124, 14);
                panel.add(lblMaterialiProdotti);
               
                JLabel lblMaterialiExtraconsumi = new JLabel("Materiali Extraconsumi:");
                lblMaterialiExtraconsumi.setBounds(366, 25, 160, 14);
                panel.add(lblMaterialiExtraconsumi);
               
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(10, 49, 330, 167);
                panel.add(scrollPane);
               
                tblMatP = new JTable();
                tblMatP.setEnabled(false);
                tblMatP.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                        "Articolo", "Materiale", "Quantit\u00E0", "Udm"
                                }
                        ));
                tblMatP.getColumnModel().getColumn(3).setPreferredWidth(88);
                scrollPane.setViewportView(tblMatP);
               
                JScrollPane scrollPane_1 = new JScrollPane();
                scrollPane_1.setBounds(366, 50, 480, 167);
                panel.add(scrollPane_1);
               
                tblMatEx = new JTable();
                tblMatEx.setEnabled(false);
                tblMatEx.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                        "Articolo", "Materiale", "Quantit\u00E0", "Udm", "Costo Unitario", "Giustificato", "Data"
                                }
                        ));
                tblMatEx.getColumnModel().getColumn(3).setPreferredWidth(88);
                scrollPane_1.setViewportView(tblMatEx);
               
                JButton btnEsci = new JButton("Esci");
                btnEsci.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                frmCreazioneFattura.dispose();
                        }
                });
                btnEsci.setBounds(761, 412, 95, 23);
                frmCreazioneFattura.getContentPane().add(btnEsci);
               
                JButton btnCrea = new JButton("Crea");
                btnCrea.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                          int result = JOptionPane.showConfirmDialog(frmCreazioneFattura, "Si vuole confermare la creazione della fattura?");
                      	  if(JOptionPane.YES_OPTION == result){
                           String dt = FormatDate.getToday();
                            double imp = 0;
                            String id = "-1";
                            if(!txtImpTot.getText().isEmpty() && !txtImpTot.getText().equals("0.0"))
                            {
                                imp = Double.parseDouble(txtImpTot.getText());
	                            Fattura fat = new Fattura();
	                            fat.setIdTerz(idTerzista);
	                            fat.setImporto(imp);
	                            fat.setDataEmissione(dt);
	                            id = ResourceClass.addResources(Global._URLFatt, fat);
	                            Iterator<String> itFattBol =  _idFB.iterator();
	                            //Per ogni fattura registro le bolle d lavorazione
	                            while(itFattBol.hasNext())
	                            {   int idBolla = Integer.valueOf((String) itFattBol.next());
	                            	Fattura_Lavorazione fatLavBol = new Fattura_Lavorazione();
	                            	fatLavBol = ResourceClass.getResource(Fattura_Lavorazione.class, Global._URLImpFattBol+idBolla);
	                            	fatLavBol.setIdBolla(idBolla);
	                            	fatLavBol.setIdFattura(Integer.valueOf(id));
	                                ResourceClass.addResources(Global._URLFattBol, fatLavBol);
	                            }
	                            if(id != "-1")
	                            	JOptionPane.showMessageDialog(frmCreazioneFattura, "La fattura è stata creata!");
                            }
                             else JOptionPane.showMessageDialog(frmCreazioneFattura, "La fattura ha importo nullo!");
                      	  }
                       	}
                });
                btnCrea.setBounds(567, 86, 95, 23);
                frmCreazioneFattura.getContentPane().add(btnCrea);
                
               //Carica lista bolle
               loadLsBoll();
               final JList listBolle = new JList(dfmLs);
               listBolle.addListSelectionListener(new ListSelectionListener() {
               public void valueChanged(ListSelectionEvent e) {
                        if(listBolle.getSelectedIndex() !=-1){
                            int index = listBolle.getSelectedIndex();
                            String idB = _idB.get(index);
                            loadTableMatEx(idB);
                            loadTableMatP(idB);
                        }
                        else
                        {tblMatEx.removeAll();
                         tblMatP.removeAll();}
                	}
            	});
           
                listBolle.setBounds(10, 36, 234, 87);
                frmCreazioneFattura.getContentPane().add(listBolle);
               
                final DefaultListModel listModelFatt = new DefaultListModel();
                final JList listFatt = new JList(listModelFatt);
                listFatt.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent e) {
                         if(listFatt.getSelectedIndex() !=-1){
                           int index = listFatt.getSelectedIndex();
                           String idB = String.valueOf(_idFB.get(index));
                           loadTableMatEx(idB);
                           loadTableMatP(idB);
                         }
                         else
                           {tblMatEx.removeAll();
                            tblMatP.removeAll();}
                        }
                });
                listFatt.setBounds(322, 36, 234, 87);
                frmCreazioneFattura.getContentPane().add(listFatt);
               
                txtImpTot = new JTextField();
                txtImpTot.setEditable(false);
                txtImpTot.setBounds(567, 55, 95, 20);
                frmCreazioneFattura.getContentPane().add(txtImpTot);
                txtImpTot.setColumns(10);
               
                JButton btnDx = new JButton(">");
                btnDx.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                tblMatEx.removeAll();
                                tblMatP.removeAll();
                              
                                int index = listBolle.getSelectedIndex();
                                if(index != -1 ){ 
                                  listModelFatt.addElement(listBolle.getSelectedValue());
                                  dfmLs.removeElementAt(index);
                                  calcolaImp(_idB.get(index));
                                  _idFB.add(_idB.get(index));
                                  _idB.remove(index);
                                }
                                else JOptionPane.showMessageDialog(frmCreazioneFattura, "Non è stato selezionato niente!");
                        }
                });
               
                btnDx.setFont(new Font("Tahoma", Font.PLAIN, 8));
                btnDx.setBounds(269, 36, 40, 40);
                frmCreazioneFattura.getContentPane().add(btnDx);
               
                JButton btnSn = new JButton("<");
                btnSn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                tblMatEx.removeAll();
                                tblMatP.removeAll();
                               
                                int index = listFatt.getSelectedIndex() ;
                                if(index != -1 ){
                                  dfmLs.addElement(listFatt.getSelectedValue());
                                  listFatt.setSelectedIndex(-1);
                                  listModelFatt.removeElementAt(index);
                                  updCalcolaImp(_idFB.get(index));
                                  _idB.add(_idFB.get(index));
                                  _idFB.remove(index);
                                }
                                else JOptionPane.showMessageDialog(frmCreazioneFattura, "Non è stato selezionato niente!");
                        }
                });
                btnSn.setFont(new Font("Tahoma", Font.PLAIN, 8));
                btnSn.setBounds(269, 83, 40, 40);
                frmCreazioneFattura.getContentPane().add(btnSn);
        }
        
        private void loadTableMatEx(String idB){
            
            List<Extraconsumo> extraC = ResourceClass.getResources(Extraconsumo.class, Global._URLExtra+idB);
            if(extraC != null){
            Iterator<Extraconsumo> it = extraC.iterator();
            tblMatEx.removeAll();
            ((DefaultTableModel) tblMatEx.getModel()).setRowCount(0); //pulisce la table
           
            Iterator<Extraconsumo> itEx =extraC.iterator();
	        while(itEx.hasNext())
	        {   Extraconsumo exC = itEx.next();
	            String cod = exC.getCodiceArticolo();
	            String des = exC.getDescrizione();
	            Double qnt =  exC.getQuantita();
	            String udm = exC.getUdm();
	            Double cstU =  exC.getCosto();
	            String dt = exC.getDataRichiesta();
	            String g = "Giustificato";
	            //Se l'extraconsumo è ingiustificato lo sommo all'importo totale!
	            if(exC.getGiustificato() != 1)
	            	{	g = "Ingiustificato";
	            		mapIngImp.put(idB, qnt * cstU);
	            	}
	             //Aggiunge i valori alla tabella
	              ((DefaultTableModel) tblMatEx.getModel()).insertRow(
	                              tblMatEx.getRowCount(), new Object[]{cod,des,qnt, udm, cstU, g, dt});  
	        }
       }
    }
     
     private void loadTableMatP(String idB){
            List<MaterialeDaProdurre> lsMatP = ResourceClass.getResources(MaterialeDaProdurre.class, Global._URLMatDaProd1+idB);
            if(lsMatP != null){
            Iterator<MaterialeDaProdurre> it = lsMatP.iterator();
            tblMatP.removeAll();
            ((DefaultTableModel) tblMatP.getModel()).setRowCount(0); //pulisce la table
           
            Iterator<MaterialeDaProdurre> itP =lsMatP.iterator();
            while(itP.hasNext())
            {   MaterialeDaProdurre matP = itP.next();
                String cod= matP.getCodArt();
                String des = matP.getDescrizione();
                Double qnt =  matP.getQuantitaProdotta();
                String udm = matP.getUdm();                    
              //Aggiunge i valori alla tabella
               ((DefaultTableModel) tblMatP.getModel()).insertRow(
                                  tblMatP.getRowCount(), new Object[]{cod,des,qnt, udm});        
           }
        }
    }
     //Carica lista bolle da fatturare
     private void loadLsBoll(){
    	 List<Bolla> lista = ResourceClass.getResources(Bolla.class, Global._URLBollaTerz+idTerzista);
         Iterator<Bolla> it=lista.iterator();
         
         dfmLs = new DefaultListModel();
         while(it.hasNext())
         {
	             Bolla bolla = it.next();
		         String cdBolla = bolla.getCodice();
		         Fattura_Lavorazione chkFattBol = ResourceClass.getResource(Fattura_Lavorazione.class, Global._URLChkFattBol+bolla.getId());
		         if(!chkFattBol.isFatt()){
		        	 String dtBolla = FormatDate.getFormatDateH(bolla.getData());
			         dfmLs.addElement(cdBolla+"-"+dtBolla);
			         String idB = String.valueOf(bolla.getId());
			         _idB.add(idB);
		         }
         }
     }
     private void calcolaImp(String idBolla){
    	 Fattura_Lavorazione fatt = ResourceClass.getResource(Fattura_Lavorazione.class, Global._URLImpFattBol+idBolla);
         double imp = fatt.getTotImp2Bol();
         if(mapIngImp.containsKey(idBolla))
        	 imp += mapIngImp.get(idBolla);
         txtImpTot.setText("0.0");
         System.out.print(ImpFattBol);
         imp = calcolaIVA(imp);
         ImpFattBol += imp + Double.parseDouble(txtImpTot.getText());
         txtImpTot.setText(String.valueOf(ImpFattBol));
     }
     
     private void updCalcolaImp(String idBolla){
    	 Fattura_Lavorazione fatt = ResourceClass.getResource(Fattura_Lavorazione.class, Global._URLImpFattBol+idBolla);
         double imp = fatt.getTotImp2Bol();
         if(mapIngImp.containsKey(idBolla))
        	 imp += mapIngImp.get(idBolla);
         imp = calcolaIVA(imp);
         ImpFattBol = ImpFattBol - imp;
         txtImpTot.setText(String.valueOf(ImpFattBol));
     }
     
     private double calcolaIVA(double imp){
    	 imp = imp * 1.21;
    	 return imp;
     }

}

