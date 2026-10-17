import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** Standalone JDBC regression based on https://github.com/h2database/h2database/issues/4456. */
public class Reproduce4456 {
    public static void main(String... args) throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:")) {
            connection.createStatement().execute("CREATE TABLE T(V VARCHAR); INSERT INTO T VALUES ('01')");
            int withoutIndex = count(connection);
            connection.createStatement().execute("CREATE INDEX I ON T(V)");
            int withIndex = count(connection);
            System.out.println("without index: " + withoutIndex + "; with index: " + withIndex);
            if (withoutIndex != 1 || withIndex != 1) {
                throw new AssertionError("Adding an index changed numeric ANY comparison results");
            }
        }
    }

    private static int count(Connection connection) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM T WHERE V = ANY(?)")) {
            statement.setObject(1, 1);
            try (ResultSet result = statement.executeQuery()) {
                result.next();
                return result.getInt(1);
            }
        }
    }
}
