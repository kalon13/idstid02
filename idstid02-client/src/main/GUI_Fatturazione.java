package main;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
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

import classResources.Fattura;
import classResources.Fattura_Lavorazione;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GUI_Fatturazione {

        JFrame frmElenco;
        private JTable tableLav;
        private JTable tableExtra;
        private JTextField textNum;
        private JTextField textImpToT;
        private static GUI_RegistraDDT windowRegDDT;
        private static String[] _data;
        private static int[] _id;
        private static String[][] _dataLav;
        private static Object[] _titlesLav={"Lavorazione", "Prezzo"};
        private DefaultTableModel dfmLav;
        private JButton btnNewFatt;
        private JPanel panel_terz;
        private JPanel panel_Fatt;
       
              
        public GUI_Fatturazione() {
        		loadTableDt();
                initialize();
        }

        private void initialize() {
                frmElenco = new JFrame();
                frmElenco.setTitle("Gestione Fatturazione");
                frmElenco.setBounds(100, 100, 898, 368);
                frmElenco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmElenco.getContentPane().setLayout(null);
                
                panel_Fatt = new JPanel();
                panel_Fatt.setBounds(186, 11, 687, 308);
                frmElenco.getContentPane().add(panel_Fatt);
                panel_Fatt.setLayout(null);
               
                JLabel lblFatture = new JLabel("Fatture:");
                lblFatture.setBounds(0, 0, 46, 14);
                panel_Fatt.add(lblFatture);
               
               JScrollPane scrollPane_2 = new JScrollPane();
               scrollPane_2.setBounds(0, 21, 160, 196);
               panel_Fatt.add(scrollPane_2);
                
               final JList listFatt = new JList(_data);
               scrollPane_2.setViewportView(listFatt);
               listFatt.addListSelectionListener(new ListSelectionListener() {
                 public void valueChanged(ListSelectionEvent e) {
                	 int k = listFatt.getSelectedIndex();
                	 loadTableLavExt(k);
                 }
                    
                });
               
                btnNewFatt = new JButton("Nuova Fattura");
                btnNewFatt.setBounds(444, 251, 160, 23);
                panel_Fatt.add(btnNewFatt);
                
                 JPanel panel = new JPanel();
                 panel.setBounds(166, 5, 509, 240);
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
                     lblExtraconsumi.setBounds(262, 76, 84, 14);
                     panel.add(lblExtraconsumi);
                     
                      JScrollPane scrollPane_1 = new JScrollPane();
                      scrollPane_1.setBounds(10, 100, 230, 127);
                      panel.add(scrollPane_1);
                      
                       tableLav = new JTable(dfmLav);
                       scrollPane_1.setViewportView(tableLav);
                       tableLav.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                       
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setBounds(265, 102, 230, 127);
                        panel.add(scrollPane);
                        
                         tableExtra = new JTable();
                         scrollPane.setViewportView(tableExtra);
                         
                          textNum = new JTextField();
                          textNum.setEditable(false);
                          textNum.setBounds(89, 8, 69, 20);
                          panel.add(textNum);
                          textNum.setColumns(10);
                          
                           textImpToT = new JTextField();
                           textImpToT.setEditable(false);
                           textImpToT.setBounds(158, 36, 86, 20);
                           panel.add(textImpToT);
                           textImpToT.setColumns(10);
                           
                            JButton btnEsci = new JButton("Esci");
                            btnEsci.setBounds(598, 285, 89, 23);
                            panel_Fatt.add(btnEsci);
                            
                            JButton button = new JButton("Stampa fattura in PDF");
                            button.setBounds(0, 222, 160, 23);
                            panel_Fatt.add(button);
                            
                            panel_terz = new JPanel();
                            panel_terz.setBounds(20, 11, 156, 215);
                            frmElenco.getContentPane().add(panel_terz);
                            panel_terz.setLayout(null);
                            
                            JScrollPane scrollPane_3 = new JScrollPane();
                            scrollPane_3.setBounds(0, 21, 156, 194);
                            panel_terz.add(scrollPane_3);
                            
                            JList list_terz = new JList();
                            scrollPane_3.setRowHeaderView(list_terz);
                            
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
                                GUI_CreaFattura window = new GUI_CreaFattura();
                                window.frmCreazioneFattura.setVisible(true);
                        }
                });
                
                dfmLav=new DefaultTableModel (_dataLav, _titlesLav);
                checkTerz_VIS();
        }
        
        /********Carica i dati della fattura e delle lavorazioni**********/
        private void loadTableLavExt(int k){
             int id = _id[k];
             Fattura fatt = ResourceClass.getResource(Fattura.class, Global._URLFatt+"/"+id);
             System.out.print(fatt.getNumFattura());
             if(fatt.getNumFattura() != 0)
             textNum.setText(String.valueOf(fatt.getNumFattura()));
             textImpToT.setText(Double.toString(fatt.getImporto()));
             System.out.print(fatt.getFattLavorazione());
             if(fatt.getFattLavorazione() != null){
             List<Fattura_Lavorazione> lsLav = fatt.getFattLavorazione();
             Iterator<Fattura_Lavorazione> it=lsLav.iterator();
             int cntDt = lsLav.size();
             int cntTit = _titlesLav.length;
             _dataLav = new String[cntDt][cntTit];
             int t = 0;
              while(it.hasNext())
                  {
                    Fattura_Lavorazione fattLv = it.next();
                      if(k<cntDt){
                       _dataLav[k][0] = fattLv.getNomeLavorazione();
                       _dataLav[k][1] = Double.toString(fattLv.getPrezzoLavorazione());
                       k++;
                      }
                    }
                      dfmLav.setDataVector(_dataLav, _titlesLav);
                    }
             else 
            	 dfmLav.setRowCount(0);
        }
        
        /********Carica i dati della fattura**********/
        private void loadTableDt(){
            List<Fattura> lista = ResourceClass.getResources(Fattura.class, Global._URLFatt);
              Iterator<Fattura> it=lista.iterator();
              _data = new String[lista.size()];
              _id = new int[lista.size()];
             int k = 0;
             while(it.hasNext())
              {
               Fattura fattCl = (Fattura)it.next();
 	          String nmFatt = String.valueOf(fattCl.getNumFattura());
 	          String date =fattCl.getDataEmissione();
 	          date = FormatDate.getFormatDate(date);
 	          _data[k] = nmFatt+"-"+date;
 	          _id[k]= fattCl.getId();
 	          k++;
 	        }
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
    		  panel_Fatt.setBounds(20, 11, 687, 308);
    		  frmElenco.setBounds(100, 100, 800, 368);
    	    }
    	}
}

