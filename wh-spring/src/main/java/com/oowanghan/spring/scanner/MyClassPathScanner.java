package com.oowanghan.spring.scanner;

import com.oowanghan.spring.factorybean.DefaultFactoryBean;
import com.oowanghan.spring.importBean.MyImportBeanDefinitionRegister;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * spring的扫描器扩展
 * @Author WangHan
 * @Create 2021/12/15 1:42 上午
 */
public class MyClassPathScanner extends ClassPathBeanDefinitionScanner {
    public MyClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     *
     * @param beanDefinition
     * @return
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        System.out.println(Arrays.toString(basePackages));
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        beanDefinitionHolders.forEach(beanDefinitionHolder -> {
            // 扫描到的bean其实是接口相关的信息
            // 这里更换bean信息为DefaultFactoryBean.class
            BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();
            beanDefinition.getConstructorArgumentValues()
                    .addGenericArgumentValue(Objects.requireNonNull(beanDefinition.getBeanClassName()));
            beanDefinition.setBeanClassName(DefaultFactoryBean.class.getName());
        });
        return beanDefinitionHolders;
    }
}
