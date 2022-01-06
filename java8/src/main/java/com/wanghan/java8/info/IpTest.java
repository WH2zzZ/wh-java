package com.wanghan.java8.info;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Author WangHan
 * @Create 2021/9/16 10:17 下午
 */
public class IpTest {

    @Test
    public void getLocalIpAndHostname() throws SocketException {
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = addresses.nextElement();
                if (ip instanceof Inet4Address && !ip.isLoopbackAddress()) {
                    System.out.println("本机的IP = " + ip.getHostAddress());
                    System.out.println("user.home : " + System.getProperty("user.home"));
                }
            }
        }
    }

    @Test
    public void getLocalInetAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress address = null;
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && address.getHostAddress().indexOf(":") == -1) {
                        System.out.println(address.getHostAddress());
                    }
                }
            }
        } catch (Throwable t) {
        }
    }
}
