package py.com.nyaStore.model;

public class FacturaVenta extends Factura{
	private Cliente cliente;
	
	//constructor
	public FacturaVenta(){
			
	}
	
	//getters y setters
	public Cliente getCliente(){
		return cliente;
	}
	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}
}
