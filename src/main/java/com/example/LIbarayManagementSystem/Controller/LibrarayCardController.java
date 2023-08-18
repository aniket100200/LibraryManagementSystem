package com.example.LIbarayManagementSystem.Controller;

import com.example.LIbarayManagementSystem.Enums.CardStatus;
import com.example.LIbarayManagementSystem.Models.LibraryCard;
import com.example.LIbarayManagementSystem.Service.LibraryCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LibraryCard")
@Slf4j
public class LibrarayCardController
{
    @Autowired
    private LibraryCardService libraryCardService;

    @PostMapping("/create")
    public ResponseEntity<String> addCard(@RequestBody LibraryCard libraryCard)
    {
        String resp=libraryCardService.createLibraryCard(libraryCard);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping("/issueToStudent")
    public ResponseEntity<String> issueToStudent(@RequestParam Integer cardId,@RequestParam Integer rollNo){
        try{
            String ans= libraryCardService.issueToStudent(cardId,rollNo);
            return new ResponseEntity<>(ans,HttpStatus.OK);
        }
        catch (Exception e)
        {
           log.error("Unable to Accociate Student with Card"+e.getMessage());
           return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-Card-status-by-studentId")
    public ResponseEntity<CardStatus> getCardStatusByStudentId(@RequestParam("id") Integer studentId)
    {
        try{
            CardStatus cardStatus =libraryCardService.getCardStatusByStudentId(studentId);
            return new ResponseEntity<>(cardStatus,HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            log.error("Unable to Process Your Request {} "+e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }


}
