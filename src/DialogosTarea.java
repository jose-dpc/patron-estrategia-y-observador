import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogosTarea {

    public static void agregarTarea(JFrame parent, DefaultTableModel modeloTabla) {
        JTextField campoNombre = new JTextField(20);
        JComboBox<String> comboPrioridad = new JComboBox<>(new String[] { "1", "2", "3", "4", "5" });
        JComboBox<String> comboEstado = new JComboBox<>(new String[] { "Pendiente", "En progreso", "Completada" });
        SelectorFecha selectorFecha = new SelectorFecha();

        // Construcción del panel con GridBagLayout (igual que antes)
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500, 350));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 0: Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Nombre de materia:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoNombre, gbc);

        // Fila 1: Fecha
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Fecha de entrega:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(selectorFecha.getComponente(), gbc);

        // Fila 2: Prioridad
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Prioridad (1-5):"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboPrioridad, gbc);

        // Fila 3: Estado
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboEstado, gbc);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Agregar Nueva Tarea",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String fecha = selectorFecha.getFechaFormateada();
            modeloTabla.addRow(new Object[] {
                    campoNombre.getText(),
                    fecha,
                    comboPrioridad.getSelectedItem(),
                    comboEstado.getSelectedItem()
            });
            // aquí iría insertarTareaEnBD(...)
        }
    }

    public static void editarTarea(JFrame parent,
            JTable tablaTareas,
            DefaultTableModel modeloTabla) {
        int fila = tablaTareas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(parent, "Selecciona una tarea para editar.");
            return;
        }

        String fechaActual = (String) modeloTabla.getValueAt(fila, 1);
        String prioridadActual = modeloTabla.getValueAt(fila, 2).toString();
        String estadoActual = (String) modeloTabla.getValueAt(fila, 3);

        SelectorFecha selectorFecha = new SelectorFecha();
        try {
            Date f = new SimpleDateFormat("yyyy-MM-dd").parse(fechaActual);
            selectorFecha.setFecha(f);
        } catch (ParseException ignored) {
        }

        JComboBox<String> comboPrioridad = new JComboBox<>(new String[] { "1", "2", "3", "4", "5" });
        comboPrioridad.setSelectedItem(prioridadActual);
        JComboBox<String> comboEstado = new JComboBox<>(new String[] { "Pendiente", "En progreso", "Completada" });
        comboEstado.setSelectedItem(estadoActual);

        // Panel GridBagLayout idéntico al de agregar
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 0: Fecha
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Nueva fecha de entrega:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(selectorFecha.getComponente(), gbc);

        // Fila 1: Prioridad
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Nueva prioridad:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboPrioridad, gbc);

        // Fila 2: Estado
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Nuevo estado:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboEstado, gbc);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Editar Tarea",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nuevaFecha = selectorFecha.getFechaFormateada();
            if (nuevaFecha == null)
                nuevaFecha = fechaActual;
            int nuevaPri = Integer.parseInt(comboPrioridad.getSelectedItem().toString());
            String nuevoEst = comboEstado.getSelectedItem().toString();

            modeloTabla.setValueAt(nuevaFecha, fila, 1);
            modeloTabla.setValueAt(nuevaPri, fila, 2);
            modeloTabla.setValueAt(nuevoEst, fila, 3);
            // aquí iría actualizarTareaEnBD(...)
        }
    }
}
