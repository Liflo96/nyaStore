package py.com.nyaStore.model;

import java.util.Date;

public class PersonaFisica extends Persona{
	private String nombre;
	private String apellido;
	private String CI;
	private Date fechaNacimiento;
	private String sexo;

	//constructor
	public PersonaFisica(){
			
	}
		
	//getters y setters
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}	
	
	public String getApellido(){
		return apellido;
	}
	public void setApellido(String apellido){
		this.apellido = apellido;
	}
	
	public String getCI(){
		return CI;
	}
	public void setCI(String CI){
		this.CI = CI;
	}
	
	public Date getFechaNacimiento(){
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento){
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getSexo(){
		return sexo;
	}
	public void setSexo(String sexo){
		this.sexo = sexo;
	}
}

