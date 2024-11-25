package com.experian.challenge.model.dto;

import lombok.Data;

public class DeviceRequestDTO {
    private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}