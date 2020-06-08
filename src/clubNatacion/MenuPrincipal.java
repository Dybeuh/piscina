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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPrincipal extends JFrame implements WindowListener, ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	Date fechaHora = new Date();
	DateFormat fechaHoraFormato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	int usu;
	// Menú
	JMenuBar barraMenu = new JMenuBar();
	JMenu menuSocios = new JMenu("Socios");
	JMenu menuMonitores = new JMenu("Monitores");
	JMenu menuClases = new JMenu("Clases");
	JMenu menuFacturas = new JMenu("Facturas");
	JMenu menuPiscinas = new JMenu("Piscinas");
	JMenu menuAyuda = new JMenu("Ayuda");
	JMenu menuCerrar = new JMenu("Cerrar sesión");
	JMenuItem miSociosAlta = new JMenuItem("Nuevo socio");
	JMenuItem miSociosModificar = new JMenuItem("Modificar socio");
	JMenuItem miSociosBaja = new JMenuItem("Eliminar socio");
	JMenuItem miSociosConsulta = new JMenuItem("Consulta socio");
	JMenuItem miMonitoresAlta = new JMenuItem("Nuevo monitor");
	JMenuItem miMonitoresModificar = new JMenuItem("Modificar monitor");
	JMenuItem miMonitoresBaja = new JMenuItem("Eliminar monitor");
	JMenuItem miMonitoresConsulta = new JMenuItem("Consultar monitor");
	JMenuItem miClasesAlta = new JMenuItem("Nueva clase");
	JMenuItem miClasesModificar = new JMenuItem("Modificar clase");
	JMenuItem miClasesBaja = new JMenuItem("Eliminar clase");
	JMenuItem miClasesConsulta = new JMenuItem("Consulta clase");
	JMenuItem miFacturaAlta = new JMenuItem("Nueva factura");
	JMenuItem miFacturaModificar = new JMenuItem("Modificar factura");
	JMenuItem miFacturaBaja = new JMenuItem("Eliminar factura");
	JMenuItem miFacturaConsulta = new JMenuItem("Consulta factura");
	JMenuItem miPiscinaAlta = new JMenuItem("Nueva Piscina");
	JMenuItem miPiscinaModificar = new JMenuItem("Modificar Piscina");
	JMenuItem miPiscinaBaja = new JMenuItem("Eliminar Piscina");
	JMenuItem miPiscinaConsulta = new JMenuItem("Consulta Piscina");
	/* Componentes básicos */
	// Etiquetas
	JLabel lblTitulo = new JLabel("Club de Natación");
	JLabel lblIcono = new JLabel();
	// Paneles y Dialogos
	JPanel pnl1 = new JPanel();
	// Definir un color y fuente
	Color color = new Color(77, 190, 235);
	Font fuenteTitulo = new Font("Candara", 3, 40);
	/* Añadir imágenes */
	ImageIcon imagen = new ImageIcon("../../logo3.png");
	Image img = imagen.getImage();
	Image newimg = img.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
	ImageIcon imagentnf = new ImageIcon(newimg);

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/clubnatacion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String login = "root";
	String password = "Campesino89!";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	MenuPrincipal(int tu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		usu = tu;
		setTitle("Menú");
		setLayout(new GridLayout(1, 1));
		setSize(430, 460);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);
		lblTitulo.setFont(fuenteTitulo);
		pnl1.add(lblTitulo);
		lblTitulo.setBounds(60, 0, 500, 100);
		lblIcono.setIcon(imagentnf);
		pnl1.add(lblIcono);
		lblIcono.setBounds(60, 80, 300, 300);
		pnl1.setBackground(color);
		add(pnl1);
		// Menú
		menuSocios.add(miSociosAlta);
		menuMonitores.add(miMonitoresAlta);
		menuClases.add(miClasesAlta);
		menuFacturas.add(miFacturaAlta);
		menuPiscinas.add(miPiscinaAlta);
		if (tu == 0) {
			usu = tu;
			menuSocios.add(miSociosModificar);
			menuSocios.add(miSociosBaja);
			menuSocios.add(miSociosConsulta);
			menuMonitores.add(miMonitoresModificar);
			menuMonitores.add(miMonitoresBaja);
			menuMonitores.add(miMonitoresConsulta);
			menuClases.add(miClasesModificar);
			menuClases.add(miClasesBaja);
			menuClases.add(miClasesConsulta);
			menuFacturas.add(miFacturaModificar);
			menuFacturas.add(miFacturaBaja);
			menuFacturas.add(miFacturaConsulta);
			menuPiscinas.add(miPiscinaModificar);
			menuPiscinas.add(miPiscinaBaja);
			menuPiscinas.add(miPiscinaConsulta);
		}
		barraMenu.add(menuSocios);
		barraMenu.add(menuMonitores);
		barraMenu.add(menuClases);
		barraMenu.add(menuPiscinas);
		barraMenu.add(menuFacturas);
		barraMenu.add(menuAyuda);
		barraMenu.add(menuCerrar);
		setJMenuBar(barraMenu);
