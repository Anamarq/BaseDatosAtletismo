package Clases;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.sql.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Window.Type;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class Inicio {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTable table;
	private JTextField sedeT;
	private JTextField fechaT;
	private String sede;
	private String fecha;
	private String descripcion;
	private JTextField DescT;
	private String consulta;
	private JTextField textFedB;
	private JTextField textFedA;
	private JTextField textTlf;
	private JTextField textWeb;
	private JTextField textLoc;
	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();  ///Iniciar aplicacion
		conn=conexion.Conector();
		if (conn!=null)
			JOptionPane.showMessageDialog(null,"Conexi�n exitosa!");
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 781, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 765, 430);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Inicio", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Lista Competiciones", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Organizar", null, panel_2, null);
		panel_2.setLayout(null);
		

		////////////////////////////////////////PANEL/////////////////////////////////////////////////
		JLabel Titulo = new JLabel("BASE DE DATOS DE COMPETICIONES DE ATLETISMO");
		Titulo.setForeground(Color.RED);
		Titulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		Titulo.setBounds(153, 11, 413, 46);
		panel.add(Titulo);
		
		JLabel Logo = new JLabel("");
		Image img= new ImageIcon(this.getClass().getResource("/running-shoes.png")).getImage();   //////Imagen 
		Logo.setIcon(new ImageIcon(img));  ////Imagen
		Logo.setBounds(226, 151, 422, 191);
		panel.add(Logo);
		
		JLabel lblNewLabel_5 = new JLabel("Para ver los datos de una competici\u00F3n en concreto (atletas, pruebas...), ir a la pesta\u00F1a \"Organizar\". O explora otras pesta\u00F1as.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(24, 68, 700, 66);
		panel.add(lblNewLabel_5);
		
		
		////////////////////////////////////////PANEL_1/////////////////////////////////////////////////
		
		DefaultTableModel model;
		model=new DefaultTableModel();   //Crear modelo tabla (Defecto)
		table = new JTable(model);  //Crear Jtable
		panel_1.add(table);   //A�adir a panel_1
		JScrollPane scrollPane ;
		scrollPane= new JScrollPane(table);///Scrollpane para ver nombre columnas
		table.setFillsViewportHeight(true);
		panel_1.add(scrollPane);  //A�adir a panel_1
		
		try {
			//MOSTRAR TABLA EN PANEL_1
			accion.mostrar( "select* from competicion",model);
	
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		////////////////////////////////////////PANEL_2/////////////////////////////////////////////////
		
		sedeT = new JTextField();
		sedeT.setBounds(140, 117, 171, 28);
		panel_2.add(sedeT);
		sedeT.setColumns(10);
		
		fechaT = new JTextField();
		fechaT.setBounds(140, 166, 171, 28);
		panel_2.add(fechaT);
		fechaT.setColumns(10);
		
		JLabel labelSede = new JLabel("SEDE");
		labelSede.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSede.setLabelFor(frame);
		labelSede.setBounds(25, 121, 69, 21);
		panel_2.add(labelSede);
		
		JLabel labelFecha = new JLabel("FECHA");
		labelFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelFecha.setBounds(25, 170, 69, 21);
		panel_2.add(labelFecha);
		
		DescT = new JTextField();
		DescT.setColumns(10);
		DescT.setBounds(141, 68, 231, 21);
		panel_2.add(DescT);
		
		JButton btnNewButton = new JButton("Datos");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		//Si se pulsa el bot�n:
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 descripcion=DescT.getText();
				//frame.dispose(); //esconde el frame
				Frame2 f2=new Frame2(descripcion);  //Crea frame2
				f2.setVisible(true); //Si es true se hace visible
			}
		});
		btnNewButton.setBounds(492, 164, 109, 34);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Nueva");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sede= sedeT.getText();
				fecha=fechaT.getText();
				descripcion= DescT.getText();
				consulta= "Insert into COMPETICION(SEDE,FECHA,DESCRIPCION) values('"+sede+"', to_date('"+fecha+"','DD/MM/RR'), '"+descripcion+"')";
				
				try {
					//MOSTRAR TABLA EN PANEL_1
					accion.update(consulta);
					accion.mostrar( "select * from competicion where descripcion='"+descripcion+"'",model);  //PAra actualizar
					JOptionPane.showMessageDialog(null,"Competici�n a�adida!");
				
				}catch (SQLIntegrityConstraintViolationException e2) {
					JOptionPane.showMessageDialog(null,"Esa competicion ya existe en la base de datos!"); ///
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(492, 82, 109, 34);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sede= sedeT.getText();
				fecha=fechaT.getText();
				consulta= "delete competicion where descripcion='"+descripcion+"'";
				
				try {
					//MOSTRAR TABLA EN PANEL_1
					accion.update(consulta);  //No se actualiza :(
					JOptionPane.showMessageDialog(null,"Competici�n Borrada!");
					//model.fireTableDataChanged();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(492, 234, 109, 34);
		panel_2.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Introduzca los datos de la competici\u00F3n. \r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(44, 9, 290, 34);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Datos: Busca y muestra los datos de la competici\u00F3n \r\n");
		lblNewLabel_2.setBounds(420, 113, 260, 40);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nueva: Introduce una nueva competici\u00F3n\r\n");
		lblNewLabel_3.setBounds(420, 48, 243, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Borrar: borra una competici\u00F3n");
		lblNewLabel_4.setBounds(420, 209, 212, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblDescripcionsoloSi = new JLabel("DESCRIPCION: ");
		lblDescripcionsoloSi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcionsoloSi.setBounds(25, 68, 96, 21);
		panel_2.add(lblDescripcionsoloSi);
		
		JLabel lblNewLabel_12 = new JLabel("Para a\u00F1adir nueva copetici\u00F3n hay que introducir la descripci\u00F3n, sede y fecha. Buscar datos y Borrar solo requieren la descripci\u00F3n");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(10, 299, 721, 14);
		panel_2.add(lblNewLabel_12);
		
		
		
////////////////////////////////////PANEL_1/////////////////////////////////////////
				
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		tabbedPane.addTab("Federaciones", null, panel_11, null);
		panel_11.setLayout(null);
		
		JButton btnNewButton_4 = new JButton("Mostrar federaciones ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta="select * from Federaciones ";
				
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
			}
		});
		btnNewButton_4.setBounds(412, 38, 243, 39);
		panel_11.add(btnNewButton_4);
		
		JLabel lblNewLabel_8 = new JLabel("Para borrar una federaci\u00F3n, introduzca su Nombre:  ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(20, 117, 346, 33);
		panel_11.add(lblNewLabel_8);
		
		textFedB = new JTextField();
		textFedB.setColumns(10);
		textFedB.setBounds(442, 140, 98, 26);
		panel_11.add(textFedB);
		
		JButton btnNewButton_1_1 = new JButton("Mostrar atletas");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta="select * from atletas where federaciones_federacion='"+textFedB.getText()+"'";
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
			}
		});
		btnNewButton_1_1.setBounds(599, 160, 107, 27);
		panel_11.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Borrar");
		btnNewButton_2_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			consulta= "delete federaciones where federacion='"+textFedB.getText()+"'";
			
			try {
				accion.update(consulta);
				JOptionPane.showMessageDialog(null,"Federacion Borrada!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		});
		btnNewButton_2_1.setBounds(599, 121, 107, 26);
		panel_11.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("Para a\u00F1adir federacion, introduzca sus datos:");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8_1.setBounds(36, 214, 300, 33);
		panel_11.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_9 = new JLabel("Nombre");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9.setBounds(36, 263, 80, 19);
		panel_11.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Telefono");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10.setBounds(36, 313, 98, 26);
		panel_11.add(lblNewLabel_10);
		
		JLabel lblNewLabel_9_1 = new JLabel("Web");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9_1.setBounds(352, 263, 80, 19);
		panel_11.add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_10_1 = new JLabel("Localizaci\u00F3n");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10_1.setBounds(352, 313, 98, 26);
		panel_11.add(lblNewLabel_10_1);
		
		textFedA = new JTextField();
		textFedA.setBounds(115, 264, 175, 20);
		panel_11.add(textFedA);
		textFedA.setColumns(10);
		
		textTlf = new JTextField();
		textTlf.setBounds(115, 318, 175, 20);
		panel_11.add(textTlf);
		textTlf.setColumns(10);
		
		textWeb = new JTextField();
		textWeb.setBounds(442, 264, 161, 20);
		panel_11.add(textWeb);
		textWeb.setColumns(10);
		
		textLoc = new JTextField();
		textLoc.setBounds(444, 318, 159, 20);
		panel_11.add(textLoc);
		textLoc.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("A\u00F1adir");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta="Insert into FEDERACIONES(FEDERACION,TELEFONO,WEB,LOCALIZACI�N) values('"+textFedA.getText() +"','"+textTlf.getText()+"','"+textWeb.getText()+"','"+textLoc.getText()+"')";
				try {
					accion.update(consulta);
					JOptionPane.showMessageDialog(null,"Federaci�n a�adida!");
				}catch (SQLIntegrityConstraintViolationException e2) {
					JOptionPane.showMessageDialog(null,"Ya existe esa federaci�n en la base de datos!"); ///
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(645, 277, 98, 33);
		panel_11.add(btnNewButton_5);
		JLabel lblNewLabel_7 = new JLabel("Lista de federaciones de cada Pa\u00EDs");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(47, 50, 300, 14);
		panel_11.add(lblNewLabel_7);
		JLabel lblNewLabel_11 = new JLabel("Tambi\u00E9n puede buscar atletas de una federaci\u00F3n(introduce nombre):");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(20, 165, 385, 14);
		panel_11.add(lblNewLabel_11);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Mejores Marcas", null, panel_3, null);
		panel_3.setLayout(null);

		////////////////////////////////////////PANEL_3/////////////////////////////////////////////////
		
		JComboBox comboBoxS = new JComboBox();
		comboBoxS.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBoxS.setToolTipText("");
		comboBoxS.setBounds(366, 205, 110, 22);
		panel_3.add(comboBoxS);
		
		JComboBox comboBoxT = new JComboBox();
		comboBoxT.setModel(new DefaultComboBoxModel(new String[] {"Velocidad", "Saltos", "Lanzamientos"}));
		comboBoxT.setToolTipText("");
		comboBoxT.setBounds(219, 205, 110, 22);
		panel_3.add(comboBoxT);
		
		JComboBox comboBoxP = new JComboBox();
		comboBoxP.setModel(new DefaultComboBoxModel(new String[] {"100ml", "400ml", "1500ml", "100/110m vallas", "Longitud", "Triple", "P\u00E9rtiga", "Altura", "Jabalina", "Peso", "Martillo", "Disco"}));
		comboBoxP.setToolTipText("");
		comboBoxP.setBounds(90, 205, 86, 22);
		panel_3.add(comboBoxP);
		
		JLabel lblNewLabel_1 = new JLabel("Introduzca pruba para ver las mejores marcas de todas las competiciones :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(61, 32, 549, 41);
		panel_3.add(lblNewLabel_1);
		
		JButton btnNewButton_41 = new JButton("Buscar");
		btnNewButton_41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sexo=comboBoxS.getSelectedItem().toString();//Coge el sexo del combo box
				String prueba=comboBoxP.getSelectedItem().toString();//Coge el sexo del combo box
				consulta="select atletas.licencia, atletas.Nombre, atletas.apellidos,participa.resultado  from (participa join atletas on licencia=atletas_licencia)"
						+ " where  participa.pruebas_prueba='"+prueba+"' and sexo='"+sexo+"'"
						+ " order by"
						+ " case when participa.pruebas_prueba='100ml' or participa.pruebas_prueba='400ml' "
						+ " or participa.pruebas_prueba='1500ml' or participa.pruebas_prueba='100/110m vallas' then resultado end ,"
						+ " case when participa.pruebas_prueba='Altura' or participa.pruebas_prueba='Longitud' or participa.pruebas_prueba='P�rtiga' or participa.pruebas_prueba='Triple' "
						+ " or participa.pruebas_prueba='Jabalina' or participa.pruebas_prueba='Peso' or participa.pruebas_prueba='Martillo' or participa.pruebas_prueba='Disco'"
						+ " then resultado end desc";
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
				
			}
		});
		btnNewButton_41.setBounds(510, 196, 100, 41);
		panel_3.add(btnNewButton_41);
		
		JLabel lblNewLabel_6 = new JLabel("Prueba:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(90, 151, 86, 17);
		panel_3.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Tipo:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6_1.setBounds(228, 154, 86, 17);
		panel_3.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Sexo:");
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6_1_1.setBounds(369, 165, 86, 17);
		panel_3.add(lblNewLabel_6_1_1);
		
		
		
		
		
		
		
		
		
		
	}
}


/*//frame.dispose(); //esconde el frame
				InfoCompeticiones ic=new InfoCompeticiones();
				ic.setVisible(true); //Si es true se hace visible*/
