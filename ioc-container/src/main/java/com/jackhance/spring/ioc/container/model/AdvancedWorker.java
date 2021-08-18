package com.jackhance.spring.ioc.container.model;

import com.jackhance.spring.ioc.container.annotation.Advanced;

/**
 * 新生代工人
 *
 * @author jackhance
 * @mail jackhance0825@163.com
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
