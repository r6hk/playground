package dev.rennen.springbootproject.proxy.dynamicproxy;

import dev.rennen.springbootproject.proxy.TV;

public class TVConsumer {

    public static void main(String[] args) {
        TVCompany target = new TVFactory();
        TVCompany tvCompany = (TVCompany) new TVProxyFactory(target).getProxy();
        TV tv = tvCompany.produceTV();
        tvCompany.repair(tv);
    }
}