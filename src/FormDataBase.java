import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
public class FormDataBase  implements ActionListener {
    JFrame f;
   JLabel idLabel,nameLabel,addressLabel;
   JTextField txtID,txtName,txtAddress;

   JButton addButton,removeButton,clearButton;
    JTable table;
    DefaultTableModel dtm;
    JScrollPane jsp;
    Connection conn;
    public FormDataBase(){
        f = new JFrame("Rajkumar Subedi");
        idLabel = new JLabel("ID:");
        idLabel.setBounds(40,50,60,30);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(40,90,60,30);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(40,130,60,30);

        txtID = new JTextField();
        txtID.setBounds(110,50,150,30);

        txtName = new JTextField();
        txtName.setBounds(110,90,150,30);

        txtAddress = new JTextField();
        txtAddress.setBounds(110,130,150,30);

        addButton = new JButton("Add");
        addButton.setBounds(300,100,100,30);
        addButton.addActionListener(this);

        removeButton = new JButton("Remove");
        removeButton.setBounds(400,200,80,30);
        removeButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.setBounds(410,100,100,30);
        clearButton.addActionListener(this);

        String title[] ={"ID","Name","Address"};

        String data[][] = {};

        dtm = new DefaultTableModel(data,title);

        table = new JTable(dtm);

        jsp = new JScrollPane(table);
        jsp.setBounds(40,170,350,200);


        f.add(idLabel);
        f.add(nameLabel);
        f.add(addressLabel);
        f.add(txtID);
        f.add(txtName);
        f.add(txtAddress);
        f.add(addButton);
        f.add(removeButton);
        f.add(clearButton);
        f.add(jsp);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,500);
        f.setLayout(null);
        f.setVisible(true);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bim","root","");
Statement stm = conn.createStatement();
ResultSet rst = stm.executeQuery("Select * from student");
while(rst.next()){
Object arr[] = {rst.getInt("id"),rst.getString("name"),rst.getString("address")};
dtm.addRow(arr);
}


}catch(Exception e){
System.out.println(e);;
}
}
public static void main(String[] args) {
new FormDataBase();
}

@Override
public void actionPerformed(ActionEvent e){
if(e.getSource() .equals( addButton)){
//                int id = Integer.parseInt(txtID.getText());
String id = txtID.getText();
String name = txtName.getText();

String address = txtAddress.getText();

if( id.equals("")|| name.equals("") || address.equals("")  ){
JOptionPane.showMessageDialog(f,"Blank Data Cannot be Inserted","Empty Data",JOptionPane.ERROR_MESSAGE);
return;

}
else {

try {

PreparedStatement pstm = conn.prepareStatement("Insert into student (id,name,address) values (?,?,?)");
pstm.setInt(1,Integer.parseInt(id));
pstm.setString(2,name);
pstm.setString(3,address);
int res = pstm.executeUpdate();
if(res > 0){
Object[] arr = {id,name,address};
dtm.addRow(arr);
}

}catch (Exception ex){
System.out.println(e);;
}
}
}
else if (e.getSource() .equals( removeButton)) {
int rowToBeDeleted =  table.getSelectedRow();
if(rowToBeDeleted != -1){
int idToDelete = Integer.parseInt(table.getValueAt(rowToBeDeleted,0).toString());
try {
PreparedStatement stm = conn.prepareStatement("Delete from student where id = ?" );
stm.setInt(1,idToDelete);
int s = JOptionPane.showConfirmDialog(f,"Confirm Delete");
if(s == 0) {
int res = stm.executeUpdate();
if (res > 0) {
dtm.removeRow(rowToBeDeleted);
}
return;
}
return;

}catch (Exception ex){
ex.printStackTrace();
}

}
else {
JOptionPane.showMessageDialog(f,"Please Select a row to Delete","No row Selected",JOptionPane.ERROR_MESSAGE);
//                       JOptionPane.showMessageDialog(f, "No Item selected to delete", "No Item Selected", JOptionPane.ERROR_MESSAGE);
return;
                   }
            }
            else if (e.getSource().equals(clearButton)) {
                txtID.setText("");
                txtName.setText("");
                txtAddress.setText("");
            }
    }
}

