package main;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import classResources.Fattura;
import classResources.Materiale;
import classResources.Messaggio;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class GUI_Messaggio {

        public GUI_Messaggio(String numeroBolla)
        {
                if (numeroBolla != " ") 
                {
                        System.out.println(numeroBolla);
                        loadTableDt(numeroBolla); //carica la lista con i messaggi
                        initialize(numeroBolla);
                }
                else
                {
                        System.out.println("no num bolla");
                }
        }
        
        public JFrame frmMessaggi;
        private JTextField textField;
        private static String[] _data;
        private static int[] _id;
        List<Messaggio> lista = null;
        
        private void loadTableDt(String numeroBolla){
                //Load lista messaggi   
                //lista = ResourceClass.getResources(Messaggio.class, Global._URLMess);
                
                //aggiunto
                if (ResourceClass.getResources(Messaggio.class, Global._URLMessSearch+numeroBolla) != null)
                        //Lista dei messaggi riferiti a quel numero di bolla
                        lista = ResourceClass.getResources(Messaggio.class, Global._URLMessSearch+numeroBolla);
                else 
                        //Lista di tutti i messaggi
                        lista = ResourceClass.getResources(Messaggio.class, Global._URLMess);
                        
                Iterator<Messaggio> it = lista.iterator();

                _data = new String[lista.size()]; //Array della stessa lunghezza della lista di tutti i messaggi
                _id = new int[lista.size()];
                int k = 0;
                while(it.hasNext())
                        {
                                Messaggio messCl = (Messaggio)it.next();
                                String nmMess = String.valueOf(messCl.getId());
                                String[] dtMess = messCl.getData().replace("-", "/").split(" ");
                                _data[k] = nmMess + "-" + dtMess[0]; //id del mess + data del mess
                                _id[k]= messCl.getId();
                                k++;
                        }
        }
        
        /**
         * Launch the application.
         */
//      public static void main(String[] args) {
//              EventQueue.invokeLater(new Runnable() {
//                      public void run() {
//                              try {
//                                      GUI_Messaggio window = new GUI_Messaggio();
//                                      window.frmMessaggi.setVisible(true);
//                              } catch (Exception e) {
//                                      e.printStackTrace();
//                              }
//                      }
//              });
//      }

        /**
         * Create the application.
         */
//      public GUI_Messaggio() {
//              loadTableDt();
//              initialize();
//      }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize(String numeroBolla) {
                frmMessaggi = new JFrame();
                frmMessaggi.setTitle("Messaggi");
                frmMessaggi.setResizable(false);
                frmMessaggi.setBounds(100, 100, 450, 300);
                frmMessaggi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmMessaggi.getContentPane().setLayout(null);
                
                JLabel lblMessaggi = new JLabel("Numero Bolla:");
                lblMessaggi.setBounds(10, 11, 125, 14);
                frmMessaggi.getContentPane().add(lblMessaggi);
                                
                final JTextPane textPaneMessaggio = new JTextPane();
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
                btnEsci.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                frmMessaggi.dispose();
                        }
                });
                
                frmMessaggi.getContentPane().add(btnEsci);
                
                textField = new JTextField();
                textField.setEditable(false);
                textField.setText("");
                textField.setBounds(134, 8, 86, 20);
                frmMessaggi.getContentPane().add(textField);
                textField.setColumns(10);
                
                textField.setText(numeroBolla); //numeroBolla che è stato passato dalla GUI_Bolla
                
                final JList listMessaggi = new JList(_data);
                listMessaggi.addListSelectionListener(new ListSelectionListener() {
                        //Quando seleziono un messaggio dalla lista visualizza il testo a fianco
                        public void valueChanged(ListSelectionEvent e) {
                                int indice = listMessaggi.getSelectedIndex();
                                Messaggio m = lista.get(indice);
                                String testo = m.getTesto();
                                textPaneMessaggio.setText(testo);
                        }
                });
                listMessaggi.setBounds(10, 36, 210, 77);
                frmMessaggi.getContentPane().add(listMessaggi);
        }
}