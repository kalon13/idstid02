package gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import main.*;
import utils.*;

import javax.swing.JButton;

//import com.sun.faces.facelets.util.Resource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GUI_CreaBollaDDT {

        JFrame frmCreaDdt;
        private static String[] _titlesNewDDT = {"Codice", "Descrizione", "Quantità", "UdM"};
        private static Object[][] _dataNewDDT;
        private static ArrayList<Integer> _idMatDDT;
        private DefaultTableModel dfmDDT;
        private JTable tableDDT;
        private int idTerzista;
        private int idBolla;
       
        public GUI_CreaBollaDDT(int idTerz, int idBolla) {
        	_idMatDDT = new ArrayList<Integer>();
        	idTerzista = idTerz;
        	this.idBolla = idBolla;
        	loadTableDt(idTerz);
            initialize();
        }
       
        private void initialize() {
                frmCreaDdt = new JFrame();
                frmCreaDdt.setTitle("DDT Inviato");
                frmCreaDdt.setBounds(100, 100, 491, 335);
                frmCreaDdt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmCreaDdt.getContentPane().setLayout(null);
                
                JPanel panel = new JPanel();
                panel.setBounds(22, 43, 426, 253);
                frmCreaDdt.getContentPane().add(panel);
                panel.setLayout(null);
                
                /****  Modelli delle Table   ***/
               dfmDDT = new DefaultTableModel (_dataNewDDT,_titlesNewDDT){
        			boolean[] columnEditables = new boolean[]{
        			false,false,false,false
        			};
        			public boolean isCellEditable(int row, int column){
        			    return columnEditables[column];	
        			}
        		};
                
                JScrollPane scrollPane_1 = new JScrollPane();
                scrollPane_1.setBounds(45, 11, 370, 233);
                panel.add(scrollPane_1);
                
                tableDDT = new JTable(dfmDDT);
                scrollPane_1.setViewportView(tableDDT);
                     
                    JLabel lblMaterialiDelDdt = new JLabel("Materiali del DDT inviato:");
                    lblMaterialiDelDdt.setBounds(22, 18, 153, 14);
                    frmCreaDdt.getContentPane().add(lblMaterialiDelDdt);
                    //crea il DDT
                    creaDDT();
        }
    
    
     /*****Carica tabella materiali*****/
	private void loadTableDt(int idTerz){
    	List<MaterialeDaProdurre> listaMDaProd = null;
    	listaMDaProd = ResourceClass.getResources(MaterialeDaProdurre.class, Global._URLMatDaProd1+idBolla);
        Iterator<MaterialeDaProdurre> it = listaMDaProd.iterator();
        int cntDt = listaMDaProd.size();
        _dataNewDDT = new String[cntDt][4];
        int k=0;
        
        while(it.hasNext())
        {   MaterialeDaProdurre matCl = (MaterialeDaProdurre)it.next();
            if(matCl.getQuantitaProdotta() - matCl.getQuantitaSpedita() != 0){
            _dataNewDDT[k][0] = matCl.getCodArt();
            _dataNewDDT[k][1] = matCl.getDescrizione();
            _dataNewDDT[k][2] = String.valueOf(matCl.getQuantitaProdotta() - matCl.getQuantitaSpedita());
            _dataNewDDT[k][3] = matCl.getUdm();
            _idMatDDT.add(matCl.getId_materiale());
            k++;
            }
        }
    }
    
    private void creaDDT(){
    	String today = FormatDate.getToday();
		   double qtaMat = 0; double qntupdMat = 0;
		   Materiale m = null; List<Consumo> semLav = null;
		   ///check value of qnt
		   boolean flg_ckqnt = false;
		   boolean flg_ckqntCons = false;
		   boolean flg_matPrMag = true;
		   for(int row = 0; row<tableDDT.getRowCount(); row++){
			if(row < _idMatDDT.size()){
			  int idMat = _idMatDDT.get(row);
			  qtaMat = Double.valueOf(tableDDT.getValueAt(row, 2).toString());
			  m = ResourceClass.getResource(Materiale.class, Global._URLMag+"/"+idMat+"/"+idTerzista);
			  qntupdMat = m.getQuantita();
		     if(qntupdMat < qtaMat ){
		    	  flg_ckqnt = true;
		    	  break;
		    	  }
		     }
		   }
		   //INSERT in DDT e UPDATE in MatTerz e in DDTMat 
		   if(flg_ckqnt == false) 
		   {
          DDT ddt = new DDT(today, idTerzista, true, false);
          String id  = ResourceClass.addResources(Global._URLddt, ddt);
          for(int row = 0; row<tableDDT.getRowCount(); row++){
           if(row < _idMatDDT.size()){
               qtaMat = Double.valueOf(tableDDT.getValueAt(row, 2).toString());
       	       int idMat = _idMatDDT.get(row);
       	       int idDDT = Integer.valueOf(id);                                  
        	   MaterialeDDT mat = new MaterialeDDT(idDDT,idMat,qtaMat, idTerzista);
     	       String idmD = ResourceClass.addResources(Global._URLddtInsMat, mat);
           }
          }
          if(id != "-1")
          {
       	   loadTableDt(idTerzista);
       	  // dfmMat.setDataVector(_dataMat, _titlesMat);
       	   JOptionPane.showMessageDialog(frmCreaDdt, "Il DDT è stato creato e inviato all'azienda!");
          }
		   }
          else{ 
         	 if(flg_matPrMag == true)
         	   JOptionPane.showMessageDialog(frmCreaDdt, "La quantità da spedire nel DDT "+qtaMat+" supera la quantità in magazzino "+qntupdMat);
         	 }
		   }
    }

