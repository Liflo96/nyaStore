package py.com.nyaStore.model;

public class Articulo {
	private String articuloId;
	private Serie serie;
	private String descripcion;
	private TipoArticulo tipoArticulo;
	private Proveedor proveedor;
	private Integer precioCompra;
	private Integer precioVenta;
	private Sucursal ubicacionFisica;
	
	//constructor
	public Articulo(){
		
	}
	
	
	//getters y setters
	public String getArticuloId() {
		return articuloId;
	}
	public void setArticuloId(String articuloId) {
		this.articuloId = articuloId;
	}
	
	public Serie getSerie(){
		return serie;
	}
	public void setSerie(Serie serie){
		this.serie = serie;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	
	public TipoArticulo getTipoArticulo(){
		return tipoArticulo;
	}
	public void setTipoArticulo(TipoArticulo tipoArticulo){
		this.tipoArticulo = tipoArticulo;
	}
	
	public Proveedor getProveedor(){
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor){
		this.proveedor = proveedor;
	}
	
	public Integer getPrecioCompra(){
		return precioCompra;
	}
	public void setPrecioCompra(Integer precioCompra){
		this.precioCompra = precioCompra;
	}
	
	public Integer getPrecioVenta(){
		return precioVenta;
	}
	public void setPrecioVenta(Integer precioVenta){
		this.precioVenta = precioVenta;
	}
	
	public Sucursal getUbicacionFisica(){
		return ubicacionFisica;
	}
	public void setUbicacionFisica(Sucursal ubicacionFisica){
		this.ubicacionFisica = ubicacionFisica;
	}
	
	@Override
	public String toString() {
		return "Articulo [descripcion=" + descripcion + ", precioCompra=" + precioCompra
				+ ", precioVenta=" + precioVenta + "]";
	}
	
}
