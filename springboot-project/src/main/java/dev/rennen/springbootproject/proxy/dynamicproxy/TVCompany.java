package dev.rennen.springbootproject.proxy.dynamicproxy;

import dev.rennen.springbootproject.proxy.TV;

public interface TVCompany {

    /**
     * 生产电视机
     * @return 电视机
     */
    public TV produceTV();

    /**
     * 维修电视机
     * @param tv 电视机
     * @return 电视机
     */
    public TV repair(TV tv);
}