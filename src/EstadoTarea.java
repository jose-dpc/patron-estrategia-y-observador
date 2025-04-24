public class EstadoTarea {

        private String estado;
    
        // Solo dentro de esta clase se va a determinar el estado de la tarea
        private EstadoTarea(String estado) {
            this.estado = estado;
        }
    
        @Override
        public String toString() {
            return estado;
        }
    
        // Estados posibles de una tarea
        public static final EstadoTarea Pendiente = new EstadoTarea("Pendiente");
        public static final EstadoTarea EnProgreso = new EstadoTarea("En progreso");
        public static final EstadoTarea Completada = new EstadoTarea("Completada");
    
}
