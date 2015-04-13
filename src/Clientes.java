import java.io.Serializable;


public class Clientes implements Serializable  {
	private String nombre;
	private String apellidos;
	private int id;
	private String nif;
	private String razonSocial;
	private String domicilio;
	private String fechaAlta;
	public  boolean estado;
	Clientes( int id, String nif, String nombre, String apellidos, String razonSocial , String domicilio, String fechaAlta)
	{
		 this.id=id;
		 this.nif=nif;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.razonSocial=razonSocial;
		this.domicilio=domicilio;
		this.fechaAlta="";
		estado=true;
	}
	//getter
	public String getNombre()
	{
		return nombre;
	}
	public String getApellido()
	{
		return apellidos;
	}
	public String getNif()
	{
		return nif;
	}
	public String getRazonSocial()
	{
		return razonSocial;
	}
	public String getDomicilio()
	{
		return domicilio;
	}
	public boolean getEstado()
	{
		return estado;
	}
	public int getId()
	{
		return id;
	}
	//setter
	public void setNombre( String variable)
	{
		 nombre=variable;
	}
	public void setApellido( String variable)
	{
		 apellidos=variable;
	}
	public void setNif( String variable)
	{
		 nif=variable;
	}
	public void setRazonSocial( String variable)
	{
		 razonSocial=variable;
	}
	public void setDomicilio( String variable)
	{
		 domicilio=variable;
	}
	public void setEstado(boolean variable)
	{
		 estado=variable;
	}
	
}
