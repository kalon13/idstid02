package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classResources.Bolla;
import classResources.Fase;
import classResources.Lavorazione;
import classResources.LavorazioneTerzista;
import classResources.Terzista;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.Component;
import javax.swing.JTextArea;

public class GUI_ModificaLavorazioni {

	public JFrame frmModificaLavorazioni;
	
	private String user;
	private int tipo;
	private JTabbedPane panel;
	JTable table_1,table;
	ArrayList index=new ArrayList();
	ArrayList id_Lav=new ArrayList();
	ArrayList id_Lav1=new ArrayList();
	ArrayList id_Lav2=new ArrayList();
	private JTable table_2;
	private JTable table_3;
	
	public GUI_ModificaLavorazioni(String user, int tipo) {
		this.user=user;
		this.tipo=tipo;
//		//Settiamo lo stile windows alle screen (basta metterlo nel main)
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificaLavorazioni = new JFrame();
		frmModificaLavorazioni.setTitle("Modifica Lavorazioni");
		frmModificaLavorazioni.setBounds(100, 100, 578, 307);
		frmModificaLavorazioni.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JTabbedPane(JTabbedPane.TOP);
		frmModificaLavorazioni.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel inserimentoLavorazione = new JPanel();
		panel.addTab("Inserimento lavorazione", null, inserimentoLavorazione, null);
		inserimentoLavorazione.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 46, 518, 126);
		inserimentoLavorazione.add(panel_1);
		panel_1.setLayout(null);
		final JComboBox cmbTipoLavorazioni = new JComboBox();
		cmbTipoLavorazioni.setBounds(0, 25, 147, 20);
		panel_1.add(cmbTipoLavorazioni);
		JLabel label_1 = new JLabel("Capacit\u00E0");
		label_1.setBounds(260, 0, 86, 14);
		panel_1.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel label_2 = new JLabel("Prezzo");
		label_2.setBounds(157, 0, 80, 14);
		panel_1.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel lblLavorazione = new JLabel("Lavorazione");
		lblLavorazione.setBounds(0, 0, 95, 14);
		panel_1.add(lblLavorazione);
		lblLavorazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		final JTextField txtCapacita = new JTextField();
		txtCapacita.setBounds(260, 25, 86, 20);
		panel_1.add(txtCapacita);
		txtCapacita.setColumns(10);
		
		final JTextField txtPrezzo = new JTextField();
		txtPrezzo.setBounds(157, 25, 86, 20);
		panel_1.add(txtPrezzo);
		txtPrezzo.setColumns(10);
		
