package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import classResources.Bolla;
import classResources.Materiale;
import classResources.MaterialeDaProdurre;
import classResources.MaterialeTeorico;
import classResources.Terzista;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import java.awt.Color;

public class GUI_Bolla_Terzista {

	private JFrame frmBolleDiLavorazione;
	private JTextField textField;
	private int id; //id bolla
	private int idTerzista; //id terzista
	private int statoBolla;
	
	GUI_Messaggio messaggio;
	GUI_Extraconsumo extraconsumo;
	List<Bolla> lista = null;
	List<Bolla> listaBTer = null; //lista bolle del terzista selezionato
	List<MaterialeTeorico> lista1 = null;
	List<MaterialeDaProdurre> listaMDaProd = null; //senza join
	List<MaterialeDaProdurre> listaMDaProd1 = null; //con join
	List<Materiale> lista2 = null;
	List<Terzista> listaTerz = null;
	private static String[] _data1;
	private static int[] _id1;
	private static String[] _data3; //bolle-terzisti
	private static int[] _id3;
	private static String[] _nomeLav;
	private static int[] _statoBol;
	
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
					false, false, false, true, true, true
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
	private JTable table;
	private JTable table_1;
	private JTextField textField_1; //textbox dell'id del terzista
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
	
	//aggiunto
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Bolla_Terzista window = new GUI_Bolla_Terzista();
					window.frmBolleDiLavorazione.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI_Bolla_Terzista() {
		initialize();
	}
	
	//ListModel lista delle bolle del terzista
	public DefaultListModel listModel = new DefaultListModel();
	public JList list = new JList(listModel);
	
	private JTextField textField_2;
	JButton btnRichiediExtra = new JButton("Richiedi Extra");
	
	//Carica JList dcon le bolle del terzista
	private void caricaJListBolle(int idTerzista){
		loadListaBolleTerzista(idTerzista); //carica lista bolle del terzista in ListaBTer (e crea il vettore _data3)
		
		//**JList Bolle**
		for (int i = 0; i<_data3.length; i++)
		listModel.addElement(_data3[i]); //aggiunge al modello della lista bolle, le bolle assegnate a quel terzista (numero + data delle bolle)
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == true)
				{
					textField_2.setText("");
					btnRichiediExtra.setEnabled(true);
					
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
		frmBolleDiLavorazione = new JFrame();
		frmBolleDiLavorazione.setResizable(false);
		frmBolleDiLavorazione.setTitle("Bolle di Lavorazione");
		frmBolleDiLavorazione.setBounds(100, 100, 662, 418);
		frmBolleDiLavorazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBolleDiLavorazione.getContentPane().setLayout(null);
		
		JLabel lblBolleDiLavorazione = new JLabel("Bolle assegnate:");
		lblBolleDiLavorazione.setBounds(10, 64, 106, 14);
		frmBolleDiLavorazione.getContentPane().add(lblBolleDiLavorazione);
		
		JPanel panel = new JPanel();
		panel.setBounds(178, 11, 468, 329);
		frmBolleDiLavorazione.getContentPane().add(panel);
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
		lblMaterialiTeorici.setBounds(10, 150, 112, 14);
		panel.add(lblMaterialiTeorici);
		
		JScrollPane scrollPane_1 = new JScrollPane();

		scrollPane_1.setBounds(10, 46, 448, 93);
		panel.add(scrollPane_1);
		
		//Inizializza crea tabelle materiali
		table = new JTable(dmPrima);
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 175, 448, 109);
		panel.add(scrollPane);
		table_1 = new JTable(dm);
		table_1.setEnabled(false);
		scrollPane.setViewportView(table_1);
		
		//**btnEsci**
		JButton btnEsci = new JButton("Esci");
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmBolleDiLavorazione.dispose();
			}
		});
		btnEsci.setBounds(557, 352, 89, 23);
		frmBolleDiLavorazione.getContentPane().add(btnEsci);
		
		textField_1 = new JTextField();
		textField_1.setText("1");
		System.out.println(textField_1.getText());
		textField_1.setBounds(10, 33, 158, 20);
		frmBolleDiLavorazione.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//ID TERZISTA --> dopo da cambiare in base alla sessione
		idTerzista = Integer.parseInt(textField_1.getText());
		caricaJListBolle(idTerzista); //carica JList bolle del terzista
		
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
//		JButton btnRichiediExtra = new JButton("Richiedi Extra");
		btnRichiediExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1){ //se un elemento della lista è selezionato
					//Passo codice bolla nella text box e id della bolla per la query
					extraconsumo = new GUI_Extraconsumo(textField.getText(), id);
					extraconsumo.frmExtraconsumo.setVisible(true);
					}
				}
		});
		btnRichiediExtra.setBounds(346, 295, 112, 23);
		panel.add(btnRichiediExtra);
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.RED);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(10, 296, 284, 20);
		panel.add(textField_2);
			
		list.setBounds(10, 89, 158, 149);
		frmBolleDiLavorazione.getContentPane().add(list);
		
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
					b.setTerzista_id(-1); //setto id_terzista -1
					System.out.println("BollaID: " + id);
					ResourceClass.updResources(Bolla.class, Global._URLBollaStato, String.valueOf(id), b);
					listModel.removeAllElements(); //pulisce la lista delle bolle del terzista
					caricaJListBolle(idTerzista); //ricarica la lista delle bolle del terzista
					dm.setRowCount(0); //pulisce la table_1 (dm = datamodel della table_1)
					dmPrima.setRowCount(0); //pulisce la table (dmPrima = datamodel della table)
				}
			}
		});
		btnAnnullaBolla.setBounds(10, 249, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnAnnullaBolla);
		
		JButton btnChiudiParzialmente = new JButton("Chiudi Parzialmente");
		btnChiudiParzialmente.setBounds(10, 283, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnChiudiParzialmente);
		
		JButton btnChiudiBolla = new JButton("Chiudi Bolla");
		btnChiudiBolla.setBounds(10, 317, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnChiudiBolla);
		
		JLabel lblTerzisti = new JLabel("Terzista:");
		lblTerzisti.setBounds(10, 11, 106, 14);
		frmBolleDiLavorazione.getContentPane().add(lblTerzisti);
		
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

						Bolla b = new Bolla();
						b.setStato(2); //setto lo stato della bolla selezionata a 2 = in corso di lavorazione
						b.setTerzista_id(Integer.parseInt(textField_1.getText())); //id_terzista --> DA MODIFICARE CON LA SESSIONE
						ResourceClass.updResources(MaterialeDaProdurre.class, Global._URLMatDaProdurre, String.valueOf(mdp.getId()), mdp);
						ResourceClass.updResources(Bolla.class, Global._URLBollaStato, String.valueOf(id), b);
						statoBolla = b.getStato(); //aggiorno variabile dello stato della bolla selezionata
					}
					catch(Exception er) {
						er.printStackTrace();
					}					
				}
			}
		});
	}
}
