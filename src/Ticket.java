import java.io.Serializable;
import java.util.ArrayList;


public class Ticket implements Serializable {
	private int codigo;
	ArrayList productos;
	ArrayList cantidades;
	Clientes cliente;
	public Ticket(int codigo, Clientes cliente)
	{
		this.codigo=codigo;
		this.cliente=cliente;
	}
	public void nuevoProducto(cantidad cantidad, Productos producto )
	{
		//Se comprueba que existe el producto ArrayListProductos antes de entra se mira stock de producto...
		
		//Se añade el producto al arrayList
		//se añade la cantidad al arrayList
	}
}
