package clubNatacion;

import java.awt.*;
import java.awt.event.*;
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
import javax.swing.*;

public class Socio extends JFrame implements WindowListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	Date fechaHora = new Date();
	DateFormat fechaHoraFormato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	JFrame menu = new JFrame();
	int usu;
	/* ALTA */
	// Etiquetas
	JLabel lblTituloAlt = new JLabel("NUEVO SOCIO");
	JLabel lblNom = new JLabel("*Nombre:");
	JLabel lblAp1 = new JLabel("*Primer apellido:");
	JLabel lblAp2 = new JLabel(" Segundo apellido:");
	JLabel lblDni = new JLabel("*DNI (Sin letra):");
	JLabel lblTel = new JLabel("Telefono:");
	JLabel lblidS = new JLabel("idSocio:");
	JLabel lblAdv = new JLabel("--Los campos con * son obligatorios--");

	// Casillas de texto
	JTextField txtNomAlt = new JTextField(20);
	JTextField txtAp1Alt = new JTextField(20);
	JTextField txtAp2Alt = new JTextField(20);
	JTextField txtDniAlt = new JTextField(20);
	JTextField txtTelAlt = new JTextField(20);
	JTextField txtidSAlt = new JTextField(20);

	// Botones
	JButton btnBrrAlt = new JButton("Borrar todo");
	JButton btnDarAlt = new JButton("Dar de alta");

	/* MODIFICACIÓN */
	// Etiquetas
	JLabel lblTituloMod = new JLabel("MODIFICAR SOCIO");
	JLabel lblSelecMod = new JLabel("Socio seleccionado:");

	// Casillas de texto
	JTextField txtBuscMod = new JTextField(20);
	JTextField txtSocioMod = new JTextField(20);
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
	JLabel lblTituloBaj = new JLabel("ELIMINAR SOCIO");
	JButton btnElmBaj = new JButton("Dar de baja");
	JButton btnBuscElm = new JButton("Buscar");
	JTextField txtBuscElm = new JTextField(20);
	JTextField txtSocioElm = new JTextField(20);
	List lstBrr = new List(3, false);

	/* CONSULTA */
	JLabel lblTituloCons = new JLabel("CONSULTAR SOCIO");
	JLabel lblIdCons = new JLabel("Id:");
	JTextField txtIdCons = new JTextField(20);
	JTextField txtSocioCons = new JTextField(20);
	JTextField txtBuscCons = new JTextField(20);
	JButton btnBuscCons = new JButton("Buscar");
	List lstCons = new List(3, false);

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

	void nuevoSocio(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Nuevo socio");
		setLayout(new GridLayout(1, 1));
		setSize(350, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);

		// Añadir componentes
		lblTituloAlt.setFont(fuenteTitulo);
		pnl1.add(lblTituloAlt);
		lblTituloAlt.setBounds(120, 5, 300, 50);
		pnl1.add(lblNom);
		lblNom.setBounds(89, 60, 60, 20);
		pnl1.add(txtNomAlt);
		txtNomAlt.setBounds(150, 60, 150, 25);
		pnl1.add(lblAp1);
		lblAp1.setBounds(45, 90, 150, 20);
		pnl1.add(txtAp1Alt);
		txtAp1Alt.setBounds(150, 90, 150, 25);
		pnl1.add(lblAp2);
		lblAp2.setBounds(35, 120, 150, 20);
		pnl1.add(txtAp2Alt);
		txtAp2Alt.setBounds(150, 120, 150, 25);
		pnl1.add(lblDni);
		lblDni.setBounds(55, 150, 150, 20);
		pnl1.add(txtDniAlt);
		txtDniAlt.setBounds(150, 150, 150, 25);
		pnl1.add(lblTel);
		lblTel.setBounds(87, 180, 150, 20);
		pnl1.add(txtTelAlt);
		txtTelAlt.setBounds(150, 180, 150, 25);
		pnl1.add(lblAdv);
		lblAdv.setBounds(70, 260, 250, 30);
		pnl1.add(btnDarAlt);
		btnDarAlt.setBounds(35, 220, 120, 30);
		pnl1.add(btnBrrAlt);
		btnBrrAlt.setBounds(185, 220, 120, 30);
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

	void modificarSocio(JFrame m, int u) {
		menu = m; setTitle("Modificar socio");setLayout
		  (new GridLayout(1,1)); setSize(505, 270); setLocationRelativeTo(null);
		  setResizable(false); pnl1.setLayout(null);
		  
		  //Añadir componentes 
		  lblTituloMod.setFont(fuenteTitulo);
		  pnl1.add(lblTituloMod); lblTituloMod.setBounds(100, 5, 300, 50);
		usu = u;
		m.setEnabled(false);
		setTitle("Modificar Socio");
		setLayout(new GridLayout(1, 1));
		setSize(350, 320);
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
		pnl1.add(lblAp1);
		lblAp1.setBounds(45, 90, 150, 20);
		pnl1.add(txtAp1Alt);
		txtAp1Alt.setBounds(150, 90, 150, 25);
		pnl1.add(lblAp2);
		lblAp2.setBounds(35, 120, 150, 20);
		pnl1.add(txtAp2Alt);
		txtAp2Alt.setBounds(150, 120, 150, 25);
		pnl1.add(lblDni);
		lblDni.setBounds(55, 150, 150, 20);
		pnl1.add(txtDniAlt);
		txtDniAlt.setBounds(150, 150, 150, 25);
		pnl1.add(lblTel);
		lblTel.setBounds(87, 180, 150, 20);
		pnl1.add(txtTelAlt);
		txtTelAlt.setBounds(150, 180, 150, 25);
		pnl1.add(lblidS);
		lblidS.setBounds(89, 210, 60, 20);
		pnl1.add(txtidSAlt);
		txtidSAlt.setBounds(150, 210, 150, 25);
		pnl1.add(lblAdv);
		lblAdv.setBounds(70, 290, 250, 30);
		pnl1.add(btnModMod);
		btnModMod.setBounds(35, 250, 120, 30);
		pnl1.add(btnBrrAlt);
		btnBrrAlt.setBounds(185, 250, 120, 30);
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

	void eliminarSocio(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Eliminar socio");
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
		pnl1.add(txtSocioElm);
		txtSocioElm.setBounds(10, 220, 260, 25);
		txtSocioElm.setEditable(true);
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

	void consultarSocio(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Consultar socio");
		setLayout(new GridLayout(1, 1));
		setSize(570, 330);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);

		// Añadir componentes
		lblTituloCons.setFont(fuenteTitulo);
		pnl1.add(lblTituloCons);
		lblTituloCons.setBounds(200, 5, 300, 50);
		pnl1.add(lblidS);
		lblidS.setBounds(10, 220, 225, 25);
		pnl1.add(txtidSAlt);
		txtidSAlt.setBounds(10, 250, 225, 25);
		txtidSAlt.setEditable(true);

		// Separador
		separador.setOrientation(SwingConstants.VERTICAL);
		pnl1.add(separador);
		separador.setBounds(260, 60, 2, 210);

		// Datos
		pnl1.add(btnBuscCons);
		btnBuscCons.setBounds(70, 120, 100, 30);
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
		pnl1.add(lblTel);
		lblTel.setBounds(338, 220, 150, 20);
		pnl1.add(txtTelAlt);
		txtTelAlt.setBounds(401, 220, 150, 25);
		txtTelAlt.setEditable(false);
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
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Alta Socio "
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
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Usuario][Alta Socio "
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
							"INSERT INTO Socios(nombreSocio,apellido1Socio,apellido2Socio, dniSocio, telefonoSocio) VALUES ('"
									+ txtNomAlt.getText() + "','" + txtAp1Alt.getText() + "','" + txtAp2Alt.getText()
									+ "','" + txtDniAlt.getText() + "'," + txtTelAlt.getText() + ")");
				} catch (SQLException e) {
					System.out.println("Error en la sentencia SQL");
				}

				JOptionPane.showMessageDialog(this, "El socio ha sido dado de alta correctamente", "Confirmación",
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
			if (txtNomAlt.getText().equals("") | txtidSAlt.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
						"El socio " + txtSocioMod.getText() + " NO ha sido modificado correctamente",
						"Mensaje de ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
						"El socio " + txtSocioMod.getText() + " ha sido modificado correctamente", "Confirmación",
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
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Modificar Socio "
								+ txtSocioMod.getText() + " en campo " + chcLstAtrib.getSelectedItem() + "]\n");
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
				statement.executeUpdate("UPDATE Socios SET nombreSocio='" + txtNomAlt.getText() + "', apellido1Socio='"
						+ txtAp1Alt.getText() + "', apellido2Socio='" + txtAp2Alt.getText() + "', dniSocio='" + txtDniAlt.getText() + "', telefonoSocio='" 
						+ txtTelAlt.getText() + "' WHERE idSocio=" + txtidSAlt.getText() + ";");

			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL");
				e.printStackTrace();
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
			if (!txtSocioElm.getText().equals("")) {
				int opcion = JOptionPane.showConfirmDialog(this,
						"¿Estas seguro que deseas eliminar a " + txtSocioElm.getText() + " ?", "Dar de Baja",
						JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(this,
							"El socio " + txtSocioElm.getText() + " ha sido eliminado correctamente", "Confirmación",
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
									+ "][Administrador][Eliminar Socio " + txtSocioElm.getText() + "]\n");
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
									"DELETE FROM Socios WHERE idSocio ='" + txtSocioElm.getText() + "';");
						} catch (SQLException e) {
							System.out.println("Error en la sentencia SQL");
							e.printStackTrace();
						}
					}
					menu.setEnabled(true);
					setVisible(false);

				}
			} else {
				JOptionPane.showMessageDialog(this, "NO ha sido seleccionado ningún socio", "Mensaje de ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (btnBuscCons.equals(arg0.getSource())) {
			if (!txtidSAlt.getText().equals("")) {
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
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Eliminar Socio "
								+ txtSocioElm.getText() + "]\n");
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
						rs = statement.executeQuery("SELECT * FROM Socios WHERE idSocio=" + txtidSAlt.getText());
						if (rs.next()) {
							String dni = rs.getString("dniSocio");
							String nombre = rs.getString("nombreSocio");
							String apellido1 = rs.getString("apellido1Socio");
							String apellido2 = rs.getString("apellido2Socio");
							String telefono = Integer.toString(rs.getInt("telefonoSocio"));
							// campos que aparecen
							txtDniAlt.setText(dni);
							txtNomAlt.setText(nombre);
							txtAp1Alt.setText(apellido1);
							txtAp2Alt.setText(apellido2);
							txtTelAlt.setText(telefono);
						}
					} catch (SQLException e) {
						System.out.println("Error en la sentencia SQL");
						e.printStackTrace();
					}
				}
				menu.setEnabled(true);
				// setVisible(false);

			} else {
				JOptionPane.showMessageDialog(this, "NO ha sido seleccionado ningún socio", "Mensaje de ERROR",
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
			txtSocioMod.setText(lstMod.getSelectedItem());
		}
//BAJA   
		if (e.getSource().equals(lstBrr)) {
			txtSocioElm.setText(lstBrr.getSelectedItem());
		}
//CONSULTA   
		if (e.getSource().equals(lstCons)) {
			txtSocioCons.setText(lstCons.getSelectedItem());
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
					salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Consultar Socio "
							+ txtSocioCons.getText() + "]\n");
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
				rs = statement.executeQuery("SELECT * FROM Socios WHERE idSocio=" + txtidSAlt.getText());
				if (rs.next()) {
					String dni = rs.getString("dniSocio");
					String nombre = rs.getString("nombreSocio");
					String apellido1 = rs.getString("apellido1Socio");
					String apellido2 = rs.getString("apellido2Socio");
					String telefono = Integer.toString(rs.getInt("telefonoSocio"));
					// campos que aparecen
					txtDniAlt.setText(dni);
					txtNomAlt.setText(nombre);
					txtAp1Alt.setText(apellido1);
					txtAp2Alt.setText(apellido2);
					txtTelAlt.setText(telefono);

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