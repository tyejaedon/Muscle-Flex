import java.sql.*;



public class DatabaseConnector {
    Connection conn;
    Statement stmt;
    ResultSet rs;

    private static final String URL = "jdbc:mysql://localhost:3306/gymApp";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    // STEP 1: Load the JDBC driver

    void connect()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
           conn =  DriverManager.getConnection(URL, USER, PASSWORD);
           stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    

    public  boolean registerUser(String email, String password, int age, float weight)  {
        connect();
        String sql = "INSERT INTO users (email, password, age, weight) VALUES (?, ?, ?, ?)";
        
        try {
          PreparedStatement preparedStatement = conn.prepareStatement(sql);
          preparedStatement.setString(1, email);
          preparedStatement.setString(2, password);
          preparedStatement.setInt(3, age);  
          preparedStatement.setFloat(4, weight);    
          preparedStatement.executeUpdate();      
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        
        }
    return false;
    }
    public boolean loginUser(String email, String password ){
        connect();
        String sql = "SELECT password FROM users WHERE email = ? ";
        try {
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String stored_p = rs.getString("password");
                if (password.equals(stored_p)) {
                    return true;
                }   
            }

        } catch (Exception e) {
            
            System.err.println(e);
        }
        return false;
    }
  
}
