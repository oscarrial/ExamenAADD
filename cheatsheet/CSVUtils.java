package examen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class CSVUtils {
	
	
    // Selecciona un archivo CSV.
    public static File seleccionFicheroCSV() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new CSVFileFilter()); // Aplica el filtro CSV
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int seleccion = jfc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile();
        } else {
            return null;
        }
    }

    // Añade una línea a un archivo CSV.
    public static boolean anhadirLineaCSV(File fichero, String linea) {
        try {
            if (fichero.exists() && fichero.getName().endsWith(".csv")) {
                try (FileWriter fw = new FileWriter(fichero, true)) {
                    fw.write("\n" + linea); // Añade una nueva línea
                }
                return true;
            } else {
                throw new IllegalArgumentException("No es un fichero CSV.");
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Carga un archivo CSV en un JTable.
    public static void cargarCSVEnTabla(JTable table) {
    	
    	// Llamar al método seleccionFicheroCSV para obtener el archivo CSV
        File fichero = seleccionFicheroCSV();
    	
        // PARA SELECIONAR CUALQUIER ARCHIVO
        //JFileChooser jfc = new JFileChooser();
        //jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //int seleccion = jfc.showOpenDialog(null);
        //if (seleccion == JFileChooser.APPROVE_OPTION) {
            //File fichero = jfc.getSelectedFile();
        
            if (fichero.getName().endsWith(".csv")) {
                try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                    String line;
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Limpia la tabla
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        model.addRow(data); // Añade los datos a la tabla
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un archivo CSV.");
            }
        }
    
    
    public static void main(String[] args) {
    
        // Llamar al método seleccionFicheroCSV
        File archivoCSV = seleccionFicheroCSV();

        // Verificar si se seleccionó un archivo
        if (archivoCSV != null) {
            System.out.println("Archivo CSV seleccionado: " + archivoCSV.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
        
        // Añadir una linea a un archivo .csv
        String linea = "fdfs";
        
        //Verificar que la linea se añadió
        if (archivoCSV != null) {
            boolean exito = anhadirLineaCSV(archivoCSV, linea);
            if (exito) {
                System.out.println("La línea se ha añadido correctamente.");
            } else {
                System.out.println("No se pudo añadir la línea.");
            }
        } else {
            System.out.println("No se seleccionó un archivo CSV.");
        }
        
        // Cargar csv en tabla 
        
        // Crear la ventana principal (JFrame)
        JFrame frame = new JFrame("Cargar CSV en Tabla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un modelo de tabla con nombres de columnas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Columna 1");
        model.addColumn("Columna 2");
        model.addColumn("Columna 3");

        // Crear la tabla y asignarle el modelo
        JTable table = new JTable(model);
        
        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Configurar el tamaño de la ventana
        frame.setSize(500, 300);
        frame.setVisible(true);

        // Llamar al método cargarCSVEnTabla para cargar datos en la tabla
        CSVUtils.cargarCSVEnTabla(table);
    }
}
