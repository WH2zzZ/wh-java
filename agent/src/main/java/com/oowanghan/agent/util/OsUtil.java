
package com.oowanghan.agent.util;

import java.util.List;
import oshi.SystemInfo;
import oshi.driver.linux.proc.CpuInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.Sensors;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class OsUtil {
    private static final SystemInfo systemInfo = new SystemInfo();
    private static final HardwareAbstractionLayer hardware;
    private static final OperatingSystem os;

    public OsUtil() {
    }

    public static OperatingSystem getOs() {
        return os;
    }

    public static OSProcess getCurrentProcess() {
        return os.getProcess(os.getProcessId());
    }

    public static HardwareAbstractionLayer getHardware() {
        return hardware;
    }

    public static ComputerSystem getSystem() {
        return hardware.getComputerSystem();
    }

    public static GlobalMemory getMemory() {
        return hardware.getMemory();
    }

    public static CentralProcessor getProcessor() {
        return hardware.getProcessor();
    }

    public static Sensors getSensors() {
        return hardware.getSensors();
    }

    public static List<HWDiskStore> getDiskStores() {
        return hardware.getDiskStores();
    }

    public static List<NetworkIF> getNetworkIFs() {
        return hardware.getNetworkIFs();
    }

    static {
        hardware = systemInfo.getHardware();
        os = systemInfo.getOperatingSystem();
    }
}
