import java.awt.Component;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.GenericType;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Magazzino {

	private JFrame frame;
	private static String[][] _data = null;
	private static List<String> _titles = new ArrayList<String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		  public void run() {
				try {
					
					String path = "magazzinoterzista";
					
					String id = "4";
					Materiale m = new Materiale(2, "1000", "desTest", 2.3);
					ResourceClass.updResources(Materiale.class, path, "2", m);
					Materiale m1 = new Materiale(0, "1001", "desTest", 2.3);
					id = ResourceClass.addResources(path, m1);
					m1.setId(Integer.parseInt(id));
					
					id = "10";
					ResourceClass.delResources(path, id);
					List<Materiale> lista = ResourceClass.getResources(Materiale.class, path);
					
				    Iterator<Materiale> it=lista.iterator();
				    int cntDt = lista.size();
				    _titles.add("Descrizione");
				    _titles.add("Codice");
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Table tableMag = new Table(_titles,  _data);
	    //tableMag.setValueAt("var", 0, 0);
	    Component table = tableMag.createTable();
	    frame.getContentPane().add(table, BorderLayout.NORTH);
	}

}