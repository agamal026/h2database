import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** Diagnostic: compare indexed and scan results, retaining pre-existing failures. */
public class OrderProbe4456 {
    public static void main(String... args) throws Exception {
        String[] indexes = { "V ASC, ID ASC", "V DESC, ID ASC", "V ASC, ID DESC", "V DESC, ID DESC" };
        String[] orders = { "V, ID", "V DESC, ID DESC", "ID", "ID DESC" };
        String[] limits = { "", " LIMIT 1", " LIMIT 2" };
        Object[] values = { "01", 1, new Integer[] { 1, 2 }, new String[] { "1", "02" },
                new Integer[0], null, new Integer[] { null, 2 }, new String[] { "01" }, 0 };
        int cases = 0, mismatches = 0;
        for (int index = 0; index < indexes.length; index++) {
            try (Connection c = DriverManager.getConnection("jdbc:h2:mem:")) {
                Statement s = c.createStatement();
                s.execute("CREATE TABLE U(ID INT, V VARCHAR); CREATE TABLE T(ID INT, V VARCHAR)");
                String rows = "(1,'01'),(2,'1'),(3,'2'),(4,'02'),(5,NULL),(6,'0'),(7,'00'),(8,'9')";
                s.execute("INSERT INTO U VALUES " + rows + "; INSERT INTO T VALUES " + rows);
                s.execute("CREATE INDEX IX ON T(" + indexes[index] + ")");
                for (int order = 0; order < orders.length; order++) {
                    for (int limit = 0; limit < limits.length; limit++) {
                        String suffix = " WHERE V=ANY(?) ORDER BY " + orders[order] + limits[limit];
                        try (PreparedStatement scan = c.prepareStatement("SELECT ID FROM U" + suffix);
                                PreparedStatement indexed = c.prepareStatement("SELECT ID FROM T" + suffix)) {
                            for (int binding = 0; binding < values.length; binding++) {
                                List<Integer> a = read(scan, values[binding]);
                                List<Integer> b = read(indexed, values[binding]);
                                cases++;
                                if (!a.equals(b)) {
                                    mismatches++;
                                }
                                System.out.println(index + ":" + order + ":" + limit + ":" + binding
                                        + "\t" + a + "\t" + b);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("SUMMARY\tcases=" + cases + "\tmismatches=" + mismatches);
    }

    private static List<Integer> read(PreparedStatement p, Object value) throws Exception {
        p.setObject(1, value);
        List<Integer> ids = new ArrayList<>();
        try (ResultSet r = p.executeQuery()) {
            while (r.next()) {
                ids.add(r.getInt(1));
            }
        }
        return ids;
    }
}
