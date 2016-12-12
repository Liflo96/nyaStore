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


import py.com.nyaStore.model.Empleado;

import py.com.nyaStore.model.Sucursal;




public class SucursalDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	private EmpleadoDao empleadoDao = new EmpleadoDao();
	
	
	public Sucursal recuperarSucursal(){
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectSQL = "SELECT * FROM sucursal";
		Sucursal sucursal = null;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet resp = 	statement.executeQuery(selectSQL);
			
			while (resp.next()) {
				
				sucursal = new Sucursal();
				sucursal.setCodigo(resp.getString(1));
				sucursal.setDireccion(resp.getString(2));
				sucursal.setTelefono(resp.getString(3));
				sucursal.setEmail(resp.getString(4));
//				String gerente = resp.getString(2);
//				if (gerente != null) {
//					Empleado empleado = this.empleadoDao.recuperarEmpleado();
//					sucursal.setGerente(empleado);
//				}

			}
			System.out.println(sucursal.toString());
			return sucursal;
			
 
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
		return sucursal;
	}
	
	public boolean insertarSucursal(Sucursal sucursal) throws SQLException {
		 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO sucursal"
				+ "(codigo, direccion, telefono, email, CI_empleado) VALUES"
				+ "(?,?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			preparedStatement.setString(1, sucursal.getCodigo());
			preparedStatement.setString(2, sucursal.getDireccion());
			preparedStatement.setString(3, sucursal.getTelefono());
			preparedStatement.setString(4, sucursal.getEmail());
			if (sucursal.getGerente() != null) {
				preparedStatement.setString(5, sucursal.getGerente().getCI());
			} else {
				preparedStatement.setNull(5, Types.CHAR);
			}
 
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into SUCURSAL table!");
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
	
	public List<Sucursal> recuperarSucursales() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM sucursal";
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		
		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				Sucursal sucursal = new Sucursal();
				
				sucursal.setCodigo(rs.getString(1));
				sucursal.setDireccion(rs.getString(2));
				sucursal.setTelefono(rs.getString(3));
				sucursal.setEmail(rs.getString(4));
//				String gerente = rs.getString(5);
//				if (gerente != null) {
//					Empleado empleado = this.empleadoDao.recuperarEmpleado();
//					sucursal.setGerente(empleado);
//				}
				
				sucursales.add(sucursal);
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
		
		return sucursales;

	}

}
