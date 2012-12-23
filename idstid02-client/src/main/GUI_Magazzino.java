package main;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.util.Iterator;
import java.util.List;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JScrollPane;

import classResources.DDT;
import classResources.Materiale;
import classResources.Terzista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;


import java.awt.Frame;


public class GUI_Magazzino {

	JFrame frmGestioneMagazzino;
	private JTextField textSearch;
	
	// WINDOW
	private static GUI_RegistraDDT windowRegDDT;
	private static GUI_CreaDDT windowNewDDT;
   
    // TABLE
    private DefaultTableModel dfmDDT;
    private DefaultTableModel dfmDDTMat;
    private DefaultTableModel dfm;
    private JTable tableDDT;
    private JTable tableDDTMat;
    private JTable table;
    // TITLE TABLE
    private static String[] _titlesDDT = {"Codice DDT", "Data invio", "Data Ricezione", "Inviato da:", "Registrato"};
    private static String[] _titlesMat = {"Codice", "Descrizione", "Quantità", "UdM"};
    private static String[] _titles = {"Codice", "Descrizione", "Quantità", "UdM"};
    //DATA TABLE
    private static Object[][] _dataDDT;
	private static Object[][] _dataMat;
	private static Object[][] _data;
	// ID ARRAY TABLE
    private static int[] _idDDT;
    private static int[] _idMat;
    private static Object[] _id;
        
    // BUTTON
    private JButton btnUpdMat;
    private JButton btnNewDDT;
    private JButton btnRegDDT;
    
    // Lista e Dati Terzista
	private static String[] _dataTerz = null;
    private static int[] _idTerz;
    private static int idTerzista = 0;
    private static JList list_terz;
    List<Terzista> listaTerz = null;
    private JLabel lblTerzisti;
    private JScrollPane scrollPane_Terz; 
    private JPanel panelMag; 
    
    public GUI_Magazzino() {
		checkTerz_DT();
		initialize();
	}

