import java.util.*;
// Clase que agrega las tareas a un arreglo y llama la estrategia de ordenamiento
class GestorDeTareas {
    private List<Tarea> tareas = new ArrayList<>();
    private EstrategiaOrdenamiento estrategia;
 
 
    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }
 
 
    public void setEstrategia(EstrategiaOrdenamiento estrategia) {
        this.estrategia = estrategia;
    }
 
 //siempre y cuando estrategia tenga un valor, se llama EstrategiaOrdenamiento para el arreglo de tareas
    public void ordenarTareas() {
        if (estrategia != null) {
            estrategia.ordenar(tareas);
        }
    }
 
 //funcion que muestra cada 't' de tipo tarea
    public void mostrarTareas() {
        for (Tarea t : tareas) {
            System.out.println(t);
        }
    }
}