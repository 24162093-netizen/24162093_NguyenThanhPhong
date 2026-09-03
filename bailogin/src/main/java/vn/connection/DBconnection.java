package vn.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

    private final String serverName = "localhost";
    private final String portNumber = "3306";
    private final String dbName = "ShoppingServiceMVC";
    private final String userID = "root";
    private final String password = "A3032006";

    public Connection getConnection() throws Exception {

        String url = "jdbc:mysql://"
                + serverName
                + ":"
                + portNumber
                + "/"
                + dbName
                + "?useSSL=false"
                + "&serverTimezone=Asia/Ho_Chi_Minh"
                + "&allowPublicKeyRetrieval=true";

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                url,
                userID,
                password
        );
    }
}