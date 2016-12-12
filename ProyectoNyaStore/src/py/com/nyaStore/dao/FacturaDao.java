package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import py.com.nyaStore.model.Factura;
import py.com.nyaStore.model.MedioPago;
import py.com.nyaStore.model.Sucursal;



public class FacturaDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	private MedioPagoDao medioPagoDao = new MedioPagoDao();
	private SucursalDao sucursalDao = new SucursalDao();
	
	public boolean insertarFactura(Factura factura) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO factura"
				+ "(fechaEmision, descripcion_medioPago, codigo_sucursal, importe, cantidad) VALUES"
				+ "(?,?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			preparedStatement.setDate(1, (java.sql.Date) factura.getFechaEmision());
			if (factura.getMedioPago() != null) {
				preparedStatement.setString(2, factura.getMedioPago().getDescripcion());
			} else {
				preparedStatement.setNull(2, Types.CHAR);
			}
			if (factura.getSucursal() != null) {
				preparedStatement.setString(3, factura.getSucursal().getCodigo());
			} else {
				preparedStatement.setNull(3, Types.CHAR);
			}
			preparedStatement.setInt(4, factura.getImporte());
			preparedStatement.setInt(5, factura.getCantidad());
 
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into FACTURA table!");
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
	
	public Factura recuperarFactura(){
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQL = "SELECT * FROM factura";
		Factura factura = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet resp = statement.executeQuery(selectSQL);
			
			while (resp.next()) {
				
				factura = new Factura();
				factura.setFechaEmision(resp.getDate(1));
				String descripcion = resp.getString(2);
				if (descripcion != null) {
					MedioPago medioPago = this.medioPagoDao.recuperarMedioPago();
					factura.setMedioPago(medioPago);
				}
				String codigo = resp.getString(3);
//				if (codigo != null) {
//					Sucursal sucursal = this.sucursalDao.recuperarSucursal();
//					factura.setSucursal(sucursal);
//				}
				factura.setImporte(resp.getInt(2));
				factura.setCantidad(resp.getInt(3));

			}
			System.out.println(factura.toString());
			return factura;
			
 
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
		return factura;
	}

}
