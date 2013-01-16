package gui;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;


import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import main.Autenticazione;

import utils.Global;
import utils.Notification;
import utils.ResourceClass;

public class GUI_Home {

        public JFrame frmHome;
        private String user;
        private int tipo;
        static GUI_Magazzino windowMagazzino;
        public static GUI_SceltaTerzista windowScelta;
        static GUI_Fatturazione windowFatturazione;
        static GUI_Bolla windowBolla;
        static GUI_Bolla_Terzista windowBollaTerzista;
        public static GUI_DatiTerzistaTr windowDatiTr;

        
        /**
		 * @wbp.parser.constructor
		 */
        public GUI_Home() {
                this.user = Autenticazione.getSessione().getUtente().getUser();
                this.tipo = Autenticazione.getSessione().getUtente().getTipo();
                
                Notification.start(Autenticazione.getSessione());
                
                initialize();
        }

        private void initialize() {
                frmHome = new JFrame();
                frmHome.setResizable(false);
                frmHome.setTitle("Home");
                frmHome.setBounds(100, 100, 405, 201);
                frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frmHome.getContentPane().setLayout(new BorderLayout(10, 10));
                
                JLabel label = new JLabel("Benvenuto " + user);
                frmHome.getContentPane().add(label, BorderLayout.NORTH);
                
                JPanel panel = new JPanel();
                frmHome.getContentPane().add(panel, BorderLayout.CENTER);
                panel.setLayout(new GridLayout(0, 2, 10, 10));
                
                JButton btnGestioneDati = new JButton("Gestione Dati Terzista");
                btnGestioneDati.setMnemonic(KeyEvent.VK_D);
                btnGestioneDati.addActionListener(new ActionListener() {
                	
                	public void actionPerformed(ActionEvent e) {
                		//Se e' l'Operatore dell'azienda che visualizza
        				if(tipo==1 || tipo==2 || tipo==3 || tipo==4){
        					windowScelta = new GUI_SceltaTerzista();
        					windowScelta.frmSceltaTerzista.setVisible(true);
                    		frmHome.setVisible(false);
        				}
        				else{
        					windowDatiTr = new GUI_DatiTerzistaTr();
        					windowDatiTr.frmDatiTerzistaTr.setVisible(true);
                    		frmHome.setVisible(false);
        				}
                	}
                	
                });
                panel.add(btnGestioneDati);
                
                JButton btnGestioneMagazzino = new JButton("Gestione Magazzino");
                btnGestioneMagazzino.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		windowMagazzino = new GUI_Magazzino();
                        windowMagazzino.frmGestioneMagazzino.setVisible(true);
                        frmHome.setVisible(false);
                	}
                });
                panel.add(btnGestioneMagazzino);
                
                JButton btnGestioneBolle = new JButton("Gestione Bolle di lavorazione");
                btnGestioneBolle.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		if (tipo < 5) //operatore azienda
                		{
                			windowBolla = new GUI_Bolla();
                            windowBolla.frmBolleDiLavorazione.setVisible(true);
                            frmHome.setVisible(false);
                		}
                		else if (tipo == 5) //terzista
                		{
                			windowBollaTerzista = new GUI_Bolla_Terzista();
                			windowBollaTerzista.frmBolleDiLavorazioneTerzista.setVisible(true);
                            frmHome.setVisible(false);
                		}
                		 
                	}
                });
                panel.add(btnGestioneBolle);
                
                JButton btnGestioneFatture = new JButton("Gestione Fatture");
                btnGestioneFatture.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		 windowFatturazione = new GUI_Fatturazione();
                         windowFatturazione.frmElenco.setVisible(true);
                         frmHome.setVisible(false);
                	}
                });
                panel.add(btnGestioneFatture);
                
                JLabel label_1 = new JLabel("");
                panel.add(label_1);
                
                JButton btnLogout = new JButton("Logout");
                btnLogout.addActionListener(new LogoutListener());
                panel.add(btnLogout);
                
                JLabel lblNewLabel = new JLabel("");
                frmHome.getContentPane().add(lblNewLabel, BorderLayout.WEST);
                
                JLabel lblNewLabel_1 = new JLabel("");
                frmHome.getContentPane().add(lblNewLabel_1, BorderLayout.EAST);
                
                JLabel lblNewLabel_2 = new JLabel("");
                frmHome.getContentPane().add(lblNewLabel_2, BorderLayout.SOUTH);

        }
        
        private class LogoutListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				MultivaluedMap<String, String> param = new MultivaluedMapImpl();
				param.add("sid", Autenticazione.getSessione().getSessionID());
				   
				ResourceClass.getService().path(Global._URLAutLogout).
				            accept(MediaType.APPLICATION_JSON).post(String.class, param);
				   
				 Notification.stop();
				 frmHome.dispose();
				 GUI_Autenticazione windowAuth = new GUI_Autenticazione();
				 windowAuth.getFrame().setVisible(true);
			}
        	
                
                /*********************Aggiunto menu*************************************/
//        		menu app = new menu(frmHome, "Home");
                
        }
}

