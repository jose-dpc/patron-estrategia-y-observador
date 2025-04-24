import java.util.Comparator;
import java.util.List;
//con esto se comparan las fechas obtenidas y en base a esto se ordenan en orden ascendente
public class OrdenarPorFecha implements EstrategiaOrdenamiento{
    
    @Override
    public void ordenar(List<Tarea> tareas){
        tareas.sort(Comparator.comparing(Tarea::getFechaEntrega));
    }
}
