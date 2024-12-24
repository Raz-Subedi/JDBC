import java.sql.*;
public class InsertData {
public static void main(String[] args) {
    String query = "INSERT INTO student VALUES(3,'Raz','Okhaldhunga')";

try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/bim", "root","");
Statement st = con.createStatement();
int result = st.executeUpdate(query);
if(result>0){
System.out.println("Records inserted");
}else{
System.out.println("Records insertion failed.");
}
st.close();
con.close();
}catch(Exception e){
e.printStackTrace();
}
}
}