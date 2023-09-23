package com.ipcamera.logging.repository;

import com.ipcamera.logging.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByServiceName(String serviceName);
}
