# userAgent

----------------------------
The library of user agent parser, using to parse information of browser,os,device and net version and so on. This version of parser contains much of special useragent of chinese feature.

# Examples

----------------------------
```
String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Safari/537.36";
UserAgentParser userAgentParser = new UserAgentParser();
UserAgent userAgent = userAgentParser.parse(userAgent);

//browser 
System.out.print(userAgent.browser.vendor);
System.out.print(userAgent.browser.family);
System.out.print(userAgent.browser.major);
System.out.print(userAgent.browser.minor);

//browser engine
System.out.print(userAgent.browser.engine.family);
System.out.print(userAgent.browser.engine.version);

//device
System.out.print(userAgent.device.vendor);
System.out.print(userAgent.device.family);
System.out.print(userAgent.device.deviceType);

//os
System.out.print(userAgent.os.vendor);
System.out.print(userAgent.os.family);
System.out.print(userAgent.os.major);
System.out.print(userAgent.os.minor);
System.out.print(userAgent.os.platform);

//net type
System.out.print(userAgent.netType);

//check mobile
System.out.print(userAgent.isMobile);

//check TV
System.out.print(userAgent.isTv);
```
# support

----------------
- Common Browsers
- Common Mobile Devices
- Net type 2G/3G/4G/5G/Wifi
- Device type PC/Phone/Pad/TV
- Equipment unique identification 
- Device platform ARM/X64/X86/


# License

----------------
[Apache License 2](http://www.apache.org/licenses/LICENSE-2.0.txt)