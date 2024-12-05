package com.experian.challenge.controller;

import com.experian.challenge.model.dto.DeviceResponseDTO;
import com.experian.challenge.model.entity.Device;
import com.experian.challenge.service.DeviceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeviceControllerTest {

    @Mock
    private DeviceServiceImpl deviceService;

    @InjectMocks
    private DeviceController deviceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    void createOrUpdateDevice() throws Exception {
        DeviceRequestDTO requestDTO = new DeviceRequestDTO();
        requestDTO.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");

        DeviceResponseDTO responseDTO = new DeviceResponseDTO();
        responseDTO.setDeviceId("1");
        responseDTO.setOsName("Windows");
        responseDTO.setOsVersion("10");
        responseDTO.setBrowserName("Chrome");
        responseDTO.setBrowserVersion("58.0.3029.110");
        responseDTO.setHitCount(1);

        when(deviceService.createOrUpdateDeviceCount(any(DeviceRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/devices/match")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userAgent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3\"}"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.osName").value("Windows"))
                .andExpect(jsonPath("$.osVersion").value("10"))
                .andExpect(jsonPath("$.browserName").value("Chrome"))
                .andExpect(jsonPath("$.browserVersion").value("58.0.3029.110"))
                .andExpect(jsonPath("$.hitCount").value(1));
    }

    @Test
    void getDeviceById() throws Exception {
        Device device = new Device("1", "Windows", "10", "Chrome", "58.0.3029.110", 1);

        when(deviceService.getDeviceById("1")).thenReturn(Optional.of(device));

        mockMvc.perform(get("/api/devices/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.osName").value("Windows"))
                .andExpect(jsonPath("$.osVersion").value("10"))
                .andExpect(jsonPath("$.browserName").value("Chrome"))
                .andExpect(jsonPath("$.browserVersion").value("58.0.3029.110"))
                .andExpect(jsonPath("$.hitCount").value(1));
    }

    @Test
    void getDeviceById_NotFound() throws Exception {
        when(deviceService.getDeviceById("invalid-id")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/devices/invalid-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getDevicesByOsName() throws Exception {
        Device device = new Device("1", "Windows", "10", "Chrome", "58.0.3029.110", 1);

        when(deviceService.getDevicesByOsName("Windows")).thenReturn(Collections.singletonList(device));

        mockMvc.perform(get("/api/devices/os/Windows"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].osName").value("Windows"))
                .andExpect(jsonPath("$[0].osVersion").value("10"))
                .andExpect(jsonPath("$[0].browserName").value("Chrome"))
                .andExpect(jsonPath("$[0].browserVersion").value("58.0.3029.110"))
                .andExpect(jsonPath("$[0].hitCount").value(1));
    }

    @Test
    void deleteDevicesByIds() throws Exception {
        mockMvc.perform(delete("/api/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"1\"]"))
                .andExpect(status().isNoContent());
    }
}