package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import main.Materiale;

import utils.Global;
import utils.ResourceClass;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.ComponentOrientation;


public class GUI_UpdMagazzino {

        public JFrame frameUpdMat;
        private JTextField textCod;
        private JTextField textDesc;
        private JTextField textQnt;
        private String cod;
        private String desc;
        private String qnt;
        private int idMatTer;
        //TODO terzista che si � loggato
        private int id_terzista = 1;
       
        public GUI_UpdMagazzino(int idMatTer, String cod, String desc, String qnt) {
                this.cod = cod;
                this.desc = desc;
                this.qnt = qnt;
                this.idMatTer = idMatTer;
                initialize();
        }

        private void initialize() {
                frameUpdMat = new JFrame();
                frameUpdMat.setTitle("Aggiorna materiale");
                frameUpdMat.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                frameUpdMat.setResizable(false);
                frameUpdMat.setBounds(100, 100, 270, 192);
                frameUpdMat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameUpdMat.getContentPane().setLayout(null);
               
                textCod = new JTextField();
                textCod.setEditable(false);
                textCod.setText(cod);
                textCod.setBounds(139, 25, 86, 20);
                frameUpdMat.getContentPane().add(textCod);
                textCod.setColumns(10);
               
                textDesc = new JTextField();
                textDesc.setEditable(false);
                textDesc.setText(desc);
                textDesc.setBounds(139, 56, 86, 20);
                frameUpdMat.getContentPane().add(textDesc);
                textDesc.setColumns(10);
               
                textQnt = new JTextField();
                textQnt.setText(qnt);
                textQnt.setBounds(139, 90, 86, 20);
                frameUpdMat.getContentPane().add(textQnt);
                textQnt.setColumns(10);
               
                JButton btnSave = new JButton("Salva");
                btnSave.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                        	if(verificaQntIns(textQnt.getText())){
                                Materiale m = new Materiale(idMatTer, Double.parseDouble(textQnt.getText()));
                                ResourceClass.updResources(Materiale.class, Global._URLMag, String.valueOf(idMatTer), m);
                                frameUpdMat.dispose();
                        	}
                        }
                });
                btnSave.setBounds(45, 133, 89, 23);
                frameUpdMat.getContentPane().add(btnSave);
               
                JButton btnAnnulla = new JButton("Annulla");
                btnAnnulla.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                frameUpdMat.dispose();
                        }
                });
                btnAnnulla.setBounds(144, 133, 89, 23);
                frameUpdMat.getContentPane().add(btnAnnulla);
               
                JLabel lblNewLabel = new JLabel("Codice:");
                lblNewLabel.setBounds(65, 28, 46, 14);
                frameUpdMat.getContentPane().add(lblNewLabel);
               
                JLabel lblDescrizione = new JLabel("Descrizione:");
                lblDescrizione.setBounds(45, 59, 59, 14);
                frameUpdMat.getContentPane().add(lblDescrizione);
               
                JLabel lblQuantit = new JLabel("Quantit\u00E0:");
                lblQuantit.setBounds(59, 93, 46, 14);
                frameUpdMat.getContentPane().add(lblQuantit);
        }
        
        public boolean verificaQntIns(String qnt){
    		try{
    			Double qt = Double.parseDouble(qnt);
    			if(qt<0){
    				JOptionPane.showMessageDialog(null, "La Quantit� non pu� essere negativa!", "Attenzione", 0);
    				return false;
    			}
    			else
    				return true;
    		}
    		catch(Exception ex){
    			JOptionPane.showMessageDialog(null, "Quantit� non corretta!", "Attenzione", 0);
    			return false;
    		}
    	}

}