//Listeners y visibilidad   
		miSociosAlta.addActionListener(this);
		miSociosModificar.addActionListener(this);
		miSociosBaja.addActionListener(this);
		miSociosConsulta.addActionListener(this);
		miMonitoresAlta.addActionListener(this);
		miMonitoresModificar.addActionListener(this);
		miMonitoresBaja.addActionListener(this);
		miMonitoresConsulta.addActionListener(this);
		miClasesAlta.addActionListener(this);
		miClasesModificar.addActionListener(this);
		miClasesBaja.addActionListener(this);
		miClasesConsulta.addActionListener(this);
		miFacturaAlta.addActionListener(this);
		miFacturaModificar.addActionListener(this);
		miFacturaBaja.addActionListener(this);
		miFacturaConsulta.addActionListener(this);
		miPiscinaAlta.addActionListener(this);
		miPiscinaBaja.addActionListener(this);
		miPiscinaConsulta.addActionListener(this);
		miPiscinaModificar.addActionListener(this);
		menuAyuda.addMouseListener(this);
		menuCerrar.addMouseListener(this);
		setVisible(true);

		// Cargar el Driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		// Establecer la conexión con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//SOCIOS   
		if (miSociosAlta.equals(arg0.getSource())) {
			new Socio().nuevoSocio(this, usu);
		}
		if (miSociosModificar.equals(arg0.getSource())) {
			new Socio().modificarSocio(this, usu);
		}
		if (miSociosBaja.equals(arg0.getSource())) {
			new Socio().eliminarSocio(this, usu);
		}
		if (miSociosConsulta.equals(arg0.getSource())) {
			new Socio().consultarSocio(this, usu);
		}
//MONITORES	
		if (miMonitoresAlta.equals(arg0.getSource())) {
			new Monitor().nuevoMonitor(this, usu);
		}
		if (miMonitoresModificar.equals(arg0.getSource())) {
			new Monitor().modificarMonitor(this, usu);
		}
		if (miMonitoresBaja.equals(arg0.getSource())) {
			new Monitor().eliminarMonitor(this, usu);
		}
		if (miMonitoresConsulta.equals(arg0.getSource())) {
			new Monitor().consultarMonitor(this, usu);
		}
//CLASES 
		if (miClasesAlta.equals(arg0.getSource())) {
			new Clase().nuevaClase(this, usu);
		}
		if (miClasesModificar.equals(arg0.getSource())) {
			new Clase().modificarClase(this, usu);
		}
		if (miClasesBaja.equals(arg0.getSource())) {
			new Clase().eliminarClase(this, usu);
		}
		if (miClasesConsulta.equals(arg0.getSource())) {
			new Clase().consultarClase(this, usu);
		}
// PISCINAS
		if (miPiscinaAlta.equals(arg0.getSource())) {
			new Piscina().nuevoPiscina(this, usu);
		}
		if (miPiscinaModificar.equals(arg0.getSource())) {
			new Piscina().modificarPiscina(this, usu);
		}
		if (miPiscinaBaja.equals(arg0.getSource())) {
			new Piscina().eliminarPiscina(this, usu);
		}
		if (miPiscinaConsulta.equals(arg0.getSource())) {
			new Piscina().consultarPiscina(this, usu);
		}
// FACTURAS
		if (miFacturaAlta.equals(arg0.getSource())) {
			new Factura().nuevoFactura(this, usu);
		}
		if (miFacturaModificar.equals(arg0.getSource())) {
			new Factura().modificarFactura(this, usu);
		}
		if (miFacturaBaja.equals(arg0.getSource())) {
			new Factura().eliminarFactura(this, usu);
		}
		if (miFacturaConsulta.equals(arg0.getSource())) {
			new Factura().consultarFactura(this, usu);
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(menuCerrar)) {
			int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de querer cerrar la sesión?", "Cerrar sesión",
					JOptionPane.YES_NO_OPTION);
			if (opcion == JOptionPane.YES_OPTION) {
				if (usu == 0) {
					try {
//Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src por defecto, sino poner ruta)      
//El true es para que no se machaque el texto 
						FileWriter fw = new FileWriter("movimientos.log", true);
//Buffer para poder acceder al fichero       
						BufferedWriter bf = new BufferedWriter(fw);
//Para poder escribir en el fichero       
						PrintWriter salida = new PrintWriter(bf);
//Para escribir   
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Cerrar sesión]\n");
//Cerrar lo creado en orden inverso 
						salida.close();
						bf.close();
						fw.close();
					} catch (IOException er) {
						System.out.println("Error");
					}
				} else if (usu == 1) {
					try {
//Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src por defecto, sino poner ruta) 
//El true es para que no se machaque el texto 
						FileWriter fw = new FileWriter("movimientos.log", true);
//Buffer para poder acceder al fichero       
						BufferedWriter bf = new BufferedWriter(fw);
						PrintWriter salida = new PrintWriter(bf);
//Para poder escribir en el fichero       
//Para escribir       
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Usuario][Cerrar sesión]\n");
//Cerrar lo creado en orden inverso 
						salida.close();
						bf.close();
						fw.close();
					} catch (IOException er) {
						System.out.println("Error");
					}
				}
				setVisible(false);
				new Login();
			}
		}
		if (e.getSource().equals(menuAyuda)) {
			new Ayuda().TxtAyuda(this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
