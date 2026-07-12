package com.marvellous.MarvellousPortal.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "UserAccounts")
@Data
public class UserAccount {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private String password; // BCrypt hashed

    private String role;     // ADMIN, TEACHER, STUDENT, PARENT

    private String studentId;  // for STUDENT/PARENT roles
    private String batchId;
    private String name;
    private boolean active = true;
}