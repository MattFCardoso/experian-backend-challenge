package com.experian.challenge.model.mappers;

import com.experian.challenge.model.dto.DeviceResponseDTO;
import com.experian.challenge.model.entity.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceResponseDTO toResponseDTO(Device device);

}

