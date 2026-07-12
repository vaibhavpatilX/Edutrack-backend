package com.marvellous.MarvellousPortal.controller;

import com.marvellous.MarvellousPortal.Entity.NoticeEntry;
import com.marvellous.MarvellousPortal.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService service;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public ResponseEntity<List<NoticeEntry>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<NoticeEntry>> getForRole(@PathVariable String role) {
        return ResponseEntity.ok(service.getForRole(role));
    }

    // 🔥 We only have ONE PostMapping now!
    @PostMapping
    public ResponseEntity<NoticeEntry> create(@RequestBody NoticeEntry notice) {
        // 1. Save it to MongoDB using your service
        NoticeEntry savedNotice = service.save(notice);

        // 2. Broadcast the live STOMP message to React
        messagingTemplate.convertAndSend("/topic/notices", savedNotice);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeEntry> update(@PathVariable String id, @RequestBody NoticeEntry n) {
        NoticeEntry updated = service.update(id, n);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}