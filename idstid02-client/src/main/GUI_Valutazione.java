package main;

import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI_Valutazione {

	public JFrame frmValutazione;	//Ci vorrebbe il getFrame() e lasciarlo private
	private JTable tableValutazione;
	ArrayList index=new ArrayList();
	ArrayList qualita=new ArrayList();
	ArrayList numVotazioni=new ArrayList();
	ArrayList lavorazioni=new ArrayList();


	public GUI_Valutazione() {
		initialize();
		trovaBolleDaValutare();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmValutazione = new JFrame();
		frmValutazione.setResizable(false);
		frmValutazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmValutazione.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUI_Home.windowScelta.frmSceltaTerzista.setEnabled(true);
			}
		});
		frmValutazione.setTitle("Valutazione");
		frmValutazione.setBounds(100, 100, 705, 272);
		
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
		scrollPane.setBounds(10, 77, 669, 132);
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
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Short.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableValutazione.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableValutazione.getColumnModel().getColumn(1).setPreferredWidth(101);
		tableValutazione.getColumnModel().getColumn(2).setPreferredWidth(142);
		tableValutazione.getColumnModel().getColumn(3).setPreferredWidth(189);
		tableValutazione.getColumnModel().getColumn(4).setPreferredWidth(69);
		tableValutazione.setRowSelectionAllowed(false);
		tableValutazione.setAutoCreateColumnsFromModel(false);
		
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		tableValutazione.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "UPDATE");
		tableValutazione.getActionMap().put("UPDATE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	int bollaSelezionata=(Integer) index.get(tableValutazione.getSelectedRow());
            	int lavorazSelezionata=(Integer) lavorazioni.get(tableValutazione.getSelectedRow());
            	String voto=String.valueOf(tableValutazione.getValueAt(tableValutazione.getSelectedRow(), 4));
            	try{
            		Float.parseFloat(voto);
            		if(Float.parseFloat(voto)>=0 && Float.parseFloat(voto)<=10){
            			Bolla b=new Bolla(bollaSelezionata);
            			ResourceClass.updResources(Bolla.class, Global._URLBollaValuta, String.valueOf(bollaSelezionata), b);
            			//Ora occorre fare la media sulla tab lavorazioneterzista
            			double qualitaAttuale=(Double) qualita.get(tableValutazione.getSelectedRow());
            			int numVotazAttuale=(Integer) numVotazioni.get(tableValutazione.getSelectedRow());
            			double sommaAttuale=qualitaAttuale*numVotazAttuale;
            			double qualitaNuova=(sommaAttuale+Float.parseFloat(voto))/(numVotazAttuale+1);
            			LavorazioneTerzista lUpd=new LavorazioneTerzista(qualitaNuova, numVotazAttuale+1);
            			ResourceClass.updResources(LavorazioneTerzista.class, Global._URLLavorazTerzista+"valuta/", String.valueOf(lavorazSelezionata), lUpd);
            			JOptionPane.showMessageDialog(null, "Bolla valutata correttamente.", "Attenzione", 1);
            			//Refresh della screen
            			frmValutazione.dispose();
            			GUI_Valutazione windowValutazione=new GUI_Valutazione();
            			windowValutazione.frmValutazione.setVisible(true);
            		}
            		else
            			JOptionPane.showMessageDialog(null, "Sono ammesse votazioni da 0 a 10", "Attenzione", 0);
            	}
            	catch(Exception e){
            		JOptionPane.showMessageDialog(null, "Inserire un valore valido!", "Attenzione", 0);
            	}
            }
        });
		
		JTextArea txtrPerOgniRiga = new JTextArea();
		txtrPerOgniRiga.setEditable(false);
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
		qualita.clear();
		numVotazioni.clear();
		lavorazioni.clear();
		int numRow=-1;
		while (bolle.hasNext()){
			Bolla bolla=bolle.next();
			index.add(bolla.getId());
			lavorazioni.add(bolla.getLavorazioneTerzistaId());
			numRow++;
				Terzista terz=ResourceClass.getResource(Terzista.class, Global._URLTerz+bolla.getTerzistaId());
				String nomeTerz=terz.getRagioneSociale();
				LavorazioneTerzista lavTerz=ResourceClass.getResource(LavorazioneTerzista.class, Global._URLLavorazTerzista+"select/"+bolla.getLavorazioneTerzistaId());
				int lavId=lavTerz.getLavorazioneID();
				qualita.add(lavTerz.getQualita());
				numVotazioni.add(lavTerz.getNumVotaz());
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
