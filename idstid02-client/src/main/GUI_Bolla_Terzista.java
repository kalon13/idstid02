package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import classResources.Bolla;
import classResources.Extraconsumo;
import classResources.Materiale;
import classResources.MaterialeDaProdurre;
import classResources.MaterialeTeorico;
import classResources.Paia;
import classResources.Terzista;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class GUI_Bolla_Terzista {

        public JFrame frmBolleDiLavorazioneTerzista;
        private JTextField textField;
        public JLabel lblExtra = new JLabel("");
        public JLabel lblUdm = new JLabel("");
        private int id; //id bolla
        private Terzista terzista; //id terzista
        private int statoBolla;
        public int idMatTeo;
       
        GUI_Messaggio messaggio;
        GUI_Extraconsumo extraconsumo;
        GUI_Home home;
        List<Bolla> lista = null;
        List<Bolla> listaBTer = null; //lista bolle del terzista selezionato
        List<MaterialeTeorico> lista1 = null;
        List<MaterialeDaProdurre> listaMDaProd1 = null; //con join
        List<Paia> listaPaia = null;
        List<Materiale> lista2 = null;
        List<Terzista> listaTerz = null;
        private static int[] _id1;
        private static String[] _data2;
        private static int[] _id2;
        private static String[] _data3; //bolle-terzisti
        private static int[] _id3;
        private static String[] _nomeLav;
        private static int[] _statoBol;
       
        //TableModel per tableDaProdurre (materiali da produrre)
        @SuppressWarnings("serial")
        public DefaultTableModel dmPrima = new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                                "Descrizione", "Quantità", "udm", "Num.Morti", "Q.ta Prodotta", "Q.ta Spedita"
                })
                {
        		@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
    				String.class, Integer.class, String.class, Integer.class, Double.class, Double.class
    			};
    			@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
    				return columnTypes[columnIndex];
    			} 
                boolean[] columnEditables = new boolean[] { //non editabili le prime tre colonne
                    false, false, false, true, true, true
                };
                public boolean isCellEditable(int row, int column) {
                	return columnEditables[column];
                }
            };
       
        //TableModel per table_1 (materiali teorici)
        @SuppressWarnings("serial")
		public DefaultTableModel dm = new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                                "Descrizione", "Quantità", "udm", "Costo Unitario",
                })
        {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
            }
        };
        
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
        
        private JTable tableDaProdurre;
        private JTable table_1;
        private JLabel textField_1; //textbox dell'id del terzista
        private JTextField txtNomeLav; //textbox del nome della lavorazione
       
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
                String[] dtMess = bollaCl.getData().replace("-", "/").split(" ");
                _nomeLav[k] = bollaCl.getNomeLavorazione();
                _data3[k] = codBol + "-" + dtMess[0]; //codBolla + dataBolla
                _id3[k]= bollaCl.getId();
                _statoBol[k] = bollaCl.getStato();
                k++;
            }
        }
        
        private void loadTableMatTeo(int numBolla){
        	 dm.setRowCount(0); //pulisce la table_1 (dm = datamodel della table_1)
        	 
            //Load lista - Query con join
            lista1 = ResourceClass.getResources(MaterialeTeorico.class, Global._URLMatTeoSearch+numBolla);
            Iterator<MaterialeTeorico> it = lista1.iterator();
            
            _data2 = new String[lista1.size()];
            _id2 = new int[lista1.size()];
            int k = 0;
            while(it.hasNext())
            {                      
                MaterialeTeorico matCl = (MaterialeTeorico)it.next();
                String descMat = String.valueOf(matCl.getDescrizione());
                String costMat = String.valueOf(matCl.getCostoUnitario());
                String qtaMat = String.valueOf(matCl.getQuantita());
                String idBolla = String.valueOf(matCl.getId_bolla());
                String udm = String.valueOf(matCl.getUdm());
                _data2[k] = descMat + "-" + costMat + "-" + qtaMat + "-" + idBolla;
                _id2[k]= matCl.getId();
                k++;            
                //Aggiunge i valori alla tabella
                ((DefaultTableModel) table_1.getModel()).insertRow(
                    table_1.getRowCount(), new Object[]{descMat, qtaMat, udm, costMat});
            }      
        }
       
        private void loadTableMatDaProdurre1(int numBolla){
            //Load lista materiali da produrre con JOIN
            dmPrima.setRowCount(0); //pulisce la table (dmPrima = datamodel della table)
           
            listaMDaProd1 = ResourceClass.getResources(MaterialeDaProdurre.class, Global._URLMatDaProd1+numBolla);
            Iterator<MaterialeDaProdurre> it = listaMDaProd1.iterator();
           
            _id1 = new int[listaMDaProd1.size()];
            int k = 0;
            while(it.hasNext())
            {                      
                MaterialeDaProdurre matCl = (MaterialeDaProdurre)it.next();
                int qtaMat = matCl.getQuantita();
                int numMorti = matCl.getNumeroMorti();
                Double qtaProdotta = matCl.getQuantitaProdotta();
                Double qtaSpedita = matCl.getQuantitaSpedita();
                String desc = matCl.getDescrizione();
                String udm = matCl.getUdm();
                
                _id1[k]= matCl.getId();
                k++;
                //Aggiunge i valori alla tabella
                ((DefaultTableModel) tableDaProdurre.getModel()).insertRow(
                    tableDaProdurre.getRowCount(), new Object[]{desc, qtaMat, udm, numMorti, qtaProdotta, qtaSpedita});
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
//                                        GUI_Bolla_Terzista window = new GUI_Bolla_Terzista();
//                                        window.frmBolleDiLavorazioneTerzista.setVisible(true);
//                                } catch (Exception e) {
//                                        e.printStackTrace();
//                                }
//                        }
//                });
//        }

        public GUI_Bolla_Terzista() {
            initialize();
        }
       
        //ListModel lista delle bolle del terzista
        public DefaultListModel listModel = new DefaultListModel();
        public JList list = new JList(listModel);
       
        private JLabel textField_2;
        JButton btnRichiediExtra = new JButton("Visualizza Extra");
        private JTextField txtQuantitaExtra;
        private JTable tablePaia;
       
        //Carica JList dcon le bolle del terzista
        private void caricaJListBolle(int idTerzista){
            loadListaBolleTerzista(idTerzista); //carica lista bolle del terzista in ListaBTer (e crea il vettore _data3)
           
            //**JList Bolle**
            for (int i = 0; i<_data3.length; i++)
            listModel.addElement(_data3[i]); //aggiunge al modello della lista bolle, le bolle assegnate a quel terzista (numero + data delle bolle)
            list.addListSelectionListener(new ListSelectionListener() {
            	//Seleziono bolla nella lista
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting() == true)
                    {
                        textField_2.setText("");
                        btnRichiediExtra.setEnabled(true);
                        lblExtra.setText("");
                        lblUdm.setText("");
                        txtQuantitaExtra.setEditable(false);
                       
                        int indice = list.getSelectedIndex();
                        Bolla b = listaBTer.get(indice);
                        String testo = b.getCodice();
                        textField.setText(testo); //numero bolla selezionata nella lista
                        statoBolla = b.getStato(); //stato della bolla selezionata
                        System.out.println(testo);
                       
                        int k = list.getSelectedIndex();
                        id = _id3[k]; //id bolla
                        txtNomeLav.setText(_nomeLav[k]); //nome lavorazione della bolla selezionata
                 
                        loadTableMatTeo(id); //carica i materiali teorici di quella bolla
                        loadTableMatDaProdurre1(id); //carica i materiali da produrre di quella bolla
                        loadTablePaia(id); //carica le paia della bolla
                        if (_statoBol[k] == 3 || _statoBol[k] == 4)
                        {
                                textField_2.setText("Bolla chiusa!");
                                btnRichiediExtra.setEnabled(false);
                        }
                    }
                }
            });
        }
       
        private void initialize() {
            frmBolleDiLavorazioneTerzista = new JFrame();
            frmBolleDiLavorazioneTerzista.setResizable(false);
            frmBolleDiLavorazioneTerzista.setTitle("Bolle di Lavorazione");
            frmBolleDiLavorazioneTerzista.setBounds(100, 100, 663, 607);
            frmBolleDiLavorazioneTerzista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frmBolleDiLavorazioneTerzista.getContentPane().setLayout(null);
           
            JLabel lblBolleDiLavorazione = new JLabel("Bolle assegnate:");
            lblBolleDiLavorazione.setBounds(10, 64, 106, 14);
            frmBolleDiLavorazioneTerzista.getContentPane().add(lblBolleDiLavorazione);
           
            JPanel panel = new JPanel();
            panel.setBounds(178, 11, 468, 521);
            frmBolleDiLavorazioneTerzista.getContentPane().add(panel);
            panel.setLayout(null);
           
            JLabel lblNewLabel = new JLabel("Numero bolla:");
            lblNewLabel.setBounds(10, 11, 87, 14);
            panel.add(lblNewLabel);
           
            //**textField**
            textField = new JTextField();
            textField.setEditable(false);
            textField.setBounds(107, 8, 44, 20);
            panel.add(textField);
            textField.setColumns(10);
           
            txtNomeLav = new JTextField();
            txtNomeLav.setEditable(false);
            txtNomeLav.setBounds(161, 8, 140, 20);
            panel.add(txtNomeLav);
            txtNomeLav.setColumns(10);
           
            JLabel lblMaterialiDaProdurre = new JLabel("Materiali da produrre:");
            lblMaterialiDaProdurre.setBounds(10, 28, 141, 14);
            panel.add(lblMaterialiDaProdurre);
           
            JLabel lblMaterialiTeorici = new JLabel("Materiali teorici:");
            lblMaterialiTeorici.setBounds(10, 270, 112, 14);
            panel.add(lblMaterialiTeorici);
           
            JScrollPane scrollPane_1 = new JScrollPane();

            scrollPane_1.setBounds(10, 46, 448, 93);
            panel.add(scrollPane_1);
           
            //Inizializza crea tabelle materiali
            tableDaProdurre = new JTable(dmPrima);
            scrollPane_1.setViewportView(tableDaProdurre);
           
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 287, 448, 109);
            panel.add(scrollPane);
            table_1 = new JTable(dm);
            table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    		table_1.addMouseListener(new MouseAdapter() {
    			@Override
    			//Seleziono una riga sulla tabella dei materiali teorici
    			public void mouseReleased(MouseEvent e) {
    				idMatTeo = selezionaRiga();
    				txtQuantitaExtra.setEditable(true);
    			}
    		});
            scrollPane.setViewportView(table_1);
           
            //**btnEsci**
            JButton btnEsci = new JButton("Esci");
            btnEsci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frmBolleDiLavorazioneTerzista.setVisible(false);
                home = new GUI_Home();
                home.frmHome.setVisible(true);
            }
            });
            btnEsci.setBounds(557, 543, 89, 23);
            frmBolleDiLavorazioneTerzista.getContentPane().add(btnEsci);
           
            textField_1 = new JLabel();
            terzista = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+Autenticazione.getSessione().getUtente().getUserId());
      
            textField_1.setText(terzista.getRagioneSociale());
            
            textField_1.setBounds(10, 33, 158, 20);
            frmBolleDiLavorazioneTerzista.getContentPane().add(textField_1);
           
            caricaJListBolle(terzista.getId()); //carica JList bolle del terzista
            
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
            btnVisualizzaNote.setBounds(311, 7, 147, 23);
            panel.add(btnVisualizzaNote);
           
            //**btnRichiediExtra**
