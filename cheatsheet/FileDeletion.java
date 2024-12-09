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
}