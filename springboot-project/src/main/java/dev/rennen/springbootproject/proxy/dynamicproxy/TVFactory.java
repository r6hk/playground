package dev.rennen.springbootproject.proxy.dynamicproxy;

import dev.rennen.springbootproject.proxy.TV;

public class TVFactory implements TVCompany {
    @Override
    public TV produceTV() {
        System.out.println("TV factory produce TV...");
        return new TV("小米电视机","合肥");
    }

    @Override
    public TV repair(TV tv) {
        System.out.println("tv is repair finished...");
        return new TV("小米电视机","合肥");
    }
}