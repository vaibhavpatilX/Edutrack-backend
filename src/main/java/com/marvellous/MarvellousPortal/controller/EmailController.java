package com.marvellous.MarvellousPortal.controller;

import com.marvellous.MarvellousPortal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired private EmailService emailService;

    @PostMapping("/fee-reminder")
    public ResponseEntity<?> feeReminder(@RequestBody Map<String, Object> body) {
        try {
            emailService.sendFeeReminder(
                    (String) body.get("to"),
                    (String) body.get("studentName"),
                    (String) body.get("batchName"),
                    (Integer) body.get("amountDue"),
                    (String) body.get("dueDate")
            );
            return ResponseEntity.ok(Map.of("message", "Fee reminder sent!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/attendance-alert")
    public ResponseEntity<?> attendanceAlert(@RequestBody Map<String, Object> body) {
        try {
            emailService.sendAttendanceAlert(
                    (String) body.get("to"),
                    (String) body.get("studentName"),
                    (String) body.get("batchName"),
                    (Integer) body.get("percentage")
            );
            return ResponseEntity.ok(Map.of("message", "Attendance alert sent!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/notice")
    public ResponseEntity<?> noticeEmail(@RequestBody Map<String, Object> body) {
        try {
            emailService.sendNoticeEmail(
                    (String) body.get("to"),
                    (String) body.get("recipientName"),
                    (String) body.get("noticeTitle"),
                    (String) body.get("noticeContent")
            );
            return ResponseEntity.ok(Map.of("message", "Notice email sent!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}