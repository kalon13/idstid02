import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import classResources.*;

public class GUI_CreaFattura {

	JFrame frmCreazioneFattura;
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private String[] _data;
	private int[] _id;
	
	/**
	 * Create the application.
	 */
	public GUI_CreaFattura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreazioneFattura = new JFrame();
		frmCreazioneFattura.setResizable(false);
		frmCreazioneFattura.setTitle("Creazione Fattura");
		frmCreazioneFattura.setBounds(100, 100, 471, 294);
		frmCreazioneFattura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreazioneFattura.getContentPane().setLayout(null);
		
		JLabel lblBolle = new JLabel("Bolle:");
		lblBolle.setBounds(10, 11, 46, 14);
		frmCreazioneFattura.getContentPane().add(lblBolle);
		
		JLabel lblDaFatturare = new JLabel("Da Fatturare:");
		lblDaFatturare.setBounds(200, 11, 115, 14);
		frmCreazioneFattura.getContentPane().add(lblDaFatturare);
		
		JLabel lblImportoTotale = new JLabel("Importo Totale:");
		lblImportoTotale.setBounds(347, 37, 85, 14);
		frmCreazioneFattura.getContentPane().add(lblImportoTotale);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setForeground(Color.BLACK);
		panel.setBounds(10, 134, 432, 93);
		frmCreazioneFattura.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDettagliBollaSelezionata = new JLabel("Dettagli Bolla Selezionata:");
		lblDettagliBollaSelezionata.setBounds(10, 11, 181, 14);
		panel.add(lblDettagliBollaSelezionata);
		
		JLabel lblMaterialiProdotti = new JLabel("Materiali Prodotti:");
		lblMaterialiProdotti.setBounds(10, 25, 124, 14);
		panel.add(lblMaterialiProdotti);
		
		JLabel lblMaterialiExtraconsumi = new JLabel("Materiali Extraconsumi:");
		lblMaterialiExtraconsumi.setBounds(220, 25, 160, 14);
		panel.add(lblMaterialiExtraconsumi);
		
		table = new JTable();
		table.setBounds(10, 49, 202, 33);
		panel.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(220, 49, 202, 33);
		panel.add(table_1);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCreazioneFattura.dispose();
			}
		});
		btnEsci.setBounds(347, 232, 95, 23);
		frmCreazioneFattura.getContentPane().add(btnEsci);
		
		JButton btnCrea = new JButton("Crea");
		btnCrea.setBounds(347, 87, 95, 23);
		frmCreazioneFattura.getContentPane().add(btnCrea);
		
		List<Bolla> lista = ResourceClass.getResources(Bolla.class, Global._URLBolla);
		 Iterator<Bolla> it=lista.iterator();
		 _data = new String[lista.size()];
		 _id = new int[lista.size()];
	     int k = 0;
	    while(it.hasNext())
	    {//[riga][colonna]
	      Bolla bolla = it.next();
         String cdBolla = bolla.getCodice();
         String[] dtBolla = bolla.getData().replace("-", "/").split(" ");
         _data[k] = cdBolla+"-"+dtBolla[0];
         _id[k]= bolla.getId();
          k++;
         }
	    
		JList listBolle = new JList(_data);
		listBolle.setBounds(10, 36, 127, 87);
		frmCreazioneFattura.getContentPane().add(listBolle);
		
		JList listFatt = new JList();
		listFatt.setBounds(200, 36, 127, 87);
		frmCreazioneFattura.getContentPane().add(listFatt);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(347, 56, 95, 20);
		frmCreazioneFattura.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton(">");
		
		button.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button.setBounds(147, 36, 40, 40);
		frmCreazioneFattura.getContentPane().add(button);
		
		JButton button_1 = new JButton("<");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_1.setBounds(147, 83, 40, 40);
		frmCreazioneFattura.getContentPane().add(button_1);
	}

}
