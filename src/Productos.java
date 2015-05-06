import java.io.Serializable;


public class Productos implements Serializable {
	private  int codigo;/*Código del producto */
	private String descripcion;/*Descripcion del producto */
	private Double precioUnitario; /*Precio de una unidad del producto sin aplicarle el iva*/
	private int IVA;/*iva que se le aplicara al producto*/
	private int cantidadStock;/*Cantidad del producto en el almacen*/
	private double precioUnitarioConIva; /*Precio de un producto despues de aplicarle el iva*/
	private boolean activo;
	Productos(int codigo , String descripcion, double precioUnitario, int IVA, int cantidadStock)
	{
		/*Constructor por defecto en el que metes todos los parametros el producto*/
		this.codigo=codigo;
		this.descripcion=descripcion;
		this.precioUnitario=precioUnitario;
		this.IVA=IVA;
		this.cantidadStock=cantidadStock;
		activo=true;
		precioUnitarioConIva=(precioUnitario*IVA/100)+precioUnitario;
	}
	
	/*GETTER*/
	public int getCodigo()
	{
		/*Devuelve el código del producto*/
		return codigo;
	}
	public String getDescripcion()
	{
		/*Debuelve la descripcion del producto*/
		return descripcion;
	}
	public double getPrecioUnitario()
	{
		/*Devuelve el precio unitario del producto*/
		return precioUnitario;
	}
	public double getPrecioConIva()
	{
		/*Devuelve el precio unitario con iva*/
	
		return precioUnitarioConIva;
	}
	public int getIva()
	{
		/*Devuelve el iva del producto*/
		return IVA;
	}
	public int getcantidadStock()
	{
		/*Devuelve la cantidad de stock de producto en el almacen*/
		return cantidadStock;
	}
	public boolean getEstado()
	{
		return activo;
	}
	/*SETTER*/
	public void setDescripcion(String cambio)
	{
		/*metodo para cambiar la descripcion del producto*/
			descripcion=cambio;		
	}
	public void setPrecioUnitario(double precio)
	{
		/*Cambia el preico nitario del producto */
		precioUnitario=precio;		
	}
	public void setIva(int nuevoIva)
	{
		/*Cambia el iva aplicable al producto */
		IVA=nuevoIva;
		precioUnitarioConIva=(precioUnitario*IVA/100)+precioUnitario;
	}
	public void setAumentoStock(int aumento)
	{
		/*Cambio del stock del producto en el almacen(Cuando se aumenta)*/
		cantidadStock=cantidadStock+aumento;
	}
	public boolean setDisminucionStock( int disminucion)
	{
		/*Disminicion del stock por una venta, si devuelve false el stock es negativo por lo que no se puede realizar*/
		int Stock=cantidadStock;
		Stock=Stock-disminucion;
		if(Stock>=0)
		{
			cantidadStock=Stock;
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setStock(int stock)
	{
		cantidadStock=stock;
	}
	public void estado( boolean estado)
	{
		activo=estado;
		
	}

	
}
