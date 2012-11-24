package main;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import classResources.DDT;
import classResources.Fattura;
import classResources.Fattura_Lavorazione;
import classResources.Materiale;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;


public class GUI_CreaDDT {

	JFrame frmCreaDdt;
	private JTable tblMateriale;
	private DefaultTableModel dfm;
	private static GUI_CreaDDT windowNewDDT;
	private static Object[][] _dataNewDDT;
	private static String[] _comboNewDDT;
	private static JComboBox comboBoxDDT;
	private static JTextField textDDT;
	private static int[] _idMat;
	private static Object[] _titlesDDT={"Materiale", "Quantità"};
	/**
	 * Launch the application.*/
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowNewDDT = new GUI_CreaDDT();
					windowNewDDT.frmCreaDdt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_CreaDDT() {
		loadComboDDT();
		initialize();
	}

	private static void loadComboDDT(){
		List<Materiale> lsMat = ResourceClass.getResources(Materiale.class, Global._URLMag);
		if(lsMat != null){
		Iterator<Materiale> it=lsMat.iterator();
		int cntDt = lsMat.size(); int cntTit=_titlesDDT.length;
		_comboNewDDT = new String[cntDt];
		_idMat = new int[cntDt];
	     int k = 0;
	    while(it.hasNext())
	    {//[riga][colonna]
	     Materiale mt = it.next();
	     _comboNewDDT[k] = mt.getCodice()+" - "+mt.getDescrizione();;
	     _idMat[k]= mt.getId();
         k++;
        }
	  }
		else _comboNewDDT = null;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreaDdt = new JFrame();
		frmCreaDdt.setResizable(false);
		frmCreaDdt.setTitle("Crea nuovo DDT");
		frmCreaDdt.setBounds(100, 100, 508, 347);
		frmCreaDdt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreaDdt.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(18, 11, 476, 307);
		frmCreaDdt.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_Mat = new JScrollPane();
		scrollPane_Mat.setFocusTraversalPolicyProvider(true);
		scrollPane_Mat.setFocusTraversalKeysEnabled(false);
		scrollPane_Mat.setBounds(0, 0, 359, 245);
		panel.add(scrollPane_Mat);
		
		 JComboBox qta=new JComboBox();
		 for(int i=0;i<=50;i++)
		 qta.addItem(new Integer(i));
		 comboBoxDDT = new JComboBox(_comboNewDDT);
			//scrollPane_Mat.setColumnHeaderView(comboBoxDDT);
		TableColumn col = tblMateriale.getColumnModel().getColumn(1);
		col.setCellEditor((TableCellEditor) comboBoxDDT);
		 _dataNewDDT = new String[2][2] ; 
		_dataNewDDT[0][0] = comboBoxDDT;
		tblMateriale = new JTable(_dataNewDDT, _titlesDDT);
		tblMateriale.setAutoCreateRowSorter(true);
		tblMateriale.setRowSelectionAllowed(false);
		scrollPane_Mat.setViewportView(tblMateriale);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"caio\tcio"}));
		scrollPane_Mat.setColumnHeaderView(comboBox);

		
		JButton btnNew = new JButton("Crea ");
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*int i = listDDT.getSelectedIndex();
				int idDDT = _idLsDDT[i];
				DDT ddt = ResourceClass.getResource(DDT.class, Global._URLddt+"/"+idDDT);
				List<Materiale> lsMatDDT = ddt.getDdtMateriale();
				if(lsMatDDT != null){
				Iterator<Materiale> it=lsMatDDT.iterator();
				while(it.hasNext())
			    {
			     Materiale mtDDT = it.next();
			     int idMat = mtDDT.getId();mtDDT.setId_terzista(1);
			     Materiale mat = ResourceClass.getResource(Materiale.class, Global._URLMag+"/"+idMat+
			    		 "/"+mtDDT.getId_terzista());			    	
			     if(mat != null)
			     {  //update
			    	double qnt = mat.getQuantita() + mtDDT.getQuantita();  
			    	mat.setQuantita(qnt);
					ResourceClass.updResources(Materiale.class, Global._URLMag, 
			    			String.valueOf(mat.getId_matTerz()), mat);
				 }
					else{
					//TODO Insert terzista! int id, double quantita, int id_terzista
					Materiale m1 = new Materiale(mtDDT.getId(),mtDDT.getQuantita(), mtDDT.getId_terzista());
					String id = ResourceClass.addResources("/magazzinoterzista", m1);
					m1.setId(Integer.parseInt(id));
				}
		      }
			}
				ResourceClass.updResources(DDT.class, Global._URLddt, 
		    			String.valueOf(idDDT), ddt);
				loadLstDDT();
				listDDT.setSelectedIndex(-1);
				deleteAllRowTable();
				listDDT.setModel(modelLsDDT);*/
		  }
		});
		btnNew.setBounds(249, 256, 89, 23);
		panel.add(btnNew);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCreaDdt.dispose();
			}
		});
		btnAnnulla.setBounds(348, 256, 89, 23);
		panel.add(btnAnnulla);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(371, 87, 89, 23);
		panel.add(btnAggiungi);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.setBounds(370, 121, 89, 23);
		panel.add(btnRimuovi);
	}
	private void deleteAllRowTable(){
		int numRows = dfm.getRowCount()-1;
		for (int i=numRows;i>=0;i--) {
		  dfm.removeRow(i);
		  System.out.println("i"+i+"num"+numRows);
		}
	}
}
