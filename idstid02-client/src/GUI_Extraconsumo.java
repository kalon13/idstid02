import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.UIManager;




public class GUI_Extraconsumo {

	private JFrame frmExtraconsumo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Extraconsumo window = new GUI_Extraconsumo();
					window.frmExtraconsumo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_Extraconsumo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExtraconsumo = new JFrame();
		frmExtraconsumo.setTitle("Extraconsumo");
		frmExtraconsumo.setBounds(100, 100, 322, 270);
		frmExtraconsumo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblBollaN = new JLabel("Bolla n\u00B0:");
		lblBollaN.setBounds(7, 7, 94, 20);
		
		textField = new JTextField();
		textField.setBounds(79, 7, 61, 20);
		textField.setEditable(false);
		textField.setColumns(10);
		
//		table = new JTable();
//		table.setBounds(179, 7, 0, 0);
		frmExtraconsumo.getContentPane().setLayout(null);
		
		frmExtraconsumo.getContentPane().add(lblBollaN);
		frmExtraconsumo.getContentPane().add(textField);
		
		JLabel lblMaterialiTeorici = new JLabel("Materiali teorici:");
		lblMaterialiTeorici.setBounds(7, 31, 105, 14);
		frmExtraconsumo.getContentPane().add(lblMaterialiTeorici);
		
		JButton btnRichiedi = new JButton("Richiedi");
		btnRichiedi.setBounds(114, 206, 90, 23);
		frmExtraconsumo.getContentPane().add(btnRichiedi);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setBounds(214, 206, 90, 23);
		frmExtraconsumo.getContentPane().add(btnEsci);
		
		
		//table_1 = new JTable();
		//*****************
//		DefaultTableModel model;
//		String data[][] = {{" "," "," "}};
//	    String col[] = {"Name","Course","Subject"};
//		model = new DefaultTableModel(data,col);
//		table_1 = new JTable(model);
//		
//		table_1 = new JTable(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null},
//			},
//			new String[] {
//				"Name", "Course", "Subject"
//			}
//		));
		//*****************
		
		int rows = 3;
		int cols = 2;
		JTable table_1 = new JTable(rows, cols);
		JTableHeader header = table_1.getTableHeader();
		
		table_1.setBounds(7, 56, 297, 139);
		frmExtraconsumo.getContentPane().add(header);
		frmExtraconsumo.getContentPane().add(table_1);
		
		//int rows = 21;
		//int cols = 3;
		//JTable table = new JTable(rows, cols);
		//JTableHeader header = table.getTableHeader();
		//JPanel panel = new JPanel();
		//panel.setLayout(new BorderLayout());
		//panel.add(header, BorderLayout.NORTH);
		//panel.add(table, BorderLayout.CENTER);
		//frmExtraconsumo.getContentPane().add(panel);
	}
}
