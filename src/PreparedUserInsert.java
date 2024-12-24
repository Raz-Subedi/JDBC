import java.sql.*;
import java.util.Scanner;
public class PreparedUserInsert {
     public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/bim", "root","");
            Statement st = con.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.println("Roll_no: ");
            int roll = sc.nextInt();   

            System.out.println("Course Name: ");
            String name = sc.nextLine();
            sc.nextLine();

            System.out.println("Mark Obtained: ");
            int mark = sc.nextInt();
            
            String query = "Insert into result(roll_no,Course Name,Marks Obtained) values(?,?,?);";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, roll);
            pst.setString(2, name);
            pst.setInt(3,mark);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Records inserted.");
            } else {
                System.out.println("Records insertion failed.");
            }
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
