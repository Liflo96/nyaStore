package py.com.nyaStore.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.omg.CORBA.ValueMember;

import py.com.nyaStore.dao.ArticuloDao;
import py.com.nyaStore.dao.ClaveDuplicadaException;
import py.com.nyaStore.dao.ProveedorDao;

import py.com.nyaStore.dao.SerieDao;
import py.com.nyaStore.dao.SucursalDao;
import py.com.nyaStore.dao.TipoArticuloDao;
import py.com.nyaStore.model.Articulo;
import py.com.nyaStore.model.Proveedor;

import py.com.nyaStore.model.Serie;
import py.com.nyaStore.model.Sucursal;
import py.com.nyaStore.model.TipoArticulo;

import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArticuloView {
	private JFrame frmArticuloView;
	private JTextField txtDescripcion;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	private JTextField txtArticuloId;
	private JComboBox<Serie> cboSerie = new JComboBox<>();
	private JComboBox<TipoArticulo> cboTipoArticulo = new JComboBox<>();
	private JComboBox<Proveedor> cboProveedor = new JComboBox<>();
	private JComboBox<Sucursal> cboUbicacionFisica = new JComboBox<>();
	private SerieDao serieDao = new SerieDao();
	private TipoArticuloDao tipoArticuloDao = new TipoArticuloDao();
	private ProveedorDao proveedorDao= new ProveedorDao();
	private SucursalDao ubicacionFisicaDao = new SucursalDao();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArticuloView window = new ArticuloView();
					window.frmArticuloView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArticuloView() {
		initialize();
	}
	
		
	private void initialize() {
		frmArticuloView = new JFrame();
		frmArticuloView.setBackground(new Color(255, 255, 255));
		frmArticuloView.getContentPane().setBackground(new Color(250, 235, 215));
		frmArticuloView.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sasha\\Desktop\\^w^\\UAA\\3\u00B0 semestre\\Base de datos\\Proyecto Nya Store (Entrega final)\\Fotos\\vinilo-gatito-bola-de-lana.jpg"));
		frmArticuloView.setTitle("ARTICULO");
		frmArticuloView.setBounds(100, 100, 381, 455);
		frmArticuloView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		List<Serie> series = serieDao.recuperarSeries();
		
		ArrayList<String> wikiSerie = new  ArrayList<>();
		for (Serie serie : series) {
			wikiSerie.add(serie.getNombre());
		}
		JComboBox cboSerie = new JComboBox(wikiSerie.toArray());
		
		List<TipoArticulo> tipoArticulos = tipoArticuloDao.recuperarTipoArticulos();
		
		ArrayList<String> wikiTipoArticulo = new  ArrayList<>();
		for (TipoArticulo tipoArticulo : tipoArticulos) {
			wikiTipoArticulo.add(tipoArticulo.getDescripcion());
		}
		JComboBox cboTipoArticulo = new JComboBox(wikiTipoArticulo.toArray());
		
		List<Sucursal> sucursales = ubicacionFisicaDao.recuperarSucursales();
		
		ArrayList<String> wikiSucursal = new  ArrayList<>();
		for (Sucursal sucursal : sucursales) {
			wikiSucursal.add(sucursal.getCodigo());
		}
		JComboBox cboUbicacionFisica = new JComboBox(wikiSucursal.toArray());
		
		List<Proveedor> proveedores = proveedorDao.recuperarProveedores();
		
		ArrayList<String> wikiProveedor = new  ArrayList<>();
		for (Proveedor proveedor : proveedores) {
			wikiProveedor.add(proveedor.getRazonSocial());
		}
		JComboBox cboProveedor = new JComboBox(wikiProveedor.toArray());
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(221, 160, 221));
		btnAgregar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					Articulo articulo = new Articulo();
					
					articulo.setArticuloId(txtArticuloId.getText());
					articulo.setDescripcion(txtDescripcion.getText());
					articulo.setPrecioCompra(Integer.valueOf(txtPrecioCompra.getText()));
					articulo.setPrecioVenta(Integer.valueOf(txtPrecioVenta.getText()));		
					
					Serie serie = new Serie();
					serie.setNombre(cboSerie.getSelectedItem().toString());
					articulo.setSerie(serie);
					
					TipoArticulo tipoArticulo = new TipoArticulo();
					tipoArticulo.setDescripcion(cboTipoArticulo.getSelectedItem().toString());
					articulo.setTipoArticulo(tipoArticuloDao.recuperarCodigo(tipoArticulo));
					
					Proveedor proveedor = new Proveedor();
					proveedor.setRazonSocial(cboProveedor.getSelectedItem().toString());
					articulo.setProveedor(proveedor);
					
					Sucursal ubicacionFisica = new Sucursal();					
					ubicacionFisica.setCodigo(cboUbicacionFisica.getSelectedItem().toString());
					articulo.setUbicacionFisica(ubicacionFisica);
			

					ArticuloDao articuloDao = new ArticuloDao();
					articuloDao.validarPK(articulo);
					Boolean isInserted = articuloDao.insertarArticulo(articulo);

					if (isInserted){
						JOptionPane.showMessageDialog(null, "Articulo insertado correctamente", "", JOptionPane.INFORMATION_MESSAGE);

					}else{
						JOptionPane.showMessageDialog(null, "No se pudo insertar el registro del articulo", null, JOptionPane.ERROR_MESSAGE, null);
					}

				} catch (SQLException e) {

					System.out.println(e.getMessage());

				} catch (ClaveDuplicadaException e) {
					System.out.println(e.getMessage());
				}
			} 
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(221, 160, 221));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Articulo articulo = new Articulo();

				articulo.setArticuloId(txtArticuloId.getText());
				
				
