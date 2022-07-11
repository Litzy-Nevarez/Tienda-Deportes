
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author Litzy Nevarez García
 */
public class VentanaPrincipal extends JFrame implements ActionListener{
    
     ImageIcon imagen=new ImageIcon("Imagenes\\fondo12.jpg");
     ImageIcon salir=new ImageIcon("Imagenes\\salir.png");
     ImageIcon alm=new ImageIcon("Imagenes\\almacen.png");
     ImageIcon comprar=new ImageIcon("Imagenes\\comprar.png");
     ImageIcon venta=new ImageIcon("Imagenes\\venta.png");
     ImageIcon reporte=new ImageIcon("Imagenes\\reportes.png");
     ImageIcon hora=new ImageIcon("Imagenes\\clock.png");
     
     JLabel etiqueta=new JLabel(imagen);
     JLabel titulo=new JLabel("Bienvenido");
     JLabel hraF=new JLabel();
     JLabel ht=new JLabel(hora);
     
     JButton btnAlmacen=new JButton(alm);
     JButton btnVentas=new JButton(venta);
     JButton btnCompras=new JButton(comprar);
     JButton btnReportes=new JButton(reporte);
     JButton btnCerrarSesion=new JButton(salir);
     
     
    
    public VentanaPrincipal(){
        super("Sport World");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
       
        
        agregarComponentes();
    }
    
    public void agregarComponentes(){
        
        etiqueta.setBounds(0, 0, 800, 500);
        btnAlmacen.setBounds(50, 80, 90, 90);
        btnVentas.setBounds(200, 150, 90, 90);
        btnCompras.setBounds(350, 230, 90, 90);
        btnReportes.setBounds(500, 300, 90, 90);
        titulo.setBounds(550, 50, 300, 30);
        btnCerrarSesion.setBounds(650,370,90,90);
        hraF.setBounds(110, 407, 300, 30);
        ht.setBounds(50, 400, 50, 50);
        hraF.setText(agregarFechaH());
        
        //Acciones
        btnAlmacen.addActionListener(this);
        btnVentas.addActionListener(this);
        btnCompras.addActionListener(this);
        btnReportes.addActionListener(this);
        btnCerrarSesion.addActionListener(this);
        btnAlmacen.setToolTipText("Ir al Almacen");
        btnVentas.setToolTipText("Crear Venta");
        btnCompras.setToolTipText("Crear Compra");
        btnReportes.setToolTipText("Ir a reportes");
        btnCerrarSesion.setToolTipText("Cerrar Sesión");
        hraF.setToolTipText("Hora de entrada");
        //
        
        //Diseño
        btnAlmacen.setBackground(new java.awt.Color(255, 254, 254));
        btnVentas.setBackground(new java.awt.Color(255, 254, 254));
        btnCompras.setBackground(new java.awt.Color(255, 254, 254));
        btnReportes.setBackground(new java.awt.Color(255, 254, 254));
        btnCerrarSesion.setBackground(Color.red);

        btnAlmacen.setFont(new Font("Impact",Font.PLAIN,25));
        btnVentas.setFont(new Font("Impact",Font.PLAIN,25));
        btnCompras.setFont(new Font("Impact",Font.PLAIN,25));
        btnReportes.setFont(new Font("Impact",Font.PLAIN,25));
        btnCerrarSesion.setFont(new Font("Impact",Font.PLAIN,25));
        
        titulo.setFont(new Font("Impact",Font.PLAIN,40));
        hraF.setFont(new Font("Impact",Font.PLAIN,30));
        //
        
        this.add(titulo);
        this.add(btnAlmacen);
        this.add(btnVentas);
        this.add(btnCompras);
        this.add(btnReportes);
        this.add(ht);
        this.add(hraF);
        this.add(btnCerrarSesion);
        this.add(etiqueta);
    }
    
    public String agregarFechaH(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
        
    }
   
    
    public void actionPerformed(ActionEvent e){
        Object objeto=e.getSource();
        
        if(objeto==btnAlmacen){
            Almacen av=new Almacen();
            av.setVisible(true);
            
            
        }else if(objeto==btnVentas){
            CrearVenta cv=new CrearVenta();
            cv.setVisible(true);
            
        }else if(objeto==btnCompras){
            CrearCompra cc=new CrearCompra();
            cc.setVisible(true);
            
        }else if(objeto==btnReportes){
            Reportes vr=new Reportes();
            vr.setVisible(true);
        }else if(objeto==btnCerrarSesion){
            int res;
            res=JOptionPane.showConfirmDialog(null, "¿Esta seguro(a) de salir?", "CONFIRMAR", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(res==0){
                dispose();
            }
            InicioSesion vt=new InicioSesion();
            vt.setVisible(true);
        }
        
    }
    
}
