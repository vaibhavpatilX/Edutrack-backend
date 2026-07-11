package com.marvellous.MarvellousPortal.Repository;

import com.marvellous.MarvellousPortal.Entity.TimetableEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TimetableRepository extends MongoRepository<TimetableEntry, String> {
    List<TimetableEntry> findByBatchId(String batchId);
    List<TimetableEntry> findByBatchIdAndDay(String batchId, String day);
}