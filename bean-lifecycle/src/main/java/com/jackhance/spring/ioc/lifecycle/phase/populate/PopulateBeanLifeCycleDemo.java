package com.jackhance.spring.ioc.lifecycle.phase.populate;

import com.jackhance.spring.ioc.lifecycle.model.Worker;
import com.jackhance.spring.ioc.lifecycle.model.WorkerHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean populate 生命周期示例
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class PopulateBeanLifeCycleDemo {

    public static void main(String[] args) {
        String[] configLocation = {"META-INF/lifecycle-populate.xml"};
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);

        Worker superWorker = applicationContext.getBean("superWorker", Worker.class);
        System.out.println("superWorker : " + superWorker);

        Worker worker = applicationContext.getBean("worker", Worker.class);
        System.out.println("worker : " + worker);

        WorkerHolder workerHolder = applicationContext.getBean("workerHolder", WorkerHolder.class);
        System.out.println("workerHolder : " + workerHolder);

    }
}
