package com.marvellous.MarvellousPortal.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StudentDetails")
@Data
public class StudentEntry
{

    @Id
    private String id;

    private String batchId;       // links to BatchEntry id

    private String name;
    private String email;
    private String phone;
    private String enrollmentDate; // stored as "YYYY-MM-DD" string
    private String status;         // "Active" or "Inactive"
}