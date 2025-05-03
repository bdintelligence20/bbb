package com.ruoyi.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sun.net.util.IPAddressUtil;

/**
 * ip 合法性校验等
 *
 * @author liuzhuoming
 */
public class IpAddrUtil {

    public static boolean isIpv4(String ipAddr) {
        return IPAddressUtil.isIPv4LiteralAddress(ipAddr);
    }

    public static boolean isIpv6(String ipAddr) {
        return IPAddressUtil.isIPv6LiteralAddress(ipAddr);
    }

    public static boolean isIp(String ipAddr) {
        return isIpv4(ipAddr) || isIpv6(ipAddr);
    }

    public static IpVersion getIpVersionByIpAddr(String ipAddr) {
        if (isIpv4(ipAddr)) {
            return IpVersion.IPV4;
        }
        if (isIpv6(ipAddr)) {
            return IpVersion.IPV6;
        }
        return IpVersion.UNKNOWN;
    }

    public static boolean isIpv4CIDR(String ipAddr) {
        String[] strings = ipAddr.split("/");
        if (strings.length != 2) return false;

        try {
            int cidr = Integer.parseInt(strings[1]);
            if (cidr < 0 || cidr > 32) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return isIpv4(strings[0]);
    }

    public static boolean isIpv6CIDR(String ipAddr) {
        String[] strings = ipAddr.split("/");
        if (strings.length != 2) return false;

        try {
            int cidr = Integer.parseInt(strings[1]);
            if (cidr < 0 || cidr > 128) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return isIpv6(strings[0]);
    }

    public static boolean isCIDR(String ipAddr) {
        return isIpv4CIDR(ipAddr) || isIpv6CIDR(ipAddr);
    }

    /**
     * <pre>
     * 当传入值是 CIDR 时直接返回
     * 当传入值是 IP 但不是 CIDR 时使用最小范围补全 CIDR 表达式（ipv4=32，ipv6=128）
     * 当传入值不是 IP 也不是 CIDR 时返回 null
     * </pre>
     *
     * @param ipAddr 用来校验的 ip 地址
     * @return CIDR
     */
    public static String completeCIDR(String ipAddr) {
        if (isCIDR(ipAddr)) {
            return ipAddr;
        }
        if (isIp(ipAddr)) {
            IpVersion ipVersion = getIpVersionByIpAddr(ipAddr);
            switch (ipVersion) {
                case IPV4:
                    return ipAddr + "/32";
                case IPV6:
                    return ipAddr + "/128";
                case UNKNOWN:
                default:
            }
        }
        return null;
    }

    @Getter
    @AllArgsConstructor
    public enum IpVersion {
        IPV4("ipv4"),
        IPV6("ipv6"),
        UNKNOWN("unknown"),
        ;
        private final String name;
    }
}
