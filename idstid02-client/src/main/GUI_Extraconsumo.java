package main;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.*;
import java.lang.String;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;

import classResources.Extraconsumo;

public class GUI_Extraconsumo {

        public JFrame frmExtraconsumo;
        private JTextField textField;
        private JTable table_1;
        List<Extraconsumo> listaExtra = null; //lista materiali teorici della bolla per richiedere extra
        private static String[] _data;
        private static int[] _id;
       
        //TableModel per table_1 (materiali extra)
        public DefaultTableModel dm = new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                        "codiceArticolo", "Desc", "QtaAttuale", "QtaRichiesta", "udm", "Giustificato", "DataRichiesta"
                        }
                );
       
        private void loadTable(int idBolla){
                //Load lista
                dm.setRowCount(0); //pulisce la table (dm = datamodel della table_1)
               
                listaExtra = ResourceClass.getResources(Extraconsumo.class, Global._URLExtra+idBolla);
                Iterator<Extraconsumo> it = listaExtra.iterator();
                               
                _data = new String[listaExtra.size()];
                _id = new int[listaExtra.size()];
                int k = 0;
                while(it.hasNext())
                        {                      
                        Extraconsumo extraCl = (Extraconsumo)it.next();
                                //aggiunto
                                String codArt = String.valueOf(extraCl.getCodiceArticolo());
                                String desc = String.valueOf(extraCl.getDescrizione());
                                String qtaAttu = String.valueOf(extraCl.getQtaAttuale());
                                String qtaRichiesta = String.valueOf(extraCl.getQuantita());
                                String giustif = String.valueOf(extraCl.isGiustificato());
                                String udm = String.valueOf(extraCl.getUdm());
                                String data = extraCl.getDataRichiesta();
                                _data[k] = codArt + "-" + desc + "-" + qtaAttu + "-" + qtaRichiesta + "-" + udm + "-" + giustif + "-" + data;
                                _id[k]= extraCl.getId(); //id extraconsumo
                                k++;
                                //Aggiunge i valori alla tabella
                                ((DefaultTableModel) table_1.getModel()).insertRow(
                                                table_1.getRowCount(), new Object[]{codArt, desc, qtaAttu, qtaRichiesta, udm, giustif, data});
                        }
        }
       
       
        /**
         * Launch the application.
         */
//      public static void main(String[] args) {
//              EventQueue.invokeLater(new Runnable() {
//                      public void run() {
//                              try {
//                                      GUI_Extraconsumo window = new GUI_Extraconsumo();
//                                      window.frmExtraconsumo.setVisible(true);
//                              } catch (Exception e) {
//                                      e.printStackTrace();
//                              }
//                      }
//              });
//      }

        /**
         * Create the application.
         */
        public GUI_Extraconsumo(String codiceBolla, int id) {
                initialize(id);
                textField.setText(codiceBolla);
               
                System.out.println(id);
                loadTable(id); //passo l'id della bolla selezionata nella lista in frmBolla
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize(final int id) {
                frmExtraconsumo = new JFrame();
                frmExtraconsumo.setTitle("Extraconsumo");
                frmExtraconsumo.setBounds(100, 100, 558, 270);
                frmExtraconsumo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
                final JLabel lblBollaN = new JLabel("Bolla n\u00B0:");
                lblBollaN.setBounds(7, 7, 94, 20);
               
                textField = new JTextField();
                textField.setBounds(79, 7, 61, 20);
                textField.setEditable(false);
                textField.setColumns(10);
               
                frmExtraconsumo.getContentPane().setLayout(null);
                frmExtraconsumo.getContentPane().add(lblBollaN);
                frmExtraconsumo.getContentPane().add(textField);
               
                JLabel lblMaterialiTeorici = new JLabel("Materiali teorici:");
                lblMaterialiTeorici.setBounds(7, 31, 105, 14);
                frmExtraconsumo.getContentPane().add(lblMaterialiTeorici);
               
                JButton btnRichiedi = new JButton("Richiedi");
                btnRichiedi.setBounds(350, 206, 90, 23);
                frmExtraconsumo.getContentPane().add(btnRichiedi);
               
                //**btnEsci**
                JButton btnEsci = new JButton("Esci");
                btnEsci.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                frmExtraconsumo.dispose();
                        }
                });
                btnEsci.setBounds(450, 206, 90, 23);
                frmExtraconsumo.getContentPane().add(btnEsci);

                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(7, 56, 533, 139);
                frmExtraconsumo.getContentPane().add(scrollPane);
               
                //**table_1**
                table_1 = new JTable(dm);
                scrollPane.setViewportView(table_1);
               
                //Al premere di Invio in una cella di table_1 richiama l'Update - per la modifica
                dm.addTableModelListener(new TableModelListener(){
                        @Override
                        public void tableChanged(TableModelEvent e) {
                                int col = e.getColumn();
                                System.out.println(col);
                                int row = e.getFirstRow();
                                if(col > 2) {
                                        try {
                                                Calendar c = Calendar.getInstance();
//                                              Date tempo = (c.getTime()); /* Rappresentazione come stringa in base al tuo Locale */
//                                              int anno = (c.get(Calendar.YEAR)); /* Ottieni l'anno */
//                                              int mese = (c.get(Calendar.MONTH)); /* Ottieni il mese */
//                                              int giorno = (c.get(Calendar.DAY_OF_MONTH)); /* Ottieni il giorno */
                                                String anno = Integer.toString(c.get(Calendar.YEAR));
                                                String mese = Integer.toString(c.get(Calendar.MONTH));
                                                String giorno = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
                                                String data = anno + mese + giorno;
                                                System.out.println(data);
                                               
                                                Extraconsumo ext = listaExtra.get(row);
                                                //Colonne: 3-qtaRichiesta 5-giustificato 6-dataRichiesta
                                                double qr = Double.parseDouble(dm.getValueAt(row, 3).toString());
                                                boolean giu = Boolean.parseBoolean(dm.getValueAt(row, 5).toString());
//                                              String dr = (String) dm.getValueAt(row, 6);
                                                ext.setQuantita(qr);
                                                ext.setGiustificato(giu);
                                                ext.setDataRichiesta(data);
                                                ResourceClass.updResources(Extraconsumo.class, Global._URLExtraPost, String.valueOf(ext.getId()), ext);
                                                loadTable(id); //aggiorno tabella con data
                                        }
                                        catch(Exception er) {
                                                er.printStackTrace();
                                        }                                      
                                }
                        }
                });
        }
}
