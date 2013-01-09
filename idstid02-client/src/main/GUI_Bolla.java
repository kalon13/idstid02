package main;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import classResources.Bolla;
import classResources.Materiale;
import classResources.MaterialeDaProdurre;
import classResources.MaterialeTeorico;
import classResources.Paia;
import classResources.Terzista;

import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI_Bolla {

        JFrame frmBolleDiLavorazione;
        private JTextField textField;
        private JTable table;
        private JTable table_1;
        private int id; //id bolla
        private int idTerzista; //id terzista
        private JTextField txtNomeLav; //textbox del nome della lavorazione
        private JPanel panel; 
        
        GUI_BolleChiuse bolleChiuse;
        GUI_Messaggio messaggio;
        GUI_Extraconsumo_Azienda extraconsumo;
        GUI_Home home;
        List<Bolla> lista = null;
        List<Bolla> listaBTer = null; //lista bolle del terzista selezionato
        List<MaterialeTeorico> lista1 = null;
        List<MaterialeDaProdurre> listaMDaProd1 = null; //con join
        List<Materiale> lista2 = null;
        List<Paia> listaPaia = null;
        List<Terzista> listaTerz = null;
        private static String[] _data1;
        private static int[] _id1;
        private static String[] _data2; //terzisti
        private static int[] _id2;
        private static String[] _data3; //bolle-terzisti
        private static int[] _id3;
        private static String[] _nomeLav;
        private static int[] _statoBol;
       
        private void loadListaTerzisti(){
                //Load lista terzisti
                listaTerz = ResourceClass.getResources(Terzista.class, Global._URLTerz);
                Iterator<Terzista> it = listaTerz.iterator();

                _data2 = new String[listaTerz.size()];
                _id2 = new int[listaTerz.size()];
                int k = 0;
                while(it.hasNext())
                        {
                					Terzista terCl = (Terzista)it.next();
                					String id = String.valueOf(terCl.getId());
                					String ragSoc = String.valueOf(terCl.getRagioneSociale());
                					_data2[k] = id + "-" + ragSoc; //idTerz + ragioneSociale
                					_id2[k]= terCl.getId();
                					k++;
                        }
        }
       
        private void loadListaBolleTerzista(int id_terzista){
                //Load lista bolle del terzista
                listaBTer = ResourceClass.getResources(Bolla.class, Global._URLBollaTerz+id_terzista);
                Iterator<Bolla> it = listaBTer.iterator();

                _data3 = new String[listaBTer.size()];
                _id3 = new int[listaBTer.size()];
                _nomeLav = new String[listaBTer.size()];
                _statoBol = new int[listaBTer.size()];
                int k = 0;
                while(it.hasNext())
                        {
                                Bolla bollaCl = (Bolla)it.next();
                                String codBol = String.valueOf(bollaCl.getCodice());
//                              int statoBol = bollaCl.getStato();
//                              if (statoBol != 3 && statoBol !=4) //se la bolla non è chiusa o chiusa con morti
//                              {
                                        String[] dtMess = bollaCl.getData().replace("-", "/").split(" ");
                                        _data3[k] = codBol + "-" + dtMess[0]; //codBolla + dataBolla
                                        _id3[k]= bollaCl.getId();
                                        _nomeLav[k] = bollaCl.getNomeLavorazione();
                                        _statoBol[k] = bollaCl.getStato();
//                              }
                                k++;
                        }
        }
       
        //restituisce il vettore di dati dei materiali che si riferscono alla bolla selezionata nella lista
        private void loadTableMatTeo(int numBolla){
                //Load lista
                //Query con join
                lista1 = ResourceClass.getResources(MaterialeTeorico.class, Global._URLMatTeoSearch+numBolla);
                Iterator<MaterialeTeorico> it = lista1.iterator();

                dm.setRowCount(0); //pulisce la table_1 (dm = datamodel della table_1)
               
                _data1 = new String[lista1.size()];
                _id1 = new int[lista1.size()];
                int k = 0;
                while(it.hasNext())
                        {                      
                                MaterialeTeorico matCl = (MaterialeTeorico)it.next();
                                //aggiunto
                                String descMat = String.valueOf(matCl.getDescrizione());
                                String costMat = String.valueOf(matCl.getCostoUnitario());
                                String qtaMat = String.valueOf(matCl.getQuantita());
                                String idBolla = String.valueOf(matCl.getId_bolla());
                                String udm = String.valueOf(matCl.getUdm());
                                _data1[k] = descMat + "-" + costMat + "-" + qtaMat + "-" + idBolla;
                                _id1[k]= matCl.getId();
                                k++;
                                //Aggiunge i valori alla tabella
                                ((DefaultTableModel) table_1.getModel()).insertRow(
                                    table_1.getRowCount(), new Object[]{descMat,qtaMat, udm, costMat});
                        }      
        }
       
        private void loadTableMatDaProdurre1(int numBolla){
                //Load lista materiali da produrre con JOIN
                dmPrima.setRowCount(0); //pulisce la table (dmPrima = datamodel della table)
               
                listaMDaProd1 = ResourceClass.getResources(MaterialeDaProdurre.class, Global._URLMatDaProd1+numBolla);
                Iterator<MaterialeDaProdurre> it = listaMDaProd1.iterator();
               
                _data1 = new String[listaMDaProd1.size()];
                _id1 = new int[listaMDaProd1.size()];
                int k = 0;
                while(it.hasNext())
                        {                      
                        MaterialeDaProdurre matCl = (MaterialeDaProdurre)it.next();
                                //aggiunto
                                String qtaMat = String.valueOf(matCl.getQuantita());
                                String numMorti = String.valueOf(matCl.getNumeroMorti());
                                String qtaProdotta = String.valueOf(matCl.getQuantitaProdotta());
                                String qtaSpedita = String.valueOf(matCl.getQuantitaSpedita());
                                String desc = String.valueOf(matCl.getDescrizione());
                                String costoUnit = String.valueOf(matCl.getCostoUnitario());
                                String udm = String.valueOf(matCl.getUdm());
                                _data1[k] = qtaMat + "-" + numMorti + "-" + qtaProdotta + "-" + qtaSpedita + "-" + desc + "-" + costoUnit + "-" + udm;
                                _id1[k]= matCl.getId();
                                k++;
                                //Aggiunge i valori alla tabella
                                ((DefaultTableModel) table.getModel()).insertRow(
                                    table.getRowCount(), new Object[]{desc, qtaMat, udm, numMorti, qtaProdotta, qtaSpedita});
                        }
        }
        
        private void loadTablePaia(int numBolla){
            dmPaia.setRowCount(0); //pulisce la table
           
            listaPaia = ResourceClass.getResources(Paia.class, Global._URLPaia + numBolla);
                
            int paia36 = 0;
            int paia37 = 0;
            int paia38 = 0;
            int paia39 = 0;
            int paia40 = 0;
            int paia41 = 0;
            int paia42 = 0;
            String desc = null;
            Paia paiaCl = new Paia(listaPaia.get(0).getId(), listaPaia.get(0).getIdMatDaProd(), listaPaia.get(0).getDescrizione(), listaPaia.get(0).getnScarpa(), listaPaia.get(0).getPaia());
            
            _id1 = new int[listaPaia.size()];
            int k = 0;

            //Per ogni elemento della lista (la query mi restituisce una tabella di righe con id-idMatDaProd-Descr-nScarpa-Paia)
            for (int i=0 ; i< listaPaia.size();i++){
            	 paiaCl = listaPaia.get(i);
                 desc = paiaCl.getDescrizione();
                 if (i+1 < listaPaia.size()){
                	 if (listaPaia.get(i).getDescrizione().equals(listaPaia.get(i+1).getDescrizione()))
                	 {
                		 int nScarpa = paiaCl.getnScarpa();
                		 if (nScarpa == 36){
	                     	paia36 = paiaCl.getPaia();
	                     } else if (nScarpa == 37) {
	                     	paia37 = paiaCl.getPaia();
	                     } else if (nScarpa == 38) {
	                     	paia38 = paiaCl.getPaia();
	                     } else if (nScarpa == 39) {
	                     	paia39 = paiaCl.getPaia();
	                     } else if (nScarpa == 40) {
	                     	paia40 = paiaCl.getPaia();
	                     } else if (nScarpa == 41) {
	                     	paia41 = paiaCl.getPaia();
	                     } else if (nScarpa == 42) {
	                     	paia42 = paiaCl.getPaia();
	                     }
                	 }
                	 else { //la riga dopo riguarda un altro materiale da produrre
                		 int nScarpa = paiaCl.getnScarpa();
                         if (nScarpa == 36){
                         	paia36 = paiaCl.getPaia();
                         } else if (nScarpa == 37) {
                         	paia37 = paiaCl.getPaia();
                         } else if (nScarpa == 38) {
                         	paia38 = paiaCl.getPaia();
                         } else if (nScarpa == 39) {
                         	paia39 = paiaCl.getPaia();
                         } else if (nScarpa == 40) {
                         	paia40 = paiaCl.getPaia();
                         } else if (nScarpa == 41) {
                         	paia41 = paiaCl.getPaia();
                         } else if (nScarpa == 42) {
                         	paia42 = paiaCl.getPaia();
                         }
                         //Aggiunge i valori alla tabella
                         ((DefaultTableModel) tablePaia.getModel()).insertRow(
                                 tablePaia.getRowCount(), new Object[]{desc, paia36, paia37, paia38, paia39, paia40, paia41, paia42});
                         //Azzero valori per il prossimo materiale dal produrre
	                     paia36 = 0;
	                     paia37 = 0;
	                     paia38 = 0;
	                     paia39 = 0;
	                     paia40 = 0;
	                     paia41 = 0;
	                     paia42 = 0;  
                	 }
                 }
                  else //ultimo materiale da produrre
                 {
                	  int nScarpa = paiaCl.getnScarpa();
                      if (nScarpa == 36){
                      	paia36 = paiaCl.getPaia();
                      } else if (nScarpa == 37) {
                      	paia37 = paiaCl.getPaia();
                      } else if (nScarpa == 38) {
                      	paia38 = paiaCl.getPaia();
                      } else if (nScarpa == 39) {
                      	paia39 = paiaCl.getPaia();
                      } else if (nScarpa == 40) {
                      	paia40 = paiaCl.getPaia();
                      } else if (nScarpa == 41) {
                      	paia41 = paiaCl.getPaia();
                      } else if (nScarpa == 42) {
                      	paia42 = paiaCl.getPaia();
                      }
                      //Aggiungo riga nella tabella
                	  ((DefaultTableModel) tablePaia.getModel()).insertRow(
                              tablePaia.getRowCount(), new Object[]{desc, paia36, paia37, paia38, paia39, paia40, paia41, paia42});
                 }
                 _id1[k]= paiaCl.getId(); //id delle righe della tabella paia
                 k++;
            }
        }
       
//        public static void main(String[] args) {
//                EventQueue.invokeLater(new Runnable() {
//                        public void run() {
//                                try {
//                                        GUI_Bolla window = new GUI_Bolla();
//                                        window.frmBolleDiLavorazione.setVisible(true);
//                                } catch (Exception e) {
//                                        e.printStackTrace();
//                                }
//                        }
//                });
//        }
               
        //TableModel per table (materiali da produrre)
        @SuppressWarnings("serial")
        public DefaultTableModel dmPrima = new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                        "Descrizione", "Qta", "udm", "numeroMorti", "qtaProdotta", "QtaSpedita"
                        })
                        {
                                boolean[] columnEditables = new boolean[] { //non editabili le prime tre colonne
                                        false, false, false, false, false, false
                                };
                                public boolean isCellEditable(int row, int column) {
                                        return columnEditables[column];
                                }
                        };
       
        //TableModel per table_1 (materiali teorici)
        public DefaultTableModel dm = new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                        "Desc", "Quantità", "udm", "CostoUnit",
                        }
                );
        
        private JLabel textField_1;
        private JTable tablePaia;
       
        public GUI_Bolla() {

                loadListaTerzisti(); //carica lista terzisti
                initialize();
        }

        //TableModel per dettaglio paia
        @SuppressWarnings("serial")
		public DefaultTableModel dmPaia = new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                		"Descrizione", "Num.36", "Num.37", "Num.38", "Num.39", "Num.40", "Num.41", "Num.42"
                })
        {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
            }
        };
        
        private void initialize() {    
                frmBolleDiLavorazione = new JFrame();
                frmBolleDiLavorazione.addWindowListener(new WindowAdapter() {
                	@Override
                	public void windowClosing(WindowEvent e) {
                		GUI_Home windowHome = new GUI_Home();
                        windowHome.frmHome.setVisible(true);
                	}
                });
                frmBolleDiLavorazione.setResizable(false);
                frmBolleDiLavorazione.setTitle("Bolle di Lavorazione");
                frmBolleDiLavorazione.setBounds(100, 100, 717, 596);
                frmBolleDiLavorazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmBolleDiLavorazione.getContentPane().setLayout(null);
               
                JLabel lblBolleDiLavorazione = new JLabel("Bolle assegnate:");
                lblBolleDiLavorazione.setBounds(10, 172, 106, 14);
                frmBolleDiLavorazione.getContentPane().add(lblBolleDiLavorazione);
               
                panel = new JPanel();
                panel.setBorder(new TitledBorder(null, "Dettagli bolla selezionata", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                panel.setBounds(178, 11, 523, 479);
                frmBolleDiLavorazione.getContentPane().add(panel);
                panel.setLayout(null);
               
                JLabel lblNewLabel = new JLabel("Numero bolla:");
                lblNewLabel.setBounds(10, 27, 87, 14);
                panel.add(lblNewLabel);
               
                //**textField**
                textField = new JTextField();
                textField.setEditable(false);
                textField.setBounds(107, 24, 44, 20);
                panel.add(textField);
                textField.setColumns(10);
               
                txtNomeLav = new JTextField();
                txtNomeLav.setEditable(false);
                txtNomeLav.setBounds(161, 24, 140, 20);
                panel.add(txtNomeLav);
                txtNomeLav.setColumns(10);
               
                JLabel lblMaterialiDaProdurre = new JLabel("Materiali da produrre:");
                lblMaterialiDaProdurre.setBounds(10, 55, 141, 14);
                panel.add(lblMaterialiDaProdurre);
               
                JLabel lblMaterialiTeorici = new JLabel("Materiali teorici:");
                lblMaterialiTeorici.setBounds(10, 297, 112, 14);
                panel.add(lblMaterialiTeorici);
                
                JLabel lblDettaglioMaterialiDa = new JLabel("Dettaglio materiali da produrre:");
                lblDettaglioMaterialiDa.setBounds(10, 174, 273, 14);
                panel.add(lblDettaglioMaterialiDa);
                
                JScrollPane scrollPane_2 = new JScrollPane();
                scrollPane_2.setBounds(10, 193, 503, 93);
                panel.add(scrollPane_2);
                
                tablePaia = new JTable(dmPaia);
                tablePaia.setEnabled(false);
                scrollPane_2.setViewportView(tablePaia);
               
                JScrollPane scrollPane_1 = new JScrollPane();

                scrollPane_1.setBounds(10, 73, 503, 93);
                panel.add(scrollPane_1);
               
                //Inizializza crea tabelle materiali
                table = new JTable(dmPrima);
                table.setEnabled(false);
                scrollPane_1.setViewportView(table);
               
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(10, 315, 503, 109);
                panel.add(scrollPane);
                table_1 = new JTable(dm);
                table_1.setEnabled(false);
                scrollPane.setViewportView(table_1);
               
                //**JList Bolle**
                final DefaultListModel listModel = new DefaultListModel();
                final JList list = new JList(listModel); //aggiunge alla Jlist numero + data delle bolle
                //Quando clicco su una bolla nella lista bolle
                list.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent e) {
                                if (e.getValueIsAdjusting() == true)
                                {
                                        textField_1.setText(""); //pulisce campo testo
                                        int indice = list.getSelectedIndex();
                                        Bolla b = listaBTer.get(indice);
                                        String testo = b.getCodice();
                                        textField.setText(testo); //numero bolla selezionata nella lista
                                       
                                        int k = list.getSelectedIndex();
                                        id = _id3[k]; //id bolla
                                        txtNomeLav.setText(_nomeLav[k]); //nome lavorazione della bolla selezionata
                                 
                                        loadTableMatTeo(id); //carica i materiali teorici di quella bolla
                                        loadTableMatDaProdurre1(id); //carica i materiali da produrre di quella bolla
                                        loadTablePaia(id); //carica le paia della bolla
                                        if (_statoBol[k] == 3 || _statoBol[k] == 4)
                                        {
                                                textField_1.setText("Bolla chiusa!");
                                        }
                                }
                        }
                });
               
                //**btnVisualizzaNote**
                JButton btnVisualizzaNote = new JButton("Visualizza Note");
                //Quando clicco Visualizza Note richiama la GUI Messaggio passandogli il num di bolla
                btnVisualizzaNote.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String numeroBolla = textField.getText();
                                messaggio = new GUI_Messaggio(id, numeroBolla);
                                messaggio.frmMessaggi.setVisible(true);
                        }
                });
                btnVisualizzaNote.setBounds(366, 23, 147, 23);
                panel.add(btnVisualizzaNote);
               
                //**btnRichiediExtra**
                JButton btnVisualizzaExtra = new JButton("Visualizza Extra");
                btnVisualizzaExtra.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (list.getSelectedIndex() != -1){ //se un elemento della lista è selezionato
                                        //Passo codice bolla nella text box e id della bolla per la query
                                        extraconsumo = new GUI_Extraconsumo_Azienda(textField.getText(), id);
                                        extraconsumo.frmExtraconsumo.setVisible(true);
                                        }
                                }
                });
                btnVisualizzaExtra.setBounds(366, 435, 147, 23);
                panel.add(btnVisualizzaExtra);
               
                textField_1 = new JLabel();
                textField_1.setForeground(Color.RED);
                textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
                textField_1.setBounds(10, 438, 196, 20);
                panel.add(textField_1);
                
                JButton btnModelloScarpa = new JButton("Modello Scarpa");
                btnModelloScarpa.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		//id contiene l'id della bolla
                		if(id>0){
	                		Bolla bollaImageUrl=ResourceClass.getResource(Bolla.class, Global._URLBollaImage+id);
	                		GUI_Image path = new GUI_Image(bollaImageUrl.getImageUrl());
	                		path.frmModelloScarpa.setVisible(true);
                		}
                		else
                			JOptionPane.showMessageDialog(null, "Bolla non selezionata.", "Attenzione", 0);
                	}
                });
                btnModelloScarpa.setBounds(216, 435, 140, 23);
                panel.add(btnModelloScarpa);
               
                list.setBounds(10, 197, 158, 143);
                frmBolleDiLavorazione.getContentPane().add(list);
               
                JLabel lblTerzisti = new JLabel("Terzisti:");
                lblTerzisti.setBounds(10, 11, 46, 14);
                frmBolleDiLavorazione.getContentPane().add(lblTerzisti);
                
                JScrollPane scrollPane_3 = new JScrollPane();
                scrollPane_3.setBounds(10, 36, 158, 125);
                frmBolleDiLavorazione.getContentPane().add(scrollPane_3);
                
                 //**JList Terzisti**
                 final JList list_2 = new JList(_data2);
                 scrollPane_3.setViewportView(list_2);
                 //Se clicco in un terzista della lista mi visualizza solo le bolle a lui assegnate
                 list_2.addListSelectionListener(new ListSelectionListener() {
                         public void valueChanged(ListSelectionEvent e) {
                                 if (e.getValueIsAdjusting() == true)
                                 {
                                         dm.setRowCount(0); //pulisce tabella
                                         dmPrima.setRowCount(0); //pulisce tabella
                                         dmPaia.setRowCount(0); //pulisce tabella
                                         textField.setText(""); //pulisce campi testo
                                         txtNomeLav.setText("");
                                         textField_1.setText("");
                                        
                                         int k = list_2.getSelectedIndex();
                                         idTerzista = _id2[k]; //id terzista
                                
                                         listModel.removeAllElements();
                                         loadListaBolleTerzista(idTerzista);
                                         //azzero id della bolla altrimenti mi rimane in memoria e visual l'immagine anche se non clicco una bolla
                                         id=0;
                                         for (int i = 0; i<_data3.length; i++)
                                                 listModel.addElement(_data3[i]); }
                         }
                 });
               
                JButton btnBolleChiuse = new JButton("Bolle Chiuse");
                btnBolleChiuse.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                bolleChiuse = new GUI_BolleChiuse();
                                bolleChiuse.frmBolleChiuse.setVisible(true);
                        }
                });
                btnBolleChiuse.setBounds(561, 501, 140, 23);
                frmBolleDiLavorazione.getContentPane().add(btnBolleChiuse);
               
                //Al premere di Invio in una cella di table_1 richiama l'Update
                dmPrima.addTableModelListener(new TableModelListener(){
                        @Override
                        public void tableChanged(TableModelEvent e) {
                                int col = e.getColumn();
                                int row = e.getFirstRow();
                                if(col > 2) {
                                        try {
                                                MaterialeDaProdurre mdp = listaMDaProd1.get(row);
                                                //Colonne: 3-numeroMorti 4-QtaProdotta 5-QtaSpedita
                                                int nm = Integer.parseInt(dmPrima.getValueAt(row, 3).toString());
                                                double qtp = Double.parseDouble(dmPrima.getValueAt(row, 4).toString());
                                                double qts = Double.parseDouble(dmPrima.getValueAt(row, 5).toString());
                                                mdp.setNumeroMorti(nm);
                                                mdp.setQuantitaProdotta(qtp);
                                                mdp.setQuantitaSpedita(qts);
                                                ResourceClass.updResources(MaterialeDaProdurre.class, Global._URLMatDaProdurre, String.valueOf(mdp.getId()), mdp);
                                        }
                                        catch(Exception er) {
                                                er.printStackTrace();
                                        }                                      
                                }
                        }
                });

                /*********************Aggiunto menu*************************************/
        		menu app = new menu(frmBolleDiLavorazione, "Bolla");
        		frmBolleDiLavorazione.setVisible(true);
        }
}

