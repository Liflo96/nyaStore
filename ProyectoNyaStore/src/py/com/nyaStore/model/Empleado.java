package py.com.nyaStore.model;

public class Empleado extends PersonaFisica{
	private boolean gerente;
	private Sucursal sucursal;
	
	//constructor
	public Empleado(){
			
	}
		
	//getters y setters
	public boolean getGerente(){
		return gerente;
	}
	public void setGerente(boolean gerente){
		this.gerente = gerente;
	}
	
	public Sucursal getSucursal(){
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal){
		this.sucursal = sucursal;
	}
}
