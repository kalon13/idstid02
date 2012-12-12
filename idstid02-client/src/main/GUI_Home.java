package main;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUI_Home {

        public JFrame frmHome;
        private String user;
        private int tipo;
        static GUI_Magazzino windowMagazzino;
        static GUI_GestioneDati windowGestione;
        static GUI_Fatturazione windowFatturazione;
        static GUI_Bolla windowBolla;

        
        /**
		 * @wbp.parser.constructor
		 */
        public GUI_Home(String user, int tipo) {
                this.user = user;
                this.tipo = tipo;
                initialize();
        }
        
//        public GUI_Home() {
//            this.user = user;	//Quando torno indietro non rimane lo user e nemmeno il tipo xche ho creato un nuovo oggetto
//            this.tipo = tipo;	//e dovrei quindi portarmi dietro i valori
//            initialize();
//        }

        private void initialize() {
                frmHome = new JFrame();
                frmHome.setResizable(false);
                frmHome.setTitle("Home");
                frmHome.setBounds(100, 100, 405, 168);
                frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frmHome.getContentPane().setLayout(null);
               
                JPanel panel = new JPanel();
                panel.setBounds(10, 26, 379, 105);
                frmHome.getContentPane().add(panel);
                panel.setLayout(null);
               
                JButton btnGestioneDati = new JButton("Gestione Dati Terzista");
                btnGestioneDati.setMnemonic(KeyEvent.VK_D);
                btnGestioneDati.addActionListener(new ActionListener() {
                	
                	public void actionPerformed(ActionEvent e) {
                		windowGestione = new GUI_GestioneDati(user, tipo);
                		windowGestione.frmGestioneDati.setVisible(true);
                		frmHome.setVisible(false);
                	}
                	
                });
                btnGestioneDati.setBounds(0, 0, 177, 25);
                panel.add(btnGestioneDati);
               
                JButton btnGestioneMagazzino = new JButton("Gestione Magazzino");
                btnGestioneMagazzino.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		windowMagazzino = new GUI_Magazzino();
                        windowMagazzino.frmGestioneMagazzino.setVisible(true);
                	}
                });
                btnGestioneMagazzino.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                windowMagazzino = new GUI_Magazzino();
                                windowMagazzino.frmGestioneMagazzino.setVisible(true);
                        }
                });
                btnGestioneMagazzino.setBounds(192, 0, 177, 25);
                panel.add(btnGestioneMagazzino);
               
                JButton btnGestioneBolleDi = new JButton("Gestione Bolle di lavorazione");
                btnGestioneBolleDi.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		 windowBolla = new GUI_Bolla();
                         windowBolla.frmBolleDiLavorazione.setVisible(true);
                	}
                });
                btnGestioneBolleDi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                windowBolla = new GUI_Bolla();
                                windowBolla.frmBolleDiLavorazione.setVisible(true);

                        }
                });
                btnGestioneBolleDi.setBounds(0, 34, 177, 25);
                panel.add(btnGestioneBolleDi);
               
                JButton btnGestioneFatture = new JButton("Gestione Fatture");
                btnGestioneFatture.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		 windowFatturazione = new GUI_Fatturazione();
                         windowFatturazione.frmElenco.setVisible(true);
                	}
                });
                btnGestioneFatture.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                windowFatturazione = new GUI_Fatturazione();
                                windowFatturazione.frmElenco.setVisible(true);
                        }
                });
                btnGestioneFatture.setBounds(192, 34, 179, 25);
                panel.add(btnGestioneFatture);
               
                JButton btnLogout = new JButton("Logout");
                btnLogout.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                           
                            MultivaluedMap<String, String> param = new MultivaluedMapImpl();
                            param.add("sid", Autenticazione.getSessione().getSessionID());
                           
                            ResourceClass.getService().path(Global._URLAutLogout).
                                    accept(MediaType.APPLICATION_JSON).post(String.class, param);
                           
                            frmHome.dispose();
                            GUI_Autenticazione windowAuth = new GUI_Autenticazione();
                            windowAuth.getFrame().setVisible(true);
                    }
                });

                btnLogout.setBounds(238, 80, 110, 25);
                panel.add(btnLogout);
               
                JLabel lblBenvenuto = new JLabel("Benvenuto");
                lblBenvenuto.setBounds(10, 5, 79, 14);
                frmHome.getContentPane().add(lblBenvenuto);
               
                JLabel labelUser = new JLabel(user);
                labelUser.setBounds(89, 5, 102, 14);
                frmHome.getContentPane().add(labelUser);
        }

}

