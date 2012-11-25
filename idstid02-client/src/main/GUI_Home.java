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

public class GUI_Home {

	JFrame frmHome;
	private String user;
	static GUI_Magazzino windowMagazzino;
	static GUI_Fatturazione windowFatturazione;
	static GUI_Bolla windowBolla;

	public GUI_Home(String user) {
		this.user = user;
		initialize();
	}

	private void initialize() {
		frmHome = new JFrame();
		frmHome.setResizable(false);
		frmHome.setTitle("Home");
		frmHome.setBounds(100, 100, 405, 168);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 26, 348, 105);
		frmHome.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnGestioneDati = new JButton("Gestione Dati");
		btnGestioneDati.setBounds(0, 0, 169, 25);
		panel.add(btnGestioneDati);
		
		JButton btnGestioneMagazzino = new JButton("Gestione Magazzino");
		btnGestioneMagazzino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				windowMagazzino = new GUI_Magazzino();
				windowMagazzino.frmGestioneMagazzino.setVisible(true);
			}
		});
		btnGestioneMagazzino.setBounds(179, 0, 169, 25);
		panel.add(btnGestioneMagazzino);
		
		JButton btnGestioneBolleDi = new JButton("Gestione Bolle di lavorazione");
		btnGestioneBolleDi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				windowBolla = new GUI_Bolla();
				windowBolla.frmBolleDiLavorazione.setVisible(true);

			}
		});
		btnGestioneBolleDi.setBounds(0, 34, 169, 25);
		panel.add(btnGestioneBolleDi);
		
		JButton btnGestioneFatture = new JButton("Gestione Fatture");
		btnGestioneFatture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				windowFatturazione = new GUI_Fatturazione();
				windowFatturazione.frmElenco.setVisible(true);
			}
		});
		btnGestioneFatture.setBounds(179, 34, 169, 25);
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
		lblBenvenuto.setBounds(34, 5, 59, 14);
		frmHome.getContentPane().add(lblBenvenuto);
		
		JLabel labelUser = new JLabel(user);
		labelUser.setBounds(89, 5, 102, 14);
		frmHome.getContentPane().add(labelUser);
	}

}
