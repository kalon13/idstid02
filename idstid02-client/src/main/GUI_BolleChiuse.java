package main;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import classResources.Bolla;
import classResources.MaterialeDaProdurre;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;

public class GUI_BolleChiuse {

	private int idBollaC; //id bolla chiusa
	private int idBollaM; //id bolla chiusa con morto
	JFrame frmBolleChiuse;
	private JTable tableChiuse;
	private JTable tableMorti;
	List<Bolla> listaChiuse = null; //lista bolle chiuse
	private static String[] _dataChiuse; //bolle chiuse
	private static int[] _idChiuse;
	private static String[] _lavChiuse; //lavorazioni bolle chiuse
	private static String[] _terzChiuse; //nomi dei terzisti delle bolle chiuse
	List<Bolla> listaMorti = null; //lista bolle con morti
	private static String[] _dataMorti; //bolle con morti
	private static int[] _idMorti;
	private static String[] _lavMorti; //lavorazioni bolle con morto
	private static String[] _terzMorti; //nomi dei terzisti delle bolle con morto
	List<MaterialeDaProdurre> listaMChiuse = null; //lista materiale bolle chiuse
	List<MaterialeDaProdurre> listaMMorti = null; //lista materiale bolle chiuse con morto
	
	//TableModel per table materiali bolle chiuse
	@SuppressWarnings("serial")
	public DefaultTableModel dmChiuse = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Descrizione", "Qta", "udm"
			})
			{
				boolean[] columnEditables = new boolean[] { //non editabili colonne
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			
	//TableModel per table materiali bolle con morti
	@SuppressWarnings("serial")
	public DefaultTableModel dmMorti = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Descrizione", "Qta", "udm", "Numero Morti"
			})
	{
		boolean[] columnEditables = new boolean[] { //non editabili colonne
				false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};

	private JTextField txtLavChiuse;
	private JTextField txtLavMorti;
	private JTextField txtTerzChiuse;
	private JTextField txtTerzMorti;
	
	//Lista bolle chiuse
	private void loadListaBolleChiuse(int stato){
		//Load lista bolle del terzista
		listaChiuse = ResourceClass.getResources(Bolla.class, Global._URLBollaCM+stato);
		Iterator<Bolla> it = listaChiuse.iterator();

		_dataChiuse = new String[listaChiuse.size()];
		_idChiuse = new int[listaChiuse.size()];
		_lavChiuse = new String[listaChiuse.size()];
		_terzChiuse = new String[listaChiuse.size()];
		int k = 0;
		while(it.hasNext())
			{
				Bolla bollaCl = (Bolla)it.next();
				String codBol = String.valueOf(bollaCl.getCodice());
				String[] dtMess = bollaCl.getData().replace("-", "/").split(" ");
				_dataChiuse[k] = codBol + "-" + dtMess[0]; //codBolla + dataBolla
				_idChiuse[k]= bollaCl.getId();
				_lavChiuse[k] = bollaCl.getNomeLavorazione();
				_terzChiuse[k] = bollaCl.getRagSociale();
				k++;
			}
	}
	
	//Lista bolle con morto
	private void loadListaBolleMorti(int stato){
		//Load lista bolle del terzista
		listaMorti = ResourceClass.getResources(Bolla.class, Global._URLBollaCM+stato);
		Iterator<Bolla> it = listaMorti.iterator();

		_dataMorti = new String[listaMorti.size()];
		_idMorti = new int[listaMorti.size()];
		_lavMorti = new String[listaMorti.size()];
		_terzMorti = new String[listaMorti.size()];
		int k = 0;
		while(it.hasNext())
			{
				Bolla bollaCl = (Bolla)it.next();
				String codBol = String.valueOf(bollaCl.getCodice());
				String[] dtMess = bollaCl.getData().replace("-", "/").split(" ");
				_dataMorti[k] = codBol + "-" + dtMess[0]; //codBolla + dataBolla
				_idMorti[k]= bollaCl.getId();
				_lavMorti[k] = bollaCl.getNomeLavorazione();
				_terzMorti[k] = bollaCl.getRagSociale();
				k++;
			}
	}
	
	//Lista materiali (da produrre) bolle chiuse
	private void loadTableMat(int numBolla){
		dmChiuse.setRowCount(0); //pulisce la table (dmChiuse = datamodel della table)
		
		listaMChiuse = ResourceClass.getResources(MaterialeDaProdurre.class, Global._URLMatDaProd1+numBolla);
		Iterator<MaterialeDaProdurre> it = listaMChiuse.iterator();
		while(it.hasNext())
			{			
			MaterialeDaProdurre matCl = (MaterialeDaProdurre)it.next();
				String qtaMat = String.valueOf(matCl.getQuantita());
				String desc = String.valueOf(matCl.getDescrizione());
				String udm = String.valueOf(matCl.getUdm());
				//Aggiunge i valori alla tabella
				((DefaultTableModel) tableChiuse.getModel()).insertRow(
						tableChiuse.getRowCount(), new Object[]{desc, qtaMat, udm});
			}
	}

	//Lista materiali (da produrre) bolle con morti
	private void loadTableMatM(int numBolla){
		dmMorti.setRowCount(0); //pulisce la table (dmMorti = datamodel della table)
			
		listaMMorti = ResourceClass.getResources(MaterialeDaProdurre.class, Global._URLMatDaProd1+numBolla);
		Iterator<MaterialeDaProdurre> it = listaMMorti.iterator();
		while(it.hasNext())
		{			
			MaterialeDaProdurre matCl = (MaterialeDaProdurre)it.next();
			String qtaMat = String.valueOf(matCl.getQuantita());
			String desc = String.valueOf(matCl.getDescrizione());
			String udm = String.valueOf(matCl.getUdm());
			String numMorti = String.valueOf(matCl.getNumeroMorti());
			//Aggiunge i valori alla tabella
			((DefaultTableModel) tableMorti.getModel()).insertRow(
					tableMorti.getRowCount(), new Object[]{desc, qtaMat, udm, numMorti});
		}
	}
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_BolleChiuse window = new GUI_BolleChiuse();
					window.frmBolleChiuse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_BolleChiuse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBolleChiuse = new JFrame();
		frmBolleChiuse.setTitle("Bolle Chiuse");
		frmBolleChiuse.setBounds(100, 100, 554, 436);
		frmBolleChiuse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBolleChiuse.getContentPane().setLayout(null);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmBolleChiuse.dispose();
			}
		});
		btnEsci.setBounds(441, 363, 89, 23);
		frmBolleChiuse.getContentPane().add(btnEsci);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 520, 165);
		frmBolleChiuse.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Bolle chiuse:");
		label.setBounds(10, 11, 146, 14);
		panel.add(label);
		
		//**Lista Chiuse**
		DefaultListModel listModelChiuse = new DefaultListModel();
		final JList listChiuse = new JList(listModelChiuse);
		listChiuse.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == true)
				{
					int k = listChiuse.getSelectedIndex();
					idBollaC = _idChiuse[k]; //id della bolla selezionata
					txtLavChiuse.setText(_lavChiuse[k]); //nome lavorazione della bolla selezionata
					txtTerzChiuse.setText(_terzChiuse[k]); //nome del terzista che ha fatto quella bolla
					dmChiuse.setRowCount(0);
					loadTableMat(idBollaC); //carica i materiali da produrre di quella bolla
				}
			}
		});
		loadListaBolleChiuse(3); //3 bolla chiusa
		//Nella lista delle bolle chiuse si visualizza num+data delle bolle chiuse
		for (int i = 0; i<_dataChiuse.length; i++)
			listModelChiuse.addElement(_dataChiuse[i]);
		listChiuse.setBounds(10, 36, 128, 116);
		panel.add(listChiuse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 36, 362, 116);
		panel.add(scrollPane);
		
		tableChiuse = new JTable(dmChiuse);
		scrollPane.setViewportView(tableChiuse);
		
		txtLavChiuse = new JTextField();
		txtLavChiuse.setEditable(false);
		txtLavChiuse.setBounds(148, 8, 183, 20);
		panel.add(txtLavChiuse);
		txtLavChiuse.setColumns(10);
		
		txtTerzChiuse = new JTextField();
		txtTerzChiuse.setEditable(false);
		txtTerzChiuse.setBounds(341, 8, 169, 20);
		panel.add(txtTerzChiuse);
		txtTerzChiuse.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 187, 520, 165);
		frmBolleChiuse.getContentPane().add(panel_1);
		
		JLabel lblBolleChiuseCon = new JLabel("Bolle chiuse con morto:");
		lblBolleChiuseCon.setBounds(10, 11, 274, 14);
		panel_1.add(lblBolleChiuseCon);
		
		//**Lista con morti**
		DefaultListModel listModelMorti = new DefaultListModel();
		final JList listMorti = new JList(listModelMorti);
		listMorti.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == true)
				{
					int k = listMorti.getSelectedIndex();
					idBollaM = _idMorti[k]; //id della bolla selezionata
					txtLavMorti.setText(_lavMorti[k]); //nome lavorazione della bolla selezionata
					txtTerzMorti.setText(_terzMorti[k]);
					dmMorti.setRowCount(0);
					loadTableMatM(idBollaM); //carica i materiali da produrre di quella bolla
				}
			}
		});
		loadListaBolleMorti(4); //4 bolla chiusa con morto
		//Nella lista delle bolle chiuse si visualizza num+data delle bolle chiuse
		for (int i = 0; i<_dataMorti.length; i++)
			listModelMorti.addElement(_dataMorti[i]);
		listMorti.setBounds(10, 36, 128, 116);
		panel_1.add(listMorti);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(148, 36, 362, 118);
		panel_1.add(scrollPane_1);
		
		tableMorti = new JTable(dmMorti);
		scrollPane_1.setViewportView(tableMorti);
		
		txtLavMorti = new JTextField();
		txtLavMorti.setEditable(false);
		txtLavMorti.setColumns(10);
		txtLavMorti.setBounds(148, 8, 183, 20);
		panel_1.add(txtLavMorti);
		
		txtTerzMorti = new JTextField();
		txtTerzMorti.setEditable(false);
		txtTerzMorti.setColumns(10);
		txtTerzMorti.setBounds(341, 8, 169, 20);
		panel_1.add(txtTerzMorti);
	}
}
