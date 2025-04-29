
import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectorFecha {
    private final JCalendar calendar;
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("yyyy-MM-dd");

    public SelectorFecha() {
        this.calendar = new JCalendar();
        calendar.setWeekOfYearVisible(true); // Mostrar el número de semana
        calendar.setTodayButtonVisible(true); // Botón para ir a hoy
        calendar.setPreferredSize(new Dimension(300, 200));
    }

    public JComponent getComponente() {
        return calendar;
    }

    public String getFechaFormateada() {
        Date fecha = calendar.getDate();
        return (fecha != null) ? FORMATO.format(fecha) : null;
    }

    public void setFecha(Date fecha) {
        calendar.setDate(fecha);
    }

    public static String seleccionarFechaDialog(Component parent) {
        SelectorFecha selector = new SelectorFecha();
        int opcion = JOptionPane.showConfirmDialog(
                parent,
                selector.getComponente(),
                "Seleccione la fecha",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        return (opcion == JOptionPane.OK_OPTION)
                ? selector.getFechaFormateada()
                : null;
    }
}
