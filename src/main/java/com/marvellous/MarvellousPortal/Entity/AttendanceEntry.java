package com.marvellous.MarvellousPortal.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Attendance")
@Data
public class AttendanceEntry {
    @Id
    private String id;
    private String studentId;
    private String batchId;
    private String date;        // "YYYY-MM-DD"
    private String status;      // "Present" / "Absent" / "Leave"
    private String markedBy;    // "Admin" or "Teacher"
}