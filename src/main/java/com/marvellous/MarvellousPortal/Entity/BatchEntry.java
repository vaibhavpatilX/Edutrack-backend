package com.marvellous.MarvellousPortal.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BatchDetails")
@Data
public class BatchEntry
{

    @Id
    private String id;      // Changed from ObjectId → String. Clean JSON now.
    private String name;
    private int fees;
}