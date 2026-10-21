import java.sql.*;

public class JdbcDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(url, user, password)) {
                String sql = "INSERT INTO student VALUES (?, ?, ?)";

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, 104);
                    ps.setString(2, "Aditya");
                    ps.setString(3, "IT");
                    ps.executeUpdate();
                    System.out.println("Record Inserted");
                }

                try (Statement st = con.createStatement();
                     ResultSet rs = st.executeQuery("SELECT * FROM student")) {
                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + " " +
                            rs.getString("name") + " " +
                            rs.getString("branch")
                        );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}