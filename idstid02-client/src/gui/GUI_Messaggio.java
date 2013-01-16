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

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import main.Autenticazione;
import main.Messaggio;

import utils.Global;
import utils.ResourceClass;

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
//                 System.out.println("no num bolla");
            }
        }
       
        public JFrame frmMessaggi;
        private JTextField txtNumeroBolla;
        public JList listMessaggi;
        private String[] _data;
        private int[] _id;
        List<Messaggio> lista = null;
        private int numeroBolla;
       
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
			   
			JLabel lbNumeroBolla = new JLabel("Numero Bolla:");
			lbNumeroBolla.setBounds(10, 11, 125, 14);
			frmMessaggi.getContentPane().add(lbNumeroBolla);
			
		    txtNumeroBolla = new JTextField();
		    txtNumeroBolla.setEditable(false);
		    txtNumeroBolla.setText("");
		    txtNumeroBolla.setBounds(134, 7, 86, 23);
		    frmMessaggi.getContentPane().add(txtNumeroBolla);
		    txtNumeroBolla.setColumns(10);
	    	txtNumeroBolla.setText(codiceBolla);
	    
            
            JLabel lblTestoMessaggio = new JLabel("Testo messaggio:");
            lblTestoMessaggio.setBounds(232, 45, 138, 14);
            frmMessaggi.getContentPane().add(lblTestoMessaggio);
			    
			JScrollPane scrTesto = new JScrollPane();
			scrTesto.setBounds(230, 62, 204, 90);
			frmMessaggi.getContentPane().add(scrTesto);
			                   
			final JTextPane txtTesto = new JTextPane();
			scrTesto.setViewportView(txtTesto);
			
            JLabel lblComponiNuovoMessaggio = new JLabel("Componi nuovo messaggio:");
            lblComponiNuovoMessaggio.setBounds(10, 164, 210, 14);
            frmMessaggi.getContentPane().add(lblComponiNuovoMessaggio);
			
			JScrollPane scrNuovo = new JScrollPane();
			scrNuovo.setBounds(10, 183, 424, 99);
			frmMessaggi.getContentPane().add(scrNuovo);
			   
			final JTextPane txtNuovo = new JTextPane();
			scrNuovo.setViewportView(txtNuovo);
			
	    	JLabel lblListaMessaggi = new JLabel("Lista messaggi:");
            lblListaMessaggi.setBounds(10, 45, 138, 14);
            frmMessaggi.getContentPane().add(lblListaMessaggi);
			
			JScrollPane scrLista = new JScrollPane();
		    scrLista.setBounds(10, 62, 210, 90);
		    frmMessaggi.getContentPane().add(scrLista);
	    	
		    listMessaggi = new JList(_data);
		    scrLista.setViewportView(listMessaggi);
		    listMessaggi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    listMessaggi.addListSelectionListener(new ListSelectionListener() {
		    	 //Quando seleziono un messaggio dalla lista visualizza il testo a fianco
				 public void valueChanged(ListSelectionEvent e) {
					 int indice = listMessaggi.getSelectedIndex();
				     if(indice > -1) {
						Messaggio m = lista.get(indice);
						String testo = m.getTesto();
						txtTesto.setText(testo);
						if(m.getUtenteId() != Autenticazione.getSessione().getUtente().getUserId()) {
							ResourceClass.getService().path(Global._URLMessLetto).path(String.valueOf(m.getId()))
								.accept(MediaType.APPLICATION_JSON).post(String.class);
						}
					 }
					 else {
					 	txtTesto.setText("");
					 }
				 }
			 });
		    
		    
			JButton btnInvia = new JButton("Invia");
            btnInvia.setBounds(345, 294, 89, 23);
			btnInvia.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	String messageText = txtNuovo.getText().trim();
			    	if(messageText != "") {
						MultivaluedMap<String, String> param = new MultivaluedMapImpl();
						param.add("utenteid", String.valueOf(Autenticazione.getSessione().getUtente().getUserId()));
						param.add("bollaid", String.valueOf(numeroBolla));
						param.add("message", messageText);
						String ret = ResourceClass.getService().path(Global._URLMess).path("insert")
								.accept(MediaType.APPLICATION_JSON).put(String.class, param);
						if(ret!="-1") {
							txtNuovo.setText("");
							refresh(numeroBolla);
		                }
		                else {
		                	txtNuovo.selectAll();
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
        }
        
        
        private void refresh(int numBolla) {
        	loadTableDt(numeroBolla);
        	listMessaggi.setListData(_data);
        }
}

