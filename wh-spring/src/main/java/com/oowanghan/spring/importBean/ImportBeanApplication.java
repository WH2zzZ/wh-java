package com.oowanghan.spring.importBean;

import com.oowanghan.spring.scanner.EnableScanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @Author WangHan
 * @Create 2021/12/14 1:55 上午
 */
// 这个是用户自己的实现类
@ComponentScan("com.oowanghan.spring.importBean")
// 这两个是底层的框架类
@EnableScanner("com.oowanghan.spring.bean")
public class ImportBeanApplication {


    /**
     * 自己实现类似mybatis的mapper接口
     * 1。 需要实现自己的类扫描器 -> MyClassPathScanner
     * 2。 通过FactoryBean来实现接口对象的动态代理（需要代理出要执行的方法体） -> DefaultFactoryBean
     * 3。 最后通过注册器
     * 4。 Import导入自己实现的register，会自动实现里面的bean注册
     * @param args
     */
    public static void main(String[] args) {

        // 方案3
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ImportBeanApplication.class);
        applicationContext.refresh();

        ImportService importService = applicationContext.getBean("importService", ImportService.class);
        importService.test();
    }
}
