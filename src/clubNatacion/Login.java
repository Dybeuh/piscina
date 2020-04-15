package clubNatacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame implements WindowListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	int usu;
	Date fechaHora = new Date();
	DateFormat fechaHoraFormato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/* Componentes básicos */

	// Etiquetas
	JLabel lblNom = new JLabel("Nombre:");
	JLabel lblPass = new JLabel("Password:");
	JLabel lblIcono = new JLabel();
	JLabel lblIconoPreg = new JLabel();
	JLabel lblOlvido = new JLabel("No recuerdo mis datos");
	JLabel lblEmail = new JLabel("Introduce tu email para recuperar tus datos:");
	JLabel lblTitulo = new JLabel("AquaStudium");

	// Casillas de texto
	JTextField txtNom = new JTextField(20);
	JPasswordField txtPass = new JPasswordField(20);
	JTextField txtEmail = new JTextField(50);

	// Botones
	JButton btnLgn = new JButton("Login");
	JButton btnBrr = new JButton("Borrar");
	JButton btnAceptar = new JButton("Aceptar");

	// Paneles y Dialogos
	JPanel pnl1 = new JPanel();
	JDialog dlgOlv = new JDialog(this);

	/* Añadir imágenes */

	// Logo
	ImageIcon imagen = new ImageIcon("../../logo.png");
	Image img = imagen.getImage();
	Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon imagentnf = new ImageIcon(newimg);

	// Icono Pregunta
	ImageIcon imagenIconPreg = new ImageIcon("../../logo2.jpg");
	Image imgIconPreg = imagenIconPreg.getImage();
	Image newimgIconPreg = imgIconPreg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	ImageIcon imagentntIconPreg = new ImageIcon(newimgIconPreg);

	// Definir un color y fuente
	Color color = new Color(77, 190, 235);
	Font fuenteTitulo = new Font("Candara", 3, 20);
	Font fuenteOlvidoIn = new Font("Dialog", 1, 13);
	Font fuenteOlvidoOut = new Font("Dialog", 1, 12);

	// Patrón
	String emailPatron = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[az0-9-]+)*(\\.[a-z]{2,4})$";
	Pattern pattern = Pattern.compile(emailPatron);

	Login() {
		// Pantalla Login
		setTitle("Login");
		setLayout(new GridLayout(1, 1));
		setSize(330, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);
		lblIcono.setIcon(imagentnf);
		pnl1.add(lblIcono);
		lblIcono.setBounds(95, 15, 100, 100);
		lblTitulo.setFont(fuenteTitulo);
		pnl1.add(lblTitulo);
		lblTitulo.setBounds(90, 80, 200, 100);
		pnl1.add(lblNom);
		lblNom.setBounds(55, 155, 60, 10);
		pnl1.add(txtNom);
		txtNom.setBounds(110, 148, 120, 25);
		pnl1.add(lblPass);
		lblPass.setBounds(42, 195, 70, 10);
		pnl1.add(txtPass);
		txtPass.setBounds(110, 188, 120, 25);
		pnl1.add(btnLgn);
		btnLgn.setBounds(35, 240, 100, 30);
		pnl1.add(btnBrr);
		btnBrr.setBounds(145, 240, 100, 30);
		lblOlvido.setHorizontalAlignment(SwingConstants.CENTER);
		pnl1.add(lblOlvido);
		lblOlvido.setBounds(65, 300, 150, 30);
		lblOlvido.addMouseListener(this);
		pnl1.setBackground(color);
		add(pnl1);
		// Dialogo Email
		dlgOlv.setTitle("Recuperar los datos");
		dlgOlv.setLayout(null);
		dlgOlv.setSize(350, 150);
		dlgOlv.setResizable(false);
		dlgOlv.setLocationRelativeTo(null);
		dlgOlv.add(lblEmail);
		lblEmail.setBounds(60, 10, 300, 20);
		lblIconoPreg.setIcon(imagentntIconPreg);
		dlgOlv.add(lblIconoPreg);
		lblIconoPreg.setBounds(5, 10, 50, 50);
		dlgOlv.add(txtEmail);
		txtEmail.setBounds(60, 35, 260, 25);
		dlgOlv.add(btnAceptar);
		btnAceptar.setBounds(130, 70, 100, 30);
		// Listeners y visibilidad
		btnBrr.addActionListener(this);
		btnAceptar.addActionListener(this);
		btnLgn.addActionListener(this);
		addWindowListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Login();
	}

	// Borrar los txt rápidamente
	public void borrarTxt(JTextField txt) {
		txt.selectAll();
		txt.setText("");
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (btnBrr.equals(arg0.getSource())) {
			borrarTxt(txtNom);
			borrarTxt(txtPass);
		}
		if (btnLgn.equals(arg0.getSource())) {
			char[] passPers = txtPass.getPassword();
			String pass = new String(passPers);
			if (pass.equals("2020") && txtNom.getText().equals("Administrador")) {
				usu = 0;
				try {
					// Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src
					// por defecto, sino poner ruta)
					// El true es para que el texto no se destruya
					FileWriter fw = new FileWriter("movimientos.log", true);
					// Buffer para poder acceder al fichero
					BufferedWriter bf = new BufferedWriter(fw);
					// Para poder escribir en el fichero
					PrintWriter salida = new PrintWriter(bf);
					// Para escribir
					salida.println("[" + fechaHoraFormato.format(fechaHora) + "][" + txtNom.getText() + "][Login]\n");
					// Cerrar
					salida.close();
					bf.close();
					fw.close();
				} catch (IOException e) {
					System.out.println("Error");
				}
				borrarTxt(txtNom);
				borrarTxt(txtPass);
				setVisible(false);
				new MenuPrincipal(usu);

			} else if (pass.equals("2020") && txtNom.getText().equals("Usuario")) {
				usu = 1;
				try {
//Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src por defecto, sino poner ruta)
//El true es para que el texto no se destruya	
					FileWriter fw = new FileWriter("movimientos.log", true);
//Buffer para poder acceder al fichero      
					BufferedWriter bf = new BufferedWriter(fw);
//Para poder escribir en el fichero      
					PrintWriter salida = new PrintWriter(bf);
//Para escribir 
					salida.println("[" + fechaHoraFormato.format(fechaHora) + "][" + txtNom.getText() + "][Login]\n");
//Cerrar lo creado en orden inverso 
					salida.close();
					bf.close();
					fw.close();
				} catch (IOException e) {
					System.out.println("Error");
				}
				borrarTxt(txtNom);
				borrarTxt(txtPass);
				setVisible(false);
				new MenuPrincipal(usu);
			} else {
				borrarTxt(txtNom);
				borrarTxt(txtPass);
				JOptionPane.showMessageDialog(dlgOlv, "El nombre o la contraseña no son correctos ", "Mensaje de ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (btnAceptar.equals(arg0.getSource())) {
			if (!txtEmail.getText().equals("")) {
				if (txtEmail.getText().equals("Dybeuh@gmail.com")) {
					JOptionPane.showMessageDialog(dlgOlv, "Email de recuperación de credenciales enviado ");
					dlgOlv.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(dlgOlv, "Este email no ha sido registrado ", "Mensaje de ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(dlgOlv, "No se ha introducido ningún email ", "Mensaje de ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getComponent().equals(lblOlvido)) {
			borrarTxt(txtEmail);
			dlgOlv.setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		lblOlvido.setFont(fuenteOlvidoIn);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		lblOlvido.setFont(fuenteOlvidoOut);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}