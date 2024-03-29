package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import main.*;
import utils.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI_DatiTerzistaOp {

	public JFrame frmDatiTerzistaOp;
	private String user;
	
	private JTextField indirizzo;
	private JTextField citta;
	private JTextField prov;
	private JTextField cap;
	private JTextField tel;
	private JTextField fax;
	private JTextField email;
	private JTextField piva;
	private JTextField nome;
	private JLabel lblTelefono;
	private JTable table;
	

	/**
	 * Create the application.
	 */
	
	private Terzista t;
	int indiceTerzista;
	
	//index contiene l'id del terzista da visualizzare
	public GUI_DatiTerzistaOp(int index) {
		indiceTerzista=index;
		t = ResourceClass.getResource(Terzista.class, Global._URLTerz+index);
		initialize();
		visualLavorazioni();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatiTerzistaOp = new JFrame();
		frmDatiTerzistaOp.setResizable(false);
		frmDatiTerzistaOp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDatiTerzistaOp.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUI_Home.windowScelta.frmSceltaTerzista.setEnabled(true);
			}
		});
		frmDatiTerzistaOp.setTitle("Dati Terzista");
		frmDatiTerzistaOp.setBounds(100, 100, 510, 445);
		
		indirizzo = new JTextField(t.getIndirizzo());
		indirizzo.setBounds(132, 37, 349, 20);
		indirizzo.setEditable(false);
		indirizzo.setColumns(40);
		
		citta = new JTextField(t.getCitta());
		citta.setBounds(132, 63, 349, 20);
		citta.setEditable(false);
		citta.setColumns(40);
		
		prov = new JTextField(t.getProvincia());
		prov.setBounds(132, 89, 349, 20);
		prov.setEditable(false);
		prov.setColumns(40);
		
		cap = new JTextField(t.getCap());
		cap.setBounds(132, 115, 349, 20);
		cap.setEditable(false);
		cap.setColumns(40);
		
		tel = new JTextField(t.getTelefono());
		tel.setBounds(132, 141, 349, 20);
		tel.setEditable(false);
		tel.setColumns(40);
		
		fax = new JTextField(t.getFax());
		fax.setBounds(132, 167, 349, 20);
		fax.setEditable(false);
		fax.setColumns(40);
		
		email = new JTextField(t.getEmail());
		email.setBounds(132, 193, 349, 20);
		email.setEditable(false);
		email.setColumns(40);
		
		piva = new JTextField(t.getpIva());
		piva.setBounds(132, 219, 349, 20);
		piva.setEditable(false);
		piva.setColumns(40);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:");
		lblIndirizzo.setBounds(10, 43, 112, 14);
		
		JLabel lblCitt = new JLabel("Citt\u00E0");
		lblCitt.setBounds(10, 69, 112, 14);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 95, 112, 14);
		
		JLabel lblCap = new JLabel("CAP");
		lblCap.setBounds(10, 121, 112, 14);
		
		JLabel lblFax = new JLabel("Fax");
		lblFax.setBounds(10, 173, 112, 14);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 199, 112, 14);
		
		JLabel lblPiva = new JLabel("P.IVA");
		lblPiva.setBounds(10, 225, 112, 14);
		
		nome = new JTextField(t.getRagioneSociale());
		nome.setBounds(132, 11, 349, 20);
		nome.setFont(new Font("Tahoma", Font.BOLD, 11));
		nome.setForeground(new Color(0, 0, 0));
		nome.setEditable(false);
		nome.setBackground(Color.LIGHT_GRAY);
		nome.setColumns(40);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 147, 112, 14);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 257, 477, 2);
		separator.setForeground(Color.BLACK);
		frmDatiTerzistaOp.getContentPane().setLayout(null);
		frmDatiTerzistaOp.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 290, 471, 106);
		frmDatiTerzistaOp.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Lavorazione", "Capacit\u00E0", "Prezzo", "Media Qualit\u00E0", "N\u00B0 Votazioni"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(87);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(71);
		table.setAutoCreateColumnsFromModel(false);
		frmDatiTerzistaOp.getContentPane().add(lblCitt);
		frmDatiTerzistaOp.getContentPane().add(lblProvincia);
		frmDatiTerzistaOp.getContentPane().add(lblIndirizzo);
		frmDatiTerzistaOp.getContentPane().add(lblCap);
		frmDatiTerzistaOp.getContentPane().add(lblTelefono);
		frmDatiTerzistaOp.getContentPane().add(lblFax);
		frmDatiTerzistaOp.getContentPane().add(cap);
		frmDatiTerzistaOp.getContentPane().add(citta);
		frmDatiTerzistaOp.getContentPane().add(indirizzo);
		frmDatiTerzistaOp.getContentPane().add(prov);
		frmDatiTerzistaOp.getContentPane().add(tel);
		frmDatiTerzistaOp.getContentPane().add(fax);
		frmDatiTerzistaOp.getContentPane().add(email);
		frmDatiTerzistaOp.getContentPane().add(piva);
		frmDatiTerzistaOp.getContentPane().add(nome);
		frmDatiTerzistaOp.getContentPane().add(lblEmail);
		frmDatiTerzistaOp.getContentPane().add(lblPiva);
		
        /*********************Aggiunto menu*************************************/
		//menu app = new menu(frmDatiTerzistaOp, "Bolla");
		//frmDatiTerzistaOp.setVisible(true);
	}
	
	public void visualLavorazioni(){
		List<LavorazioneTerzista> lista1 = ResourceClass.getResources(LavorazioneTerzista.class, Global._URLLavorazTerzista+"idTerzista/"+indiceTerzista);
		Iterator<LavorazioneTerzista> lavTerzista = lista1.iterator();
		int numRow=-1;
		while (lavTerzista.hasNext()){
			LavorazioneTerzista lavTerz=lavTerzista.next();
			numRow++;
			((DefaultTableModel) table.getModel()).insertRow(numRow, new Object[numRow+1][4]);
			Lavorazione lav = ResourceClass.getResource(Lavorazione.class, Global._URLLavoraz+lavTerz.getLavorazioneID());
			table.setValueAt(lav.getNome(), numRow, 0);
			table.setValueAt(lavTerz.getCapProd(), numRow, 1);
			table.setValueAt(lavTerz.getPrezzo(), numRow, 2);
			table.setValueAt(lavTerz.getQualita(), numRow, 3);
			table.setValueAt(lavTerz.getNumVotaz(), numRow, 4);
		}
	}
}
