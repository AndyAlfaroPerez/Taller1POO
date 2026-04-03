package T1;
import java.io.*;
import java.util.Scanner;
public class Taaller1 {
	// Andy Alfaro Perez 21.918.973-7 ICCI

	public static void main(String[] args) throws IOException	 {
		Scanner leido = new Scanner(System.in);
		int opcion= 0;
		boolean valido = false;
		//Muestra las opciones discpobiles del menu.
		do {
			valido = false;
			System.out.println("1) Menu de Usuarios\r\n" + "2) Menu de Analisis\r\n"	+ "3) Salir");
			try {
		        opcion = Integer.valueOf(leido.nextLine());

		        if (opcion >= 1 && opcion <= 3) {
		            valido = true;
		        } else {
		            System.out.println("Error: opcion invalida.");
		        }

		    } catch (Exception e) {
		        System.out.println("Error: debe ingresar un numero.");
		    }

			// Acceso al menu de usuarios con login
			if (opcion == 1) {

			    boolean acceso = false;

			    while (!acceso) {
			        System.out.print("Usuario: ");
			        String usuario = leido.nextLine();
			        System.out.print("Contraseña: ");
			        String contraseña = leido.nextLine();
			        Scanner arch = new Scanner(new File("Usuarios.txt"));

			        boolean ticket = false;

			        while (arch.hasNextLine()) {
			            String linea = arch.nextLine();
			            String[] partes = linea.split(";");

			            if (usuario.equals(partes[0]) && contraseña.equals(partes[1])) {
			                ticket = true;
			            }
			        }

			        arch.close();

			        if (ticket) {

			            acceso = true;

			            System.out.println("\r\nAcceso correcto!\r\n");
			            System.out.println("Bienvenido " + usuario + "!");

			            int opcione;
			
			 // SubMenu del usuario
			    do {
			    	System.out.println("Que deseas realizar?\r\n" + "\r\n" + "1) Registrar actividad.\r\n" + "2) Modificar actividad.\r\n" + "3) Eliminar actividad.\r\n" + "4) Cambiar contraseña.\r\n" + "5) Salir.");
			        
			        opcione = Integer.valueOf(leido.nextLine());
			        
			     // Registro de nueva actividad
			        if (opcione == 1) {

			        	String fecha = "";
			        	boolean fechaValida = false;

			        	while (!fechaValida) {
			        	    System.out.print("Ingrese fecha (dd/MM/yyyy): ");
			        	    fecha = leido.nextLine();

			        	    if (fecha.length() == 10 && fecha.charAt(2) == '/' && fecha.charAt(5) == '/') {
			        	    	try {

			        	    	    fechaValida = true;

			        	    	} catch (Exception e) {
			        	    	    System.out.println("Error: la fecha debe contener numeros.");
			        	    	}
			        	    } else {
			        	        System.out.println("Error: formato incorrecto. Use dd/MM/yyyy.");
			        	    }
			        	}

//			            System.out.print("Ingrese horas: ");
//			            String horas = leido.nextLine();
			            int horas = 0;
			            boolean horasValidas = false;

			            while (!horasValidas) {
			                System.out.print("Ingrese horas: ");
			                
			                try {
			                    horas = Integer.valueOf(leido.nextLine());

			                    if (horas > 0) {
			                        horasValidas = true;
			                    } else {
			                        System.out.println("Error: las horas deben ser positivo.");
			                    }

			                } catch (Exception e) {
			                    System.out.println("Error: debe ingresar un numero/no debe ser vacio.");
			                }
			                
			            }

			            String actividad = "";
			            boolean actividadValida = false;
			            
			            while (!actividadValida) {
			                System.out.print("Ingrese actividad: ");
			                actividad = leido.nextLine();

			                if (!actividad.equals("")) {
			                    actividadValida = true;
			                } else {
			                    System.out.println("Error: la actividad no puede estar vacia.");
			                }
			            }

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

						int seleccion = 0;
						boolean seleccionValida = false;
						
						while (!seleccionValida) {
						    try {
						        seleccion = Integer.valueOf(leido.nextLine());

						        if (seleccion == 0) {
						            seleccionValida = true;
						        } else if (seleccion >= 1 && seleccion <= contador) {
						            seleccionValida = true;
						        } else {
						            System.out.println("Error: opcion fuera de rango.");
						        }

						    } catch (Exception e) {
						        System.out.println("Error: debe ingresar un numero.");
						    }
						}

						if (seleccion == 0) {

						} else {
							// Submenu de modificacion
							System.out.println("Que deseas modificar?\r\n" + "\r\n" + "0) Regresar.\r\n" + "1) Fecha\r\n" + "2) Duracion\r\n" + "3) Tipo de actividad");

							int numeroopcion = 0;
							boolean opcionValida = false;

							while (!opcionValida) {
							    try {
							        numeroopcion = Integer.valueOf(leido.nextLine());

							        if (numeroopcion >= 0 && numeroopcion <= 3) {
							            opcionValida = true;
							        } else {
							            System.out.println("Error: opcion fuera de rango.");
							        }

							    } catch (Exception e) {
							        System.out.println("Error: debe ingresar un numero.");
							    }
							}
							// Obtener actividad seleccionada
							String lineaSeleccionada = registros[seleccion - 1];
							String[] partes = lineaSeleccionada.split(";");
							// Modificar campo seleccionado
							if (numeroopcion == 1) {
							
								    boolean fechaValida = false;

								    while (!fechaValida) {
								        System.out.print("Ingrese nueva fecha (dd/MM/yyyy): ");
								        partes[1] = leido.nextLine();

								        if (partes[1].length() == 10 && partes[1].charAt(2) == '/' && partes[1].charAt(5) == '/') {
								        	try {

								        	    fechaValida = true;

								        	} catch (Exception e) {
								        	    System.out.println("Error: la fecha debe contener numeros.");
								        	}
								        } else {
								            System.out.println("Error: formato incorrecto. Use dd/MM/yyyy.");
								        }
								    }

								}

							if (numeroopcion == 2) {
								int horas = 0;
							    boolean horasValidas = false;

							    while (!horasValidas) {
							        System.out.print("Ingrese nueva duracion: ");

							        try {
							            horas = Integer.valueOf(leido.nextLine());

							            if (horas > 0) {
							                horasValidas = true;
							            } else {
							                System.out.println("Error: debe ser un numero positivo.");
							            }

							        } catch (Exception e) {
							            System.out.println("Error: debe ingresar un numero.");
							        }
							    }
							    partes[2] = String.valueOf(horas);

							}

							if (numeroopcion == 3) {
							    boolean actividadValida = false;

							    while (!actividadValida) {
							        System.out.print("Ingrese nuevo tipo de actividad: ");
							        partes[3] = leido.nextLine();

							        if (!partes[3].trim().equals("")) {
							            actividadValida = true;
							        } else {
							            System.out.println("Error: la actividad no puede estar vacia.");
							        }
							    }

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
						//Eliminar una actividad
					}if (opcione == 3) {
						//Lectura
						File Registro = new File("Registros.txt");
						Scanner leerR = new Scanner(Registro);

						String[] registros = new String[300];
						int contador = 0;

						System.out.println("Cual actividad deseas eliminar?\r\n" + "0) Regresar.");
						// Filtrar actividades del usuario
						while (leerR.hasNextLine()) {
							String linea = leerR.nextLine();
							String[] partes = linea.split(";");

							String user = partes[0];

							if (usuario.equalsIgnoreCase(user)) {
								registros[contador] = linea;
								//Imprimir cada actividad 
								System.out.println((contador + 1) + ") " + linea);
								contador++;
							}
						}

						leerR.close();

						int seleccion = 0;
						boolean seleccionValida = false;
						while (!seleccionValida) {
						    try {
						        seleccion = Integer.valueOf(leido.nextLine());

						        if (seleccion == 0) {
						            seleccionValida = true;
						        } else if (seleccion >= 1 && seleccion <= contador) {
						            seleccionValida = true;
						        } else {
						            System.out.println("Error: opcion fuera de rango.");
						        }

						    } catch (Exception e) {
						        System.out.println("Error: debe ingresar un numero.");
						    }
						}
						// Volver al submenu 
						if (seleccion == 0) {
							
						} else {

							Scanner leerTodo = new Scanner(new File("Registros.txt"));
							String[] todas = new String[300];
							int total = 0;
							while (leerTodo.hasNextLine()) {
								todas[total] = leerTodo.nextLine();
								total++;
							}
							leerTodo.close();
							// Buscar indice
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
							// Eliminar desplazando elementos
							if (indice != -1) {

								for (int i = indice; i < total - 1; i++) {
									todas[i] = todas[i + 1];
								}
								total--;
							}
							// Reescribir archivo
							FileWriter fw = new FileWriter("Registros.txt");

							for (int i = 0; i < total; i++) {
								fw.write(todas[i] + "\n");
							}
							fw.close();

							System.out.println("Actividad eliminada con exito!");
						}
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
					
			    //Salir del submenu de usuario 
			    }while (opcione != 5);
			    	System.out.println("Saliendo del Menu de Usuario...\r\n");
			} else {
				// Mensaje si las credenciales son incorrectas
				System.out.println("Usuario o Contraseña incorecta.");
			}
		} while (!acceso);
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
				//Actividad mas realizada por cada usuario
				if (opcione2 == 2) {


					    Scanner leer = new Scanner(new File("Registros.txt"));

					    // Arreglos por usuario
					    String[] actividadesMartin = new String[300];
					    String[] actividadesCatalina = new String[300];
					    String[] actividadesEstefania = new String[300];

					    int contadorMartin = 0;
					    int contadorCatalina = 0;
					    int contadorEstefania = 0;

					    // Separar actividades por usuario
					    while (leer.hasNextLine()) {
					        String linea = leer.nextLine();
					        String[] partes = linea.split(";");

					        String user = partes[0];
					        String actividad = partes[3];

					        if (user.equalsIgnoreCase("Martin")) {
					            actividadesMartin[contadorMartin] = actividad;
					            contadorMartin++;
					        }

					        if (user.equalsIgnoreCase("Catalina")) {
					            actividadesCatalina[contadorCatalina] = actividad;
					            contadorCatalina++;
					        }

					        if (user.equalsIgnoreCase("Estefania")) {
					            actividadesEstefania[contadorEstefania] = actividad;
					            contadorEstefania++;
					        }
					    }

					    leer.close();

					    int maximoMartin = 0;
					    String actividadMasRepetidaMartin = "";
					    // Calcular actividad mss repetida (De cada usuario)
					    for (int i = 0; i < contadorMartin; i++) {
					        int contadorRepeticiones = 0;

					        for (int j = 0; j < contadorMartin; j++) {
					            if (actividadesMartin[i].equalsIgnoreCase(actividadesMartin[j])) {
					                contadorRepeticiones++;
					            }
					        }

					        if (contadorRepeticiones > maximoMartin) {
					            maximoMartin = contadorRepeticiones;
					            actividadMasRepetidaMartin = actividadesMartin[i];
					        }
					    }

					    int maximoCatalina = 0;
					    String actividadMasRepetidaCatalina = "";

					    for (int i = 0; i < contadorCatalina; i++) {
					        int contadorRepeticiones = 0;

					        for (int j = 0; j < contadorCatalina; j++) {
					            if (actividadesCatalina[i].equalsIgnoreCase(actividadesCatalina[j])) {
					                contadorRepeticiones++;
					            }
					        }

					        if (contadorRepeticiones > maximoCatalina) {
					            maximoCatalina = contadorRepeticiones;
					            actividadMasRepetidaCatalina = actividadesCatalina[i];
					        }
					    }

					    int maximoEstefania = 0;
					    String actividadMasRepetidaEstefania = "";

					    for (int i = 0; i < contadorEstefania; i++) {
					        int contadorRepeticiones = 0;

					        for (int j = 0; j < contadorEstefania; j++) {
					            if (actividadesEstefania[i].equalsIgnoreCase(actividadesEstefania[j])) {
					                contadorRepeticiones++;
					            }
					        }

					        if (contadorRepeticiones > maximoEstefania) {
					            maximoEstefania = contadorRepeticiones;
					            actividadMasRepetidaEstefania = actividadesEstefania[i];
					        }
					    }

					    // Mostrar resultados
					    System.out.println("Actividades mas realizadas por cada usuario:\r\n");
					    System.out.println("* Martin -> " + actividadMasRepetidaMartin + " -> con " + maximoMartin + " registros");
					    System.out.println("* Catalina -> " + actividadMasRepetidaCatalina + " -> con " + maximoCatalina + " registros");
					    System.out.println("* Estefania -> " + actividadMasRepetidaEstefania + " -> con " + maximoEstefania + " registros");
					}
					
				//Mayor procrastinacion.
				if (opcione2 == 3) {
					// Lectura del archivo
					Scanner leer = new Scanner(new File("Registros.txt"));

					// Acumuladores de horas 
					int horasMartin = 0;
					int horasCatalina = 0;
					int horasEstefania = 0;

					while (leer.hasNextLine()) {
					    String linea = leer.nextLine();
					    String[] partes = linea.split(";");
					    String user = partes[0];
					    int horas = 0;

					    try {
					        horas = Integer.valueOf(partes[2]);
					    // si hay error se ignora
					    } catch (Exception e) {
					        continue;
					    }
					    // Sumar horas segun usuario
					    if (user.equalsIgnoreCase("Martin")) {
					        horasMartin += horas;
					    }

					    if (user.equalsIgnoreCase("Catalina")) {
					        horasCatalina += horas;
					    }

					    if (user.equalsIgnoreCase("Estefania")) {
					        horasEstefania += horas;
					    }
					}

					leer.close();
					// Determinar quien tiene mas horas
					String usuarioMayor = "";
					int maxHoras = 0;

					if (horasMartin > maxHoras) {
					    maxHoras = horasMartin;
					    usuarioMayor = "Martin";
					}

					if (horasCatalina > maxHoras) {
					    maxHoras = horasCatalina;
					    usuarioMayor = "Catalina";
					}

					if (horasEstefania > maxHoras) {
					    maxHoras = horasEstefania;
					    usuarioMayor = "Estefania";
					}
					// Mostrar resultado
					System.out.println("Usuario con mayor procastinacion:");
					System.out.println(usuarioMayor + " -> con " + maxHoras + " horas registradas");
				}
				// Mostrar todas las actividadex
				if (opcione2 == 4) {

					Scanner leer = new Scanner(new File("Registros.txt")); // Se cambio para mas orden
					// Lectura e impresion 
					while (leer.hasNextLine()) {
						String linea = leer.nextLine();
						String[] partes = linea.split(";");
						System.out.println(partes[0] + " | " + partes[1] + " | " + partes[2] + " horas | " + partes[3]);
					}
					leer.close();
				}
				//salir del subemenu de analisis
			} while (opcione2 != 5);
			System.out.println("Saliendo del Menu de Analisis..." + " \r\n");
		}    
		//Salir del menu y cerrar el programa
		}while (opcion != 3);
		System.out.println("Saliendo del programa...");
	}
}