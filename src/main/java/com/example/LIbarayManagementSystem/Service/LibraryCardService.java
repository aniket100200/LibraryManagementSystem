package com.example.LIbarayManagementSystem.Service;

import com.example.LIbarayManagementSystem.Enums.CardStatus;
import com.example.LIbarayManagementSystem.Models.LibraryCard;
import com.example.LIbarayManagementSystem.Models.Student;
import com.example.LIbarayManagementSystem.Repository.LibraryCardRepository;
import com.example.LIbarayManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryCardService
{
    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private StudentRepository studentRepository;
    public String createLibraryCard(LibraryCard l)
    {
        libraryCardRepository.save(l);
        return "Card has been added to the the database successfully";
    }

    public String issueToStudent(Integer cardId,Integer rollNo) throws  Exception{

        if(!studentRepository.existsById(rollNo))throw new Exception("Student with id"+rollNo+" is Not Found");
        if(!libraryCardRepository.existsById(cardId))throw new Exception("Card Does Not exists");

        Optional<Student>optionalStudent=studentRepository.findById(rollNo);
        Student student=optionalStudent.get();

        Optional<LibraryCard> optionalLibraryCard=libraryCardRepository.findById(cardId);
        LibraryCard libraryCard=optionalLibraryCard.get();

        //I need to set  the foreign key variable..
        libraryCard.setStudent(student);


        //... It is bidirctional mapping..
        student.setLibraryCard(libraryCard);

        studentRepository.save(student);
        return "Student and Card Associated Successfully";
    }

    public CardStatus getCardStatusByStudentId(Integer studentId)throws Exception
    {
        if(!studentRepository.existsById(studentId))throw new Exception("Student with studentId Not Fount");
        Optional<Student>optionalStudent=studentRepository.findById(studentId);
        Student student=optionalStudent.get();
        //it has the Object reffering to the Card..
        LibraryCard card=student.getLibraryCard();
        return card.getCardStatus();
    }
}
