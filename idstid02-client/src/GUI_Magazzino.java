import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.util.Iterator;
import java.util.List;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import classResources.Materiale;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;


public class GUI_Magazzino {

	JFrame frame;
	private JTextField textField;
	private static String[] _titles = {"Codice", "Descrizione", "Costo Unitario"};
	private static Object[][] _data;
	private static Object[] _id;
	private JTable table;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			try {
					
				  	GUI_Magazzino window = new GUI_Magazzino();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	private static void loadTableDt(){
		 List<Materiale> lista = ResourceClass.getResources(Materiale.class, Global._URLMag);
		 Iterator<Materiale> it=lista.iterator();
	     int cntDt = lista.size();
	     int cntTit = _titles.length;
	     _data = new String[cntDt][cntTit];
	     _id = new Object[cntDt];
	    int k = 0;
	    while(it.hasNext())
        {//[riga][colonna]
          Materiale mtCl = (Materiale)it.next();
          if(k<cntDt){
           _data[k][1] = mtCl.getDescrizione();
           _data[k][0] = String.valueOf(mtCl.getCodice());
           _data[k][2] = String.valueOf(mtCl.getCostoUnitario());
           _id[k]= mtCl.getId();
           k++;
          }
        }
	}
	/**
	 * Create the application.
	 */
	public GUI_Magazzino() {
		loadTableDt();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				loadTableDt();
				DefaultTableModel dfm=new DefaultTableModel (_data,_titles);
				table.setModel(dfm);
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		frame.setResizable(false);
		frame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("ciao");
				loadTableDt();
				DefaultTableModel dfm=new DefaultTableModel (_data,_titles);
				table.setModel(dfm);
			}
		});
		frame.setBounds(100, 100, 450, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ricerca materiale:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 97, 17);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setName("searchTxt");
		textField.setBounds(117, 9, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 36, 414, 170);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(_data, _titles);
		scrollPane.setViewportView(table);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setDragEnabled(true);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnNewDDT = new JButton("Crea DDT");
		btnNewDDT.setBounds(163, 217, 123, 23);
		frame.getContentPane().add(btnNewDDT);
		
		JButton btnRegDDT = new JButton("Registra DDT");
		btnRegDDT.setBounds(296, 217, 123, 23);
		frame.getContentPane().add(btnRegDDT);
		
		JButton btnUpdMat = new JButton("Aggiorna materiale");
		btnUpdMat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  if (table.getRowCount() > 0 && table.getColumnCount() > 0) {
			    if (table.getSelectedRow() > 0) {
					int cntRow = table.getSelectedRow();
					int cntColumn =_titles.length;
					String cod = (String) table.getValueAt(cntRow, 0);
					String  desc= (String) table.getValueAt(cntRow, 1);
					String qnt = (String) table.getValueAt(cntRow, 2);
					int id = (Integer) _id[cntRow];
					GUI_UpdMagazzino window = new GUI_UpdMagazzino(id,cod,desc,qnt);
					window.frameUpdMat.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "Non è stato selezionato il materiale!", "Attenzione", 0);
				}
			 }
			 else {
			        JOptionPane.showMessageDialog(null, "La lista è vuota!", "Attenzione", 0);
			     }
			}
		});
		btnUpdMat.setName("btnUpdMat");
		btnUpdMat.setBounds(163, 245, 123, 23);
		frame.getContentPane().add(btnUpdMat);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  System.exit (0);
			}
		});
		btnChiudi.setBounds(296, 245, 123, 23);
		frame.getContentPane().add(btnChiudi);
		
		JButton button = new JButton("Ricerca");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.getText();
				//TODO creare una sql di ricerca LIKE e ricaricare la table
				_titles[0] = "Reload";
				DefaultTableModel dfm=new DefaultTableModel (_data,_titles);
				table.setModel(dfm);
			}
		});
		button.setName("searchBtn");
		button.setBounds(213, 8, 123, 23);
		frame.getContentPane().add(button);
	}
}
