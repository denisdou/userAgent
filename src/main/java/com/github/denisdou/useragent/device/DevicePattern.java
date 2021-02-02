package com.github.denisdou.useragent.device;
import com.github.denisdou.useragent.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author doujiajun
 * @date 15:45 2021/1/27
 * @email 402550833@qq.com
 */
public class DevicePattern {
    private final Pattern pattern;
    private final String vendor;
    private final String family;
    private final String deviceType;
    private final boolean isMobile;

    DevicePattern(Pattern pattern, String vendor, String family, String deviceType, boolean isMobile) {
        this.pattern = pattern;
        this.vendor = vendor;
        this.family = family;
        this.deviceType = deviceType;
        this.isMobile = isMobile;
    }

    public static DevicePattern addFromMap(Map<String, String> patternMap) {
        String regex = patternMap.get(Constants.REGEX);
        if (StringUtils.isBlank(regex)) {
            throw new IllegalArgumentException("device regex missing");
        }

        String deviceVendor = patternMap.get(Constants.VENDOR);
        String deviceFamily = patternMap.get(Constants.DEVICE);
        String isMobileString = patternMap.get(Constants.IS_MOBILE);
        boolean isMobile = true;
        if (isMobileString != null && isMobileString.equalsIgnoreCase("false")) {
            isMobile = false;
        }
        String deviceTypeString = patternMap.get(Constants.DEVICE_TYPE);
        return new DevicePattern(Pattern.compile(regex), deviceVendor, deviceFamily, deviceTypeString, isMobile);
    }

    public Device match(String agentString) {
        Matcher matcher = pattern.matcher(agentString);
        if (!matcher.find()) {
            return null;
        }
        int groupCount = matcher.groupCount();

        String deviceVendor = null, deviceFamily = null;
        String deviceTypeString = deviceType;

        if (deviceTypeString == Constants.DEFAULT_VALUE && isMobile) {
            deviceTypeString = Constants.PHONE;
        }

        if (StringUtils.isNotBlank(vendor)) {
            deviceVendor = vendor;
        } else if (groupCount > 0) {
            deviceVendor = matcher.group(1);
        }

        if (family != null) {
            deviceFamily = family;
            if (family.contains("$1") && groupCount >= 0 && matcher.group(1) != null) {
                deviceFamily = family.replaceFirst("\\$1", Matcher.quoteReplacement(matcher.group(1)));
            }

            if (family.contains("$2") && groupCount >= 2 && matcher.group(2) != null) {
                deviceFamily = family.replaceFirst("\\$2", Matcher.quoteReplacement(matcher.group(2)));
            }
        } else if (groupCount >= 2) {
            deviceFamily = matcher.group(2);
        }

        if (deviceFamily == null) {
            deviceFamily = Constants.DEFAULT_VALUE;
        }
        //parse device id
        String uuid = deviceId(agentString);
        return deviceVendor == null ? null : new Device(deviceVendor, deviceFamily, deviceTypeString, isMobile, screenSize(agentString), uuid);
    }

    private String deviceId(String agentString) {
        Pattern deviceIdPattern = Pattern.compile("[\\s&;\"](deviceid|deviceId|sdk_guid|UTDID|GUID|guid|Id|ID|id|udid|UDID|MZ)[\" /:=]+([\\w-]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = deviceIdPattern.matcher(agentString);
        return matcher.find() ? matcher.group(2) : Constants.DEFAULT_VALUE;
    }

    //parse screen size
    private String screenSize(String agentString) {
        Pattern screenSizePattern = Pattern.compile("\\W(\\d{3,4}[x\\*]\\d{3,4})\\W*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = screenSizePattern.matcher(agentString);
        return matcher.find() ? matcher.group(1) : Constants.DEFAULT_VALUE;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getVendor() {
        return vendor;
    }

    public String getFamily() {
        return family;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public boolean isMobile() {
        return isMobile;
    }
}
