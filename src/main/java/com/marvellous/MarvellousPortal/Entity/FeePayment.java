package com.marvellous.MarvellousPortal.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FeePayments")
@Data
public class FeePayment {
    @Id
    private String id;
    private String studentId;
    private String batchId;
    private int    amountPaid;
    private int    totalFees;
    private String paymentDate;   // "YYYY-MM-DD"
    private String mode;          // "Cash" / "UPI" / "Bank Transfer"
    private String status;        // "Paid" / "Partial" / "Pending"
    private String remarks;
}