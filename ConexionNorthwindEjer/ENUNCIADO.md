PRÁCTICA DE ACCESO A DATOS– “Carga de productos”
 
Se pretende crear un programa  en Java que haga uso del conector JDBC para conectarse con la base de datos Northwind con la que hemos estado trabajando previamente.
 
Requisitos:
-    	Se validarán todas las entradas
-    	Se requieren mensajes de error controlados cuando se produzcan errores de conexión/consulta/inserción en la BD.
-    	Se deben de utilizar al menos dos JFrames, uno para controlar la conexión y otro con la lógica que permite la consulta/inserción de datos en la tabla a partir de un fichero de texto.
-    	Empleo de JFileChooser para cargar/guardar ficheros en el sistema.
 
 
Al arrancar, al programa mostrará al usuario una interfaz que permita establecer la conexión con la base de datos Northwind, haciendo clic en un botón, el usuario final tendrá que establecer:
-    	Dirección del servidor
-    	Puerto
-    	Nombre de usuario
-    	Contraseña (emplear un campo de tipo contraseña).
 
Validaciones:
-    	Todos los campos han de estar cubiertos y el puerto ha de ser un valor numérico.
 
La conexión se establecerá directamente con la BD Northwind y se trabajará con la tabla de productos (products).
 
Una vez pulsado el botón de conexión, se informará mediante un JOptionPane al usuario del estado de la conexión y en caso de que sea exitosa, se mostrará otro JFrame con las siguientes opciones disponibles, mediante la interacción con diferentes botones.
 
-    	Mostrar contenido de la tabla productos: Es opción, cargará en una tabla “scrolleable” el contenido de la tabla de productos.
 
-    	Cargar datos desde un fichero: Se permitirá al usuario seleccionar un fichero TXT  (solamente txt) con los datos de los productos a insertar. El proceso de carga mostrará nuevamente el contenido de la tabla products en el JTable del programa. (Se refrescará la tabla después de la inserción)
Para realizar la inserción, haremos uso de una consulta preparada, asumiendo que el orden de las columnas del fichero de texto, es el correcto.
 
-    	Exportar datos a un fichero: Se permite exportar todos los registros de la tabla de productos a un fichero de texto que el usuario creará a partir del JFileChoose.
 
 
Después de la carga o la exportación se mostrará un mensaje del siguiente estilo:
-    	SE HAN EXPORTADO XXX REGISTROS.
-    	SE HAN IMPORTADO XXX REGISTROS
 
Los ficheros de texto con los que se trabajarán tendrán los valores separados por comas.
