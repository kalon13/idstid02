import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import classResources.DDT;
import classResources.Fattura;
import classResources.Fattura_Lavorazione;
import classResources.Materiale;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class GUI_RegistraDDT {

	JFrame frmRegistraDdt;
	private JTable tblMateriale;
	private static String[] _dataLsDDT = null;
	private static int[] _idLsDDT = null;
	private static GUI_RegistraDDT windowRegDDT;
	private static String[][] _dataDDT;
	private static int[] _idMat;
	private static Object[] _titlesDDT={"Codice", "Descrizione", "Quantità"};
	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowRegDDT = new GUI_RegistraDDT();
					windowRegDDT.frmRegistraDdt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_RegistraDDT() {
		loadLstDDT();
		initialize();
	}
	private void loadLstDDT(){
		 List<DDT> lsDDT = ResourceClass.getResources(DDT.class, Global._URLddt);
		 Iterator<DDT> it=lsDDT.iterator();
         int cntDt = lsDDT.size();
         _dataLsDDT = new String[cntDt];
         _idLsDDT = new int[cntDt];
	      int t = 0;
		    while(it.hasNext())
	        {//[riga][colonna]
		      DDT ddt = it.next();
		      String[] dtInvio = ddt.getDataInvio().replace("-", "/").split(" ");
		      String[] dtRicezione=ddt.getDataRicezione().replace("-", "/").split(" ");
	          _dataLsDDT[t] = ddt.getNumDoc()+" - data invio: "+dtInvio[0]+" - data ricezione: "+dtRicezione[0];
	          _idLsDDT[t] = ddt.getId();
	          System.out.println(_dataLsDDT[t]);
	          t++;
	     }
	 }

	private static void loadTblDDT(int id){
		DDT ddt = ResourceClass.getResource(DDT.class, Global._URLddt+"/"+id);
		List<Materiale> lsMatDDT = ddt.getDdtMateriale();
		Iterator<Materiale> it=lsMatDDT.iterator();
		int cntDt = lsMatDDT.size(); int cntTit=_titlesDDT.length;
		_dataDDT = new String[cntDt][cntTit];
		_idMat = new int[cntDt];
	     int k = 0;
	    while(it.hasNext())
	    {//[riga][colonna]
	     Materiale mt = it.next();
	     _dataDDT[k][0] = mt.getCodice();
	     _dataDDT[k][1] = mt.getDescrizione();
	     _dataDDT[k][2] = String.valueOf(mt.getQuantita());
	     _idMat[k]= mt.getId();
         k++;
        }
	 }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistraDdt = new JFrame();
		frmRegistraDdt.setResizable(false);
		frmRegistraDdt.setTitle("Registra DDT");
		frmRegistraDdt.setBounds(100, 100, 424, 305);
		frmRegistraDdt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistraDdt.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(18, 11, 390, 257);
		frmRegistraDdt.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_Mat = new JScrollPane();
		scrollPane_Mat.setFocusTraversalPolicyProvider(true);
		scrollPane_Mat.setFocusTraversalKeysEnabled(false);
		scrollPane_Mat.setBounds(2, 113, 377, 106);
		panel.add(scrollPane_Mat);
		
		tblMateriale = new JTable();
		tblMateriale.setRowSelectionAllowed(false);
		tblMateriale.setEnabled(false);
		scrollPane_Mat.setViewportView(tblMateriale);
		
		JScrollPane scrollPane_DDT = new JScrollPane();
		scrollPane_DDT.setBounds(2, 0, 377, 100);
		panel.add(scrollPane_DDT);
		
		final JList listDDT = new JList(_dataLsDDT);
		listDDT.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int k = listDDT.getSelectedIndex();
				loadTblDDT(_idLsDDT[k]);
				DefaultTableModel dfm=new DefaultTableModel (_dataDDT, _titlesDDT);
				tblMateriale.setModel(dfm);
			}
		});
		scrollPane_DDT.setViewportView(listDDT);
		
		JButton btnRegistra = new JButton("Registra");
		btnRegistra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = listDDT.getSelectedIndex();
				int idDDT = _idLsDDT[i];
				DDT ddt = ResourceClass.getResource(DDT.class, Global._URLddt+"/"+idDDT);
				List<Materiale> lsMatDDT = ddt.getDdtMateriale();
				Iterator<Materiale> it=lsMatDDT.iterator();
				while(it.hasNext())
			    {
			     Materiale mtDDT = it.next();
			     int idMat = mtDDT.getId();
			     Materiale mat = ResourceClass.getResource(Materiale.class, Global._URLMagMat+idMat);
			     if(mat != null)
			     {  //update
			    	double qnt = mat.getQuantita() + mtDDT.getQuantita();  
			    	mat.setQuantita(qnt);
			    	ResourceClass.updResources(Materiale.class, Global._URLMag, String.valueOf(idMat), mat);
			    	System.out.println("vciao"+mat.getQuantita());
				 }
					else{
					//TODO Insert terzista! int id, double quantita, int id_terzista
					Materiale m1 = new Materiale(mat.getId(),mat.getQuantita(), mat.getId_terzista());
					String id = ResourceClass.addResources(Global._URLMag, m1);
					m1.setId(Integer.parseInt(id));
					System.out.println("aciao"+m1.getQuantita());
				}
		      }
			}
		});
		btnRegistra.setBounds(195, 229, 89, 23);
		panel.add(btnRegistra);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmRegistraDdt.dispose();
			}
		});
		btnAnnulla.setBounds(294, 230, 89, 23);
		panel.add(btnAnnulla);
	}
}
