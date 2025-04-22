import java.util.*;
 
// Programa principal
public class Main {
    public static void main(String[] args) {
        GestorDeTareas gestor = new GestorDeTareas();
        
        gestor.agregarTarea(new Tarea("Entregar reporte", 3));
        gestor.agregarTarea(new Tarea("Hacer examen", 1));
        gestor.agregarTarea(new Tarea("Estudiar patrones", 2));
 
 
        System.out.println("Tareas antes de ordenar:");
        gestor.mostrarTareas();
 
 
        gestor.setEstrategia(new OrdenarPorPrioridad());
        gestor.ordenarTareas();
 
 
        System.out.println("\nTareas después de ordenar por prioridad:");
        gestor.mostrarTareas();
    }
}
 