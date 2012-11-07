import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.ComponentOrientation;


public class GUI_UpdMagazzino {

	JFrame frameUpdMat;
	private JTextField textCod;
	private JTextField textDesc;
	private JTextField textQnt;
	private String cod;
	private String desc;
	private String qnt;
	private int id;
	/**
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_UpdMagazzino window = new GUI_UpdMagazzino();
					window.frameUpdMat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public GUI_UpdMagazzino(int id, String cod, String desc, String qnt) {
		this.cod = cod;
		this.desc = desc;
		this.qnt = qnt;
		this.id = id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameUpdMat = new JFrame();
		frameUpdMat.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frameUpdMat.setResizable(false);
		frameUpdMat.setBounds(100, 100, 286, 216);
		frameUpdMat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameUpdMat.getContentPane().setLayout(null);
		
		textCod = new JTextField();
		textCod.setText(cod);
		textCod.setBounds(139, 25, 86, 20);
		frameUpdMat.getContentPane().add(textCod);
		textCod.setColumns(10);
		
		textDesc = new JTextField();
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
				String path = "magazzinoterzista";
				Materiale m = new Materiale(id, textCod.getText(), textDesc.getText(), Double.parseDouble(textQnt.getText()));
				ResourceClass.updResources(Materiale.class, path, String.valueOf(id), m);
				frameUpdMat.setVisible(false);
			}
		});
		btnSave.setBounds(45, 133, 89, 23);
		frameUpdMat.getContentPane().add(btnSave);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameUpdMat.setVisible(false);
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

}
