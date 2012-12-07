package main;

import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextArea;

import classResources.Bolla;
import classResources.Lavorazione;
import classResources.LavorazioneTerzista;
import classResources.Terzista;

public class GUI_Valutazione {

	public JFrame frmValutazione;	//Ci vorrebbe il getFrame() e lasciarlo private
	private JTable tableValutazione;
	private String user;
	private int tipo;
	ArrayList index=new ArrayList();
	ArrayList terzisti=new ArrayList();
	ArrayList lavorazioni=new ArrayList();


	public GUI_Valutazione(String user, int tipo) {
		this.user=user;
		this.tipo=tipo;
		initialize();
		trovaBolleDaValutare();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmValutazione = new JFrame();
		frmValutazione.setTitle("Valutazione");
		frmValutazione.setBounds(100, 100, 676, 307);
		frmValutazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 0));
		panel.setMaximumSize(new Dimension(0, 0));
		panel.setDoubleBuffered(false);
		frmValutazione.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblElencoLavorazioniChiuse = new JLabel("Elenco lavorazioni chiuse non valutate:");
		lblElencoLavorazioniChiuse.setBounds(10, 11, 332, 14);
		panel.add(lblElencoLavorazioniChiuse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 640, 132);
		panel.add(scrollPane);
		
		tableValutazione = new JTable();
		tableValutazione.setToolTipText("Tasto INVIO per valutare, riga per riga.");
		scrollPane.setViewportView(tableValutazione);
		tableValutazione.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome Azienda", "Codice Bolla", "Nome Lavorazione", "Data Chiusura", "Voto (0..10)"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableValutazione.getColumnModel().getColumn(0).setPreferredWidth(184);
		tableValutazione.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableValutazione.getColumnModel().getColumn(2).setPreferredWidth(169);
		tableValutazione.getColumnModel().getColumn(3).setPreferredWidth(108);
		tableValutazione.getColumnModel().getColumn(4).setPreferredWidth(73);
		tableValutazione.setRowSelectionAllowed(false);
		tableValutazione.setEnabled(false);
		tableValutazione.setAutoCreateColumnsFromModel(false);
		
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		tableValutazione.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "UPDATE");
		tableValutazione.getActionMap().put("UPDATE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//            	int lavorazSelezionata=(Integer) id_Lav1.get(table_2.getSelectedRow()); //ID della Lavorazione terzista
//            	//Occorre il controllo che non ci siano lavorazioni in corso
//            	boolean lavorazioneAperta=false;
//            	List<Bolla> listab=ResourceClass.getResources(Bolla.class, "/bolla/check/"+lavorazSelezionata+"/"+t1.getId());
//            	Iterator<Bolla> bolle = listab.iterator();
//            	while(bolle.hasNext()){
//            		Bolla b=bolle.next();
//            		if(b.getStato()==0){
//            			lavorazioneAperta=true;
//            		}
//            	}
//            	if(!lavorazioneAperta){
//            		String p=String.valueOf(table_2.getValueAt(table_2.getSelectedRow(), 1));
//            		String c=String.valueOf(table_2.getValueAt(table_2.getSelectedRow(), 2));
//            		LavorazioneTerzista l1 = new LavorazioneTerzista(Double.parseDouble(p),Float.parseFloat(c),lavorazSelezionata,t1.getId());
//            		ResourceClass.updResources(LavorazioneTerzista.class, Global._URLLavorazTerzista, String.valueOf(lavorazSelezionata), l1);
//            	}
//            	else
//            		JOptionPane.showMessageDialog(null, "Impossibile modificare. Hai ancora questa lavorazione in corso.", "Attenzione", 0);
//        		
//            	//Refresh della screen
//        		frmModificaLavorazioni.setVisible(false);
//        		GUI_ModificaLavorazioni windowLavorazioni=new GUI_ModificaLavorazioni(user,tipo);
//        		windowLavorazioni.frmModificaLavorazioni.setVisible(true);
            	
            }
        });
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_GestioneDati windowGestione = new GUI_GestioneDati(user, tipo);
				windowGestione.frmGestioneDati.setVisible(true);
				frmValutazione.setVisible(false);
			}
		});
		btnIndietro.setMnemonic(KeyEvent.VK_BACK_SPACE);
		btnIndietro.setBounds(579, 239, 71, 23);
		panel.add(btnIndietro);
		
		JTextArea txtrPerOgniRiga = new JTextArea();
		txtrPerOgniRiga.setText("Per ogni riga:\r\n   -ENTER --> Valuta");
		txtrPerOgniRiga.setOpaque(false);
		txtrPerOgniRiga.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtrPerOgniRiga.setBounds(10, 25, 283, 55);
		panel.add(txtrPerOgniRiga);
	}
	
	private void trovaBolleDaValutare(){
		List<Bolla> lista = ResourceClass.getResources(Bolla.class, Global._URLBollaValuta);
		Iterator<Bolla> bolle = lista.iterator();
		index.clear();
		//terzisti.clear();
		//lavorazioni.clear();
		int numRow=-1;
		while (bolle.hasNext()){
			Bolla bolla=bolle.next();
			index.add(bolla.getId());
			numRow++;
			//terzisti.add(bolla.getTerzista_Id());
			//lavorazioni.add(bolla.getLavorazioneTerzista_Id());
				Terzista terz=ResourceClass.getResource(Terzista.class, Global._URLTerz+bolla.getTerzistaId());
				String nomeTerz=terz.getRagioneSociale();
				LavorazioneTerzista lavTerz=ResourceClass.getResource(LavorazioneTerzista.class, Global._URLLavorazTerzista+"select/"+bolla.getLavorazioneTerzistaId());
				int lavId=lavTerz.getLavorazioneID();
				Lavorazione lav=ResourceClass.getResource(Lavorazione.class, Global._URLLavoraz+lavId);
				String nomeLav=lav.getNome();
			((DefaultTableModel) tableValutazione.getModel()).insertRow(numRow, new Object[numRow+1][5]);
			tableValutazione.setValueAt(nomeTerz, numRow, 0);
			tableValutazione.setValueAt(bolla.getCodice(), numRow, 1);
			tableValutazione.setValueAt(nomeLav, numRow, 2);
			tableValutazione.setValueAt(bolla.getData(), numRow, 3);
		}
	}

}
