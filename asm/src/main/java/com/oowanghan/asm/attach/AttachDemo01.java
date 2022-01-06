package com.oowanghan.asm.attach;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author WangHan
 * @Create 2021/9/9 1:06 上午
 */
public class AttachDemo01 {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        VirtualMachine vm = VirtualMachine.attach("55161");
        vm.loadAgent("/Users/wanghan/IdeaProjects/wh/kratos-agent/agent-boot/target/agent-boot-1.0-SNAPSHOT-jar-with-dependencies.jar");
        vm.detach();
    }
}
