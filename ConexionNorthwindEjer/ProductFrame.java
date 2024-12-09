package ConexionNorthwindEjer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProductFrame extends JFrame {
    private Connection connection;
    private JTable table;
    private DefaultTableModel tableModel;

    public ProductFrame(Connection connection) {
        this.connection = connection;
        setTitle("Gestión de Productos");
        setLayout(new BorderLayout());

        // Crear JTable para mostrar los productos
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Nombre", "Precio", "Cantidad"});
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Cargar Productos");
        JButton exportButton = new JButton("Exportar Productos");
        buttonPanel.add(loadButton);
        buttonPanel.add(exportButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarProductosDesdeArchivo();
            }
        });

        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportarProductosAArchivo();
            }
        });

        // Mostrar los productos al iniciar
        mostrarProductos();

        // Configuración de la ventana
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void mostrarProductos() {
        try {
            String query = "SELECT * FROM products";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            tableModel.setRowCount(0);  // Limpiar la tabla

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getDouble("Price"),
                    rs.getInt("Quantity")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los productos: " + ex.getMessage());
        }
    }

    private void cargarProductosDesdeArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos TXT", "txt"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO products (ProductName, Price, Quantity) VALUES (?, ?, ?)");

                int count = 0;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length == 3) {
                        ps.setString(1, fields[0]);
                        ps.setDouble(2, Double.parseDouble(fields[1]));
                        ps.setInt(3, Integer.parseInt(fields[2]));
                        ps.executeUpdate();
                        count++;
                    }
                }

                JOptionPane.showMessageDialog(this, "Se han importado " + count + " registros.");
                mostrarProductos();
            } catch (IOException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + ex.getMessage());
            }
        }
    }

    private void exportarProductosAArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos TXT", "txt"));

        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                String query = "SELECT * FROM products";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);

                int count = 0;
                while (rs.next()) {
                    String line = rs.getString("ProductName") + "," + rs.getDouble("Price") + "," + rs.getInt("Quantity");
                    bw.write(line);
                    bw.newLine();
                    count++;
                }

                JOptionPane.showMessageDialog(this, "Se han exportado " + count + " registros.");
            } catch (IOException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al exportar los productos: " + ex.getMessage());
            }
        }
    }
}
