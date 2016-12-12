package py.com.nyaStore.dao;

import java.sql.SQLException;

import py.com.nyaStore.model.Articulo;
import py.com.nyaStore.model.Empleado;
import py.com.nyaStore.model.Proveedor;
import py.com.nyaStore.model.Serie;
import py.com.nyaStore.model.Sucursal;
import py.com.nyaStore.model.TipoArticulo;
import py.com.nyaStore.ui.*;
public class Main {

	public static void main(String[] args) throws SQLException, ClaveDuplicadaException {
		ArticuloDao articuloDao = new ArticuloDao();
		Articulo articulo = new Articulo();
		
		/*
		 * Test del metodo insertar
		 * Test del metodo actualizar
		 * Test del metodo eliminar
		 *
		 */
		
		
//		articulo.setArticuloId("lala3");
//		
//		Serie serie = new Serie();
//		serie.setNombre("Pokemon");
//		articulo.setSerie(serie);
//		articulo.setDescripcion("algo");
//		
//		TipoArticulo tipoArticulo = new TipoArticulo();
//		tipoArticulo.setTipoArticuloId("01rem");
//		tipoArticulo.setDescripcion("remera");
//		articulo.setTipoArticulo(tipoArticulo);
//		
//		Proveedor proveedor = new Proveedor();
//		proveedor.setRazonSocial("Asia Team");
//		proveedor.setRUC("CS753951");
//		articulo.setProveedor(proveedor);
//		articulo.setPrecioCompra(10000);
//		articulo.setPrecioVenta(456496);
//		
//		Sucursal ubicacionFisica = new Sucursal();
//		ubicacionFisica.setCodigo("sl");
//		ubicacionFisica.setDireccion("San Lorenzo");
//		ubicacionFisica.setEmail("nyaStore.sanLorenzo@hotmail.com");
//		ubicacionFisica.setTelefono("02145645");
//		Empleado empleado =new Empleado();
//		empleado.setGerente(true);
//		ubicacionFisica.setGerente(empleado);
//		articulo.setUbicacionFisica(ubicacionFisica);
//		
//		
//		
//		try{
//		articuloDao.validarPK(articulo);
//		articuloDao.insertarArticulo(articulo);
//		//articuloDao.eliminarArticulo(articulo);
//		
//		}catch(ClaveDuplicadaException e){
//			System.out.println(e.getMessage());
//		}
		
//		articulo.setPrecioVenta(80000);
//		articulo.setArticuloId("inu23");
//		articuloDao.actualizarArticulo(articulo);
		
//		articulo.setArticuloId("lala3");
//		articuloDao.eliminarArticulo(articulo);
		
		
		/*
		 * Pruebas de los metodos que recuperan datos de la base de datos
		 */

//////		SucursalDao sucursal = new SucursalDao();
//////		sucursal.recuperarSucursal();
//////		ProveedorDao proveedor = new ProveedorDao();
//////		proveedor.recuperarProveedor().toString();
//////		proveedor.recuperarProveedores();
//////		PersonaDao persona = new PersonaDao();
//////		persona.recuperarPersona().toString();	
		
		/*
		 * Prueba comboBox tipoArticulo
		 */

//		TipoArticuloDao tipoArticuloDao = new TipoArticuloDao();
//		TipoArticulo tipoArticulo = new TipoArticulo();
//		tipoArticulo.setDescripcion("remera");
//		System.out.println(tipoArticuloDao.recuperarCodigo(tipoArticulo).toString());
	}

}
