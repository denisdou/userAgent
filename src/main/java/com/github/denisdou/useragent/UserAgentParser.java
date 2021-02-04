package com.github.denisdou.useragent;

import com.github.denisdou.useragent.browser.Browser;
import com.github.denisdou.useragent.browser.BrowserParser;
import com.github.denisdou.useragent.device.Device;
import com.github.denisdou.useragent.device.DeviceDictionary;
import com.github.denisdou.useragent.device.DeviceParser;
import com.github.denisdou.useragent.os.Os;
import com.github.denisdou.useragent.os.OsParser;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author doujiajun 402550833@qq.com
 */
public class UserAgentParser {
    public OsParser osParser;
    private BrowserParser browserParser;
    private DeviceParser deviceParser;
    private DeviceDictionary deviceDictionary;
    private Yaml yaml;

    private final static Pattern netTypePattern = Pattern.compile("\\W(WIFI|5G|4G|3G|2G)\\W*", Pattern.CASE_INSENSITIVE);

    UserAgentParser() {
        loadConfig();
    }

    public void loadConfig() {
        this.yaml = new Yaml(new SafeConstructor());
        osParser = OsParser.addList(readConfig("/os.yml"));
        browserParser = BrowserParser.addList(readConfig("/browser.yml"));
        deviceParser = DeviceParser.addList(readConfig("/device.yml"));
        deviceDictionary = DeviceDictionary.addMap(readConfig("/DeviceDictionary.yml"));
    }

    public List<Map<String, String>> readConfig(String file) {
        try (InputStream stream = UserAgentParser.class.getResourceAsStream(file)) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> configs = (List<Map<String, String>>) this.yaml.load(stream);
            if (configs == null) {
                throw new IllegalArgumentException("config loading failed.");
            }
            return configs;
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public UserAgent parse(String agentString) {
        if (StringUtils.isBlank(agentString)) {
            return null;
        }

        Os os = osParser.parse(agentString);
        Device device = deviceParser.parse(agentString);
        Browser browser = browserParser.parse(agentString);
        String netType = parseNetType(agentString);
        return new UserAgent(os, device, browser, device.isMobile(), os.isTv(), netType);
    }

    private String parseNetType(String agentString) {
        Matcher matcher = netTypePattern.matcher(agentString.toUpperCase());
        String result = "";
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return StringUtils.isBlank(result) ? Constants.DEFAULT_VALUE : result;
    }
}
