package com.experian.challenge.util;

import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.Browser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentParser {

    public static String getOsName(String userAgentString) {
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        OperatingSystem os = userAgent.getOperatingSystem();
        return os.getName();
    }

    public static String getOsVersion(String userAgentString) {
        String osName = getOsName(userAgentString);
        Pattern pattern;
        Matcher matcher;

        switch (osName) {
            case "Windows":
                pattern = Pattern.compile("Windows NT ([\\d.]+)");
                matcher = pattern.matcher(userAgentString);
                if (matcher.find()) {
                    return matcher.group(1);
                }
                break;
            case "Mac OS X":
                pattern = Pattern.compile("Mac OS X ([\\d_]+)");
                matcher = pattern.matcher(userAgentString);
                if (matcher.find()) {
                    return matcher.group(1).replace('_', '.');
                }
                break;
            case "Android":
                pattern = Pattern.compile("Android ([\\d.]+)");
                matcher = pattern.matcher(userAgentString);
                if (matcher.find()) {
                    return matcher.group(1);
                }
                break;
            case "iOS":
                pattern = Pattern.compile("OS ([\\d_]+) like Mac OS X");
                matcher = pattern.matcher(userAgentString);
                if (matcher.find()) {
                    return matcher.group(1).replace('_', '.');
                }
                break;
            default:
                return "Unknown";
        }
        return "Unknown";
    }

    public static String getBrowserName(String userAgentString) {
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

    public static String getBrowserVersion(String userAgentString) {
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        return userAgent.getBrowserVersion().getVersion();
    }
}