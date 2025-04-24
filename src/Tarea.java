import java.time.LocalDate;
import java.util.*;
// Clase Tarea simple
public class Tarea{
    private String nombre;
    private int prioridad;
    private EstadoTarea estado;
    private LocalDate fechaEntrega;
 
 
    public Tarea(String nombre, int prioridad, EstadoTarea estado, LocalDate fechaEntrega) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = EstadoTarea.Pendiente;
        this.fechaEntrega = fechaEntrega;//
    }
 
 
    public String getNombre() {
        return nombre;
    }
 
    public int getPrioridad() {
        return prioridad;
    }

    public EstadoTarea getEstado(){
        return estado;
    }

     public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }
    @Override
    public String toString() {
        return nombre + "(Prioridad: " + prioridad + ", Estado: " + estado.toString()+ ", Fecha de entrega: " + fechaEntrega.toString() + ")";
    }
}