package com.oowanghan.spring.importBean;

import com.oowanghan.spring.scanner.MyClassPathScanner;
import com.oowanghan.spring.scanner.EnableScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * 将注册bean相关的内容放到这里
 * @Author WangHan
 * @Create 2021/12/15 1:27 上午
 */
public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {

        // 扫描 -> 扫描路径， 如何扫描
//        String path = "com.oowanghan.spring.bean";
        // 通过对自定义注解上增加import注解导入当前这个类，就可以在这里获取到注解信息
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableScanner.class.getName());
        String path = (String) annotationAttributes.get("value");

        MyClassPathScanner myClassPathScanner = new MyClassPathScanner(registry);
        // typeFilter有很多实现类，有一个就是annotation的实现
        myClassPathScanner.addIncludeFilter(
                (metadataReader, metadataReaderFactory) -> true
        );
        int scan = myClassPathScanner.scan(path);
        System.out.println(scan);

        // 更换方式如上
//        List<Class> mapperClass = new ArrayList<>();
//        mapperClass.add(UserInterface.class);
//        mapperClass.add(ObjectInterface.class);
//
//        for (Class aClass : mapperClass) {
//            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//            beanDefinition.setBeanClass(DefaultFactoryBean.class);
//            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(aClass);
//            registry.registerBeanDefinition(aClass.getName(), beanDefinition);
//        }
    }

}
