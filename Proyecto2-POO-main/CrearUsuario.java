import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class CrearUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField tNombre;
	private JPasswordField tContraCrearUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				UVGenda controladorGenda = new UVGenda();
				try {
					CrearUsuario frame = new CrearUsuario();
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
	public CrearUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 448);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(170, 209, 253));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tNombre = new JTextField();
		tNombre.setBounds(245, 110, 217, 38);
		contentPane.add(tNombre);
		tNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre Usuario:");
		lblNewLabel.setBounds(61, 120, 126, 14);
		contentPane.add(lblNewLabel);
		
		tContraCrearUsuario = new JPasswordField();
		tContraCrearUsuario.setBounds(245, 182, 217, 38);
		contentPane.add(tContraCrearUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setBounds(84, 192, 103, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton BCrearusuario = new JButton("Crear Usuario");
		BCrearusuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreUsuario = tNombre.getText();
				String contrasena = new String(tContraCrearUsuario.getPassword());
				
				UVGenda controlador = new UVGenda();
				boolean creado = controlador.crearUsuario(nombreUsuario, contrasena);
				if(creado) {
					JOptionPane.showMessageDialog(null, "Usuario creado exitosamente.");
				}else {
					JOptionPane.showMessageDialog(null, "El usuario ya existe.");
				}
				
			}
		});
		BCrearusuario.setBounds(208, 264, 138, 30);
		contentPane.add(BCrearusuario);
		
		JButton Back = new JButton("Back");
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Inicio venInicio = new Inicio();
				venInicio.setVisible(true);
			}
		});
		Back.setBounds(462, 372, 89, 23);
		contentPane.add(Back);
	}

}
