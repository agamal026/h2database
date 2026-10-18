/*
 * JDBC validation companion for H2 issue #4454.
 * Prepared with AI assistance. This is automated validation, not human review.
 * This evidence-only file is not part of the proposed upstream source change.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Run4454 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1 || !(args[0].equals("baseline") || args[0].equals("candidate"))) {
            throw new IllegalArgumentException("Expected baseline or candidate");
        }
        boolean baseline = args[0].equals("baseline");
        duplicateColumns(baseline);
        permutation(baseline);
        System.out.println(baseline ? "CONFIRMED: 2 real-engine negative controls" :
                "PASS: duplicate-column recovery, three-column permutation, and selective index plan");
    }

    private static void duplicateColumns(boolean baseline) throws Exception {
        try (Connection c = DriverManager.getConnection("jdbc:h2:mem:duplicate4454");
                Statement s = c.createStatement()) {
            s.execute("CREATE TABLE T(A INT, B INT)");
            s.execute("CREATE INDEX I ON T(A, B)");
            s.execute("INSERT INTO T VALUES (5, 1), (6, 2)");
            String sql = "SELECT A, B FROM T WHERE (B, B) = (1, 1) OR (B, B) = (2, 3)";
            try {
                String actual = rows(s, sql, 2);
                System.out.println("duplicate result=" + actual);
                if (baseline) {
                    throw new AssertionError("Baseline did not reproduce the reported internal error");
                }
                require("5,1;".equals(actual), "Unexpected duplicate-column result: " + actual);
            } catch (SQLException e) {
                if (!baseline || e.getErrorCode() != 50000 ||
                        !e.getMessage().contains("Unexpected code path")) {
                    throw e;
                }
                System.out.println("EXPECTED baseline duplicate-column failure: " + e);
            }
        }
    }

    private static void permutation(boolean baseline) throws Exception {
        try (Connection c = DriverManager.getConnection("jdbc:h2:mem:permutation4454");
                Statement s = c.createStatement()) {
            s.execute("CREATE TABLE T(A INT, B INT, C INT)");
            s.execute("CREATE INDEX I ON T(A, B, C)");
            s.execute("INSERT INTO T VALUES (1, 2, 3), (4, 5, 6), (7, 8, 9)");
            String sql = "SELECT A, B, C FROM T WHERE (B, C, A) IN ((2, 3, 1), (5, 6, 4)) ORDER BY A";
            String actual = rows(s, sql, 3);
            System.out.println("permuted result=" + actual + "; expected=1,2,3;4,5,6;");
            try (ResultSet r = s.executeQuery("EXPLAIN ANALYZE " + sql)) {
                require(r.next(), "No EXPLAIN row");
                String plan = r.getString(1);
                System.out.println("permuted plan:\n" + plan);
                if (!baseline) {
                    require(plan.contains("PUBLIC.I: IN(ROW (1, 2, 3), ROW (4, 5, 6))"),
                            "Expected correctly permuted selective composite-index bounds");
                }
            }
            if (baseline) {
                require(!"1,2,3;4,5,6;".equals(actual), "Baseline permutation negative control did not fail");
                System.out.println("EXPECTED baseline wrong-result failure confirmed");
            } else {
                require("1,2,3;4,5,6;".equals(actual), "Incorrect permuted index result: " + actual);
            }
        }
    }

    private static String rows(Statement s, String sql, int columns) throws SQLException {
        StringBuilder b = new StringBuilder();
        try (ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                for (int i = 1; i <= columns; i++) {
                    if (i > 1) {
                        b.append(',');
                    }
                    b.append(r.getInt(i));
                }
                b.append(';');
            }
        }
        return b.toString();
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
