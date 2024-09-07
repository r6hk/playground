package dev.rennen.springbootproject.proxy.staticproxy;

import dev.rennen.springbootproject.proxy.TV;

public class TVConsumer {

    public static void main(String[] args) {
        TVProxy tvProxy = new TVProxy();
        TV tv = tvProxy.produceTV();
        System.out.println(tv);
    }
}