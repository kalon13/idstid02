package main;
import java.awt.Component;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.ws.rs.core.MediaType;

import main.*;
import utils.*;

import com.sun.jersey.api.client.GenericType;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JDesktopPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;


public class Magazzino {

        private JFrame frame;
        private static String[][] _data = null;
        private static Object[] _titles2 = null;
        private static List<String> _titles = new ArrayList<String>();
        private JTable table_1;
       
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                  public void run() {
                                try {
                                       
                                        String path = "magazzinoterzista";
                                        //update
                                        String id = "4";
                                        Materiale m = new Materiale(2, "1000", "desTest");
                                        ResourceClass.updResources(Materiale.class, path, "2", m);
                                        //insert
                                        Materiale m1 = new Materiale(0, "1001", "desTest");
                                        id = ResourceClass.addResources(path, m1);
                                        m1.setId(Integer.parseInt(id));
                                        //delete
                                        id = "10";
                                        ResourceClass.delResources(path, id);
                                        //select
                                        List<Materiale> lista = ResourceClass.getResources(Materiale.class, path);
                                       
                                    Iterator<Materiale> it=lista.iterator();
                                    int cntDt = lista.size();
                                    _titles.add("Descrizione");
                                    _titles.add("Codice");
                                    String tit = "Descrizione";String tit1 = "Codice";
                                    _data = new String[cntDt][2];
                                    int k = 0;
                                    while(it.hasNext())
                                {//[riga][colonna]
                                  Materiale mtCl = (Materiale)it.next();
                                  if(k<cntDt){
                                   _data[k][0] = mtCl.getDescrizione();
                                   _data[k][1] = String.valueOf(mtCl.getCodice());
                                   k++;
                                  }
                                }
                                   
                                    Magazzino window = new Magazzino();
                                    window.frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }

        public Magazzino(){
                initialize();
        }
       
        private void initialize() {
                frame = new JFrame();
                frame.setBounds(100, 100, 678, 418);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Table tableMag = new Table(_titles,  _data);
//            tableMag.setValueAt("var", 0, 0);
//            Component table = tableMag.createTable();
//            frame.getContentPane().add(table, BorderLayout.NORTH);
           
            JPanel panel = new JPanel();
            GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
            groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                                .addContainerGap())
            );
            groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                .addContainerGap())
            );
           
            JLabel lblNewLabel = new JLabel("Ricerca");
            lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
            lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
            lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
            lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
            lblNewLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            panel.add(lblNewLabel);
           
        }
}

