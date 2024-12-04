package com.experian.challenge.service;

import com.experian.challenge.model.dto.DeviceRequestDTO;
import com.experian.challenge.model.dto.DeviceResponseDTO;
import com.experian.challenge.model.entity.Device;
import com.experian.challenge.model.mappers.DeviceMapper;
import com.experian.challenge.repository.DeviceRepository;
import com.experian.challenge.util.UserAgentParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    @Override
    public DeviceResponseDTO createOrUpdateDeviceCount(DeviceRequestDTO deviceRequestDTO) {
        String userAgent = deviceRequestDTO.getUserAgent();
        String osName = UserAgentParser.getOsName(userAgent);
        String osVersion = UserAgentParser.getOsVersion(userAgent);
        String browserName = UserAgentParser.getBrowserName(userAgent);
        String browserVersion = UserAgentParser.getBrowserVersion(userAgent);

        List<Device> existingDevices = deviceRepository.findByOsNameAndOsVersionAndBrowserNameAndBrowserVersion(osName, osVersion, browserName, browserVersion);

        if (!existingDevices.isEmpty()) {
            Device device = existingDevices.get(0);
            device.setHitCount(device.getHitCount() + 1);
            return deviceMapper.toResponseDTO(deviceRepository.save(device));
        }

        Device newDevice = new Device(UUID.randomUUID().toString(), osName, osVersion, browserName, browserVersion, 1);
        return deviceMapper.toResponseDTO(deviceRepository.save(newDevice));
    }

    @Override
    public Optional<Device> getDeviceById(String id) {
        return deviceRepository.findById(id);
    }

    @Override
    public List<Device> getDevicesByOsName(String osName) {
        return deviceRepository.findByOsName(osName);
    }

    @Override
    public void deleteDevicesByIds(List<String> ids) {
        deviceRepository.deleteAllById(ids);
    }
}