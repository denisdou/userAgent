package com.github.denisdou.useragent.device;
import com.github.denisdou.useragent.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author doujiajun 402550833@qq.com
 */
public class DeviceDictionary {
    private final Map<String, Device> map;
    DeviceDictionary(Map<String, Device> map) {
        this.map = map;
    }

    public static DeviceDictionary addMap(List<Map<String, String >> list) {
        Map<String, Device> dictionary = new HashMap<>();
        list.stream().forEach(x -> {
            String vendor = x.get("vendor");
            String family = x.get("family");
            String deviceType = x.get("deviceType");
            String screenSize = x.get("screenSize");
            String regex = x.get("regex");
            dictionary.put(regex, new Device(vendor, family, deviceType, false, screenSize));
        });
        return new DeviceDictionary(dictionary);
    }

    public Device parseDevice(Device device) {
        if (Constants.PC.equals(device.getDeviceType())
                || Constants.SPIDER.equals(device.getDeviceType())
                || device.getFamily() == null
                || "-".equals(device.getFamily())) {
            return device;
        }

        String replaceFamily = StringUtils.trimToEmpty(StringUtils.replaceEach(device.getFamily(), new String[]{"\u3000"}, new String[]{""}));

        if ("".equals(replaceFamily)) {
            return new Device(device.getVendor(), Constants.DEFAULT_VALUE, device.getDeviceType(), device.isMobile(), Constants.DEFAULT_VALUE);
        }

        if ("".equals(replaceFamily.replaceAll("/", ""))) {
            return device;
        }
        String family = device.getFamily().split("/")[0].replace('_', ' ').toUpperCase();
        if (map.containsKey(family)) {
            return map.get(family);
        }
        return device;
    }
}
