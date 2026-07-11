package com.marvellous.MarvellousPortal.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Timetable")
@Data
public class TimetableEntry {
    @Id
    private String id;
    private String batchId;
    private String day;        // "Monday","Tuesday"...
    private String startTime;  // "09:00"
    private String endTime;    // "10:30"
    private String subject;
    private String teacher;
    private String room;
}