package com.oowanghan.agent;

import com.oowanghan.agent.constant.PatternConstant;
import com.oowanghan.agent.entity.AppMetaInfo;
import com.oowanghan.agent.entity.SystemMetaInfo;
import com.oowanghan.agent.transfer.TraceClassFileTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;

/**
 * 代理主类
 * @Author WangHan
 * @Create 2021/9/1 11:40 下午
 */
public class AgentStarter {

    /**
     * 主程序之前运行代理程序
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("主程序运行前");
        switch (agentArgs){
            case PatternConstant.SPRING:
            default:
//                inst.addTransformer(new TraceClassFileTransformer(agentArgs), true);
        }
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("=========classpath:" + System.getProperty("application.name"));
        System.out.println("=========classpath:" + runtimeMXBean.getSystemProperties());
        System.out.println("boot class path: " + runtimeMXBean.getBootClassPath());
        System.out.println("lib path: " + runtimeMXBean.getLibraryPath());

        System.out.println("agent runtime mx bean:" + runtimeMXBean.getName());
//        user.name 用户的账户名称
//        user.home 用户的主目录
//        user.dir 用户的当前工作目录
        System.out.println("user.name : " + System.getProperty("user.name"));
        System.out.println("user.home : " + System.getProperty("user.home"));
        System.out.println("user.dir : " + System.getProperty("user.dir"));
        System.out.println(SystemMetaInfo.getInstance());
        System.out.println(AppMetaInfo.getInstance());
        System.out.println("agent end");
    }

    /**
     * 在主程序运行之后的代理程序
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst){
//        inst.addTransformer(new TraceClassFileTransformer(agentArgs), true);
        System.out.println("主程序运行后");
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        Arrays.stream(allLoadedClasses).forEach(aClass -> {
            System.out.println(aClass.getName());
        });
        System.out.println("agent end");
    }
}
