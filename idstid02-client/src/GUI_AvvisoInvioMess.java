import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class GUI_AvvisoInvioMess {

	private JFrame frmAvvisoInvio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_AvvisoInvioMess window = new GUI_AvvisoInvioMess();
					window.frmAvvisoInvio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_AvvisoInvioMess() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAvvisoInvio = new JFrame();
		frmAvvisoInvio.setTitle("Avviso Invio");
		frmAvvisoInvio.setBounds(100, 100, 350, 125);
		frmAvvisoInvio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAvvisoInvio.getContentPane().setLayout(null);
		
		JLabel lblMessaggioInviato = new JLabel("Messaggio inviato!");
		lblMessaggioInviato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessaggioInviato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMessaggioInviato.setBounds(10, 11, 322, 33);
		frmAvvisoInvio.getContentPane().add(lblMessaggioInviato);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(125, 55, 89, 23);
		frmAvvisoInvio.getContentPane().add(btnOk);
	}

}
