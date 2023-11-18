import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class InicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField tUsuario;
	private JPasswordField tContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				UVGenda controlador = new UVGenda();
				
				try {
					InicioSesion frame = new InicioSesion();
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
	public InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 474);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 228, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(105, 94, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setBounds(94, 224, 72, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton BListo = new JButton("Listo !");
		BListo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UVGenda controlador = new UVGenda();
				controlador.cargarUsuariosDesdeCSV(); 
				String usuario = tUsuario.getText();
                String contrasena = new String(tContrasena.getPassword());
                if (controlador.iniciarSesion(usuario, contrasena)) {
                	Pagina ventanaPagina = new Pagina(controlador, usuario);
                	System.out.println(usuario);
                    ventanaPagina.setVisible(true);
                    dispose();
                } else {
                	 JOptionPane.showMessageDialog(null, "Inicio de sesión fallido.");
                }
			}
		});
		BListo.setBounds(202, 334, 89, 23);
		contentPane.add(BListo);
		
		tUsuario = new JTextField();
		tUsuario.setBounds(205, 84, 159, 35);
		contentPane.add(tUsuario);
		tUsuario.setColumns(10);
		
		tContrasena = new JPasswordField();
		tContrasena.setBounds(205, 219, 152, 30);
		contentPane.add(tContrasena);
		
		JButton bRegresar = new JButton("Regresar");
		bRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Inicio venInicio = new Inicio();
				venInicio.setVisible(true);
			}
		});
		bRegresar.setBounds(409, 390, 89, 23);
		contentPane.add(bRegresar);
	}
}
