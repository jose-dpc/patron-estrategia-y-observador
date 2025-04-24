import java.util.*;
// Estrategia concreta: Ordenar por prioridad ascendente

public class OrdenarPorPrioridad implements EstrategiaOrdenamiento {
    @Override
    public void ordenar(List<Tarea> tareas) {
        tareas.sort(Comparator.comparing(Tarea::getFechaEntrega));
    }

}