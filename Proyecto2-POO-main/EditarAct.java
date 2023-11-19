import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditarAct extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private UVGenda controlador;
	private DefaultTableModel model;
	private static String usuarioXD;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				UVGenda controlador = new UVGenda();
				controlador.cargarDatosDesdeCSV();
				try {
					EditarAct frame = new EditarAct(controlador, usuarioXD);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditarAct(UVGenda controlador, String usuario) {
		this.controlador = controlador;
		this.usuarioXD = usuario;
		
		
		setBackground(new Color(172, 221, 253));
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 415);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(172, 221, 253));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BEliminar = new JButton("Eliminar");
		BEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 String userInput = JOptionPane.showInputDialog("Ingrese el número de la actividad a eliminar:");

				 try {
					 int numeroActividad = Integer.parseInt(userInput);
					 String mensaje = controlador.eliminarActividad(usuario, numeroActividad);
					 JOptionPane.showMessageDialog(null, mensaje);
					 cargarDatosTabla();
				 } catch (NumberFormatException ex) {
					 JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
				 }
			}
		});
		BEliminar.setBounds(95, 228, 125, 29);
		contentPane.add(BEliminar);
		
		JButton BEditar = new JButton("Editar");
		BEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                String userInputFecha = JOptionPane.showInputDialog("Ingrese la nueva fecha:");
                String userInputHora = JOptionPane.showInputDialog("Ingrese la nueva hora:");
                String userInputNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la actividad:");
                try {
                    int numeroActividad = obtenerNumeroActividadParaEditar();
                    String mensaje = controlador.editarActividad(usuario, numeroActividad, userInputFecha, userInputHora, userInputNombre);
                    JOptionPane.showMessageDialog(null, mensaje);
                    cargarDatosTabla();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
			}
		});
		BEditar.setBounds(230, 228, 125, 29);
		contentPane.add(BEditar);
		
		JButton BEstado = new JButton("Cambiar Estado");
		BEstado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                String userInput = JOptionPane.showInputDialog("Ingrese el número de la actividad:");
                try {
                    int numeroActividad = Integer.parseInt(userInput);
                    if (controlador.validarIndiceActividad(usuario, numeroActividad)) {
                        String mensaje = controlador.cambiarEstadoActividad(usuario, numeroActividad);
                        JOptionPane.showMessageDialog(null, mensaje);
                        cargarDatosTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Número de actividad inválido.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
			}
		});
		BEstado.setBounds(365, 228, 144, 29);
		contentPane.add(BEstado);
		
		JButton Bregresar = new JButton("Back");
		Bregresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					setVisible(false);
					Pagina venPagina = new Pagina(controlador,usuario);
					venPagina.setVisible(true);
			}
		});
		Bregresar.setBounds(527, 307, 110, 40);
		contentPane.add(Bregresar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 627, 133);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Nombre Actividad");
        model.addColumn("Fecha de Entrega");
        model.addColumn("Hora de Entrega");
        model.addColumn("Estado");
        scrollPane.setViewportView(table);
        cargarDatosTabla();
	   	
	}
	private void cargarDatosTabla() {
        String x = usuarioXD;
        System.out.println(x);

        model.setRowCount(0);

        for (Usuario usuario : controlador.usuarios) {
            System.out.println(x);

            if (usuario.getNombreUsuario().equals(x)) {
            	System.out.println(usuario);
                for (Actividad actividad : usuario.getListaDeActividades()) {
                    Object[] rowData = {
                        actividad.getNombreActividad(),
                        actividad.getFecha(),
                        actividad.getHora(),
                        actividad.getEstado() ? "Completado" : "Pendiente"
                    };
                    model.addRow(rowData);
                    System.out.println("Actividad agregada: " + actividad.getNombreActividad());
                }
            }
        }
    }

	private int obtenerNumeroActividadParaEditar() {
		String userInput = JOptionPane.showInputDialog("Ingrese el número de la actividad a editar:");
		
		try {
			return Integer.parseInt(userInput);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
			return -1;
		}
	}
	

}