												//BOTTONE INSERISCI LAVORAZIONE
		
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setBounds(429, 24, 89, 23);
		panel_1.add(btnInserisci);
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Terzista t = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+GUI_Autenticazione.ID);
				int lavorazSelezionata=cmbTipoLavorazioni.getSelectedIndex();
				lavorazSelezionata=(Integer) index.get(lavorazSelezionata);
				LavorazioneTerzista l = new LavorazioneTerzista(Double.parseDouble(txtPrezzo.getText()),0.0,Float.parseFloat(txtCapacita.getText()),
						0,lavorazSelezionata,t.getId());
				String id = ResourceClass.addResources("/lavorazioneterzista/", l);
				JOptionPane.showMessageDialog(null, "Lavorazione inserita correttamente", "Attenzione", 1);
				//m1.setId(Integer.parseInt(id));
				frmModificaLavorazioni.setVisible(false);
				GUI_ModificaLavorazioni windowLavorazioni=new GUI_ModificaLavorazioni(user,tipo);
				windowLavorazioni.frmModificaLavorazioni.setVisible(true);
			}
		});
		btnInserisci.setMnemonic(KeyEvent.VK_ENTER);
		
		//Componiamo la ComboBox
		List<Lavorazione> lista = ResourceClass.getResources(Lavorazione.class, Global._URLLavoraz);
		Iterator<Lavorazione> lavoraz = lista.iterator();
		index.clear();
		while (lavoraz.hasNext()){
			Lavorazione lavorazione=lavoraz.next();
			cmbTipoLavorazioni.addItem(lavorazione.getNome());
			index.add(lavorazione.getId());
		}
		
		final JPanel panel_2 = new JPanel();
		panel.addTab("Inserimento fase", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 31, 267, 61);
		panel_2.add(scrollPane_1);
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				visualFase();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Lavorazione", "Capacita"
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
		table.getColumnModel().getColumn(0).setPreferredWidth(122);
		table.getColumnModel().getColumn(1).setPreferredWidth(71);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(Color.BLUE);
		table.setAutoCreateColumnsFromModel(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(303, 31, 183, 61);
		panel_2.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fase", "Ordine"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(133);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(60);
		table_1.setRowSelectionAllowed(false);
		table_1.setEnabled(false);
		table_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		final JTextField txtFase = new JTextField();
		txtFase.setBounds(303, 132, 125, 20);
		panel_2.add(txtFase);
		txtFase.setColumns(10);
		
		final JTextField txtOrdine = new JTextField();
		txtOrdine.setBounds(428, 132, 58, 20);
		panel_2.add(txtOrdine);
		txtOrdine.setColumns(10);
		
															//BOTTONE INSERISCI FASE
		
		JButton btnInserisci_1 = new JButton("Inserisci");
		btnInserisci_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					int lavorazioneTerzSelezionata=(Integer) id_Lav.get(table.getSelectedRow());
					Fase f = new Fase(txtFase.getText(),Integer.parseInt(txtOrdine.getText()),lavorazioneTerzSelezionata);
					String id = ResourceClass.addResources("/fase", f);
					JOptionPane.showMessageDialog(null, "Fase inserita correttamente", "Attenzione", 1);
					frmModificaLavorazioni.setVisible(false);
					GUI_ModificaLavorazioni windowLavorazioni=new GUI_ModificaLavorazioni(user,tipo);
					windowLavorazioni.frmModificaLavorazioni.setVisible(true);
				}
			}
		});
		btnInserisci_1.setMnemonic(KeyEvent.VK_ENTER);
		btnInserisci_1.setBounds(392, 163, 94, 23);
		panel_2.add(btnInserisci_1);
		
		JLabel lblNuovaFase = new JLabel("Nuova Fase");
		lblNuovaFase.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNuovaFase.setBounds(303, 107, 111, 14);
		panel_2.add(lblNuovaFase);
		
		JLabel lblOrdine = new JLabel("Ordine");
		lblOrdine.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOrdine.setBounds(428, 107, 58, 14);
		panel_2.add(lblOrdine);
		
		Terzista t = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+GUI_Autenticazione.ID);
		
		//Visualizziamo le lavorazioni --> Per l'inserimento delle fasi
		List<LavorazioneTerzista> lista1 = ResourceClass.getResources(LavorazioneTerzista.class, Global._URLLavorazTerzista+"idTerzista/"+t.getId());
		Iterator<LavorazioneTerzista> lavTerzista = lista1.iterator();
		int numRow=-1;
		id_Lav.clear();
		while (lavTerzista.hasNext()){
			LavorazioneTerzista lavTerz=lavTerzista.next();
			numRow++;
			((DefaultTableModel) table.getModel()).insertRow(numRow, new Object[numRow+1][2]);
			Lavorazione lav = ResourceClass.getResource(Lavorazione.class, Global._URLLavoraz+lavTerz.getLavorazioneID());
			table.setValueAt(lav.getNome(), numRow, 0);
			id_Lav.add(lavTerz.getID());
			table.setValueAt(lavTerz.getCapProd(), numRow, 1);
		}
		
		//Tab Modifica lavorazione o fase
		JPanel modifica = new JPanel();
		panel.addTab("Modifica-Elimina lavorazione/fase", null, modifica, null);
		modifica.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 89, 283, 93);
		modifica.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				visualFaseMod();
			}
		});
		
		//table_2.setToolTipText("Modifica riga per riga attraverso tasto ENTER");
		table_2.setForeground(Color.BLUE);
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Lavorazione", "Prezzo", "Capacita"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(327, 89, 220, 92);
		modifica.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table_3.setToolTipText("Modifica riga per riga attraverso tasto ENTER");
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fase", "Ordine"
			}
		));
		table_3.getColumnModel().getColumn(0).setPreferredWidth(108);
		table_3.setForeground(Color.BLACK);
		scrollPane_3.setViewportView(table_3);
		
		JTextArea txtrPerOgniRiga = new JTextArea();
		txtrPerOgniRiga.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtrPerOgniRiga.setOpaque(false);
		txtrPerOgniRiga.setText("Per ogni riga:\r\n   -ENTER --> Modifica\r\n   -CANC  --> Elimina");
		txtrPerOgniRiga.setBounds(10, 11, 283, 67);
		modifica.add(txtrPerOgniRiga);
		table_2.getColumnModel().getColumn(0).setPreferredWidth(102);
		
		final Terzista t1 = ResourceClass.getResource(Terzista.class, Global._URLTerz+"utenteId/"+GUI_Autenticazione.ID);
		//Visualizziamo le lavorazioni --> Per la modifica o cancellazione
		List<LavorazioneTerzista> lista2 = ResourceClass.getResources(LavorazioneTerzista.class, Global._URLLavorazTerzista+"idTerzista/"+t1.getId());
		Iterator<LavorazioneTerzista> lavTerzista1 = lista2.iterator();
		int numRow1=-1;
		id_Lav1.clear();
		while (lavTerzista1.hasNext()){
			LavorazioneTerzista lavTerz1=lavTerzista1.next();
			numRow1++;
			Lavorazione lav1 = ResourceClass.getResource(Lavorazione.class, Global._URLLavoraz+lavTerz1.getLavorazioneID());
			id_Lav1.add(lavTerz1.getID());
			((DefaultTableModel) table_2.getModel()).insertRow(numRow1, new Object[numRow1+1][3]);
			table_2.setValueAt(lav1.getNome(), numRow1, 0);
			table_2.setValueAt(lavTerz1.getPrezzo(), numRow1, 1);
			table_2.setValueAt(lavTerz1.getCapProd(), numRow1, 2);
		}
		
		//Pressione del tasto sopra la tabella
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		KeyStroke canc = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
		table_2.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "UPDATE");
		table_2.getActionMap().put("UPDATE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //UPDATE LAVORAZIONE
            	//Il vettore delle righe è idLav_1
            	int lavorazSelezionata=(Integer) id_Lav1.get(table_2.getSelectedRow()); //ID della Lavorazione terzista
            	//Occorre il controllo che non ci siano lavorazioni in corso
            	boolean lavorazioneAperta=false;
            	List<Bolla> listab=ResourceClass.getResources(Bolla.class, "/bolla/check/"+lavorazSelezionata+"/"+t1.getId());
            	Iterator<Bolla> bolle = listab.iterator();
            	while(bolle.hasNext()){
            		Bolla b=bolle.next();
            		if(b.getStato()==0){
            			lavorazioneAperta=true;
            		}
            	}
            	if(!lavorazioneAperta){
            		String p=String.valueOf(table_2.getValueAt(table_2.getSelectedRow(), 1));
            		String c=String.valueOf(table_2.getValueAt(table_2.getSelectedRow(), 2));
            		LavorazioneTerzista l1 = new LavorazioneTerzista(Double.parseDouble(p),Float.parseFloat(c),lavorazSelezionata,t1.getId());
            		ResourceClass.updResources(LavorazioneTerzista.class, Global._URLLavorazTerzista, String.valueOf(lavorazSelezionata), l1);
            	}
            	else
            		JOptionPane.showMessageDialog(null, "Impossibile modificare. Hai ancora questa lavorazione in corso.", "Attenzione", 0);
        		
            	//Refresh della screen
        		frmModificaLavorazioni.setVisible(false);
        		GUI_ModificaLavorazioni windowLavorazioni=new GUI_ModificaLavorazioni(user,tipo);
        		windowLavorazioni.frmModificaLavorazioni.setVisible(true);
            }
        });
		table_2.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(canc, "DELETE");
		table_2.getActionMap().put("DELETE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	//DELETE LAVORAZIONE
            	int lavorazSelezionata=(Integer) id_Lav1.get(table_2.getSelectedRow()); //ID della Lavorazione terzista
            	//Occorre il controllo che non ci siano lavorazioni in corso
            	boolean lavorazioneAperta=false;
            	List<Bolla> listab=ResourceClass.getResources(Bolla.class, "/bolla/check/"+lavorazSelezionata+"/"+t1.getId());
            	Iterator<Bolla> bolle = listab.iterator();
            	while(bolle.hasNext()){
            		Bolla b=bolle.next();
            		if(b.getStato()==0){
            			lavorazioneAperta=true;
            		}
            	}
            	if(!lavorazioneAperta){
            		//Occorre eliminare anche tutte le fasi associate a questa
            		ResourceClass.delResources(Global._URLFase+"search/", String.valueOf(lavorazSelezionata));
            		//Eliminiamo ora la lavorazione
            		ResourceClass.delResources(Global._URLLavorazTerzista, String.valueOf(lavorazSelezionata));
            		JOptionPane.showMessageDialog(null, "Lavorazione e fasi eliminate correttamente.", "Attenzione", 1);
            	}
            	else
            		JOptionPane.showMessageDialog(null, "Impossibile eliminare. Hai ancora questa lavorazione in corso.", "Attenzione", 0);
            
            	//Refresh della screen
        		frmModificaLavorazioni.setVisible(false);
        		GUI_ModificaLavorazioni windowLavorazioni=new GUI_ModificaLavorazioni(user,tipo);
        		windowLavorazioni.frmModificaLavorazioni.setVisible(true);
            }
        });
		
		//Stessa cosa per la tabella delle fasi
		table_3.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "UPDATE");
		table_3.getActionMap().put("UPDATE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //UPDATE FASE
            	//Il vettore delle righe è idLav_2
            	int faseSelezionata=(Integer) id_Lav2.get(table_3.getSelectedRow());
				String n=String.valueOf(table_3.getValueAt(table_3.getSelectedRow(), 0));
				String o=String.valueOf(table_3.getValueAt(table_3.getSelectedRow(), 1));
				Fase f1 = new Fase(n,Integer.parseInt(o));
				ResourceClass.updResources(Fase.class, Global._URLFase, String.valueOf(faseSelezionata), f1);
				//Refresh della screen
				frmModificaLavorazioni.setVisible(false);
				GUI_ModificaLavorazioni windowLavorazioni=new GUI_ModificaLavorazioni(user,tipo);
				windowLavorazioni.frmModificaLavorazioni.setVisible(true);
            }
        });
		table_3.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(canc, "DELETE");
		table_3.getActionMap().put("DELETE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	//DELETE FASE
            	//Il vettore delle righe è idLav_2
            	int faseSelezionata=(Integer) id_Lav2.get(table_3.getSelectedRow());
            	ResourceClass.delResources(Global._URLFase, String.valueOf(faseSelezionata));
            	//Refresh della screen
				frmModificaLavorazioni.setVisible(false);
				GUI_ModificaLavorazioni windowLavorazioni=new GUI_ModificaLavorazioni(user,tipo);
				windowLavorazioni.frmModificaLavorazioni.setVisible(true);
            }
        });
	
		//Settiamo direttamente il tab della modifica
		panel.setSelectedIndex(2);
	}
	
	public void visualFase(){
	//Svuotiamo la tabella
	table_1.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Fase", "Ordine"
		}
	));
	List<Fase> fasi = ResourceClass.getResources(Fase.class, Global._URLFase+id_Lav.get(table.getSelectedRow()));
	Iterator<Fase> listaFasi = fasi.iterator();
	int righe=-1;
	while(listaFasi.hasNext()){
		Fase fase = listaFasi.next();
		righe++;
		((DefaultTableModel) table_1.getModel()).insertRow(righe, new Object[righe+1][2]);
		table_1.setValueAt(fase.getNome(), righe, 0);
		table_1.setValueAt(fase.getOrdine(), righe, 1);
	}
	}
	
	public void visualFaseMod(){
		//Svuotiamo la tabella
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fase", "Ordine"
			}
		));
		List<Fase> fasi = ResourceClass.getResources(Fase.class, Global._URLFase+id_Lav.get(table_2.getSelectedRow()));
		Iterator<Fase> listaFasi = fasi.iterator();
		id_Lav2.clear();
		int righe=-1;
		while(listaFasi.hasNext()){
			Fase fase = listaFasi.next();
			righe++;
			id_Lav2.add(fase.getId());
			((DefaultTableModel) table_3.getModel()).insertRow(righe, new Object[righe+1][2]);
			table_3.setValueAt(fase.getNome(), righe, 0);
			table_3.setValueAt(fase.getOrdine(), righe, 1);
		}
	}
	
	public void queryLavorazioni(){
		
	}
}
