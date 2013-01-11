package gui;


import javax.swing.*;

import main.Autenticazione;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

public class menu{
	
		private int tipo = Autenticazione.getSessione().getUtente().getTipo();
		
		public menu(final JFrame frame, String menu)
		{	
			JMenuBar menuBar = new JMenuBar();
			menuBar.setPreferredSize(new Dimension(0, 25));
			menuBar.setMinimumSize(new Dimension(0, 5));
			menuBar.setMaximumSize(new Dimension(0, 6));			
			
			frame.setJMenuBar(menuBar);
			
			/***********Gestione Bolle***********/
			if(!menu.equals("Bolla")){
				JMenu mnGestioneBolle = new JMenu("Gestione Bolle");
				mnGestioneBolle.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if (tipo < 5) //operatore azienda
		        		{
		        			GUI_Bolla windowBolla = new GUI_Bolla();
		                    windowBolla.frmBolleDiLavorazione.setVisible(true);
		                    frame.setVisible(false);
		        		}
		        		else if (tipo == 5) //terzista
		        		{
		        			GUI_Bolla_Terzista windowBollaTerzista = new GUI_Bolla_Terzista();
		        			windowBollaTerzista.frmBolleDiLavorazioneTerzista.setVisible(true);
		        			frame.setVisible(false);
		        		}
					}
				});
				mnGestioneBolle.setFont(mnGestioneBolle.getFont().deriveFont(mnGestioneBolle.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC));
				menuBar.add(mnGestioneBolle);
			}
			
			/***********Gestione Fatturazione***********/
			if(!menu.equals("Fat")){
				JMenu mnGestioneFatturazione = new JMenu("Gestione Fatturazione");
				mnGestioneFatturazione.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						 GUI_Fatturazione windowFatturazione = new GUI_Fatturazione();
	                     windowFatturazione.frmElenco.setVisible(true);
	                     frame.setVisible(false);
					}
				});
				mnGestioneFatturazione.setFont(mnGestioneFatturazione.getFont().deriveFont(mnGestioneFatturazione.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC));
				menuBar.add(mnGestioneFatturazione);
			}
			
			/***********Gestione Terzista***********/
			if(!menu.equals("Terz")){
				JMenu mnGestioneTerzista = new JMenu("Gestione Terzista");
				mnGestioneTerzista.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//Se � l'Operatore dell'azienda che visualizza
	    				if(tipo != 5){
	    					try{
	    						GUI_Home.windowScelta.frmSceltaTerzista.dispose();
	    					}
	    					catch(Exception ex){}
	    					GUI_Home.windowScelta = new GUI_SceltaTerzista();
	    					GUI_Home.windowScelta.frmSceltaTerzista.setVisible(true);
	                		frame.setVisible(false);
	    				}
	    				else{
	    					try{
	    						GUI_Home.windowDatiTr.frmDatiTerzistaTr.dispose();
	    					}
	    					catch(Exception ex){}
	    					GUI_Home.windowDatiTr = new GUI_DatiTerzistaTr();
	    					GUI_Home.windowDatiTr.frmDatiTerzistaTr.setVisible(true);
	    					frame.setVisible(false);
	    				}
					}
				});
				mnGestioneTerzista.setFont(mnGestioneTerzista.getFont().deriveFont(mnGestioneTerzista.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC));
				menuBar.add(mnGestioneTerzista);
			}
	
			/***********Gestione Magazzino***********/
			if(!menu.equals("Mag")){
				JMenu mnGestioneMagazzino = new JMenu("Gestione Magazzino");
				mnGestioneMagazzino.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						GUI_Magazzino windowMagazzino = new GUI_Magazzino();
	                    windowMagazzino.frmGestioneMagazzino.setVisible(true);
	                    frame.setVisible(false);
					}
				});
				mnGestioneMagazzino.setFont(mnGestioneMagazzino.getFont().deriveFont(mnGestioneMagazzino.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC));
				menuBar.add(mnGestioneMagazzino);
			}
			
			/***********Home***********
			if(!menu.equals("Home")){
				JMenu mnHome = new JMenu("Home");
				mnHome.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						GUI_Home windowHome = new GUI_Home();
	                    windowHome.frmHome.setVisible(true);
	                    frame.setVisible(false);
					}
				});
				mnHome.setFont(mnHome.getFont().deriveFont(mnHome.getFont().getStyle() & ~Font.BOLD & ~Font.ITALIC));
				menuBar.add(mnHome);
			}***/
			
		}
}
