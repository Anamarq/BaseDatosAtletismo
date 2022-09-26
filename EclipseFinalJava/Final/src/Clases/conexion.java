package Clases;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class conexion {
	
	static Connection conn=null;

	public static Connection Conector() {
		 try {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@Afrodita.lcc.uma.es:1521:Apolo", "UBD1669", "Meaburro0710");
		
		return conn;
		 }catch(Exception e) {
			 JOptionPane.showMessageDialog(null,"Conexión Fallida!");
			 return null;
			 
		 }
	}
		

	

}
