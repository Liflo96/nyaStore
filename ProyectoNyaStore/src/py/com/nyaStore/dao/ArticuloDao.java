package py.com.nyaStore.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import py.com.nyaStore.model.Articulo;
import py.com.nyaStore.model.Proveedor;
import py.com.nyaStore.model.Serie;
//import py.com.nyaStore.model.Sucursal;
import py.com.nyaStore.model.TipoArticulo;

public class ArticuloDao {
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	private SerieDao serieDao = new SerieDao();
	private TipoArticuloDao tipoArticuloDao = new TipoArticuloDao();
	private ProveedorDao proveedorDao = new ProveedorDao();
	//private SucursalDao ubicacionFisicaDao =new SucursalDao();
	
	public List<Articulo> recuperarArticulos() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM articulo";
		List<Articulo> articulos = new ArrayList<Articulo>();
		
		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				Articulo articulo = new Articulo();
				
				articulo.setArticuloId(rs.getString(1));
				
				String nombreSerie = rs.getString(2);
				if (nombreSerie != null) {
					Serie serie = this.serieDao.recuperarSerie();
					articulo.setSerie(serie);
				}
				articulo.setDescripcion(rs.getString(3));
				
				String tipoArticuloId = rs.getString(4);
				if (tipoArticuloId != null) {
					TipoArticulo tipoArticulo = this.tipoArticuloDao.recuperarTipoArticulo();
					articulo.setTipoArticulo(tipoArticulo);
				}
				
				String ruc = rs.getString(5);
				if (ruc != null) {
					Proveedor proveedor = this.proveedorDao.recuperarProveedor();
					articulo.setProveedor(proveedor);
				}
				articulo.setPrecioCompra(rs.getInt(6));
				articulo.setPrecioVenta(rs.getInt(7));
				
//				String ubicacionFisica = rs.getString(5);
//				if (ubicacionFisica != null) {
//					Sucursal sucursal = this.ubicacionFi();
//					articulo.setUbicacionFisica(sucursal);
//				}
				
				articulos.add(articulo);
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
		
		return articulos;

	}
	
	
	public boolean insertarArticulo(Articulo articulo) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO articulo"
				+ "(articuloId, nombre_serie, descripcion, tipoArticuloId_tipoArticulo, "
				+ "precioCompra, precioVenta, codigo_sucursal, razonSocial_proveedor) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			preparedStatement.setString(1, articulo.getArticuloId());
			
			
			if (articulo.getSerie() != null) {
				preparedStatement.setString(2, articulo.getSerie().getNombre());
			} else {
				preparedStatement.setNull(2, Types.CHAR);
			}
			preparedStatement.setString(3, articulo.getDescripcion());
			
			
			if (articulo.getTipoArticulo() != null) {
				preparedStatement.setString(4, articulo.getTipoArticulo().getTipoArticuloId());
			} else {
				preparedStatement.setNull(4, Types.CHAR);
			}
			
			preparedStatement.setInt(5, articulo.getPrecioCompra());
			preparedStatement.setInt(6, articulo.getPrecioVenta());
			
			
			if (articulo.getUbicacionFisica() != null) {
				preparedStatement.setString(7, articulo.getUbicacionFisica().getCodigo());
			} else {
				preparedStatement.setNull(7, Types.CHAR);
			}
			
			if (articulo.getProveedor() != null) {
				preparedStatement.setString(8, articulo.getProveedor().getRazonSocial());
			} else {
				preparedStatement.setNull(8, Types.CHAR);
			}
 
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into ARTICULO table!");
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public boolean eliminarArticulo(Articulo articulo){
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE from articulo WHERE articuloId = ?";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, articulo.getArticuloId());

			preparedStatement.executeUpdate();

			System.out.println("Record is deleted!");
			return true;
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}

		}
		return false;

	}
	
	public boolean actualizarArticulo(Articulo articulo){
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String updateSql = "UPDATE articulo set precioVenta = ? WHERE articuloId = ?";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(updateSql);
			
			preparedStatement.setInt(1, articulo.getPrecioVenta());
			preparedStatement.setString(2, articulo.getArticuloId());
			
			preparedStatement.executeUpdate();

			System.out.println("Record is updated!");
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}

		}
		return false;

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
	
	public void validarPK(Articulo articulo) throws SQLException, ClaveDuplicadaException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String selectSQL = "select * from articulo where articuloId = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
 
			
			preparedStatement.setString(1, articulo.getArticuloId());

			ResultSet rs = 	preparedStatement.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "No puede insertar mas de un registro con la misma clave", null, JOptionPane.ERROR_MESSAGE, null);
				throw new ClaveDuplicadaException("No puede insertar mas de un registro con la misma clave");
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
			
 
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
		}	
	}
	
	

}
