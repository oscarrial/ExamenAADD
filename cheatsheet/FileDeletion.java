package examen;


import java.io.File;

public class FileDeletion {
    // Elimina todos los archivos de un directorio y luego intenta borrar el directorio.
    public static boolean borrarCarpeta(File directorio) {
        if (directorio.isDirectory()) {
            File[] ficheros = directorio.listFiles();
            if (ficheros != null) {
                for (File f : ficheros) {
                    f.delete(); // Elimina cada archivo
                }
            }
        }
        return directorio.delete(); // Intenta eliminar el directorio
    }
    
    public static void main(String[] args) {
        // Ruta del directorio que deseas eliminar
        String rutaDirectorio = "C:\\Users\\Aussar\\Documents\\Downloads\\borrar";

        // Crear un objeto File para el directorio
        File directorio = new File(rutaDirectorio);

        // Llamar al método borrarCarpeta
        boolean exito = FileDeletion.borrarCarpeta(directorio);
        
        if (exito) {
            System.out.println("El directorio y su contenido fueron eliminados correctamente.");
        } else {
            System.out.println("Hubo un problema al intentar eliminar el directorio.");
        }
    }
}
