public class Actividad {
    private String nombreActividad;
    private String fecha;
    private String hora;
    private boolean estado;

    public Actividad(String fecha, String hora, String nombreActividad, boolean estado) {
        this.nombreActividad = nombreActividad;
        this.fecha = fecha;
        this.hora = hora;
        estado = false;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setnombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        if (estado) {
            return "Nombre de la Actividad: " + nombreActividad + " | Fecha: " + fecha + " | Hora: " + hora
                    + " | Estado: Completado";
        }
        return "Nombre de la Actividad: " + nombreActividad + " | Fecha: " + fecha + " | Hora: " + hora
                + " | Estado: Pendiente";
    }
}