package com.marvellous.MarvellousPortal.controller;

import com.marvellous.MarvellousPortal.Entity.StudentEntry;
import com.marvellous.MarvellousPortal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController
{

    @Autowired
    private StudentService studentService;

    // GET all students in a batch
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<StudentEntry>> getStudentsByBatch(@PathVariable String batchId)
    {
        List<StudentEntry> students = studentService.getStudentsByBatch(batchId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // POST add new student
    @PostMapping
    public ResponseEntity<StudentEntry> addStudent(@RequestBody StudentEntry student)
    {
        StudentEntry saved = studentService.saveStudent(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // PUT update student
    @PutMapping("/id/{id}")
    public ResponseEntity<StudentEntry> updateStudent(@PathVariable String id, @RequestBody StudentEntry student)
    {
        StudentEntry updated = studentService.updateStudent(id, student);
        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE student
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id)
    {
        boolean deleted = studentService.deleteStudent(id);
        if (!deleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}