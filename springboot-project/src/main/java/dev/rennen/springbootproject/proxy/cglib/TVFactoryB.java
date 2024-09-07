package dev.rennen.springbootproject.proxy.cglib;

import dev.rennen.springbootproject.proxy.TV;

public class TVFactoryB {

    public TV produceTVB() {
        System.out.println("tv factory B producing tv.... ");
        return new TV("华为电视机", "南京");
    }

    public TV repairB(TV tv) {
        System.out.println("tv B is repair finished.... ");
        return tv;
    }
}