package com.jackhance.spring.ioc.lifecycle.model;

import org.springframework.beans.factory.BeanNameAware;

/**
 * 工人
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class Worker implements BeanNameAware {

    private long id;

    private String name;

    private Location location;

    private String beanName;

    public Worker() {
    }

    public Worker(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", beanName=" + beanName +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
