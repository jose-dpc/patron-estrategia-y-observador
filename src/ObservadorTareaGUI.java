import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObservadorTareaGUI extends JFrame {
    private DefaultTableModel modeloTabla;
    private JTable tablaTareas;

    // Constructor base de la interfaz gráfica
    public ObservadorTareaGUI() {
        setTitle("Gestor de Tareas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 245));
        setLayout(new BorderLayout());

        // Definición de los diferentes paneles y botones de la interfaz
        JLabel lblTitulo = new JLabel("Gestión de Tareas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(0, 99, 65));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setPreferredSize(new Dimension(900, 60));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(240, 248, 245));

        JButton btnAgregar = new JButton("Agregar Tarea");
        JButton btnQuitar = new JButton("Quitar Tarea");
        JButton btnVerCompletadas = new JButton("Ver Completadas");
        JButton btnEditar = new JButton("Editar Tarea");
        JComboBox<String> comboOrdenar = new JComboBox<>(
                new String[] { "Ordenar por...", "Fecha de entrega", "Prioridad", "Estado" });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnQuitar);
        panelBotones.add(btnVerCompletadas);
        panelBotones.add(btnEditar);
        panelBotones.add(comboOrdenar);
        add(panelBotones, BorderLayout.SOUTH);

        // Tabla de generación de tareas, hice uso de tablas en vez de un panel para
        // poder aplicar mejor los filtros y el tema de la definición del color de
        // prioridad.

        modeloTabla = new DefaultTableModel(new Object[] { "Nombre", "Fecha", "Prioridad", "Estado" }, 0);
        tablaTareas = new JTable(modeloTabla);
        tablaTareas.setRowHeight(35);
        tablaTareas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaTareas.setSelectionBackground(new Color(204, 232, 207));
        tablaTareas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaTareas.getColumnModel().getColumn(2).setCellRenderer(new ColorPriorityRenderer());

        JScrollPane scroll = new JScrollPane(tablaTareas);
        add(scroll, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> DialogosTarea.agregarTarea(this, modeloTabla));
        btnEditar.addActionListener(e -> DialogosTarea.editarTarea(this, tablaTareas, modeloTabla));
        btnQuitar.addActionListener(e -> quitarTarea());
        btnVerCompletadas.addActionListener(e -> verCompletadas());
        comboOrdenar.addActionListener(
                e -> JOptionPane.showMessageDialog(this, "Ordenar por: " + comboOrdenar.getSelectedItem()));

        cargarTareasDesdeBD();
        setVisible(true);
    }

    private void quitarTarea() {
        int fila = tablaTareas.getSelectedRow();
        if (fila >= 0) {
            String nombre = (String) modeloTabla.getValueAt(fila, 0);
            String fecha = (String) modeloTabla.getValueAt(fila, 1);
            eliminarTareaDeBD(nombre, fecha);
            modeloTabla.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para eliminar.");
        }
    }

    private void verCompletadas() {
        DefaultTableModel modeloFiltrado = new DefaultTableModel(
                new Object[] { "Nombre", "Fecha", "Prioridad", "Estado" }, 0);
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if ("Completada".equals(modeloTabla.getValueAt(i, 3))) {
                modeloFiltrado.addRow(new Object[] {
                        modeloTabla.getValueAt(i, 0),
                        modeloTabla.getValueAt(i, 1),
                        modeloTabla.getValueAt(i, 2),
                        modeloTabla.getValueAt(i, 3)
                });
            }
        }
        JTable tablaCompletadas = new JTable(modeloFiltrado);
        tablaCompletadas.setRowHeight(35);
        tablaCompletadas.getColumnModel().getColumn(2).setCellRenderer(new ColorPriorityRenderer());
        JOptionPane.showMessageDialog(this, new JScrollPane(tablaCompletadas), "Tareas Completadas",
                JOptionPane.PLAIN_MESSAGE);
    }

    // Conexion a Base de datos (pendiente) Falta Definir bien los parámetros de la
    // base de datos
    private void cargarTareasDesdeBD() {
        try (Connection conn = ConexionSQLite.conectar()) {
            String sql = "SELECT nombre, fecha, prioridad, estado FROM tareas";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                modeloTabla.addRow(new Object[] {
                        rs.getString("nombre"),
                        rs.getString("fecha"),
                        rs.getInt("prioridad"),
                        rs.getString("estado")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tareas: " + e.getMessage());
        }
    }

    // Función que se encarga de editar las tareas y mandar las modificaciones
    // dentro de la base de datos.
    private void insertarTareaEnBD(String nombre, String fecha, int prioridad, String estado) {
        try (Connection conn = ConexionSQLite.conectar()) {
            String sql = "INSERT INTO tareas(nombre, fecha, prioridad, estado) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, fecha);
            pstmt.setInt(3, prioridad);
            pstmt.setString(4, estado);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar tarea: " + e.getMessage());
        }
    }

    private void eliminarTareaDeBD(String nombre, String fecha) {
        try (Connection conn = ConexionSQLite.conectar()) {
            String sql = "DELETE FROM tareas WHERE nombre = ? AND fecha = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, fecha);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar tarea: " + e.getMessage());
        }
    }

    private void actualizarTareaEnBD(String nombre, String fechaAntigua, String nuevaFecha, int nuevaPrioridad,
            String nuevoEstado) {
        try (Connection conn = ConexionSQLite.conectar()) {
            String sql = "UPDATE tareas SET fecha = ?, prioridad = ?, estado = ? WHERE nombre = ? AND fecha = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevaFecha);
            pstmt.setInt(2, nuevaPrioridad);
            pstmt.setString(3, nuevoEstado);
            pstmt.setString(4, nombre);
            pstmt.setString(5, fechaAntigua);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar tarea: " + e.getMessage());
        }
    }

    // Definición de la celda de prioridad con su color dependiendo del valor de la
    // prioridad (rojo,naranja, amarillo, verde, azul)
    static class ColorPriorityRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            setText(value.toString());
            int prioridad = Integer.parseInt(value.toString());
            Color color;
            switch (prioridad) {
                case 5 -> color = new Color(192, 0, 0);
                case 4 -> color = new Color(255, 102, 0);
                case 3 -> color = new Color(255, 204, 0);
                case 2 -> color = new Color(0, 204, 102);
                default -> color = new Color(102, 178, 255);
            }
            setBackground(color);
            setOpaque(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ObservadorTareaGUI::new);
    }
}
