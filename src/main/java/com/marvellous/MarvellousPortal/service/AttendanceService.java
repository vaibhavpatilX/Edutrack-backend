package com.marvellous.MarvellousPortal.service;

import com.marvellous.MarvellousPortal.Entity.AttendanceEntry;
import com.marvellous.MarvellousPortal.Repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<AttendanceEntry> getByBatch(String batchId) {
        return attendanceRepository.findByBatchId(batchId);
    }

    public List<AttendanceEntry> getByBatchAndDate(String batchId, String date) {
        return attendanceRepository.findByBatchIdAndDate(batchId, date);
    }

    public List<AttendanceEntry> getByStudent(String studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<AttendanceEntry> getByStudentAndBatch(String studentId, String batchId) {
        return attendanceRepository.findByStudentIdAndBatchId(studentId, batchId);
    }

    public AttendanceEntry save(AttendanceEntry entry) {
        return attendanceRepository.save(entry);
    }

    public List<AttendanceEntry> saveAll(List<AttendanceEntry> entries) {
        return attendanceRepository.saveAll(entries);
    }

    public void delete(String id) {
        attendanceRepository.deleteById(id);
    }
}