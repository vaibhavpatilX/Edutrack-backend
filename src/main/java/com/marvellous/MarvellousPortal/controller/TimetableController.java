package com.marvellous.MarvellousPortal.controller;

import com.marvellous.MarvellousPortal.Entity.TimetableEntry;
import com.marvellous.MarvellousPortal.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/timetable")
public class TimetableController {
    @Autowired private TimetableService service;

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<TimetableEntry>> getByBatch(@PathVariable String batchId) {
        return ResponseEntity.ok(service.getByBatch(batchId));
    }
    @PostMapping
    public ResponseEntity<TimetableEntry> create(@RequestBody TimetableEntry e) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(e));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TimetableEntry> update(@PathVariable String id, @RequestBody TimetableEntry e) {
        TimetableEntry updated = service.update(id, e);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id); return ResponseEntity.noContent().build();
    }
}