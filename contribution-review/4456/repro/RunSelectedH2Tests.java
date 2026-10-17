import org.h2.test.TestAll;
import org.h2.test.TestBase;

/** Runs the named upstream tests directly so assertion failures retain a nonzero exit. */
public class RunSelectedH2Tests {
    public static void main(String... args) throws Exception {
        TestAll config = new TestAll();
        config.memory = true;
        for (String name : args) {
            TestBase test = (TestBase) Class.forName(name).getDeclaredConstructor().newInstance();
            test.init(config);
            if (!test.isEnabled()) {
                throw new AssertionError("Unexpected disabled test: " + name);
            }
            System.out.println("START " + name + " memory=true");
            test.testFromMain();
            System.out.println("PASS " + name + " memory=true");
        }
    }
}
