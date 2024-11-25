package com.experian.challenge.model.entity;

import jakarta.persistence.Entity;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;


@Document(collection = "devices")
public class Device {
    @Id
    private String id;
    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;
    private int hitCount;

    public Device() {}

    public Device(String id, String osName, String osVersion, String browserName, String browserVersion, int hitCount) {
        this.id = id;
        this.osName = osName;
        this.osVersion = osVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.hitCount = hitCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}