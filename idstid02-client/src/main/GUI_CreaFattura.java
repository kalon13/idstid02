package main;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import classResources.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class GUI_CreaFattura {

	JFrame frmCreazioneFattura;
	private JTextField txtImpTot;
	private JTable tblMatP;
	private JTable tblMatEx;
	private DefaultListModel dfmLs;
	//id bolle della lista da fatturare
	private ArrayList<String> _idB = new ArrayList<String>();
	//id bolle della lista da fatturare
	private ArrayList<String> _idFB = new ArrayList<String>();
	
	/**
	 * Create the application.
	 */
	public GUI_CreaFattura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param <T>
	 */
	 private void loadTableMatEx(String idB){
		
 		List<Extraconsumo> extraC = ResourceClass.getResources(Extraconsumo.class, Global._URLExtra+idB);
 		if(extraC != null){
		Iterator<Extraconsumo> it = extraC.iterator();
		tblMatEx.removeAll();
		((DefaultTableModel) tblMatEx.getModel()).setRowCount(0); //pulisce la table
		
		Iterator<Extraconsumo> itEx =extraC.iterator();
	    while(itEx.hasNext())
	    { Extraconsumo exC = itEx.next();
	    	String cod = exC.getCodiceArticolo();
	    	String des = exC.getDescrizione();
	    	Double qnt =  exC.getQuantita();
	    	String udm = exC.getUdm();
	    	Double cstU =  exC.getCosto();
	    	String dt = exC.getDataRichiesta();
	    	String g = "Ingiustificato";
	    	if(exC.isGiustificato()) g = "Giustificato";
	      //Aggiunge i valori alla tabella
		  ((DefaultTableModel) tblMatEx.getModel()).insertRow(
				  tblMatEx.getRowCount(), new Object[]{cod,des,qnt, udm, cstU, g, dt});	 
	    }
	   }
	}
	 
	 private void loadTableMatP(String idB){
			List<MaterialeDaProdurre> lsMatP = ResourceClass.getResources(MaterialeDaProdurre.class, Global._URLMatDaProd1+idB);
			if(lsMatP != null){
			Iterator<MaterialeDaProdurre> it = lsMatP.iterator();
			tblMatP.removeAll();
			((DefaultTableModel) tblMatP.getModel()).setRowCount(0); //pulisce la table
			
			Iterator<MaterialeDaProdurre> itP =lsMatP.iterator();
		    while(itP.hasNext())
		    { MaterialeDaProdurre matP = itP.next();
		    	String cod= matP.getCodArt();
		    	String des = matP.getDescrizione();
		    	Double qnt =  matP.getQuantitaProdotta();
		    	String udm = matP.getUdm();		    	
		      //Aggiunge i valori alla tabella
			  ((DefaultTableModel) tblMatP.getModel()).insertRow(
					  tblMatP.getRowCount(), new Object[]{cod,des,qnt, udm});	 
		    }
		  }
		}
	 
	private void initialize() {
		frmCreazioneFattura = new JFrame();
		frmCreazioneFattura.setResizable(false);
		frmCreazioneFattura.setTitle("Creazione Fattura");
		frmCreazioneFattura.setBounds(100, 100, 914, 475);
		frmCreazioneFattura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreazioneFattura.getContentPane().setLayout(null);
		
		JLabel lblBolle = new JLabel("Bolle:");
		lblBolle.setBounds(10, 11, 46, 14);
		frmCreazioneFattura.getContentPane().add(lblBolle);
		
		JLabel lblDaFatturare = new JLabel("Da Fatturare:");
		lblDaFatturare.setBounds(322, 11, 115, 14);
		frmCreazioneFattura.getContentPane().add(lblDaFatturare);
		
		JLabel lblImportoTotale = new JLabel("Importo Totale:");
		lblImportoTotale.setBounds(567, 36, 85, 14);
		frmCreazioneFattura.getContentPane().add(lblImportoTotale);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setForeground(Color.BLACK);
		panel.setBounds(10, 139, 860, 260);
		frmCreazioneFattura.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDettagliBollaSelezionata = new JLabel("Dettagli Bolla Selezionata:");
		lblDettagliBollaSelezionata.setBounds(10, 11, 181, 14);
		panel.add(lblDettagliBollaSelezionata);
		
		JLabel lblMaterialiProdotti = new JLabel("Materiali Prodotti:");
		lblMaterialiProdotti.setBounds(10, 25, 124, 14);
		panel.add(lblMaterialiProdotti);
		
		JLabel lblMaterialiExtraconsumi = new JLabel("Materiali Extraconsumi:");
		lblMaterialiExtraconsumi.setBounds(366, 25, 160, 14);
		panel.add(lblMaterialiExtraconsumi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 330, 167);
		panel.add(scrollPane);
		
		tblMatP = new JTable();
		tblMatP.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Articolo", "Materiale", "Quantit\u00E0", "Udm"
				}
			));
		tblMatP.getColumnModel().getColumn(3).setPreferredWidth(88);
		scrollPane.setViewportView(tblMatP);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(366, 50, 480, 167);
		panel.add(scrollPane_1);
		
		tblMatEx = new JTable();
		tblMatEx.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Articolo", "Materiale", "Quantit\u00E0", "Udm", "Costo Unitario", "Giustificato", "Data"
				}
			));
		tblMatEx.getColumnModel().getColumn(3).setPreferredWidth(88);
		scrollPane_1.setViewportView(tblMatEx);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCreazioneFattura.dispose();
			}
		});
		btnEsci.setBounds(761, 412, 95, 23);
		frmCreazioneFattura.getContentPane().add(btnEsci);
		
		JButton btnCrea = new JButton("Crea");
		btnCrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO Terz
				String dt = FormatDate.getToday();
				double imp = 0;
				if(!txtImpTot.getText().isEmpty())
				   imp = Double.parseDouble(txtImpTot.getText()); 
				int idT = 1;
				Fattura fat = new Fattura(dt, imp, idT);
				String id =ResourceClass.addResources(Global._URLFatt, fat);
				fat.setId(Integer.valueOf(id));
				Iterator itFattBol =  _idFB.iterator();
			    while(itFattBol.hasNext())
			    {	int idBolla = Integer.valueOf((String) itFattBol.next());
			    	fat.setIdBolla(idBolla);
			    	System.out.print(fat.getId());
			    	ResourceClass.addResources(Global._URLFatt+"/Bolla", fat);
			    }
			}
		});
		btnCrea.setBounds(567, 86, 95, 23);
		frmCreazioneFattura.getContentPane().add(btnCrea);
		
		List<Bolla> lista = ResourceClass.getResources(Bolla.class, Global._URLBolla);
		Iterator<Bolla> it=lista.iterator();
		dfmLs = new DefaultListModel();
		while(it.hasNext())
	    {//[riga][colonna]
	     Bolla bolla = it.next();
         String cdBolla = bolla.getCodice();
         String[] dtBolla = bolla.getData().replace("-", "/").split(" ");
         dfmLs.addElement(cdBolla+"-"+dtBolla[0]);
         String idB = String.valueOf(bolla.getId()); 
         _idB.add(idB);
         }
	    
	    final JList listBolle = new JList(dfmLs);
	    listBolle.addListSelectionListener(new ListSelectionListener() {
	    	public void valueChanged(ListSelectionEvent e) {
	    		if(listBolle.getSelectedIndex() !=-1){
		    		int index = listBolle.getSelectedIndex();
		    		String idB = _idB.get(index);
		    		loadTableMatEx(idB);
		    		loadTableMatP(idB);
	    		}
	    		else
	    			{tblMatEx.removeAll();
	    			 tblMatP.removeAll();}
	    	}
	    });
	   
		listBolle.setBounds(10, 36, 234, 87);
		frmCreazioneFattura.getContentPane().add(listBolle);
		
		final DefaultListModel listModelFatt = new DefaultListModel();
		final JList listFatt = new JList(listModelFatt);
		listFatt.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			 if(listFatt.getSelectedIndex() !=-1){
				int index = listFatt.getSelectedIndex();
				String idB = String.valueOf(_idFB.get(index));
	    		loadTableMatEx(idB);
	    		loadTableMatP(idB);
			 }
			 else
	 		   {tblMatEx.removeAll();
	 			tblMatP.removeAll();}
			}
		});
		listFatt.setBounds(322, 36, 234, 87);
		frmCreazioneFattura.getContentPane().add(listFatt);
		
		txtImpTot = new JTextField();
		txtImpTot.setEditable(false);
		txtImpTot.setBounds(567, 55, 95, 20);
		frmCreazioneFattura.getContentPane().add(txtImpTot);
		txtImpTot.setColumns(10);
		
		JButton btnDx = new JButton(">");
		btnDx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblMatEx.removeAll();
	 			tblMatP.removeAll();
	 			
				int index = listBolle.getSelectedIndex();
				if(index != -1 ){
				  listModelFatt.addElement(listBolle.getSelectedValue());
				  dfmLs.removeElementAt(index);
				  _idFB.add(_idB.get(index));
				  _idB.remove(index);
				}
				else JOptionPane.showMessageDialog(frmCreazioneFattura, "Non è stato selezionato niente!");
			}
		});
		
		btnDx.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnDx.setBounds(269, 36, 40, 40);
		frmCreazioneFattura.getContentPane().add(btnDx);
		
		JButton btnSn = new JButton("<");
		btnSn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblMatEx.removeAll();
	 			tblMatP.removeAll();
	 			
				int index = listFatt.getSelectedIndex() ;
				if(index != -1 ){
				  dfmLs.addElement(listFatt.getSelectedValue());
				  listFatt.setSelectedIndex(-1);
				  listModelFatt.removeElementAt(index);
				  _idB.add(_idFB.get(index));
				  _idFB.remove(index);
				}
				else JOptionPane.showMessageDialog(frmCreazioneFattura, "Non è stato selezionato niente!");
			}
		});
		btnSn.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSn.setBounds(269, 83, 40, 40);
		frmCreazioneFattura.getContentPane().add(btnSn);
	}

}
