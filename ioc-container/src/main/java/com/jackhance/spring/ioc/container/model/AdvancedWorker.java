package com.jackhance.spring.ioc.container.model;

import com.jackhance.spring.ioc.container.annotation.Advanced;

/**
 * 高级工人
 *
 * @author jackhance
 * @date 2021/8/17 0:08
 */
@Advanced
public class AdvancedWorker extends Worker{
    private String hobby;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "AdvancedWorker{" +
                "hobby='" + hobby + '\'' +
                "} " + super.toString();
    }
}
