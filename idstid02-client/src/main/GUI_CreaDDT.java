package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;


import classResources.DDT;
import classResources.Materiale;
import javax.swing.JButton;

//import com.sun.faces.facelets.util.Resource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI_CreaDDT {

        JFrame frmCreaDdt;
        private JTable table;
        private static String[] _titlesNewDDT = {"Materiale", "Quantità"};
        private static Object[][] _dataNewDDT;
        private static Object[] _id;
        private static int[] _idMat;
        private static int cntDt;
        private static String[] _comboNewDDT;
        List<TableCellEditor> editors ;
        private DefaultTableModel dfm;
       
        /**
         * Launch the application.
       
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        GUI_CreaDDT window = new GUI_CreaDDT();
                                        window.frmCreaDdt.setVisible(true);
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
                cntDt=0;
                loadTableDt();
                int cnt = loadComboDDT();
                editors = new ArrayList<TableCellEditor>(cnt);
                initialize();
        }
       
        private void loadTableDt(){
                 int cntTit = _titlesNewDDT.length;
             _dataNewDDT = new String[cntDt+1][cntTit];
           for(int i=0; i<=cntDt; i++)
       {//[riga][colonna]
          _dataNewDDT[i][0] = "Seleziona materiale";
          _dataNewDDT[i][1] = null;
       }
        }
       
        private static int loadComboDDT(){
                List<Materiale> lsMat = ResourceClass.getResources(Materiale.class, Global._URLMag);
                int k = 0;
                if(lsMat != null){
                Iterator<Materiale> it=lsMat.iterator();
                int cntDt = lsMat.size();
                _comboNewDDT = new String[cntDt];
                _idMat = new int[cntDt];
                while(it.hasNext())
            {//[riga][colonna]
             Materiale mt = it.next();
             _comboNewDDT[k] = mt.getCodice()+" - "+mt.getDescrizione();;
             _idMat[k]= mt.getId();
         k++;
        }
          }
                else _comboNewDDT = null;
                return k;
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                frmCreaDdt = new JFrame();
                frmCreaDdt.setTitle("Crea DDT");
                frmCreaDdt.setBounds(100, 100, 471, 263);
                frmCreaDdt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmCreaDdt.getContentPane().setLayout(null);
               
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(24, 11, 308, 169);
                frmCreaDdt.getContentPane().add(scrollPane);
               
                JComboBox comboBoxDDT = new JComboBox( _comboNewDDT );
        DefaultCellEditor dce = new DefaultCellEditor( comboBoxDDT );
        editors.add( dce );
       
                dfm = new DefaultTableModel(_dataNewDDT, _titlesNewDDT);
                final JTable tableMat = new JTable(dfm)
        {
            //  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column)
            {
                int modelColumn = convertColumnIndexToModel( column );

                if (modelColumn == 0){
                          return editors.get(0);
                 }
                else
                  return super.getCellEditor(row, column);
            }
        };
                scrollPane.setViewportView(tableMat);
               
                JButton btnAggiungi = new JButton("Aggiungi");
                btnAggiungi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                cntDt++;
                                dfm.insertRow(cntDt,new Object[]{"Seleziona materiale",null});
                        }
                });
                btnAggiungi.setBounds(342, 27, 99, 23);
                frmCreaDdt.getContentPane().add(btnAggiungi);
               
                JButton btnRimuovi = new JButton("Rimuovi");
                btnRimuovi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                int row = tableMat.getSelectedRow();
                                if (row >= 0){
                                dfm.removeRow(row);
                                cntDt--;
                                }
                                else
                                        JOptionPane.showMessageDialog(frmCreaDdt, "Non è stato selezionato il materiale da rimuovere dal DDT!");
                        }
                });
                btnRimuovi.setBounds(342, 58, 99, 23);
                frmCreaDdt.getContentPane().add(btnRimuovi);
               
                JButton btnCrea = new JButton("Crea");
                btnCrea.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                String DATE_FORMAT = "yyyyMMdd";
                                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                                Calendar c1 = Calendar.getInstance(); // today
                                String today = sdf.format(c1.getTime());
                                //TODO idTerz
                                int idTerzista = 1;
//                                DDT ddt = new DDT(today, idTerzista, true, false);
//                                String id  = ResourceClass.addResources(Global._URLddt, ddt);
//                                CreatePDF pdf = new CreatePDF(tableMat);
//                                String DDT = "DDT inviato all'azienda SCARPE FASHION s.r.l dal terzista Mario N. Doc "+id;
//                                pdf.print("file.pdf", DDT);
                        }
                });
                btnCrea.setBounds(246, 191, 89, 23);
                frmCreaDdt.getContentPane().add(btnCrea);
               
                JButton btnAnnulla = new JButton("Annulla");
                btnAnnulla.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                frmCreaDdt.dispose();
                        }
                });
                btnAnnulla.setBounds(342, 191, 89, 23);
                frmCreaDdt.getContentPane().add(btnAnnulla);
               
                JButton btnRimuoviTutto = new JButton("Rimuovi Tutto");
                btnRimuoviTutto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                deleteAllRowTable();
                        }
                });
                btnRimuoviTutto.setBounds(342, 88, 99, 23);
                frmCreaDdt.getContentPane().add(btnRimuoviTutto);
        }
        private void deleteAllRowTable(){
                int numRows = dfm.getRowCount()-1;
                for (int i=numRows;i>=0;i--) {
                  dfm.removeRow(i);
                }
                cntDt = -1;
        }
}

