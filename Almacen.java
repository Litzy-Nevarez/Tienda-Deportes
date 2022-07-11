
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Litzy Nevarez García
 */
public class Almacen extends JFrame implements ActionListener{
    
    String []vector =new String[4];
    DefaultTableModel dtm=new DefaultTableModel();
    final JTable miTabla=new JTable(dtm);
    ImageIcon imagen=new ImageIcon("Imagenes\\buscar2.png");
    JLabel lbbuscar=new JLabel(imagen);
    JTextField txtB=new JTextField();
    
    
    ImageIcon reg=new ImageIcon("imagenes\\regresar.png");
    JButton btnRegresar=new JButton(reg);
   
    JButton btnBuscar=new JButton("Buscar");
    
    File archivo;
    FileReader leer;
    BufferedReader almacenamiento;
    
    File fichero;
    
    public Almacen(){
        super("ALMACEN");
        setSize(700,500);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.ORANGE));

        dtm.addColumn("Producto");
        dtm.addColumn("Cantidad");
        dtm.addColumn("Precio Vta");
        dtm.addColumn("Fecha Compra");

        
        miTabla.setPreferredScrollableViewportSize(new Dimension(700,400));
        JScrollPane scrollPane=new JScrollPane(miTabla);
        scrollPane.setBounds(100, 80, 500, 300);
        
        miTabla.getTableHeader().setFont(new Font("Segoe U1",Font.BOLD,12));
        miTabla.getTableHeader().setOpaque(false);
        miTabla.getTableHeader().setBackground(new Color(248, 135, 23));
        miTabla.getTableHeader().setForeground(new Color(255,255,255));
        miTabla.setRowHeight(25);
        
        btnRegresar.setBounds(500, 400, 100, 35);
        btnBuscar.setBounds(350, 400, 100, 35);
        lbbuscar.setBounds(80, 40, 100, 30);
        txtB.setBounds(150, 40, 200, 25);

        this.add(scrollPane);
        this.add(btnRegresar);
        this.add(btnBuscar);
        this.add(lbbuscar);
        this.add(txtB);
        
        btnRegresar.addActionListener(this);
        btnBuscar.addActionListener(this);
        
        
        guardar();
        
    }
    private void Filtro() {
    String valor = txtB.getText();
    int fse = miTabla.getRowCount();
    fse -= 1;
    TableRowSorter trsFiltro = new TableRowSorter(miTabla.getModel());
    miTabla.setRowSorter(trsFiltro);
    trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + valor, 1));
 
}

    public void guardar(){
        
        
        int g=dtm.getColumnCount();
        //System.out.print(g);
         String filePath = "datosCompra.txt";
         File file = new File(filePath);
        //2
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine().trim();
            String[] columnsName = firstLine.split(",");
            dtm.setColumnIdentifiers(columnsName);
            Object[] tableLines = br.lines().toArray();
            for(int i = 0; i < tableLines.length; i++)
            {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split("//");
                dtm.addRow(dataRow);
            }
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex,"ERROR",JOptionPane.ERROR_MESSAGE);
            //System.out.println(ex);
        }
        
    }
    public void actionPerformed(ActionEvent e){
        Object objeto=e.getSource();
        
        if(objeto==btnBuscar){
            Filtro();
   
        }else if(objeto==btnRegresar){
            dispose();
        }
    }
    
}
