/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;



import javax.swing.*;
import java.awt.*;

public class dashboardAdmin {
    
    public static void main(String[] args) {
    JFrame frame = new JFrame("Send & Track");
    frame.setSize(1000, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout()); // Usar BorderLayout para mejor control de disposición

    // Panel lateral
    JPanel sidePanel = new JPanel();
    sidePanel.setPreferredSize(new Dimension(200, 600)); 
    sidePanel.setBackground(new Color(32, 56, 100)); 
    sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS)); // Mejor gestión de elementos en columna

    // Etiqueta de nombre de usuario
    JLabel userLabel = new JLabel("<html><b>Rodrigo Monterrosa</b><br>Administrador</html>");
    userLabel.setForeground(Color.WHITE);
    userLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Centrar la etiqueta

    // Botones del menú con tamaño preferido
    JButton dashboardButton = new JButton("Dashboard");
    dashboardButton.setPreferredSize(new Dimension(180, 40));
    dashboardButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

    JButton paquetesButton = new JButton("Paquetes");
    paquetesButton.setPreferredSize(new Dimension(180, 40));
    paquetesButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

    // Añadir componentes al panel lateral
    sidePanel.add(Box.createVerticalStrut(100)); // Añadir espacio superior
    sidePanel.add(userLabel);
    sidePanel.add(Box.createVerticalStrut(50)); // Espacio entre la etiqueta y el primer botón
    sidePanel.add(dashboardButton);
    sidePanel.add(Box.createVerticalStrut(10)); // Espacio entre botones
    sidePanel.add(paquetesButton);

    // Añadir el panel lateral al frame
    frame.add(sidePanel, BorderLayout.WEST);

    // Panel de estadísticas
    JPanel statsPanel = new JPanel();
    statsPanel.setPreferredSize(new Dimension(750, 150)); // Ajustar tamaño del panel de estadísticas
    statsPanel.setLayout(new GridLayout(1, 2, 10, 10)); // Distribuir en 2 columnas para evitar desbordamiento

    // Ejemplo de una tarjeta de estadísticas
    JPanel card1 = new JPanel();
    card1.setBackground(Color.LIGHT_GRAY);
    card1.setLayout(new BorderLayout());

    JLabel title1 = new JLabel("Nuevos Cargamentos:");
    title1.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel data1 = new JLabel("6");
    data1.setHorizontalAlignment(SwingConstants.CENTER);
    data1.setFont(new Font("Arial", Font.BOLD, 30));

    card1.add(title1, BorderLayout.NORTH);
    card1.add(data1, BorderLayout.CENTER);

    // Repetir para otros datos
    JPanel card2 = new JPanel();
    card2.setBackground(Color.LIGHT_GRAY);
    card2.setLayout(new BorderLayout());

    JLabel title2 = new JLabel("Ingreso Diario:");
    title2.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel data2 = new JLabel("$250");
    data2.setHorizontalAlignment(SwingConstants.CENTER);
    data2.setFont(new Font("Arial", Font.BOLD, 30));

    card2.add(title2, BorderLayout.NORTH);
    card2.add(data2, BorderLayout.CENTER);

    // Añadir tarjetas al panel de estadísticas
    statsPanel.add(card1);
    statsPanel.add(card2);

    // Añadir el panel de estadísticas al frame
    frame.add(statsPanel, BorderLayout.NORTH);

    // Tabla de repartidores libres
    String[] columnNames = {"ID Empleado", "Nombre", "Apellido"};
    Object[][] data = {
        {"#24062", "Juan Miguel", "Lopéx"}
    };

    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(500, 100)); // Ajustar el tamaño de la tabla

    // Añadir la tabla al frame
    JPanel tablePanel = new JPanel();
    tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    tablePanel.add(scrollPane);
    frame.add(tablePanel, BorderLayout.CENTER);

    // Mostrar la ventana
    frame.setVisible(true);
    }
}