import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Inicio extends JFrame {

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 560);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Iniciar Sesión");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				InicioSesion VenInicioSesion = new InicioSesion();
				VenInicioSesion.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(165, 251, 159, 50);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				CrearUsuario venCrearUsuario = new CrearUsuario();
				venCrearUsuario.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		btnNewButton_1.setBounds(165, 337, 159, 50);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\dulce\\OneDrive\\Escritorio\\Proyecto2\\Proyecto2\\uvvgenda!.png"));
		lblNewLabel.setBounds(0, 0, 494, 530);
		getContentPane().add(lblNewLabel);
		
		
		
	}
}
