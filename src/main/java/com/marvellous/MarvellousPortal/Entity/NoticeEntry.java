package com.marvellous.MarvellousPortal.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Notices")
@Data
public class NoticeEntry {
    @Id
    private String id;
    private String title;
    private String content;
    private String targetRole;   // "ALL","STUDENT","PARENT","TEACHER"
    private String targetBatchId;// null = all batches
    private String priority;     // "LOW","NORMAL","HIGH","URGENT"
    private String postedBy;
    private String postedDate;   // "YYYY-MM-DD"
    private boolean active;
}