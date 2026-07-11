package com.marvellous.MarvellousPortal.Repository;

import com.marvellous.MarvellousPortal.Entity.NoticeEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NoticeRepository extends MongoRepository<NoticeEntry, String> {
    List<NoticeEntry> findByActiveTrue();
    List<NoticeEntry> findByTargetRoleInAndActiveTrue(List<String> roles);
    List<NoticeEntry> findByTargetBatchIdAndActiveTrue(String batchId);
}