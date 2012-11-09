import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;


public class GUI_Fatturazione {

	private JFrame frmElenco;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Fatturazione window = new GUI_Fatturazione();
					window.frmElenco.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Fatturazione() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmElenco = new JFrame();
		frmElenco.setTitle("Elenco");
		frmElenco.setBounds(100, 100, 450, 314);
		frmElenco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmElenco.getContentPane().setLayout(null);
		
		JLabel lblFatture = new JLabel("Fatture:");
		lblFatture.setBounds(10, 11, 46, 14);
		frmElenco.getContentPane().add(lblFatture);
		
		JList list = new JList();
		list.setBounds(10, 32, 160, 178);
		frmElenco.getContentPane().add(list);
		
		JButton btnNewButton = new JButton("Nuova Fattura");
		btnNewButton.setBounds(10, 221, 160, 23);
		frmElenco.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(178, 11, 254, 233);
		frmElenco.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFatturaN = new JLabel("Fattura n\u00B0:");
		lblFatturaN.setBounds(10, 11, 69, 14);
		panel.add(lblFatturaN);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(137, 11, 49, 14);
		panel.add(lblData);
		
		JLabel lblImportoTotale = new JLabel("Importo Totale:");
		lblImportoTotale.setBounds(10, 39, 107, 14);
		panel.add(lblImportoTotale);
		
		JLabel lblContenutoFattura = new JLabel("Contenuto Fattura:");
		lblContenutoFattura.setBounds(10, 90, 132, 14);
		panel.add(lblContenutoFattura);
		
		JLabel lblExtraconsumi = new JLabel("Extraconsumi:");
		lblExtraconsumi.setBounds(137, 90, 84, 14);
		panel.add(lblExtraconsumi);
		
		table = new JTable();
		table.setBounds(10, 115, 117, 107);
		panel.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(137, 115, 107, 107);
		panel.add(table_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(89, 8, 38, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(181, 8, 63, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JCheckBox chckbxPagata = new JCheckBox("Pagata");
		chckbxPagata.setEnabled(false);
		chckbxPagata.setBounds(10, 60, 97, 23);
		panel.add(chckbxPagata);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(158, 36, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setBounds(343, 246, 89, 23);
		frmElenco.getContentPane().add(btnEsci);
		
	}
}
