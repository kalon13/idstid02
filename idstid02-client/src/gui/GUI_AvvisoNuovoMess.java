package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;


public class GUI_AvvisoNuovoMess {

        private JFrame frmAvviso;

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        GUI_AvvisoNuovoMess window = new GUI_AvvisoNuovoMess();
                                        window.frmAvviso.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }

        /**
         * Create the application.
         */
        public GUI_AvvisoNuovoMess() {
                initialize();
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                frmAvviso = new JFrame();
                frmAvviso.setResizable(false);
                frmAvviso.setTitle("Avviso");
                frmAvviso.setBounds(100, 100, 347, 130);
                frmAvviso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmAvviso.getContentPane().setLayout(null);
               
                JLabel lblCiSonoNuovi = new JLabel("Ci sono nuovi messaggi da leggere!");
                lblCiSonoNuovi.setHorizontalAlignment(SwingConstants.CENTER);
                lblCiSonoNuovi.setFont(new Font("Tahoma", Font.PLAIN, 18));
                lblCiSonoNuovi.setBounds(10, 26, 321, 23);
                frmAvviso.getContentPane().add(lblCiSonoNuovi);
               
                JButton btnOk = new JButton("OK");
                btnOk.setBounds(128, 60, 89, 23);
                frmAvviso.getContentPane().add(btnOk);
        }

}

