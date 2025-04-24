import java.time.LocalDate;
import java.util.*;

public class Tarea{
    //Los parametros que cada tarea debe tener
    private String nombre;
    private int prioridad;
    private EstadoTarea estado;
    private LocalDate fechaEntrega;
 
 
    public Tarea(String nombre, int prioridad, EstadoTarea estado, LocalDate fechaEntrega) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        //el estado default de cada tarea que se agregue sera pendiente porque para que vas a agregar una tarea que ya completaste
        this.estado = EstadoTarea.Pendiente;
        this.fechaEntrega = fechaEntrega;
    }
 
    //getters
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
    
    //va a imprimir cada uno de estos parameteros
    @Override
    public String toString() {
        return nombre + "(Prioridad: " + prioridad + ", Estado: " + estado.toString()+ ", Fecha de entrega: " + fechaEntrega.toString() + ")";
    }
}