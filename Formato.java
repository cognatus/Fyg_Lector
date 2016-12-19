public class Formato {

	private String nombre;
	private String rfc;
	private String numEmpleado;
	private String fecha;

	public Formato(){
		this.nombre = "";
		this.rfc = "";
		this.numEmpleado = "";
		this.fecha = "";
	}

	public Formato(String nombre, String rfc, String numEmpleado, String fecha){
		this.nombre = nombre;
		this.rfc = rfc;
		this.numEmpleado = numEmpleado;
		this.fecha = fecha;
	}

	public void limpia(){
		this.nombre = "";
		this.rfc = "";
		this.numEmpleado = "";
		this.fecha = "";
	}

	public void setNombre (String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre (){
		return this.nombre;
	}

	public void setRfc (String rfc){
		this.rfc = rfc;
	}
	
	public String getRfc (){
		return this.rfc;
	}

	public void setFecha (String fecha){
		this.fecha = fecha;
	}
	
	public String getFecha (){
		return this.fecha;
	}

	public void setNumEmpleado (String numEmpleado){
		this.numEmpleado = numEmpleado;
	}
	
	public String getNumEmpleado (){
		return this.numEmpleado;
	}
}