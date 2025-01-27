package Clases;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class accion {

	public static void mostrar( String cadena, DefaultTableModel model) throws SQLException {
		//NO modifica la DB (select..) siempre muestra tabla
		Connection con=conexion.Conector(); //Conecta
		
		Statement stmt=con.createStatement(); //
		ResultSet rset= stmt.executeQuery(cadena); //El resultado se guarda en resulset
		ResultSetMetaData rMD=rset.getMetaData();  //La clase ResultSetMetaData contiene toda la informaci�n sobre los campos de base de datos contenidos en el ResultSet
		int ncol=rMD.getColumnCount();  //N�mero de columnas 

		//PONER ETIQUETAS(NOMBRE COLUMNAS)
		// Se crea un array de etiquetas para rellenar
		Object[] etiquetas = new Object[ncol];

		// Se obtiene cada una de las etiquetas para cada columna
		for (int i = 0; i < ncol; i++)
		{
			etiquetas[i] = rMD.getColumnLabel(i + 1);
		}
		model.setColumnIdentifiers(etiquetas);
		
		
		///INTRODUCIR DATOS
		String[] dato= new String[ncol]; //array de String
		 while (rset.next()) { 
			 for(int i=0; i<ncol;++i) {
				// System.out.print(rset.getString(i));
				 dato[i]=rset.getString(i+1);  //Meter en el array 
				 
			 }
			 
			 model.addRow(dato); //A�adir al modelo
		 }
		

		
		stmt.close();
		rset.close();
		con.close();
		
	}
	public static void update( String cadena) throws SQLException {
		//Modifica la DB,(insert, delete...) No muestra tabla
		///System.out.print(cadena);
		Connection con=conexion.Conector(); //Conecta		
		Statement stmt=con.createStatement(); //
		stmt.executeUpdate(cadena); //Consulta que modifica el esquema
		stmt.close();
		con.close();
	}
	public static void subirFoto( String licencia, String nombre) throws IOException {
		Connection con=conexion.Conector(); //Conecta
		try {
			//�Que atleta es? licencia
			//�Nombre foto?
			File fichero=new File("bin/IMG/"+nombre); //Instanciar objeto tipo File a traves de la ruta 
			FileInputStream flujo=new FileInputStream(fichero); ///Pasar el objeto tipo File a un flujo de entrada para leer la info
			byte bytes[]=new byte[(int)fichero.length()]; //Array de bytes, cada posicion tiene bytes del fichero binario en filas
			flujo.read(bytes); //Guardo la info del fichero en bytes
			flujo.close();
			
			///Hacer en 2 pasos la transacion
			Statement stmt=con.createStatement(); //
			String cadena="insert into FotoAtleta(atletas_licencia,foto,nombre) values ('"+licencia+"', empty_blob(), '"+nombre+"')";  //AL principio se queda vacia la foto
			stmt.executeUpdate(cadena); //Consulta que modifica el esquema
			stmt.close();
			
			//Paso 2: actualizar fila pasando info
			PreparedStatement pstmt= con.prepareStatement(" select foto from FotoAtleta where atletas_licencia= '"+licencia+"' for update");
			ResultSet rset= pstmt.executeQuery();
			rset.next(); ///Se coloca donde esta la foto
			Blob b= rset.getBlob(1);  //Mapea el espacio de java modificando el sql (Mapeo objeto-relacional)
			b.setBytes(1,bytes);  //Escribir en la fila y columnas obtenidas el array bytes
			rset.close();
			pstmt.close();	
			//System.out.println("Foto Subida");
			con.close();
		}catch (NumberFormatException e) {
			System.out.print("Error: formato incorrecto(Se pide un n�mero)");

		}catch(SQLException e){
			System.out.print("Error: en la consulta "+e.getLocalizedMessage());
		
		}
	}

	public static void MostrarFoto( String licencia) throws IOException {
		Connection con=conexion.Conector(); //Conecta
		try {
			//�Que atleta es? licencia
			//�Nombre foto?
			
			FileOutputStream flujo=new FileOutputStream("bin/IMG/"+licencia+".jpg"); 
			PreparedStatement pstmt= con.prepareStatement(" select foto from FotoAtleta where atletas_licencia= '"+licencia+"' for update");
			ResultSet rset= pstmt.executeQuery();
			rset.next(); ///Se coloca donde esta la foto
			Blob b= rset.getBlob(1);  //Mapea el espacio de java modificando el sql (Mapeo objeto-relacional)
			
			byte bytes[]=b.getBytes((long) 1, (int) b.length()); //Obtener array de bytes, a partir del contenido de la BD 
			
			flujo.write(bytes); //Escribir en la ruta
			flujo.flush();  //Vaciar cache
			flujo.close();
			rset.close();
			pstmt.close();	
			
			///MOSTRAR FOTO EN FRAME FOTO
			FrameFoto ff=new FrameFoto(licencia);  //Crea frame2
			ff.setVisible(true); //Si es true se hace visible
			
			con.close();
		
			
		}catch (NumberFormatException e) {
			System.out.print("Error: formato incorrecto(Se pide un n�mero)");

		}catch(SQLException e){
			System.out.print("Error: en la consulta "+e.getLocalizedMessage());
		}
		
		
	}
}