//              JButton btnRichiediExtra = new JButton("Richiedi Extra");
            btnRichiediExtra.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (list.getSelectedIndex() != -1){ //se un elemento della lista è selezionato
	                    //Passo codice bolla nella text box e id della bolla per la query
	                    extraconsumo = new GUI_Extraconsumo(textField.getText(), id);
	                    extraconsumo.frmExtraconsumo.setVisible(true);
	                    }
	                }
            });
            btnRichiediExtra.setBounds(346, 487, 112, 23);
            panel.add(btnRichiediExtra);
           
            textField_2 = new JLabel();
            textField_2.setForeground(Color.RED);
            textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
            textField_2.setBounds(10, 407, 284, 20);
            panel.add(textField_2);
            
            lblExtra.setBounds(21, 458, 106, 14);
            panel.add(lblExtra);
            
            txtQuantitaExtra = new JTextField();
            txtQuantitaExtra.setEditable(false);
            txtQuantitaExtra.setBounds(126, 452, 86, 20);
            panel.add(txtQuantitaExtra);
            txtQuantitaExtra.setColumns(10);
            
            JButton btnRichiedi = new JButton("Richiedi");
            btnRichiedi.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		if (!txtQuantitaExtra.getText().isEmpty()){ //se il campo con la quantità da richiedere non è vuota
            			if (verificaQuantitaIns(txtQuantitaExtra.getText())){ //Controllo se ho inserito una quantità double
            				//fare una Insert nella tabella Extraconsumo
	            			//matTeoid quant giust datarichiesta
	            			Extraconsumo ext = new Extraconsumo();
	            		
	            			//Data di oggi
	            			Calendar calendar = Calendar.getInstance();
	            			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	            			String today = dateFormat.format(calendar.getTime());
	            			System.out.println("Today: " + dateFormat.format(calendar.getTime()));
	                    
	            			double qr = Double.parseDouble(txtQuantitaExtra.getText());
	            			ext.setQuantita(qr);
	            			ext.setGiustificato(0); //0 - false
	            			ext.setDataRichiesta(today); //data
	            			ext.setIdMatTeo(idMatTeo);
	            			System.out.println(qr);
	            			System.out.println(today);
	            			System.out.println(idMatTeo);
	            			ResourceClass.addResources(Global._URLExtraIns, ext);
	            			txtQuantitaExtra.setText("");
            			}
            		}
            		else
            		{
            			JOptionPane.showMessageDialog(frmBolleDiLavorazioneTerzista,
            				    "Campo quantità richiesta vuoto!",
            				    "Attenzione!",
            				    JOptionPane.PLAIN_MESSAGE);
            		}
            	}
            });
            btnRichiedi.setBounds(346, 452, 89, 23);
            panel.add(btnRichiedi);
            
            lblUdm.setBounds(237, 458, 46, 14);
            panel.add(lblUdm);
            
            JLabel lblDettaglioMaterialiDa = new JLabel("Dettaglio materiali da produrre:");
            lblDettaglioMaterialiDa.setBounds(10, 147, 273, 14);
            panel.add(lblDettaglioMaterialiDa);
            
            JScrollPane scrollPane_2 = new JScrollPane();
            scrollPane_2.setBounds(10, 166, 448, 93);
            panel.add(scrollPane_2);
            
            tablePaia = new JTable(dmPaia);
            scrollPane_2.setViewportView(tablePaia);
            
            JButton btnModelloScarpa = new JButton("Modello Scarpa");
            btnModelloScarpa.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		//id contiene l'id della bolla
            		Bolla bollaImageUrl=ResourceClass.getResource(Bolla.class, Global._URLBollaImage+id);
            		GUI_Image path = new GUI_Image(bollaImageUrl.getImageUrl());
            		path.frmModelloScarpa.setVisible(true);
            	}
            });
            btnModelloScarpa.setBounds(224, 487, 112, 23);
            panel.add(btnModelloScarpa);
                   
            list.setBounds(10, 89, 158, 149);
            frmBolleDiLavorazioneTerzista.getContentPane().add(list);
           
            //**btnAnnullaBolla**
            JButton btnAnnullaBolla = new JButton("Annulla Bolla");
            //Se cerco di annullare una bolla già iniziata -> errore!
            btnAnnullaBolla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (statoBolla == 2) {
                    JDialog dialog = new JDialog();
                    JLabel label = new JLabel("Impossibile annullare una bolla in corso di lavorazione!");
                    dialog.setLocationRelativeTo(null);
                    dialog.setTitle("Attenzione!");
                    dialog.getContentPane().add(label);
                    dialog.pack();
                    dialog.setVisible(true);
                    System.out.println(statoBolla);
                }
                else //se la bolla non è stata ancora iniziata può essere annullata
                {
                //Mettere a -1 l'id_terzista di quella bolla
                    Bolla b = new Bolla();
                    b.setStato(0); //setto a 0 = bolla da assegnare, lo stato della bolla
                    b.setTerzistaId(-1); //setto id_terzista -1
                    System.out.println("BollaID: " + id);
                    System.out.println(b.getId());
                    System.out.println(b.getTerzistaId());
                    ResourceClass.updResources(Bolla.class, Global._URLBollaStato, String.valueOf(id), b);
                    listModel.removeAllElements(); //pulisce la lista delle bolle del terzista
                    caricaJListBolle(terzista.getId()); //ricarica la lista delle bolle del terzista
                    dm.setRowCount(0); //pulisce la table_1 (dm = datamodel della table_1)
                    dmPrima.setRowCount(0); //pulisce la table (dmPrima = datamodel della table)
                }
            }
            });
            btnAnnullaBolla.setBounds(10, 249, 158, 23);
            frmBolleDiLavorazioneTerzista.getContentPane().add(btnAnnullaBolla);
           
            JButton btnChiudiParzialmente = new JButton("Chiudi Parzialmente");
            btnChiudiParzialmente.setBounds(10, 283, 158, 23);
            frmBolleDiLavorazioneTerzista.getContentPane().add(btnChiudiParzialmente);
           
            JButton btnChiudiBolla = new JButton("Chiudi Bolla");
            btnChiudiBolla.setBounds(10, 317, 158, 23);
            frmBolleDiLavorazioneTerzista.getContentPane().add(btnChiudiBolla);
           
            JLabel lblTerzisti = new JLabel("Terzista:");
            lblTerzisti.setBounds(10, 11, 106, 14);
            frmBolleDiLavorazioneTerzista.getContentPane().add(lblTerzisti);
           
            //Al premere di Invio in una cella di tableDaProdurre richiama l'Update
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
                    
                    double qta = Double.parseDouble(dmPrima.getValueAt(row, 1).toString());
                    if (qtp > qta){ //se inserisco una quantità prodotta maggiore di quella richiesta errore
            			JOptionPane.showMessageDialog(frmBolleDiLavorazioneTerzista,
            				    "Campo quantità prodotta errato!",
            				    "Attenzione!",
            				    JOptionPane.PLAIN_MESSAGE);
            			loadTableMatDaProdurre1(id);
                    }
                    else {
                    mdp.setNumeroMorti(nm);
                    mdp.setQuantitaProdotta(qtp);
                    mdp.setQuantitaSpedita(qts);

                    Bolla b = new Bolla();
                    b.setStato(2); //setto lo stato della bolla selezionata a 2 = in corso di lavorazione
                    b.setTerzistaId(terzista.getId()); //id_terzista --> DA MODIFICARE CON LA SESSIONE
                    ResourceClass.updResources(MaterialeDaProdurre.class, Global._URLMatDaProdurre, String.valueOf(mdp.getId()), mdp);
                    ResourceClass.updResources(Bolla.class, Global._URLBollaStato, String.valueOf(id), b);
                    statoBolla = b.getStato(); //aggiorno variabile dello stato della bolla selezionata
                    }
                }
                catch(Exception er) {
                    er.printStackTrace();
                }                                      
            }
        }
        });
    }
      
    public int selezionaRiga(){
		//Svuotiamo la tabella
		
		
		if(table_1.getSelectedRow()!=-1){
			System.out.println(_id2[0]);
			System.out.println(_id2[1]);

			int row = table_1.getSelectedRow();
            MaterialeTeorico mt = lista1.get(row);
            //Colonne: 3-numeroMorti 4-QtaProdotta 5-QtaSpedita
            int id = mt.getId();
            String descrizione = mt.getDescrizione();
            String udm = mt.getUdm();
            System.out.println("riga " + row);
            System.out.println("ID " + id);
            System.out.println("desc " + descrizione);
            lblExtra.setText(descrizione);
            lblUdm.setText(udm);

			System.out.println("Click");
			int idmater = _id2[table_1.getSelectedRow()];
			System.out.println(table_1.getSelectedRow());
			System.out.println(idmater);
			return idmater;
//    			List<Fase> fasi = ResourceClass.getResources(Fase.class, Global._URLFase+id_Lav.get(table.getSelectedRow()));
//    			Iterator<Fase> listaFasi = fasi.iterator();
//    			int righe=-1;
//    			while(listaFasi.hasNext()){
//    				Fase fase = listaFasi.next();
//    				righe++;
//    				((DefaultTableModel) tableFase.getModel()).insertRow(righe, new Object[righe+1][2]);
//    				tableFase.setValueAt(fase.getNome(), righe, 0);
//    				tableFase.setValueAt(fase.getOrdine(), righe, 1);
			}
		return -1;
		}
    	
		public boolean verificaQuantitaIns(String p){
			try{
				Double.parseDouble(p);
				return true;
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Quantità non corretta!", "Attenzione", 0);
				return false;
			}
		}
}
