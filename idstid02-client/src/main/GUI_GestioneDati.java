package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import classResources.Bolla;
import classResources.Terzista;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

public class GUI_GestioneDati {

	public JFrame frmGestioneDati;
//	private String user;
//	private int tipo;

	public static GUI_SceltaTerzista windowScelta;
	public static GUI_DatiTerzistaTr windowDatiTr;
	public static GUI_ModificaDati windowModificaDati;
	public static GUI_Valutazione windowValutazione;
	public static GUI_CancellazioneTerzistaOp windowCanc;
	
	public GUI_GestioneDati() {
		initialize();
	}

	private void initialize() {
		frmGestioneDati = new JFrame();
		frmGestioneDati.setResizable(false);
		frmGestioneDati.setTitle("Gestione Dati");
		frmGestioneDati.setBounds(100, 100, 390, 280);
		frmGestioneDati.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnVisualizza = new JButton("Visualizza Dati Terzista");
		btnVisualizza.setMnemonic(KeyEvent.VK_V);
		btnVisualizza.setBounds(115, 34, 156, 23);
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Se è l'Operatore dell'azienda che visualizza
				if(Autenticazione.getSessione().getTipoUtente()==2){
					windowScelta = new GUI_SceltaTerzista();
					windowScelta.frmSceltaTerzista.setVisible(true);
					frmGestioneDati.setVisible(false);
				}
				else
				{
					windowDatiTr = new GUI_DatiTerzistaTr();
					windowDatiTr.frmDatiTerzistaTr.setVisible(true);
					frmGestioneDati.setVisible(false);
				}
			}
			
		});
		frmGestioneDati.getContentPane().setLayout(null);
		btnVisualizza.setPreferredSize(new Dimension(111, 23));
		frmGestioneDati.getContentPane().add(btnVisualizza);
		
		JButton btnModificaValuta = new JButton();
		if(Autenticazione.getSessione().getUtente().getTipo()==2) btnModificaValuta.setText("Valuta Bolle Chiuse");
		else btnModificaValuta.setText("Modifica Dati");
		btnModificaValuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Autenticazione.getSessione().getTipoUtente()==1){
					windowModificaDati = new GUI_ModificaDati();
					windowModificaDati.frmModificaDati.setVisible(true);
					frmGestioneDati.setVisible(false);
				}
				else{
					//Finestra di valutazione
					windowValutazione = new GUI_Valutazione();
					windowValutazione.frmValutazione.setVisible(true);
					frmGestioneDati.setVisible(false);
				}
			}
		});
		btnModificaValuta.setMnemonic(KeyEvent.VK_M);
		btnModificaValuta.setBounds(115, 91, 156, 25);
		frmGestioneDati.getContentPane().add(btnModificaValuta);
		
		JButton btnElimina = new JButton();
		if(Autenticazione.getSessione().getUtente().getTipo()==2) btnElimina.setText("Rimuovi Terzisti");
		else btnElimina.setText("Cancella Profilo");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Autenticazione.getSessione().getTipoUtente()==1){//E' il terzista
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
							ResourceClass.delResources(Global._URLTerz, String.valueOf(terzSelezionato));
							JOptionPane.showMessageDialog(null, "Terzista eliminato correttamente.", "Attenzione", 1);
							//Occorrono a ritroso tutte le cancellazioni nelle altre tabelle dove c'è questo terzista
							//e inviare una comunicazione all'azienda
							//Occorre il Logout
							frmGestioneDati.setVisible(false);
						}
		        	}
		        	else
		        		JOptionPane.showMessageDialog(null, "Cancellazione negata, hai ancora delle lavorazioni in corso.", "Attenzione", 0);
				}
				else{//E' l'azienda
					windowCanc = new GUI_CancellazioneTerzistaOp();
					windowCanc.frmCancellazioneTerzista.setVisible(true);
					frmGestioneDati.setVisible(false);
				}
			}
		});
		btnElimina.setMnemonic(KeyEvent.VK_E);
		btnElimina.setBounds(115, 148, 156, 25);
		btnElimina.setPreferredSize(new Dimension(111, 23));
		frmGestioneDati.getContentPane().add(btnElimina);
		
		JButton btnHome = new JButton("Home");
		btnHome.setMnemonic(KeyEvent.VK_H);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGestioneDati.setVisible(false);
				GUI_Home windowHome=new GUI_Home();
				windowHome.frmHome.setVisible(true);
			}
		});
		btnHome.setBounds(157, 216, 78, 25);
		frmGestioneDati.getContentPane().add(btnHome);
	}

}
