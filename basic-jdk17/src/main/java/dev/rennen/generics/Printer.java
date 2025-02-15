package dev.rennen.generics;


/**
 * @author rennen.dev
 * @since 2025/1/4 15:47
 */
public class Printer <T>{
    public void print(T t){
        System.out.println(t);
    }

    int sumOfList(List<? super Integer> list) {
        int sum = 0;
        for (int i=0; i<list.size(); i++) {
            list.add(i);
        }
        return sum;
    }


}
