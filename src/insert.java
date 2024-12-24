import java.sql.*;
public class insert {
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
          
            int result=stmt.executeUpdate("insert into student values ('5', 'Sandip', 'Pepsicola');");
            // For inserting the data

            if(result>0){
                System.out.println("Value Inserted");
            }
            else{
                System.out.println("Insertion failed");
           } 

            //Step-5: Close the conncection
            conn.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}