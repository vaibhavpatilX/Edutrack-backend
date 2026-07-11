package com.marvellous.MarvellousPortal.service;

import com.marvellous.MarvellousPortal.Entity.StudentEntry;
import com.marvellous.MarvellousPortal.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{

    @Autowired
    private StudentRepository studentRepository;

    public StudentEntry saveStudent(StudentEntry student)
    {
        return studentRepository.save(student);
    }

    public List<StudentEntry> getStudentsByBatch(String batchId)
    {
        return studentRepository.findByBatchId(batchId);
    }

    public Optional<StudentEntry> findById(String id)
    {
        return studentRepository.findById(id);
    }

    public StudentEntry updateStudent(String id, StudentEntry updated)
    {
        StudentEntry existing = studentRepository.findById(id).orElse(null);
        if (existing == null) return null;
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setEnrollmentDate(updated.getEnrollmentDate());
        existing.setStatus(updated.getStatus());
        return studentRepository.save(existing);
    }

    public boolean deleteStudent(String id)
    {
        if (!studentRepository.existsById(id)) return false;
        studentRepository.deleteById(id);
        return true;
    }

    public void deleteStudentsByBatch(String batchId)
    {
        studentRepository.deleteByBatchId(batchId);
    }
}