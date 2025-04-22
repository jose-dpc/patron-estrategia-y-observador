public class EstadoTarea {

        private String nombre;
    
        // Solo dentro de esta clase se va a determinar el estado de la tarea
        private EstadoTarea(String nombre) {
            this.nombre = nombre;
        }
    
        @Override
        public String toString() {
            return nombre;
        }
    
        // Estados posibles de una tarea
        public static final EstadoTarea PENDIENTE = new EstadoTarea("Pendiente");
        public static final EstadoTarea EN_PROGRESO = new EstadoTarea("En progreso");
        public static final EstadoTarea COMPLETADA = new EstadoTarea("Completada");
    
}
