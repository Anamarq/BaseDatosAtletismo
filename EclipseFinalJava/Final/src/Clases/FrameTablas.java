package Clases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;

public class FrameTablas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private String texto;

			public void run() {
				try {
					FrameTablas frame = new FrameTablas(texto);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JTable table;

	/**
	 * Create the frame.
	 */
	
	public FrameTablas(String texto) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Cerrar solo ventana
		setBounds(100, 100, 804, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DefaultTableModel model=new DefaultTableModel();   //Crear modelo tabla (Defecto)
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		table = new JTable(model);  //Crear Jtable
		//table.setPreferredSize(new Dimension(600,200));
		contentPane.add(table);   //A�adir a panel_1
		JScrollPane scrollPane = new JScrollPane(table);///Scrollpane para ver nombre columnas
		scrollPane.setMinimumSize(new Dimension(600,23));
		//table.setFillsViewportHeight(true);
		contentPane.add(scrollPane);  //A�adir a panel_1
		
		try {
			//MOSTRAR TABLA 

			accion.mostrar(texto,model);
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
