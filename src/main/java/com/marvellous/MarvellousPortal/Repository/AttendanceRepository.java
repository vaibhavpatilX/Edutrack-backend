package com.marvellous.MarvellousPortal.Repository;

import com.marvellous.MarvellousPortal.Entity.AttendanceEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AttendanceRepository extends MongoRepository<AttendanceEntry, String> {
    List<AttendanceEntry> findByBatchId(String batchId);
    List<AttendanceEntry> findByStudentId(String studentId);
    List<AttendanceEntry> findByBatchIdAndDate(String batchId, String date);
    List<AttendanceEntry> findByStudentIdAndBatchId(String studentId, String batchId);
}