package com.jackhance.spring.ioc.dependency.lookup;

import com.jackhance.spring.ioc.dependency.lookup.model.Worker;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Map;

/**
 * 层次性类型依赖查找
 *
 * @author jackhance
 * @mail jackhance0825@163.com
 */
public class HierarchyTypeLookupBeanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(HierarchyTypeLookupBeanDemo.class);

        // ConfigurableListableBeanFactory -> ConfigurableBeanFactory -> HierarchicalBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        // 设置 Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);

        applicationContext.refresh();

        displayContainsLocalBean(beanFactory, "worker");
        displayContainsLocalBean(parentBeanFactory, "worker");

        displayContainsBean(beanFactory, "worker");
        displayContainsBean(parentBeanFactory, "worker");

        System.out.println("beanFactory.getBean : " + beanFactory.getBean("worker"));

        // 根据 Bean 类型查找实例列表
        //单一类型
        Worker beanOfType = BeanFactoryUtils.beanOfType(beanFactory, Worker.class);
        System.out.println("beanOfType : " + beanOfType);

        // 集合类型
        Map<String, Worker> beansOfTypeIncludingAncestors = BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, Worker.class);
        System.out.println("beansOfTypeIncludingAncestors : " + beansOfTypeIncludingAncestors);

        // 根据 Bean 类型查找名称列表
        String[] beanNamesForTypeIncludingAncestors = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, Worker.class);
        System.out.println("beanNamesForTypeIncludingAncestors : " + Arrays.toString(beanNamesForTypeIncludingAncestors));

        applicationContext.close();
    }

    @Bean
    public Worker annotationWorker() {
        return new Worker(3, "jay");
    }

    /**
     * 打印层级 BeanFactory 是否包含 Bean
     *
     * @param beanFactory 工厂
     * @param name        Bean 名称
     */
    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String name) {
        System.out.printf("层级 BeanFactory[%s] 是否包含 Bean[name : %s] : %s%n",
                beanFactory.hashCode(), name, containsBean(beanFactory, name));
    }

    /**
     * 是否包含 Bean
     *
     * @param beanFactory 工厂
     * @param beanName    Bean 名称
     * @see {@link BeanFactory#containsBean(String)}
     */
    private static boolean containsBean(@Nullable HierarchicalBeanFactory beanFactory, String beanName) {
        if (beanFactory == null) {
            return false;
        }

        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = (HierarchicalBeanFactory) parentBeanFactory;
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    /**
     * 打印当前 BeanFactory 是否包含 Bean
     *
     * @param beanFactory 工厂
     * @param beanName    Bean 名称
     */
    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name : %s] : %s%n",
                beanFactory.hashCode(), beanName, beanFactory.containsLocalBean(beanName));
    }

    public static HierarchicalBeanFactory createParentBeanFactory() {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // XML 配置文件 ClassPath 路径
        String location = "classpath:/META-INF/dependency-lookup-hierarchical.xml";

        // 加载配置
        reader.loadBeanDefinitions(location);

        return beanFactory;
    }


}
