package com.marvellous.MarvellousPortal.Repository;

import com.marvellous.MarvellousPortal.Entity.StudentEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface StudentRepository extends MongoRepository<StudentEntry, String>
{

    List<StudentEntry> findByBatchId(String batchId);

    void deleteByBatchId(String batchId);
}