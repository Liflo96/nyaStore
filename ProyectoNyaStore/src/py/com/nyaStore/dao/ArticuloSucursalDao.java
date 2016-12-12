package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import py.com.nyaStore.model.Articulo;
import py.com.nyaStore.model.ArticuloSucursal;
import py.com.nyaStore.model.Sucursal;


public class ArticuloSucursalDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	private ArticuloDao articuloDao = new ArticuloDao();
	private SucursalDao sucursalDao = new SucursalDao();
	
	public boolean insertarArticuloSucursal(ArticuloSucursal articuloSucursal) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO articuloSucursal"
				+ "(articuloId_articulo, codigo_sucursal, cantidad) VALUES"
				+ "(?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			if (articuloSucursal.getArticulo() != null) {
				preparedStatement.setString(1, articuloSucursal.getArticulo().getArticuloId());
			} else {
				preparedStatement.setNull(1, Types.CHAR);
			}
			if (articuloSucursal.getSucursal() != null) {
				preparedStatement.setString(2, articuloSucursal.getSucursal().getCodigo());
			} else {
				preparedStatement.setNull(2, Types.CHAR);
			}
			preparedStatement.setInt(3, articuloSucursal.getCantidad());
 
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into ARTICULO_SUCURSAL table!");
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
	
	public List<ArticuloSucursal> recuperarArticuloSucursal() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM articuloSucursal";
		List<ArticuloSucursal> articulosSucursal = new ArrayList<ArticuloSucursal>();
		
		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				ArticuloSucursal articuloSucursal = new ArticuloSucursal();
				
				String articuloId = rs.getString(1);
				if (articuloId != null) {
					Articulo articulo = (Articulo) this.articuloDao.recuperarArticulos();
					articuloSucursal.setArticulo(articulo);
				}
				String codigo = rs.getString(2);
//				if (codigo != null) {
//					Sucursal sucursal = this.sucursalDao.recuperarSucursal();
//					articuloSucursal.setSucursal(sucursal);
//				}
				articuloSucursal.setCantidad(rs.getInt(3));
				
				articulosSucursal.add(articuloSucursal);
			}			
		}

		catch (Exception e) {
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
		
		return articulosSucursal;

	}
	
}

