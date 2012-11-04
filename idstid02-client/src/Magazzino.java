import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JTable;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.ArrayList;
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
					ResourceClass rs = new ResourceClass(); 
					List<Materiale> lista = rs.getResource("magazzinoterzista");
			   
				    Iterator<Materiale> it=lista.iterator();
				    int cntDt = lista.size();
				    _titles.add("Descrizione");
				    _titles.add("Codivùce");
				    _data = new String[cntDt][2];
				    int k = 0;
				    while(it.hasNext())
			        {//[riga][colonna]
			          Materiale mtCl = (Materiale)it.next();
			          if(k<cntDt){
			           _data[k][0] = mtCl.getDescrizione();
			           _data[k][1] = mtCl.getCodice();
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
