import java.sql.*;
public class PreparedInsert {
    public static void main(String args[]){  
		try{  
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql:// localhost:3306/bim", "root","");  
		PreparedStatement stmt=con.prepareStatement("insert into student values(?,?,?)");  

		stmt.setInt(1,3);
		stmt.setString(2,"Rajkumar");  
        stmt.setString(3,"Kadaghari");

		int res=stmt.executeUpdate();  
		System.out.println(res+" records inserted");  

		con.close();  
		}
		catch(Exception e)
        { System.out.println(e);}  
	}  
}

