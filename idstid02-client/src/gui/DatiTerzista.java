package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JButton;

public class DatiTerzista {

	public JFrame frmDatiTerzista;
	private JTextField indirizzo;
	private JTextField citta;
	private JTextField prov;
	private JTextField cap;
	private JTextField telefono;
	private JTextField fax;
	private JTextField email;
	private JTextField piva;
	private JTextField nome;
	private JLabel lblTelefono;
	private JTable table;


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DatiTerzista window = new DatiTerzista();
//					window.frmDatiTerzista.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public DatiTerzista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatiTerzista = new JFrame();
		frmDatiTerzista.setTitle("Dati Terzista");
		frmDatiTerzista.setBounds(100, 100, 567, 463);
		frmDatiTerzista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		indirizzo = new JTextField();
		indirizzo.setEditable(false);
		indirizzo.setColumns(40);
		
		citta = new JTextField();
		citta.setEditable(false);
		citta.setColumns(40);
		
		prov = new JTextField();
		prov.setEditable(false);
		prov.setColumns(40);
		
		cap = new JTextField();
		cap.setEditable(false);
		cap.setColumns(40);
		
		telefono = new JTextField();
		telefono.setEditable(false);
		telefono.setColumns(40);
		
		fax = new JTextField();
		fax.setEditable(false);
		fax.setColumns(40);
		
		email = new JTextField();
		email.setEditable(false);
		email.setColumns(40);
		
		piva = new JTextField();
		piva.setEditable(false);
		piva.setColumns(40);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:");
		
		JLabel lblCitt = new JLabel("Citt\u00E0");
		
		JLabel lblProvincia = new JLabel("Provincia");
		
		JLabel lblCap = new JLabel("CAP");
		
		JLabel lblFax = new JLabel("Fax");
		
		JLabel lblEmail = new JLabel("Email");
		
		JLabel lblPiva = new JLabel("P.IVA");
		
		nome = new JTextField();
		nome.setFont(new Font("Tahoma", Font.BOLD, 11));
		nome.setForeground(new Color(0, 0, 0));
		nome.setEditable(false);
		nome.setBackground(Color.LIGHT_GRAY);
		nome.setColumns(40);
		
		lblTelefono = new JLabel("Telefono");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		
		JLabel lblLavorazione = new JLabel("Lavorazione");
		lblLavorazione.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCapacit = new JLabel("Capacit\u00E0");
		lblCapacit.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblMediaQualit = new JLabel("Media Qualit\u00E0");
		lblMediaQualit.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		table = new JTable();
		table.setEnabled(false);
		table.setSize(new Dimension(1, 1));
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Lavorazione", "Capacit\u00E0", "Prezzo", "Media Qualit\u00E0"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(87);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.setAutoCreateColumnsFromModel(false);
		
		JButton btnIndietro = new JButton("Indietro");
		GroupLayout groupLayout = new GroupLayout(frmDatiTerzista.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblCitt, Alignment.LEADING)
							.addComponent(lblProvincia, Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblIndirizzo)
											.addGap(26))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCap)
											.addGap(50)))
									.addComponent(lblTelefono)
									.addComponent(lblFax))
								.addGap(52)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(cap, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(citta, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(indirizzo)
									.addComponent(prov, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(telefono, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(fax, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(email, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(piva, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
									.addComponent(nome, 0, 0, Short.MAX_VALUE))
								.addGap(120)))
						.addComponent(lblEmail)
						.addComponent(lblPiva)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLavorazione)
							.addGap(93)
							.addComponent(lblCapacit)
							.addGap(38)
							.addComponent(lblPrezzo, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(lblMediaQualit))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnIndietro)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblIndirizzo)
						.addComponent(indirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCitt)
						.addComponent(citta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblProvincia)
						.addComponent(prov, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCap)
						.addComponent(cap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(telefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefono))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(fax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFax))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmail)
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPiva)
						.addComponent(piva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLavorazione)
						.addComponent(lblCapacit)
						.addComponent(lblPrezzo)
						.addComponent(lblMediaQualit))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(85)
					.addComponent(btnIndietro)
					.addContainerGap())
		);
		frmDatiTerzista.getContentPane().setLayout(groupLayout);
	}
}
