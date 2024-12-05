package com.experian.challenge.service;

import com.experian.challenge.model.dto.DeviceResponseDTO;
import com.experian.challenge.model.entity.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    DeviceResponseDTO createOrUpdateDeviceCount(String userAgent);
    Optional<Device> getDeviceById(String id);
    List<Device> getDevicesByOsName(String osName);
    void deleteDevicesByIds(List<String> ids);
}
