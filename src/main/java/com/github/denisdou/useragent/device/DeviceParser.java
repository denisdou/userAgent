package com.github.denisdou.useragent.device;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author doujiajun 402550833@qq.com
 */
public class DeviceParser {
    private List<DevicePattern> patterns;

    public DeviceParser(List<DevicePattern> patterns) {
        this.patterns = patterns;
    }

    public static DeviceParser addList(List<Map<String, String>> list) {
        return new DeviceParser(
                list.stream().map(config -> {
                    return DevicePattern.addFromMap(config);
                }).collect(Collectors.toList())
        );
    }

    public Device parse(String agentString) {
        if (StringUtils.isBlank(agentString)) {
            return Device.DEFAULT_PC;
        }

        Device device;
        for (DevicePattern p : patterns) {
            if ((device = p.match(agentString)) != null) {
                return device;
            }
        }
        String lower = agentString.toLowerCase();
        if (lower.contains("ottsdk")){
            return Device.DEFAULT_TV;
        }else if(lower.contains("android") || lower.contains("phone") || lower.contains("mobile")){
            return Device.DEFAULT_PHONE;
        }
        return Device.DEFAULT_PC;
    }
}
