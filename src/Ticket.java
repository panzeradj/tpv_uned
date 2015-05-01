import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Ticket implements Serializable {
	private int codigo;
	ArrayList productos;
	ArrayList cantidades;
	Clientes cliente;
	int ano;//año de emision
	int mes;//mes de emision
	int dia;//dia de emision
	public Ticket(int codigo, Clientes cliente)
	{
		this.codigo=codigo;
		this.cliente=cliente;
		Calendar fecha=new GregorianCalendar();
		ano=fecha.get(Calendar.YEAR);
		mes=fecha.get(Calendar.MONTH)+1;
		dia=fecha.get(Calendar.DAY_OF_MONTH);
	}
	
}
