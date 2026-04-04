# Sistema de Registro de Actividades

## Descripción del proyecto
Aplicación desarrollada en Java que permite gestionar usuarios y registrar actividades diarias utilizando archivos de texto como almacenamiento persistente.
El sistema valida el acceso mediante un archivo llamado `Usuarios.txt`, donde se almacenan los usuarios y sus contraseñas. Una vez autenticado, el usuario puede interactuar con el sistema a través de un menú en consola que permite registrar nuevas actividades, modificarlas, eliminarlas y cambiar su contraseña.
Las actividades se almacenan en el archivo `Registros.txt`, donde cada registro contiene usuario, fecha, duración en horas y tipo de actividad.
Además, el sistema incluye un menú de análisis que permite obtener información relevante a partir de los datos registrados.

El sistema incorpora validación de datos de entrada para evitar errores, como campos vacíos, formatos incorrectos o ingreso de valores no numéricos.

---

## Integrantes
Andy Alfaro Perez  
RUT: 21.918.973-7  
GitHub: AndyAlfaroPerez

---

## Estructura del proyecto

T1/Taaller1.java  
Contiene la lógica completa del sistema, incluyendo:

- Menú principal
- Sistema de login de usuarios
- Menú de gestión de actividades (registrar, modificar, eliminar, Cambio de contraseña)
- Menú de análisis de datos (ver actividades)
- Lectura de archivos mediante Scanner
- Escritura y actualización de datos mediante FileWriter

Usuarios.txt  
Archivo que almacena las credenciales en formato:  
usuario;contraseña  

Registros.txt  
Archivo que almacena las actividades en formato:  
usuario;fecha;horas;actividad  

---

## Instrucciones de ejecución

Tener instalado Java (JDK).  

Clonar o descargar el proyecto.  

Verificar que los archivos `Usuarios.txt` y `Registros.txt` se encuentren en la misma carpeta del programa.  

Compilar el programa:
javac T1/Taaller1.java  

Ejecutar el programa:
java T1.Taaller1  

Interactuar con el sistema mediante el menú en consola.

