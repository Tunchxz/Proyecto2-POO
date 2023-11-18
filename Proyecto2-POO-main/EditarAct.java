import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

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
		BEliminar.setBounds(95, 228, 125, 29);
		contentPane.add(BEliminar);
		
		JButton BEditar = new JButton("Editar");
		BEditar.setBounds(230, 228, 125, 29);
		contentPane.add(BEditar);
		
		JButton BEstado = new JButton("Cambiar Estado");
		BEstado.setBounds(365, 228, 144, 29);
		contentPane.add(BEstado);
		
		JButton Bregresar = new JButton("Back");
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
	
	
	
	
}
