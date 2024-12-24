import java.sql.*;
import java.util.Scanner;
public class PreparedUserDisplay {
     public static void main(String args[]){  
		try{  
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql:// localhost:3306/bim", "root","");
		PreparedStatement stmt=con.prepareStatement("select * from salary where emp_id = ?;");  
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the employee id :");
                int id = sc.nextInt();
                stmt.setInt(1, id);
		ResultSet rs=stmt.executeQuery();  
		while(rs.next()){  
                    System.out.println(rs.getInt("emp_id")+" "+rs.getString("emp_name") +" "+ rs.getString("emp_salary"));  
		}  
  		con.close();  
  		}catch(Exception e){ System.out.println(e);}  
  	}  
}

