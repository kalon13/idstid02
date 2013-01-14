package gui;

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

import main.*;
import utils.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.KeyEvent;

public class GUI_Messaggio {

        public GUI_Messaggio(int numeroBolla, String codiceBolla)
        {
            if (numeroBolla != 0)
            {
            	this.numeroBolla = numeroBolla;
                loadTableDt(numeroBolla); //carica la lista con i messaggi
                initialize(numeroBolla, codiceBolla);
            }
            else
            {
                 System.out.println("no num bolla");
            }
        }
       
        public JFrame frmMessaggi;
        private JTextField textField;
        public JList listMessaggi;
        private String[] _data;
        private int[] _id;
        List<Messaggio> lista = null;
        private int numeroBolla;
        JTextPane textPaneNuovoMess;
       
        private void loadTableDt(int numeroBolla){
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
                        String preview = messCl.getTesto();
                        if(messCl.getTesto().length() > 17) {
                        	preview = messCl.getTesto().substring(0, 17) + "...";
                        }
                        _data[k] = dtMess[0] + " - " + preview; //id del mess + data del mess
                        _id[k]= messCl.getId();
                        k++;
                }
        }

        /**
         * Initialize the contents of the frame.
         * @wbp.parser.entryPoint
         */
        private void initialize(final int numeroBolla, String codiceBolla) {
                frmMessaggi = new JFrame();
                frmMessaggi.setTitle("Messaggi");
                frmMessaggi.setResizable(false);
                frmMessaggi.setBounds(100, 100, 450, 356);
                frmMessaggi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmMessaggi.getContentPane().setLayout(null);
               
                JLabel lblMessaggi = new JLabel("Numero Bolla:");
                lblMessaggi.setBounds(10, 11, 125, 14);
                frmMessaggi.getContentPane().add(lblMessaggi);
               
                JButton btnInvia = new JButton("Invia");
                btnInvia.setMnemonic(KeyEvent.VK_ENTER);
                btnInvia.setBounds(345, 294, 89, 23);
                btnInvia.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	String messageText = textPaneNuovoMess.getText().trim();
                    	if(messageText != "") {
                        	MultivaluedMap<String, String> param = new MultivaluedMapImpl();
		                    param.add("utenteid", String.valueOf(Autenticazione.getSessione().getUtente().getUserId()));
		                    param.add("bollaid", String.valueOf(numeroBolla));
		                    param.add("message", messageText);
    	                    String ret = ResourceClass.getService().path(Global._URLMess).path("insert")
    	                    		.accept(MediaType.APPLICATION_JSON).put(String.class, param);
    	                    if(ret!="-1") {
    	                    	textPaneNuovoMess.setText("");
    	                    	refresh(numeroBolla);
    	                    }
    	                    else {
    	                    	textPaneNuovoMess.selectAll();
    	                    }
                    	}
                    }
				});
                frmMessaggi.getContentPane().add(btnInvia);
               
                JButton btnEsci = new JButton("Chiudi");
                btnEsci.setBounds(10, 294, 89, 23);
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
               
                textField.setText(codiceBolla);
                   
                   JScrollPane scrollPane_1 = new JScrollPane();
                   scrollPane_1.setBounds(12, 179, 422, 97);
                   frmMessaggi.getContentPane().add(scrollPane_1);
                  
                   textPaneNuovoMess = new JTextPane();
                   scrollPane_1.setViewportView(textPaneNuovoMess);
                    
                    JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setBounds(10, 67, 208, 75);
                    frmMessaggi.getContentPane().add(scrollPane);
                   
                    listMessaggi = new JList(_data);
                    scrollPane.setViewportView(listMessaggi);
                    listMessaggi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                
                JScrollPane scrollPane_2 = new JScrollPane();
                scrollPane_2.setBounds(232, 67, 202, 75);
                frmMessaggi.getContentPane().add(scrollPane_2);
                    
                final JTextPane textPaneMessaggio = new JTextPane();
                scrollPane_2.setViewportView(textPaneMessaggio);
                
                JLabel lblListaMessaggi = new JLabel("Lista messaggi:");
                lblListaMessaggi.setBounds(10, 45, 138, 14);
                frmMessaggi.getContentPane().add(lblListaMessaggi);
                
                JLabel lblTestoMessaggio = new JLabel("Testo messaggio:");
                lblTestoMessaggio.setBounds(232, 45, 138, 14);
                frmMessaggi.getContentPane().add(lblTestoMessaggio);
                
                JLabel lblComponiNuovoMessaggio = new JLabel("Componi nuovo messaggio:");
                lblComponiNuovoMessaggio.setBounds(10, 160, 210, 14);
                frmMessaggi.getContentPane().add(lblComponiNuovoMessaggio);
                  listMessaggi.addListSelectionListener(new ListSelectionListener() {
                      //Quando seleziono un messaggio dalla lista visualizza il testo a fianco
                      public void valueChanged(ListSelectionEvent e) {
                          int indice = listMessaggi.getSelectedIndex();
                          if(indice > -1) {
	                            Messaggio m = lista.get(indice);
	                            String testo = m.getTesto();
	                            textPaneMessaggio.setText(testo);
	                            System.out.println(m.isLetto());
	                            if(m.getUtenteId() != Autenticazione.getSessione().getUtente().getUserId()) {
	                            	ResourceClass.getService().path(Global._URLMessLetto).path(String.valueOf(m.getId()))
	                            		.accept(MediaType.APPLICATION_JSON).post(String.class);
	                            }
	                            System.out.println(m.isLetto());
                          }
                          else {
                          	textPaneMessaggio.setText("");
                          }
                      }
                  });
        }
        
        
        private void refresh(int numBolla) {
        	loadTableDt(numeroBolla);
        	listMessaggi.setListData(_data);
        }
}

