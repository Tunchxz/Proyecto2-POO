import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.io.*;

public class UVGenda {
    public ArrayList<Usuario> usuarios;

    public UVGenda() {
        this.usuarios = new ArrayList<Usuario>();
    }
   
    
    public void cargarDatosDesdeCSV() {
        cargarUsuariosDesdeCSV();
        cargarActividadesDesdeCSV();
    }


    public void cargarUsuariosDesdeCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("Usuarios.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int idu = Integer.parseInt(partes[0]);
                    String nombreUsuario = partes[1];
                    String contrasena = partes[2];
                    usuarios.add(new Usuario(idu, nombreUsuario, contrasena));
                    
                    System.out.println("Usuario cargado: " + nombreUsuario + ", Contraseña: " + contrasena);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarActividadesDesdeCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("Actividades.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    String nombreUsuario = partes[0];
                    String fecha = partes[1];
                    String hora = partes[2];
                    String nombreActividad = partes[3];
                    boolean estado = Boolean.parseBoolean(partes[4]);
                    System.out.println("Datos leídos: Nombre Usuario: " + nombreUsuario + ", Fecha: " + fecha + ", Hora: " + hora + ", Nombre Actividad: " + nombreActividad + ", Estado: " + estado);

                    Usuario usuarioEncontrado = null;
                    for (Usuario usuario : usuarios) {
                        if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                            usuarioEncontrado = usuario;
                            break;
                        }
                    }

                    if (usuarioEncontrado != null) {
                        usuarioEncontrado.getListaDeActividades().add(new Actividad(fecha, hora, nombreActividad, estado));
                        System.out.println("Actividad agregada para el usuario: " + usuarioEncontrado.getNombreUsuario());
                        
                    }
                }else {
                	System.out.println("La línea no tiene el formato correcto: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarUsuariosEnCSV(String archivo) {
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getIdu() + ";" + usuario.getNombreUsuario() + ";" + usuario.getContrasena());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarActividadesEnCSV(String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            for (Usuario usuario : usuarios) {
                for (Actividad actividad : usuario.getListaDeActividades()) {
                    bw.write(usuario.getNombreUsuario() + ";" + actividad.getFecha() + ";" + actividad.getHora() + ";" +
                            actividad.getNombreActividad() + ";" + actividad.getEstado());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean crearUsuario(String nombreUsuario, String contrasena) {
    	if (usuarios.isEmpty()) {
            Usuario nuevoUsuario = new Usuario(1, nombreUsuario, contrasena);
            usuarios.add(nuevoUsuario);
            guardarUsuariosEnCSV("usuarios.csv"); 
            return true;
        } else {
            for (Usuario usuario : usuarios) {
                if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                    return false;
                }
            }

            int ultimoID = usuarios.get(usuarios.size() - 1).getIdu();
            Usuario nuevoUsuario = new Usuario(ultimoID + 1, nombreUsuario, contrasena);
            usuarios.add(nuevoUsuario);
            guardarUsuariosEnCSV("usuarios.csv"); 
            return true;
        }
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public String crearActividad(String nombreUsuario, String hora, String nombreAct, String fecha) {
    	for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                Actividad nuevaActividad = new Actividad(fecha, hora, nombreAct, false);
                usuario.getListaDeActividades().add(nuevaActividad);
                guardarActividadesEnCSV("Actividades.csv");
                return "\n[Sistema]: Actividad registrada con éxito.";
            }
        }
        return "\n[Sistema]: Error al registrar la actividad.";
    }

    public boolean validarListaActividades(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario.getListaDeActividades().isEmpty();
            }
        }
        return false;
    }

    public String mostrarActividades(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                int indice = 0;
                String actividades = "\n--------------- Tu Lista de Actividades ---------------";
                for (Actividad actividad : usuario.getListaDeActividades()) {
                    indice++;
                    actividades += "\n" + indice + " " + actividad.getNombreActividad();
                }
                return actividades;
            }
        }
        return "\n[Sistema]: Error01. Problemas con el perfil del usuario";
    }

    public boolean validarIndiceActividad(String nombreUsuario, int indiceActividad) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                ArrayList<Actividad> listaDeActividades = usuario.getListaDeActividades();
                return indiceActividad >= 1 && indiceActividad <= listaDeActividades.size();
            }
        }
        return false;
    }

    public String editarActividad(String nombreUsuario, int indiceActividad, String nuevaFecha, String nuevaHora,
            String nuevoNombreActividad) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                Actividad actividad = usuario.getListaDeActividades().get(indiceActividad - 1);
                actividad.setFecha(nuevaFecha);
                actividad.setHora(nuevaHora);
                actividad.setnombreActividad(nuevoNombreActividad);
                guardarActividadesEnCSV("actividades.csv");
                return "\n[Sistema]: Actividad editada con éxito.";
            }
        }
        return "\n[Sistema]: Error01. Problemas con el perfil del usuario";
    }

    public String cambiarEstadoActividad(String nombreUsuario, int indiceActividad) {
        if (!validarIndiceActividad(nombreUsuario, indiceActividad)) {
            return "\n[Sistema]: Error02. Número de actividad seleccionada inválido.";
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                Actividad actividad = usuario.getListaDeActividades().get(indiceActividad - 1);
                if (actividad.getEstado() == true) {
                    actividad.setEstado(false);
                    guardarActividadesEnCSV("actividades.csv");
                    return "\n[Sistema]: Estado de la actividad cambiado con éxito.";
                }
                actividad.setEstado(true);
                guardarActividadesEnCSV("actividades.csv");
                return "\n[Sistema]: Estado de la actividad cambiado con éxito.";
            }
        }
        return "\n[Sistema]: Error01. Problemas con el perfil del usuario";
    }

    public String eliminarActividad(String nombreUsuario, int indiceActividad) {
        if (!validarIndiceActividad(nombreUsuario, indiceActividad)) {
            return "\n[Sistema]: Error02. Número de actividad seleccionada inválido.";
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                usuario.getListaDeActividades().remove(indiceActividad - 1);
                guardarActividadesEnCSV("actividades.csv");
                return "\n[Sistema]: La actividad ha sido eliminada correctamente.";
            }
        }
        return "\n[Sistema]: Error01. Problemas con el perfil del usuario";
    }

    public String mostrarActividadesPendientes(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                String actividades = "";
                int indice = 0;
                for (Actividad actividad : usuario.getListaDeActividades()) {
                    if (!actividad.getEstado()) {
                        indice++;
                        actividades += "\n" + indice + " " + actividad.toString();
                    }
                }
                if (actividades.isEmpty()) {
                    return "\n[Sistema]: No tienes actividades pendientes.";
                } else {
                    return "\n--------------- Tus Actividades Pendientes ---------------" + actividades;
                }
            }
        }
        return "\n[Sistema]: Error01. Problemas con el perfil del usuario";
    }
}