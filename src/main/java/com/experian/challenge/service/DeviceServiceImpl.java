package com.experian.challenge.service;

import com.experian.challenge.model.dto.DeviceResponseDTO;
import com.experian.challenge.model.entity.Device;
import com.experian.challenge.model.mappers.DeviceMapper;
import com.experian.challenge.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ua_parser.Client;
import ua_parser.Parser;

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
    public DeviceResponseDTO createOrUpdateDeviceCount(String userAgent) {
        Parser uaParser = new Parser();
        Client client = uaParser.parse(userAgent);


        String osVersionMajor = ObjectUtils.isEmpty(client.os.major) ? "0." : client.os.major + ".";
        String osVersionMinor = ObjectUtils.isEmpty(client.os.minor) ? "0." : client.os.minor + ".";
        String osVersionPatch = ObjectUtils.isEmpty(client.os.patch) ? "0" : client.os.patch;

        String osName = client.os.family;
        String osVersion = osVersionMajor + osVersionMinor + osVersionPatch;
        String browserName = client.userAgent.family;
        String browserVersion = client.userAgent.major + "." + client.userAgent.minor + "." + client.userAgent.patch;

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