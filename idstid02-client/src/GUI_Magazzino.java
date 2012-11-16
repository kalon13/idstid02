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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class GUI_Magazzino {

	JFrame frmGestioneMagazzino;
	private JTextField textSearch;
	private static GUI_RegistraDDT windowRegDDT;
	private static String[] _titles = {"Codice", "Descrizione", "Quantità"};
	private static Object[][] _data;
	private static Object[] _id;
	private JTable table;
	
	private void loadTableDt(Boolean flgSearch){
		 List<Materiale> lista = null;
		if(flgSearch==false || textSearch.getText().equals(""))
		 lista = ResourceClass.getResources(Materiale.class, Global._URLMag);
		else
		{	if (ResourceClass.getResources(Materiale.class, Global._URLMagSearch+textSearch.getText()) != null)
			lista = ResourceClass.getResources(Materiale.class, Global._URLMagSearch+textSearch.getText());
		    else lista=null;
		}
		if(lista!=null){
		 Iterator<Materiale> it=lista.iterator();
	     int cntDt = lista.size();
	     int cntTit = _titles.length;
	     _data = new String[cntDt][cntTit];
	     _id = new Object[cntDt];
	    int k = 0;
	    while(it.hasNext())
        {//[riga][colonna]
          Materiale mtCl = it.next();
          if(k<cntDt){
           _data[k][1] = mtCl.getDescrizione();
           _data[k][0] = String.valueOf(mtCl.getCodice());
           _data[k][2] = String.valueOf(mtCl.getQuantita());
           _id[k]= mtCl.getId_matTerz();
           k++;}
          }
        }
	}
	/**
	 * Create the application.
	 */
	public GUI_Magazzino() {
		loadTableDt(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestioneMagazzino = new JFrame();
		frmGestioneMagazzino.setTitle("Gestione Magazzino");
		frmGestioneMagazzino.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				loadTableDt(false);
				DefaultTableModel dfm=new DefaultTableModel (_data,_titles);
				table.setModel(dfm);
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		frmGestioneMagazzino.setResizable(false);
		frmGestioneMagazzino.setBounds(100, 100, 444, 325);
		frmGestioneMagazzino.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestioneMagazzino.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ricerca materiale:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 97, 17);
		frmGestioneMagazzino.getContentPane().add(lblNewLabel);
		
		textSearch = new JTextField();
		textSearch.setName("searchTxt");
		textSearch.setBounds(117, 9, 86, 20);
		frmGestioneMagazzino.getContentPane().add(textSearch);
		textSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 36, 414, 170);
		frmGestioneMagazzino.getContentPane().add(scrollPane);
		
		table = new JTable(_data, _titles);
		scrollPane.setViewportView(table);
		table.setDragEnabled(true);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnNewDDT = new JButton("Crea DDT");
		btnNewDDT.setBounds(163, 217, 123, 23);
		frmGestioneMagazzino.getContentPane().add(btnNewDDT);
		
		JButton btnRegDDT = new JButton("Registra DDT");
		btnRegDDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				windowRegDDT = new GUI_RegistraDDT();
				windowRegDDT.frmRegistraDdt.setVisible(true);
			}
		});
		btnRegDDT.setBounds(296, 217, 123, 23);
		frmGestioneMagazzino.getContentPane().add(btnRegDDT);
		
		JButton btnUpdMat = new JButton("Aggiorna materiale");
		btnUpdMat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  if (table.getRowCount() > 0 && table.getColumnCount() > 0) {
			    if (table.getSelectedRow() >= 0) {
					int cntRow = table.getSelectedRow();
					int cntColumn =_titles.length;
					String cod = (String) table.getValueAt(cntRow, 0);
					String  desc= (String) table.getValueAt(cntRow, 1);
					String qnt = (String) table.getValueAt(cntRow, 2);
					int idMatTer = (Integer) _id[cntRow];
					GUI_UpdMagazzino window = new GUI_UpdMagazzino(idMatTer,cod,desc,qnt);
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
		frmGestioneMagazzino.getContentPane().add(btnUpdMat);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  frmGestioneMagazzino.dispose();
			}
		});
		btnChiudi.setBounds(296, 245, 123, 23);
		frmGestioneMagazzino.getContentPane().add(btnChiudi);
		
		JButton button = new JButton("Ricerca");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textSearch.getText();
				//TODO creare una sql di ricerca LIKE e ricaricare la table
				loadTableDt(true);
				DefaultTableModel dfm=new DefaultTableModel (_data,_titles);
				table.setModel(dfm);
			}
		});
		button.setName("searchBtn");
		button.setBounds(213, 8, 123, 23);
		frmGestioneMagazzino.getContentPane().add(button);
		
		JMenuBar menuBar = new JMenuBar();
		frmGestioneMagazzino.setJMenuBar(menuBar);
		
		JMenu mnDdt = new JMenu("Gestione DDT");
		menuBar.add(mnDdt);
		
		JMenuItem mntmCreaDdt = new JMenuItem("Crea DDT");
		mnDdt.add(mntmCreaDdt);
		
		JMenuItem mntmRegistraDdt = new JMenuItem("Registra DDT");
		mnDdt.add(mntmRegistraDdt);
	}
}
