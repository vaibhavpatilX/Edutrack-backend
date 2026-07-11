package com.marvellous.MarvellousPortal.controller;

import com.marvellous.MarvellousPortal.Entity.FeePayment;
import com.marvellous.MarvellousPortal.service.FeePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fees")
public class FeePaymentController {

    @Autowired
    private FeePaymentService feePaymentService;

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<FeePayment>> getByBatch(@PathVariable String batchId) {
        return ResponseEntity.ok(feePaymentService.getByBatch(batchId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FeePayment>> getByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(feePaymentService.getByStudent(studentId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<FeePayment>> getPending() {
        return ResponseEntity.ok(feePaymentService.getPending());
    }

    @PostMapping
    public ResponseEntity<FeePayment> create(@RequestBody FeePayment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feePaymentService.save(payment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeePayment> update(@PathVariable String id, @RequestBody FeePayment payment) {
        FeePayment updated = feePaymentService.update(id, payment);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        feePaymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}