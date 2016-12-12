package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.com.nyaStore.model.TipoArticulo;

public class TipoArticuloDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	
	public boolean insertarTipoArticulo(TipoArticulo tipoArticulo) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO tipoArticulo"
				+ "(tipoArticuloId, descripcion) VALUES"
				+ "(?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			preparedStatement.setString(1, tipoArticulo.getTipoArticuloId());
			preparedStatement.setString(2, tipoArticulo.getDescripcion());

			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into TIPO_ARTICULO table!");
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
	
	public TipoArticulo recuperarCodigo(TipoArticulo tipoArticulo) throws SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM tipoArticulo WHERE descripcion=?";
		
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, tipoArticulo.getDescripcion());
			ResultSet rs = 	preparedStatement.executeQuery();
			while (rs.next()) {
				tipoArticulo.setTipoArticuloId(rs.getString(1));
				tipoArticulo.setDescripcion(rs.getString(2));
			}
		
		} catch (SQLException e) {
 
			System.out.println("No se ha podido establecer conexion con la base de datos");
 
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return tipoArticulo;
	}
	public TipoArticulo recuperarTipoArticulo(){
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQL = "SELECT * FROM tipoArticulo";
		TipoArticulo tipoTipoArticulo = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet resp = statement.executeQuery(selectSQL);
			
			while (resp.next()) {
				
				tipoTipoArticulo = new TipoArticulo();
				tipoTipoArticulo.setDescripcion(resp.getString(1));

			}
			System.out.println(tipoTipoArticulo.toString());
			return tipoTipoArticulo;
			
 
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
		return tipoTipoArticulo;
	}
	
	public List<TipoArticulo> recuperarTipoArticulos() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM tipoArticulo";
		List<TipoArticulo> tipoArticulos = new ArrayList<TipoArticulo>();
		
		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				TipoArticulo tipoArticulo = new TipoArticulo();
				
				tipoArticulo.setTipoArticuloId(rs.getString(1));
				tipoArticulo.setDescripcion(rs.getString(2));
				
				tipoArticulos.add(tipoArticulo);
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
		
		return tipoArticulos;

	}

}
