package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import py.com.nyaStore.model.Cliente;
import py.com.nyaStore.model.FacturaVenta;


public class FacturaVentaDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	private ClienteDao clienteDao = new ClienteDao();
	
	public boolean insertarFacturaVenta(FacturaVenta facturaVenta) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO facturaVenta"
				+ "(cliente) VALUES"
				+ "(?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			if (facturaVenta.getCliente() != null) {
				preparedStatement.setString(1, facturaVenta.getCliente().getCI());
			} else {
				preparedStatement.setNull(1, Types.CHAR);
			}
 
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into FACTURA_VENTA table!");
			return true;
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
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
	
	public FacturaVenta recuperarFacturaVenta(){
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQL = "SELECT * FROM facturaVenta";
		FacturaVenta facturaVenta = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet resp = statement.executeQuery(selectSQL);
			
			while (resp.next()) {
				
				facturaVenta = new FacturaVenta();
				String ci = resp.getString(5);
				if (ci != null) {
					Cliente cliente = this.clienteDao.recuperarCliente();
					facturaVenta.setCliente(cliente);
				}

			}
			System.out.println(facturaVenta.toString());
			return facturaVenta;
			
 
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
		return facturaVenta;
	}

}
