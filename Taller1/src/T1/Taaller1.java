package T1;
import java.io.*;
import java.util.Scanner;
public class Taaller1 {
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
			            //Modificar una actividad
			        }if (opcione == 2) {
			        	// Lectura de registros
			        	Scanner leerR = new Scanner(new File("Registros.txt"));

						String[] registros = new String[300];
						int contador = 0;

						System.out.println("Cual actividad deseas modificar?\r\n" + "0) Regresar.\r\n");

						// Filtrar actividades del usuario logueado
						while (leerR.hasNextLine()) {
							String linea = leerR.nextLine();
							String[] partes = linea.split(";");

							String user = partes[0];

							if (usuario.equalsIgnoreCase(user)) {
								registros[contador] = linea;
								System.out.println((contador + 1) + ") " + linea);
								contador++;
							}
						}

						leerR.close();

						int seleccion = Integer.valueOf(leido.nextLine());

						if (seleccion == 0) {

						} else {
							// Submenu de modificacion
							System.out.println("Que deseas modificar?\r\n" + "\r\n" + "0) Regresar.\r\n" + "1) Fecha\r\n" + "2) Duracion\r\n" + "3) Tipo de actividad");

							int numeroopcion = Integer.valueOf(leido.nextLine());
							// Obtener actividad seleccionada
							String lineaSeleccionada = registros[seleccion - 1];
							String[] partes = lineaSeleccionada.split(";");
							// Modificar campo seleccionado
							if (numeroopcion == 1) {
								System.out.print("Ingrese nueva fecha: ");
								partes[1] = leido.nextLine();
							}

							if (numeroopcion == 2) {
								System.out.print("Ingrese nueva duracion: ");
								partes[2] = leido.nextLine();
							}

							if (numeroopcion == 3) {
								System.out.print("Ingrese nuevo tipo de actividad: ");
								partes[3] = leido.nextLine();
							}
							// Reconstruccion de la linea 
							String nuevaLinea = partes[0] + ";" + partes[1] + ";" + partes[2] + ";" + partes[3];

							Scanner leerTodo = new Scanner(new File("Registros.txt"));
							String[] todas = new String[300];
							int total = 0;

							while (leerTodo.hasNextLine()) {
								todas[total] = leerTodo.nextLine();
								total++;
							}
							leerTodo.close();
							// Buscar indice real en el archivo
							int indice = -1;
							int cont = 0;

							for (int i = 0; i < total; i++) {
								String[] p = todas[i].split(";");

								if (usuario.equalsIgnoreCase(p[0])) {
									cont++;
									if (cont == seleccion) {
										indice = i;
									}
								}
							}
							// Reemplazar linea modificada
							if (indice != -1) {
								todas[indice] = nuevaLinea;
							}
							// Rescribir archivo actualizado
							FileWriter fw = new FileWriter("Registros.txt");

							for (int i = 0; i < total; i++) {
								fw.write(todas[i] + "\n");
							}

							fw.close();

							System.out.println("Actividad modificada con exito!");
						}
					}if (opcione == 3) {
						//Luego XD
						System.out.println("Ya se hara");
					//Modificar contraseña
					}if (opcione == 4) {
						
			        	System.out.print("Ingrese nueva contraseña: ");
						String nuevaPass = leido.nextLine();
						//Abrir archivo
						Scanner usuarioLeer = new Scanner(new File("Usuarios.txt"));
						String[] usuarios = new String[100];
						int total = 0;

						while (usuarioLeer.hasNextLine()) {
							usuarios[total] = usuarioLeer.nextLine();
							total++;
						}

						usuarioLeer.close();
						// Actualizar contraseña del usuario
						for (int i = 0; i < total; i++) {
							String[] partes = usuarios[i].split(";");

							String user = partes[0];

							if (usuario.equalsIgnoreCase(user)) {
								usuarios[i] = partes[0] + ";" + nuevaPass;
							}
						}
						// Reescribir archivo
						FileWriter fw = new FileWriter("Usuarios.txt");

						for (int i = 0; i < total; i++) {
							fw.write(usuarios[i] + "\n");
						}

						fw.close();
						System.out.println("Contraseña cambiada con exito!");
			        }
					
			    //Salir del submenu 
			    }while (opcione != 5);
			} else {
				// Mensaje si las credenciales son incorrectas
				System.out.println("Usuario o Contraseña incorecta.");
			}
		}
		if (opcion == 2) {

			int opcione2;
			// Submenu de analisis
			do {
				System.out.println("Bienvenido al menu de analisis!\r\n" + "\r\n" + "Que deseas realizar?\r\n" + "\r\n" + "1) Actividad más realizada\r\n" + "2) Actividad más realizada por cada usuario\r\n" + "3) Usuario con mayor procastinacion\r\n" + "4) Ver todas las actividades\r\n" + "5) Salir");
				opcione2 = Integer.valueOf(leido.nextLine());
				// Calcular actividad más repetida
				if (opcione2 == 1) {
					Scanner leer = new Scanner(new File("Registros.txt"));

					String[] actividades = new String[300];
					int total = 0;
					// Lectura de actividades 
					while (leer.hasNextLine()) {
						String linea = leer.nextLine();
						String[] partes = linea.split(";");

						actividades[total] = partes[3];
						total++;
					}
					leer.close();

					int max = 0;
					String repetida = "";
					// Conteo de frecuencia de actividades
					for (int i = 0; i < total; i++) {

						int contador = 0;

						for (int j = 0; j < total; j++) {
							if (actividades[i].equalsIgnoreCase(actividades[j])) {
								contador++;
							}
						}

						if (contador > max) {
							max = contador;
							repetida = actividades[i];
						}
					}
					System.out.println("Actividad más realizada:");
					//Mostrar resultado 
					System.out.println(repetida + " -> " + max + " veces");

				}
				if (opcione2 == 2) {
					System.out.println("Hacer despues");
				}
				if (opcione2 == 3) {
					System.out.println("Hacer despues xd");
				}
				// Mostrar todas las actividades
				if (opcione2 == 4) {

					File archivo = new File("Registros.txt");
					Scanner leer = new Scanner(archivo);
					// Lectura e impresion 
					while (leer.hasNextLine()) {
						String linea = leer.nextLine();
						String[] partes = linea.split(";");

						System.out.println(partes[0] + " | " + partes[1] + " | " + partes[2] + " horas | " + partes[3]);
					}
					leer.close();
				}
			} while (opcione2 != 5);
		}    
		//Salir del menu y cerrar el programa
		}while (opcion != 3);
	}
}