package py.com.nyaStore.model;

public class Serie {
	private String nombre;
	
	//constructor
	public Serie(){
			
	}
		
	//getters y setters
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Serie [nombre=" + nombre + "]";
	}
	
	
}
