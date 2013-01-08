package main;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import classResources.Lavorazione;
import classResources.LavorazioneTerzista;
import classResources.Terzista;
import classResources.Utente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class GUI_SceltaTerzista {

	public JFrame frmSceltaTerzista;
	
	JComboBox cmbTipoLavorazione;
	JLabel lblTipoLavorazione;
	JList listTerzisti;
	
	static GUI_DatiTerzistaOp windowDati;
	static GUI_Valutazione windowValutazione;
	static GUI_CancellazioneTerzistaOp windowCanc;
	
	ArrayList index=new ArrayList();
	ArrayList index1=new ArrayList();
	ArrayList list=new ArrayList();
	int selezioneLavorazione;

	/**
	 * Create the application.
	 */
	
	public void compose(){
		
		lblTipoLavorazione.setText(cmbTipoLavorazione.getSelectedItem().toString());
		selezioneLavorazione=cmbTipoLavorazione.getSelectedIndex();
		List<LavorazioneTerzista> lista1 = ResourceClass.getResources(LavorazioneTerzista.class, Global._URLLavorazTerzista+index.get(selezioneLavorazione));
		Iterator<LavorazioneTerzista> lavTerzista = lista1.iterator();
		index1.clear();
		while (lavTerzista.hasNext()){
			LavorazioneTerzista lavTerz=lavTerzista.next();
			index1.add(lavTerz.getTerzistaID());
		}
		//Ora per ogni terzista id devo fare una query nella tab Terzista ed elencare il nome
		list.clear();
		if(!index1.isEmpty()){
			for(int i=0;i<index1.size();i++){
				Terzista terz=ResourceClass.getResource(Terzista.class, Global._URLTerz+index1.get(i));
				list.add(terz.getRagioneSociale());
			}
			listTerzisti.setListData(list.toArray());
		}
		else
			listTerzisti.setListData(new Object[0]); //Svuotiamo la lista
		
	}
	
	public GUI_SceltaTerzista() {
		
		initialize();
		
		//Componiamo la ComboBox
		List<Lavorazione> lista = ResourceClass.getResources(Lavorazione.class, Global._URLLavoraz);
		Iterator<Lavorazione> lavoraz = lista.iterator();
		while (lavoraz.hasNext()){
			Lavorazione lavorazione=lavoraz.next();
				index.add(lavorazione.getId());
				cmbTipoLavorazione.addItem(lavorazione.getNome());
		}
		
		compose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSceltaTerzista = new JFrame();
		frmSceltaTerzista.setTitle("Scelta Terzista - Valutazione Lavorazioni - Cancellazione Terzista");
		frmSceltaTerzista.setResizable(false);
		frmSceltaTerzista.setBounds(100, 100, 516, 362);
		frmSceltaTerzista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmSceltaTerzista.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSelezionaIlTipo = new JLabel("Seleziona il tipo di lavorazione:");
		lblSelezionaIlTipo.setBounds(31, 40, 231, 14);
		lblSelezionaIlTipo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblSelezionaIlTipo);
		
		cmbTipoLavorazione = new JComboBox();
		cmbTipoLavorazione.setBounds(31, 66, 231, 20);
		cmbTipoLavorazione.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				lblTipoLavorazione.setText(cmbTipoLavorazione.getSelectedItem().toString());
				compose();
			}
		});
		
		cmbTipoLavorazione.setMaximumRowCount(20);
		panel.add(cmbTipoLavorazione);
		
		JLabel lblTerzistiCheEffettuano = new JLabel("Terzisti che effettuano:");
		lblTerzistiCheEffettuano.setBounds(31, 97, 231, 14);
		panel.add(lblTerzistiCheEffettuano);
		
		lblTipoLavorazione = new JLabel("");
		lblTipoLavorazione.setBounds(31, 122, 231, 14);
		lblTipoLavorazione.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblTipoLavorazione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipoLavorazione.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblTipoLavorazione);
		
		JButton btnAvanti = new JButton("Visualizza Dati Terzista");
		btnAvanti.setToolTipText("Visualizza i dati del terzista selezionato");
		btnAvanti.setBounds(31, 252, 231, 23);
		btnAvanti.setMnemonic(KeyEvent.VK_ENTER);
		btnAvanti.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(listTerzisti.getSelectedIndex()!=-1){
					//ID del terzista selezionato dalla lista
					int indice=listTerzisti.getSelectedIndex();
					indice=(Integer) index1.get(indice);
					windowDati = new GUI_DatiTerzistaOp(indice);
					windowDati.frmDatiTerzistaOp.setVisible(true);
					frmSceltaTerzista.setEnabled(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Selezionare prima un Terzista.", "Attenzione", 0);
			}
			
		});
		panel.add(btnAvanti);
		
//		JButton btnIndietro = new JButton("Home");
//		btnIndietro.setBounds(307, -2, 153, 23);
//		btnIndietro.setMnemonic(KeyEvent.VK_BACK_SPACE);
//		btnIndietro.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frmSceltaTerzista.setVisible(false);
//				GUI_Home windowHome=new GUI_Home();
//				windowHome.frmHome.setVisible(true);
//			}
//		});
//		panel.add(btnIndietro);
		
		JButton btnModificaValuta = new JButton("Valuta Bolle Chiuse");
		btnModificaValuta.setToolTipText("Valuta le bolle di tutti i terzisti");
		btnModificaValuta.setMnemonic(KeyEvent.VK_V);
		btnModificaValuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Finestra di valutazione
				windowValutazione = new GUI_Valutazione();
				windowValutazione.frmValutazione.setVisible(true);
				frmSceltaTerzista.setEnabled(false);
			}
		});
		btnModificaValuta.setBounds(335, 218, 143, 23);
		panel.add(btnModificaValuta);
		
		JButton btnElimina = new JButton("Rimuovi Terzisti");
		btnElimina.setToolTipText("Scegli il terzista da rimuovere");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowCanc = new GUI_CancellazioneTerzistaOp();
				windowCanc.frmCancellazioneTerzista.setVisible(true);
				frmSceltaTerzista.setEnabled(false);
			}
		});
		btnElimina.setMnemonic(KeyEvent.VK_R);
		btnElimina.setBounds(335, 252, 143, 23);
		panel.add(btnElimina);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 147, 231, 95);
		panel.add(scrollPane);
		
		listTerzisti = new JList();
		scrollPane.setViewportView(listTerzisti);
		listTerzisti.setVisibleRowCount(5);
		listTerzisti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTerzisti.setSelectionForeground(Color.WHITE);
		listTerzisti.setForeground(Color.BLUE);
		listTerzisti.setBackground(Color.WHITE);
		listTerzisti.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		/*********************Aggiunto menu*************************************/
		menu app = new menu(frmSceltaTerzista, "Terz");
		
	}
}

