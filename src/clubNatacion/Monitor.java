package clubNatacion;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Monitor extends JFrame implements WindowListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	Date fechaHora = new Date();
	DateFormat fechaHoraFormato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	JFrame menu = new JFrame();
	int usu;

	/* ALTA */
	// Etiquetas
	JLabel lblTituloAlt = new JLabel("NUEVO MONITOR");
	JLabel lblNom = new JLabel("*Nombre:");
	JLabel lblDni = new JLabel("*DNI (Sin letra):");
	JLabel lblAp1 = new JLabel("*Primer apellido:");
	JLabel lblAp2 = new JLabel(" Segundo apellido:");
	JLabel lblDir = new JLabel("*Dirección:");
	JLabel lblTel = new JLabel("*Telefono:");
	JLabel lblExp = new JLabel("*Años de exp");
	JLabel lblEst = new JLabel("*Estilo");
	JLabel lblidM = new JLabel("idMonitor:");
	JLabel lblAdv = new JLabel("--Los campos con * son obligatorios--");
	// Casillas de texto
	JTextField txtNomAlt = new JTextField(20);
	JTextField txtDniAlt = new JTextField(20);
	JTextField txtAp1Alt = new JTextField(20);
	JTextField txtAp2Alt = new JTextField(20);
	JTextField txtDirAlt = new JTextField(20);
	JTextField txtTelAlt = new JTextField(20);
	JTextField txtExpAlt = new JTextField(20);
	JTextField txtEstAlt = new JTextField(20);
	JTextField txtidMAlt = new JTextField(20);
	// Botones
	JButton btnBrrAlt = new JButton("Borrar todo");
	JButton btnDarAlt = new JButton("Dar de alta");
	/* MODIFICACIÓN */
	// Etiquetas
	JLabel lblTituloMod = new JLabel("MODIFICAR MONITOR");
	JLabel lblSelecMod = new JLabel("Monitor seleccionado:");
	JLabel lblCampMod = new JLabel("Seleccione campo a modificar:");
	// Casillas de texto
	JTextField txtBuscMod = new JTextField(20);
	JTextField txtMonitorMod = new JTextField(20);
	JTextField txtModMod = new JTextField(20);
	// Botones
	JButton btnBuscMod = new JButton("Buscar");
	JButton btnModMod = new JButton("Modificar");
	// Lista
	List lstMod = new List(3, false);
	Choice chcLstAtrib = new Choice();
	// Separador
	JSeparator separador = new JSeparator();

	/* BORRAR */
	JLabel lblTituloBaj = new JLabel("ELIMINAR MONITOR");
	JButton btnElmBaj = new JButton("Dar de baja");
	JButton btnBuscElm = new JButton("Buscar");
	JTextField txtBuscElm = new JTextField(20);
	JTextField txtMonitorElm = new JTextField(20);
	List lstBrr = new List(3, false);
	/* Se ha reutilizado componentes de la sección modificar: labels */

	/* CONSULTA */
	JLabel lblTituloCons = new JLabel("CONSULTAR MONITOR");
	JLabel lblIdCons = new JLabel("Id:");
	JTextField txtIdCons = new JTextField(20);
	JTextField txtMonitorCons = new JTextField(20);
	JTextField txtBuscCons = new JTextField(20);
	JButton btnBuscCons = new JButton("Buscar");
	List lstCons = new List(3, false);
	/* Se ha reutilizado componentes de la sección modificar y alta: labels */
	// Paneles y Diálogos
	JPanel pnl1 = new JPanel();
	// Definir un color y fuente
	Color color = new Color(238, 182, 240);
	Font fuenteTitulo = new Font("Candara", 3, 20);
	// Conexión con BBDD
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/clubnatacion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String login = "root";
	String password = "Campesino89!";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	void nuevoMonitor(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Nuevo monitor");
		setLayout(new GridLayout(1, 1));
		setSize(350, 420);
		setLocationRelativeTo(null);
		setResizable(true);
		pnl1.setLayout(null);
		// Añadir componentes
		lblTituloAlt.setFont(fuenteTitulo);
		pnl1.add(lblTituloAlt);
		lblTituloAlt.setBounds(100, 5, 300, 50);
		pnl1.add(lblNom);
		lblNom.setBounds(89, 60, 60, 20);
		pnl1.add(txtNomAlt);
		txtNomAlt.setBounds(150, 60, 150, 25);
		pnl1.add(lblDni);
		lblDni.setBounds(55, 90, 150, 20);
		pnl1.add(txtDniAlt);
		txtDniAlt.setBounds(150, 90, 150, 25);
		pnl1.add(lblAp1);
		lblAp1.setBounds(45, 120, 150, 20);
		pnl1.add(txtAp1Alt);
		txtAp1Alt.setBounds(150, 120, 150, 25);
		pnl1.add(lblAp2);
		lblAp2.setBounds(35, 150, 150, 20);
		pnl1.add(txtAp2Alt);
		txtAp2Alt.setBounds(150, 150, 150, 25);
		pnl1.add(lblDir);
		lblDir.setBounds(35, 180, 150, 20);
		pnl1.add(txtDirAlt);
		txtDirAlt.setBounds(150, 180, 150, 25);
		pnl1.add(lblTel);
		lblTel.setBounds(35, 210, 150, 20);
		pnl1.add(txtTelAlt);
		txtTelAlt.setBounds(150, 210, 150, 25);
		pnl1.add(lblExp);
		lblExp.setBounds(35, 240, 150, 20);
		pnl1.add(txtExpAlt);
		txtExpAlt.setBounds(150, 240, 150, 25);
		pnl1.add(lblEst);
		lblEst.setBounds(35, 270, 150, 20);
		pnl1.add(txtEstAlt);
		txtEstAlt.setBounds(150, 270, 150, 25);
		pnl1.add(btnDarAlt);
		btnDarAlt.setBounds(35, 330, 120, 30);
		pnl1.add(btnBrrAlt);
		btnBrrAlt.setBounds(185, 330, 120, 30);
		pnl1.setBackground(color);
		add(pnl1);
		// Listeners y visibilidad
		btnDarAlt.addActionListener(this);
		btnBrrAlt.addActionListener(this);
		addWindowListener(this);
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

	void modificarMonitor(JFrame m, int u) {
		menu = m; setTitle("Modificar monitor");setLayout
		  (new GridLayout(1,1)); setSize(505, 270); setLocationRelativeTo(null);
		  setResizable(false); pnl1.setLayout(null);
		  
		  //Añadir componentes 
		  lblTituloMod.setFont(fuenteTitulo);
		  pnl1.add(lblTituloMod); lblTituloMod.setBounds(100, 5, 300, 50);
		usu = u;
		m.setEnabled(false);
		setTitle("Modificar Monitor");
		setLayout(new GridLayout(1, 1));
		setSize(350, 480);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);

		// Añadir componentes
		lblTituloAlt.setFont(fuenteTitulo);
		pnl1.add(lblTituloMod);
		lblTituloAlt.setBounds(120, 5, 300, 50);
		pnl1.add(lblNom);
		lblNom.setBounds(89, 60, 60, 20);
		pnl1.add(txtNomAlt);
		txtNomAlt.setBounds(150, 60, 150, 25);
		pnl1.add(lblDni);
		lblDni.setBounds(55, 150, 150, 20);
		pnl1.add(txtDniAlt);
		txtDniAlt.setBounds(150, 150, 150, 25);
		pnl1.add(lblAp1);
		lblAp1.setBounds(45, 90, 150, 20);
		pnl1.add(txtAp1Alt);
		txtAp1Alt.setBounds(150, 90, 150, 25);
		pnl1.add(lblAp2);
		lblAp2.setBounds(35, 120, 150, 20);
		pnl1.add(txtAp2Alt);
		txtAp2Alt.setBounds(150, 120, 150, 25);
		pnl1.add(lblDir);
		lblDir.setBounds(75, 180, 150, 20);
		pnl1.add(txtDirAlt);
		txtDirAlt.setBounds(150, 180, 150, 25);
		pnl1.add(lblTel);
		lblTel.setBounds(75, 210, 150, 20);
		pnl1.add(txtTelAlt);
		txtTelAlt.setBounds(150, 210, 150, 25);
		pnl1.add(lblExp);
		lblExp.setBounds(60, 240, 150, 20);
		pnl1.add(txtExpAlt);
		txtExpAlt.setBounds(150, 240, 150, 25);
		pnl1.add(lblEst);
		lblEst.setBounds(89, 270, 150, 20);
		pnl1.add(txtEstAlt);
		txtEstAlt.setBounds(150, 270, 150, 25);
		pnl1.add(lblidM);
		lblidM.setBounds(75, 300, 60, 20);
		pnl1.add(txtidMAlt);
		txtidMAlt.setBounds(150, 300, 150, 25);
		pnl1.add(lblAdv);
		lblAdv.setBounds(70, 340, 250, 30);
		pnl1.add(btnModMod);
		btnModMod.setBounds(35, 380, 120, 30);
		pnl1.add(btnBrrAlt);
		btnBrrAlt.setBounds(185, 380, 120, 30);
		pnl1.setBackground(color);
		add(pnl1);

		// Listeners y visibilidad
		btnModMod.addActionListener(this);
		btnBrrAlt.addActionListener(this);
		addWindowListener(this);
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

	void eliminarMonitor(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Eliminar monitor");
		setLayout(new GridLayout(1, 1));
		setSize(290, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);

		// Añadir componentes
		lblTituloBaj.setFont(fuenteTitulo);
		pnl1.add(lblTituloBaj);
		lblTituloBaj.setBounds(80, 5, 300, 50);

		pnl1.add(lblSelecMod);
		lblSelecMod.setBounds(10, 190, 260, 25);
		pnl1.add(txtMonitorElm);
		txtMonitorElm.setBounds(10, 220, 260, 25);
		txtMonitorElm.setEditable(true);
		pnl1.add(btnElmBaj);
		btnElmBaj.setBounds(90, 250, 100, 30);
		pnl1.setBackground(color);
		add(pnl1);
		// Listeners y visibilidad
		btnBuscElm.addActionListener(this);
		btnElmBaj.addActionListener(this);
		lstBrr.addMouseListener(this);
		addWindowListener(this);
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

	void consultarMonitor(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Consultar monitor");
		setLayout(new GridLayout(1, 1));
		setSize(570, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);

		// Añadir componentes
		lblTituloCons.setFont(fuenteTitulo);
		pnl1.add(lblTituloCons);
		lblTituloCons.setBounds(200, 5, 300, 50);
		pnl1.add(lblidM);
		lblidM.setBounds(10, 220, 225, 25);
		pnl1.add(txtidMAlt);
		txtidMAlt.setBounds(10, 250, 225, 25);
		txtidMAlt.setEditable(true);

		// Separador
		separador.setOrientation(SwingConstants.VERTICAL);
		pnl1.add(separador);
		separador.setBounds(260, 60, 2, 210);

		// Datos
		pnl1.add(btnBuscCons);
		btnBuscCons.setBounds(120, 300, 100, 30);
		pnl1.add(lblIdCons);
		lblIdCons.setBounds(377, 70, 60, 20);
		pnl1.add(txtIdCons);
		txtIdCons.setBounds(400, 70, 150, 25);
		txtIdCons.setEditable(false);
		pnl1.add(lblNom);
		lblNom.setBounds(340, 100, 60, 20);
		pnl1.add(txtNomAlt);
		txtNomAlt.setBounds(401, 100, 150, 25);
		txtNomAlt.setEditable(false);
		pnl1.add(lblAp1);
		lblAp1.setBounds(296, 130, 150, 20);
		pnl1.add(txtAp1Alt);
		txtAp1Alt.setBounds(401, 130, 150, 25);
		txtAp1Alt.setEditable(false);
		pnl1.add(lblAp2);
		lblAp2.setBounds(286, 160, 150, 20);
		pnl1.add(txtAp2Alt);
		txtAp2Alt.setBounds(401, 160, 150, 25);
		txtAp2Alt.setEditable(false);
		pnl1.add(lblDni);
		lblDni.setBounds(306, 190, 150, 20);
		pnl1.add(txtDniAlt);
		txtDniAlt.setBounds(401, 190, 150, 25);
		txtDniAlt.setEditable(false);
		pnl1.add(lblDir);
		lblDir.setBounds(330, 250, 150, 20);
		pnl1.add(txtDirAlt);
		txtDirAlt.setBounds(401, 250, 150, 25);
		txtDirAlt.setEditable(false);
		pnl1.add(lblTel);
		lblTel.setBounds(338, 220, 150, 20);
		pnl1.add(txtTelAlt);
		txtTelAlt.setBounds(401, 220, 150, 25);
		txtTelAlt.setEditable(false);
		pnl1.add(lblExp);
		lblExp.setBounds(315, 280, 150, 20);
		pnl1.add(txtExpAlt);
		txtExpAlt.setBounds(401, 280, 150, 25);
		txtExpAlt.setEditable(false);
		pnl1.add(lblEst);
		lblEst.setBounds(338, 310, 150, 20);
		pnl1.add(txtEstAlt);
		txtEstAlt.setBounds(401, 310, 150, 25);
		txtEstAlt.setEditable(false);
		pnl1.setBackground(color);
		add(pnl1);

		// Listeners y visibilidad
		btnBuscCons.addActionListener(this);
		lstCons.addMouseListener(this);
		addWindowListener(this);
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

	public void borrarTxt(JTextField txt) {
		txt.selectAll();
		txt.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// ALTA
		if (btnBrrAlt.equals(arg0.getSource())) {
			borrarTxt(txtNomAlt);
			borrarTxt(txtAp1Alt);
			borrarTxt(txtAp2Alt);
			borrarTxt(txtDniAlt);
			borrarTxt(txtTelAlt);
			borrarTxt(txtDirAlt);
			borrarTxt(txtEstAlt);
			borrarTxt(txtExpAlt);
		}

		if (btnDarAlt.equals(arg0.getSource())) {
			if ((txtNomAlt.getText().equals("")) | (txtAp1Alt.getText().equals(""))
					| (txtDniAlt.getText().equals(""))) {
				JOptionPane.showMessageDialog(this, "Uno o varios de los campos obligatorios NO han sido rellenados ",
						"Mensaje de ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
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
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Alta Monitor "
								+ txtNomAlt.getText() + " " + txtAp1Alt.getText() + "]\n");
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
//Para poder escribir en el fichero       
						PrintWriter salida = new PrintWriter(bf);
//Para escribir       
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Usuario][Alta Monitor "
								+ txtNomAlt.getText() + " " + txtAp1Alt.getText() + "] \n");
//Cerrar lo creado en orden inverso       
						salida.close();
						bf.close();
						fw.close();
					} catch (IOException er) {
						System.out.println("Error");
					}
				}

				// Preparar el statement
				try {
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					statement.executeUpdate(
							"INSERT INTO Monitores(nombreMonitor,apellido1Monitor,apellido2Monitor, dniMonitor, direccionMonitor, telefonoMonitor, experienciaMonitor, estiloNatacionMonitor) VALUES ('"
									+ txtNomAlt.getText() + "','" + txtAp1Alt.getText() + "','" + txtAp2Alt.getText()
									+ "','" + txtDniAlt.getText() + "','" + txtDirAlt.getText() + "','" + txtTelAlt.getText() + "','" + txtExpAlt.getText() + "','" + txtEstAlt.getText() + "')");
				} catch (SQLException e) {
					System.out.println("Error en la sentencia SQL");
				}

				JOptionPane.showMessageDialog(this, "El Monitor ha sido dado de alta correctamente", "Confirmación",
						JOptionPane.INFORMATION_MESSAGE);
				menu.setEnabled(true);
				setVisible(false);
			}
		}
//MODIFICACIÓN   
		if (btnBuscMod.equals(arg0.getSource())) {
			for (int i = 0; i < lstMod.getItemCount(); i++) {
				if (lstMod.getItem(i).equals(txtBuscMod.getText())) {
					lstMod.select(i);
				}
			}
		}
		if (btnModMod.equals(arg0.getSource())) {
			if (txtNomAlt.getText().equals("") | txtidMAlt.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
						"El monitor " + txtMonitorMod.getText() + " NO ha sido modificado correctamente",
						"Mensaje de ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
						"El monitor " + txtMonitorMod.getText() + " ha sido modificado correctamente", "Confirmación",
						JOptionPane.INFORMATION_MESSAGE);
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
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Modificar Monitor "
								+ txtMonitorMod.getText() + " en campo " + chcLstAtrib.getSelectedItem() + "]\n");
//Cerrar lo creado en orden inverso       
						salida.close();
						bf.close();
						fw.close();
					} catch (IOException er) {
						System.out.println("Error");
					}
				}
				menu.setEnabled(true);
				setVisible(false);
			}
			// Preparar el statement
			try {
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				statement.executeUpdate("UPDATE Monitores SET nombreMonitor='" + txtNomAlt.getText() + "', apellido1Monitor='"
						+ txtAp1Alt.getText() + "', apellido2Monitor='" + txtAp2Alt.getText() + "', dniMonitor='" + txtDniAlt.getText() + "', direccionMonitor='" + txtDirAlt.getText() + "', "
								+ "estiloNatacionMonitor='" + txtEstAlt.getText() + "', experienciaMonitor='" + txtExpAlt.getText() + "', telefonoMonitor='" + txtTelAlt.getText() + "' WHERE idMonitor=" + txtidMAlt.getText() + ";");

			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL");
			}

		}
