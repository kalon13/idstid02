package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ListSelectionModel;

import main.Bolla;
import main.Terzista;

import utils.Global;
import utils.ResourceClass;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;

public class GUI_CancellazioneTerzistaOp {

	public JFrame frmCancellazioneTerzista;
	JList listTerzisti;
	ArrayList terz_id= new ArrayList();
	ArrayList listaTerzisti= new ArrayList();


	public GUI_CancellazioneTerzistaOp() {
		initialize();
		//Riempiamo la lista terzisti, solo quelli che non hanno lavorazioni in corso
    	List<Terzista> listat=ResourceClass.getResources(Terzista.class, Global._URLTerz);
    	Iterator<Terzista> terz = listat.iterator();
    	terz_id.clear();
    	
    	while(terz.hasNext()){
    		Terzista t=terz.next();
    		boolean lavorazioneAperta=false;
        	List<Bolla> listab=ResourceClass.getResources(Bolla.class, "/bolla/search/"+t.getId());
        	Iterator<Bolla> bolle = listab.iterator();
        	while(bolle.hasNext()){
        		Bolla b=bolle.next();
        		if(b.getStato()==2){
        			lavorazioneAperta=true;
        		}
        	}
        	if(!lavorazioneAperta){
        		terz_id.add(t.getId());
        		listaTerzisti.add(t.getRagioneSociale());
        	}
    	}
    	
    	listTerzisti.setListData(listaTerzisti.toArray());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancellazioneTerzista = new JFrame();
		frmCancellazioneTerzista.setResizable(false);
		frmCancellazioneTerzista.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUI_Home.windowScelta.frmSceltaTerzista.setEnabled(true);
			}
		});
		frmCancellazioneTerzista.setTitle("Cancellazione Terzista");
		frmCancellazioneTerzista.setBounds(100, 100, 288, 242);
		frmCancellazioneTerzista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		frmCancellazioneTerzista.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblElencoTerzistiChe = new JLabel("Elenco Terzisti che \u00E8 possibile rimuovere:");
		lblElencoTerzistiChe.setBounds(10, 11, 252, 14);
		panel.add(lblElencoTerzistiChe);
		
		JLabel lblNomeAzienda = new JLabel("Nome Azienda");
		lblNomeAzienda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomeAzienda.setBounds(10, 36, 80, 14);
		panel.add(lblNomeAzienda);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 201, 80);
		panel.add(scrollPane);
		
		listTerzisti = new JList();
		listTerzisti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listTerzisti);
		listTerzisti.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listTerzisti.setBackground(Color.WHITE);
		listTerzisti.setVisibleRowCount(5);
		listTerzisti.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listTerzisti.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.setMnemonic(KeyEvent.VK_ENTER);
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Rimozione
				int terzSelezionato=listTerzisti.getSelectedIndex();
				if(terzSelezionato!=-1){
					terzSelezionato=(Integer) terz_id.get(terzSelezionato);
					
					//Occorrono a ritroso tutte le cancellazioni nelle altre tabelle dove c'� questo terzista
					//e inviare una comunicazione al terzista
					
					String[] choices = {"Si", "No"};
					int response=JOptionPane.showOptionDialog(null,"Sicuro di voler rimuovere il Terzista?","Rimozione terzista",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,choices,"No");
					if(response==0){
						//Occorre risettare come ancora da assegnare le bolle che aveva assegnate
						Bolla bDaRiassegnare=new Bolla();
						ResourceClass.updResources(Bolla.class, Global._URLBollaRiassegna, String.valueOf(terzSelezionato), bDaRiassegnare);
						//Eliminiamo proprio il terzista
						ResourceClass.delResources(Global._URLTerz, String.valueOf(terzSelezionato));
						JOptionPane.showMessageDialog(null, "Terzista eliminato correttamente.\nTutte le bolle a lui assegnate sono state\nimpostate da riassegnare.", "Attenzione", 1);
						frmCancellazioneTerzista.dispose();
						GUI_Home.windowScelta.frmSceltaTerzista.dispose();
						GUI_Home.windowScelta = new GUI_SceltaTerzista();
						GUI_Home.windowScelta.frmSceltaTerzista.setVisible(true);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Selezionare prima un Terzista.", "Attenzione", 0);
			}
		});
		btnRimuovi.setBounds(183, 180, 89, 23);
		panel.add(btnRimuovi);
	}

}
