
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



/**
 *
 * @author Litzy Nevarez García
 */

public class InicioSesion extends JFrame implements ActionListener{
    
    JLabel lbUser=new JLabel("Usuario:");
    JLabel lbPass=new JLabel("Contraseña:");
    ImageIcon imagen=new ImageIcon("Imagenes\\sport.png");
    JLabel etiqueta=new JLabel(imagen);
    
    ImageIcon imguser=new ImageIcon("Imagenes\\user.png");
    JLabel user=new JLabel(imguser);
    
    ImageIcon imgp=new ImageIcon("Imagenes\\pass.png");
    JLabel pass=new JLabel(imgp);
   
    JLabel titulo=new JLabel("Sport World");
            
    
    JTextField txtUser=new JTextField();
    JPasswordField txtPass = new JPasswordField();
    
    JButton btnIniciar=new JButton("Iniciar Sesión");
    JButton btnSalir=new JButton("Salir");
    
    
    public InicioSesion(){
        
        super("Inicio de Sesión");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        agregarComponentes();
        
    }
    public void agregarComponentes(){
        
        titulo.setBounds(100,30,200,30);
        user.setBounds(27, 200, 30, 30);
        lbUser.setBounds(50, 180, 100, 25);
        txtUser.setBounds(60, 200, 250, 30);
        
        pass.setBounds(25, 260, 30, 30);
        lbPass.setBounds(50, 230, 100, 25);
        txtPass.setBounds(60, 260, 250, 30);
        
        
        btnSalir.setBounds(50, 320, 80, 25);
        btnIniciar.setBounds(200, 320, 115, 25);
        etiqueta.setBounds(20, 0, 300, 250);
        
        titulo.setFont(new Font("Impact",Font.PLAIN,25));
        
        
        this.add(user);
        this.add(lbUser);
        this.add(etiqueta);
        this.add(txtUser);
        this.add(pass);
        this.add(titulo);
        this.add(lbPass);
        this.add(txtPass);
        this.add(btnSalir);
        this.add(btnIniciar);
        
        btnSalir.addActionListener(this);
        btnIniciar.addActionListener(this);
        
        
        
    }
    
    public static void main(String[]args){
        InicioSesion ventanaInicial=new InicioSesion();
        ventanaInicial.setVisible(true);
    }
    
     public void actionPerformed(ActionEvent e){
        Object objeto=e.getSource();
        
        if(objeto==btnIniciar){
            
            String user="litzy";
            String pass="12345";
            
            if(txtUser.getText().equals(user)&&txtPass.getText().equals(pass)){
                VentanaPrincipal vp=new VentanaPrincipal();
                JOptionPane.showMessageDialog(null, "Datos Correctos");
                vp.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Datos Incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            
        }else if(objeto==btnSalir){
            int res;
            res=JOptionPane.showConfirmDialog(null, "¿Esta seguro(a) de salir?", "CONFIRMAR", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(res==0){
                dispose();
            }
        }
     }
    
}
