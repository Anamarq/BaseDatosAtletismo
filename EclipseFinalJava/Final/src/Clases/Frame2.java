package Clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.SystemColor;

public class Frame2 extends JFrame {

	private JPanel contentPane;
	private JTextField textLicBusc;
	private JTextField textNombre;
	private JTextField textAp;
	private JTextField textNac;
	private JTextField textLic;
	private JTextField textCiudad;
	private JTable table;
	private JTextField textLicR;
	private JTextField textLicR2;
	private JTextField textRes;
	private String consulta;
	private String consulta2;
	private String sexo;
	private String prueba;
	private String tipo;
	private JTextField textFoto;
	private JTextField textFed;
	private JTextField textFH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private String desc;
			public void run() {
				try {
					Frame2 frame = new Frame2(desc);
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
	public Frame2(String desc) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 796, 424);
		contentPane.add(tabbedPane);
		
		///////////////////////////////Panel_1///////////////////////////////////
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Prueba", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_6_1 = new JLabel("Fecha y Hora");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6_1.setBounds(503, 125, 103, 14);
		panel_1.add(lblNewLabel_6_1);
		
		textFH = new JTextField();
		textFH.setColumns(10);
		textFH.setBounds(616, 124, 121, 20);
		panel_1.add(textFH);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tipo");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(255, 124, 98, 17);
		panel_1.add(lblNewLabel_3_1);
		
		JComboBox comboBoxT2 = new JComboBox();
		comboBoxT2.setModel(new DefaultComboBoxModel(new String[] {"Velocidad", "Saltos", "Lanzamientos"}));
		comboBoxT2.setToolTipText("");
		comboBoxT2.setBounds(363, 123, 110, 22);
		panel_1.add(comboBoxT2);
		
		JLabel lblNewLabel_8 = new JLabel("A\u00F1adir o borrar pruebas a la competici\u00F3n:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(83, 51, 432, 20);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Prueba:");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1_1.setBounds(27, 127, 98, 17);
		panel_1.add(lblNewLabel_3_1_1);
		
		JComboBox comboBoxP2 = new JComboBox();
		comboBoxP2.setModel(new DefaultComboBoxModel(new String[] {"100ml", "400ml", "1500ml", "100/110m vallas", "Longitud", "Triple", "P\u00E9rtiga", "Altura", "Jabalina", "Peso", "Martillo", "Disco"}));
		comboBoxP2.setToolTipText("");
		comboBoxP2.setBounds(97, 126, 110, 22);
		panel_1.add(comboBoxP2);
		
		JButton btnNewButton_4 = new JButton("A\u00F1adir");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prueba=comboBoxP2.getSelectedItem().toString();//Coge prueba del combo box
				tipo=comboBoxT2.getSelectedItem().toString();//Coge tipo del combo box
				System.out.println(textFH.getText());
				consulta="Insert into TIENE(COMPETICION_DESCRIPCION,PRUEBAS_PRUEBA,HORA) values('"+desc+"','"+prueba+"',to_date('"+textFH.getText()+"','DD/MM/YY HH24:MI'))";
				
				try {
					accion.update(consulta);
				}catch(SQLIntegrityConstraintViolationException e1) {
					consulta2="Insert into PRUEBAS(PRUEBA,TIPO) values('"+prueba+"','"+tipo+"')";  //La prueba nunca ha existido, va a catch
					try {
						accion.update(consulta2);
						accion.update(consulta);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null,"Prueba a�adida!");
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_4.setBounds(150, 192, 145, 55);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Borrar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prueba=comboBoxP2.getSelectedItem().toString();//Coge prueba del combo box
				consulta="delete tiene  where pruebas_prueba='"+prueba+"' and competicion_descripcion='"+desc+"'";
				try {
					accion.update(consulta);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null,"Prueba borrada!");
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_5.setBounds(416, 192, 121, 55);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_10 = new JButton("Horario");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta="select tiene.hora, pruebas.* from pruebas join Tiene on prueba=pruebas_prueba where competicion_descripcion='"+desc+"' order by hora";
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
			}
		});
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_10.setBounds(264, 302, 145, 46);
		panel_1.add(btnNewButton_10);
		
	
		
		
		////////////////////////////////////PANEL_2/////////////////////////////////////////
		
		////////////////////////////////////PANEL_3/////////////////////////////////////////
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Resultados", null, panel_3, null);
		panel_3.setLayout(null);
		
		JComboBox comboBoxS = new JComboBox();  ////
		comboBoxS.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBoxS.setToolTipText("");
		comboBoxS.setBounds(237, 66, 110, 22);
		panel_3.add(comboBoxS);
		
		
		
		JComboBox comboBoxPR = new JComboBox();
		comboBoxPR.setModel(new DefaultComboBoxModel(new String[] {"100ml", "400ml", "1500ml", "100/110m vallas", "Longitud", "Triple", "P\u00E9rtiga", "Altura", "Jabalina", "Peso", "Martillo", "Disco"}));
		comboBoxPR.setToolTipText("");
		comboBoxPR.setBounds(359, 127, 110, 22);
		panel_3.add(comboBoxPR);
		
		JComboBox comboBoxP = new JComboBox();
		comboBoxP.setModel(new DefaultComboBoxModel(new String[] {"100ml", "400ml", "1500ml", "100/110m vallas", "Longitud", "Triple", "P\u00E9rtiga", "Altura", "Jabalina", "Peso", "Martillo", "Disco"}));
		comboBoxP.setToolTipText("");
		comboBoxP.setBounds(131, 300, 86, 22);
		panel_3.add(comboBoxP);
		
		JLabel lblNewLabel_11 = new JLabel("Aqu\u00ED se pueden consultar los resultados:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(83, 11, 299, 22);
		panel_3.add(lblNewLabel_11);
		
		JButton btnNewButton_6 = new JButton("Resultados totales");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sexo=comboBoxS.getSelectedItem().toString();
				consulta= "select atletas.licencia, atletas.Nombre, atletas.apellidos,participa.pruebas_prueba AS prueba,participa.resultado"+
						" from (participa join tiene on participa.pruebas_prueba=tiene.pruebas_prueba)join atletas on licencia=atletas_licencia"+
						 " where competicion_descripcion ='"+desc+"' and sexo= '"+sexo+"'";
				
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
			}
		});
		btnNewButton_6.setBounds(472, 53, 208, 35);
		panel_3.add(btnNewButton_6);
		
		JLabel lblNewLabel_12 = new JLabel("Resultados por prueba, introduzca prueba:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_12.setBounds(44, 121, 253, 35);
		panel_3.add(lblNewLabel_12);
		
		JButton btnNewButton_7 = new JButton("Resultados por prueba");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prueba=comboBoxPR.getSelectedItem().toString();  //Coge prueba
				sexo=comboBoxS.getSelectedItem().toString();  //sexo
				consulta="select atletas.licencia, atletas.Nombre, atletas.apellidos,participa.resultado  from ((participa join tiene on participa.pruebas_prueba=tiene.pruebas_prueba) join atletas on licencia=atletas_licencia)"
						+ " where (competicion_descripcion ='"+desc+"' and participa.pruebas_prueba='"+prueba+"') and sexo='"+sexo+"'"
						+ " order by "
						+ " case when participa.pruebas_prueba='100ml' or participa.pruebas_prueba='400ml' "
						+ " or participa.pruebas_prueba='1500ml' or participa.pruebas_prueba='100/110m vallas' then resultado end ,"
						+ " case when participa.pruebas_prueba='Altura' or participa.pruebas_prueba='Longitud' or participa.pruebas_prueba='P�rtiga' or participa.pruebas_prueba='Triple' "
						+ " or participa.pruebas_prueba='Jabalina' or participa.pruebas_prueba='Peso' or participa.pruebas_prueba='Martillo' or participa.pruebas_prueba='Disco'"
						+ " then resultado end desc";
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
						
			}
		});
		btnNewButton_7.setBounds(573, 124, 166, 28);
		panel_3.add(btnNewButton_7);
		
		JLabel lblNewLabel_12_1 = new JLabel("Resultados por atleta, introduzca Licencia:");
		lblNewLabel_12_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_12_1.setBounds(24, 198, 273, 35);
		panel_3.add(lblNewLabel_12_1);
		
		textLicR = new JTextField();
		textLicR.setColumns(10);
		textLicR.setBounds(336, 202, 139, 28);
		panel_3.add(textLicR);
		
		JButton btnNewButton_7_1 = new JButton("Resultados por atleta");
		btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sexo=comboBoxS.getSelectedItem().toString();  //sexo
				consulta="select atletas.licencia, atletas.Nombre, atletas.apellidos,participa.pruebas_prueba AS prueba,participa.resultado from ((participa join tiene on participa.pruebas_prueba=tiene.pruebas_prueba) join atletas on licencia=atletas_licencia)"
						+ "where (competicion_descripcion ='"+desc+"' and licencia='"+textLicR.getText()+"') and sexo='"+sexo+"'";
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
			}
		});
		btnNewButton_7_1.setBounds(552, 202, 166, 28);
		panel_3.add(btnNewButton_7_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nombre prueba");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(10, 299, 129, 20);
		panel_3.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Licencia atleta");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4_1.setBounds(267, 303, 120, 14);
		panel_3.add(lblNewLabel_4_1);
		
		textLicR2 = new JTextField();
		textLicR2.setColumns(10);
		textLicR2.setBounds(406, 301, 86, 20);
		panel_3.add(textLicR2);
		
		JLabel lblNewLabel_5_1 = new JLabel("Resultado");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(520, 302, 90, 14);
		panel_3.add(lblNewLabel_5_1);
		
		textRes = new JTextField();
		textRes.setColumns(10);
		textRes.setBounds(632, 301, 86, 20);
		panel_3.add(textRes);
		
		JLabel lblNewLabel_14 = new JLabel("A\u00F1adir o borrar resultado, introduzca datos (poner resultado con \",\" ): ");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_14.setBounds(27, 258, 483, 14);
		panel_3.add(lblNewLabel_14);
		
		JLabel lblNewLabel_11_1 = new JLabel("Para cualquier consulta, elija sexo:");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11_1.setBounds(27, 65, 190, 22);
		panel_3.add(lblNewLabel_11_1);
		
		JLabel lblNewLabel_15 = new JLabel("Resultados totales:");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_15.setBounds(459, 16, 129, 14);
		panel_3.add(lblNewLabel_15);
		
		JButton btnNewButton_8 = new JButton("A\u00F1adir");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prueba=comboBoxP.getSelectedItem().toString();  //prueba
				consulta="Insert into PARTICIPA(ATLETAS_LICENCIA,PRUEBAS_PRUEBA,RESULTADO) values('"+textLicR2.getText()+"','"+prueba+"','"+textRes.getText()+"')";
				try {
					accion.update(consulta);
				}catch (SQLIntegrityConstraintViolationException e1) {
					JOptionPane.showMessageDialog(null,"A�ade antes la prueba a la competici�n!");
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null,"Resultado a�adido!");
			}
		});
		btnNewButton_8.setBounds(196, 338, 101, 35);
		panel_3.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Borrar");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prueba=comboBoxP.getSelectedItem().toString();  //prueba
				consulta="delete participa where atletas_licencia='"+textLicR2.getText()+"'and pruebas_prueba='"+prueba+"'";
				try {
					accion.update(consulta);
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null,"Resultado Borrado!");
			}
		});
		btnNewButton_9.setBounds(444, 338, 110, 35);
		panel_3.add(btnNewButton_9);
		
		
		////////////////////////////////////PANEL/////////////////////////////////////////
		JPanel panel = new JPanel();
		tabbedPane.addTab("Atletas", null, panel, null);
		panel.setLayout(null);
		

		
		JButton btnNewButton = new JButton("Atletas inscritos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				consulta="select atletas.* from atletas join compite on atletas_licencia=licencia where competicion_descripcion='"+desc+"'";
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
			}
		});
		btnNewButton.setBounds(283, 41, 220, 33);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Para buscar o borrar un atleta,  y ver su foto introduzca su Licencia:  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 101, 387, 33);
		panel.add(lblNewLabel);
		
		textLicBusc = new JTextField();
		textLicBusc.setBounds(433, 105, 98, 26);
		panel.add(textLicBusc);
		textLicBusc.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta="select atletas.* from atletas join compite on atletas_licencia=licencia where competicion_descripcion='"+desc+"'and atletas_licencia='"+textLicBusc.getText()+"'"; 
				FrameTablas ft=new FrameTablas(consulta);
				ft.setVisible(true); //Si es true se hace visible
				try {
					accion.MostrarFoto(textLicBusc.getText());   ///Muestra la foto de ese atleta
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(582, 86, 107, 27);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta="delete compite where competicion_descripcion='"+desc+"' and atletas_licencia='"+textLicBusc.getText()+"'";
				consulta2="delete participa where atletas_licencia in'"+textLicBusc.getText()+"'";
				try {
					accion.update(consulta);
					accion.update(consulta2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(582, 124, 107, 26);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Para a\u00F1adir atleta, introduzca sus datos. La foto debe estar en la carpeta IMG que est\u00E1 en la raiz/bin. Federacion NO es obligatoria");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(23, 170, 750, 20);
		panel.add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(153, 224, 86, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textAp = new JTextField();
		textAp.setBounds(131, 288, 108, 20);
		panel.add(textAp);
		textAp.setColumns(10);
		
		textNac = new JTextField();
		textNac.setBounds(153, 344, 86, 20);
		panel.add(textNac);
		textNac.setColumns(10);
		
		textLic = new JTextField();
		textLic.setBounds(383, 224, 86, 20);
		panel.add(textLic);
		textLic.setColumns(10);
		
		textCiudad = new JTextField();
		textCiudad.setBounds(383, 288, 86, 20);
		panel.add(textCiudad);
		textCiudad.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(23, 224, 75, 20);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(23, 288, 98, 17);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha nacimiento");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(23, 346, 120, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Licencia");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(283, 230, 90, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Ciudad");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(283, 291, 64, 14);
		panel.add(lblNewLabel_6);
		
		JComboBox comboBoxS_1 = new JComboBox();
		comboBoxS_1.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBoxS_1.setToolTipText("");
		comboBoxS_1.setBounds(371, 343, 110, 22);
		panel.add(comboBoxS_1);
		
		
		JButton btnNewButton_3 = new JButton("A\u00F1adir");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sexo=comboBoxS_1.getSelectedItem().toString();//Coge el sexo del combo box
				consulta= "Insert into ATLETAS(LICENCIA,NOMBRE,APELLIDOS,SEXO,FECHANACIMIENTO,FEDERACIONES_FEDERACION,CIUDAD) values('"+textLic.getText()+
						"','"+textNombre.getText()+"','"+textAp.getText()+"','"+sexo+"',to_date('"+textNac.getText()+
						"','DD/MM/RR'),'"+textFed.getText()+"','"+textCiudad.getText()+"')";
				consulta2="Insert into COMPITE(COMPETICION_DESCRIPCION, ATLETAS_LICENCIA) values('"+desc+"','"+textLic.getText()+"')";
				
				try {
					//MOSTRAR TABLA EN PANEL_1
					accion.update(consulta);
					accion.update(consulta2);
					accion.subirFoto(textLic.getText(),textFoto.getText());
					JOptionPane.showMessageDialog(null,"Atleta y fotos subidos!");
				
				}catch(SQLIntegrityConstraintViolationException e1) {
					try {
						accion.update(consulta2);   ///Si ya existe el atleta, simplemente se a�ade a competicion
					}catch (SQLIntegrityConstraintViolationException e2) {
						JOptionPane.showMessageDialog(null,"Ya esta inscrito!"); ///
					}catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					JOptionPane.showMessageDialog(null,"Atleta y foto subidos!");
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Error ");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(532, 336, 98, 36);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_7 = new JLabel("Foto");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(532, 226, 46, 17);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_5_2 = new JLabel("Sexo");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2.setBounds(283, 350, 90, 14);
		panel.add(lblNewLabel_5_2);
		
		textFoto = new JTextField();
		textFoto.setBounds(615, 224, 86, 20);
		panel.add(textFoto);
		textFoto.setColumns(10);
		
		textFed = new JTextField();
		textFed.setBounds(615, 268, 86, 20);
		panel.add(textFed);
		textFed.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Federacion");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_16.setBounds(503, 271, 86, 17);
		panel.add(lblNewLabel_16);
		
		JButton btnNewButton_11 = new JButton("Cambiar foto");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta="delete FotoAtleta where atletas_licencia= '"+textLic.getText()+"'";
				try {
					accion.update(consulta);
					accion.subirFoto(textLic.getText(),textFoto.getText());
					JOptionPane.showMessageDialog(null,"foto cambiada!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Error ");
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_11.setBounds(653, 336, 120, 36);
		panel.add(btnNewButton_11);
		
		JLabel lblNewLabel_9 = new JLabel("La foto debe tener dimensiones de maximo 600x300. Para cambiar foto solo se necesita introducir licencia y nombre foto");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(23, 199, 742, 14);
		panel.add(lblNewLabel_9);
		
		
		
		
	}
}
