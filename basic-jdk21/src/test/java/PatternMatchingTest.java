import org.example.sealed.SealedImplAA;
import org.example.sealed.SealedImplAB;
import org.example.sealed.SealedImplB;
import org.example.sealed.SealedInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author rennen.dev
 * @date 2025/10/10 11:36
 */
public class PatternMatchingTest {

    @Test
    public void test1() {
        SealedInterface foo = new SealedImplAA();
        Assertions.assertEquals("aa", ifPatternMatching(foo));
    }

    private static String ifPatternMatching(SealedInterface foo) {

        if (foo instanceof SealedImplAA aa) {
            return aa.hello();
        }

//        if (foo instanceof SealedImplAA) {
//            SealedImplAA aa = (SealedImplAA) foo;
//            return aa.hello();
//        }
        return "error";
    }

}
