import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import classResources.Fattura;
import classResources.Fattura_Lavorazione;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GUI_Fatturazione {

	JFrame frmElenco;
	private JTable table;
	private JTable table_1;
	private JTextField textNum;
	private JTextField textDt;
	private JTextField textImpToT;
	private static GUI_RegistraDDT windowRegDDT;
	private static String[] _data;
	private static int[] _id;
	private static String[][] _dataLav;
	private static Object[] _titlesLav={"Lavorazione", "Prezzo"};
	
	private void loadTableDt(){
		List<Fattura> lista = ResourceClass.getResources(Fattura.class, Global._URLFatt);
		 Iterator<Fattura> it=lista.iterator();
		 _data = new String[lista.size()];
		 _id = new int[lista.size()];
	     int k = 0;
	    while(it.hasNext())
       {//[riga][colonna]
	      Fattura fattCl = (Fattura)it.next();
         String nmFatt = String.valueOf(fattCl.getNumFattura());
         String[] dtFatt = fattCl.getDataEmissione().replace("-", "/").split(" ");
         _data[k] = nmFatt+"-"+dtFatt[0];
         _id[k]= fattCl.getId();
          k++;
         }
	}
	/**
	 * Create the application.
	 */
	public GUI_Fatturazione() {
		loadTableDt();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmElenco = new JFrame();
		frmElenco.setTitle("Gestione Fatturazione");
		frmElenco.setBounds(100, 100, 450, 314);
		frmElenco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmElenco.getContentPane().setLayout(null);
		
		JLabel lblFatture = new JLabel("Fatture:");
		lblFatture.setBounds(10, 11, 46, 14);
		frmElenco.getContentPane().add(lblFatture);
		
		final JList list = new JList(_data);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			 int k = list.getSelectedIndex();
			 int id = _id[k];
	         Fattura fatt = ResourceClass.getResource(Fattura.class, Global._URLFatt+"/"+id);
	         textNum.setText(Integer.toString(fatt.getNumFattura()));
	         textDt.setText(fatt.getDataEmissione());
	         textImpToT.setText(Double.toString(fatt.getImporto()));
	         List<Fattura_Lavorazione> lsLav = fatt.getFattLavorazione();
			 Iterator<Fattura_Lavorazione> it=lsLav.iterator();
	         int cntDt = lsLav.size();
			 int cntTit = _titlesLav.length;
			 _dataLav = new String[cntDt][cntTit];
		      int t = 0;
			    while(it.hasNext())
		        {//[riga][colonna]
			      Fattura_Lavorazione fattLv = it.next();
		          if(k<cntDt){
		           _dataLav[k][0] = fattLv.getNomeLavorazione();
		           _dataLav[k][1] = Double.toString(fattLv.getPrezzoLavorazione());
		           k++;
		          }
		        }
		     DefaultTableModel dfm=new DefaultTableModel (_dataLav, _titlesLav);
	         table.setModel(dfm);
			}
		});
		list.setBounds(10, 32, 160, 178);
		frmElenco.getContentPane().add(list);
		
		JButton btnNewFatt = new JButton("Nuova Fattura");
		btnNewFatt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_CreaFattura window = new GUI_CreaFattura();
				window.frmCreazioneFattura.setVisible(true);
			}
		});
		btnNewFatt.setBounds(10, 221, 160, 23);
		frmElenco.getContentPane().add(btnNewFatt);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(178, 11, 254, 233);
		frmElenco.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFatturaN = new JLabel("Fattura n\u00B0:");
		lblFatturaN.setBounds(10, 11, 69, 14);
		panel.add(lblFatturaN);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(137, 11, 49, 14);
		panel.add(lblData);
		
		JLabel lblImportoTotale = new JLabel("Importo Totale:");
		lblImportoTotale.setBounds(10, 39, 107, 14);
		panel.add(lblImportoTotale);
		
		JLabel lblContenutoFattura = new JLabel("Contenuto Fattura:");
		lblContenutoFattura.setBounds(10, 90, 132, 14);
		panel.add(lblContenutoFattura);
		
		JLabel lblExtraconsumi = new JLabel("Extraconsumi:");
		lblExtraconsumi.setBounds(137, 90, 84, 14);
		panel.add(lblExtraconsumi);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 115, 117, 107);
		panel.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 115, 107, 107);
		panel.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		textNum = new JTextField();
		textNum.setEditable(false);
		textNum.setBounds(89, 8, 38, 20);
		panel.add(textNum);
		textNum.setColumns(10);
		
		textDt = new JTextField();
		textDt.setEditable(false);
		textDt.setBounds(181, 8, 63, 20);
		panel.add(textDt);
		textDt.setColumns(10);
		
		JCheckBox chckbxPagata = new JCheckBox("Pagata");
		chckbxPagata.setEnabled(false);
		chckbxPagata.setBounds(10, 60, 97, 23);
		panel.add(chckbxPagata);
		
		textImpToT = new JTextField();
		textImpToT.setEditable(false);
		textImpToT.setBounds(158, 36, 86, 20);
		panel.add(textImpToT);
		textImpToT.setColumns(10);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmElenco.dispose();
			}
		});
		btnEsci.setBounds(343, 246, 89, 23);
		frmElenco.getContentPane().add(btnEsci);
		
	}
}
