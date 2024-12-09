package examen;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class GestionFicheros {
    // Método para seleccionar un archivo o directorio.
    public static File seleccionFichero() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int seleccion = jfc.showOpenDialog(null); // Abre el diálogo
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile(); // Retorna el archivo o directorio seleccionado
        } else {
            return null; // Retorna null si se cancela la selección
        }
    }

    // Método para seleccionar un archivo con filtro personalizado.
    public static File seleccionFichero(String descripcion, String extension) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        filtro(jfc, descripcion, extension); // Aplica el filtro
        int seleccion = jfc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile();
        } else {
            return null;
        }
    }

    // Método auxiliar para aplicar un filtro al JFileChooser.
    public static void filtro(JFileChooser fc, String descripcion, String extension) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(descripcion, extension);
        fc.setFileFilter(filter);
    }

    // Crea un archivo con el nombre especificado en el directorio seleccionado.
    public static File crearArchivo(String nombre) {
        File s = seleccionFichero(); // Selecciona el directorio donde se creará el archivo
        if (s == null) return null; // Si no se seleccionó directorio, retorna null

        String dir = s.getPath() + "/" + nombre; // Construye la ruta completa
        File f = new File(dir);
        if (f.exists()) {
            System.out.printf("El archivo %s ya existe.%n", f.getName());
            return f; // Retorna el archivo existente
        } else {
            try {
                f.createNewFile(); // Crea el archivo
                return f;
            } catch (IOException e) {
                System.out.println("No se pudo crear el archivo.");
                return null;
            }
        }
    }

    // Muestra el contenido de un archivo en la consola.
    public static void mostrar(File fichero) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); // Imprime cada línea del archivo
            }
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
	public static void main(String[] args) {
			
			//String archivo = "alter.txt";
			//crearArchivo(archivo);
			
			//String archivo = "C:\\Users\\Aussar\\Documents\\Downloads\\alter.txt";
			//File archivoM = new File(archivo);
			//mostrar(archivoM);
			
			//File archivoSeleccionado = seleccionFichero("Archivos de texto", "txt");
			
			File archivoSeleccionado = seleccionFichero();

		}
}
