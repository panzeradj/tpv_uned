

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

//Clase para crear los objetos facturas
public class Factura implements Serializable {
	ArrayList ticket;
	Clientes cliente;
	int id;//numero de la factura
	String cif_vendedor="16813058N";//cif del vendedor
	String razon_social="SA";
	int ano;
	int mes;
	int dia;
	double total;//total de la factura
	
	Factura(ArrayList ticket, Clientes cliente, int id, double total){
		//Constructor por defecto
		this.ticket=ticket;
		this.cliente=cliente;
		this.id=id;
		Calendar fecha=new GregorianCalendar();
		ano=fecha.get(Calendar.YEAR);
		mes=fecha.get(Calendar.MONTH)+1;
		dia=fecha.get(Calendar.DAY_OF_MONTH);
		this.total=total;
	}

}
