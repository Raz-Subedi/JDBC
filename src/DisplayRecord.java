import java.sql.*;
import java.util.Scanner;
public class DisplayRecord {
public static void main(String[] args) {
try {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/bim", "root","");
Statement st = con.createStatement();
Scanner sc = new Scanner(System.in);
System.out.println("Enter roll number:\n");
int roll = sc.nextInt();
String query = "SELECT * FROM result where roll_no=" +roll;
ResultSet rs = st.executeQuery(query);
while (rs.next()) {
System.out.println("\nRoll no: " + rs.getInt("roll_no") + "\nCourse name: " + rs.getString("Course Name") + "\nMarks Obtained: " + rs.getString("Marks Obtained") + "\n");
}
rs.close();
st.close();
con.close();
} catch (Exception e) {
e.printStackTrace();
}}}
