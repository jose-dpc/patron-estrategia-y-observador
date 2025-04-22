import java.util.*;
// Clase Tarea simple
public class Tarea{
    private String nombre;
    private int prioridad;
    private EstadoTarea estado;
 
 
    public Tarea(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = EstadoTarea.Pendiente;//
    }
 
 
    public String getNombre() {
        return nombre;
    }
 
 
    public int getPrioridad() {
        return prioridad;
    }
 
 
    @Override
    public String toString() {
        return nombre + " (Prioridad: " + prioridad + ")";
    }
}