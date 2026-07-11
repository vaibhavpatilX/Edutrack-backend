package com.marvellous.MarvellousPortal.service;

import com.marvellous.MarvellousPortal.Entity.BatchEntry;
import com.marvellous.MarvellousPortal.Repository.BatchEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BatchEntryService
{
    @Autowired
    private BatchEntryRepository batchEntryRepository;

    //C : CREATE
    //POST
    public void saveEntry(BatchEntry batchEntry)
    {
        batchEntryRepository.save(batchEntry);
    }

    //R : READ
    //GET
    public List<BatchEntry> getAll()
    {
        return batchEntryRepository.findAll();
    }

    //R : READ
    //GET
    public Optional<BatchEntry> findById(String id)
    {
        return batchEntryRepository.findById(id);
    }
    public void deleteById(String id)
    {
        batchEntryRepository.deleteById(id);
    }


}
