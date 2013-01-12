package gui;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;

//import com.sun.faces.facelets.util.Resource;

import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

import main.Consumo;
import main.DDT;
import main.Materiale;
import main.MaterialeDDT;

import utils.FormatDate;
import utils.Global;
import utils.ResourceClass;

public class GUI_CreaDDT {

        JFrame frmCreaDdt;
        private static String[] _titlesNewDDT = {"Codice", "Descrizione", "Quantitï¿½", "UdM"};
        private static String[][] _dataNewDDT;
        private static String[] _titlesMat = {"Codice", "Descrizione", "Quantitï¿½", "UdM"};
    	private static String[][] _dataMat;
        private static ArrayList<Integer> _idMatDDT;
        private static ArrayList<Integer> _idMat = new ArrayList<Integer>();
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
                frmCreaDdt.setResizable(false);
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
                	Class[] columnTypes = new Class[] {
                			Object.class, Object.class, Double.class, Object.class
            			};
                	public Class getColumnClass(int columnIndex) {
            				return columnTypes[columnIndex];
            			}
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
                tableMat.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                scrollPane.setViewportView(tableMat);
                
                JScrollPane scrollPane_1 = new JScrollPane();
                scrollPane_1.setBounds(437, 11, 318, 233);
                panel.add(scrollPane_1);
                
