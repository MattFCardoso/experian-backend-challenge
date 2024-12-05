package com.experian.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceResponseDTO {
    private String deviceId;
    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;
    private int hitCount;
}

