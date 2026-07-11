package com.marvellous.MarvellousPortal.controller;

import com.marvellous.MarvellousPortal.Entity.AttendanceEntry;
import com.marvellous.MarvellousPortal.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<AttendanceEntry>> getByBatch(@PathVariable String batchId) {
        return ResponseEntity.ok(attendanceService.getByBatch(batchId));
    }

    @GetMapping("/batch/{batchId}/date/{date}")
    public ResponseEntity<List<AttendanceEntry>> getByBatchAndDate(
            @PathVariable String batchId, @PathVariable String date) {
        return ResponseEntity.ok(attendanceService.getByBatchAndDate(batchId, date));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceEntry>> getByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(attendanceService.getByStudent(studentId));
    }

    @GetMapping("/student/{studentId}/batch/{batchId}")
    public ResponseEntity<List<AttendanceEntry>> getByStudentAndBatch(
            @PathVariable String studentId, @PathVariable String batchId) {
        return ResponseEntity.ok(attendanceService.getByStudentAndBatch(studentId, batchId));
    }

    // Mark attendance for multiple students at once
    @PostMapping("/bulk")
    public ResponseEntity<List<AttendanceEntry>> markBulk(@RequestBody List<AttendanceEntry> entries) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.saveAll(entries));
    }

    @PostMapping
    public ResponseEntity<AttendanceEntry> mark(@RequestBody AttendanceEntry entry) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.save(entry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        attendanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}