import org.example.sealed.SealedImplAA;
import org.example.sealed.SealedImplAB;
import org.example.sealed.SealedImplB;
import org.example.sealed.SealedInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author rennen.dev
 * @date 2025/10/10 11:16
 */
public class SealedSwitchTest {

    @Test
    public void test1() {
        switch1(null);

        switch2(new SealedImplAA());

        Assertions.assertThrows(NullPointerException.class, () -> switch3(null));
    }


    private static void switch1(SealedInterface sealedInterface) {
        // 作为表达式，必须有返回值，所以要求穷尽枚举
        String res = switch (sealedInterface) {
            case SealedImplAA aa -> "AA";
            case SealedImplAB ab -> "AB";
            case SealedImplB b -> "B";
            // case null 加不加都行，加了不会报 npe
            case null -> "null";
            // 已穷尽，可以不加 default
        };
        System.out.println(res);
    }

    private static void switch2(SealedInterface sealedInterface) {
        // 作为表达式，必须有返回值，所以要求穷尽枚举
        String res = switch (sealedInterface) {
            case SealedImplAA aa -> "AA";
            // null 和 default 可以放在一起
            case null, default -> "null or default";
        };

        System.out.println(res);
    }

    private static void switch3(SealedInterface sealedInterface) {
        // 作为表达式，必须有返回值，所以要求穷尽枚举
        String res = switch (sealedInterface) {
            case SealedImplAA aa -> "AA";
            case SealedImplAB ab -> "AB";
            // default 不会匹配 null
            default -> "default";
        };

        System.out.println(res);
    }


}
