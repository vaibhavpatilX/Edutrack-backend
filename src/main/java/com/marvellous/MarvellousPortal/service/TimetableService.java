package com.marvellous.MarvellousPortal.service;

import com.marvellous.MarvellousPortal.Entity.TimetableEntry;
import com.marvellous.MarvellousPortal.Repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimetableService {
    @Autowired private TimetableRepository repo;

    public List<TimetableEntry> getByBatch(String batchId) { return repo.findByBatchId(batchId); }
    public TimetableEntry save(TimetableEntry e) { return repo.save(e); }
    public TimetableEntry update(String id, TimetableEntry updated) {
        TimetableEntry ex = repo.findById(id).orElse(null);
        if (ex == null) return null;
        ex.setDay(updated.getDay()); ex.setStartTime(updated.getStartTime());
        ex.setEndTime(updated.getEndTime()); ex.setSubject(updated.getSubject());
        ex.setTeacher(updated.getTeacher()); ex.setRoom(updated.getRoom());
        return repo.save(ex);
    }
    public void delete(String id) { repo.deleteById(id); }
}