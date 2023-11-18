import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import java.awt.Color;

public class Pagina extends JFrame {

	private JPanel contentPane;
	private JTextField tNombreAct;
	private JTextField tFecha;
	private JTextField tHora;
	private JTextField tNombreUsuario;
	private UVGenda controlador;
	private static String usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				UVGenda controlador = new UVGenda();
				controlador.cargarDatosDesdeCSV();

				try {
					Pagina frame = new Pagina(controlador,usuario);
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
	public Pagina(UVGenda controlador, String usuario) {
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 367);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 206));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Actividad:");
		lblNewLabel.setBounds(10, 77, 136, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de Entrega:");
		lblNewLabel_1.setBounds(10, 135, 136, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hora de Entrega:");
		lblNewLabel_2.setBounds(16, 194, 130, 14);
		contentPane.add(lblNewLabel_2);
		
		tNombreAct = new JTextField();
		tNombreAct.setBounds(171, 80, 226, 23);
		contentPane.add(tNombreAct);
		tNombreAct.setColumns(10);
		
		tFecha = new JTextField();
		tFecha.setBounds(171, 138, 226, 23);
		contentPane.add(tFecha);
		tFecha.setColumns(10);
		
		tHora = new JTextField();
		tHora.setBounds(175, 194, 222, 23);
		contentPane.add(tHora);
		tHora.setColumns(10);
		
		JButton BCrear = new JButton("Crear Actividad");
		BCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreUsuario = tNombreUsuario.getText();
		        String nombreAct = tNombreAct.getText();
		        String fecha = tFecha.getText();
		        String hora = tHora.getText();
		        
		        String mensaje = controlador.crearActividad(nombreUsuario, hora, nombreAct, fecha);
		        
		        JOptionPane.showMessageDialog(null, mensaje);
				
				
			}
		});
		BCrear.setBounds(54, 249, 136, 23);
		contentPane.add(BCrear);
		
		JButton BMostrar = new JButton("Mostrar Actividades");
		BMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
		        String nombreUsuario = tNombreUsuario.getText();
		        EditarAct venEditar = new EditarAct(controlador, usuario);
		        System.out.println(usuario);
		        venEditar.setVisible(true);
			}
		});
		BMostrar.setBounds(235, 249, 162, 23);
		contentPane.add(BMostrar);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre Usuario: ");
		lblNewLabel_3.setBounds(16, 30, 119, 14);
		contentPane.add(lblNewLabel_3);
		
		tNombreUsuario = new JTextField();
		tNombreUsuario.setBounds(171, 30, 226, 23);
		contentPane.add(tNombreUsuario);
		tNombreUsuario.setColumns(10);
	}
	
    
}
