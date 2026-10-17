import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.h2.test.jdbc.TestPreparedStatement;

/** Runs the integrated regression in a separate working directory. */
public class RunRegression4456 {
    public static void main(String... args) throws Exception {
        TestPreparedStatement test = new TestPreparedStatement();
        test.init();
        Method regression = TestPreparedStatement.class.getDeclaredMethod("testPreparedStatementWithAnyParameterTypes");
        regression.setAccessible(true);
        try {
            regression.invoke(test);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof Exception) {
                throw (Exception) cause;
            }
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw exception;
        }
        System.out.println("Integrated ANY parameter-type regression passed");
    }
}
