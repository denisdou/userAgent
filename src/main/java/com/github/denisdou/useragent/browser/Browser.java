package com.github.denisdou.useragent.browser;

import com.github.denisdou.useragent.Constants;

/**
 * @author doujiajun
 * @date 10:47 2021/1/28
 * @email 402550833@qq.com
 */
public class Browser {
    public static final Browser DEFAULT_BROWSER = new Browser(Constants.DEFAULT_VALUE, Constants.DEFAULT_VALUE, null, null, Engine.DEFAULT_ENGINE);
    private final String vendor;
    private final String family;
    private final String major;
    private final String minor;
    private final Engine engine;

    Browser(String vendor, String family, String major, String minor, Engine engine) {
        this.vendor = vendor;
        this.family = family;
        this.major = major;
        this.minor = minor;
        this.engine = engine;
    }

    public String getVendor() {
        return vendor;
    }

    public String getFamily() {
        return family;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    public Engine getEngine() {
        return engine;
    }
}