//BAJA   
		if (btnBuscElm.equals(arg0.getSource())) {
			for (int i = 0; i < lstBrr.getItemCount(); i++) {
				if (lstBrr.getItem(i).equals(txtBuscElm.getText())) {
					lstBrr.select(i);
				}
			}
		}
		if (btnElmBaj.equals(arg0.getSource())) {
			if (!txtMonitorElm.getText().equals("")) {
				int opcion = JOptionPane.showConfirmDialog(this,
						"¿Estas seguro que deseas eliminar a " + txtMonitorElm.getText() + " ?", "Dar de Baja",
						JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(this,
							"El monitor " + txtMonitorElm.getText() + " ha sido eliminado correctamente", "Confirmación",
							JOptionPane.INFORMATION_MESSAGE);
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
							salida.println("[" + fechaHoraFormato.format(fechaHora)
									+ "][Administrador][Eliminar Monitor " + txtMonitorElm.getText() + "]\n");
//Cerrar lo creado en orden inverso        
							salida.close();
							bf.close();
							fw.close();
						} catch (IOException er) {
							System.out.println("Error");
						}
						try {
							statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_READ_ONLY);
							statement.executeUpdate(
									"DELETE FROM Monitores WHERE idMonitor ='" + txtMonitorElm.getText() + "';");
						} catch (SQLException e) {
							System.out.println("Error en la sentencia SQL");
							e.printStackTrace();
						}
					}
					menu.setEnabled(true);
					setVisible(false);

				}
			} else {
				JOptionPane.showMessageDialog(this, "NO ha sido seleccionado ningún monitor", "Mensaje de ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (btnBuscCons.equals(arg0.getSource())) {
			if (!txtidMAlt.getText().equals("")) {
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
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Eliminar Monitor"
								+ txtMonitorElm.getText() + "]\n");
//Cerrar lo creado en orden inverso        
						salida.close();
						bf.close();
						fw.close();
					} catch (IOException er) {
						System.out.println("Error");
					}
					try {
						statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						rs = statement.executeQuery("SELECT * FROM Monitores WHERE idMonitor=" + txtidMAlt.getText());
						if (rs.next()) {
							String dni = rs.getString("dniMonitor");
							String nombre = rs.getString("nombreMonitor");
							String apellido1 = rs.getString("apellido1Monitor");
							String apellido2 = rs.getString("apellido2Monitor");
							String telefono = Integer.toString(rs.getInt("telefonoMonitor"));
							String direccion = rs.getString("direccionMonitor");
							String experiencia = Integer.toString(rs.getInt("experienciaMonitor"));
							String estilo = rs.getString("estiloNatacionMonitor");
							// campos que aparecen
							txtDniAlt.setText(dni);
							txtNomAlt.setText(nombre);
							txtAp1Alt.setText(apellido1);
							txtAp2Alt.setText(apellido2);
							txtTelAlt.setText(telefono);
							txtDirAlt.setText(direccion);
							txtExpAlt.setText(experiencia);
							txtEstAlt.setText(estilo);
						}
					} catch (SQLException e) {
						System.out.println("Error en la sentencia SQL");
						e.printStackTrace();
					}
				}
				menu.setEnabled(true);
				// setVisible(false);

			} else {
				JOptionPane.showMessageDialog(this, "NO ha sido seleccionado ningún monitor", "Mensaje de ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		menu.setEnabled(true);
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
	public void mouseClicked(MouseEvent e) {
//MODIFICACION
		if (e.getSource().equals(lstMod)) {
			txtMonitorMod.setText(lstMod.getSelectedItem());
		}
//BAJA   
		if (e.getSource().equals(lstBrr)) {
			txtMonitorElm.setText(lstBrr.getSelectedItem());
		}
//CONSULTA   
		if (e.getSource().equals(lstCons)) {
			txtMonitorCons.setText(lstCons.getSelectedItem());
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
					salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Consultar Monitor "
							+ txtMonitorCons.getText() + "]\n");
//Cerrar lo creado en orden inverso      
					salida.close();
					bf.close();
					fw.close();
				} catch (IOException er) {
					System.out.println("Error");
				}
			}
			try {
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = statement.executeQuery("SELECT * FROM Monitores WHERE idMonitor=" + txtidMAlt.getText());
				if (rs.next()) {
					String dni = rs.getString("dniMonitor");
					String nombre = rs.getString("nombreMonitor");
					String apellido1 = rs.getString("apellido1Monitor");
					String apellido2 = rs.getString("apellido2Monitor");
					String telefono = Integer.toString(rs.getInt("telefonoMonitor"));
					String direccion = rs.getString("direccionMonitor");
					String experiencia = Integer.toString(rs.getInt("experienciaMonitor"));
					String estilo = Integer.toString(rs.getInt("estiloNatacionMonitor"));
					// campos que aparecen
					txtDniAlt.setText(dni);
					txtNomAlt.setText(nombre);
					txtAp1Alt.setText(apellido1);
					txtAp2Alt.setText(apellido2);
					txtTelAlt.setText(telefono);
					txtDirAlt.setText(direccion);
					txtExpAlt.setText(experiencia);
					txtEstAlt.setText(estilo);

				}
			} catch (SQLException error) {
				System.out.println("Error en la sentencia SQL");
				error.printStackTrace();
			}
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
