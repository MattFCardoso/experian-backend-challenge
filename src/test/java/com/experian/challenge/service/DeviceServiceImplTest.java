package com.experian.challenge.service;

import com.experian.challenge.model.entity.Device;
import com.experian.challenge.model.mappers.DeviceMapper;
import com.experian.challenge.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceMapper deviceMapper;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //todo: fix
//    @Test
//    void createOrUpdateDevice_NewDevice() {
//        DeviceRequestDTO requestDTO = new DeviceRequestDTO();
//        requestDTO.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
//
//        when(deviceRepository.findByOsNameAndOsVersionAndBrowserNameAndBrowserVersion(anyString(), anyString(), anyString(), anyString()))
//                .thenReturn(Collections.emptyList());
//
//        Device newDevice = new Device("1", "Windows", "10", "Chrome", "58.0.3029.110", 1);
//        when(deviceRepository.save(any(Device.class))).thenReturn(newDevice);
//
//        deviceService.createOrUpdateDeviceCount(requestDTO);
//
//        verify(deviceRepository, times(1)).save(any(Device.class));
//    }

    @Test
    void getDeviceById() {
        Device device = new Device("1", "Windows", "10", "Chrome", "58.0.3029.110", 1);
        when(deviceRepository.findById("1")).thenReturn(Optional.of(device));

        Optional<Device> result = deviceService.getDeviceById("1");

        assertEquals(device, result.orElse(null));
    }

    @Test
    void createOrUpdateDevice_InvalidUserAgent_ThrowException() {
        DeviceRequestDTO requestDTO = new DeviceRequestDTO();
        requestDTO.setUserAgent("Invalid User Agent");

        when(deviceRepository.findByOsNameAndOsVersionAndBrowserNameAndBrowserVersion(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.emptyList());

        assertThrows(IllegalArgumentException.class, () -> {
            deviceService.createOrUpdateDeviceCount(requestDTO);
        });
    }

    @Test
    void getDeviceById_NotFound() {
        when(deviceRepository.findById("invalid-id")).thenReturn(Optional.empty());

        Optional<Device> result = deviceService.getDeviceById("invalid-id");

        assertFalse(result.isPresent());
    }

    @Test
    void getDevicesByOsName_NotFound() {
        when(deviceRepository.findByOsName("NonExistentOS")).thenReturn(Collections.emptyList());

        assertTrue(deviceService.getDevicesByOsName("NonExistentOS").isEmpty());
    }

    @Test
    void deleteDevicesByIds_InvalidIds_ThrowException() {
        doThrow(new IllegalArgumentException("Invalid ID")).when(deviceRepository).deleteAllById(anyList());

        assertThrows(IllegalArgumentException.class, () -> {
            deviceService.deleteDevicesByIds(Collections.singletonList("invalid-id"));
        });
    }
}