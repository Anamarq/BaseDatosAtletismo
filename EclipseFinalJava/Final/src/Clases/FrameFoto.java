package Clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class FrameFoto extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String licencia = null;
					FrameFoto frame = new FrameFoto(licencia);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameFoto(String licencia) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Imagen = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("/IMG/"+licencia+".jpg")).getImage();   ////La foto del atleta

		Imagen.setIcon(new ImageIcon(img));  ////Imagen
		Imagen.setBounds(0, 0, 609, 314);
		contentPane.add(Imagen);
	}

}
