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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//TODO: Replicar la b�squeda por id-nombre de los monitores y piscinas visto en Socios. JList y dem�as.
public class Clase extends JFrame implements WindowListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	Date fechaHora = new Date();
	DateFormat fechaHoraFormato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	JFrame menu = new JFrame();
	int usu;
	/* ALTA */
	// Etiquetas
	JLabel lblTituloAlt = new JLabel("NUEVA CLASE");
	JLabel lblNom = new JLabel("Nivel:");
	JLabel lblAp1 = new JLabel("Fecha y hora:");
	JLabel lblAp2 = new JLabel("Precio:");
	JLabel lblDni = new JLabel("*idMonitor");
	JLabel lblTel = new JLabel("idPiscina:");
	JLabel lblidS = new JLabel("idClase:");
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

	/* MODIFICACI�N */
	// Etiquetas
	JLabel lblTituloMod = new JLabel("MODIFICAR CLASE");
	JLabel lblSelecMod = new JLabel("Clase seleccionada:");

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
	JLabel lblTituloBaj = new JLabel("ELIMINAR CLASE");
	JButton btnElmBaj = new JButton("Dar de baja");
	JButton btnBuscElm = new JButton("Buscar");
	JTextField txtBuscElm = new JTextField(20);
	JTextField txtSocioElm = new JTextField(20);
	List lstBrr = new List(3, false);

	/* CONSULTA */
	JLabel lblTituloCons = new JLabel("CONSULTAR CLASE");
	JLabel lblIdCons = new JLabel("Id:");
	JTextField txtIdCons = new JTextField(20);
	JTextField txtSocioCons = new JTextField(20);
	JTextField txtBuscCons = new JTextField(20);
	JButton btnBuscCons = new JButton("Buscar");
	List lstCons = new List(3, false);
	List jListSocios = new List(30, false);

	// Paneles y Di�logos
	JPanel pnl1 = new JPanel();
	// Definir un color y fuente
	Color color = new Color(238, 182, 240);
	Font fuenteTitulo = new Font("Candara", 3, 20);
	// Conexi�n con BBDD
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/clubnatacion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String login = "root";
	String password = "Campesino89!";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	void nuevaClase(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Nuevo socio");
		setLayout(new GridLayout(1, 1));
		setSize(350, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);

		// A�adir componentes
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
		// Establecer la conexi�n con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			e.printStackTrace();
		}
	}

	void modificarClase(JFrame m, int u) {
		menu = m; setTitle("Modificar socio");setLayout
		  (new GridLayout(1,1)); setSize(505, 270); setLocationRelativeTo(null);
		  setResizable(false); pnl1.setLayout(null);
		  
		  //A�adir componentes 
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
		
		// A�adir componentes
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
		// Establecer la conexi�n con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			e.printStackTrace();
		}
	}

	void eliminarClase(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Eliminar socio");
		setLayout(new GridLayout(1, 1));
		setSize(290, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);

		// A�adir componentes
		lblTituloBaj.setFont(fuenteTitulo);
		pnl1.add(lblTituloBaj);
		lblTituloBaj.setBounds(80, 5, 300, 50);

		//pnl1.add(lblSelecMod);
		//lblSelecMod.setBounds(10, 190, 260, 25);
		pnl1.add(jListSocios);
		jListSocios.setBounds(10, 55, 260, 60);
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
		// Establecer la conexi�n con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			e.printStackTrace();
		}
		// Precarga de nombres de Socio
		java.util.List<String> listaSocios = this.getAllSocios(connection);
		for(String elemento : listaSocios) {
			jListSocios.add(elemento);
		}

	}

	void consultarClase(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Consultar socio");
		setLayout(new GridLayout(1, 1));
		setSize(570, 330);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);
		
		// A�adir componentes
		lblTituloCons.setFont(fuenteTitulo);
		pnl1.add(lblTituloCons);
		lblTituloCons.setBounds(200, 5, 300, 50);
		pnl1.add(lblidS);
		jListSocios.setBounds(10, 160, 225, 250);
		pnl1.add(jListSocios);
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
		// Establecer la conexi�n con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			e.printStackTrace();
		}
		java.util.List<String> listaSocios = this.getAllSocios(connection);
		for(String elemento : listaSocios) {
			jListSocios.add(elemento);
		}

	}
	
	public java.util.List<String> getAllSocios(Connection connection){
		java.util.List<String> result = new ArrayList<String>();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT * FROM Clases");
			while(rs.next()) {
				Integer idClase = rs.getInt("idClase");
				String horaClase = rs.getString("horaClase");
				String nombreCompleto = idClase + " - " + horaClase;
				result.add(nombreCompleto);
			}
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
			e.printStackTrace();
		}
		return result;
	}
	
	public String getSocioIdByName(String name){
		return name.split(" - ")[0];
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
							"INSERT INTO Clases(nivelClase,horaClase,precioClase,idMonitorFK1, idPiscinaFK2) VALUES ('"
									+ txtNomAlt.getText() + "','" + txtAp1Alt.getText() + "','" + txtAp2Alt.getText()
									+ "','" + txtDniAlt.getText() + "'," + txtTelAlt.getText() + ")");
				} catch (SQLException e) {
					System.out.println("Error en la sentencia SQL");
				}

				JOptionPane.showMessageDialog(this, "El socio ha sido dado de alta correctamente", "Confirmaci�n",
						JOptionPane.INFORMATION_MESSAGE);
				menu.setEnabled(true);
				setVisible(false);
			}
		}
