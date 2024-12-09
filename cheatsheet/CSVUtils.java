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
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int seleccion = jfc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = jfc.getSelectedFile();
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
    }
}
