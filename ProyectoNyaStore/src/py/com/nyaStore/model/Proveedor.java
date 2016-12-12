package py.com.nyaStore.model;

public class Proveedor extends Persona{
	private String razonSocial;
	private String RUC;
		
	//getters y setters
	public String getRazonSocial(){
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial){
		this.razonSocial = razonSocial;
	}
	
	public String getRUC(){
		return RUC;
	}
	public void setRUC(String RUC){
		this.RUC = RUC;
	}
	
	//constructor
	public Proveedor(){
			
	}
	@Override
	public String toString() {
		return "Proveedor [razonSocial=" + razonSocial + ", RUC=" + RUC + "]";
	}
		

}
