package py.com.nyaStore.model;


public class Sucursal {
	private String codigo;
	private String direccion;
	private String telefono;
	private String email;
	private Empleado gerente;
	
	//constructor
	public Sucursal(){
			
	}
		
	//métodos
	public void actualizarDatos(){
			
	}
	public void emitirFactura(){
		
	}
		
	//getters y setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
	public Empleado getGerente(){
		return gerente;
	}
	public void setGerente(Empleado gerente){
		this.gerente = gerente;
	}
	
	@Override
	public String toString() {
		return "Sucursal [direccion=" + direccion + ", telefono=" + telefono
				+ ", email=" + email + "]";
	}
	
}
