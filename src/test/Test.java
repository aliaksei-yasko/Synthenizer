import java.awt.datatransfer.StringSelection;

/**
 * @author Q-YAA
 */
public class Test {

    @org.junit.Test
    public void test() {
        String str = "   DASd ASDSDAS     asdasda   ";
        System.out.println(str.replaceAll("\\s+", ""));
    }
}
