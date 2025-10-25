package dev.rennen;

public class Point {

    public static void main(String[] args) {
        test(null);
    }

    public enum Status {
        SUCCESS, FAIL, DEFAULT;
    }

    private static int test(Integer status) {
        switch (status) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
        return 1;
    }
}