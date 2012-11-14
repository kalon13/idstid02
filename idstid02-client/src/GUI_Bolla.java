import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;


public class GUI_Bolla {

	private JFrame frmBolleDiLavorazione;
	private JTextField textField;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Bolla window = new GUI_Bolla();
					window.frmBolleDiLavorazione.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Bolla() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBolleDiLavorazione = new JFrame();
		frmBolleDiLavorazione.setResizable(false);
		frmBolleDiLavorazione.setTitle("Bolle di Lavorazione");
		frmBolleDiLavorazione.setBounds(100, 100, 569, 344);
		frmBolleDiLavorazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBolleDiLavorazione.getContentPane().setLayout(null);
		
		JLabel lblBolleDiLavorazione = new JLabel("Bolle assegnate:");
		lblBolleDiLavorazione.setBounds(10, 11, 106, 14);
		frmBolleDiLavorazione.getContentPane().add(lblBolleDiLavorazione);
		
		JPanel panel = new JPanel();
		panel.setBounds(178, 11, 373, 258);
		frmBolleDiLavorazione.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Numero bolla:");
		lblNewLabel.setBounds(10, 11, 87, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(107, 8, 44, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblMaterialiDaProdurre = new JLabel("Materiali da produrre:");
		lblMaterialiDaProdurre.setBounds(10, 28, 141, 14);
		panel.add(lblMaterialiDaProdurre);
		
		JButton btnVisualizzaNote = new JButton("Visualizza Note");
		btnVisualizzaNote.setBounds(216, 7, 147, 23);
		panel.add(btnVisualizzaNote);
		
		table = new JTable();
		table.setBounds(10, 46, 353, 71);
		panel.add(table);
		
		JLabel lblMaterialiTeorici = new JLabel("Materiali teorici:");
		lblMaterialiTeorici.setBounds(10, 128, 112, 14);
		panel.add(lblMaterialiTeorici);
		
		table_1 = new JTable();
		table_1.setBounds(10, 142, 353, 71);
		panel.add(table_1);
		
		JButton btnNewButton = new JButton("Segna Morti");
		btnNewButton.setBounds(10, 224, 112, 23);
		panel.add(btnNewButton);
		
		JButton btnRichiediExtra = new JButton("Richiedi Extra");
		btnRichiediExtra.setBounds(132, 224, 112, 23);
		panel.add(btnRichiediExtra);
		
		JButton btnAggiornaStato = new JButton("Aggiorna Stato");
		btnAggiornaStato.setBounds(251, 224, 112, 23);
		panel.add(btnAggiornaStato);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setBounds(451, 276, 89, 23);
		frmBolleDiLavorazione.getContentPane().add(btnEsci);
		
		JList list = new JList();
		list.setBounds(10, 28, 158, 161);
		frmBolleDiLavorazione.getContentPane().add(list);
		
		JButton btnAnnullaBolla = new JButton("Annulla Bolla");
		btnAnnullaBolla.setBounds(10, 200, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnAnnullaBolla);
		
		JButton btnChiudiParzialmente = new JButton("Chiudi Parzialmente");
		btnChiudiParzialmente.setBounds(10, 234, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnChiudiParzialmente);
		
		JButton btnChiudiBolla = new JButton("Chiudi Bolla");
		btnChiudiBolla.setBounds(10, 268, 158, 23);
		frmBolleDiLavorazione.getContentPane().add(btnChiudiBolla);
	}
}
