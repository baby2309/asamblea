import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class aplicacion{

    private static JTextField marcaField;
    private static JTextField modeloField;
    private static JTextField IdentificacionField;
    private static JTextField precioField;
    private static JTextField nombreField;
    private static JTextField telefonoField;
    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Aplicación de Concesionario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        JLabel marcaLabel = new JLabel("Marca:");
        marcaField = new JTextField(20);
        JLabel modeloLabel = new JLabel("Modelo:");
        modeloField = new JTextField(20);
        JLabel IdentificacionLabel = new JLabel("Identificacion:");
        IdentificacionField = new JTextField(10);
        JLabel precioLabel = new JLabel("Precio:");
        precioField = new JTextField(20);
        JLabel nombreLabel = new JLabel("Nombre del Cliente:");
        nombreField = new JTextField(20);
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField(20);

        JButton enviarButton = new JButton("Enviar a la Base de Datos");
      
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = marcaField.getText();
                String modelo = modeloField.getText();
                int identificacion = Integer.parseInt(IdentificacionField.getText());
                double precio = Double.parseDouble(precioField.getText());
                String nombreCliente = nombreField.getText();
                String telefono = telefonoField.getText();

                try {
                    // Establecer la conexión a la base de datos
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario", "root", "");
                    
                    // Crear una sentencia SQL para insertar datos en la base de datos
                    String sql = "INSERT INTO usuarios (marca, modelo, identificacion, precio, nombre, telefono) VALUES (?, ?, ?, ?, ?, ?)";
                    
                    PreparedStatement statement = conexion.prepareStatement(sql);
                    
                    statement.setString(1, marca);
                    statement.setString(2, modelo);
                    statement.setInt(3, identificacion);
                    statement.setDouble(4, precio);
                    statement.setString(5, nombreCliente);
                    statement.setString(6, telefono);
                    
                    // Ejecutar la sentencia SQL para insertar los datos
                    statement.executeUpdate();
                    
                    // Cerrar la conexión
                    conexion.close();
                    
                    JOptionPane.showMessageDialog(frame, "Datos insertados en la base de datos.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(IdentificacionLabel);
        panel.add(IdentificacionField);
        panel.add(telefonoLabel);
        panel.add(telefonoField);
        panel.add(precioLabel);
        panel.add(precioField);
        panel.add(marcaLabel);
        panel.add(marcaField);
        panel.add(modeloLabel);
        panel.add(modeloField);
	    panel.add(new JLabel(""));
        panel.add(enviarButton);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
