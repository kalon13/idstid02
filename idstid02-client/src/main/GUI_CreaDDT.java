package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;


import classResources.DDT;
import classResources.Materiale;
import classResources.MaterialeDDT;

import javax.swing.JButton;

//import com.sun.faces.facelets.util.Resource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GUI_CreaDDT {

        JFrame frmCreaDdt;
        private static String[] _titlesNewDDT = {"Codice", "Descrizione", "Quantità", "UdM"};
        private static Object[][] _dataNewDDT;
        private static String[] _titlesMat = {"Codice", "Descrizione", "Quantità", "UdM"};
    	private static Object[][] _dataMat;
        private static ArrayList<Integer> _idMatDDT;
        private static ArrayList<Integer> _idMat = new ArrayList<Integer>();
        private static int cntDt;
        private static String[] _comboNewDDT;
        List<TableCellEditor> editors ;
        private DefaultTableModel dfmMat;
        private DefaultTableModel dfmDDT;
        private JTable tableMat;
        private JTable tableDDT;
        private int idTerzista;
       
        public GUI_CreaDDT(int idTerz) {
        	_idMatDDT = new ArrayList<Integer>();
        	idTerzista = idTerz;
        	loadTableDt(idTerz);
            initialize();
        }
       
        private void initialize() {
                frmCreaDdt = new JFrame();
                frmCreaDdt.setTitle("Invia DDT");
                frmCreaDdt.setBounds(100, 100, 823, 377);
                frmCreaDdt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmCreaDdt.getContentPane().setLayout(null);
                
                JPanel panel = new JPanel();
                panel.setBounds(24, 41, 765, 289);
                frmCreaDdt.getContentPane().add(panel);
                panel.setLayout(null);
                
                /****  Modelli delle Table   ***/
                dfmMat = new DefaultTableModel (_dataMat,_titlesMat){
        			boolean[] columnEditables = new boolean[]{
        			false,false,false
        			};
        			public boolean isCellEditable(int row, int column){
        			    return false;	
        			}
        		};
        		
                dfmDDT = new DefaultTableModel (_dataNewDDT,_titlesNewDDT){
        			boolean[] columnEditables = new boolean[]{
        			false,false,true,false
        			};
        			public boolean isCellEditable(int row, int column){
        			    return columnEditables[column];	
        			}
        		};
                
                JButton btnAggiungi = new JButton("Aggiungi");
                btnAggiungi.setBounds(328, 11, 99, 23);
                panel.add(btnAggiungi);
                 
               JButton btnRimuovi = new JButton("Rimuovi");
               btnRimuovi.setBounds(328, 42, 99, 23);
               panel.add(btnRimuovi);
              
               JButton btnCrea = new JButton("Nuovo DDT");
               btnCrea.setBounds(531, 255, 89, 23);
               panel.add(btnCrea);
                   
                JButton btnAnnulla = new JButton("Annulla");
                btnAnnulla.setBounds(630, 255, 89, 23);
                panel.add(btnAnnulla);
                
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(20, 11, 298, 233);
                panel.add(scrollPane);
                
                tableMat = new JTable(dfmMat);
                scrollPane.setViewportView(tableMat);
                
                JScrollPane scrollPane_1 = new JScrollPane();
                scrollPane_1.setBounds(437, 11, 318, 233);
                panel.add(scrollPane_1);
                
                tableDDT = new JTable(dfmDDT);
                scrollPane_1.setViewportView(tableDDT);
                     
                 JLabel lblMaterialiDaInserire = new JLabel("Elenco Materiali:");
                 lblMaterialiDaInserire.setBounds(25, 13, 153, 14);
                 frmCreaDdt.getContentPane().add(lblMaterialiDaInserire);
                     
                     JLabel label = new JLabel("Materiali da inserire nel DDT:");
                     label.setBounds(461, 13, 153, 14);
                     frmCreaDdt.getContentPane().add(label);
                    btnAnnulla.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                    frmCreaDdt.dispose();
                            }
                    });
                    //TODO
                   btnCrea.addMouseListener(new MouseAdapter() {
                           @Override
                           public void mouseClicked(MouseEvent e) {
                        	   int result = JOptionPane.showConfirmDialog(frmCreaDdt, "Si vuole confermare l'invio del DDT all'azienda?");
                        	   if(JOptionPane.YES_OPTION == result){
                        		   String today = FormatDate.getToday();
                        		   double qtaMat = 0;
                        		   Materiale m = null;
                        		   String id = null;
                        		   ///check value of qnt
                        		   boolean flg_ckqnt = false;
                        		   for(int row = 0; row<tableDDT.getRowCount(); row++){
                        			  int idMat = _idMatDDT.get(row);
                        			  qtaMat = Double.valueOf(tableDDT.getValueAt(row, 2).toString());
                        		      m = ResourceClass.getResource(Materiale.class, Global._URLMag+idMat+"/"+idTerzista);
                        		      System.out.print(m.getQuantita()+"+"+qtaMat);
                        		      if(qtaMat < m.getQuantita()){
                        		    	  flg_ckqnt = true;
                        		    	  break;
                        		    	  }
                        		   }
                        		   if(flg_ckqnt == false) 
                        		   {
                                     DDT ddt = new DDT(today, idTerzista, true, false);
                                     id  = ResourceClass.addResources(Global._URLddt, ddt);
                                     for(int row = 0; row<tableDDT.getRowCount(); row++){
                                	   qtaMat = Double.valueOf(tableDDT.getValueAt(row, 2).toString());
                                	   int idMat = _idMatDDT.get(row);
                                	   int idDDT = Integer.valueOf(id);
                                	   MaterialeDDT mat = new MaterialeDDT(idDDT,idMat,qtaMat, idTerzista);
                                	   String idmD = ResourceClass.addResources(Global._URLddtInsMat, mat);
                                     }
                        		   }
                                     else 
                                    	 JOptionPane.showMessageDialog(frmCreaDdt, "La quantità da spedire nel DDT "+qtaMat+" supera la quantità in magazzino "+m.getQuantita());
                                   if(id != "-1")
                                	   JOptionPane.showMessageDialog(frmCreaDdt, "Il DDT è stato creato e inviato all'azienda!");
                             }
                         }
                   });
                  btnRimuovi.addMouseListener(new MouseAdapter() {
                          @Override
                          public void mouseClicked(MouseEvent e) {
                        	  if(tableDDT.getSelectedRow() != -1){
                           		int rowMat = tableDDT.getSelectedRow();
                           		dfmDDT.removeRow(rowMat);
                           		_idMatDDT.remove(rowMat);
                               }
                              	 else
                                     JOptionPane.showMessageDialog(frmCreaDdt, "Non è stato selezionato il materiale da rimuovere dal DDT!");
                               }
                  });
                 btnAggiungi.addMouseListener(new MouseAdapter() {
                         @Override
                         public void mouseClicked(MouseEvent e) {
                          if(tableMat.getSelectedRow() != -1){
                     		int rowMat = tableMat.getSelectedRow();
                     		String cod = (String) tableMat.getValueAt(rowMat, 0);
							String desc = (String) tableMat.getValueAt(rowMat, 1);
                     		double qtaMat = 0.0;
                     		String udm = (String) tableMat.getValueAt(rowMat, 3);
                     		dfmDDT.insertRow(
                     				tableDDT.getRowCount(), new Object[]{cod, desc, qtaMat, udm});
                     		_idMatDDT.add(_idMat.get(rowMat));
                        	 }
                        	 else
                               JOptionPane.showMessageDialog(frmCreaDdt, "Non è stato selezionato il materiale da aggiungere al DDT!");
                         }
                 });
              //dfm = new DefaultTableModel(_dataNewDDT, _titlesNewDDT);
        }
        
     /*****Carica tabella materiali*****/
    private void loadTableDt(int idTerz){
    		List<Materiale> lista = null;
    		lista = ResourceClass.getResources(Materiale.class, Global._URLMagMatTerz+idTerz);
    		if(lista!=null){
        	  Iterator<Materiale> it=lista.iterator();
    	      int cntDt = lista.size();
    	      _dataMat = new String[cntDt][4];
    	      int k = 0;
    	    while(it.hasNext())
            {Materiale mtCl = it.next();
              if(k<cntDt)
              {  _dataMat[k][1] = mtCl.getDescrizione();
                _dataMat[k][0] = String.valueOf(mtCl.getCodice());
                _dataMat[k][2] = String.valueOf(mtCl.getQuantita());
                _dataMat[k][3] = String.valueOf(mtCl.getudm());
                _idMat.add(mtCl.getId());
                k++;
              }
            }
          }
    	}
}

