package com.ipcamera.logging.repository;

import com.ipcamera.logging.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
