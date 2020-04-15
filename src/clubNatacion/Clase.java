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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class Clase extends JFrame implements WindowListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	Date fechaHora = new Date();
	DateFormat fechaHoraFormato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	JFrame menu = new JFrame();
	int usu;
	/* ALTA */
	// Etiquetas
	JLabel lblTituloAlt = new JLabel("NUEVA CLASE");
	JLabel lblHora = new JLabel("*Hora:");
	JLabel lblPrecio = new JLabel("*Precio:");
	JLabel lblPista = new JLabel("*Piscina:");
	JLabel lblProfe = new JLabel("*Monitor:");
	JLabel lblNvl = new JLabel("*Nivel:");
	JLabel lblAdv = new JLabel("--Los campos con * son obligatorios--");
	// Casillas de texto
	JTextField txtHoraAlt = new JTextField(20);
	JTextField txtPrecioAlt = new JTextField(20);
	JTextField txtPistaAlt = new JTextField(20);
	// Botones
	JButton btnBrrAlt = new JButton("Borrar todo");
	JButton btnDarAlt = new JButton("Dar de alta");
	// Menú desplegable
	Choice chcLstNvl = new Choice();
	Choice chcLstProfe = new Choice();
	/* MODIFICACIÓN */
	// Etiquetas
	JLabel lblTituloMod = new JLabel("MODIFICAR CLASE");
	JLabel lblSelecMod = new JLabel("Clase seleccionada:");
	JLabel lblSelecModSoc = new JLabel("Socio seleccionado:");
	JLabel lblCampMod = new JLabel("Seleccione campo a modificar:");
	// Casillas de texto
	JTextField txtBuscMod = new JTextField(20);
	JTextField txtBuscModSoc = new JTextField(20);
	JTextField txtAtrbMod = new JTextField(20);
	JTextField txtAtrbModSoc = new JTextField(20);
	JTextField txtModMod = new JTextField(20);
	// Botones
	JButton btnBuscMod = new JButton("Buscar");
	JButton btnModMod = new JButton("Modificar");
	JButton btnBuscModSoc = new JButton("Buscar");
	// Lista
	List lstMod = new List(3, false);
	Choice chcLstAtrib = new Choice();
	List lstModSoc = new List(3, false);
	// Separador
	JSeparator separador = new JSeparator();
	JSeparator separador2 = new JSeparator();
	/* BORRAR */
	JLabel lblTituloBaj = new JLabel("ELIMINAR CLASE");
	JButton btnElmBaj = new JButton("Dar de baja");
	JButton btnBuscElm = new JButton("Buscar");
	JTextField txtBuscElm = new JTextField(20);
	JTextField txtClasElm = new JTextField(20);
	List lstBrr = new List(3, false);
	/* Se ha reutilizado componentes de la sección modificar: labels */
	/* CONSULTA */
	JLabel lblTituloCons = new JLabel("CONSULTAR CLASE");
	JLabel lblIdCons = new JLabel("Id:");
	JTextField txtIdCons = new JTextField(20);
	JTextField txtNvlCons = new JTextField(20);
	JTextField txtProfCons = new JTextField(20);
	JTextField txtClasCons = new JTextField(20);
	JTextField txtBuscCons = new JTextField(20);
	JButton btnBuscCons = new JButton("Buscar");
	JTextArea txtASociosCons = new JTextArea();
	JLabel lblSociosCons = new JLabel("Socios:");
	List lstCons = new List(3, false);
	/* Se ha reutilizado componentes de la sección modificar y alta: labels */
	// Paneles y Diálogos
	JPanel pnl1 = new JPanel();
	// Definir un color y fuente
	Color color = new Color(238, 182, 240);
	Font fuenteTitulo = new Font("Candara", 3, 20);

	void nuevaClase(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Nueva clase");
		setLayout(new GridLayout(1, 1));
		setSize(350, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);
		// Añadir componentes
		lblTituloAlt.setFont(fuenteTitulo);
		pnl1.add(lblTituloAlt);
		lblTituloAlt.setBounds(120, 5, 300, 50);
		pnl1.add(lblHora);
		lblHora.setBounds(100, 60, 60, 20);
		pnl1.add(txtHoraAlt);
		txtHoraAlt.setBounds(150, 60, 150, 25);
		pnl1.add(lblPrecio);
		lblPrecio.setBounds(90, 90, 150, 20);
		pnl1.add(txtPrecioAlt);
		txtPrecioAlt.setBounds(150, 90, 150, 25);
		pnl1.add(lblPista);
		lblPista.setBounds(97, 120, 150, 20);
		pnl1.add(txtPistaAlt);
		txtPistaAlt.setBounds(150, 120, 150, 25);
		pnl1.add(lblProfe);
		lblProfe.setBounds(77, 150, 70, 20);
		// Opciones de la lista desplegable nivel
		chcLstProfe.add("Seleccione uno. ");
		chcLstProfe.add("Michael Phelps");
		chcLstProfe.add("Alexander Popov");
		chcLstProfe.add("Vladimir Salnikov");
		chcLstProfe.add("Ian Thorphe");
		pnl1.add(chcLstProfe);
		chcLstProfe.setBounds(150, 150, 150, 25);
		pnl1.add(lblNvl);
		lblNvl.setBounds(100, 180, 40, 20);
		// Opciones de la lista desplegable nivel
		chcLstNvl.add("Seleccione uno. ");
		chcLstNvl.add("Bajo");
		chcLstNvl.add("Medio");
		chcLstNvl.add("Alto");
		pnl1.add(chcLstNvl);
		chcLstNvl.setBounds(150, 180, 150, 25);
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
	}

	void modificarClase(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Modificar clase");
		setLayout(new GridLayout(1, 1));
		setSize(790, 330);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);
		// Añadir componentes
		lblTituloMod.setFont(fuenteTitulo);
		pnl1.add(lblTituloMod);
		lblTituloMod.setBounds(310, 5, 300, 50);
		pnl1.add(txtBuscMod);
		txtBuscMod.setBounds(10, 60, 140, 25);
		pnl1.add(btnBuscMod);
		btnBuscMod.setBounds(155, 57, 80, 30);
		// Opciones de la lista
		lstMod.add("001");
		lstMod.add("002");
		lstMod.add("003");
		lstMod.add("004");
		lstMod.add("005");
		lstMod.add("006");
		lstMod.add("007");
		lstMod.add("008");
		lstMod.add("009");
		pnl1.add(lstMod);
		lstMod.setBounds(10, 100, 225, 110);
		// Separador
		separador.setOrientation(SwingConstants.VERTICAL);
		pnl1.add(separador);
		separador.setBounds(260, 60, 2, 200);
		pnl1.add(lblSelecMod);
		lblSelecMod.setBounds(285, 50, 260, 25);
		pnl1.add(txtAtrbMod);
		txtAtrbMod.setBounds(285, 80, 200, 25);
		txtAtrbMod.setEditable(false);
		pnl1.add(lblSelecModSoc);
		lblSelecModSoc.setBounds(285, 110, 150, 25);
		pnl1.add(txtAtrbModSoc);
		txtAtrbModSoc.setBounds(285, 140, 200, 25);
		txtAtrbModSoc.setEditable(false);
		pnl1.add(lblCampMod);
		lblCampMod.setBounds(285, 170, 180, 25);
		// Opciones de la lista desplegable
		chcLstAtrib.add("Seleccione uno. ");
		chcLstAtrib.add("Hora");
		chcLstAtrib.add("Precio");
		chcLstAtrib.add("Pista");
		chcLstAtrib.add("Profesor");
		chcLstAtrib.add("Nivel");
		chcLstAtrib.add("Añadir socio");
		chcLstAtrib.add("Eliminar socio");
		pnl1.add(chcLstAtrib);
		chcLstAtrib.setBounds(285, 200, 200, 25);
		pnl1.add(txtModMod);
		txtModMod.setBounds(285, 230, 200, 25);
		pnl1.add(btnModMod);
		btnModMod.setBounds(335, 260, 100, 30);
		// Separador
		separador2.setOrientation(SwingConstants.VERTICAL);
		pnl1.add(separador2);
		separador2.setBounds(510, 60, 2, 200);
		pnl1.add(txtBuscModSoc);
		txtBuscModSoc.setBounds(540, 60, 140, 25);
		pnl1.add(btnBuscModSoc);
		btnBuscModSoc.setBounds(685, 57, 80, 30);
		// Opciones de la lista
		lstModSoc.add("David Díaz");
		lstModSoc.add("Francisco Rufo");
		lstModSoc.add("Salvador Blanco");
		lstModSoc.add("Bebop Jimenez");
		lstModSoc.add("Antonio Martin");
		lstModSoc.add("Julio Rosa");
		lstModSoc.add("Eduardo Lopez");
		lstModSoc.add("Sheyla Paredes");
		lstModSoc.add("Arancha Villaverde");
		pnl1.add(lstModSoc);
		lstModSoc.setBounds(540, 100, 225, 110);
		pnl1.setBackground(color);
		add(pnl1);
		// Listeners y visibilidad
		btnBuscModSoc.addActionListener(this);
		btnBuscMod.addActionListener(this);
		btnModMod.addActionListener(this);
		lstMod.addMouseListener(this);
		lstModSoc.addMouseListener(this);
		chcLstAtrib.addMouseListener(this);
		addWindowListener(this);
		setVisible(true);
	}

	void eliminarClase(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Eliminar clase");
		setLayout(new GridLayout(1, 1));
		setSize(290, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);
		// Añadir componentes
		lblTituloBaj.setFont(fuenteTitulo);
		pnl1.add(lblTituloBaj);
		lblTituloBaj.setBounds(60, 5, 300, 50);
		pnl1.add(txtBuscElm);
		txtBuscElm.setBounds(10, 60, 150, 25);
		pnl1.add(btnBuscElm);
		btnBuscElm.setBounds(170, 57, 100, 30);
		// Opciones de la lista
		lstBrr.add("001");
		lstBrr.add("002");
		lstBrr.add("003");
		lstBrr.add("004");
		lstBrr.add("005");
		lstBrr.add("006");
		lstBrr.add("007");
		lstBrr.add("008");
		lstBrr.add("009");
		pnl1.add(lstBrr);
		lstBrr.setBounds(10, 100, 260, 110);
		pnl1.add(lblSelecMod);
		lblSelecMod.setBounds(10, 190, 260, 25);
		pnl1.add(txtClasElm);
		txtClasElm.setBounds(10, 220, 260, 25);
		txtClasElm.setEditable(false);
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
	}

	void consultarClase(JFrame m, int u) {
		menu = m;
		usu = u;
		m.setEnabled(false);
		setTitle("Consultar clase");
		setLayout(new GridLayout(1, 1));
		setSize(760, 330);
		setLocationRelativeTo(null);
		setResizable(false);
		pnl1.setLayout(null);
		// Añadir componentes
		lblTituloCons.setFont(fuenteTitulo);
		pnl1.add(lblTituloCons);
		lblTituloCons.setBounds(330, 5, 300, 50);
		pnl1.add(txtBuscCons);
		txtBuscCons.setBounds(10, 60, 140, 25);
		pnl1.add(btnBuscCons);
		btnBuscCons.setBounds(155, 57, 80, 30);
		// Opciones de la lista
		lstCons.add("001");
		lstCons.add("002");
		lstCons.add("003");
		lstCons.add("004");
		lstCons.add("005");
		lstCons.add("006");
		lstCons.add("007");
		lstCons.add("008");
		lstCons.add("009");
		pnl1.add(lstCons);
		lstCons.setBounds(10, 100, 225, 110);
		pnl1.add(lblSelecMod);
		lblSelecMod.setBounds(10, 220, 225, 25);
		pnl1.add(txtClasCons);
		txtClasCons.setBounds(10, 250, 225, 25);
		txtClasCons.setEditable(false);
		// Separador
		separador.setOrientation(SwingConstants.VERTICAL);
		pnl1.add(separador);
		separador.setBounds(260, 60, 2, 210);
		// Datos
		pnl1.add(lblIdCons);
		lblIdCons.setBounds(337, 70, 60, 20);
		pnl1.add(txtIdCons);
		txtIdCons.setBounds(360, 70, 150, 25);
		txtIdCons.setEditable(false);
		pnl1.add(lblHora);
		lblHora.setBounds(313, 100, 60, 20);
		pnl1.add(txtHoraAlt);
		txtHoraAlt.setBounds(360, 100, 150, 25);
		txtHoraAlt.setEditable(false);
		pnl1.add(lblPrecio);
		lblPrecio.setBounds(305, 130, 150, 20);
		pnl1.add(txtPrecioAlt);
		txtPrecioAlt.setBounds(360, 130, 150, 25);
		txtPrecioAlt.setEditable(false);
		pnl1.add(lblPista);
		lblPista.setBounds(310, 160, 150, 20);
		pnl1.add(txtPistaAlt);
		txtPistaAlt.setBounds(360, 160, 150, 25);
		txtPistaAlt.setEditable(false);
		pnl1.add(lblProfe);
		lblProfe.setBounds(290, 190, 150, 20);
		pnl1.add(txtProfCons);
		txtProfCons.setBounds(360, 190, 150, 25);
		txtProfCons.setEditable(false);
		pnl1.add(lblNvl);
		lblNvl.setBounds(310, 220, 150, 20);
		pnl1.add(txtNvlCons);
		txtNvlCons.setBounds(360, 220, 150, 25);
		txtNvlCons.setEditable(false);
		// Separador
		separador2.setOrientation(SwingConstants.VERTICAL);
		pnl1.add(separador2);
		separador2.setBounds(550, 60, 2, 210);
		pnl1.add(lblSociosCons);
		lblSociosCons.setBounds(580, 50, 70, 25);
		pnl1.add(txtASociosCons);
		txtASociosCons.setBounds(580, 80, 160, 200);
		txtASociosCons.setEnabled(false);
		pnl1.setBackground(color);
		add(pnl1);
		// Listeners y visibilidad
		btnBuscCons.addActionListener(this);
		lstCons.addMouseListener(this);
		addWindowListener(this);
		setVisible(true);
	}

	public void borrarTxt(JTextField txt) {
		txt.selectAll();
		txt.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// MODIFICACIÓN
		if (arg0.getSource().equals(lstMod)) {
			txtAtrbMod.setText(lstMod.getSelectedItem());
		}
		if (arg0.getSource().equals(lstModSoc)) {
			txtAtrbModSoc.setText(lstModSoc.getSelectedItem());
		}
		// BAJA
		if (arg0.getSource().equals(lstBrr)) {
			txtClasElm.setText(lstBrr.getSelectedItem());
		}
		// CONSULTA
		if (arg0.getSource().equals(lstCons)) {
			txtClasCons.setText(lstCons.getSelectedItem());
			if (usu == 0) {
				try {
					// Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src
					// por defecto, sino poner ruta)
					// El true es para que no se machaque el texto
					FileWriter fw = new FileWriter("movimientos.log", true);
					// Buffer para poder acceder al fichero
					BufferedWriter bf = new BufferedWriter(fw);
					// Para poder escribir en el fichero
					PrintWriter salida = new PrintWriter(bf);
					// Para escribir
					salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Co nsultar Clase "
							+ txtClasCons.getText() + "]\n");
					// Cerrar lo creado en orden inverso
					salida.close();
					bf.close();
					fw.close();
				} catch (IOException er) {
					System.out.println("Error");
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ALTA
		if (btnBrrAlt.equals(e.getSource())) {
			borrarTxt(txtHoraAlt);
			borrarTxt(txtPrecioAlt);
			borrarTxt(txtPistaAlt);
			chcLstProfe.select(0);
			chcLstNvl.select(0);
		}
		if (btnDarAlt.equals(e.getSource())) {
			if ((txtHoraAlt.getText().equals("")) | (txtPrecioAlt.getText().equals(""))
					| (txtPistaAlt.getText().equals("")) | (chcLstProfe.getSelectedIndex() == 0)
					| (chcLstNvl.getSelectedIndex() == 0)) {
				JOptionPane.showMessageDialog(this, "Uno o varios de los campos obligatorios NO han sido rellenados ",
						"Mensaje de ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				if (usu == 0) {
					try {
						// Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src
						// por defecto, sino poner ruta)
						// El true es para que no se machaque el texto
						FileWriter fw = new FileWriter("movimientos.log", true);
						// Buffer para poder acceder al fichero
						BufferedWriter bf = new BufferedWriter(fw);
						// Para poder escribir en el fichero
						PrintWriter salida = new PrintWriter(bf);
						// Para escribir
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Al ta Clase]\n");
						// Cerrar lo creado en orden inverso
						salida.close();
						bf.close();
						fw.close();
					} catch (IOException er) {
						System.out.println("Error");
					}
				} else if (usu == 1) {
					try {
						// Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src
						// por defecto, sino poner ruta)
						// El true es para que no se machaque el texto
						FileWriter fw = new FileWriter("movimientos.log", true);
						// Buffer para poder acceder al fichero
						BufferedWriter bf = new BufferedWriter(fw);
						// Para poder escribir en el fichero
						PrintWriter salida = new PrintWriter(bf);
						// Para escribir
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Usuario][Alta Clase] \n");
						// Cerrar lo creado en orden inverso
						salida.close();
						bf.close();
						fw.close();
					} catch (IOException er) {
						System.out.println("Error");
					}
				}
				JOptionPane.showMessageDialog(this, "La clase ha sido dado de alta correctamente", "Confirmación",
						JOptionPane.INFORMATION_MESSAGE);
				menu.setEnabled(true);
				setVisible(false);
			}
		}
		// MODIFICACIÓN
		if (btnBuscMod.equals(e.getSource())) {
			for (int i = 0; i < lstMod.getItemCount(); i++) {
				if (lstMod.getItem(i).equals(txtBuscMod.getText())) {
					lstMod.select(i);
				}
			}
		}
		if (btnBuscModSoc.equals(e.getSource())) {
			for (int i = 0; i < lstModSoc.getItemCount(); i++) {
				if (lstModSoc.getItem(i).equals(txtBuscModSoc.getText())) {
					lstModSoc.select(i);
				}
			}
		}
		if (btnModMod.equals(e.getSource())) {
			if (txtModMod.getText().equals("") | txtAtrbMod.getText().equals("")
					| chcLstAtrib.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this,
						"La clase " + txtAtrbMod.getText() + " NO ha sido modificado correctamente", "Mensaje de ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (chcLstAtrib.getSelectedIndex() == 6) {
				if (txtAtrbMod.getText().equals("David Díaz")) {
					JOptionPane.showMessageDialog(this,
							"El socio " + txtAtrbMod.getText() + " ya fue añadido a la clase", "Mensaje de ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (chcLstAtrib.getSelectedIndex() == 7) {
				if (txtAtrbMod.getText().equals("Francisco Rufo")) {
					JOptionPane.showMessageDialog(this,
							"El socio " + txtAtrbMod.getText() + " no fue anadido a la clase", "Mensaje de ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if (usu == 0) {
					try {
						// Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src
						// por defecto, sino poner ruta)
						// El true es para que no se machaque el texto
						FileWriter fw = new FileWriter("movimientos.log", true);
						// Buffer para poder acceder al fichero
						BufferedWriter bf = new BufferedWriter(fw);
						// Para poder escribir en el fichero
						PrintWriter salida = new PrintWriter(bf);
						// Para escribir
						salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Modificar Clase "
								+ txtAtrbMod.getText() + " en campo " + chcLstAtrib.getSelectedItem() + "]\n");
						// Cerrar lo creado en orden inverso
						salida.close();
						bf.close();
						fw.close();
					} catch (IOException er) {
						System.out.println("Error");
					}
				}
				JOptionPane.showMessageDialog(this,
						"La clase " + txtAtrbMod.getText() + " ha sido modificado correctamente", "Confirmación",
						JOptionPane.INFORMATION_MESSAGE);
				menu.setEnabled(true);
				setVisible(false);
			}
		}
		// BAJA
		if (btnBuscElm.equals(e.getSource())) {
			for (int i = 0; i < lstBrr.getItemCount(); i++) {
				if (lstBrr.getItem(i).equals(txtBuscElm.getText())) {
					lstBrr.select(i);
				}
			}
		}
		if (btnElmBaj.equals(e.getSource())) {
			if (!txtClasElm.getText().equals("")) {
				int opcion = JOptionPane.showConfirmDialog(this,
						"¿Estás seguro de eliminar la clase " + txtClasElm.getText() + " ?", "Dar de baja",
						JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					if (usu == 0) {
						try {
							// Indicar el fichero donde se guarda ya creado o se crea solo si no existe (src
							// por defecto, sino poner ruta)
							// El true es para que no se machaque el texto
							FileWriter fw = new FileWriter("movimientos.log", true);
							// Buffer para poder acceder al fichero
							BufferedWriter bf = new BufferedWriter(fw);
							// Para poder escribir en el fichero
							PrintWriter salida = new PrintWriter(bf);
							// Para escribir
							salida.println("[" + fechaHoraFormato.format(fechaHora) + "][Administrador][Eliminar Clase "
									+ txtClasElm.getText() + "]\n");
							// Cerrar lo creado en orden inverso
							salida.close();
							bf.close();
							fw.close();
						} catch (IOException er) {
							System.out.println("Error");
						}
					}
					JOptionPane.showMessageDialog(this,
							"La clase " + txtClasElm.getText() + " ha sido eliminada correctamente", "Confirmación",
							JOptionPane.INFORMATION_MESSAGE);
					menu.setEnabled(true);
					setVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No ha sido seleccionada ninguna clase", "Mensaje de ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
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
		menu.setEnabled(true);
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

}
			
			

			 
		