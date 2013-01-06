package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JScrollPane;

public class GUI_Image {

	public JFrame frmModelloScarpa;
	private BufferedImage image;
	private JLabel lblImage;
	private String url;


	public GUI_Image(String url) {
		this.url = url;
		initialize();
	}

	private void initialize() {
		frmModelloScarpa = new JFrame();
		frmModelloScarpa.setResizable(false);
		frmModelloScarpa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModelloScarpa.setTitle("Modello Scarpa");
		frmModelloScarpa.setBounds(100, 100, 689, 541);
		frmModelloScarpa.getContentPane().setLayout(null);
		
		try {
			
			image = ImageIO.read(new URL("http://localhost:8080/media/"+url));
	    } catch (IOException ex) {
	            System.out.print(ex);
	    }
		
		ImageIcon img=new ImageIcon(image);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 663, 491);
		frmModelloScarpa.getContentPane().add(scrollPane);
		lblImage = new JLabel(img);
		scrollPane.setViewportView(lblImage);
		lblImage.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	}
}
