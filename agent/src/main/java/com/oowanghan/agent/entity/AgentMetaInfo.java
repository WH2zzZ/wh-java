package com.oowanghan.agent.entity;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * agent获取的项目源信息
 * @Author WangHan
 * @Create 2021/9/12 7:27 下午
 */
public class AgentMetaInfo {

    private AgentMetaInfo() {
        this.agentId = initAppId();
    }

    private static class AgentMetaInfoInstance{
        private static final AgentMetaInfo INSTANCE = new AgentMetaInfo();
    }

    public static AgentMetaInfo getInstance() {
        return AgentMetaInfoInstance.INSTANCE;
    }

    private String initAppId() {
        return agentName;
    }

    private String agentId;

    private String agentName;



}
