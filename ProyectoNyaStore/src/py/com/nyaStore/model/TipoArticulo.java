package py.com.nyaStore.model;

public class TipoArticulo {
	private String tipoArticuloId;
	private String descripcion;
	
	//constructor
	public TipoArticulo(){
			
	}
		
	//getters y setters
	public String getTipoArticuloId() {
		return tipoArticuloId;
	}

	public void setTipoArticuloId(String tipoArticuloId) {
		this.tipoArticuloId = tipoArticuloId;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoArticulo [tipoArticuloId=" + tipoArticuloId + ", descripcion=" + descripcion + "]";
	}
}
