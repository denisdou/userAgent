package com.github.denisdou.useragent.browser;

import com.github.denisdou.useragent.Constants;

/**
 * @author doujiajun
 * @date 10:47 2021/1/28
 * @email 402550833@qq.com
 */
public class Engine {
    public static final Engine DEFAULT_ENGINE = new Engine(Constants.DEFAULT_VALUE, Constants.DEFAULT_VALUE);
    private final String family;
    private final String version;

    Engine(String family, String version) {
        this.family = family;
        this.version = version;
    }

    public String getName() {
        return family;
    }

    public String getVersion() {
        return version;
    }
}