package com.marvellous.MarvellousPortal.controller;

import com.marvellous.MarvellousPortal.Entity.BatchEntry;
import com.marvellous.MarvellousPortal.service.BatchEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/batches")

public class BatchEntryController
{
    @Autowired
    private BatchEntryService batchEntryService;

    @GetMapping
    public ResponseEntity<?> getAll()
    {
        List<BatchEntry> allData = batchEntryService.getAll();

        if((allData != null) && !allData.isEmpty())
        {
            return new ResponseEntity<>(allData, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BatchEntry> createEntry(@RequestBody BatchEntry myentry)
    {
        try
        {
            batchEntryService.saveEntry(myentry);
            return new ResponseEntity<BatchEntry>(myentry,HttpStatus.CREATED);
        }
        catch (Exception eobj)
        {
            return new ResponseEntity<BatchEntry>(HttpStatus.BAD_REQUEST);
        }

    }

    //@GetMapping("/id/{myid}")//myid : object id from mongoDB
    //public ResponseEntity<BatchEntry> getBatchEntryById(@PathVariable ObjectId myid)

    @GetMapping("/id/{myid}")
    public ResponseEntity<BatchEntry> getBatchEntryById(@PathVariable String myid)
    // same for PUT and DELETE
    {

        Optional<BatchEntry> batchEntry = batchEntryService.findById(myid);
        if(batchEntry.isPresent())
        {
            return new ResponseEntity<BatchEntry>(batchEntry.get(),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<BatchEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String myid)
    {
        batchEntryService.deleteById(myid);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myid}")
    public ResponseEntity<?> updateEntryById(@PathVariable String myid, @RequestBody BatchEntry mynewentry)
    {
        BatchEntry isEntry = batchEntryService.findById(myid).orElse(null);

        if(isEntry != null)
        {
            isEntry.setName(mynewentry.getName());
            isEntry.setFees(mynewentry.getFees());
            batchEntryService.saveEntry(isEntry);
            return  new ResponseEntity<>(isEntry,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
