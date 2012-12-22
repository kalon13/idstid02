package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import classResources.Fase;
import classResources.Lavorazione;
import classResources.LavorazioneTerzista;
import classResources.Terzista;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridLayout;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class GUI_DatiTerzistaTr {

	public JFrame frmDatiTerzistaTr;
	private int ID;
	
	private JTextField indirizzo;
	private JTextField citta;
	private JTextField prov;
	private JTextField cap;
	private JTextField telefono;
	private JTextField fax;
	private JTextField email;
	private JTextField piva;
	private JTextField nome;
	private JLabel lblTelefono;
	private JTable table;
	private JTable tableFase;
	
	private Terzista t;
	ArrayList id_Lav=new ArrayList();

	/**
	 * Create the application.
	 */
	public GUI_DatiTerzistaTr() {
		t = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+
				Autenticazione.getSessione().getUtente().getUserId());
		initialize();
		visualLavorazioni();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatiTerzistaTr = new JFrame();
		frmDatiTerzistaTr.setTitle("Dati Terzista");
		frmDatiTerzistaTr.setBounds(100, 100, 643, 553);
		frmDatiTerzistaTr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		indirizzo = new JTextField(t.getIndirizzo());
		indirizzo.setEditable(false);
		indirizzo.setColumns(40);
		
		citta = new JTextField(t.getCitta());
		citta.setEditable(false);
		citta.setColumns(40);
		
		prov = new JTextField(t.getProvincia());
		prov.setEditable(false);
		prov.setColumns(40);
		
		cap = new JTextField(t.getCap());
		cap.setEditable(false);
		cap.setColumns(40);
		
		telefono = new JTextField(t.getTelefono());
		telefono.setEditable(false);
		telefono.setColumns(40);
		
		fax = new JTextField(t.getFax());
		fax.setEditable(false);
		fax.setColumns(40);
		
		email = new JTextField(t.getEmail());
		email.setEditable(false);
		email.setColumns(40);
		
		piva = new JTextField(t.getpIva());
		piva.setEditable(false);
		piva.setColumns(40);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:");
		
		JLabel lblCitt = new JLabel("Citt\u00E0");
		
		JLabel lblProvincia = new JLabel("Provincia");
		
		JLabel lblCap = new JLabel("CAP");
		
		JLabel lblFax = new JLabel("Fax");
		
		JLabel lblEmail = new JLabel("Email");
		
		JLabel lblPiva = new JLabel("P.IVA");
		
		nome = new JTextField(t.getRagioneSociale());
		nome.setFont(new Font("Tahoma", Font.BOLD, 11));
		nome.setForeground(new Color(0, 0, 0));
		nome.setEditable(false);
		nome.setBackground(Color.LIGHT_GRAY);
		nome.setColumns(40);
		
		lblTelefono = new JLabel("Telefono");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setMnemonic(KeyEvent.VK_BACK_SPACE);
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDatiTerzistaTr.setVisible(false);
				GUI_GestioneDati windowGestione = new GUI_GestioneDati();
				windowGestione.frmGestioneDati.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmDatiTerzistaTr.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 619, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblCitt, Alignment.LEADING)
							.addComponent(lblProvincia, Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblIndirizzo)
											.addGap(26))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCap)
											.addGap(50)))
									.addComponent(lblTelefono)
									.addComponent(lblFax))
								.addGap(52)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(cap, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(citta, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(indirizzo)
									.addComponent(prov, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(telefono, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(fax, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(email, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(piva, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(nome, 0, 0, Short.MAX_VALUE))
								.addGap(120)))
						.addComponent(lblEmail)
						.addComponent(lblPiva)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnIndietro))
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblIndirizzo)
						.addComponent(indirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCitt)
						.addComponent(citta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblProvincia)
						.addComponent(prov, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCap)
						.addComponent(cap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(telefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefono))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(fax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFax))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmail)
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPiva)
						.addComponent(piva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIndietro))
					.addGap(26))
		);
		
		tableFase = new JTable();
		scrollPane_1.setViewportView(tableFase);
		tableFase.setSize(new Dimension(1, 1));
		tableFase.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fase", "Ordine"
			}
		)
		{
			boolean[] columnEditables = new boolean[] {
				false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		tableFase.getColumnModel().getColumn(0).setPreferredWidth(99);
		tableFase.getColumnModel().getColumn(1).setPreferredWidth(56);
		tableFase.setRowSelectionAllowed(false);
		tableFase.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tableFase.setEnabled(false);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setForeground(Color.BLUE);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				visualFase();
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSize(new Dimension(1, 1));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Lavorazione", "Capacit\u00E0", "Prezzo", "Media Qualit\u00E0", "N\u00B0 Votazioni"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(146);
		table.getColumnModel().getColumn(1).setPreferredWidth(71);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(79);
		table.getColumnModel().getColumn(4).setPreferredWidth(68);
		table.setAutoCreateColumnsFromModel(false);
		frmDatiTerzistaTr.getContentPane().setLayout(groupLayout);
	}
	
	public void visualLavorazioni(){
		List<LavorazioneTerzista> lista1 = ResourceClass.getResources(LavorazioneTerzista.class, Global._URLLavorazTerzista+"idTerzista/"+t.getId());
		Iterator<LavorazioneTerzista> lavTerzista = lista1.iterator();
		int numRow=-1;
		id_Lav.clear();
		while (lavTerzista.hasNext()){
			LavorazioneTerzista lavTerz=lavTerzista.next();
			numRow++;
			((DefaultTableModel) table.getModel()).insertRow(numRow, new Object[numRow+1][4]);
			Lavorazione lav = ResourceClass.getResource(Lavorazione.class, Global._URLLavoraz+lavTerz.getLavorazioneID());
			table.setValueAt(lav.getNome(), numRow, 0);
			id_Lav.add(lavTerz.getID());
			table.setValueAt(lavTerz.getCapProd(), numRow, 1);
			table.setValueAt(lavTerz.getPrezzo(), numRow, 2);
			table.setValueAt(lavTerz.getQualita(), numRow, 3);
			table.setValueAt(lavTerz.getNumVotaz(), numRow, 4);
		}
	}
	
	public void visualFase(){
		//Svuotiamo la tabella
		tableFase.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fase", "Ordine"
			}
		)
		{
			boolean[] columnEditables = new boolean[] {
				false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		if(table.getSelectedRow()!=-1){
			List<Fase> fasi = ResourceClass.getResources(Fase.class, Global._URLFase+id_Lav.get(table.getSelectedRow()));
			Iterator<Fase> listaFasi = fasi.iterator();
			int righe=-1;
			while(listaFasi.hasNext()){
				Fase fase = listaFasi.next();
				righe++;
				((DefaultTableModel) tableFase.getModel()).insertRow(righe, new Object[righe+1][2]);
				tableFase.setValueAt(fase.getNome(), righe, 0);
				tableFase.setValueAt(fase.getOrdine(), righe, 1);
			}
		}
	}
}
