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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class GuiJava implements ActionListener{//implementando el listener de eventos
 
    JPanel PanelProductos, PanelClientes, PanelVentas;
    JButton jb1, jb2, jb3, jbP1, jbP2, jbP3;   
    JMenu productosMenu;
    JFrame jfM;
    ArrayList  productosArray, clientesArray, ticketArray;
    JTextArea areaProducto, areaCliente,areaTicket;
    JTextArea area_Productos;
    String rutaArchivo="C:/Users/panzer/Desktop/eclipseProyectos/tpv_uned_Roberto_Velez/";
    String cadenaProductosTicket;
    
    public GuiJava(){
    	productosArray= new ArrayList();
    	clientesArray=new ArrayList();
    	ticketArray=new ArrayList();
        jfM = new JFrame("JPanel En Java");  
        jfM.setLayout(null);
 
        productos(); clientes(); ventas(); //invocamos los metodos que contienen los paneles 
        PanelProductos.setVisible(false);
        PanelProductos.setLayout(null);
        PanelClientes.setVisible(false);
        PanelClientes.setLayout(null);
        PanelVentas.setLayout(null);
        PanelVentas.setVisible(false);
        
        
  
        PanelProductos.setBounds(0, 0, 1200, 1200); PanelClientes.setBounds(0, 0, 1200, 1200); PanelVentas.setBounds(0, 0, 1200, 1200); 
        
        jfM.add(PanelProductos); jfM.add(PanelClientes); jfM.add(PanelVentas); 
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
			}
		});
	
		menu.add(cliente);
		JMenu facturacion=new JMenu ("facturacion");
		facturacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//////////////////////
			}
		});
	
		menu.add(facturacion);
	
		
		jfM.setExtendedState(jfM.MAXIMIZED_BOTH);
    }
 
    public void productos(){
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
			}
		});
		
		PanelProductos.add(importar);
    	
    	JButton exportar= new JButton("exportar");
		exportar.setBounds(120, 25, 109, 23);
		exportar.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0) {			
				exportarProducto();				
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
				}
				else
				{
					JOptionPane.showMessageDialog(jfM, "Error en la eliminación del producto", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				actualizarProductos();
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
				modificarProducto(modi.getText());
				actualizarProductos();
			}
		});
		PanelProductos.add(modi);
		PanelProductos.add(modificar);
		
		
		
        PanelProductos.setVisible(true);
    }
    public void clientes()
    {
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
				modificarCliente(modi.getText());
				actualizarClientes();
			}
		});
		PanelClientes.add(modi);
		PanelClientes.add(modificar);
		
				
				
		
    }
    public void ventas()
    {
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
			}
		});
		PanelVentas.add(nuevoTicket);
    }
 
 
 
    public static void main(String[] args) {        
        GuiJava gj = new GuiJava();//uso de constructor para la ventana
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	public void exportarProducto()
	{
		//exporta el arraylist de productos
		   FileOutputStream fichero = null;
	        ObjectOutputStream salida = null;
	        Productos p;
	        cantidad c;
	        
	    
	        String continuar="si";
			
			 try {
				
				 String ruta =rutaArchivo+"Productos.txt";
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
		            System.out.println(e.getMessage());
		        } catch (IOException e) {
		            System.out.println(e.getMessage());
		        } finally {
		            try {
		                if(fichero!=null) fichero.close();
		                if(salida!=null) salida.close();
		            } catch (IOException e) {
		                System.out.println(e.getMessage());
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
			
			 String ruta=rutaArchivo+"Clientes.txt";
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
	            System.out.println(e.getMessage());
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            try {
	                if(fichero!=null) fichero.close();
	                if(salida!=null) salida.close();
	            } catch (IOException e) {
	                System.out.println(e.getMessage());
	            }
	        }
		 actualizarClientes();
	}
	public void importarProducto()
	{
		
		 String ruta=rutaArchivo+"Productos.txt";
		
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
           System.out.println(e.getMessage());
          
       } catch (ClassNotFoundException e) {
           System.out.println(e.getMessage());
          
       } catch (IOException e) {
           System.out.println(e.getMessage());
       } finally {
           try {
               if (ficheroLeer != null) {
               	ficheroLeer.close();
               }
               if (entrada != null) {
                   entrada.close();
               }
           } catch (IOException e) {
               System.out.println(e.getMessage());
               
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
			
			 String ruta=rutaArchivo+"Tickets.txt";
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
	            System.out.println(e.getMessage());
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            try {
	                if(fichero!=null) fichero.close();
	                if(salida!=null) salida.close();
	            } catch (IOException e) {
	                System.out.println(e.getMessage());
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
        	String ruta=rutaArchivo+"Clientes.txt";
       	 
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
            System.out.println(e.getMessage());
           
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
           
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (ficheroLeer != null) {
                	ficheroLeer.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                
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
        	String ruta=rutaArchivo+"Tickets.txt";
       	 
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
            System.out.println(e.getMessage());
           
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
           
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (ficheroLeer != null) {
                	ficheroLeer.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                
            }
        }
        actualizarTickets();
	}
	public void modificarProducto(String Texto)
	{
		
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
			}
		});
		aceptar.setBounds(120, 250, 100, 50);
		frame.add(aceptar);
		frame.setVisible(true);
	}
	public void NuevoTicket()
	{
		//ticket= new Ticket();
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
			
			public void keyPressed(KeyEvent arg0) {
				 String cadena= "ID   "+ "Nombre\n";
		         	Clientes c;
					Iterator<Clientes> it = clientesArray.iterator();
					while(it.hasNext())
					{
						c=it.next();
						if(c.getEstado())
						{
							if(c.getNombre().indexOf(Buscador_cliente_texto.getText())!=-1)
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
    	String cadenaProdu="Productos\n Denominacion     Precio Cantidad\n ";
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
					
					public void keyPressed(KeyEvent arg0) {
						
						 String cadena= "ID   "+ "Nombre\n";
				         	Productos c;
							Iterator<Productos> it = productosArray.iterator();
							while(it.hasNext())
							{
								c=it.next();
								if(c.getEstado())
								{
									if(c.getDescripcion().indexOf(producto.getText())!=-1)
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
						System.out.println("Aceptar producto");
						Productos productoTicket ;
						cantidad c;
						
						int IdProducto = Integer.parseInt(producto.getText());
						int cantidadProducto= Integer.parseInt(cantidad.getText());
						System.out.println( IdProducto  + "   "+cantidadProducto);
						if(productosArray.size()>= IdProducto && cantidadProducto>0)
						{
							
							productoTicket=(Productos) productosArray.get(IdProducto-1);
							if(productoTicket.setDisminucionStock(cantidadProducto))
							{
								c= new cantidad(cantidadProducto);
								productosTicket.add(productoTicket);
								productosTicket.add(cantidadProducto);
								cadenaProductosTicket=productoTicket.getDescripcion()+"    \t "+productoTicket.getPrecioConIva() +"\n ";
								System.out.println("Todo ok ");
							}
							else
							{
								System.out.println("No hay cantidad en stock suficiente");						
							}
							
						}
						else
						{
							//error no existe producto p cantidad menor que 0
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
				
				if(clientesArray.size()> IdCliente)
				{
					Ticket ticket= new Ticket((ticketArray.size()+1), (Clientes) clientesArray.get(IdCliente-1));
					ticket.productos=productosArray;
					ticketArray.add(ticket);
					
					//areaTicket
					actualizarTickets();
				}
				else
				{
					//error no existe cliente
					System.out.println("Cliente no existe");
				}
				
				frame.setVisible(false);
			}
		});
		aceptar.setBounds(10, 700, 770, 40);
		frame.add(aceptar);
		frame.setVisible(true);
		
	}
	public void actualizarProductos()
	{
		 String cadena="Codigo, "
			 		+ "Descripcion, Precio unitario\n";
	        
	      	
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
		 String cadena="Nombre\n";
	        
	      	
	         	Clientes c;
				Iterator<Clientes> it = clientesArray.iterator();
				while(it.hasNext())
				{
					c=it.next();
					if(c.getEstado())
					{
						cadena=cadena+ c.getNombre()+"\n";
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
					
					cadena=cadena+ c.cliente.getNombre()+"\n";
					
					
				}
				areaTicket.setText(cadena);
	}

}
