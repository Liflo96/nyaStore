package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import py.com.nyaStore.model.PersonaFisica;

public class PersonaFisicaDao {
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	
	public boolean insertarPersonaFisica(PersonaFisica personaFisica) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO personaFisica"
				+ "(nombre, apellido, CI, fechaNacimiento, sexo) VALUES"
				+ "(?,?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			preparedStatement.setString(1, personaFisica.getNombre());
			preparedStatement.setString(2, personaFisica.getApellido());
			preparedStatement.setString(3, personaFisica.getCI());
			preparedStatement.setDate(4, (Date) personaFisica.getFechaNacimiento());
			preparedStatement.setString(5, personaFisica.getSexo());
 
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into PERSONA_FISICA table!");
			return true;
 
		} catch (SQLException e) {
 
			System.out.println("No se ha podido establecer conexion con la base de datos");
			return false;
			
 
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
	
	private static Connection getDBConnection() {
		 
		Connection dbConnection = null;
 
		try {
 
			Class.forName(DB_DRIVER);
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("No se ha encontrado descripcion en DB_DRIVER");
 
		}
 
		try {
 
			dbConnection = DriverManager.getConnection(
                            DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
 
		} catch (SQLException e) {
 
			System.out.println("No se ha podido establecer conexion con la base de datos");
 
		}
 
		return dbConnection;
 
	}
	
//	public PersonaFisica recuperarPersonaFisica(){
//		Connection dbConnection = null;
//		Statement statement = null;
//		
//		String selectSQL = "SELECT * FROM personaFisica";
//		PersonaFisica personaFisica = null;
//		
//		try {
//			dbConnection = getDBConnection();
//			statement = dbConnection.createStatement();
//			ResultSet resp = statement.executeQuery(selectSQL);
//			
//			while (resp.next()) {
//				
//				personaFisica = new PersonaFisica();
//				personaFisica.setNombre(resp.getString(1));
//				personaFisica.setApellido(resp.getString(2));
//				personaFisica.setCI(resp.getString(3));
//				personaFisica.setFechaNacimiento(resp.getDate(4));
//				personaFisica.setSexo(resp.getString(5));
//
//			}
//			System.out.println(personaFisica.toString());
//			return personaFisica;
//			
// 
//		} catch (SQLException e) {
// 
//			System.out.println("No se ha podido establecer conexion con la base de datos");
// 
//		} finally {
//			try {
//				statement.close();
//				dbConnection.close();
//				} catch (SQLException e) {
//				e.printStackTrace();
//				}
//		}
//		return personaFisica;
//	}

}
