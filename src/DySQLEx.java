import java.sql.*;
import java.util.Scanner;
public class DySQLEx {
    public static void main(String[] args) {
        String name,address;
        int id;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your Id: ");
        id=sc.nextInt();
        System.out.print("Enter your name: ");
        name=sc.nextLine();
        System.out.print("Enter your address: ");
        address =sc.nextLine();
        String query ="INSERT INTO student VALUES(?,?,?);";
        String url="jdbc:mysql://localhost:3306/bim";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,"root","");
            PreparedStatement pstmt=conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            int res=pstmt.executeUpdate();
            if(res>0){
                System.out.println("inserted");
            }
            else{
                System.out.println("Insert failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
