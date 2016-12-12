package py.com.nyaStore.model;

public class MedioPago {
	private String descripcion;
	private boolean IVA;
	
	//constructor
	public MedioPago(){
			
	}
	
	//getters y setters
	public String getDescripcion(){
		return descripcion;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	
	public boolean getIVA(){
		return IVA;
	}
	public void setIVA(boolean IVA){
		this.IVA = IVA;
	}
}
