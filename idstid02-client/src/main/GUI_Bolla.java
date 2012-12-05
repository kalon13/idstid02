package main;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import classResources.Terzista;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class GUI_Bolla {

	JFrame frmBolleDiLavorazione;
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private int id; //id bolla
	private int idTerzista; //id terzista
	private JTextField txtNomeLav; //textbox del nome della lavorazione
	
	GUI_Messaggio messaggio;
	GUI_Extraconsumo_Azienda extraconsumo;
	List<Bolla> lista = null;
	List<Bolla> listaBTer = null; //lista bolle del terzista selezionato
	List<MaterialeTeorico> lista1 = null;
	List<MaterialeDaProdurre> listaMDaProd = null; //senza join
	List<MaterialeDaProdurre> listaMDaProd1 = null; //con join
	List<Materiale> lista2 = null;
	List<Terzista> listaTerz = null;
	private static String[] _data1;
	private static int[] _id1;
	private static String[] _data2; //terzisti
	private static int[] _id2;
	private static String[] _data3; //bolle-terzisti
	private static int[] _id3;
	private static String[] _nomeLav;
	
	private void loadListaTerzisti(){
		//Load lista terzisti
		listaTerz = ResourceClass.getResources(Terzista.class, Global._URLTerzista);
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
	
//	private void loadTableDt(){
//		//Load lista bolle
//		lista = ResourceClass.getResources(Bolla.class, Global._URLBolla);
//		Iterator<Bolla> it = lista.iterator();
//
//		_data = new String[lista.size()];
//		_id = new int[lista.size()];
//		int k = 0;
//		while(it.hasNext())
//			{
//				Bolla messCl = (Bolla)it.next();
//				String codBol = String.valueOf(messCl.getCodice());
//				String[] dtMess = messCl.getData().replace("-", "/").split(" ");
//				_data[k] = codBol + "-" + dtMess[0]; //codBolla + dataBolla
//				_id[k]= messCl.getId();
//				k++;
//			}
//	}
	
	private void loadListaBolleTerzista(int id_terzista){
		//Load lista bolle del terzista
		listaBTer = ResourceClass.getResources(Bolla.class, Global._URLBollaTerz+id_terzista);
		Iterator<Bolla> it = listaBTer.iterator();

		_data3 = new String[listaBTer.size()];
		_id3 = new int[listaBTer.size()];
		_nomeLav = new String[listaBTer.size()];
		int k = 0;
		while(it.hasNext())
			{
				Bolla bollaCl = (Bolla)it.next();
				String codBol = String.valueOf(bollaCl.getCodice());
				String[] dtMess = bollaCl.getData().replace("-", "/").split(" ");
				_data3[k] = codBol + "-" + dtMess[0]; //codBolla + dataBolla
				_id3[k]= bollaCl.getId();
				_nomeLav[k] = bollaCl.getNomeLavorazione();
				System.out.println(_data3[k]);
				System.out.println(_id3[k]);
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
					GUI_Bolla window = new GUI_Bolla();
					window.frmBolleDiLavorazione.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
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
	
	public GUI_Bolla() {
//		loadTableDt(); //carica lista bolle
		loadListaTerzisti(); //carica lista terzisti
		initialize();
	}

	private void initialize() {	
		frmBolleDiLavorazione = new JFrame();
		frmBolleDiLavorazione.setResizable(false);
		frmBolleDiLavorazione.setTitle("Bolle di Lavorazione");
		frmBolleDiLavorazione.setBounds(100, 100, 662, 418);
		frmBolleDiLavorazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBolleDiLavorazione.getContentPane().setLayout(null);
		
		JLabel lblBolleDiLavorazione = new JLabel("Bolle assegnate:");
		lblBolleDiLavorazione.setBounds(10, 172, 106, 14);
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
		
		//**JList Bolle**
		final DefaultListModel listModel = new DefaultListModel();
		final JList list = new JList(listModel); //aggiunge alla Jlist numero + data delle bolle
		list.addMouseListener(new MouseAdapter() { //quando clicco su una bolla nella lista bolle
			@Override
			public void mouseReleased(MouseEvent e) {			
				int indice = list.getSelectedIndex();
				Bolla b = listaBTer.get(indice);
				String testo = b.getCodice();
				textField.setText(testo); //numero bolla selezionata nella lista
				System.out.println(testo);
				
				int k = list.getSelectedIndex();
				id = _id3[k]; //id bolla
				txtNomeLav.setText(_nomeLav[k]); //nome lavorazione della bolla selezionata
		         
				loadTableMatTeo(id); //carica i materiali teorici di quella bolla
				loadTableMatDaProdurre1(id); //carica i materiali da produrre di quella bolla
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
		btnVisualizzaNote.setBounds(311, 7, 147, 23);
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
		btnVisualizzaExtra.setBounds(311, 295, 147, 23);
		panel.add(btnVisualizzaExtra);
		
		list.setBounds(10, 197, 158, 143);
		frmBolleDiLavorazione.getContentPane().add(list);
		
		JLabel lblTerzisti = new JLabel("Terzisti:");
		lblTerzisti.setBounds(10, 11, 46, 14);
		frmBolleDiLavorazione.getContentPane().add(lblTerzisti);
		
		//**JList Terzisti**
		final JList list_2 = new JList(_data2);
		//Se clicco in un terzista della lista mi visualizza solo le bolle a lui assegnate
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int k = list_2.getSelectedIndex();
				idTerzista = _id2[k]; //id terzista
				
				System.out.print(idTerzista);
				
				listModel.removeAllElements();
				loadListaBolleTerzista(idTerzista);
				for (int i = 0; i<_data3.length; i++)
					listModel.addElement(_data3[i]); 
			}
		});
		
		list_2.setBounds(10, 36, 158, 125);
		frmBolleDiLavorazione.getContentPane().add(list_2);
		
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
	}
}
