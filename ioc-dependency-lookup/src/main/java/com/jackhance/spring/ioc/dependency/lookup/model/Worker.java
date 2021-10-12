package com.jackhance.spring.ioc.dependency.lookup.model;

/**
 * 工人
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class Worker {

    private int id;

    private String name;

    public Worker() {
    }

    public Worker(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hashCode='" + hashCode() + '\'' +
                '}';
    }
}
