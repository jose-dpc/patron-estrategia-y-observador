import java.util.*;
// Clase Gestor que usa una estrategia
class GestorDeTareas {
    private List<Tarea> tareas = new ArrayList<>();
    private EstrategiaOrdenamiento estrategia;
 
 
    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }
 
 
    public void setEstrategia(EstrategiaOrdenamiento estrategia) {
        this.estrategia = estrategia;
    }
 
 
    public void ordenarTareas() {
        if (estrategia != null) {
            estrategia.ordenar(tareas);
        }
    }
 
 
    public void mostrarTareas() {
        for (Tarea t : tareas) {
            System.out.println(t);
        }
    }
}