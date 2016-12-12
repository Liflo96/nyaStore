package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.com.nyaStore.model.Persona;
import py.com.nyaStore.model.Proveedor;

public class ProveedorDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	private PersonaDao personaDao = new PersonaDao();
	
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
	
//	public Proveedor recuperarProveedor(){
//		Connection dbConnection = null;
//		Statement statement = null;
//		
//		String selectSQL = "SELECT * FROM proveedor";
//		Proveedor proveedor = null;
//		
//		try {
//			dbConnection = getDBConnection();
//			statement = dbConnection.createStatement();
//			ResultSet resp = statement.executeQuery(selectSQL);
//			
//			while (resp.next()) {
//				
//				proveedor = new Proveedor();
////				proveedor.setPersonaId(resp.getInt(1));
////				proveedor.setDireccion(resp.getString(2));
////				proveedor.setTelefono(resp.getString(3));
//				proveedor.setPersonaId(resp.getString(1));
//				proveedor.setRazonSocial(resp.getString(2));
//				proveedor.setRUC(resp.getString(3));
//
//			}
//			System.out.println(proveedor.toString());
//			return proveedor;
//			
// 
//		} catch (SQLException e) {
//			e.printStackTrace();
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
//		return proveedor;
//	}
	
	public Proveedor recuperarProveedor(){
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQL = "SELECT * FROM proveedor";
		Proveedor proveedor = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = 	statement.executeQuery(selectSQL);
			
			while (rs.next()) {
				
				proveedor = new Proveedor();
				
				String personaId = rs.getString(1);
				if (personaId != null) {
					Persona persona = this.personaDao.recuperarPersona();
					proveedor.setPersonaId(Integer.valueOf(persona.getPersonaId()));			
				}
				proveedor.setRazonSocial(rs.getString(2));
				proveedor.setRUC(rs.getString(3));

			}
			System.out.println(proveedor.toString());
			return proveedor;
			
 
		} catch (SQLException e) {
 
			e.printStackTrace();
 
		} finally {
			try {
				statement.close();
				dbConnection.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		return proveedor;
	}
	
	public List<Proveedor> recuperarProveedores() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM proveedor";
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		
		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				Proveedor proveedor = new Proveedor();
				
				String personaId = rs.getString(1);
				if (personaId != null) {
					Persona persona = this.personaDao.recuperarPersona();
					proveedor.setPersonaId(Integer.valueOf(persona.getPersonaId()));
				}
				proveedor.setRazonSocial(rs.getString(2));
				proveedor.setRUC(rs.getString(3));
				
				proveedores.add(proveedor);
			}			
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
	 
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return proveedores;

	}

}

