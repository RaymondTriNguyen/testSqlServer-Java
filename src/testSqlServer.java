import java.sql.*;
public class testSqlServer{
    static final String db_url="jdbc:sqlserver://localhost:1433;databaseName=testDBJava;integratedSecurity=true;trustServerCertificate=true";

    public static void main(String[] args){

        Connection conn= null;
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(db_url);
            System.out.println("Connected to SQL Server.");

            String insertSql = "insert into Users(name, email) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, "def");
            pstmt.setString(2, "def@example.com");
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");

            String selectSql = "SELECT * FROM Users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);

            System.out.println("User Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Email: " + rs.getString("email"));
            }
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
            System.out.println("All done.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
