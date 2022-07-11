
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Litzy Nevarez
 */
public class CrearVenta extends JFrame implements ActionListener{
    
    ImageIcon reg=new ImageIcon("imagenes\\regresar.png");
    JLabel lbCliente=new JLabel("Cliente:");
    JLabel lbProducto=new JLabel("Producto:");
    JLabel lbCantidad=new JLabel("Cantidad:");
    JLabel lbFecha=new JLabel("Fecha:");
    JLabel lbTotal=new JLabel("TOTAL:");
    JLabel lbT=new JLabel();
    
    ArrayList <Integer>nuevasCantidades= new ArrayList(); 
    
    JTextField txtCliente=new JTextField();
    JSpinner spiner=new JSpinner();
    JComboBox productos=new JComboBox();
    JComboBox listaPrecio=new JComboBox();
    JComboBox cantidades=new JComboBox();
    
    
    JLabel lbF=new JLabel();
    
    JButton btnCrear=new JButton("Crear Venta");
    JButton btnRegresar=new JButton(reg);
    
    File archivo;//para manipular el archivo
    FileWriter escribir;
    PrintWriter linea;
    
    File archivoC;
    FileWriter escribirC;
    PrintWriter lineaC;
    
    File archivoGanancias;
    FileWriter escribirG;
    PrintWriter lineaG;

    public CrearVenta(){
        super("Crear Venta");
        setSize(400,350);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE)); 
        
        //llenar combo box de productos 
        try{
                FileInputStream fstream = new FileInputStream("Productos.txt");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    productos.addItem(strLine);
                    
                    }
            in.close();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex,"Error",JOptionPane.ERROR_MESSAGE);
                }
        //Para precios
        try{
            FileInputStream fstream = new FileInputStream("Precios.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
                while ((strLine = br.readLine()) != null) {
                    
                    listaPrecio.addItem(strLine);
                    
                    }
            in.close();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex,"ErrorP",JOptionPane.ERROR_MESSAGE);
        }
        
        //Para las cantidades
        try{
                FileInputStream fstream = new FileInputStream("Cantidades.txt");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    //System.out.println("\n");
                    cantidades.addItem(strLine);
                    
                    }
            in.close();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex,"ErrorC",JOptionPane.ERROR_MESSAGE);
                }
        
        agregarEtiquetas();
        agregarBotones();
        
        
    }
    public void agregarEtiquetas(){
        
        lbF.setBounds(200, 20, 200, 30);
        lbCliente.setBounds(50, 60, 100, 30);
        txtCliente.setBounds(110, 60, 230, 30);
        lbProducto.setBounds(50, 100, 100, 30);
        productos.setBounds(110, 100, 230, 30);
     
        lbCantidad.setBounds(50, 160, 100, 30);
        spiner.setBounds(110, 160, 100, 30);
        
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel();
        modeloSpinner.setMaximum(100);//rango del spiner
        modeloSpinner.setMinimum(1);
        modeloSpinner.setStepSize(1);
        spiner.setModel(modeloSpinner);
        spiner.setValue(1);
        
        lbF.setText(agregarFecha());
        this.add(lbF);
        this.add(lbCliente);
        this.add(txtCliente);
        this.add(lbProducto);
        this.add(productos);
        this.add(lbCantidad);
        this.add(spiner);
        
    }
    public void agregarBotones(){
        btnCrear.setBounds(90, 240, 140, 35);
        btnRegresar.setBounds(250, 240, 100, 35);
        
        this.add(btnCrear);
        this.add(btnRegresar);
        
        btnCrear.addActionListener(this);
        btnRegresar.addActionListener(this);
    }
     public String agregarFecha(){
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
        
    }
 
    public void actionPerformed(ActionEvent e){
        Object objeto=e.getSource();
        
        if(objeto.equals(btnCrear)){
            try{
                String sCliente, sFecha, sPago,recibo;
                double pago;
                int precio,nuevaCantidad,ncg;
                int cantidad=(Integer)spiner.getValue();
                int index=productos.getSelectedIndex();
                String itemSelec=productos.getSelectedItem().toString();
                int pos=listaPrecio.getSelectedIndex();
                String p,cantConvertida,nuevaC;
                boolean letras=true;
                
                sFecha=lbF.getText();
                sCliente=txtCliente.getText();
                
                for (int i = 0; i < sCliente.length(); i++){ //Recorremos cada caracter de la cadena y comprobamos si son letras.
                
                char caracter = sCliente.toUpperCase().charAt(i);
		int valorASCII = (int)caracter;
		if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))//Si está fuera del rango 65 - 90, es que NO son letras.
                letras =false;
		}
                
                if(sCliente.length()==0||letras==false){
                    JOptionPane.showMessageDialog(null, "Digite el correctamente el nombre del cliente","ERROR",JOptionPane.ERROR_MESSAGE);
                }else{
                    while(index!=pos){
                    pos++;
                }
                listaPrecio.setSelectedIndex(pos);
                p=listaPrecio.getSelectedItem().toString();
                precio=Integer.parseInt(p);
                pago=precio*cantidad;
                sPago=Double.toString(pago);
                
                cantidades.setSelectedIndex(pos);//cantidad seleccionada
                cantConvertida=cantidades.getSelectedItem().toString();
                nuevaCantidad=Integer.parseInt(cantConvertida);//cantidad en int para realizar la resta

                ncg=nuevaCantidad-cantidad;//cantidad que queda en al almacen
                nuevaC=Integer.toString(ncg);
                //nuevasCantidades.add(ncg);
                //System.out.println(nuevasCantidades);
                
                if(cantidad>nuevaCantidad){
                JOptionPane.showMessageDialog(null, "No tenemos esa cantidad","ERROR",JOptionPane.ERROR_MESSAGE);
                }else{
                    recibo="Fecha: "+sFecha+"\nClientes: "+sCliente+"\nProducto: "+itemSelec+"\nCantidad: "+cantidad+"\nTotal: "+sPago;
                JOptionPane.showMessageDialog(null, recibo,"Recibo de Venta",1);
                
                
                archivo = new File("datosVenta.txt");
                archivoGanancias=new File("Ganancias.txt");
                
                try{
                    escribir = new FileWriter(archivo, true);
                    escribirG=new FileWriter(archivoGanancias,true);
                    
                    linea = new PrintWriter(escribir);
                    lineaG=new PrintWriter(escribirG);
                    //Escribimos en el archivo
                    linea.print("\n");
                    linea.print(sFecha+"//");
                    linea.print(sCliente+"//");
                    linea.print(itemSelec+"//");
                    linea.print(cantidad+"//");
                    linea.print(sPago);
                    lineaG.println(sPago);
                    
                    JOptionPane.showMessageDialog(null, "Venta Guardada");

                    linea.close();
                    lineaG.close();
                    
                    escribir.close();
                    escribirG.close();
                    
                    txtCliente.setText("");
                    spiner.setValue(1);
                    productos.setSelectedIndex(0);
                    listaPrecio.setSelectedIndex(0);
                    cantidades.setSelectedIndex(0);

                    }catch(Exception ex){
                    System.out.println(ex);
                    }
                    
                    }//termino del else de cantidad
                }
    
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            

        }else if(objeto==btnRegresar){
            dispose();
            }
    }

}
