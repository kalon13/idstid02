package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;


public class GUI_AggiornaStatoBolla {

        private JFrame frmAggiornaStatoBolla;
        private JTextField txtNumBolla;
        private JTable tblProdotti;
        private JTable tblUtilizzati;

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        GUI_AggiornaStatoBolla window = new GUI_AggiornaStatoBolla();
                                        window.frmAggiornaStatoBolla.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }

        /**
         * Create the application.
         */
        public GUI_AggiornaStatoBolla() {
                initialize();
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                frmAggiornaStatoBolla = new JFrame();
                frmAggiornaStatoBolla.setTitle("Aggiorna Stato Bolla");
                frmAggiornaStatoBolla.setResizable(false);
                frmAggiornaStatoBolla.setBounds(100, 100, 298, 436);
                frmAggiornaStatoBolla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmAggiornaStatoBolla.getContentPane().setLayout(null);
               
                JLabel lblBollaN = new JLabel("Bolla n\u00B0:");
                lblBollaN.setBounds(10, 11, 46, 14);
                frmAggiornaStatoBolla.getContentPane().add(lblBollaN);
               
                txtNumBolla = new JTextField();
                txtNumBolla.setEditable(false);
                txtNumBolla.setBounds(66, 8, 86, 20);
                frmAggiornaStatoBolla.getContentPane().add(txtNumBolla);
                txtNumBolla.setColumns(10);
               
                JPanel panel = new JPanel();
                panel.setBounds(10, 36, 272, 161);
                frmAggiornaStatoBolla.getContentPane().add(panel);
                panel.setLayout(null);
               
                JLabel lblMaterialiProdotti = new JLabel("Materiali Prodotti:");
                lblMaterialiProdotti.setBounds(10, 11, 116, 14);
                panel.add(lblMaterialiProdotti);
               
                tblProdotti = new JTable();
                tblProdotti.setBounds(10, 30, 252, 97);
                panel.add(tblProdotti);
               
                JButton btnAggiornaQt = new JButton("Aggiorna Quantit\u00E0");
                btnAggiornaQt.setBounds(127, 132, 135, 23);
                panel.add(btnAggiornaQt);
               
                JPanel panel_1 = new JPanel();
                panel_1.setBounds(10, 202, 272, 161);
                frmAggiornaStatoBolla.getContentPane().add(panel_1);
                panel_1.setLayout(null);
               
                JLabel lblMaterialiUtilizzati = new JLabel("Materiali Utilizzati:");
                lblMaterialiUtilizzati.setBounds(10, 11, 86, 14);
                panel_1.add(lblMaterialiUtilizzati);
               
                JButton btnAggiornaQt2 = new JButton("Aggiorna Quantit\u00E0");
                btnAggiornaQt2.setBounds(127, 133, 135, 23);
                panel_1.add(btnAggiornaQt2);
               
                tblUtilizzati = new JTable();
                tblUtilizzati.setBounds(10, 28, 252, 97);
                panel_1.add(tblUtilizzati);
        }

}
