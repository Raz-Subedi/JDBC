
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.table.*;

public class guijdbc extends MouseAdapter implements ActionListener {
    JFrame f;
    JTextField txtId,txtName,txtAddress;
    JButton btnAdd,btnRemove;
    JScrollPane jsp;
    JLabel l1,l2,l3;
    JTable table;
    DefaultTableModel dtm;
    String header[]={"Sno.","Name","Address"};
    String data[][]={};
    Connection conn;
    int row=-1;

    public guijdbc(){
        // Creating frame
        f = new JFrame("Mero Frame");
        f.setSize(600,500);
        f.setLayout(null);

        // Label for Id    
        l1 = new JLabel("ID:");
        l1.setBounds(20,20,50,25);
        f.add(l1);

        // Textfield for Id
        txtId = new JTextField();
        txtId.setBounds(85,20,100,25);
        f.add(txtId);

        // Label for name
        l2 = new JLabel("Name:");
        l2.setBounds(20,50,50,25);
        f.add(l2);

         // Textfield for name
        txtName = new JTextField();
        txtName.setBounds(85,50,100,25);
        f.add(txtName);

        // Label for address
        l3 = new JLabel("Address:");
        l3.setBounds(20,80,70,25);
        f.add(l3);

        // Textfield for address
        txtAddress = new JTextField();
        txtAddress.setBounds(85,80,100,25);
        f.add(txtAddress);

        // Add button
        btnAdd=new JButton("ADD");
        btnAdd.addActionListener(this);
        btnAdd.setBounds(270,50,70,25);
        f.add(btnAdd);

        // Table using data table model
        dtm = new DefaultTableModel(data, header);
        table = new JTable(dtm);
        table.addMouseListener(this);
        // table.setBounds(20, 200, 250, 100);

        // JScrollPane
        jsp = new JScrollPane(table);
        jsp.setBounds(20, 200, 300, 130);
        f.add(jsp);

        // Button for Remove
        btnRemove=new JButton("Remove");
        btnRemove.addActionListener(this);
        btnRemove.setBounds(360,250,100,25);
        f.add(btnRemove);
        btnRemove.setEnabled(false);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        // Java to Database Connection
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bim", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from student;");
             while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
                 Object[] obj = {rs.getInt("id"), rs.getString("name"), rs.getString("address")};
              
                 dtm.addRow(obj);
             }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
public static void main(String[] args) {
    new guijdbc();
                                 // Integer.parseInt(str);
   } 

   //override the abstract class
   @Override
   public void actionPerformed(ActionEvent e) {
       if(e.getSource().equals(btnAdd)){
           //add ko kaam garne
           int id = Integer.parseInt(txtId.getText());
           String name = txtName.getText();
           String address = txtAddress.getText();
           String query = "INSERT INTO student VALUES(?,?,?);";
           try{
               PreparedStatement pstmt = conn.prepareStatement(query);
               pstmt.setInt(1, id);
               pstmt.setString(2, name);
               pstmt.setString(3, address);
               int res = pstmt.executeUpdate();
               if(res>0){
                   dtm.addRow(new Object[]{id, name, address});
                   System.out.println("Sucessfully add to the table");
               }else{
                   System.out.println("Cannot add.");
               }
           }catch(Exception ex){
               ex.printStackTrace();
           }
       }else{
           //remove ko kaam garne
           if(row<0){
            System.out.println("Row not Selected");
            return;
           }
           int selectedId= Integer.parseInt(dtm.getValueAt(row, 0).toString());
           String query="Delete from student where id=?;";
           try{
            PreparedStatement pstmt=conn.prepareStatement(query);
            pstmt.setInt(1,selectedId);
            int res = pstmt.executeUpdate();
            if(res>0){
                System.out.println("Delete Sucess");
                dtm.removeRow(row);
            }
            else{
                System.out.println("Cannot Delete");
            }
            row=-1;
            btnRemove.setEnabled(false);
           }catch(Exception ex){
            ex.printStackTrace();
           }
       }
   }
   @Override
   public void mouseClicked(MouseEvent e){
       row = table.getSelectedRow();
       btnRemove.setEnabled(true);
   }

 }