//MODIFICACI�N   
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
						"El socio " + txtSocioMod.getText() + " ha sido modificado correctamente", "Confirmaci�n",
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
				statement.executeUpdate("UPDATE Clases SET nivelClase='" + txtNomAlt.getText() + "', horaClase='"
						+ txtAp1Alt.getText() + "', precioClase='" + txtAp2Alt.getText() + "', idMonitorFK1='" + txtDniAlt.getText() + "', idPiscinaFK2='" 
						+ txtTelAlt.getText() + "' WHERE idClase=" + txtidSAlt.getText() + ";");

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
			if (jListSocios.getSelectedIndex() != -1) {
				String elementoSeleccionado = jListSocios.getSelectedItem();
				String idSocioSeleccionado = getSocioIdByName(elementoSeleccionado);
				
				int opcion = JOptionPane.showConfirmDialog(this,
						"�Estas seguro que deseas eliminar a " + elementoSeleccionado + " ?", "Dar de Baja",
						JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(this,
							"El socio " + elementoSeleccionado + " ha sido eliminado correctamente", "Confirmaci�n",
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
									"DELETE FROM Clases WHERE idClase ='" + idSocioSeleccionado + "';");
						} catch (SQLException e) {
							System.out.println("Error en la sentencia SQL");
							e.printStackTrace();
						}
					}
					menu.setEnabled(true);
					setVisible(false);

				}
			} else {
				JOptionPane.showMessageDialog(this, "NO ha sido seleccionado ning�n socio", "Mensaje de ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (btnBuscCons.equals(arg0.getSource())) {
			if (jListSocios.getSelectedIndex() != -1) {
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
						String elementoSeleccionado = jListSocios.getSelectedItem();
						String idSocioSeleccionado = getSocioIdByName(elementoSeleccionado);
						rs = statement.executeQuery("SELECT * FROM Clases WHERE idClase=" + idSocioSeleccionado);
						if (rs.next()) {
							String idClase = rs.getString("idClase");
							String nivelClase = rs.getString("nivelClase");
							String horaClase = rs.getString("horaClase");
							String precioClase = rs.getString("precioClase");
							String idMonitorFk1 = rs.getString("idMonitorFk1");
							String idPiscinaFK2 = rs.getString("idPiscinaFK2");
							// campos que aparecen
							txtidSAlt.setText(idClase);
							txtDniAlt.setText(idPiscinaFK2);
							txtNomAlt.setText(nivelClase);
							txtAp1Alt.setText(horaClase);
							txtAp2Alt.setText(precioClase);
						}
					} catch (SQLException e) {
						System.out.println("Error en la sentencia SQL");
						e.printStackTrace();
					}
				}
				menu.setEnabled(true);
				// setVisible(false);

			} else {
				JOptionPane.showMessageDialog(this, "NO ha sido seleccionado ning�n socio", "Mensaje de ERROR",
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
				rs = statement.executeQuery("SELECT * FROM Clases WHERE idClase=" + txtidSAlt.getText());
				if (rs.next()) {
					String idClase = rs.getString("idClase");
					String nivelClase = rs.getString("nivelClase");
					String horaClase = rs.getString("horaClase");
					String precioClase = rs.getString("precioClase");
					String idMonitorFk1 = rs.getString("idMonitorFk1");
					String idPiscinaFK2 = rs.getString("idPiscinaFK2");
					// campos que aparecen
					txtidSAlt.setText(idClase);
					txtDniAlt.setText(idPiscinaFK2);
					txtNomAlt.setText(nivelClase);
					txtAp1Alt.setText(horaClase);
					txtAp2Alt.setText(precioClase);
					txtTelAlt.setText(idMonitorFk1);

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
