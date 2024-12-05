package com.experian.challenge.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {

    @Id
    private String id;
    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;
    private int hitCount;
}