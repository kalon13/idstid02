import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;


public class GUI_Messaggio {

	private JFrame frmMessaggi;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Messaggio window = new GUI_Messaggio();
					window.frmMessaggi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Messaggio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMessaggi = new JFrame();
		frmMessaggi.setTitle("Messaggi");
		frmMessaggi.setResizable(false);
		frmMessaggi.setBounds(100, 100, 450, 300);
		frmMessaggi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMessaggi.getContentPane().setLayout(null);
		
		JLabel lblMessaggi = new JLabel("Numero Bolla:");
		lblMessaggi.setBounds(10, 11, 125, 14);
		frmMessaggi.getContentPane().add(lblMessaggi);
		
		JList listMessaggi = new JList();
		listMessaggi.setBounds(10, 36, 210, 77);
		frmMessaggi.getContentPane().add(listMessaggi);
		
		JTextPane textPaneMessaggio = new JTextPane();
		textPaneMessaggio.setBounds(230, 36, 204, 77);
		frmMessaggi.getContentPane().add(textPaneMessaggio);
		
		JTextPane textPaneNuovoMess = new JTextPane();
		textPaneNuovoMess.setBounds(10, 124, 424, 99);
		frmMessaggi.getContentPane().add(textPaneNuovoMess);
		
		JButton btnInvia = new JButton("Invia");
		btnInvia.setBounds(246, 234, 89, 23);
		frmMessaggi.getContentPane().add(btnInvia);
		
		JButton btnEsci = new JButton("Chiudi");
		btnEsci.setBounds(345, 234, 89, 23);
		frmMessaggi.getContentPane().add(btnEsci);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("");
		textField.setBounds(134, 8, 86, 20);
		frmMessaggi.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
