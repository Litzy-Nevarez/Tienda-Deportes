
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Litzy Nevarez García
 */
public class CrearCompra extends JFrame implements ActionListener{
    
    JLabel lbFecha=new JLabel("Fecha:");
    JLabel lbProducto=new JLabel("Producto:");
    JLabel lbCantidad=new JLabel("Cantidad:");
    JLabel lbVender=new JLabel("Precio de Venta:");
    JLabel lbTotal=new JLabel("TOTAL:");
    
    //SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yy");
    //Date objDate = new Date(); 
    JLabel lbF=new JLabel();
    
    JSpinner spiner=new JSpinner();
    
    JTextField txtProducto=new JTextField();
    JTextField txtTotal=new JTextField();
    JTextField txtPV=new JTextField();
    
    //FileWriter datosCompra;
    File archivo;//para manipular el archivo
    File archivoProductos;
    File archivoPrecios;
    File archivoCantidades;
    File archivoPerdidas;
    
    FileWriter escribirProductos;
    FileWriter escribirPrecios;
    FileWriter escribir;
    FileWriter escribirCantidades;
    FileWriter escribirPerdidas;
    
    PrintWriter linea;
    PrintWriter lineaProductos;
    PrintWriter lineaPrecios;
    PrintWriter lineaCantidades;
    PrintWriter lineaPerdidas;
    
    
    
    JPanel pnl=new JPanel();

    JButton btnCrear=new JButton("CREAR COMPRA");
    JButton btnCancelar=new JButton("CANCELAR");
    
    
    
    public CrearCompra(){
        super("Crear Compra");
        setSize(400,350);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        
        getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.MAGENTA));  
        
        agregarEtiquetas();
        agregarBotones();
        
    }
    
    public void agregarEtiquetas(){

        lbF.setBounds(200, 20, 200, 30);
        lbProducto.setBounds(50, 50, 100, 30);
        txtProducto.setBounds(110, 50, 230, 30);
        lbCantidad.setBounds(50, 90, 100, 30);
        spiner.setBounds(110, 90, 100, 30);
        
        lbTotal.setBounds(50, 130, 100, 30);
        txtTotal.setBounds(110,130,230,30);
        lbVender.setBounds(50, 170, 150, 30);
        txtPV.setBounds(150, 170, 100, 30);
        
        lbF.setText(agregarFecha());
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel();
        
        modeloSpinner.setMaximum(100);//rango del spiner
        modeloSpinner.setMinimum(1);
        modeloSpinner.setStepSize(1);
        spiner.setModel(modeloSpinner);
        spiner.setValue(1);
        
        this.add(lbF);
        this.add(lbFecha);
        this.add(lbProducto);
        this.add(txtProducto);
        this.add(lbCantidad);
        this.add(spiner);
        this.add(lbTotal);
        this.add(txtTotal);
        this.add(lbVender);
        this.add(txtPV);
 
    }
    public String agregarFecha(){
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
        
    }
    
    public void agregarBotones(){
        btnCrear.setBounds(50, 230, 150, 35);
        btnCancelar.setBounds(250, 230, 100, 35);
        
        this.add(btnCrear);
        this.add(btnCancelar);
        
        btnCrear.addActionListener(this);
        btnCancelar.addActionListener(this);
        
    }
    
    public static boolean esNumerico(String valor){     
    try{
        if(valor!= null){
            Integer.parseInt(valor);
        }
    }catch(NumberFormatException nfe){
         return false; 
    }
    return false;
    }
  

  
    public void actionPerformed(ActionEvent e){
        Object objeto=e.getSource();
        
        if(objeto==btnCrear){

            try{
                String sFecha,sProducto,sTotal,sPV,todo="";
                int cantidad=(Integer) spiner.getValue();
                int perdida;
                double pago;
            
                sFecha=lbF.getText();
                sProducto=txtProducto.getText();
                sTotal=txtTotal.getText();
                sPV=txtPV.getText();
                archivo = new File("datosCompra.txt");
                archivoProductos=new File("Productos.txt");
                archivoPrecios=new File("Precios.txt");
                archivoCantidades=new File("Cantidades.txt");
                archivoPerdidas=new File("Perdidas.txt");
            
                 boolean letras=true;
            
            
                for(int i=0;i<sProducto.length();i++){
                    char caracter = sProducto.toUpperCase().charAt(i);
                    int valorASCII = (int)caracter;
                    if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90)){
                    letras =false;//no son letras
                    }
                }
                
                int i=Integer.parseInt(sTotal);
                int j=Integer.parseInt(sPV);
                
                 if((sProducto.length()==0) || (sTotal.length()==0) || (sPV.length()==0) || (letras==false)){
                    JOptionPane.showMessageDialog(null, "Digite correctamente el producto o no deje espacios en blanco","ERROR",JOptionPane.ERROR_MESSAGE);
                }else{
                    try{
                        pago=Double.parseDouble(sTotal);
                        escribirProductos=new FileWriter(archivoProductos,true);
                        escribirPrecios=new FileWriter(archivoPrecios,true);
                        escribir = new FileWriter(archivo, true);
                        escribirCantidades=new FileWriter(archivoCantidades,true);
                        escribirPerdidas=new FileWriter(archivoPerdidas,true);
                    
                        linea = new PrintWriter(escribir);
                        lineaProductos=new PrintWriter(escribirProductos);
                        lineaPrecios=new PrintWriter(escribirPrecios);
                        lineaCantidades=new PrintWriter(escribirCantidades);
                        lineaPerdidas=new PrintWriter(escribirPerdidas);
                    
                        //Escribimos en el archivo
                        linea.print("\n");
                        linea.print(sFecha+"//");
                        linea.print(sProducto+"//");
                        linea.print(cantidad+"//");
                        linea.print(sTotal+"//");
                        linea.print(sPV);
                        lineaProductos.println(sProducto);//para los produtos
                        lineaCantidades.println(cantidad);
                        lineaPrecios.println(sPV);//para los precios
                        lineaPerdidas.println(pago);
                        
                        JOptionPane.showMessageDialog(null, "Compra Creada");

                        linea.close();
                        lineaPrecios.close();
                        lineaProductos.close();
                        lineaCantidades.close();
                        lineaPerdidas.close();
                    
                        escribirPrecios.close();
                        escribirProductos.close();
                        escribir.close();
                        escribirCantidades.close();
                        escribirPerdidas.close();
                    
                        txtPV.setText("");
                        spiner.setValue(1);
                        txtProducto.setText("");
                        txtTotal.setText("");
                        
                        
                        
                    }catch(Exception ex){
                        System.out.print(ex);
                    }
                }
                
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Digite solo numeros en el Total y Precio de venta","ERROR",JOptionPane.ERROR_MESSAGE);
               }

        }else if(objeto==btnCancelar){
            int res;
            res=JOptionPane.showConfirmDialog(null, "¿Esta seguro(a) de cancelar la compra?", "CONFIRMAR", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(res==0){
                dispose();
            }
        }
        
    }
}
