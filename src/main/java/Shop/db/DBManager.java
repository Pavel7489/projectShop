package Shop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

        private static final String DATABASE_URL =
                "jdbc:mysql://127.0.0.1:3306/shop?serverTimezone=UTC";
        private static final String USER = "root";
        private static final String PASS = "root";

        private static Connection connection;

        static {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static Connection getConnection() {

            return connection;

    }
}
