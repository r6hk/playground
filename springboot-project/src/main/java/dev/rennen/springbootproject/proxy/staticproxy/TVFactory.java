package dev.rennen.springbootproject.proxy.staticproxy;

import dev.rennen.springbootproject.proxy.TV;

public class TVFactory implements TVCompany {
    @Override
    public TV produceTV() {
        System.out.println("TV factory produce TV...");
        return new TV("小米电视机","合肥");
    }
}