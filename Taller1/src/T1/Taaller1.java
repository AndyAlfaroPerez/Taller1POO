package T1;
import java.io.*;
import java.util.Scanner;
public class Taaller13 {
	// Andy Alfaro Perez 21.918.973-7 ICCI

	public static void main(String[] args) throws IOException	 {
		Scanner leido = new Scanner(System.in);
		
		int opcion;
		//Muestra las opciones discpobiles del menu.
		do {
			System.out.println("1) Menu de Usuarios\r\n"
					+ "2) Menu de Analisis\r\n"
					+ "3) Salir");
			
			
			opcion = Integer.valueOf(leido.nextLine());
			// Acceso al menu de usuarios con login
			if (opcion == 1) {
				System.out.print("Usuario: ");
				String usuario = leido.nextLine();
				System.out.print("Contraseña: ");
				String contraseña = leido.nextLine();
				//Lectura de archivos de Usuarios
				File achivo = new File("Usuarios.txt");
				Scanner arch = new Scanner(achivo);
				boolean ticket = false;
				// Divicion por partes de los datos del archivo
				while (arch.hasNextLine()) {
					String linea = arch.nextLine();
					String[] partes = linea.split(";");
					
					String user = partes[0];
					String pass = partes[1];
					
					
					if (usuario.equalsIgnoreCase(user) && contraseña.equals(pass)) {
						ticket = true;
						
					}
				
				} 
			arch.close();
			// Acceso concedido
			if (ticket) {
				System.out.println("\r\n" + "Acceso correcto!" + "\r\n");
			    System.out.println("Bienvenido " + usuario + "!");
			    int opcione;
			
			 // SubMenu del usuario
			    do {
			    	System.out.println("Que deseas realizar?\r\n" + "\r\n" + "1) Registrar actividad.\r\n" + "2) Modificar actividad.\r\n" + "3) Eliminar actividad.\r\n" + "4) Cambiar contraseña.\r\n" + "5) Salir.");
			        
			        opcione = Integer.valueOf(leido.nextLine());
			        
			     // Registro de nueva actividad
			        if (opcione == 1) {

			            System.out.print("Ingrese fecha: ");
			            String fecha = leido.nextLine();

			            System.out.print("Ingrese horas: ");
			            String horas = leido.nextLine();

			            System.out.print("Ingrese actividad: ");
			            String actividad = leido.nextLine();

			         // Escritura en archivo de registros
			            FileWriter leer = new FileWriter("Registros.txt", true);

			            leer.write(usuario + ";" + fecha + ";" + horas + ";" + actividad + "\n");
			            
			            System.out.println("\r\n" + "Actividad registrada correctamente!" + "\r\n");
			            
			            
			            leer.close();

			        }
			    //Salir del submenu 
			    }while (opcione != 5);
			}
			}
		//Salir del menu y cerrar el programa
		}while (opcion != 3);
	}
}