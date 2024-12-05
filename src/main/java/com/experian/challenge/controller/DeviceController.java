package com.experian.challenge.controller;

import com.experian.challenge.model.dto.DeviceResponseDTO;
import com.experian.challenge.model.entity.Device;
import com.experian.challenge.service.DeviceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceServiceImpl deviceService;

    public DeviceController(DeviceServiceImpl deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/match")
    public ResponseEntity<DeviceResponseDTO> matchDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");

        DeviceResponseDTO responseDTO = deviceService.createOrUpdateDeviceCount(userAgent);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable String id) {
        Optional<Device> device = deviceService.getDeviceById(id);
        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/os/{osName}")
    public ResponseEntity<List<Device>> getDevicesByOsName(@PathVariable String osName) {
        List<Device> devices = deviceService.getDevicesByOsName(osName);
        return ResponseEntity.ok(devices);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDevicesByIds(@RequestBody List<String> ids) {
        deviceService.deleteDevicesByIds(ids);
        return ResponseEntity.noContent().build();
    }
}