	private void initialize() {
		frmGestioneMagazzino = new JFrame();
		frmGestioneMagazzino.setTitle("Gestione Magazzino");
		frmGestioneMagazzino.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				loadTableDt(false, idTerzista);
				 dfm.setDataVector(_data,_titles);
				 loadTableDDT(idTerzista);
				 dfmDDT.setDataVector(_dataDDT, _titlesDDT);
				 }
			public void windowLostFocus(WindowEvent e) {
			}
		});
		frmGestioneMagazzino.setBounds(100, 100, 1039, 701);
		frmGestioneMagazzino.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestioneMagazzino.getContentPane().setLayout(null);
		
		panelMag = new JPanel();
		panelMag.setBorder(new TitledBorder(null, "Gestione Magazzino", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMag.setBounds(240, 10, 742, 278);
		frmGestioneMagazzino.getContentPane().add(panelMag);
		
		JLabel lblNewLabel = new JLabel("Ricerca materiale:");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		textSearch = new JTextField();
		textSearch.setName("searchTxt");
		textSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setEnabled(false);
		
		dfm=new DefaultTableModel (_data, _titles){
					boolean[] columnEditables = new boolean[]{
					false,false,false,false
			};
			public boolean isCellEditable(int row, int column){
			return columnEditables[column];	
			}
		};
		
        table = new JTable(dfm);
        scrollPane.setViewportView(table);
        table.setDragEnabled(true);
        table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        dfmDDTMat=new DefaultTableModel (_dataMat, _titlesMat);
        dfmDDT=new DefaultTableModel (_dataDDT,_titlesDDT){
			boolean[] columnEditables = new boolean[]{
			false,false,false
			};
			public boolean isCellEditable(int row, int column){
			    return false;	
			}
		};
	         
        JButton button = new JButton("Ricerca");
        button.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		textSearch.getText();
        		loadTableDt(true, idTerzista);
        		dfm.setDataVector(_data,_titles);
        		if(_data.length == 0)
        			JOptionPane.showMessageDialog(frmGestioneMagazzino, "La ricerca non ha restituito risultati!");
        	}
        });
        button.setName("searchBtn");
        btnUpdMat = new JButton("Aggiorna materiale");
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
        GroupLayout gl_panelMag = new GroupLayout(panelMag);
        gl_panelMag.setHorizontalGroup(
        	gl_panelMag.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelMag.createSequentialGroup()
        			.addGap(24)
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(button, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panelMag.createSequentialGroup()
        			.addGap(50)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 639, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panelMag.createSequentialGroup()
        			.addGap(505)
        			.addComponent(btnUpdMat))
        );
        gl_panelMag.setVerticalGroup(
        	gl_panelMag.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelMag.createSequentialGroup()
        			.addGroup(gl_panelMag.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panelMag.createSequentialGroup()
        					.addGap(3)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panelMag.createSequentialGroup()
        					.addGap(1)
        					.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addComponent(button))
        			.addGap(11)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(btnUpdMat))
        );
        panelMag.setLayout(gl_panelMag);
		
		/************lista Terzisti*************************/
		 lblTerzisti = new JLabel("Terzisti:");
         lblTerzisti.setBounds(10, 11, 46, 14);
         frmGestioneMagazzino.getContentPane().add(lblTerzisti);
               
        scrollPane_Terz = new JScrollPane();
        scrollPane_Terz.setBounds(28, 36, 196, 242);
        frmGestioneMagazzino.getContentPane().add(scrollPane_Terz);
        
        	//**JList Terzisti**
         list_terz = new JList(_dataTerz);
         scrollPane_Terz.setViewportView(list_terz);
         
           list_terz.addListSelectionListener(new ListSelectionListener() {
              public void valueChanged(ListSelectionEvent e) {
                 if (e.getValueIsAdjusting() == true)
                  { int k = list_terz.getSelectedIndex();
	                idTerzista = _idTerz[k]; 
	                loadDtTer(idTerzista);
                   }
                }
           });
              
		/*****Crea DDT*****/		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Gestione DDT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(17, 293, 968, 321);
		frmGestioneMagazzino.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		btnNewDDT = new JButton("Nuovo DDT");
		btnNewDDT.setBounds(652, 283, 135, 23);
		panel_1.add(btnNewDDT);
		
		JScrollPane scrollPane_DDTMat = new JScrollPane();
		scrollPane_DDTMat.setBounds(446, 45, 495, 214);
		panel_1.add(scrollPane_DDTMat);
		
		tableDDTMat = new JTable(dfmDDTMat);
		tableDDTMat.setEnabled(false);
		scrollPane_DDTMat.setViewportView(tableDDTMat);
		btnRegDDT = new JButton("Registra DDT");
		btnRegDDT.setBounds(504, 283, 135, 23);
		panel_1.add(btnRegDDT);
		
		JScrollPane scrollPane_DDT = new JScrollPane();
		scrollPane_DDT.setBounds(20, 45, 391, 214);
		panel_1.add(scrollPane_DDT);
		
		tableDDT = new JTable(dfmDDT);
		tableDDT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			  if(tableDDT.getSelectedRow() != -1){
           		int idDDT = tableDDT.getSelectedRow();
           	 	loadTableDDTMat(idDDT);
           	 	if(_dataMat != null)
           	 	 dfmDDTMat.setDataVector(_dataMat, _titlesMat);
           	 	 else dfmDDTMat.setRowCount(0);
           	 	}
			}
           });
	    scrollPane_DDT.setViewportView(tableDDT);
		tableDDT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel label_1 = new JLabel("Materiali DDT:");
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_1.setBounds(432, 25, 153, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Elenco DDT:");
		label_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_2.setBounds(10, 25, 153, 14);
		panel_1.add(label_2);
		
		JButton btnStampaDdtIn = new JButton("Stampa DDT in PDF");
		btnStampaDdtIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tableDDT.getSelectedRow() != -1){
					int row = tableDDT.getSelectedRow();
					String cod = (String) tableDDT.getValueAt(row, 0);
					CreatePDF pdf = new CreatePDF(tableDDTMat);
					String DDT = "DDT inviato all'azienda SCARPE FASHION s.r.l da "+Autenticazione.getSessione().getUtente().getUser().toString()+" N. Doc "+cod;
					pdf.print("urlDDT.pdf", DDT);
				}
				else JOptionPane.showMessageDialog(frmGestioneMagazzino , "Non è stato selezionato il DDT!");
			}
		});
		btnStampaDdtIn.setBounds(806, 283, 135, 23);
		panel_1.add(btnStampaDdtIn);
		btnRegDDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<DDT> lsDDT = ResourceClass.getResources(DDT.class, Global._URLddt);
				if(lsDDT.size() != 0){
					windowRegDDT = new GUI_RegistraDDT();
					windowRegDDT.frmRegistraDdt.setVisible(true);
				}
				else JOptionPane.showMessageDialog(frmGestioneMagazzino , "Non sono presenti nuovi DDT!");	
			}
		});
		btnNewDDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				windowNewDDT = new GUI_CreaDDT(idTerzista);
				windowNewDDT.frmCreaDdt.setVisible(true);
			}
		});
		
		
		
		/*****Chiudi*****/
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  frmGestioneMagazzino.dispose();
			}
		});
		btnChiudi.setBounds(879, 631, 135, 23);
		frmGestioneMagazzino.getContentPane().add(btnChiudi);
		
		JLabel label = new JLabel("Elenco DDT:");
		label.setBounds(500, 57, 153, 14);
		frmGestioneMagazzino.getContentPane().add(label);
		checkTerz_VIS();
	}
	
	/*****Carica Dati terzista*****/
	private void loadListaTerzisti(){
        //Load lista terzisti
		listaTerz = ResourceClass.getResources(Terzista.class, Global._URLTerz);
        Iterator<Terzista> it = listaTerz.iterator();

        _dataTerz = new String[listaTerz.size()];
        _idTerz = new int[listaTerz.size()];
        int k = 0;
        while(it.hasNext())
	        {  Terzista terCl = (Terzista)it.next();
	            String ragSoc = String.valueOf(terCl.getRagioneSociale());
	            _dataTerz[k] = ragSoc; //ragioneSociale
	            _idTerz[k]= terCl.getId();
	            k++;
	        }
	}
	
	
	/*****Carica tabella materiali*****/
	private void loadTableDt(Boolean flgSearch, int idTerz){
		List<Materiale> lista = null;
		_data=null; _id = null;
		if(flgSearch==false || textSearch.getText().equals(""))
		 lista = ResourceClass.getResources(Materiale.class, Global._URLMagMatTerz+idTerz);
		else
		{  if(ResourceClass.getResources(Materiale.class, Global._URLMagSearch+idTerz+"/"+textSearch.getText()) != null){
			 String searchTxt = textSearch.getText().trim().toLowerCase();
			 lista = ResourceClass.getResources(Materiale.class, Global._URLMagSearch+idTerz+"/"+searchTxt);
			 }
		    else lista=null;
		}
		if(lista!=null){
    	  Iterator<Materiale> it=lista.iterator();
	      int cntDt = lista.size();
	      int cntTit = _titles.length;
	      _data = new String[cntDt][4];
	      _id = new Object[cntDt];
	      int k = 0;
	     while(it.hasNext())
         {//[riga][colonna]
           Materiale mtCl = it.next();
         if(k<cntDt)
         {  _data[k][1] = mtCl.getDescrizione();
            _data[k][0] = String.valueOf(mtCl.getCodice());
            _data[k][2] = String.valueOf(mtCl.getQuantita());
            _data[k][3] = String.valueOf(mtCl.getudm());
            _id[k]= mtCl.getId_matTerz();
            k++;
         }
        }
      }
	}
	
	/*****Carica tabella DDT*****/
	private void loadTableDDT(int idTerz){
    	List<DDT> lsddt = null;
    	lsddt = ResourceClass.getResources(DDT.class, Global._URLddtTerz+idTerz);
        if(lsddt!=null){
      	  Iterator<DDT> it=lsddt.iterator();
  	      int cntDt = lsddt.size();
  	      int cntTit = _titlesDDT.length;
  	      _dataDDT = new String[cntDt][cntTit];
  	      _idDDT = new int[cntDt];
  	      int k = 0;
  	     while(it.hasNext())
          {//[riga][colonna]
             DDT mtCl = it.next();
           if(k<cntDt)
           {  _dataDDT[k][0] = String.valueOf(mtCl.getNumDoc());
              _dataDDT[k][1] = FormatDate.getFormatDate(mtCl.getDataInvio());
              _dataDDT[k][2] = FormatDate.getFormatDate(mtCl.getDataRicezione());
              if(mtCl.isFlussoAzienda())
                { _dataDDT[k][3] = "AZIENDA";
                  if(mtCl.isRegistrato()) _dataDDT[k][4] = "REGISTRATO";
                  else _dataDDT[k][4] = "NON REGISTRATO";
            	}
              else {
            	  _dataDDT[k][3] = "TERZISTA";
            	  _dataDDT[k][4] = "";
              }
              _idDDT[k] = mtCl.getId();
              k++;
           }
        }
    }
 }
	
	/*****Carica tabella materiali DDT*****/
    private void loadTableDDTMat(int idDDT){
      idDDT =(Integer) _idDDT[idDDT]; 
      DDT ddt = ResourceClass.getResource(DDT.class, Global._URLddt+"/"+idDDT);
      _dataMat = null;
       if(ddt!=null){
          List<Materiale> lsMtddt = ddt.getDdtMateriale();
        if(lsMtddt!=null){  
      	  Iterator<Materiale> it=lsMtddt.iterator();
  	      int cntDt = lsMtddt.size();
  	      int cntTit = _titlesMat.length;
  	      _dataMat = new String[cntDt][cntTit];
  	      _idMat = new int[cntDt];
  	      int k = 0;  	      
  	     while(it.hasNext())
          {//[riga][colonna]
             Materiale mtCl = it.next();
           if(k<cntDt)
           {  _dataMat[k][0] = String.valueOf(mtCl.getCodice());
           	  _dataMat[k][1] = String.valueOf(mtCl.getDescrizione());
           	  _dataMat[k][2] = String.valueOf(mtCl.getQuantita());
           	  _dataMat[k][3] = String.valueOf(mtCl.getudm());
           	 _idMat[k] = mtCl.getId();
              k++;
           }
        }
      }
    }
 }
    /**** Se entra un opAz nn deve poter cambiare il magazzino nè registrare DDT****/
    //caricamento dati
	private void checkTerz_DT(){
		if(Autenticazione.getSessione().getUtente().getTipo() != 5){
			loadListaTerzisti();
		}
		else
		{loadListaTerzisti();
		 idTerzista = Autenticazione.getSessione().getUtente().getUserId(); 
		 loadTableDt(false, idTerzista);
		 loadTableDDT(idTerzista);
		}
	}
	//modo di visualizzazione
	private void checkTerz_VIS(){
		if(Autenticazione.getSessione().getUtente().getTipo() != 5){
			btnUpdMat.setVisible(false);
			btnNewDDT.setVisible(false);
			btnRegDDT.setVisible(false);
		}
		else
		{
		  panelMag.setBounds(125, 10, 742, 278);
		  list_terz.setVisible(false);
		  scrollPane_Terz.setVisible(false);
		  lblTerzisti.setVisible(false);
		 
		}
	}
	
		
	/**Carica dati quando clicco i terzisti nella lista**/
    private void loadDtTer(int idT){
    	loadTableDt(false, idTerzista);
		dfm.setDataVector(_data,_titles);
		loadTableDDT(idTerzista);
		dfmDDT.setDataVector(_dataDDT, _titlesDDT);
		dfmDDTMat.setRowCount(0);
    }
}
