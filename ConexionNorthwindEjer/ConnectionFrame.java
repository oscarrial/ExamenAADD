package ConexionNorthwindEjer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFrame extends JFrame {
    private JTextField txtServer, txtPort, txtUser;
    private JPasswordField txtPassword;
    private JButton btnConnect;

    public ConnectionFrame() {
        setTitle("Conexión a Northwind");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Componentes
        add(new JLabel("Servidor:"));
        txtServer = new JTextField();
        add(txtServer);

        add(new JLabel("Puerto:"));
        txtPort = new JTextField();
        add(txtPort);

        add(new JLabel("Usuario:"));
        txtUser = new JTextField();
        add(txtUser);

        add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        btnConnect = new JButton("Conectar");
        add(btnConnect);

        // Acción del botón de conexión
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String server = txtServer.getText();
                String port = txtPort.getText();
                String user = txtUser.getText();
                String password = new String(txtPassword.getPassword());

                // Validar entradas
                if (server.isEmpty() || port.isEmpty() || user.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int portNumber = Integer.parseInt(port); // Validar puerto como número
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El puerto debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Intentar la conexión a la base de datos
                try (Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://" + server + ":" + port + "/northwind", user, password)) {
                    JOptionPane.showMessageDialog(null, "Conexión exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    new ProductFrame(conn).setVisible(true);
                    dispose(); // Cerrar esta ventana
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConnectionFrame().setVisible(true));
    }
}

