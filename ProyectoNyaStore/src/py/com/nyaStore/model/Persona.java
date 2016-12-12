package py.com.nyaStore.model;

public class Persona {
	private int personaId;
	private String direccion;
	private String telefono;
	private String email;
	
	//constructor
	public Persona(){
			
	}
		
	//métodos
	public void agregarPersona(){
			
	}
	public void actualizarDatos(){
		
	}
		
	//getters y setters
	public int getPersonaId() {
		return personaId;
	}

	public void setPersonaId(int personaId) {
		this.personaId = personaId;
	}
	
	public String getDireccion(){
		return direccion;
	}
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	
	public String getTelefono(){
		return telefono;
	}
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	@Override
	public String toString() {
		return "Persona [personaId=" + personaId + ", direccion=" + direccion + ", telefono=" + telefono + ", email="
				+ email + "]";
	}
	
	
}
