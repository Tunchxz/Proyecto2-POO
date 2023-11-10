import java.util.ArrayList;

public class Usuario {
    private int idu;
    private String nombreUsuario;
    private String contrasena;
    private ArrayList<Actividad> actividades;

    public Usuario(int idu, String nombreUsuario, String contrasena) {
        this.idu = idu;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        actividades = new ArrayList<Actividad>();
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<Actividad> getListaDeActividades() {
        return actividades;
    }
}