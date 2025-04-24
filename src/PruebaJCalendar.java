import javax.swing.JFrame;
import com.toedter.calendar.JCalendar;

public class PruebaJCalendar {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba JCalendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JCalendar());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
