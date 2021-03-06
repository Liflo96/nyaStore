package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import py.com.nyaStore.model.MedioPago;

public class MedioPagoDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	
	public boolean insertarMedioPago(MedioPago medioPago) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO medioPago"
				+ "(descripcion,IVA) VALUES"
				+ "(?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			preparedStatement.setString(1, medioPago.getDescripcion());
			preparedStatement.setBoolean(2, medioPago.getIVA());
 
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into MEDIO_PAGO table!");
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
	
	public MedioPago recuperarMedioPago(){
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQL = "SELECT * FROM medioPago";
		MedioPago medioPago = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet resp = statement.executeQuery(selectSQL);
			
			while (resp.next()) {
				
				medioPago = new MedioPago();
				medioPago.setDescripcion(resp.getString(1));
				medioPago.setIVA(resp.getBoolean(2));

			}
			System.out.println(medioPago.toString());
			return medioPago;
			
 
		} catch (SQLException e) {
 
			System.out.println("No se ha podido establecer conexion con la base de datos");
 
		} finally {
			try {
				statement.close();
				dbConnection.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		return medioPago;
	}
}

