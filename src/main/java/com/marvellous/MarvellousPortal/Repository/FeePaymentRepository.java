package com.marvellous.MarvellousPortal.Repository;

import com.marvellous.MarvellousPortal.Entity.FeePayment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface FeePaymentRepository extends MongoRepository<FeePayment, String> {
    List<FeePayment> findByBatchId(String batchId);
    List<FeePayment> findByStudentId(String studentId);
    List<FeePayment> findByStatus(String status);
}