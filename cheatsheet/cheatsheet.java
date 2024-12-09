package GestionFicheros;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class GestionFicheros {
//tested
    public static File seleccionFichero() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int seleccion = jfc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile();
        } else {
            return null;
        }
    }
//tested
    public static File seleccionFichero(String descripcion, String extension) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        filtro(jfc,descripcion,extension);
        int seleccion = jfc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile();
        } else {
            return null;
        }
    }
    //tested
public static void filtro(JFileChooser fc,String descripcion, String extension){
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
    fc.setFileFilter(filter);
}



    public static File seleccionFicheroCSV() {
        JFileChooser jfc = new JFileChooser();
        CSVFileFilter csvFilter = new CSVFileFilter();

        jfc.setFileFilter(csvFilter);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = jfc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile();
        } else {
            return null;
        }
    }

    public static boolean anhadirLineaCSV(File fichero, String linea) {
        try {
            if (fichero.exists()) {
                if (fichero.getName().endsWith(".csv")) {
                    FileWriter fw = new FileWriter(fichero, true);
                    fw.write("\n");
                    fw.write(linea);
                    fw.close();
                } else {
                    throw new IllegalArgumentException("No es un fichero CSV");
                }
                return true;
            } else {
                throw new IOException("No existe el fichero");

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return false;

    }

    public static void escribir(File fichero, String mensaje, boolean machacar) {
        // Crear FileWriter con el modo de sobreescritura o de adjuntar
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, !machacar))) {
            // Escribir el mensaje en el archivo
            bw.write(mensaje);
            bw.newLine(); // Añadir una nueva línea después del mensaje


        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public static File crearArchivo(String nombre) {
        File s = seleccionFichero();
        String dir = s.getPath();
        dir += "/" + nombre;
        File f = new File(dir);
        if (f.exists()) {
            String mensaje = String.format(
                    "%s existe\n%s un archivo\n%s permisos de escritura\nTamaño %d bytes\n",
                    f.getName(),
                    f.isFile() ? "Es" : "No es",
                    f.canWrite() ? "Con" : "Sin",
                    f.length());

            System.out.println(mensaje);
            System.out.println("La ruta completa es: " + f.getPath());

            return f;
        } else {
            System.out.println("El fichero/directorio no existe");
            try {
                System.out.println("Se ha creado");
                f.createNewFile();
                return f;
            } catch (Exception e) {
                System.out.println("No se ha podido crear");
                return null;
            }

        }

    }

    public static void mostrar(File fichero) {
        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException fne) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("Error en la lectura del fichero");
        }
    }

    public static boolean borrarCarpeta(File directorio) {

        File[] ficheros = directorio.listFiles();
        for (File f : ficheros) {
            System.out.println(f.getName());
            f.delete();
        }
        try {
          return  directorio.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static void cargarCSVEnTabla(JTable table) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = jfc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = jfc.getSelectedFile();
            if (fichero.getName().endsWith(".csv")) {
                try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                    String line;
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Limpiar la tabla
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        model.addRow(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un fichero CSV.");
            }
        }
    }


}