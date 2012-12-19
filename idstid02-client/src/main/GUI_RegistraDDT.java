package main;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import classResources.DDT;
import classResources.Fattura;
import classResources.Fattura_Lavorazione;
import classResources.Materiale;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class GUI_RegistraDDT {

        JFrame frmRegistraDdt;
        private static GUI_RegistraDDT windowRegDDT;
        
        // TABLE MATERIALE
        private JTable tblMateriale;
        private static int[] _idMat;
        private DefaultTableModel dfm;
        
        // TABLE DDT
        private DefaultListModel modelLsDDT;
        private static int[] _idLsDDT = null;
        private static Object[] _titlesDDT={"Codice", "Descrizione", "Quantità", "UDM"};
        private static String[][] _dataDDT;
        
        private int idTerz;
      
        public GUI_RegistraDDT() {
        	 idTerz = Autenticazione.getSessione().getUtente().getUserId();
             loadLstDDT(idTerz);
             initialize();
        }
                /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                frmRegistraDdt = new JFrame();
                frmRegistraDdt.setResizable(false);
                frmRegistraDdt.setTitle("Registra DDT");
                frmRegistraDdt.setBounds(100, 100, 417, 301);
                frmRegistraDdt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmRegistraDdt.getContentPane().setLayout(null);
               
                JPanel panel = new JPanel();
                panel.setBounds(18, 11, 390, 257);
                frmRegistraDdt.getContentPane().add(panel);
                panel.setLayout(null);
               
                JScrollPane scrollPane_Mat = new JScrollPane();
                scrollPane_Mat.setFocusTraversalPolicyProvider(true);
                scrollPane_Mat.setFocusTraversalKeysEnabled(false);
                scrollPane_Mat.setBounds(2, 113, 377, 106);
                panel.add(scrollPane_Mat);
               
                tblMateriale = new JTable();
                tblMateriale.setRowSelectionAllowed(false);
                tblMateriale.setEnabled(false);
                scrollPane_Mat.setViewportView(tblMateriale);
               
                JScrollPane scrollPane_DDT = new JScrollPane();
                scrollPane_DDT.setBounds(2, 0, 377, 100);
                panel.add(scrollPane_DDT);
               
                final JList listDDT = new JList(modelLsDDT);
                listDDT.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent e) {
                                int k = listDDT.getSelectedIndex();
                                if(k!=-1){
                                loadTblDDT(_idLsDDT[k]);
                                dfm=new DefaultTableModel (_dataDDT, _titlesDDT);
                                tblMateriale.setModel(dfm);
                          }
                        }
                });
                scrollPane_DDT.setViewportView(listDDT);
               
                JButton btnRegistra = new JButton("Registra");
                btnRegistra.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                        	if(listDDT.getSelectedIndex() != -1)
                        	{   
                                int i = listDDT.getSelectedIndex();
                                int idDDT = _idLsDDT[i];
                                DDT ddt = ResourceClass.getResource(DDT.class, Global._URLddt+"/"+idDDT);
                                List<Materiale> lsMatDDT = ddt.getDdtMateriale();
                                if(lsMatDDT != null){
                                Iterator<Materiale> it=lsMatDDT.iterator();
                                while(it.hasNext())
                                {
		                         Materiale mtDDT = it.next();
		                         int idMat = mtDDT.getId();
		                         Materiale mat = ResourceClass.getResource(Materiale.class, Global._URLMag+"/"+idMat+
		                                     "/"+idTerz);                          
		                         if(mat != null)
		                         {  //update
		                            double qnt = mat.getQuantita() + mtDDT.getQuantita();  
		                            mat.setQuantita(qnt);
		                                    ResourceClass.updResources(Materiale.class, Global._URLMag,
		                                            String.valueOf(mat.getId_matTerz()), mat);
		                             }
		                             else{
		                                  //TODO Insert terzista! int id, double quantita, int id_terzista
		                                  Materiale m1 = new Materiale(mtDDT.getId(),mtDDT.getQuantita(), idTerz);
		                                  String id = ResourceClass.addResources("/magazzinoterzista", m1);
		                                  m1.setId(Integer.parseInt(id));
		                            }
                                }
                               }
                                ResourceClass.updResources(DDT.class, Global._URLddt, String.valueOf(idDDT), ddt);
                                int idTerz = Autenticazione.getSessione().getUtente().getUserId();
                                loadLstDDT(idTerz);
                                listDDT.setSelectedIndex(-1);
                                deleteAllRowTable();
                                listDDT.setModel(modelLsDDT);
                                JOptionPane.showMessageDialog(frmRegistraDdt , "Il DDT è stato registrato!");
                       } 
                        	else JOptionPane.showMessageDialog(frmRegistraDdt , "Non è stato selezionato il DDT da registrare!");
                    }
                });
                btnRegistra.setBounds(195, 229, 89, 23);
                panel.add(btnRegistra);
               
                JButton btnAnnulla = new JButton("Annulla");
                btnAnnulla.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                frmRegistraDdt.dispose();
                        }
                });
                btnAnnulla.setBounds(294, 230, 89, 23);
                panel.add(btnAnnulla);
        }
        
        private void deleteAllRowTable(){
                int numRows = dfm.getRowCount()-1;
                for (int i=numRows;i>=0;i--) {
                  dfm.removeRow(i);
                }
        }

        /***********Carica i dati del DDT*******/
        private void loadLstDDT(int idTer){
              List<DDT> lsDDT = ResourceClass.getResources(DDT.class, Global._URLddtNnReg+idTer);
              Iterator<DDT> it=lsDDT.iterator();
              modelLsDDT = new DefaultListModel();
              int cntDt = lsDDT.size();
         	  _idLsDDT = new int[cntDt];
              int t = 0;
              while(it.hasNext())
                {
                  DDT ddt = it.next();
                  String dtInvio = FormatDate.getFormatDate(ddt.getDataInvio());
                  modelLsDDT.addElement(ddt.getNumDoc()+" - data invio: "+dtInvio);
                  _idLsDDT[t] = ddt.getId();
                  t++;
             }
         }

        /***********Carica i materiali del DDT*******/
        private void loadTblDDT(int idDDT){
            DDT ddt = ResourceClass.getResource(DDT.class, Global._URLddt+"/"+idDDT);
            _dataDDT = null;
             if(ddt!=null){
                List<Materiale> lsMtddt = ddt.getDdtMateriale();
              if(lsMtddt!=null){  
            	  Iterator<Materiale> it=lsMtddt.iterator();
        	      int cntDt = lsMtddt.size();
        	      int cntTit = _titlesDDT.length;
        	      _dataDDT = new String[cntDt][cntTit];
        	      _idMat = new int[cntDt];
        	      int k = 0;  	      
        	     while(it.hasNext())
                {//[riga][colonna]
                   Materiale mtCl = it.next();
                 if(k<cntDt)
                 {  _dataDDT[k][0] = String.valueOf(mtCl.getCodice());
                 	_dataDDT[k][1] = String.valueOf(mtCl.getDescrizione());
                 	_dataDDT[k][2] = String.valueOf(mtCl.getQuantita());
                 	_dataDDT[k][3] = String.valueOf(mtCl.getudm());
                 	_idMat[k]= mtCl.getId();
                    k++;
                 }
              }
            }
          }
        }
}

