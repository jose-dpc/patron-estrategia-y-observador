import java.util.Comparator;
import java.util.List;

public class OrdenarPorFecha implements EstrategiaOrdenamiento{
    
    @Override
    public void ordenar(List<Tarea> tareas){
        tareas.sort(Comparator.comparing(Tarea::getFechaEntrega));
    }
}
