package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import py.com.nyaStore.model.FacturaCompra;
import py.com.nyaStore.model.Proveedor;




public class FacturaCompraDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	private ProveedorDao proveedorDao = new ProveedorDao();
	
	public boolean insertarFacturaVenta(FacturaCompra facturaCompra) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO facturaCompra"
				+ "(proveedor) VALUES"
				+ "(?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			if (facturaCompra.getProveedor() != null) {
				preparedStatement.setString(1, facturaCompra.getProveedor().getRUC());
			} else {
				preparedStatement.setNull(1, Types.CHAR);
			}
 
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into FACTURA_COMPRA table!");
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
	
	public FacturaCompra recuperarFacturaCompra(){
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQL = "SELECT * FROM facturaCompra";
		FacturaCompra facturaCompra = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet resp = statement.executeQuery(selectSQL);
			
			while (resp.next()) {
				
				facturaCompra = new FacturaCompra();
				String ruc = resp.getString(1);
				if (ruc != null) {
					Proveedor proveedor = this.proveedorDao.recuperarProveedor();
					facturaCompra.setProveedor(proveedor);
				}

			}
			System.out.println(facturaCompra.toString());
			return facturaCompra;
			
 
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
		return facturaCompra;
	}

}
