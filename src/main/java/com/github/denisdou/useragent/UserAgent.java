package com.github.denisdou.useragent;

import com.github.denisdou.useragent.browser.Browser;
import com.github.denisdou.useragent.device.Device;
import com.github.denisdou.useragent.os.Os;

/**
 * @author doujiajun 402550833@qq.com
 */
public class UserAgent {
    private final Os os;
    private final Browser browser;
    private final Device device;
    private final boolean isMobile;
    private final boolean isTv;
    private final String netType;

    UserAgent(Os os, Device device, Browser browser, boolean isMobile, boolean isTv, String netType) {
        this.os = os;
        this.device = device;
        this.browser = browser;
        this.isMobile = isMobile;
        this.isTv = isTv;
        this.netType = netType;
    }

    public Os getOs() {
        return os;
    }

    public Browser getBrowser() {
        return browser;
    }

    public Device getDevice() {
        return device;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public boolean isTv() {
        return isTv;
    }

    public String getNetType() {
        return netType;
    }
}
