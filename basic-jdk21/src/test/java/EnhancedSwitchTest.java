import org.example.sealed.SealedImplAA;
import org.example.sealed.SealedImplAB;
import org.example.sealed.SealedImplB;
import org.example.sealed.SealedInterface;

/**
 * @author rennen.dev
 * @date 2025/10/10 13:46
 */
public class EnhancedSwitchTest {
    public enum ABCD {
        A, B, C, D
    }

    private static void enumSwitch(ABCD abcd) {
        // switch 语句匹配 enum, string, integer, 普通object，可以有遗漏的分支
        switch (abcd) {
            case A -> System.out.println(1);
            case B -> System.out.println(2);
            case C -> {
                System.out.println(3);
                System.out.println(4);
            }
//            case D -> System.out.println(2);
        }

    }

    private static void enumSwitch2(ABCD abcd) {
        // switch 表达式，有返回值，要求不能有遗漏的分支
        // 匹配枚举类型可以穷尽所有分支
        // 匹配 integer 或者 string 不能穷尽所有分支，只能使用 default
        int res = switch (abcd) {
            case A, B -> 1;
            case C -> {
                System.out.println(2);
                // 新引入 yield 关键字为 switch 表达式返回值
                yield 2;
            }
            case D -> 3;
            case null -> 4;
        }; // 最后有分号
    }

    static String describe(Object obj) {
        return switch (obj) {
            case String s when s.isEmpty()      -> "空字符串";
            case String s when s.length() > 10  -> "长字符串";
            case String s                       -> "普通字符串";
            case Integer i when i < 0           -> "负整数";
            case Integer i                      -> "非负整数";
            case null                           -> "null";
            default                             -> "其他类型";
        };
    }


    private static void switchPatternMatching(SealedInterface foo) {
        // sealed class 和 switch 模式匹配一起使用，要求不能有遗漏的分支
        switch (foo) {
            case SealedImplAA aa ->
                    System.out.println(1);
            case SealedImplAB ab ->
                    System.out.println(2);
            case SealedImplB sealedImplB -> System.out.println(3);
        }
    }
}
