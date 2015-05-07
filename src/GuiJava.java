import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class GuiJava implements ActionListener{//implementando el listener de eventos

    JPanel PanelProductos, PanelClientes, PanelVentas, PanelFacturas;
    JButton jb1, jb2, jb3, jbP1, jbP2, jbP3;   
    JMenu productosMenu;
    JFrame jfM;
    ArrayList  productosArray, clientesArray, ticketArray,facturaArray;
    JTextArea areaProducto, areaCliente,areaTicket, areaFactura , area_Productos;
    String rutaArchivo="";
    String cadenaProductosTicket;
    boolean banderaMostrar;//para sacar facturas
    double totalFactura=0;
    int id_cliente=-1;
    public GuiJava(){
    	File fichero = new File("");
    	rutaArchivo=fichero.getAbsolutePath();
    	
    	productosArray= new ArrayList();
    	clientesArray=new ArrayList();
    	ticketArray=new ArrayList();
    	facturaArray=new ArrayList();
        jfM = new JFrame("JPanel En Java");  
        jfM.setLayout(null);
 
        productos(); clientes(); ventas(); facturas();//invocamos los metodos que contienen los paneles 
        PanelProductos.setVisible(false);
        PanelProductos.setLayout(null);
        PanelClientes.setVisible(false);
        PanelClientes.setLayout(null);
        PanelVentas.setLayout(null);
        PanelVentas.setVisible(false);
        PanelFacturas.setVisible(false);
        PanelFacturas.setLayout(null);
  
        PanelProductos.setBounds(0, 0, 1200, 1200); PanelClientes.setBounds(0, 0, 1200, 1200); PanelVentas.setBounds(0, 0, 1200, 1200);  PanelFacturas.setBounds(0, 0, 1200, 1200); 
        
        jfM.add(PanelProductos); jfM.add(PanelClientes); jfM.add(PanelVentas); jfM.add(PanelFacturas);
        jfM.setLocation(100, 50);
        jfM.setResizable(false);
        jfM.setVisible(true);
        jfM.setSize(800, 600);
        jfM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*MENU*/
        jfM.pack();
        jfM.setTitle("TPV Roberto Vélez Gamboa");
        jfM.getMenuBar();
        jfM.setVisible(true);
		
		JMenuBar menu = new JMenuBar();
	
		jfM.setJMenuBar(menu);
		 productosMenu=new JMenu ("productos");
		productosMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
	            	PanelProductos.setVisible(true); 
	            	PanelClientes.setVisible(false);
	            	PanelVentas.setVisible(false);
	            	PanelFacturas.setVisible(false);
	            	
			}
		});
		menu.add(productosMenu);
		
		
		
		JMenu ventas=new JMenu("Ventas");
		ventas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {			
	            	PanelProductos.setVisible(false); 
	            	PanelClientes.setVisible(false);	           
	            	PanelVentas.setVisible(true);	
	            	PanelFacturas.setVisible(false);
			}
		});
		menu.add(ventas);
		
		JMenu cliente=new JMenu("Clientes");
		cliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelProductos.setVisible(false); 
            	PanelClientes.setVisible(true);	   
            	PanelVentas.setVisible(false);
            	PanelFacturas.setVisible(false);
			}
		});
	
		menu.add(cliente);
		JMenu facturacion=new JMenu ("facturacion");
		facturacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelProductos.setVisible(false); 
            	PanelClientes.setVisible(false);	   
            	PanelVentas.setVisible(false);
            	PanelFacturas.setVisible(true);
			}
		});
	
		menu.add(facturacion);
		JMenu listado=new JMenu ("Listado");
		facturacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelProductos.setVisible(false); 
            	PanelClientes.setVisible(false);	   
            	PanelVentas.setVisible(false);
            	PanelFacturas.setVisible(true);
			}
		});
	
		menu.add(listado);
		JMenuItem todosClientes = new JMenuItem("Listado todos los clientes");
		JMenuItem unCliente = new JMenuItem("Listado de un cliente");
		JMenuItem ProductosMasVendidos = new JMenuItem("Productos más vendidos");
		
		listado.add(todosClientes);
		listado.add(unCliente);
		listado.add(ProductosMasVendidos);
		
		todosClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("hol amundo en todos clientes");
				JFrame frame = new JFrame("TPV");
				frame.getContentPane().setLayout(null);
				frame.setBounds(500, 200, 600, 600);
				Container contentPane =frame.getContentPane();
				JLabel Fecha_anterior_Text=new JLabel("Fecha anterior(2015/04/04 año/mes/dia");
				Fecha_anterior_Text.setBounds(10, 10, 400, 50);
				frame.add(Fecha_anterior_Text);
				JTextField Fecha_anterior= new JTextField();
				Fecha_anterior.setBounds(350, 25, 100, 25);
				frame.add(Fecha_anterior);
				
				JLabel Fecha_posterior_Text=new JLabel("Fecha posterior(2015/04/04 año/mes/dia");
				Fecha_posterior_Text.setBounds(10, 60, 400, 50);
				frame.add(Fecha_posterior_Text);
				JTextField Fecha_posterior= new JTextField();
				Fecha_posterior.setBounds(350, 75, 100, 25);
				frame.add(Fecha_posterior);
				JButton aceptar= new JButton("ACEPTAR");
				aceptar.setBounds(20,500,550,50);
				frame.add(aceptar);
				
				
				aceptar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String anterior=Fecha_anterior.getText();
						String siguiente=Fecha_posterior.getText();
						String[] partes_anterior = anterior.split("/");
						String[] partes_siguiente = siguiente.split("/");
						System.out.println(partes_anterior.length);
						if(partes_anterior.length==3 && partes_siguiente.length==3){
							if(partes_anterior[0].length()==4 && partes_anterior[1].length()==2 && partes_anterior[2].length()==2 && partes_siguiente[0].length()==4 && partes_siguiente[1].length()==2 && partes_siguiente[2].length()==2){
								if(anterior.compareTo(siguiente) < 0){
											JFrame VENTANA2 = new JFrame("Listado clientes/ventas");
											VENTANA2.getContentPane().setLayout(null);
											VENTANA2.setBounds(500, 200, 600, 600);
											VENTANA2.setVisible(true);
											frame.setVisible(false);
											JTextArea area_venana2=new JTextArea();
											area_venana2.setBounds(15, 15, 550, 550);
											VENTANA2.add(area_venana2);
											//Primero miro todos los clientes
											Clientes c;
											Iterator<Clientes> it = clientesArray.iterator();
											String cadena="";
											while(it.hasNext())
											{
												
												c=it.next();
												int id=c.getId();
												String nombre=c.getNombre()+" "+c.getApellido();
												cadena=cadena+ nombre+"\n ";
												cadena=cadena+"Fecha\t"+"Total\n";
												//recorro el array de ticket y solo saco los que son de este cliente
												Ticket t;
												Iterator<Ticket> ct = ticketArray.iterator();
												while(ct.hasNext())
												{
													t=ct.next();
													//miro la fecha del ticekt y total
													int dia=t.dia;
													int mes=t.mes;
													int ano=t.ano;
													String fecha=""+ano+"/"+mes+"/"+dia;
													if(dia<10){
														if(mes<10){
														 fecha=""+ano+"/0"+mes+"/0"+dia;
														}else{
															 fecha=""+ano+"/"+mes+"/0"+dia;
														}
													}
													if(mes<10){
														if(dia<10){
														 fecha=""+ano+"/0"+mes+"/0"+dia;
														}else{
															 fecha=""+ano+"/0"+mes+"/"+dia;
														}
													}
													
													
													boolean bandera=false;//comprobador de fechas
													
													if((anterior.compareTo(fecha) <= 0) && siguiente.compareTo(fecha) >= 0){
														bandera=true;
														
													}
													if(t.cliente.getId()==c.getId())
													{
														if(bandera){//el ticket esta en la fecha dadaa
															System.out.println("en ticket");
															Productos p;
															 int delimitador=t.productos.size();
															 double total=0;
															for(int i=0;i<delimitador;i++){
																Double precio_con_iva= ((Productos) t.productos.get(i)).getPrecioConIva();
																int iva= ((Productos) t.productos.get(i)).getIva();
																int codigo= ((Productos) t.productos.get(i)).getCodigo();
																i++;//para la cantidad
																int Cantidad= ((cantidad)t.productos.get(i)).cant;
																double precio_total_individual=Cantidad*precio_con_iva;
																total=precio_total_individual+total;
																
															}
															cadena=cadena+""+ano+"/"+mes+"/"+dia+"\t"+total+"\n";
														}
													}
													
													
												}
												
												
											}
											area_venana2.setText(cadena);
											
											
									}else{
									JOptionPane.showMessageDialog(jfM, "Error, no has introducido las fechas correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}else{
								JOptionPane.showMessageDialog(jfM, "Error, no has introducido las fechas correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(jfM, "Error, no has introducido las fechas correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			
				frame.setVisible(true);
			}			
		});
		unCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame frame = new JFrame("TPV");
				frame.getContentPane().setLayout(null);
				frame.setBounds(500, 200, 600, 600);
				Container contentPane =frame.getContentPane();
				JLabel Fecha_anterior_Text=new JLabel("Fecha anterior(2015/04/04 año/mes/dia");
				Fecha_anterior_Text.setBounds(10, 10, 400, 50);
				frame.add(Fecha_anterior_Text);
				JTextField Fecha_anterior= new JTextField();
				Fecha_anterior.setBounds(350, 25, 100, 25);
				frame.add(Fecha_anterior);
				
				JLabel Fecha_posterior_Text=new JLabel("Fecha posterior(2015/04/04 año/mes/dia");
				Fecha_posterior_Text.setBounds(10, 60, 400, 50);
				frame.add(Fecha_posterior_Text);
				
				JLabel cliente_text=new JLabel("Cliente(Código)");
				cliente_text.setBounds(10, 110, 400, 50);
				frame.add(cliente_text);
				
				JTextField cliente= new JTextField();
				cliente.setBounds(350, 115, 100, 25);
				frame.add(cliente);
				//sacar todos los id de clientes
				
				JTextField Fecha_posterior= new JTextField();
				Fecha_posterior.setBounds(350, 75, 100, 25);
				frame.add(Fecha_posterior);
				
				JTextArea area= new JTextArea();
				area.setBounds(10, 150, 550, 300);
				frame.add(area);
				
				 String cadena="Codigo\t Nombre\n";	      	
		         	Clientes c;
					Iterator<Clientes> it = clientesArray.iterator();
					while(it.hasNext())
					{
						c=it.next();
						if(c.getEstado())
						{
							cadena=cadena+ c.getId()+"\t"+c.getNombre()+"\n";
						}
						
					}
					 area.setText(cadena);
					 
				
				JButton aceptar= new JButton("ACEPTAR");
				aceptar.setBounds(20,500,550,50);
				frame.add(aceptar);
				aceptar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String anterior=Fecha_anterior.getText();
						String siguiente=Fecha_posterior.getText();
						String[] partes_anterior = anterior.split("/");
						String[] partes_siguiente = siguiente.split("/");
						System.out.println(partes_anterior.length);
						if(partes_anterior.length==3 && partes_siguiente.length==3){
							if(partes_anterior[0].length()==4 && partes_anterior[1].length()==2 && partes_anterior[2].length()==2 && partes_siguiente[0].length()==4 && partes_siguiente[1].length()==2 && partes_siguiente[2].length()==2){
								if(anterior.compareTo(siguiente) < 0){
											JFrame VENTANA2 = new JFrame("Listado clientes/ventas");
											VENTANA2.getContentPane().setLayout(null);
											VENTANA2.setBounds(500, 200, 600, 600);
											VENTANA2.setVisible(true);
											frame.setVisible(false);
											JTextArea area_venana2=new JTextArea();
											area_venana2.setBounds(15, 15, 550, 550);
											VENTANA2.add(area_venana2);
											//Primero miro todos los clientes
											id_cliente=Integer.parseInt(cliente.getText());
											Clientes c;
											Iterator<Clientes> it = clientesArray.iterator();
											String cadena="";
											while(it.hasNext())
											{
												
												c=it.next();
												if(c.getId()==id_cliente){
													int id=c.getId();
													String nombre=c.getNombre()+" "+c.getApellido();
													cadena=cadena+ nombre+"\n ";
													cadena=cadena+"Fecha\t"+"Total\n";
													//recorro el array de ticket y solo saco los que son de este cliente
													Ticket t;
													Iterator<Ticket> ct = ticketArray.iterator();
													while(ct.hasNext())
													{
														t=ct.next();
														//miro la fecha del ticekt y total
														int dia=t.dia;
														int mes=t.mes;
														int ano=t.ano;
														String fecha=""+ano+"/"+mes+"/"+dia;
														if(dia<10){
															if(mes<10){
															 fecha=""+ano+"/0"+mes+"/0"+dia;
															}else{
																 fecha=""+ano+"/"+mes+"/0"+dia;
															}
														}
														if(mes<10){
															if(dia<10){
															 fecha=""+ano+"/0"+mes+"/0"+dia;
															}else{
																 fecha=""+ano+"/0"+mes+"/"+dia;
															}
														}
														
														
														boolean bandera=false;//comprobador de fechas
														
														if((anterior.compareTo(fecha) <= 0) && siguiente.compareTo(fecha) >= 0){
															bandera=true;
															
														}
														if(t.cliente.getId()==c.getId())
														{
															if(bandera){//el ticket esta en la fecha dadaa
																System.out.println("en ticket");
																Productos p;
																 int delimitador=t.productos.size();
																 double total=0;
																for(int i=0;i<delimitador;i++){
																	Double precio_con_iva= ((Productos) t.productos.get(i)).getPrecioConIva();
																	int iva= ((Productos) t.productos.get(i)).getIva();
																	int codigo= ((Productos) t.productos.get(i)).getCodigo();
																	i++;//para la cantidad
																	int Cantidad= ((cantidad)t.productos.get(i)).cant;
																	double precio_total_individual=Cantidad*precio_con_iva;
																	total=precio_total_individual+total;
																	
																}
																cadena=cadena+""+ano+"/"+mes+"/"+dia+"\t"+total+"\n";
															}
														}
														
														
													}
													
												}
													
												
											}
											area_venana2.setText(cadena);
											
											
									}else{
									JOptionPane.showMessageDialog(jfM, "Error, no has introducido las fechas correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}else{
								JOptionPane.showMessageDialog(jfM, "Error, no has introducido las fechas correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(jfM, "Error, no has introducido las fechas correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				frame.setVisible(true);
				
			}			
		});
		ProductosMasVendidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame frame = new JFrame("TPV");
				frame.getContentPane().setLayout(null);
				frame.setBounds(500, 200, 600, 600);
				Container contentPane =frame.getContentPane();
				JLabel Fecha_anterior_Text=new JLabel("Fecha anterior(2015/04/04 año/mes/dia");
				Fecha_anterior_Text.setBounds(10, 10, 400, 50);
				frame.add(Fecha_anterior_Text);
				JTextField Fecha_anterior= new JTextField();
				Fecha_anterior.setBounds(350, 25, 100, 25);
				frame.add(Fecha_anterior);
				
				JLabel Fecha_posterior_Text=new JLabel("Fecha posterior(2015/04/04 año/mes/dia");
				Fecha_posterior_Text.setBounds(10, 60, 400, 50);
				frame.add(Fecha_posterior_Text);
			
				
			
				JTextField Fecha_posterior= new JTextField();
				Fecha_posterior.setBounds(350, 75, 100, 25);
				frame.add(Fecha_posterior);
				
			
				JButton aceptar= new JButton("ACEPTAR");
				aceptar.setBounds(20,500,550,50);
				frame.add(aceptar);
				aceptar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String anterior=Fecha_anterior.getText();
						String siguiente=Fecha_posterior.getText();
						String[] partes_anterior = anterior.split("/");
						String[] partes_siguiente = siguiente.split("/");
						System.out.println(partes_anterior.length);
						if(partes_anterior.length==3 && partes_siguiente.length==3){
							if(partes_anterior[0].length()==4 && partes_anterior[1].length()==2 && partes_anterior[2].length()==2 && partes_siguiente[0].length()==4 && partes_siguiente[1].length()==2 && partes_siguiente[2].length()==2){
								if(anterior.compareTo(siguiente) < 0){
									JFrame VENTANA2 = new JFrame("Listado clientes/ventas");
									VENTANA2.getContentPane().setLayout(null);
									VENTANA2.setBounds(500, 200, 600, 600);
									VENTANA2.setVisible(true);
									frame.setVisible(false);
									JTextArea area_venana2=new JTextArea();
									area_venana2.setBounds(15, 15, 550, 550);
									VENTANA2.add(area_venana2);
									//Creo array con el codigo de producto size+1
									int b[] = new int[productosArray.size()+1];
									for(int i=0;i<productosArray.size()+1;i++){
										b[i]=0;
									}
									String cadena="";
											cadena=cadena+ "Producto\t Cantidad\t Posición\n ";
											//recorro el array de ticket 
											Ticket t;
											Iterator<Ticket> ct = ticketArray.iterator();
											while(ct.hasNext())
											{
												t=ct.next();
												//miro la fecha del ticekt y total
												int dia=t.dia;
												int mes=t.mes;
												int ano=t.ano;
												String fecha=""+ano+"/"+mes+"/"+dia;
												
												boolean bandera=false;//comprobador de fechas
												
												if((anterior.compareTo(fecha) <= 0) && siguiente.compareTo(fecha) >= 0){
													bandera=true;
													
												}
													if(bandera){//el ticket esta en la fecha dada
														System.out.println("en ticket");
														Productos p;
														 int delimitador=t.productos.size();
														 double total=0;
														for(int i=0;i<delimitador;i++){
															
															
															int codigoProductos= ((Productos) t.productos.get(i)).getCodigo();
															i++;//para la cantidad
															int Cantidad= ((cantidad)t.productos.get(i)).cant;
														b[codigoProductos]=b[codigoProductos]+Cantidad;
														}
														//cadena=cadena+""+ano+"/"+mes+"/"+dia+"\t"+total+"\n";
													}	
											}
											int comparadorCantidad[] = new int[productosArray.size()+1];
											for(int i=1;i<productosArray.size()+1;i++){
												comparadorCantidad[i]=b[i];
											}
											//ahora ordeno las cantidades
											int cuentaintercambios=0;
											for (boolean ordenado=false;!ordenado;){
									            for (int i=1;i<comparadorCantidad.length-1;i++){
									                if (comparadorCantidad[i]>comparadorCantidad[i+1]){
									                    //Intercambiamos valores
									                    int variableauxiliar=comparadorCantidad[i];
									                    comparadorCantidad[i]=comparadorCantidad[i+1];
									                    comparadorCantidad[i+1]=variableauxiliar;
									                    //indicamos que hay un cambio
									                    cuentaintercambios++;
									                }
									            }
									            //Si no hay intercambios, es que esta ordenado.
									            if (cuentaintercambios==0){
									                ordenado=true;
									            }
									            //Inicializamos la variable de nuevo para que empiece a contar de nuevo
									            cuentaintercambios=0;
									        }
											//System.out.println(cuentaintercambios);
											/*for(int i=1;i<productosArray.size()+1;i++){
												System.out.println(i+"  "+b[i]);
											}*/
											//comparo las cantidades y lo imprimo en la cadena 
											cadena="Posicion\tCódigo\tDescripción\tCantidad\n";
											for(int i=productosArray.size();i>=1;i--){
												for(int a=1;a<productosArray.size()+1;a++){
													if(comparadorCantidad[i]==b[a]){
														Productos p=(Productos) productosArray.get(a-1);
														System.out.println((productosArray.size()+1-i)+"\t "+p.getCodigo()+"\t "+p.getDescripcion() +"\t "+comparadorCantidad[i] +"\n" );
														cadena=cadena+(productosArray.size()+1-i)+"\t "+p.getCodigo()+"\t "+p.getDescripcion() +"\t "+comparadorCantidad[i] +"\n" ; 
													}
												}
													
											}
											area_venana2.setText(cadena);
											
										}
											
							}else{
								JOptionPane.showMessageDialog(jfM, "Error, no has introducido las fechas correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(jfM, "Error, no has introducido las fechas correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				frame.setVisible(true);
				
			}			
		});
		jfM.setExtendedState(jfM.MAXIMIZED_BOTH);
    }
 
    public void productos(){
    	
    	try{
    	//creación del panel de productos
    	PanelProductos = new JPanel();
    	//PanelProductos
    	 areaProducto = new JTextArea();
		areaProducto.setBounds(100, 100, 500, 500);
		areaProducto.setAutoscrolls(true);
		PanelProductos.add(areaProducto);
		
    	JButton importar= new JButton("importar");
		importar.setBounds(10, 25, 109, 23);
		importar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {			
				importarProducto();	
				 JOptionPane.showMessageDialog(jfM, "Productos actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		PanelProductos.add(importar);
    	
    	JButton exportar= new JButton("exportar");
		exportar.setBounds(120, 25, 109, 23);
		exportar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {			
				exportarProducto();	
				 JOptionPane.showMessageDialog(jfM, "Productos actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);		
			}
			
		});
		PanelProductos.add(exportar);
		
		JButton nuevo= new JButton("nuevoProducto");
		nuevo.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
				
				NuevoProducto();
				
			}
		});
			
		nuevo.setBounds(230, 25, 139,23);
		PanelProductos.add(nuevo);
 
		JButton eliminar = new JButton("Eliminar");
		eliminar.setBounds(630, 150, 139,23);
		JTextField elimi = new JTextField();
		elimi.setBounds(800,150,130,23);
		PanelProductos.add(elimi);
		eliminar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
			int Texto=Integer.parseInt(elimi.getText());
				if(productosArray.size()>=Texto)
				{
					((Productos) productosArray.get(Texto-1)).estado(false);
					actualizarProductos();
					JOptionPane.showMessageDialog(jfM, "Productos actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(jfM, "Error en la eliminación del producto", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		PanelProductos.add(eliminar);
		
		JButton modificar = new JButton("modificar productos");
		modificar.setBounds(630, 200, 139,23);
		JTextField modi = new JTextField();
		modi.setBounds(800,200,130,23);
		
		modificar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
				
				int Texto=Integer.parseInt(modi.getText());
				if(productosArray.size()>=Texto)
				{
					modificarProducto(modi.getText());
					actualizarProductos();
					
				}else{
					JOptionPane.showMessageDialog(jfM, "Error, no existe ese producto", "ERROR", JOptionPane.ERROR_MESSAGE);	
				}
				
			}
		});
		PanelProductos.add(modi);
		PanelProductos.add(modificar);
        PanelProductos.setVisible(true);
    	}catch(NumberFormatException e){
    		 JOptionPane.showMessageDialog(jfM, "Error al introducir algúbn dato", "ERROR", JOptionPane.ERROR_MESSAGE);
    	 }
    }
    public void clientes()
    {
    	try{
	    	PanelClientes = new JPanel();
	    	
			
			areaCliente = new JTextArea();
			areaCliente.setBounds(100, 100, 500, 500);
			areaCliente.setAutoscrolls(true);
			PanelClientes.add(areaCliente);
			JButton importar= new JButton("importar clientes");
			importar.setBounds(10, 25, 109, 23);
			importar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					
					importarClientes();
					actualizarClientes();
					 JOptionPane.showMessageDialog(jfM, "Clientes actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			PanelClientes.add(importar);
			
			
			JButton exportar= new JButton("exportar clientes");
			exportar.setBounds(120, 25, 109, 23);
			exportar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					
					 exportarClientes();			
				}
			});
			PanelClientes.add(exportar);
			JButton nuevoCliente= new JButton("nuevoCliente");
			nuevoCliente.setBounds(220, 25, 109, 23);
			nuevoCliente.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					
					NuevoCliente();		
					 //JOptionPane.showMessageDialog(jfM, "Clientes actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			PanelClientes.add(nuevoCliente);
			
			JButton eliminar = new JButton("Eliminar");
			eliminar.setBounds(630, 150, 139,23);
			JTextField elimi = new JTextField();
			elimi.setBounds(800,150,130,23);
			PanelClientes.add(elimi);
			eliminar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
				int Texto=Integer.parseInt(elimi.getText());
					if(clientesArray.size()>=Texto)
					{
						((Clientes) clientesArray.get(Texto-1)).setEstado(false);
						JOptionPane.showMessageDialog(jfM, "Clientes actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(jfM, "Error en la eliminación del producto", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					actualizarClientes();
				}
			});
			PanelClientes.add(eliminar);
			
			JButton modificar = new JButton("modificar productos");
			modificar.setBounds(630, 200, 139,23);
			JTextField modi = new JTextField();
			modi.setBounds(800,200,130,23);
			
			modificar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					int Texto=Integer.parseInt(modi.getText());
					if(clientesArray.size()>=Texto){
						modificarCliente(modi.getText());
						actualizarClientes();
					}else{
						JOptionPane.showMessageDialog(jfM, "Error, no existe ese producto", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				
				}
			});
			PanelClientes.add(modi);
			PanelClientes.add(modificar);
    	}catch(NumberFormatException e){
    		 JOptionPane.showMessageDialog(jfM, "Error al introducir algúbn dato", "ERROR", JOptionPane.ERROR_MESSAGE);
    	 }
			
				
		
    }
    public void facturas()
    {
    	try{
	    	PanelFacturas = new JPanel();
	    	JButton nuevaFactura= new JButton("nueva factura");
	    	nuevaFactura.setBounds(220, 25, 109, 23);
	    	nuevaFactura.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					nuevaFactura();
					actualizarfacturas();
				}
			});
			PanelFacturas.add(nuevaFactura);
			
			areaFactura = new JTextArea();
			areaFactura.setBounds(100, 100, 500, 500);
			areaFactura.setAutoscrolls(true);
			PanelFacturas.add(areaFactura);
			JButton exportar= new JButton("exportar facturas");
			exportar.setBounds(120, 25, 109, 23);
			exportar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					
					exportarFacturas();
					actualizarfacturas();
					//JOptionPane.showMessageDialog(jfM, "Facturas actualizadas", "INFO", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			PanelFacturas.add(exportar);
			JButton importar= new JButton("importar clientes");
			importar.setBounds(10, 25, 109, 23);
			importar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					
					importarFacturas();
					actualizarfacturas();
					JOptionPane.showMessageDialog(jfM, "Facturas actualizadas", "INFO", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			PanelFacturas.add(importar);
			
			JButton mostrar_text = new JButton("Mostrar factura ");
			mostrar_text.setBounds(630, 150, 139,23);
			JTextField mostrar = new JTextField();
			mostrar.setBounds(800,150,130,23);
			PanelFacturas.add(mostrar_text);
			PanelFacturas.add(mostrar);
			mostrar_text.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					//System.out.println(facturaArray.size());
					int Texto=Integer.parseInt(mostrar.getText());
					if(facturaArray.size()>=Texto){
						mostrarFactura(Texto);
						actualizarTickets();
					}else{
					
						JOptionPane.showMessageDialog(jfM, "Error, no existe esa factura", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
    	}catch(NumberFormatException e){
    		 JOptionPane.showMessageDialog(jfM, "Error al introducir algúbn dato", "ERROR", JOptionPane.ERROR_MESSAGE);
    	 }
		
    }
    public void ventas()
    {
    	try{
	    	PanelVentas = new JPanel();
	    	
	    	areaTicket = new JTextArea();
	    	areaTicket.setBounds(100, 100, 500, 500);
	    	areaTicket.setAutoscrolls(true);
	    	PanelVentas.add(areaTicket);
			
			JButton importar= new JButton("importar Ticket");
			importar.setBounds(10, 25, 109, 23);
			importar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					
					importarTickets();
					actualizarTickets();
					JOptionPane.showMessageDialog(jfM, "Ticket actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			PanelVentas.add(importar);
			
			JButton exportar= new JButton("exportar Ticket");
			exportar.setBounds(120, 25, 109, 23);
			exportar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					
					 exportarTickets();	
					
				}
			});
			PanelVentas.add(exportar);
			JButton nuevoTicket= new JButton("nuevo ticket");
			nuevoTicket.setBounds(220, 25, 109, 23);
			nuevoTicket.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
					
					NuevoTicket();	
					actualizarTickets();
					
				}
			});
			PanelVentas.add(nuevoTicket);
			
			JButton mostrar_text = new JButton("Mostrar");
			mostrar_text.setBounds(630, 150, 139,23);
			JTextField mostrar = new JTextField();
			mostrar.setBounds(800,150,130,23);
			PanelVentas.add(mostrar_text);
			PanelVentas.add(mostrar);
			mostrar_text.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) {
				
					int Texto=Integer.parseInt(mostrar.getText());
					if(ticketArray.size()>=Texto){
						mostrarTicket(Texto);
						actualizarTickets();
					}else{
					
						JOptionPane.showMessageDialog(jfM, "Error, no existe ese ticket", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
    	}catch(NumberFormatException e){
    		 JOptionPane.showMessageDialog(jfM, "Error al introducir algúbn dato", "ERROR", JOptionPane.ERROR_MESSAGE);
    	 }
		
    }
 
 
 
    public static void main(String[] args) {        
        GuiJava gj = new GuiJava();//uso de constructor para la ventana
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	public void exportarProducto()
	{
	
		   FileOutputStream fichero = null;
	        ObjectOutputStream salida = null;
	        Productos p;
	        cantidad c;
	        
	    
	        String continuar="si";
			
			 try {
				
				 String ruta =rutaArchivo+"/Productos.txt";
		            fichero = new FileOutputStream(ruta);
		            salida = new ObjectOutputStream(fichero);
		            c=new cantidad(productosArray.size());
		            salida.writeObject(c);
		            /*Introducimos los objetos en el fichero*/
		          for(int i=0;i<productosArray.size();i++)
		          {
		        	  salida.writeObject(productosArray.get(i));
		          }        
			 } catch (FileNotFoundException e) {
		       
		            JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		            
		        } catch (IOException e) {
		        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		        } finally {
		            try {
		                if(fichero!=null) fichero.close();
		                if(salida!=null) salida.close();
		            } catch (IOException e) {
		            	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		            }
		        }
			 actualizarProductos();
	}
	public void exportarClientes()
	{
		FileOutputStream fichero = null;
        ObjectOutputStream salida = null;
        Clientes p;
        cantidad c;
        
        /*Meter datso en arrayList*/
         
        String continuar="si";
		
		 try {
			
			 String ruta=rutaArchivo+"/Clientes.txt";
	            fichero = new FileOutputStream(ruta);
	            salida = new ObjectOutputStream(fichero);
	            c=new cantidad(clientesArray.size());
	            salida.writeObject(c);
	            //System.out.println("exportar "+c.cant);
	          for(int i=0;i<clientesArray.size();i++)
	          {
	        	  
	        	  salida.writeObject(clientesArray.get(i));
	          }        
		 } catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	        } catch (IOException e) {
	        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	        } finally {
	            try {
	                if(fichero!=null) fichero.close();
	                if(salida!=null) salida.close();
	            } catch (IOException e) {
	            	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	            }
	        }
		 actualizarClientes();
	}
	public void exportarFacturas()
	{
		FileOutputStream fichero = null;
        ObjectOutputStream salida = null;
        Factura p;
        cantidad c;
        
        /*Meter datso en arrayList*/
         
        String continuar="si";
		
		 try {
			
			 String ruta=rutaArchivo+"/Facturas.txt";
	            fichero = new FileOutputStream(ruta);
	            salida = new ObjectOutputStream(fichero);
	            c=new cantidad(facturaArray.size());
	            salida.writeObject(c);
	            //System.out.println("exportar "+c.cant);
	          for(int i=0;i<facturaArray.size();i++)
	          {
	        	  salida.writeObject(facturaArray.get(i));
	          }        
		 } catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	        } catch (IOException e) {
	        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	        } finally {
	            try {
	                if(fichero!=null) fichero.close();
	                if(salida!=null) salida.close();
	            } catch (IOException e) {
	            	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	            }
	        }
		 
	}
	public void importarFacturas()
	{
		
		 String ruta=rutaArchivo+"/Facturas.txt";
		
		 Factura p;
	     cantidad c;
		FileInputStream ficheroLeer = null;
       ObjectInputStream entrada = null;
       

       try {

       	productosArray.clear();
           ficheroLeer = new FileInputStream(ruta);
           entrada = new ObjectInputStream(ficheroLeer);
           c= (cantidad) entrada.readObject();
           
           int cantidad=c.cant;
           
           while(cantidad>0)
           {
           	p = (Factura) entrada.readObject();
          
           	facturaArray.add(p);
           	 cantidad--;
           }
          actualizarProductos();
          
       } catch (FileNotFoundException e) {
    	   JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
          
       } catch (ClassNotFoundException e) {
    	   JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
          
       } catch (IOException e) {
    	   JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
       } finally {
           try {
               if (ficheroLeer != null) {
               	ficheroLeer.close();
               }
               if (entrada != null) {
                   entrada.close();
               }
           } catch (IOException e) {
        	   JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
               
           }
       }
       actualizarProductos();
	}
	public void importarProducto()
	{
		
		 String ruta=rutaArchivo+"/Productos.txt";
		
		 Productos p;
	     cantidad c;
		FileInputStream ficheroLeer = null;
       ObjectInputStream entrada = null;
       

       try {

       	productosArray.clear();
           ficheroLeer = new FileInputStream(ruta);
           entrada = new ObjectInputStream(ficheroLeer);
           c= (cantidad) entrada.readObject();
           
           int cantidad=c.cant;
           
           while(cantidad>0)
           {
           	p = (Productos) entrada.readObject();
          
           	productosArray.add(p);
           	 cantidad--;
           }
          actualizarProductos();
          
       } catch (FileNotFoundException e) {
    	   JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
          
       } catch (ClassNotFoundException e) {
    	   JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
          
       } catch (IOException e) {
    	   JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
       } finally {
           try {
               if (ficheroLeer != null) {
               	ficheroLeer.close();
               }
               if (entrada != null) {
                   entrada.close();
               }
           } catch (IOException e) {
        	   JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           }
       }
       actualizarProductos();
	}
	
	public void exportarTickets()
	{
		FileOutputStream fichero = null;
        ObjectOutputStream salida = null;
        Ticket p;
        cantidad c;
        
        /*Meter datso en arrayList*/
         
        String continuar="si";
		
		 try {
			
			 String ruta=rutaArchivo+"/Tickets.txt";
	            fichero = new FileOutputStream(ruta);
	            salida = new ObjectOutputStream(fichero);
	            c=new cantidad(ticketArray.size());
	            salida.writeObject(c);
	            //System.out.printaln("exportar "+c.cant);
	          for(int i=0;i<ticketArray.size();i++)
	          {
	        	  
	        	  salida.writeObject(ticketArray.get(i));
	          }        
		 } catch (FileNotFoundException e) {
			 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	        } catch (IOException e) {
	        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	        } finally {
	            try {
	                if(fichero!=null) fichero.close();
	                if(salida!=null) salida.close();
	            } catch (IOException e) {
	            	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	            }
	        }
		 actualizarClientes();
	}
	
	public void importarClientes()
	{
		 
	
		
		 Clientes cli;
	     cantidad c;
		FileInputStream ficheroLeer = null;
        ObjectInputStream entrada = null;		     

        try {
        	String ruta=rutaArchivo+"/Clientes.txt";
       	 
        	clientesArray.clear();
            ficheroLeer = new FileInputStream(ruta);
            entrada = new ObjectInputStream(ficheroLeer);
            c= (cantidad) entrada.readObject();

            int cantidad=c.cant;
           
            while(cantidad>0)
            {
            	//System.out.println("HOLA MUNDO");
               	cli = (Clientes) entrada.readObject();
            	clientesArray.add(cli);
            	cantidad--;
            }
         
           
        } catch (FileNotFoundException e) {
        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           
        } catch (ClassNotFoundException e) {
        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           
        } catch (IOException e) {
        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ficheroLeer != null) {
                	ficheroLeer.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
            	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        actualizarClientes();
	}
	public void importarTickets()
	{

		 Ticket tick;
	     cantidad c;
		FileInputStream ficheroLeer = null;
        ObjectInputStream entrada = null;		     

        try {
        	String ruta=rutaArchivo+"/Tickets.txt";
       	 
        	ticketArray.clear();
            ficheroLeer = new FileInputStream(ruta);
            entrada = new ObjectInputStream(ficheroLeer);
            c= (cantidad) entrada.readObject();

            int cantidad=c.cant;
           
            while(cantidad>0)
            {
           
            	tick = (Ticket) entrada.readObject();
            	ticketArray.add(tick);
            	cantidad--;
            }
         
           
        } catch (FileNotFoundException e) {
        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           
        } catch (ClassNotFoundException e) {
        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           
        } catch (IOException e) {
        	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ficheroLeer != null) {
                	ficheroLeer.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
            	 JOptionPane.showMessageDialog(jfM, "Error, "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);                
            }
        }
        actualizarTickets();
	}
	public void modificarProducto(String Texto)
	{
		try{
			JFrame frame = new JFrame("TPV");
			frame.getContentPane().setLayout(null);
			frame.setBounds(500, 200, 400, 400);
			Container contentPane =frame.getContentPane();
			
			
			JLabel descripcion_texto= new JLabel("Descripcion");
			descripcion_texto.setBounds(30, 25, 139,23);
			frame.add(descripcion_texto);
			JTextField descripcion = new JTextField();
			descripcion.setBounds(230, 25, 139,23);
			frame.add(descripcion);
			
			JLabel precio_texto= new JLabel("Precio_unitario");
			precio_texto.setBounds(30, 55, 139,23);
			frame.add(precio_texto);
			JTextField precio = new JTextField();
			precio.setBounds(230, 55, 139,23);
			frame.add(precio);
			
			JLabel iva_texto= new JLabel("IVA");
			iva_texto.setBounds(30, 85, 139,23);
			frame.add(iva_texto);
			JTextField iva = new JTextField();
			iva.setBounds(230, 85, 139,23);
			frame.add(iva);
			
			JLabel cantidad_texto= new JLabel("Cantidad en stock");
			cantidad_texto.setBounds(30, 115, 139,23);
			frame.add(cantidad_texto);
			JTextField cantidad = new JTextField();
			cantidad.setBounds(230, 115, 139,23);
			frame.add(cantidad);
			JButton aceptar= new JButton("Aceptar");
			aceptar.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent arg0) 
				{
					
					if(Texto!="")
					{
					
						
							int Codigo_modi=Integer.parseInt(Texto);
						
						if(productosArray.size()>=Codigo_modi )
						{
					
							if((!descripcion.getText().equals("")))
							{ 
								((Productos) productosArray.get(Codigo_modi-1)).setDescripcion(descripcion.getText());
							}
													
							if((!precio.getText().equals("")))
							{
								String precioS=precio.getText();
								double precio=Double.parseDouble(precioS);
								((Productos) productosArray.get(Codigo_modi-1)).setPrecioUnitario(precio);
							}
							
							if((!iva.getText().equals("")))
							{
								String ivaS=iva.getText();
								int iva= Integer.parseInt(ivaS);
								((Productos) productosArray.get(Codigo_modi-1)).setIva(iva);
							}
	
							if((!cantidad.getText().equals("")))
							{
								String cantidadS=cantidad.getText();
								int cantidad= Integer.parseInt(cantidadS);
								((Productos) productosArray.get(Codigo_modi-1)).setStock(cantidad);
							}
							JOptionPane.showMessageDialog(jfM, "Productos actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(jfM, "Error en la modificación del producto", "ERROR", JOptionPane.ERROR_MESSAGE);
						}	
					}
					else
					{
						frame.setVisible(false);
						JOptionPane.showMessageDialog(jfM, "Error en la modificación del producto", "ERROR", JOptionPane.ERROR_MESSAGE);	
					}
					
				
					frame.setVisible(false);
	
					actualizarProductos();	
				}
			});
			aceptar.setBounds(120, 150, 150, 150);
			frame.add(aceptar);
			frame.setVisible(true);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(jfM, "Error en la introduccion de algun dato", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void modificarCliente( String Texto)
	{
		JFrame frame = new JFrame("TPV");
		frame.getContentPane().setLayout(null);
		frame.setBounds(500, 200, 400, 400);
		Container contentPane =frame.getContentPane();
		frame.setVisible(true);
		
		JLabel nombre_Texto= new JLabel("Nombre");
		nombre_Texto.setBounds(30, 25, 139,23);
		frame.add(nombre_Texto);
		JTextField nombre = new JTextField();
		nombre.setBounds(230, 25, 139,23);
		frame.add(nombre);
		
		JLabel apellido_Texto= new JLabel("Apellido");
		apellido_Texto.setBounds(30, 55, 139,23);
		frame.add(apellido_Texto);
		JTextField apellido = new JTextField();
		apellido.setBounds(230, 55, 139,23);
		frame.add(apellido);
		
		JLabel nif_Texto= new JLabel("Nif");
		nif_Texto.setBounds(30, 85, 139,23);
		frame.add(nif_Texto);
		JTextField nif = new JTextField();
		nif.setBounds(230, 85, 139,23);
		frame.add(nif);
		
		JLabel razonSocial_Texto= new JLabel("Razon social");
		razonSocial_Texto.setBounds(30, 115, 139,23);
		frame.add(razonSocial_Texto);
		JTextField razonSocial = new JTextField();
		razonSocial.setBounds(230, 115, 139,23);
		frame.add(razonSocial);
		
		JLabel domicilio_Texto= new JLabel("Domicilio");
		domicilio_Texto.setBounds(30, 145, 139,23);
		frame.add(domicilio_Texto);
		JTextField domicilio = new JTextField();
		domicilio.setBounds(230, 145, 139,23);
		frame.add(domicilio);
		
		JButton aceptar= new JButton("Aceptar");
		aceptar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				
				if(Texto!="")
				{
					int Codigo_modi=Integer.parseInt(Texto);
					if(clientesArray.size()>=Codigo_modi )
					{
				
						if((!nombre.getText().equals("")))
						{ 
							((Clientes) clientesArray.get(Codigo_modi-1)).setNombre(nombre.getText());
						}
						if((!apellido.getText().equals("")))
						{ 
							((Clientes) clientesArray.get(Codigo_modi-1)).setApellido(apellido.getText());
						}
						if((!nif.getText().equals("")))
						{ 
							((Clientes) clientesArray.get(Codigo_modi-1)).setNif(nif.getText());
						}
						if((!razonSocial.getText().equals("")))
						{ 
							((Clientes) clientesArray.get(Codigo_modi-1)).setRazonSocial(razonSocial.getText());
						}
						if((!domicilio.getText().equals("")))
						{ 
							((Clientes) clientesArray.get(Codigo_modi-1)).setDomicilio(domicilio.getText());
						}
												
						JOptionPane.showMessageDialog(jfM, "Clientes actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
						
					}
					else
					{
						JOptionPane.showMessageDialog(jfM, "Error en la modificación del cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}
				else
				{
					frame.setVisible(false);
					JOptionPane.showMessageDialog(jfM, "Error en la modificación del cliente", "ERROR", JOptionPane.ERROR_MESSAGE);	
				}
				
			
				frame.setVisible(false);

				actualizarClientes();	
			}
		});
		aceptar.setBounds(120, 200, 100, 100);
		frame.add(aceptar);
		frame.setVisible(true);
	}
	public  void NuevoProducto()
	{ 
		//Ventana para añadir un nuevo producto a la lista
	
		Productos p;
		JFrame frame = new JFrame("TPV");
		frame.getContentPane().setLayout(null);
		frame.setBounds(500, 200, 400, 400);
		Container contentPane =frame.getContentPane();
		JLabel descripcion_texto= new JLabel("Descripcion");
		descripcion_texto.setBounds(30, 25, 139,23);
		frame.add(descripcion_texto);
		JTextField descripcion = new JTextField();
		descripcion.setBounds(230, 25, 139,23);
		frame.add(descripcion);
		
		JLabel precio_texto= new JLabel("Precio_unitario");
		precio_texto.setBounds(30, 55, 139,23);
		frame.add(precio_texto);
		JTextField precio = new JTextField();
		precio.setBounds(230, 55, 139,23);
		frame.add(precio);
		
		JLabel iva_texto= new JLabel("IVA");
		iva_texto.setBounds(30, 85, 139,23);
		frame.add(iva_texto);
		JTextField iva = new JTextField();
		iva.setBounds(230, 85, 139,23);
		frame.add(iva);
		
		JLabel cantidad_texto= new JLabel("Cantidad en stock");
		cantidad_texto.setBounds(30, 115, 139,23);
		frame.add(cantidad_texto);
		JTextField cantidad = new JTextField();
		cantidad.setBounds(230, 115, 139,23);
		frame.add(cantidad);
		JButton aceptar= new JButton("Aceptar");
		aceptar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
				
			
				frame.setVisible(false);
				int codigo= productosArray.size()+1;
				String precioS=precio.getText();
				double precio=Double.parseDouble(precioS);
				String ivaS=iva.getText();
				int iva= Integer.parseInt(ivaS);
				String cantidadS=cantidad.getText();
				int cantidad= Integer.parseInt(cantidadS);
				Productos p= new Productos(codigo,descripcion.getText(),precio,iva , cantidad);  
				productosArray.add(p);
				actualizarProductos();
				JOptionPane.showMessageDialog(jfM, "Productos actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		aceptar.setBounds(120, 250, 100, 50);
		frame.add(aceptar);
		frame.setVisible(true);
	}
	public void NuevoCliente(){
		JFrame frame = new JFrame("TPV");
		frame.getContentPane().setLayout(null);
		frame.setBounds(500, 200, 400, 400);
		Container contentPane =frame.getContentPane();
		
		
		JLabel nombre_Texto= new JLabel("Nombre");
		nombre_Texto.setBounds(30, 25, 139,23);
		frame.add(nombre_Texto);
		JTextField nombre = new JTextField();
		nombre.setBounds(230, 25, 139,23);
		frame.add(nombre);
		
		JLabel apellido_Texto= new JLabel("Apellido");
		apellido_Texto.setBounds(30, 55, 139,23);
		frame.add(apellido_Texto);
		JTextField apellido = new JTextField();
		apellido.setBounds(230, 55, 139,23);
		frame.add(apellido);
		
		JLabel nif_Texto= new JLabel("Nif");
		nif_Texto.setBounds(30, 85, 139,23);
		frame.add(nif_Texto);
		JTextField nif = new JTextField();
		nif.setBounds(230, 85, 139,23);
		frame.add(nif);
		
		JLabel razonSocial_Texto= new JLabel("Razon social");
		razonSocial_Texto.setBounds(30, 115, 139,23);
		frame.add(razonSocial_Texto);
		JTextField razonSocial = new JTextField();
		razonSocial.setBounds(230, 115, 139,23);
		frame.add(razonSocial);
		
		JLabel domicilio_Texto= new JLabel("Domicilio");
		domicilio_Texto.setBounds(30, 145, 139,23);
		frame.add(domicilio_Texto);
		JTextField domicilio = new JTextField();
		domicilio.setBounds(230, 145, 139,23);
		frame.add(domicilio);
		
		JButton aceptar= new JButton("Aceptar");
		aceptar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
				
				frame.setVisible(false);
				
				Clientes c= new Clientes( clientesArray.size()+1, nif.getText(),  nombre.getText(),  apellido.getText(),  razonSocial.getText() ,  domicilio.getText(), "");
				clientesArray.add(c);
				actualizarClientes();
				JOptionPane.showMessageDialog(jfM, "Clientes actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aceptar.setBounds(120, 250, 100, 50);
		frame.add(aceptar);
		frame.setVisible(true);
	}
	public void nuevaFactura(){
		
		ArrayList ticket_factura=new ArrayList();
		JFrame frame = new JFrame("TPV");
		frame.getContentPane().setLayout(null);
		frame.setBounds(500, 10, 800, 1000);
		Container contentPane =frame.getContentPane();
		
		//Cliente Y BUSCADOR CLIENTE 
		JLabel cliente_Text=new JLabel("CLIENTE: ");
		cliente_Text.setBounds(5, 5, 100, 50);
		frame.add(cliente_Text);
		JTextField cliente=new JTextField();
		cliente.setBounds(120, 10, 100, 30);
		frame.add(cliente);
			//buscador cliente
		JLabel Buscador_cliente_Text=new JLabel("Buscador: ");
		Buscador_cliente_Text.setBounds(300, 5, 100, 50);
		frame.add(Buscador_cliente_Text);
		JTextField buscador_cliente=new JTextField();
		buscador_cliente.setBounds(420, 10, 100, 30);
		frame.add(buscador_cliente);
		
		JTextArea area_Buscador_Cliente= new JTextArea();	
		area_Buscador_Cliente.setBounds(400, 100, 300, 150);
		area_Buscador_Cliente.setAutoscrolls(true);
		frame.add(area_Buscador_Cliente);
		
		buscador_cliente.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyReleased(KeyEvent arg0) {
				
				 String cadena= "ID   "+ "Nombre\n";
		         	Clientes c;
					Iterator<Clientes> it = clientesArray.iterator();
					
					String buscador;
					
					if(buscador_cliente.getText().length()==0){
						
						buscador="asdasdasdasdasdkiosdhaslkdnasd";
						// JOptionPane.showMessageDialog(jfM, "Error, no se encuentra ningún cliente con esa búsqueda", "ERROR", JOptionPane.ERROR_MESSAGE);
						cadena="No se encuentra ningún cliente con esa búsqueda";
						// cadena="";
					}else{
						buscador=buscador_cliente.getText();
					}
					while(it.hasNext())
					{
						c=it.next();
						if(c.getEstado())
						{
							if(c.getNombre().indexOf(buscador)!=-1)
							{
								cadena=cadena+c.getId() +"   "+  c.getNombre()+"\n";
							}
						}
					}
					area_Buscador_Cliente.setText(cadena);
			}
			
		});
    	 banderaMostrar=true;//si no se muestra los ticket no se 
		//Fecha
    	 JTextArea area_Ticket= new JTextArea();
 		
    	 area_Ticket.setBounds(20, 290,750, 400);
    	 area_Ticket.setAutoscrolls(true);
    	 area_Ticket.setText("Ticket que se introduciran en la factura");
     	frame.add(area_Ticket);
     	
		JLabel ano_Text=new JLabel("Fecha(Año):");
		ano_Text.setBounds(5, 40, 100, 50);
		frame.add(ano_Text);
		JTextField ano=new JTextField();
		ano.setBounds(120, 45, 100, 30);
		frame.add(ano);
		JButton mostrarDatos= new JButton("Mostrar datos de factura");
		mostrarDatos.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
				totalFactura=0;
				String cadenaTicket="Fecha\t Importe total\t\n";
				ticket_factura.clear();
				//no hay clienteSystem.out.println("En cliente"+cliente.getText()+"asdasd");
				if(cliente.getText().equals("")){//No hay cliente
					//no hay cliente
					JOptionPane.showMessageDialog(jfM, "Error, no hay cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
					banderaMostrar=false;
				}
				if(ano.getText().equals("")){//No hay cliente
					JOptionPane.showMessageDialog(jfM, "Error, no hay año", "ERROR", JOptionPane.ERROR_MESSAGE);
					banderaMostrar=false;
				}
				
				if(banderaMostrar==true){
					//Miramos cada ticket y se comprueba cliente y fecha si esta correcto se introduce en el array
					
					 String cadena= "ID   "+ "Nombre\n";
			         	Ticket t;
						Iterator<Ticket> it = ticketArray.iterator();	
						while(it.hasNext())
						{
							Clientes c;
							t=it.next();
							if(t.cliente.getEstado()){
								int id_cliente=Integer.parseInt(cliente.getText());
								int id_ano=Integer.parseInt(ano.getText());
								if(t.cliente.getId()==id_cliente && id_ano==t.ano){
									ticket_factura.add(t);
									
									
									Productos p;
									
									 int delimitador=t.productos.size();
									 
									
									 double total=0;
									for(int i=0;i<delimitador;i++){
										
										String nombre= ((Productos) t.productos.get(i)).getDescripcion();
										Double precio_con_iva= ((Productos) t.productos.get(i)).getPrecioConIva();
										int iva= ((Productos) t.productos.get(i)).getIva();
										int codigo= ((Productos) t.productos.get(i)).getCodigo();
										i++;//para la cantidad
										//asdasdasd
										int Cantidad= ((cantidad)t.productos.get(i)).cant;
										
										double precio_total_individual=Cantidad*precio_con_iva;
										cadena=cadena+codigo+"\t"+nombre+"\t"+ +Cantidad +"\t"+precio_con_iva+"\t"+iva+"\t"+precio_total_individual+"\n";
										total=precio_total_individual+total;
										
									}//fin for
									totalFactura=totalFactura+total;
									
									cadenaTicket=cadenaTicket+""+t.dia+"/"+t.mes+"/"+t.ano+"\t"+total+"\n";
								}
							}
						}
						area_Ticket.setText(cadenaTicket);
				}
			}
		});
		mostrarDatos.setBounds(10, 90, 300 , 50);
		frame.add(mostrarDatos);
		
		
		JButton aceptar= new JButton("Aceptar");
		aceptar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {
				if(banderaMostrar){
					
					//Creamos la factura
					int id_cli=Integer.parseInt(cliente.getText());
					if(clientesArray.size()>=id_cli){
						id_cli--;
						if(totalFactura==0){
							JOptionPane.showMessageDialog(jfM, "Pulse el botón mostrar datos de factura", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else{
							frame.setVisible(false);
							Factura factura= new Factura(ticket_factura,(Clientes)clientesArray.get(id_cli), (facturaArray.size()+1),totalFactura);
							//añadimos la factura
							facturaArray.add(factura);
							actualizarfacturas();
							JOptionPane.showMessageDialog(jfM, "Facturas actualizadas", "INFO", JOptionPane.INFORMATION_MESSAGE);
						}
						
					}else{
						JOptionPane.showMessageDialog(jfM, "Error, no hay cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					//System.out.println("Primero pulse el boton mostrar para saber la cantidad de ticket que se le incluiran en la factura");
					JOptionPane.showMessageDialog(jfM, "Primero pulse el boton mostrar para saber la cantidad de ticket que se le incluiran en la factura", "ERROR", JOptionPane.ERROR_MESSAGE);
				}				
			}
			
		});
		aceptar.setBounds(10, 700, 770, 40);
		frame.add(aceptar);
		frame.setVisible(true);
	}
	public void NuevoTicket()
	{
		cadenaProductosTicket="";
		ArrayList productosTicket= new ArrayList();
		
		JFrame frame = new JFrame("TPV");
		frame.getContentPane().setLayout(null);
		frame.setBounds(500, 200, 800, 800);
		Container contentPane =frame.getContentPane();
		
		JLabel cliente_texto= new JLabel("cliente(ID)");
		cliente_texto.setBounds(30, 25, 139,23);
		frame.add(cliente_texto);
		JTextField cliente = new JTextField();
		cliente.setBounds(230, 25, 139,23);
		frame.add(cliente);
		
		
		
		JTextArea area_Buscador_Cliente= new JTextArea();
		
		area_Buscador_Cliente.setBounds(50, 100, 300, 300);
		area_Buscador_Cliente.setAutoscrolls(true);
    	frame.add(area_Buscador_Cliente);
    	
    	JTextField Buscador_cliente_texto= new JTextField();
		Buscador_cliente_texto.setBounds(30, 55, 139,23);
		frame.add(Buscador_cliente_texto);
		Buscador_cliente_texto.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyReleased(KeyEvent arg0) {
				 String cadena= "ID   "+ "Nombre\n";
		         	Clientes c;
					Iterator<Clientes> it = clientesArray.iterator();
					
					String buscador;
					if(Buscador_cliente_texto.getText().length()==0){
						
						buscador="asdasdasdasdasdkiosdhaslkdnasd";
						cadena="No se encuentra ningún producto con esa búsqueda";
					}else{
						buscador=Buscador_cliente_texto.getText();
					}
					while(it.hasNext())
					{
						c=it.next();
						if(c.getEstado())
						{
							if(c.getNombre().indexOf(buscador)!=-1)
							{
								cadena=cadena+c.getId() +"   "+  c.getNombre()+"\n";
							}
						}
					}
					area_Buscador_Cliente.setText(cadena);
			}
		});
    	
    	area_Productos = new JTextArea();
    	area_Productos.setBounds(450, 100, 300, 300);
    	area_Productos.setAutoscrolls(true);
    	frame.add(area_Productos);
    	String cadenaProdu="Codigo\t Deno\tPrecio\t Cant\n ";
    	JButton Nuevo_producto= new JButton("Producto nuevo");
    	Nuevo_producto.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				JFrame ventana2 = new JFrame("TPV");
				ventana2.getContentPane().setLayout(null);
				ventana2.setBounds(500, 200, 800, 800);
				
				JLabel producto_texto= new JLabel("producto(ID)");
				producto_texto.setBounds(30, 25, 139,23);
				ventana2.add(producto_texto);
				
				JTextField producto = new JTextField();
				producto.setBounds(230, 25, 139,23);
				ventana2.add(producto);
				
				JTextArea area_Buscador_Producto= new JTextArea();
				
				area_Buscador_Producto.setBounds(50, 100, 300, 300);
				area_Buscador_Producto.setAutoscrolls(true);
				ventana2.add(area_Buscador_Producto);
				area_Buscador_Producto.setText("Productos");
				
				JTextField Buscador_producto = new JTextField();
				Buscador_producto.setBounds(530, 25, 139,23);
				ventana2.add(Buscador_producto);
				Buscador_producto.addKeyListener(new KeyAdapter() {
					@Override
					
					public void keyReleased(KeyEvent arg0) {
						
						 String cadena= "ID   "+ "Nombre\n";
				         	Productos c;
							Iterator<Productos> it = productosArray.iterator();
							
							String buscador;
							if(Buscador_producto.getText().length()==0){
								
								buscador="asdasdasdasdasdkiosdhaslkdnasd";
								cadena="No se encuentra ningún producto con esa búsqueda";
							}else{
								buscador=Buscador_producto.getText();
							}
							while(it.hasNext())
							{
								c=it.next();
								if(c.getEstado())
								{
									
									if(c.getDescripcion().indexOf(buscador)!=-1)
									{
										cadena=cadena+ c.getCodigo()+"    " +c.getDescripcion() + c.getcantidadStock() +"\n";
									}
								
								}
							}
							area_Buscador_Producto.setText(cadena);
					}
				});
				
				JLabel cantidad_texto= new JLabel("Cantidad");
				cantidad_texto.setBounds(30, 55, 139,23);
				ventana2.add(cantidad_texto);
				
				JTextField cantidad = new JTextField();
				cantidad.setBounds(230, 55, 139,23);
				ventana2.add(cantidad);
				

				JButton aceptar= new JButton("Aceptar");
				aceptar.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent arg0) 
					{
						
						Productos productoTicket ;
						cantidad c;
						
						int IdProducto = Integer.parseInt(producto.getText());
						int cantidadProducto= Integer.parseInt(cantidad.getText());
						
						if(productosArray.size()>= IdProducto && cantidadProducto>0)
						{
							
							productoTicket=(Productos) productosArray.get(IdProducto-1);
							if(productoTicket.setDisminucionStock(cantidadProducto))
							{
								
								c= new cantidad(cantidadProducto);
								
								productosTicket.add(productoTicket);
								productosTicket.add(c);
								cadenaProductosTicket=cadenaProductosTicket+productoTicket.getCodigo()+"\t"+productoTicket.getDescripcion()+"    \t "+productoTicket.getPrecioConIva()+"\t"+cantidadProducto +"\n ";
								
							}
							else
							{
								JOptionPane.showMessageDialog(jfM, "No hay cantidad en stock suficiente", "ERROR", JOptionPane.ERROR_MESSAGE);
								//System.out.println("No hay cantidad en stock suficiente");						
							}
							
						}
						else
						{
							//error no existe producto p cantidad menor que 0
							if(cantidadProducto<=0){
								JOptionPane.showMessageDialog(jfM, "La cantidad es menor que 1", "ERROR", JOptionPane.ERROR_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(jfM, "El producto no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							
						}
						area_Productos.setText(cadenaProdu+cadenaProductosTicket);
						ventana2.setVisible(false);
						
					}
				});
				aceptar.setBounds(10, 700, 770, 40);
				ventana2.add(aceptar);
				ventana2.setVisible(true);
			}
		});
    	Nuevo_producto.setBounds(550, 20, 180, 40);
		frame.add(Nuevo_producto);
		
		JButton aceptar= new JButton("Aceptar");
		aceptar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				
				Clientes clienteTicket ;
				int IdCliente = Integer.parseInt(cliente.getText());
				
				if(clientesArray.size()> IdCliente-1)
				{
					Ticket ticket= new Ticket((ticketArray.size()+1), (Clientes) clientesArray.get(IdCliente-1));
					ticket.productos=productosTicket;
					ticketArray.add(ticket);
					
					//areaTicket
					actualizarTickets();
					JOptionPane.showMessageDialog(jfM, "Ticket actualizados", "INFO", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
				
					JOptionPane.showMessageDialog(jfM, "El cliente no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				frame.setVisible(false);
			}
		});
		aceptar.setBounds(10, 700, 770, 40);
		frame.add(aceptar);
		frame.setVisible(true);
		
	}
	public void mostrarFactura( int indiceFactura)
	{
		JFrame ventana2 = new JFrame("TPV");
		ventana2.getContentPane().setLayout(null);
		ventana2.setBounds(500, 200, 800, 800);
		if(facturaArray.size()>=indiceFactura)
		{
			String cadena="";
		
			ventana2.setVisible(true);
			
			JLabel producto_texto= new JLabel("Factura nº: "+indiceFactura);
			producto_texto.setBounds(300, 25, 390,70);
			producto_texto.setFont(new java.awt.Font("Tahoma", 0, 36)); 
			ventana2.add(producto_texto);
			
			JTextArea area_Factura= new JTextArea();
			area_Factura.setBounds(100, 100, 600,600);
			
			ventana2.add(area_Factura);
			Factura f=(Factura) facturaArray.get(indiceFactura-1);
			cadena=cadena+"Cliente\t NIF\t\n ";
			cadena=cadena+f.cliente.getNombre()+" "+f.cliente.getApellido()+"\t"+f.cliente.getNif()+"\nFecha: "+f.dia+"/"+f.mes+"/"+f.ano+"\n";
			cadena=cadena+"Ticket/s \n\tFecha\tTotal\t\n";
			
			double TotalFactura=0;
			Iterator<Ticket> it = f.ticket.iterator();
			double total=0;
			while(it.hasNext())
			{
				Ticket t=it.next();
				total=0;
				Productos p;
				
				int delimitador=t.productos.size();

				for(int i=0;i<delimitador;i++){
					double precio=((Productos) t.productos.get(i)).getPrecioConIva();
					i++;
					int Cantidad= ((cantidad)t.productos.get(i)).cant;
					
					total=(precio*Cantidad)+total;
				}
				cadena=cadena+"\t"+t.dia+"/"+t.mes+"/"+t.ano+"\t"+total+"\n";
				TotalFactura=total+TotalFactura;
			}
			cadena=cadena+"\t\t\t\tTOTAL FACTURA: "+TotalFactura;			
			area_Factura.setText(cadena);
		
		}else{
			//si NO esta en el array mensaje de error
			JOptionPane.showMessageDialog(jfM, "El tamnaño del ticket es erróneo", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	public void mostrarTicket( int indiceTicket)
	{
		JFrame ventana2 = new JFrame("TPV");
		ventana2.getContentPane().setLayout(null);
		ventana2.setBounds(500, 200, 800, 800);
		if(ticketArray.size()>=indiceTicket)
		{
			String cadena="Código\t Descripción\t Cantidad\t Precio\t IVA\t Importe Total\n";
			//si esta en el array se muestra el frame 
			ventana2.setVisible(true);
			
			JLabel producto_texto= new JLabel("Ticket nº: "+indiceTicket);
			producto_texto.setBounds(300, 25, 190,70);
			producto_texto.setFont(new java.awt.Font("Tahoma", 0, 36)); 
			ventana2.add(producto_texto);
			
			JTextArea area_Ticket= new JTextArea();
			area_Ticket.setBounds(100, 100, 600,600);
			
			ventana2.add(area_Ticket);
			Ticket T;
			Iterator<Ticket> it = ticketArray.iterator();
					
			int contador=0;
			
			while(it.hasNext())
			{
				contador++;
				T=it.next();
				if(contador==indiceTicket){
					//cojo el array de productos del ticket
					Productos p;
					
				 int delimitador=T.productos.size();
				 
				
				 double total=0;
				for(int i=0;i<delimitador;i++){
					
					
					String nombre= ((Productos) T.productos.get(i)).getDescripcion();
					Double precio_con_iva= ((Productos) T.productos.get(i)).getPrecioConIva();
					int iva= ((Productos) T.productos.get(i)).getIva();
					int codigo= ((Productos) T.productos.get(i)).getCodigo();
					i++;//para la cantidad
					//asdasdasd
					int Cantidad= ((cantidad)T.productos.get(i)).cant;
					System.out.println(Cantidad);
					double precio_total_individual=Cantidad*precio_con_iva;
					cadena=cadena+codigo+"\t"+nombre+"\t"+ +Cantidad +"\t"+precio_con_iva+"\t"+iva+"\t"+precio_total_individual+"\n";
					total=precio_total_individual+total;
				}
				cadena=cadena+"\n\n\t\t\t\tTotal ticket:"+total;					
				}
				area_Ticket.setText(cadena);	
			}
			areaTicket.setText(cadena);
		
		}else{
			//si NO esta en el array mensaje de error
			JOptionPane.showMessageDialog(jfM, "El tamnaño del ticket es erróneo", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	public void actualizarProductos()
	{
		 String cadena="Codigo, "+ "Descripcion, Precio unitario\n";
	         	Productos p;
				Iterator<Productos> it = productosArray.iterator();
				while(it.hasNext())
				{
					p=it.next();
					if(p.getEstado())
					{
						cadena=cadena+ p.getCodigo() + " " + p.getDescripcion() + " " + p.getPrecioUnitario()+"\n";
					}
					
				}
				 areaProducto.setText(cadena);
				
	}
	public void actualizarClientes()
	{
		 String cadena="Codigo\t Nombre\n";	      	
	         	Clientes c;
				Iterator<Clientes> it = clientesArray.iterator();
				while(it.hasNext())
				{
					c=it.next();
					if(c.getEstado())
					{
						cadena=cadena+ c.getId()+"\t"+c.getNombre()+"\n";
					}
					
				}
				 areaCliente.setText(cadena);
				
	}
	public void actualizarTickets()
	{
		 String cadena="Cliente  Total\n";
	        
	      	
		 Ticket c;
				Iterator<Ticket> it = ticketArray.iterator();
				while(it.hasNext())
				{
					c=it.next();
					
					cadena=cadena+ c.cliente.getNombre()+ "  Tamaño array  "+(c.productos.size()/2)+"\n";
					
					
				}
				areaTicket.setText(cadena);
				
	}
	public void actualizarfacturas()
	{
		
		 String cadena="Cliente\t Fecha\t  Total\n";
	        
	    
		 Factura c;
				Iterator<Factura> it = facturaArray.iterator();
				while(it.hasNext())
				{
					c=it.next();
					
					cadena=cadena+""+c.cliente.getNombre()+" "+c.cliente.getApellido()+"\t"+c.dia+"/"+c.mes+"/"+c.ano+"\t"+c.total+"\n";

				}
				areaFactura.setText(cadena);
			
	}
}
