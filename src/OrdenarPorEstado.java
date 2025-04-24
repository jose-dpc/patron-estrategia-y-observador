import java.util.Comparator;
import java.util.List;

public class OrdenarPorEstado implements EstrategiaOrdenamiento {
    @Override
    /*A cada objeto t que es de tipo tarea, get su estado y luego pasalo por getORdenEstado para que
    tenga un valor numerico que indique su prioridad o jerarquia*/
    public void ordenar(List<Tarea> tareas) {
        tareas.sort(Comparator.comparingInt(t -> getOrdenEstado(t.getEstado())));
    }

    private int getOrdenEstado(EstadoTarea estado) {
        if (estado == EstadoTarea.Pendiente) return 0;
        if (estado == EstadoTarea.EnProgreso) return 1;
        if (estado == EstadoTarea.Completada) return 2;
        return 3; // Por si acaso hay algún otro estado no previsto
    }
}
