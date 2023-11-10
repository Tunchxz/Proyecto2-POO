import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String usuarioMaster = null;
        Boolean salirMenuInicial = false;
        Boolean salirMenuPrincipal = true;
        Boolean cicloMaster = true;

        UVGenda controlador = new UVGenda();
        controlador.cargarUsuariosDesdeCSV("usuarios.csv");
        controlador.cargarActividadesDesdeCSV("actividades.csv");

        while (cicloMaster) {
            while (!salirMenuInicial) {
                System.out.println(
                        "\n--------------- Bienvendio a UVGenda ---------------\n1. Iniciar Sesión\n2. Registrarse\n3. Salir");
                System.out.println("Seleccione una opción: ");
                String opcion = entrada.nextLine();

                if (!esCadenaNumeroValida(opcion)) {
                    System.out.println("\n[Sistema]: Por favor, ingrese una entrada válida.");
                    continue;
                }
                int opcionMenuInicial = Integer.parseInt(opcion);

                switch (opcionMenuInicial) {
                    case 1: {
                        System.out.println("\n[Sistema]: Ingresa tu nombre de usuario: ");
                        String nombreUsuario = entrada.nextLine();
                        System.out.println("[Sistema]: Ingresa tu contraseña: ");
                        String contrasenaUsuario = entrada.nextLine();
                        if (controlador.iniciarSesion(nombreUsuario, contrasenaUsuario)) {
                            salirMenuInicial = true;
                            salirMenuPrincipal = false;
                            usuarioMaster = nombreUsuario;
                            System.out.println("\n[Sistema]: Sesión iniciada con éxito.");
                            break;
                        }
                        System.out.println("\n[Sistema]: Usuario o contraseña incorrectos. Intenta de nuevo.");
                        break;
                    }
                    case 2: {
                        System.out.println("\n[Sistema]: Ingresa tu nombre de usuario: ");
                        String nombreUsuario = entrada.nextLine();
                        System.out.println("[Sistema]: Ingresa tu contraseña: ");
                        String contrasenaUsuario = entrada.nextLine();
                        if (controlador.crearUsuario(nombreUsuario, contrasenaUsuario)) {
                            System.out.println("\n[Sistema]: ¡Usuario creado correctamente!");
                            break;
                        }
                        System.out.println(
                                "\n[Sistema]: Lo siento, ese nombre de usuario ya está en uso. Intenta de nuevo con otro nombre.");
                        break;
                    }
                    case 3: {
                        salirMenuInicial = true;
                        cicloMaster = false;
                        System.out.println("\n[Sistema]: Gracias por usar UVGenda. ¡Ten un lindo día!");
                        break;
                    }
                    default:
                        System.out.println("\n[Sistema]: Error03. Por favor, ingresa una opción válida del menú.");
                        break;
                }
            }

            while (!salirMenuPrincipal) {
                System.out.println(
                        "\n--------------- Menú Principal UVGenda ---------------\n1. Crear Actividad\n2. Mostrar Todas las Actividades\n3. Editar Actividad\n4. Cambiar Estado de Actividad\n5. Eliminar Actividad\n6. Mostrar Actividades Pendientes\n7. Cerrar Sesión");
                System.out.println("Seleccione una opción: ");
                String opcion = entrada.nextLine();

                if (!esCadenaNumeroValida(opcion)) {
                    System.out.println("\n[Sistema]: Por favor, ingrese una entrada válida.");
                    continue;
                }
                int opcionMenuPrincipal = Integer.parseInt(opcion);

                switch (opcionMenuPrincipal) {
                    case 1: {
                        System.out.println("\n[Sistema]: Ingresa el nombre de la Actividad: ");
                        String nombreActividad = entrada.nextLine();
                        System.out.println("[Sistema]: Ingresa la fecha para la Actividad (dd/mm/aaaa): ");
                        String fechaActividad = entrada.nextLine();
                        System.out.println("[Sistema]: Ingresa la hora para la Actividad (hh:mm): ");
                        String horaActividad = entrada.nextLine();
                        System.out.println(
                                controlador.crearActividad(fechaActividad, horaActividad, nombreActividad,
                                        usuarioMaster));
                        break;
                    }
                    case 2: {
                        if (controlador.validarListaActividades(usuarioMaster)) {
                            System.out.println("\n[Sistema]: Parece ser que no tienes actividades aún...");
                            break;
                        }
                        System.out.println(controlador.mostrarActividades(usuarioMaster));
                        break;
                    }
                    case 3: {
                        if (controlador.validarListaActividades(usuarioMaster)) {
                            System.out.println("\n[Sistema]: No tienes actividades aún.");
                            break;
                        }
                        System.out.println(controlador.mostrarActividades(usuarioMaster));
                        System.out.println("\n[Sistema]: Ingresa el número de Actividad que deseas editar: ");
                        String numOpt = entrada.nextLine();

                        if (!esCadenaNumeroValida(numOpt)) {
                            System.out.println("\n[Sistema]: Por favor, ingrese una entrada válida.");
                            continue;
                        }
                        int indiceActividad = Integer.parseInt(numOpt);

                        if (controlador.validarIndiceActividad(usuarioMaster, indiceActividad)) {
                            System.out.println("[Sistema]: Ingresa el nuevo nombre de la Actividad: ");
                            String nuevoNombreActividad = entrada.nextLine();
                            System.out.println("[Sistema]: Ingresa la nueva fecha para la Actividad (dd/mm/aaaa): ");
                            String nuevaFecha = entrada.nextLine();
                            System.out.println("[Sistema]: Ingresa la nueva hora para la Actividad (hh:mm): ");
                            String nuevaHora = entrada.nextLine();
                            System.out.println(controlador.editarActividad(usuarioMaster, indiceActividad, nuevaFecha,
                                    nuevaHora, nuevoNombreActividad));
                            break;
                        }
                        System.out.println("\n[Sistema]: Error02. Número de actividad seleccionada inválido.");
                        break;
                    }
                    case 4: {
                        if (controlador.validarListaActividades(usuarioMaster)) {
                            System.out.println("\n[Sistema]: No tienes actividades aún.");
                            break;
                        }
                        System.out.println(controlador.mostrarActividades(usuarioMaster));
                        System.out
                                .println("\n[Sistema]: Ingresa el número de Actividad que deseas cambiar de estado: ");
                        String numOpt = entrada.nextLine();

                        if (!esCadenaNumeroValida(numOpt)) {
                            System.out.println("\n[Sistema]: Por favor, ingrese una entrada válida.");
                            continue;
                        }
                        int indiceActividad = Integer.parseInt(numOpt);

                        System.out.println(controlador.cambiarEstadoActividad(usuarioMaster, indiceActividad));
                        break;
                    }
                    case 5: {
                        if (controlador.validarListaActividades(usuarioMaster)) {
                            System.out.println("\n[Sistema]: No tienes actividades aún.");
                            break;
                        }
                        System.out.println(controlador.mostrarActividades(usuarioMaster));
                        System.out.println("\n[Sistema]: Ingresa el número de Actividad que deseas eliminar: ");
                        String numOpt = entrada.nextLine();

                        if (!esCadenaNumeroValida(numOpt)) {
                            System.out.println("\n[Sistema]: Por favor, ingrese una entrada válida.");
                            continue;
                        }
                        int indiceActividad = Integer.parseInt(numOpt);

                        System.out.println(controlador.eliminarActividad(usuarioMaster, indiceActividad));
                        break;
                    }
                    case 6: {
                        if (controlador.validarListaActividades(usuarioMaster)) {
                            System.out.println("\n[Sistema]: No tienes actividades aún.");
                            break;
                        }
                        System.out.println(controlador.mostrarActividadesPendientes(usuarioMaster));
                        break;
                    }
                    case 7: {
                        salirMenuInicial = false;
                        salirMenuPrincipal = true;
                        System.out.println("\n[Sistema]: Sesión finalizada con éxito.");
                        break;
                    }
                    default:
                        System.out.println("\n[Sistema]: Error03. Por favor, ingresa una opción válida del menú.");
                        break;
                }
            }
        }
    }

    private static boolean esCadenaNumeroValida(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}