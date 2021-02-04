package com.github.denisdou.useragent.device;

/***********************************
 * device type: pc,tv,phone,pad,spider,-
 **********************************/

import com.github.denisdou.useragent.Constants;

/**
 * @author doujiajun 402550833@qq.com
 */
public class Device {
    public static final Device DEFAULT_PC = new Device(Constants.DEFAULT_VALUE, Constants.DEFAULT_VALUE, Constants.PC, false, Constants.DEFAULT_VALUE, Constants.DEFAULT_VALUE);
    public static final Device DEFAULT_PHONE = new Device(Constants.DEFAULT_VALUE, Constants.DEFAULT_VALUE, Constants.PHONE, true, Constants.DEFAULT_VALUE, Constants.DEFAULT_VALUE);
    public static final Device DEFAULT_TV = new Device(Constants.DEFAULT_VALUE, Constants.DEFAULT_VALUE, Constants.TV, false, Constants.DEFAULT_VALUE, Constants.DEFAULT_VALUE);

    private final String vendor;
    private final String family;
    private final String deviceType;
    private final boolean isMobile;
    private final String screenSize;
    private final String uuid;

    public Device(String vendor, String family, String deviceType, boolean isMobile, String uuid) {
        this.vendor = vendor;
        this.family = family;
        this.deviceType = deviceType;
        this.isMobile = isMobile;
        this.uuid = uuid;
        this.screenSize = Constants.DEFAULT_VALUE;
    }

    public Device(String vendor, String family, String deviceType, boolean isMobile, String screenSize, String uuid) {
        this.vendor = vendor;
        this.family = family;
        this.deviceType = deviceType;
        this.isMobile = isMobile;
        this.screenSize = screenSize;
        this.uuid = uuid;
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

    public String getScreenSize() {
        return screenSize;
    }

    public String getUuid() {
        return uuid;
    }
}