//				Serie serie = (Serie) cboSerie.getSelectedItem();
//				articulo.setSerie(serie);
				
				articulo.setDescripcion(txtDescripcion.getText());		
				
//				TipoArticulo tipoArticulo = (TipoArticulo) cboTipoArticulo.getSelectedItem();
//				articulo.setTipoArticulo(tipoArticulo);
				
//				Proveedor proveedor = (Proveedor) cboProveedor.getSelectedItem();
//				articulo.setProveedor(proveedor);

				String precioCompra = txtPrecioCompra.getText();
				articulo.setPrecioCompra(Integer.valueOf(precioCompra));

				String precioVenta = txtPrecioVenta.getText();
				articulo.setPrecioVenta(Integer.valueOf(precioVenta));	
				
//				Sucursal ubicacionFisica = (Sucursal) cboUbicacionFisica.getSelectedItem();
//				articulo.setUbicacionFisica(ubicacionFisica);
				
				
				ArticuloDao articuloDao = new ArticuloDao();
				Boolean isDeleted = articuloDao.eliminarArticulo(articulo);

				if (isDeleted){
					JOptionPane.showMessageDialog(null, "Articulo eliminado correctamente", "", JOptionPane.INFORMATION_MESSAGE);

				}else{
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro del articulo", null, JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBackground(new Color(221, 160, 221));
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Articulo articulo = new Articulo();

				articulo.setArticuloId(txtArticuloId.getText());
				
				
//				Serie serie = (Serie) cboSerie.getSelectedItem();
//				articulo.setSerie(serie);
				
				articulo.setDescripcion(txtDescripcion.getText());		
				
//				TipoArticulo tipoArticulo = (TipoArticulo) cboTipoArticulo.getSelectedItem();
//				articulo.setTipoArticulo(tipoArticulo);
				
//				Proveedor proveedor = (Proveedor) cboProveedor.getSelectedItem();
//				articulo.setProveedor(proveedor);

				String precioCompra = txtPrecioCompra.getText();
				articulo.setPrecioCompra(Integer.valueOf(precioCompra));

				String precioVenta = txtPrecioVenta.getText();
				articulo.setPrecioVenta(Integer.valueOf(precioVenta));	
				
//				Sucursal ubicacionFisica = (Sucursal) cboUbicacionFisica.getSelectedItem();
//				articulo.setUbicacionFisica(ubicacionFisica);
				
				
				ArticuloDao articuloDao = new ArticuloDao();
				Boolean isUpdated = articuloDao.actualizarArticulo(articulo);

				if (isUpdated){
					JOptionPane.showMessageDialog(null, "Articulo actualizado correctamente", "", JOptionPane.INFORMATION_MESSAGE);

				}else{
					JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro del articulo", null, JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		
		JLabel lblDescripcion = new JLabel("Descripcion");

		JLabel lblPrecioCompra = new JLabel("Precio Compra");

		JLabel lblPrecioVenta = new JLabel("Precio Venta");

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);

		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(10);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setColumns(10);

		JLabel lblTipoArticulo = new JLabel("Tipo Articulo");
//		List<TipoArticulo> tipoArticulos = tipoArticuloDao.recuperarTipoArticulos();
//		
//		ArrayList<String> wikiTipoArticulo = new  ArrayList<>();
//		for (TipoArticulo tipoArticulo : tipoArticulos) {
//			wikiTipoArticulo.add(tipoArticulo.getDescripcion());
//		}
//		JComboBox cboTipoArticulo = new JComboBox(wikiTipoArticulo.toArray());
		JLabel lblSerie = new JLabel("Serie");
		
//		
//		List<Serie> series = serieDao.recuperarSeries();
//		
//		ArrayList<String> wikiSerie = new  ArrayList<>();
//		for (Serie serie : series) {
//			wikiSerie.add(serie.getNombre());
//		}
//		JComboBox cboSerie = new JComboBox(wikiSerie.toArray());
//		
		JLabel lblArticuloId = new JLabel("Codigo");

		txtArticuloId = new JTextField();
		txtArticuloId.setColumns(10);
		
//		List<Proveedor> proveedores = proveedorDao.recuperarProveedores();
//		
//		ArrayList<String> wikiProveedor = new  ArrayList<>();
//		for (Proveedor proveedor : proveedores) {
//			wikiProveedor.add(proveedor.getRazonSocial());
//		}
//		JComboBox cboProveedor = new JComboBox(wikiProveedor.toArray());
		
		
		JLabel lblProveedor = new JLabel("Proveedor");
		
		JLabel lblUbicacionFisica = new JLabel("Ubicacion fisica");
		
//		List<Sucursal> sucursales = ubicacionFisicaDao.recuperarSucursales();
//		
//		ArrayList<String> wikiSucursal = new  ArrayList<>();
//		for (Sucursal sucursal : sucursales) {
//			wikiSucursal.add(sucursal.getDireccion());
//		}
//		JComboBox cboUbicacionFisica = new JComboBox(wikiSucursal.toArray());

		GroupLayout groupLayout = new GroupLayout(frmArticuloView.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipoArticulo)
								.addComponent(lblDescripcion)
								.addComponent(lblArticuloId)
								.addComponent(lblSerie)
								.addComponent(lblPrecioCompra)
								.addComponent(lblPrecioVenta)
								.addComponent(lblProveedor)
								.addComponent(lblUbicacionFisica))
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtPrecioVenta)
								.addComponent(txtPrecioCompra)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboProveedor, 0, 198, Short.MAX_VALUE))
								.addComponent(cboTipoArticulo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtDescripcion)
								.addComponent(cboSerie, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtArticuloId, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addComponent(cboUbicacionFisica, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArticuloId)
						.addComponent(txtArticuloId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSerie)
						.addComponent(cboSerie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescripcion)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoArticulo)
						.addComponent(cboTipoArticulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProveedor))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrecioCompra)
						.addComponent(txtPrecioCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrecioVenta)
						.addComponent(txtPrecioVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboUbicacionFisica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUbicacionFisica))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnActualizar, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
					.addContainerGap())
		);
		frmArticuloView.getContentPane().setLayout(groupLayout);
	}
}