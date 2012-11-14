package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Dimension;

public class GUI_GestioneDati {

	public JFrame frmGestioneDati;

//	DatiTerzista windowDati = new DatiTerzista();
	static GUI_GestioneDati windowGestione;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					windowGestione = new GUI_GestioneDati();
					windowGestione.frmGestioneDati.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI_GestioneDati() {
		initialize();
	}

	private void initialize() {
		frmGestioneDati = new JFrame();
		frmGestioneDati.setResizable(false);
		frmGestioneDati.setTitle("Gestione Dati");
		frmGestioneDati.setBounds(100, 100, 390, 280);
		frmGestioneDati.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnVisualizza = new JButton("Visualizza");
		btnVisualizza.setBounds(137, 34, 123, 23);
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_DatiTerzistaOp windowDati = new GUI_DatiTerzistaOp();
				windowDati.frmDatiTerzista.setVisible(true);
				frmGestioneDati.setVisible(false);
			}
			
		});
		frmGestioneDati.getContentPane().setLayout(null);
		btnVisualizza.setPreferredSize(new Dimension(111, 23));
		frmGestioneDati.getContentPane().add(btnVisualizza);
		
		JButton btnModificaValuta = new JButton("Modifica / Valuta");
		btnModificaValuta.setBounds(137, 91, 123, 25);
		frmGestioneDati.getContentPane().add(btnModificaValuta);
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setBounds(137, 150, 123, 23);
		btnElimina.setPreferredSize(new Dimension(111, 23));
		frmGestioneDati.getContentPane().add(btnElimina);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(293, 207, 65, 25);
		frmGestioneDati.getContentPane().add(btnHome);
	}

}
