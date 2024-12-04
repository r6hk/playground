package dev.rennen;

/**
 * @author rennen.dev
 * @date 2024/12/1 10:50
 */
public class TryCatchTest {

    public int vaule = 0;

    public static void main(String[] args) {
//        System.out.println("test1()函数返回：" + test1());
//        System.out.println("test2()函数返回：" + test2().vaule);
        System.out.println("test3()函数返回：" + test3());
        System.out.println("test4()函数返回：" + test4());
    }

    private static int test1(){
        int i = 0;
        try {
            System.out.println("Try block executing: " + ++i);
            return i;
        }catch (Exception e){
            System.out.println("Catch Error executing: " + ++i);
            return -1;
        }finally {
            System.out.println("finally executing: " + ++i);
        }
    }

    private static TryCatchTest test2(){
        TryCatchTest t = new TryCatchTest();
        try {
            t.vaule = 1;
            System.out.println("Try block executing: " + t.vaule);
            return t;
        }catch (Exception e){
            t.vaule = -1;
            System.out.println("Catch Error executing: " + t.vaule);
            return t;
        }finally {
            t.vaule = 3;
            System.out.println("finally executing: " + t.vaule);
        }
    }

    private static int test3(){
        int i = 0;
        try {
            System.out.println("Try block executing: " + ++i);
            return ++i;
        }catch (Exception e){
            System.out.println("Catch Error executing: " + ++i);
            return -1;
        }finally {
            System.out.println("finally executing: " + ++i);
            return i;
        }
    }

    private static int test4(){
        int i = 0;
        try {
            System.out.println("Try block executing: " + ++i);
            throw new Exception();
        }catch (Exception e){
            System.out.println("Catch Error executing: " + ++i);
            return -1;
        }finally {
            System.out.println("finally executing: " + ++i);
            return i;
        }
    }
}
