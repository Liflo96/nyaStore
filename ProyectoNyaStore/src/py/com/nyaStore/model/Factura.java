package py.com.nyaStore.model;

import java.util.Date;
//import java.util.List;

public class Factura {
	private Date fechaEmision;
	private MedioPago medioPago;
	private Sucursal sucursal;
	private Integer importe;
//	private List<Articulo> articulo;
	private Integer cantidad;
	
	//constructor
	public Factura(){
		
	}
	
	//métodos
	public void agregarFactura(){
		
	}
	
	//getters y setters
	public Date getFechaEmision(){
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision){
		this.fechaEmision = fechaEmision;
	}
	
	public MedioPago getMedioPago(){
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago){
		this.medioPago = medioPago;
	}
	
	public Sucursal getSucursal(){
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal){
		this.sucursal = sucursal;
	}
	
	public Integer getImporte(){
		return importe;
	}
	public void setImporte(Integer importe){
		this.importe = importe;
	}
	
//	public List<Articulo> getArticulo(){
//		return articulo;
//	}
//	public void setArticulo(List<Articulo> articulo){
//		this.articulo = articulo;
//	}
	
	public Integer getCantidad(){
		return cantidad;
	}
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
	}
	
}
