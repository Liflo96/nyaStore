package py.com.nyaStore.model;

public class FacturaCompra extends Factura {
	private Proveedor proveedor;
	
	//constructor
	public FacturaCompra(){
			
	}
	
	//getters y setters
	public Proveedor getProveedor(){
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor){
		this.proveedor = proveedor;
	}
	
}
