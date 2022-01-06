package com.oowanghan.agent;

import com.oowanghan.agent.former.MainClassFileTransformer;

import java.lang.instrument.Instrumentation;

/**
 * 代理主类
 * @Author WangHan
 * @Create 2021/9/1 11:40 下午
 */
public class AgentMain {

    public static void premain(String agentArgs, Instrumentation inst){
        inst.addTransformer(new MainClassFileTransformer(), true);
    }
}
