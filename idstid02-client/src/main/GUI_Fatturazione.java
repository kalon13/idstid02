package main;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import classResources.Bolla;
import classResources.Extraconsumo;
import classResources.Fattura;
import classResources.Fattura_Lavorazione;
import classResources.Terzista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class GUI_Fatturazione {

        JFrame frmElenco;
        private JTable tableLav;
        private JTable tableExtra;
        private JTextField textNum;
        private JTextField textImpToT;
        private static GUI_RegistraDDT windowRegDDT;
        
        private static String[][] _dataLav;
        private static Object[] _titlesLav={"Num. Bolla","Lavorazione", "Prezzo"};
        private DefaultTableModel dfmLav;
        
        //Lista Fatt
        private static String[] _dataFat;
        private static int[] _idFat;
        private JList listFatt; 
        private Boolean isLoadFatt = false;
        
        private JButton btnNewFatt;
        private JPanel panel_terz;
        private JPanel panel_Fatt;
        
        // Lista e Dati Terzista
    	private static String[] _dataTerz = null;
        private static int[] _idTerz;
        private static int idTerzista = -1;
        private static JList list_terz;
        List<Terzista> listaTerz = null;
        private JLabel lblTerzisti;
        private JScrollPane scrollPane_Terz;
              
        public GUI_Fatturazione() {
        	    checkTerz_DT();
                initialize();
        }

        private void initialize() {
                frmElenco = new JFrame();
                frmElenco.addFocusListener(new FocusAdapter() {
                	@Override
                	public void focusGained(FocusEvent e) {
                		checkTerz_DT();
                	}
                });
                frmElenco.setTitle("Gestione Fatturazione");
                frmElenco.setBounds(100, 100, 1014, 395);
                frmElenco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmElenco.getContentPane().setLayout(null);
                
                panel_Fatt = new JPanel();
                panel_Fatt.setBounds(186, 11, 810, 308);
                frmElenco.getContentPane().add(panel_Fatt);
                panel_Fatt.setLayout(null);
               
                JLabel lblFatture = new JLabel("Fatture:");
                lblFatture.setBounds(0, 0, 46, 14);
                panel_Fatt.add(lblFatture);
               
                JScrollPane scrollPane_2 = new JScrollPane();
                scrollPane_2.setBounds(0, 21, 160, 196);
                panel_Fatt.add(scrollPane_2);
                             
                btnNewFatt = new JButton("Nuova Fattura");
                btnNewFatt.setBounds(0, 250, 160, 23);
                panel_Fatt.add(btnNewFatt);
                
                JPanel panel = new JPanel();
                panel.setBounds(166, 5, 634, 268);
                panel_Fatt.add(panel);
                panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                panel.setLayout(null);
                 
                JLabel lblFatturaN = new JLabel("Fattura n\u00B0:");
                lblFatturaN.setBounds(10, 11, 69, 14);
                panel.add(lblFatturaN);
                  
                JLabel lblImportoTotale = new JLabel("Importo Totale:");
                lblImportoTotale.setBounds(10, 39, 107, 14);
                panel.add(lblImportoTotale);
                   
                JLabel lblContenutoFattura = new JLabel("Contenuto Fattura:");
                lblContenutoFattura.setBounds(10, 75, 132, 14);
                panel.add(lblContenutoFattura);
                    
                JLabel lblExtraconsumi = new JLabel("Extraconsumi:");
                lblExtraconsumi.setBounds(215, 75, 84, 14);
                panel.add(lblExtraconsumi);
                     
			    JScrollPane scrollPane_1 = new JScrollPane();
			    scrollPane_1.setBounds(10, 100, 186, 157);
			    panel.add(scrollPane_1);
                      
                dfmLav = new DefaultTableModel(_dataLav, _titlesLav);
                tableLav = new JTable(dfmLav);
                tableLav.setEnabled(false);
                scrollPane_1.setViewportView(tableLav);
                tableLav.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                       
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(206, 102, 418, 155);
                panel.add(scrollPane);
                
                tableExtra = new JTable();
                tableExtra.setEnabled(false);
                tableExtra.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                        "Articolo", "Materiale", "Quantit\u00E0", "Udm", "Costo", "Giustificato", "Data"
                                }
                        ));
                tableExtra.getColumnModel().getColumn(3).setPreferredWidth(88);
                scrollPane.setViewportView(tableExtra);
             
                textNum = new JTextField();
                textNum.setEditable(false);
                textNum.setBounds(99, 8, 69, 20);
                panel.add(textNum);
                textNum.setColumns(10);
              
                textImpToT = new JTextField();
                textImpToT.setEditable(false);
                textImpToT.setBounds(99, 36, 86, 20);
                panel.add(textImpToT);
                textImpToT.setColumns(10);
               
                JButton btnEsci = new JButton("Esci");
                btnEsci.setBounds(711, 284, 89, 23);
                panel_Fatt.add(btnEsci);
                
                JButton button = new JButton("Stampa fattura in PDF");
                button.setBounds(0, 222, 160, 23);
                panel_Fatt.add(button);
                
                panel_terz = new JPanel();
                panel_terz.setBounds(20, 11, 156, 215);
                frmElenco.getContentPane().add(panel_terz);
                panel_terz.setLayout(null);
                            
                if(isLoadFatt == false)
             	   listFatt = new JList();
                else
                   listFatt = new JList(_dataFat);
                
                scrollPane_2.setViewportView(listFatt);
                listFatt.addListSelectionListener(new ListSelectionListener() {
                  public void valueChanged(ListSelectionEvent e) {
                  if (e.getValueIsAdjusting() == true)
                    { 
                 	 int k = listFatt.getSelectedIndex();
                 	 loadTableLavExt(k);                 	 
                    }
                  }
                 });
                
                    
                    JScrollPane scrollPane_terz = new JScrollPane();
                    scrollPane_terz.setBounds(0, 21, 156, 194);
                    panel_terz.add(scrollPane_terz);
                    
                	//**JList Terzisti**
                    list_terz = new JList(_dataTerz);
                    list_terz.setValueIsAdjusting(true);
                    list_terz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    list_terz.addListSelectionListener(new ListSelectionListener() {
                         public void valueChanged(ListSelectionEvent e) {
                            if (e.getValueIsAdjusting() == true)
                             { 
                               int k = list_terz.getSelectedIndex();
           	                   idTerzista = _idTerz[k]; 
           	                   loadDtTer(idTerzista);
                              }
                           }
                     
                      });

                    scrollPane_terz.setViewportView(list_terz);
                    
                    JLabel lblTerzisti = new JLabel("Terzisti:");
                    lblTerzisti.setBounds(0, 0, 46, 14);
                    panel_terz.add(lblTerzisti);
                    btnEsci.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                    frmElenco.dispose();
                            }
                    });
        btnNewFatt.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	if (chkBollFatt())
                	{	GUI_CreaFattura window = new GUI_CreaFattura();
                        window.frmCreazioneFattura.setVisible(true);
                	}
                	else JOptionPane.showMessageDialog(frmElenco, "Non ci sono bolle da fatturare!");
                }
        });
        /*********************Aggiunto menu*************************************/
		menu app = new menu(frmElenco, "Fat");
        checkTerz_VIS();
}
        
        /********Carica i dati della fattura e delle lavorazioni**********/
        private void loadTableLavExt(int k){
        	 //Pulisco la tabella extraC
        	 tableExtra.removeAll();
             ((DefaultTableModel) tableExtra.getModel()).setRowCount(0); 
             int id = _idFat[k];
             Fattura fatt = ResourceClass.getResource(Fattura.class, Global._URLFatt+"/"+id);
             textNum.setText(String.valueOf(fatt.getNumFattura()));
             textImpToT.setText(Double.toString(fatt.getImporto()));
             if(fatt.getFattLavorazione() != null)
             {
	             List<Fattura_Lavorazione> lsLav = fatt.getFattLavorazione();
	             Iterator<Fattura_Lavorazione> it=lsLav.iterator();
	             int cntDt = lsLav.size();
	             int cntTit = _titlesLav.length;
	             _dataLav = new String[cntDt][cntTit];
	             int t = 0;
	              while(it.hasNext())
	                  {
	                    Fattura_Lavorazione fattLv = it.next();
	                      if(t<cntDt){
	                       _dataLav[t][0] = fattLv.getCodBolla();
	                       _dataLav[t][1] = fattLv.getNomeLavorazione();
	                       _dataLav[t][2] = Double.toString(fattLv.getPrezzoLavorazione());
	                       t++;
	                       loadTableMatEx(String.valueOf(fattLv.getIdBolla()));
	                      }
	                    } 
	                     dfmLav.setDataVector(_dataLav, _titlesLav);
	           }
	             else 
	            	 dfmLav.setRowCount(0);
      }
        
        /*****Carica Dati terzista*****/
    	private void loadListaTerzisti(){
            //Load lista terzisti
    		listaTerz = ResourceClass.getResources(Terzista.class, Global._URLTerz);
            Iterator<Terzista> it = listaTerz.iterator();

            _dataTerz = new String[listaTerz.size()];
            _idTerz = new int[listaTerz.size()];
            int k = 0;
            while(it.hasNext())
    	        {  Terzista terCl = (Terzista)it.next();
    	            String ragSoc = String.valueOf(terCl.getRagioneSociale());
    	            _dataTerz[k] = ragSoc; 
    	            _idTerz[k]= terCl.getId();
    	            k++;
    	        }
    	}
    	
        /********Carica i dati della fattura**********/
        private void loadTableDt(int idTerzista){
            List<Fattura> lista = ResourceClass.getResources(Fattura.class, Global._URLFattTerz+idTerzista);
              Iterator<Fattura> it=lista.iterator();
              _dataFat = new String[lista.size()];
              _idFat = new int[lista.size()];
             int k = 0;
             while(it.hasNext())
              {
            	  Fattura fattCl = (Fattura)it.next();
	 	          String nmFatt = String.valueOf(fattCl.getNumFattura());
	 	          String date =fattCl.getDataEmissione();
	 	          date = FormatDate.getFormatDate(date);
	 	          _dataFat[k] = nmFatt+"-"+date;
	 	          _idFat[k]= fattCl.getId();
	 	          k++;
 	        }
             isLoadFatt = true;
         }
        
      /**** Se entra un opAz nn deve poter creare la fattura****/
      //modo di visualizzazione
    	private void checkTerz_VIS(){
    		if(Autenticazione.getSessione().getUtente().getTipo() != 5){
    		   btnNewFatt.setVisible(false);
    		}
    		else
    		{
    		  panel_terz.setVisible(false);	
    		  panel_Fatt.setBounds(20, 11, 820, 400);
    		  frmElenco.setBounds(100, 100, 850, 405);
    	    }
    	}
    	//caricamento dati
    	private void checkTerz_DT(){
    		if(Autenticazione.getSessione().getUtente().getTipo() != 5){
    			loadListaTerzisti();
    		}
    		else
    		{	loadListaTerzisti();
    			Terzista terzista = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+Autenticazione.getSessione().getUtente().getUserId()); 
       	 		idTerzista =  terzista.getId(); 
				loadTableDt(idTerzista);
    		}
    	}
    	
    	/**Carica dati quando clicco i terzisti nella lista**/
        private void loadDtTer(int idT){
        	loadTableDt(idTerzista);
    		listFatt.setListData(_dataFat);
        }
        
        /**Controlla se ci sono bolle da fatturare**/
        private boolean chkBollFatt(){
       	 List<Bolla> lista = ResourceClass.getResources(Bolla.class, Global._URLBollaTerz+idTerzista);
            Iterator<Bolla> it=lista.iterator();
            boolean chkBollFatt = false;
            
            while(it.hasNext())
            {
   	             Bolla bolla = it.next();
   		         String cdBolla = bolla.getCodice();
   		         Fattura_Lavorazione chkFattBol = ResourceClass.getResource(Fattura_Lavorazione.class, Global._URLChkFattBol+bolla.getId());
   		         if(!chkFattBol.isFatt()){
   		        	chkBollFatt = true;
   		        	break;
   		          }
            }
            return chkBollFatt;
        }
     /**Carica Tabella Extraconsumo**/   
     private void loadTableMatEx(String idB){
            List<Extraconsumo> extraC = ResourceClass.getResources(Extraconsumo.class, Global._URLExtra+idB);
            System.out.print( Global._URLExtra+idB);
            if(extraC != null){
            Iterator<Extraconsumo> itEx =extraC.iterator();
	        while(itEx.hasNext())
	        {   Extraconsumo exC = itEx.next();
	            String cod = exC.getCodiceArticolo();
	            String des = exC.getDescrizione();
	            Double qnt =  exC.getQuantita();
	            String udm = exC.getUdm();
	            Double cstU =  exC.getCosto();
	            String dt = exC.getDataRichiesta();
	            String g = "Ingiustificato";
	            if(exC.getGiustificato() == 1) g = "Giustificato";
	             //Aggiunge i valori alla tabella
	              ((DefaultTableModel) tableExtra.getModel()).insertRow(
	            		  tableExtra.getRowCount(), new Object[]{cod,des,qnt, udm, cstU, g, dt});  
	        }
       }
    }
     
}

