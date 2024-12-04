package com.experian.challenge.repository;

import com.experian.challenge.model.entity.Device;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends AerospikeRepository<Device, String> {
    List<Device> findByOsNameAndOsVersionAndBrowserNameAndBrowserVersion(String osName, String osVersion, String browserName, String browserVersion);
    List<Device> findByOsName(String osName);
}