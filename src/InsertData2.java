import java.sql.*;
import java.util.Scanner;
public class InsertData2 {
public static void main(String[] args) {

try {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/bim", "root","");
Statement st = con.createStatement();

Scanner sc = new Scanner(System.in);
System.out.println("ID: ");
int id = sc.nextInt();
//sc.nextLine();

System.out.println("Name: ");
String name = sc.nextLine();

System.out.println("Address: ");
String address = sc.nextLine();

String query = "Insert into student(id,name,address) values (" + id +",'"+name+"','"+address+"')";
int result = st.executeUpdate(query);
if (result > 0) {
System.out.println("Records inserted.");
} else {
System.out.println("Records insertion failed.");
}
st.close();
con.close();
} catch (Exception e) {
e.printStackTrace();
}}}