                tableDDT = new JTable(dfmDDT);
                tableDDT.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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
                        	 if (isQntDDTPositive()){
	                        	   int result = JOptionPane.showConfirmDialog(frmCreaDdt, "Si vuole confermare l'invio del DDT all'azienda?");
	                        	   if(JOptionPane.YES_OPTION == result){
	                        		   String today = FormatDate.getToday();
	                        		   double qtaMat = 0; double qntupdMat = 0;
	                        		   Materiale m = null; List<Consumo> semLav = null;
	                        		   ///check value of qnt
	                        		   boolean flg_ckqnt = false;
	                        		   boolean flg_ckqntCons = false;
	                        		   boolean flg_matPrMag = true;
	                        		   for(int row = 0; row<tableDDT.getRowCount(); row++){
	                        			  int idMat = _idMatDDT.get(row);
	                        			  qtaMat = Double.valueOf(tableDDT.getValueAt(row, 2).toString());
	                        			  m = ResourceClass.getResource(Materiale.class, Global._URLMag+"/"+idMat+"/"+idTerzista);
	                        			  qntupdMat = m.getQuantita();
	                        			  //check qnt da inviare ï¿½ min di quella in mag
	                        		     if(qntupdMat < qtaMat ){
	                        		    	  flg_ckqnt = true;
	                        		    	  break;
	                        		    	  }
	                        		     //check se il tipo di mat da inv ï¿½ un semil in caso verifico che le mat prime consumate nella lav siano in mag
	                        		     if(m.getTipo().equals("SL")){
		   									 semLav = ResourceClass.getResources(Consumo.class, Global._URLConsMat+idMat);
		   									 if(semLav != null){
		   									  Iterator<Consumo> it=semLav.iterator();
		   									 while(it.hasNext())
		   							          {  Consumo c = it.next();
		   							          	 //la quantita del consumo ï¿½ unitaria la moltiplico per il valore inserite da spedire tramite DDT
		   							             qntupdMat = c.getQuantita() * qtaMat;
		   							             int idMatC = c.getMatPrima();
		   							             Materiale mat = ResourceClass.getResource(Materiale.class, Global._URLMag+"/"+idMatC+"/"+idTerzista);
		   							             if(mat != null){
		   							               if(mat.getQuantita() < qntupdMat ){
		   							               //metto la qnt in qtaMat per farla apparire nel messaggio nn lo faccio sopra altrimenti la prox iteraz dello while ï¿½ falsata
		   							          	   qtaMat = mat.getQuantita();
		                        		    	   flg_ckqntCons = true;
		                        		    	   break;
		                        		    	  }
		   							             }
		   							             else{
		   							            	flg_matPrMag = false; flg_ckqntCons = true;
			   										JOptionPane.showMessageDialog(frmCreaDdt, "La materia prima necessaria per la produzione del semilavorato non ï¿½ prensente in magazzino!");
		   							            	 break;
		   							             }
		   									  }
		   									}
		   									  else {
		   										flg_matPrMag = false; flg_ckqntCons = true;
		   										JOptionPane.showMessageDialog(frmCreaDdt, "La materia prima necessaria per la produzione del semilavorato non ha una valore di consumo!");
		   									}
	                        		     }
	                        		   }
	                        		   //INSERT in DDT e UPDATE in MatTerz e in DDTMat 
	                        		   if(flg_ckqnt == false && flg_ckqntCons == false) 
	                        		   {
	                                     DDT ddt = new DDT(today, idTerzista, true, false);
	                                     String id  = ResourceClass.addResources(Global._URLddt, ddt);
	                                     for(int row = 0; row<tableDDT.getRowCount(); row++){
	                                       qtaMat = Double.valueOf(tableDDT.getValueAt(row, 2).toString());
	                                  	   int idMat = _idMatDDT.get(row);
	                                  	   int idDDT = Integer.valueOf(id);                                  
	                                   	   MaterialeDDT mat = new MaterialeDDT(idDDT,idMat,qtaMat, idTerzista);
	                                	   String idmD = ResourceClass.addResources(Global._URLddtInsMat, mat);
	                                	   //Se il materiale ï¿½ un semilavorato scalo dal magaz anche le qnt d mat prime consumate per la sua lavorazione
		   									if(m.getTipo().equals("SL")){
		   									  semLav = ResourceClass.getResources(Consumo.class, Global._URLConsMat+idMat);
		   							      	  Iterator<Consumo> it=semLav.iterator();
		   									 while(it.hasNext())
		   							          {  Consumo c = it.next();
		   										 String idMP = String.valueOf(c.getMatPrima());
		   										 c.setIdTerzista(idTerzista);
		   										 ResourceClass.updResources(Consumo.class, Global._URLMagUpdMat, idMP, c);
		   									  }
		   									}
	                                     }
	                                     if(id != "-1")
	                                     {
	                                  	   loadTableDt(idTerzista);
	                                  	   dfmMat.setDataVector(_dataMat, _titlesMat);
	                                  	   JOptionPane.showMessageDialog(frmCreaDdt, "Il DDT ï¿½ stato creato e inviato all'azienda!");
	                                     }
	                        		   }
	                                     else{ 
	                                    	 if(flg_matPrMag == true)
	                                    	   JOptionPane.showMessageDialog(frmCreaDdt, "La quantitï¿½ da spedire nel DDT "+qtaMat+" supera la quantitï¿½ in magazzino "+qntupdMat);
	                                    	 }
	                        		 
	                         }
                           }
                           else
                   			JOptionPane.showMessageDialog(frmCreaDdt, "La quantita inserita non ï¿½ corretta!");
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
                                     JOptionPane.showMessageDialog(frmCreaDdt, "Non ï¿½ stato selezionato il materiale da rimuovere dal DDT!");
                               }
                  });
                 btnAggiungi.addMouseListener(new MouseAdapter() {
                         @Override
                         public void mouseClicked(MouseEvent e) {
                          if(tableMat.getSelectedRow() != -1){
                     		int rowMat = tableMat.getSelectedRow();
                     		String cod = (String) tableMat.getValueAt(rowMat, 0);
                     		if(!isInDDT(cod)){
								String desc = (String) tableMat.getValueAt(rowMat, 1);
	                     		double qtaMat = 0.0;
	                     		String udm = (String) tableMat.getValueAt(rowMat, 3);
	                     		dfmDDT.insertRow(
	                     				tableDDT.getRowCount(), new Object[]{cod, desc, qtaMat, udm});
	                     		_idMatDDT.add(_idMat.get(rowMat));
                     		}
                     		else
                     			JOptionPane.showMessageDialog(frmCreaDdt, "Il materiale è già stato inserito nel DDT!");
                        	 }
                        	 else
                               JOptionPane.showMessageDialog(frmCreaDdt, "Non ï¿½ stato selezionato il materiale da aggiungere al DDT!");
                         }
                 });
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
              { _dataMat[k][1] = mtCl.getDescrizione();
                _dataMat[k][0] = String.valueOf(mtCl.getCodice());
                _dataMat[k][2] = String.valueOf(mtCl.getQuantita());
                _dataMat[k][3] = String.valueOf(mtCl.getudm());
                _idMat.add(mtCl.getId());
                k++;
              }
            }
          }
    	}
    private boolean isQntDDTPositive(){
    	for(int row =0; row< tableDDT.getRowCount(); row++){
    		try{
    			Double qnt =Double.parseDouble(String.valueOf(tableDDT.getValueAt(row, 2)));
    			if(qnt <= 0){
    	    		return false;
    	    		}
    		}
    		catch(Exception ex){
    			System.out.print(ex);
    		}
    	}
	   return true;
    }
    private boolean isInDDT(String cod){
		for(int row =0; row< tableDDT.getRowCount(); row++)
		{	String cdDDT = (String.valueOf(tableDDT.getValueAt(row, 0)));
			if(cdDDT.equals(cod))
				return true;
		}
    	return false;
    }
}

