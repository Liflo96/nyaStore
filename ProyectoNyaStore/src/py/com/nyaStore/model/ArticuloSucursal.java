package py.com.nyaStore.model;

public class ArticuloSucursal {
	private Articulo articulo;
	private Sucursal sucursal;
	private int cantidad;
	
	//constructor vacio
	public ArticuloSucursal() {

	}
	
	//getters y setters
	public Sucursal getSucursal(){
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal){
		this.sucursal = sucursal;
	}
	
	public Articulo getArticulo(){
		return articulo;
	}
	public void setArticulo(Articulo articulo){
		this.articulo = articulo;
	}
	
	public Integer getCantidad(){
		return cantidad;
	}
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
	}
}
