package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import classResources.Bolla;
import classResources.Fase;
import classResources.Lavorazione;
import classResources.LavorazioneTerzista;
import classResources.Terzista;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

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
	private JButton btnModificaLavorazioni;
	private JButton btnModificaAnagrafica;
	
	GUI_ModificaAnagrafica windowAnagrafica;
	GUI_ModificaLavorazioni windowLavorazioni;
	private JButton btnCancellaProfilo;

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
		indirizzo.setBounds(132, 37, 326, 20);
		indirizzo.setEditable(false);
		indirizzo.setColumns(40);
		
		citta = new JTextField(t.getCitta());
		citta.setBounds(132, 63, 326, 20);
		citta.setEditable(false);
		citta.setColumns(40);
		
		prov = new JTextField(t.getProvincia());
		prov.setBounds(132, 89, 326, 20);
		prov.setEditable(false);
		prov.setColumns(40);
		
		cap = new JTextField(t.getCap());
		cap.setBounds(132, 115, 326, 20);
		cap.setEditable(false);
		cap.setColumns(40);
		
		telefono = new JTextField(t.getTelefono());
		telefono.setBounds(132, 141, 326, 20);
		telefono.setEditable(false);
		telefono.setColumns(40);
		
		fax = new JTextField(t.getFax());
		fax.setBounds(132, 167, 326, 20);
		fax.setEditable(false);
		fax.setColumns(40);
		
		email = new JTextField(t.getEmail());
		email.setBounds(132, 193, 326, 20);
		email.setEditable(false);
		email.setColumns(40);
		
		piva = new JTextField(t.getpIva());
		piva.setBounds(132, 219, 326, 20);
		piva.setEditable(false);
		piva.setColumns(40);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:");
		lblIndirizzo.setBounds(10, 43, 44, 14);
		
		JLabel lblCitt = new JLabel("Citt\u00E0");
		lblCitt.setBounds(10, 69, 23, 14);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 95, 43, 14);
		
		JLabel lblCap = new JLabel("CAP");
		lblCap.setBounds(10, 121, 20, 14);
		
		JLabel lblFax = new JLabel("Fax");
		lblFax.setBounds(10, 173, 18, 14);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 199, 24, 14);
		
		JLabel lblPiva = new JLabel("P.IVA");
		lblPiva.setBounds(10, 225, 27, 14);
		
		nome = new JTextField(t.getRagioneSociale());
		nome.setBounds(132, 11, 326, 20);
		nome.setFont(new Font("Tahoma", Font.BOLD, 11));
		nome.setForeground(new Color(0, 0, 0));
		nome.setEditable(false);
		nome.setBackground(Color.LIGHT_GRAY);
		nome.setColumns(40);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 147, 42, 14);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 261, 607, 2);
		separator.setForeground(Color.BLACK);
		
		JButton btnIndietro = new JButton("Home");
		btnIndietro.setBounds(481, 138, 136, 23);
		btnIndietro.setMnemonic(KeyEvent.VK_BACK_SPACE);
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDatiTerzistaTr.setVisible(false);
				GUI_Home windowHome = new GUI_Home();
				windowHome.frmHome.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 281, 602, 103);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 418, 327, 71);
		
		btnModificaLavorazioni = new JButton("Modifica Lavorazioni");
		btnModificaLavorazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowLavorazioni= new GUI_ModificaLavorazioni();
				windowLavorazioni.frmModificaLavorazioni.setVisible(true);
				//frmDatiTerzistaTr.setVisible(false);
			}
		});
		btnModificaLavorazioni.setMnemonic(KeyEvent.VK_L);
		btnModificaLavorazioni.setBounds(481, 39, 136, 23);
		
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
		frmDatiTerzistaTr.getContentPane().setLayout(null);
		frmDatiTerzistaTr.getContentPane().add(btnModificaLavorazioni);
		frmDatiTerzistaTr.getContentPane().add(separator);
		frmDatiTerzistaTr.getContentPane().add(lblCitt);
		frmDatiTerzistaTr.getContentPane().add(lblProvincia);
		frmDatiTerzistaTr.getContentPane().add(lblIndirizzo);
		frmDatiTerzistaTr.getContentPane().add(lblCap);
		frmDatiTerzistaTr.getContentPane().add(lblTelefono);
		frmDatiTerzistaTr.getContentPane().add(lblFax);
		frmDatiTerzistaTr.getContentPane().add(cap);
		frmDatiTerzistaTr.getContentPane().add(citta);
		frmDatiTerzistaTr.getContentPane().add(indirizzo);
		frmDatiTerzistaTr.getContentPane().add(prov);
		frmDatiTerzistaTr.getContentPane().add(telefono);
		frmDatiTerzistaTr.getContentPane().add(fax);
		frmDatiTerzistaTr.getContentPane().add(email);
		frmDatiTerzistaTr.getContentPane().add(piva);
		frmDatiTerzistaTr.getContentPane().add(nome);
		frmDatiTerzistaTr.getContentPane().add(lblEmail);
		frmDatiTerzistaTr.getContentPane().add(lblPiva);
		frmDatiTerzistaTr.getContentPane().add(scrollPane_1);
		frmDatiTerzistaTr.getContentPane().add(btnIndietro);
		frmDatiTerzistaTr.getContentPane().add(scrollPane);
		
		btnModificaAnagrafica = new JButton("Modifica Anagrafica");
		btnModificaAnagrafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowAnagrafica = new GUI_ModificaAnagrafica();
				windowAnagrafica.frmModificaAnagrafica.setVisible(true);
				frmDatiTerzistaTr.setVisible(false);
			}
		});
		btnModificaAnagrafica.setMnemonic(KeyEvent.VK_A);
		btnModificaAnagrafica.setBounds(481, 10, 136, 23);
		frmDatiTerzistaTr.getContentPane().add(btnModificaAnagrafica);
		
		btnCancellaProfilo = new JButton("Cancella Profilo");
		btnCancellaProfilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Terzista t=ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+
						Autenticazione.getSessione().getUtente().getUserId());
		int terzSelezionato=t.getId();
		boolean lavorazioneAperta=false;
		List<Bolla> listab=ResourceClass.getResources(Bolla.class, "/bolla/search/"+terzSelezionato);
    	Iterator<Bolla> bolle = listab.iterator();
    	while(bolle.hasNext()){
    		Bolla b=bolle.next();
    		if(b.getStato()==2){
    			lavorazioneAperta=true;
    		}
    	}
    	if(!lavorazioneAperta){
    		String[] choices = {"Si", "No"};
			int response=JOptionPane.showOptionDialog(null,"Sicuro di voler rimuovere il tuo profilo dal sistema e,\nquindi, di non" +
					" ricevere più lavori dall'azienda?","Rimozione terzista",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,choices,"No");
			if(response==0){
				//Occorre risettare come ancora da assegnare le bolle che aveva assegnate
				Bolla bDaRiassegnare=new Bolla();
				ResourceClass.updResources(Bolla.class, Global._URLBollaRiassegna, String.valueOf(terzSelezionato), bDaRiassegnare);
				//Eliminiamo proprio il terzista
				ResourceClass.delResources(Global._URLTerz, String.valueOf(terzSelezionato));
				JOptionPane.showMessageDialog(null, "Cancellazione avvenuta correttamente.", "Attenzione", 1);
				//Occorrono a ritroso tutte le cancellazioni nelle altre tabelle dove c'è questo terzista
				//e inviare una comunicazione all'azienda
				//Facciamo il Logout
				MultivaluedMap<String, String> param = new MultivaluedMapImpl();
                param.add("sid", Autenticazione.getSessione().getSessionID());
                ResourceClass.getService().path(Global._URLAutLogout).
                        accept(MediaType.APPLICATION_JSON).post(String.class, param);
                frmDatiTerzistaTr.dispose();
                GUI_Autenticazione windowAuth = new GUI_Autenticazione();
                windowAuth.getFrame().setVisible(true);
			}
    	}
    	else
    		JOptionPane.showMessageDialog(null, "Cancellazione negata, hai ancora delle lavorazioni in corso.", "Attenzione", 0);
			}
		});
		btnCancellaProfilo.setMnemonic(KeyEvent.VK_P);
		btnCancellaProfilo.setBounds(481, 75, 136, 23);
		frmDatiTerzistaTr.getContentPane().add(btnCancellaProfilo);
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
