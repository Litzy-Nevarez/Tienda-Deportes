
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Litzy Nevarez García
 */

public class Reportes extends JFrame implements ActionListener{
    
    JScrollPane scrollPane;
    JScrollPane scrollC;
    
    ImageIcon imagen=new ImageIcon("imagenes\\regresar.png");
    JButton btnVentas =new JButton("VENTAS");
    JButton btnCompras=new JButton("COMPRAS");
    JButton btnRegresar=new JButton(imagen);
    JButton btnTotal=new JButton("TOTAL");
    
    //JLabel etiqueta=new JLabel(imagen);
    JLabel lbTotal=new JLabel("TOTAL :  $");
    JLabel lbT=new JLabel();
    JLabel lbPerdida=new JLabel("TOTAL : $");
    JLabel lbP=new JLabel();
    
    String []vectorVta =new String[5];
    DefaultTableModel tablaV=new DefaultTableModel();
    
    String []vectorCom =new String[4];
    DefaultTableModel tablaC=new DefaultTableModel();
    
    JComboBox ganancias=new JComboBox();
    JComboBox perdidas=new JComboBox();
    
    double totalV,totalP,TOTAL;
    
    
    public Reportes(){
        super("REPORTES");
        setSize(700,500);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));
        
        btnVentas.setBounds(200, 50, 90, 35);
        btnCompras.setBounds(400, 50, 100, 35);
        btnRegresar.setBounds(500, 410, 100, 35);
        btnTotal.setBounds(380, 410, 100, 35);
        //etiqueta.setBounds(0, 0, 700, 500);
        lbTotal.setBounds(100, 360, 100, 100);
        lbT.setBounds(200, 360, 100, 100);
        lbPerdida.setBounds(100, 360, 100, 100);
        lbP.setBounds(200, 360, 100, 100);
        
        lbTotal.setVisible(false);
        lbPerdida.setVisible(false);
        //diseño
         lbTotal.setForeground(new java.awt.Color(122, 244, 61));
         lbT.setBackground(new java.awt.Color(255, 254, 254));
         lbTotal.setFont(new Font("Impact",Font.PLAIN,25));
         lbT.setFont(new Font("Impact",Font.PLAIN,25));
         
         lbPerdida.setForeground(new java.awt.Color(242, 21, 17));
         lbP.setBackground(new java.awt.Color(255, 254, 254));
         lbPerdida.setFont(new Font("Impact",Font.PLAIN,25));
         lbP.setFont(new Font("Impact",Font.PLAIN,25));
        //
        
        this.add(btnVentas);
        this.add(btnCompras);
        this.add(btnRegresar);
        this.add(btnTotal);
        this.add(lbTotal);
        this.add(lbT);
        this.add(lbPerdida);
        this.add(lbP);
        //this.add(etiqueta);
        
        btnVentas.addActionListener(this);
        btnCompras.addActionListener(this);
        btnRegresar.addActionListener(this);
        btnTotal.addActionListener(this);
        
        tablaVentas();
        tablaCompras();
        
    }
    
    public void tablaVentas(){
        final JTable miTabla=new JTable(tablaV);
        tablaV.addColumn("Fecha");
        tablaV.addColumn("Cliente");
        tablaV.addColumn("Producto");
        tablaV.addColumn("Cantidad");
        tablaV.addColumn("Ganancias");
        
        miTabla.setPreferredScrollableViewportSize(new Dimension(700,400));
        scrollPane=new JScrollPane(miTabla);
        scrollPane.setBounds(100, 100, 500, 280);
        miTabla.getTableHeader().setFont(new Font("Segoe U1",Font.BOLD,12));
        miTabla.getTableHeader().setOpaque(false);
        miTabla.getTableHeader().setBackground(new Color(65, 162, 242));
        miTabla.getTableHeader().setForeground(new Color(255,255,255));
        miTabla.setRowHeight(25);
        //scrollPane.setVisible(false);
        this.add(scrollPane);
         //Tabla de ventas
        String filePath2 = "datosVenta.txt";
        File file2 = new File(filePath2);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file2));
            String firstLine = br.readLine().trim();
            String[] columnsName = firstLine.split(",");
            tablaV.setColumnIdentifiers(columnsName);
            Object[] tableLines = br.lines().toArray();
            for(int i = 0; i < tableLines.length; i++)
            {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split("//");
                tablaV.addRow(dataRow);
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        //para el archivo de ganancias
        try{
                FileInputStream fstream = new FileInputStream("Ganancias.txt");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    ganancias.addItem(strLine);
                    
                    }
            in.close();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex,"ErrorG",JOptionPane.ERROR_MESSAGE);
                }
        
        //para las perdidas
        try{
            FileInputStream fstream = new FileInputStream("Perdidas.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                perdidas.addItem(strLine);
                    
                }
            in.close();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex,"ErrorP",JOptionPane.ERROR_MESSAGE);
                }
    }
    public void tablaCompras(){
        
        final JTable miTabla=new JTable(tablaC);
        //tablaC.addColumn("Fecha");
        //tablaC.addColumn("Producto");
        //tablaC.addColumn("Cantidad");
        //tablaC.addColumn("Perdida");
        
        miTabla.setPreferredScrollableViewportSize(new Dimension(700,400));
        scrollC=new JScrollPane(miTabla);
        scrollC.setBounds(100, 100, 500, 280);
        miTabla.getTableHeader().setFont(new Font("Segoe U1",Font.BOLD,12));
        miTabla.getTableHeader().setOpaque(false);
        miTabla.getTableHeader().setBackground(new Color(65, 162, 242));
        miTabla.getTableHeader().setForeground(new Color(255,255,255));
        miTabla.setRowHeight(25);
        
        this.add(scrollC);
        
        //Tabla de compras
        String filePath = "datosCompra.txt";
         File file = new File(filePath);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine().trim();
            String[] columnsName = firstLine.split(",");
            tablaC.setColumnIdentifiers(columnsName);
            Object[] tableLines = br.lines().toArray();
            for(int i = 0; i < tableLines.length; i++)
            {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split("//");
                tablaC.addRow(dataRow);
            }
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex,"ERROR",JOptionPane.ERROR_MESSAGE);
            //System.out.println(ex);
        }

    }
    
    
    public void actionPerformed(ActionEvent e){
        Object objeto=e.getSource();
        
        if(objeto==btnVentas){
            try{
            scrollPane.setVisible(true);
            scrollC.setVisible(false);
            
            int index=ganancias.getSelectedIndex();
            int cant=ganancias.getItemCount();
            double g,sumas=0;
            String gan,total;
            
            for(int pos=index;pos<cant;pos++){
                ganancias.setSelectedIndex(pos);
                gan=ganancias.getSelectedItem().toString();
                g=Double.parseDouble(gan);
                sumas=sumas+g;
            }
            totalV=sumas;
                    
            total=String.valueOf(sumas);
            lbTotal.setVisible(true);
            lbT.setText(total);
            lbT.setVisible(true);
            
            lbPerdida.setVisible(false);
            lbP.setVisible(false);

                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "No hay ventas");
            }
            
            
            
        }else if(objeto==btnCompras){
            
            try{
                
            scrollPane.setVisible(false);
            scrollC.setVisible(true);
            int index=perdidas.getSelectedIndex();
            int cant=perdidas.getItemCount();
            double p,sumas=0;
            String per,total;
            
            for(int pos=index;pos<cant;pos++){
                perdidas.setSelectedIndex(pos);
                per=perdidas.getSelectedItem().toString();
                p=Double.parseDouble(per);
                sumas=sumas+p;
            }
            totalP=sumas;
            total=String.valueOf(sumas);
            lbPerdida.setVisible(true);
            lbP.setText(total);
            lbP.setVisible(true);
            
            lbTotal.setVisible(false);
            lbT.setVisible(false);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "No hay compras");
            }

        }else if(objeto==btnRegresar){
            dispose();
        }else if(objeto==btnTotal){
            TOTAL=totalV-totalP;
            String mensaje="";
            if(TOTAL<=2000){
                mensaje="Necesitas Mejorar tus ventas";
            }else{
                mensaje="¡Sigue asi!";
            }
            JOptionPane.showMessageDialog(null, "Total de Ganancias: "+TOTAL+"\n"+mensaje);
        }
        
    }
}
