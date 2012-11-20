import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import classResources.Bolla;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class GUI_Bolla {

	JFrame frmBolleDiLavorazione;
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	
	GUI_Messaggio messaggio;
	List<Bolla> lista = null;
	private static String[] _data;
	private static int[] _id;
	
	private void loadTableDt(){
		//Load lista
		lista = ResourceClass.getResources(Bolla.class, Global._URLBolla);
		Iterator<Bolla> it = lista.iterator();

		_data = new String[lista.size()];
		_id = new int[lista.size()];
		int k = 0;
		while(it.hasNext())
			{
				Bolla messCl = (Bolla)it.next();
				String nmMess = String.valueOf(messCl.getId());
				String[] dtMess = messCl.getData().replace("-", "/").split(" ");
				_data[k] = nmMess + "-" + dtMess[0]; //numBolla + dataBolla
				_id[k]= messCl.getId();
				k++;
			}
	}
	
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
	
	public GUI_Bolla() {
		loadTableDt();
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
		
		//**textField**
		//Quando cambia il contenuto del textField (quindi il numero di bolla)
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			     public void removeUpdate(DocumentEvent e) {
			        // TODO add code!
			     }
			     public void insertUpdate(DocumentEvent e) {
			        // TODO add code!
						String numeroBolla = textField.getText();
			     }
			     public void changedUpdate(DocumentEvent e) {
			        // TODO add code!
			     }
			  });
			
		textField.setEditable(false);
		textField.setBounds(107, 8, 44, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblMaterialiDaProdurre = new JLabel("Materiali da produrre:");
		lblMaterialiDaProdurre.setBounds(10, 28, 141, 14);
		panel.add(lblMaterialiDaProdurre);
		
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
		
		//**btnEsci**
		JButton btnEsci = new JButton("Esci");
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmBolleDiLavorazione.dispose();
			}
		});
		btnEsci.setBounds(451, 276, 89, 23);
		frmBolleDiLavorazione.getContentPane().add(btnEsci);
		
		final JList list = new JList(_data); //aggiunge alla Jlist numero + data delle bolle
		list.addListSelectionListener(new ListSelectionListener() {
			//Se seleziono un valore dalla lista delle bolle, il num di bolla va nel textField
			public void valueChanged(ListSelectionEvent e) {
				int indice = list.getSelectedIndex();
				Bolla b = lista.get(indice);
				String testo = b.getCodice();
				textField.setText(testo);
			}
		});
		
		//**btnVisualizzaNote**
		JButton btnVisualizzaNote = new JButton("Visualizza Note");
		//Quando clicco Visualizza Bolle richiama la GUI Messaggio passandogli il num di bolla
		btnVisualizzaNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroBolla = textField.getText();
				messaggio = new GUI_Messaggio(numeroBolla);
				messaggio.frmMessaggi.setVisible(true);
			}
		});
		btnVisualizzaNote.setBounds(216, 7, 147, 23);
		panel.add(btnVisualizzaNote);
		
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
