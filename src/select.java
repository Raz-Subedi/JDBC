import java.sql.*;
public class select {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/bim";
        String user="root";
        String pass="";

        try {
            // Step-1: Register the Driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step-2: Create connection
            Connection conn = DriverManager.getConnection(url, user, pass);

            // Step-3: Create Statement
            Statement stmt=conn.createStatement();

            //Step-4: Execute Queries
            ResultSet result=stmt.executeQuery("select * from student");


            //For selecting the data
            while(result.next()) 
            {
                System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getString(3));
            }

            //Step-5: Close the conncection
            conn.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}