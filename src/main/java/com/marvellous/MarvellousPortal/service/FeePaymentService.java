package com.marvellous.MarvellousPortal.service;

import com.marvellous.MarvellousPortal.Entity.FeePayment;
import com.marvellous.MarvellousPortal.Repository.FeePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeePaymentService {

    @Autowired
    private FeePaymentRepository feePaymentRepository;

    public List<FeePayment> getByBatch(String batchId) {
        return feePaymentRepository.findByBatchId(batchId);
    }

    public List<FeePayment> getByStudent(String studentId) {
        return feePaymentRepository.findByStudentId(studentId);
    }

    public List<FeePayment> getPending() {
        return feePaymentRepository.findByStatus("Pending");
    }

    public FeePayment save(FeePayment payment) {
        return feePaymentRepository.save(payment);
    }

    public FeePayment update(String id, FeePayment updated) {
        FeePayment existing = feePaymentRepository.findById(id).orElse(null);
        if (existing == null) return null;
        existing.setAmountPaid(updated.getAmountPaid());
        existing.setPaymentDate(updated.getPaymentDate());
        existing.setMode(updated.getMode());
        existing.setStatus(updated.getStatus());
        existing.setRemarks(updated.getRemarks());
        return feePaymentRepository.save(existing);
    }

    public boolean delete(String id) {
        if (!feePaymentRepository.existsById(id)) return false;
        feePaymentRepository.deleteById(id);
        return true;
    }
}