package clubNatacion;

import java.awt.Color; 
import java.awt.GridLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowEvent; 
import java.awt.event.WindowListener; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JTextArea; 

public class Ayuda extends JFrame implements WindowListener, ActionListener {
	
	private static final long serialVersionUID = 1L;    
	
	JFrame menu = new JFrame(); 
	 
	 JTextArea txtAAyud= new JTextArea();  
	 JButton btnSalir = new JButton("Salir");  
	 //Paneles y Diálogos  
	 JPanel pnl1 = new JPanel();  
	 //Definir un color y fuente  
	 Color color=new Color(238, 182, 240);    
	 void TxtAyuda(JFrame m){   
		 menu=m;   m.setEnabled(false);   
		 setTitle("Ayuda");   
		 setLayout (new GridLayout(1,1));   
		 setSize(295, 400);   
		 setLocationRelativeTo(null);   
		 setResizable(false);   
		 pnl1.setLayout(null); 
		 txtAAyud.append("Aquí podemos mostrar el texto que necesitemos\n y que se considere de ayuda para el trabajador.");
		 pnl1.add(txtAAyud);   
		 txtAAyud.setBounds(5, 5, 279, 300);   
		 txtAAyud.setEditable(false);   
		 pnl1.add(btnSalir);   
		 btnSalir.setBounds(80, 325, 120, 30);   
		 pnl1.setBackground(color);   
		 add(pnl1);    
		 //Listeners y visibilidad   
		 btnSalir.addActionListener(this);   
		 addWindowListener(this);   
		 setVisible(true);  
		 }
	 @Override  
	 public void actionPerformed(ActionEvent arg0) {   
		 if(btnSalir.equals(arg0.getSource())) {    
			 menu.setEnabled(true);    
			 setVisible(false);   
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
	 public void windowIconified(WindowEvent e) {    
	} 
	 @Override  
	 public void windowOpened(WindowEvent e) { 
	}
}
