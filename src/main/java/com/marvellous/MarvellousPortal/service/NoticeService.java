package com.marvellous.MarvellousPortal.service;

import com.marvellous.MarvellousPortal.Entity.NoticeEntry;
import com.marvellous.MarvellousPortal.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;

@Service
public class NoticeService {
    @Autowired private NoticeRepository repo;

    public List<NoticeEntry> getAll() { return repo.findByActiveTrue(); }
    public List<NoticeEntry> getForRole(String role) {
        return repo.findByTargetRoleInAndActiveTrue(Arrays.asList("ALL", role));
    }
    public NoticeEntry save(NoticeEntry n) { n.setActive(true); return repo.save(n); }
    public NoticeEntry update(String id, NoticeEntry updated) {
        NoticeEntry ex = repo.findById(id).orElse(null);
        if (ex == null) return null;
        ex.setTitle(updated.getTitle()); ex.setContent(updated.getContent());
        ex.setPriority(updated.getPriority()); ex.setTargetRole(updated.getTargetRole());
        ex.setTargetBatchId(updated.getTargetBatchId());
        return repo.save(ex);
    }
    public void delete(String id) {
        NoticeEntry ex = repo.findById(id).orElse(null);
        if (ex != null) { ex.setActive(false); repo.save(ex); }
    }
}