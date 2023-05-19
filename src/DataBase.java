import javax.management.Query;
import java.sql.*;

public class DataBase {
    private Connection connection;
    public DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                    "databaseName=Test;integratedSecurity=true;encrypt=false;");
        } catch (Exception ignored) {}
    }
    public boolean checkLoginCredentials(String UserName, String Pass, boolean isAdmin) {
        String query;
        ResultSet ans;
        if(isAdmin)
            query = "select * from admins where userName = '%s' and [password] = '%s'".formatted(UserName, Pass);
        else
            query = "select * from users where userName = '%s' and [password] = '%s'".formatted(UserName, Pass);
        try {
            Statement st = connection.createStatement();
            ans = st.executeQuery(query);
            return ans.next();
        }
        catch (Exception ignored){
        }
        return false;
    }
    public Connection getConnection() {
        return connection;
    }
}
