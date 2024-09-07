package dev.rennen.springbootproject.proxy.cglib;

import dev.rennen.springbootproject.proxy.TV;
import dev.rennen.springbootproject.proxy.dynamicproxy.TVCompany;
import dev.rennen.springbootproject.proxy.dynamicproxy.TVFactory;

public class TVConsumer {

    public static void main(String[] args) {
        TVCompany tvCompany = (TVCompany) new TVProxyCglib().getProxyInstance(TVFactory.class);
        TV tv = tvCompany.produceTV();
        tvCompany.repair(tv);
        System.out.println("==============================");

        TVFactoryB tvFactoryB = (TVFactoryB) new TVProxyCglib().getProxyInstance(TVFactoryB.class);
        TV tvb = tvFactoryB.produceTVB();
        tvFactoryB.repairB(tvb);
    }
}