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

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI_Bolla {

	JFrame frmBolleDiLavorazione;
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private int id; //id bolla
	
	GUI_Messaggio messaggio;
	GUI_Extraconsumo extraconsumo;
	List<Bolla> lista = null;
	List<MaterialeTeorico> lista1 = null;
	List<MaterialeDaProdurre> listaMDaProd = null; //senza join
	List<MaterialeDaProdurre> listaMDaProd1 = null; //con join
	List<Materiale> lista2 = null;
	private static String[] _data;
	private static int[] _id;
	private static String[] _data1;
	private static int[] _id1;
	
	private void loadTableDt(){
		//Load lista bolle
		lista = ResourceClass.getResources(Bolla.class, Global._URLBolla);
		Iterator<Bolla> it = lista.iterator();

		_data = new String[lista.size()];
		_id = new int[lista.size()];
		int k = 0;
		while(it.hasNext())
			{
				Bolla messCl = (Bolla)it.next();
				String codBol = String.valueOf(messCl.getCodice());
				String[] dtMess = messCl.getData().replace("-", "/").split(" ");
				_data[k] = codBol + "-" + dtMess[0]; //codBolla + dataBolla
				_id[k]= messCl.getId();
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
	
	private String[] loadTableMatTeo1(String numBolla){
		//Load lista materiali teorici senza JOIN
		dm.setRowCount(0); //pulisce la table_1 (dm = datamodel della table_1)
		
		lista1 = ResourceClass.getResources(MaterialeTeorico.class, Global._URLMatTeoSearch1+numBolla);
		Iterator<MaterialeTeorico> it = lista1.iterator();

		_data1 = new String[lista1.size()];
		_id1 = new int[lista1.size()];
		int k = 0;
		while(it.hasNext())
			{			
				MaterialeTeorico matCl = (MaterialeTeorico)it.next();
				//aggiunto
				String nmMat = String.valueOf(matCl.getId());
				String idMat = String.valueOf(matCl.getId_materiale());
				String idBolla = String.valueOf(matCl.getId_bolla());
				String qtaMat = String.valueOf(matCl.getQuantita());
				_data1[k] = nmMat + "-" + idMat + "-" + idBolla + "-" + qtaMat;
				_id1[k]= matCl.getId();
				k++;
				//Aggiunge i valori alla tabella
				((DefaultTableModel) table_1.getModel()).insertRow(
			            table_1.getRowCount(), new Object[]{nmMat, idMat, idBolla, qtaMat});
			}		
		return _data1;
	}
	
	private void loadTableMatDaProdurre(String numBolla){
		//Load lista materiali da produrre senza JOIN
		dmPrima.setRowCount(0); //pulisce la table (dmPrima = datamodel della table)
		
		listaMDaProd = ResourceClass.getResources(MaterialeDaProdurre.class, Global._URLMatDaProd+numBolla);
		Iterator<MaterialeDaProdurre> it = listaMDaProd.iterator();

		_data1 = new String[listaMDaProd.size()];
		_id1 = new int[listaMDaProd.size()];
		int k = 0;
		while(it.hasNext())
			{			
			MaterialeDaProdurre matCl = (MaterialeDaProdurre)it.next();
				//aggiunto
				String nmMat = String.valueOf(matCl.getId());
				String qtaMat = String.valueOf(matCl.getQuantita());
				String numMorti = String.valueOf(matCl.getNumeroMorti());
				String qtaProdotta = String.valueOf(matCl.getQuantitaProdotta());
				String qtaSpedita = String.valueOf(matCl.getQuantitaSpedita());
				_data1[k] = nmMat + "-" + qtaMat + "-" + numMorti + "-" + qtaProdotta + "-" + qtaSpedita;
				_id1[k]= matCl.getId();
				k++;
				//Aggiunge i valori alla tabella
				((DefaultTableModel) table.getModel()).insertRow(
			            table.getRowCount(), new Object[]{nmMat, qtaMat, numMorti, qtaProdotta, qtaSpedita});
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
	
//	Materiale m = new Materiale(idMatTer, Double.parseDouble(textQnt.getText()));
//	ResourceClass.updResources(Materiale.class, Global._URLMag, String.valueOf(idMatTer), m);
//	frameUpdMat.dispose();
	
	//TableModel per table (materiali da produrre)
	public DefaultTableModel dmPrima = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Descrizione", "Qta", "udm", "numeroMorti", "qtaProdotta", "QtaSpedita"
			}
		);
	
	//TableModel per table_1 (materiali teorici)
	public DefaultTableModel dm = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Desc", "Quantità", "udm", "CostoUnit", 
			}
		);
	
	public GUI_Bolla() {
		
		loadTableDt(); //carica lista bolle
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		frmBolleDiLavorazione = new JFrame();
		frmBolleDiLavorazione.setResizable(false);
		frmBolleDiLavorazione.setTitle("Bolle di Lavorazione");
		frmBolleDiLavorazione.setBounds(100, 100, 662, 418);
		frmBolleDiLavorazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBolleDiLavorazione.getContentPane().setLayout(null);
		
		JLabel lblBolleDiLavorazione = new JLabel("Bolle assegnate:");
		lblBolleDiLavorazione.setBounds(10, 11, 106, 14);
		frmBolleDiLavorazione.getContentPane().add(lblBolleDiLavorazione);
		
		JPanel panel = new JPanel();
		panel.setBounds(178, 11, 468, 329);
		frmBolleDiLavorazione.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Numero bolla:");
		lblNewLabel.setBounds(10, 11, 87, 14);
		panel.add(lblNewLabel);
		
		//**textField**
		//Quando cambia il contenuto del textField (quindi il numero di bolla)
		textField = new JTextField();
//		textField.getDocument().addDocumentListener(new DocumentListener() {
//			     public void removeUpdate(DocumentEvent e) {
//			        // TODO add code!
//			     }
//			     public void insertUpdate(DocumentEvent e) {
//			        // TODO add code!
////						String numeroBolla = textField.getText();
//			     }
//			     public void changedUpdate(DocumentEvent e) {
//			        // TODO add code!
//			     }
//			  });
			
		textField.setEditable(false);
		textField.setBounds(107, 8, 44, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblMaterialiDaProdurre = new JLabel("Materiali da produrre:");
		lblMaterialiDaProdurre.setBounds(10, 28, 141, 14);
		panel.add(lblMaterialiDaProdurre);
		
		JLabel lblMaterialiTeorici = new JLabel("Materiali teorici:");
		lblMaterialiTeorici.setBounds(10, 136, 112, 14);
		panel.add(lblMaterialiTeorici);
		
		JScrollPane scrollPane_1 = new JScrollPane();

		scrollPane_1.setBounds(10, 46, 448, 79);
		panel.add(scrollPane_1);
		
		//Inizializza crea tabelle materiali
		table = new JTable(dmPrima);
		scrollPane_1.setViewportView(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 161, 448, 74);
		panel.add(scrollPane);
		table_1 = new JTable(dm);
		table_1.setEnabled(false);
		scrollPane.setViewportView(table_1);
		
		
		
		final DefaultListModel modelloLista=new DefaultListModel();
		JList list_1 =new JList(modelloLista);
		list_1.setBounds(10, 246, 353, 38);
		panel.add(list_1);
		
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
		
		final JList list = new JList(_data); //aggiunge alla Jlist numero + data delle bolle
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {			
				int indice = list.getSelectedIndex();
				Bolla b = lista.get(indice);
				String testo = b.getCodice();
				textField.setText(testo); //numero bolla selezionata nella lista
				System.out.println(testo);
				
				int k = list.getSelectedIndex();
				id = _id[k]; //id bolla
		         
//				String[] data = new String[1000];
//				data = loadTableMatTeo1(testo); //carica la lista materiali teorici in base al numero bolla selezionato
				loadTableMatTeo(id);
//				loadTableMatDaProdurre(testo);
				loadTableMatDaProdurre1(id);
//				for (int i=0; i<lista1.size(); i++)
//				{
//					System.out.println(data[i]);
//					modelloLista.addElement(data[i]);
//				}
			}
		});
		
		//**btnVisualizzaNote**
		JButton btnVisualizzaNote = new JButton("Visualizza Note");
		//Quando clicco Visualizza Bolle richiama la GUI Messaggio passandogli il num di bolla
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
		JButton btnRichiediExtra = new JButton("Richiedi Extra");
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
		
		list.setBounds(10, 28, 158, 161);
		frmBolleDiLavorazione.getContentPane().add(list);
		
		JButton btnAnnullaBolla = new JButton("Annulla Bolla");
		btnAnnullaBolla.setBounds(10, 200, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnAnnullaBolla);
		
		JButton btnChiudiParzialmente = new JButton("Chiudi Parzialmente");
		btnChiudiParzialmente.setBounds(10, 234, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnChiudiParzialmente);
		
		JButton btnChiudiBolla = new JButton("Chiudi Bolla");
		btnChiudiBolla.setBounds(10, 268, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnChiudiBolla);
		
		//Al premere di Invio in una cella di table_1 richiama l'Update
		dmPrima.addTableModelListener(new TableModelListener(){
			@Override
			public void tableChanged(TableModelEvent e) {
				int col = e.getColumn();
				int row = e.getFirstRow();
//				System.out.println(col + "  " + row + " ");
				if(col > 2) {
//					System.out.println(dmPrima.getValueAt(e.getFirstRow(), e.getColumn()));
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
