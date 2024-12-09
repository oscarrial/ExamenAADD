package examen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class FormularioSwing extends JFrame {

    // Componentes
    private JLabel labelNombre, labelApellido, labelEdad, labelTelefono, labelEmail;
    private JTextField textFieldNombre, textFieldApellido, textFieldEdad, textFieldTelefono, textFieldEmail;
    private JButton button;

    public FormularioSwing() {
        // Configuración básica del JFrame
        setTitle("Formulario de registro");
        setSize(300, 300);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear los componentes
        labelNombre = new JLabel("Nombre:");
        textFieldNombre = new JTextField(20);
        
        labelApellido = new JLabel("Apellido:");
        textFieldApellido = new JTextField(20);
        
        labelEdad = new JLabel("Edad:");
        textFieldEdad = new JTextField(20);
        
        labelTelefono = new JLabel("Teléfono:");
        textFieldTelefono = new JTextField(20);
        
        labelEmail = new JLabel("Correo electrónico:");
        textFieldEmail = new JTextField(20);
        
        button = new JButton("Enviar");

        // Configurar el layout (diseño) del formulario
        setLayout(new FlowLayout());

        // Agregar los componentes a la ventana
        add(labelNombre);
        add(textFieldNombre);
        
        add(labelApellido);
        add(textFieldApellido);
        
        add(labelEdad);
        add(textFieldEdad);
        
        add(labelTelefono);
        add(textFieldTelefono);
        
        add(labelEmail);
        add(textFieldEmail);
        
        add(button);

        // Acción del botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText();
                String apellido = textFieldApellido.getText();
                String edadStr = textFieldEdad.getText();
                String telefono = textFieldTelefono.getText();
                String email = textFieldEmail.getText();
                
                // Validación del nombre y apellido
                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: Nombre y apellido son obligatorios.");
                    return; // Si alguno está vacío, salimos del método
                }
                
                // Validación de la edad
                try {
                    int edad = Integer.parseInt(edadStr);
                    if (edad <= 0) {
                        throw new NumberFormatException("La edad debe ser un número positivo.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: La edad debe ser un número válido.");
                    return; // Si la edad no es válida, salimos del método
                }
                
                // Validación del teléfono (debe ser solo números)
                if (!telefono.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "Error: El teléfono debe tener 10 dígitos.");
                    return; // Si el teléfono no es válido, salimos del método
                }

                // Validación del correo electrónico (expresión regular simple)
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Error: El correo electrónico no es válido.");
                    return; // Si el correo no es válido, salimos del método
                }

                // Si todo es válido
                JOptionPane.showMessageDialog(null, "Formulario enviado correctamente.");

                // Limpiar los campos del formulario
                textFieldNombre.setText("");
                textFieldApellido.setText("");
                textFieldEdad.setText("");
                textFieldTelefono.setText("");
                textFieldEmail.setText("");
            }
        });
    }

    // Método para validar el correo electrónico usando una expresión regular
    private boolean isValidEmail(String email) {
        // Expresión regular para validar un correo electrónico simple
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        // Crear y mostrar el formulario
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioSwing().setVisible(true);
            }
        });
    }
}
