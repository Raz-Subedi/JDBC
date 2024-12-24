import java.sql.*;
public class ReadDataBase {
public static void main(String[] args) {
try {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql:// localhost:3306/bim","root","");
Statement stm = conn.createStatement();
ResultSet rst = stm.executeQuery("Select * from student");

while(rst.next()) {
System.out.println(rst.getInt(1) + " " + rst.getString(2) + " " + rst.getString(3));
}
conn.close();
} catch (Exception e) {
e.printStackTrace();
}
System.out.println("Records Updated");
}
}