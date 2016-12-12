package py.com.nyaStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import py.com.nyaStore.model.Empleado;
import py.com.nyaStore.model.PersonaFisica;
import py.com.nyaStore.model.Sucursal;

public class EmpleadoDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/NyaStore";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "lalala961";
	//private SucursalDao sucursalDao = new SucursalDao();
	//private PersonaFisicaDao personaFisicaDao = new PersonaFisicaDao();
	
	public boolean insertarEmpleado(Empleado empleado) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO empleado"
				+ "(gerente, codigo_sucursal) VALUES"
				+ "(?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			
			preparedStatement.setBoolean(1, empleado.getGerente());
			if (empleado.getSucursal() != null) {
				preparedStatement.setString(2, empleado.getSucursal().getCodigo());
			} else {
				preparedStatement.setNull(2, Types.CHAR);
			}
			
			preparedStatement.executeUpdate();
		
			System.out.println("Record is inserted into EMPLEADO table!");
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
	
//	public Empleado recuperarEmpleado(){
//		Connection dbConnection = null;
//		Statement statement = null;
//		
//		String selectSQL = "SELECT * FROM empleado";
//		
//		Empleado empleado = null;
//		
//		try {
//			dbConnection = getDBConnection();
//			statement = dbConnection.createStatement();
//			ResultSet resp = statement.executeQuery(selectSQL);
//			
//			while (resp.next()) {
//				
//				empleado = new Empleado();
//				empleado.setGerente(resp.getBoolean(1));
//				String codigo = resp.getString(2);
//				if (codigo != null) {
//					Sucursal sucursal = this.sucursalDao.recuperarSucursal();
//					empleado.setSucursal(sucursal);
//				}
//				
//				String nombreEmpleado = resp.getString(3);
//				if (nombreEmpleado != null) {
//					PersonaFisica personaFisica = this.personaFisicaDao.recuperarPersonaFisica();
//					empleado.setNombre(personaFisica.getNombre());
//					empleado.setCI(personaFisica.getCI());
//				}
//				
//
//			}
//			System.out.println(empleado.toString());
//			return empleado;
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
//		return empleado;
//	}

}
