package com.example.LIbarayManagementSystem.Service;

import com.example.LIbarayManagementSystem.Enums.Department;
import com.example.LIbarayManagementSystem.Models.Student;
import com.example.LIbarayManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent (Student student) throws Exception
    {
        if(student.getRollNO()!=null)
        {
            throw new Exception("Student you can't provde the roll NO");
        }
        studentRepository.save(student);
        return "Student has been added to the database successfully";
    }

    public Department getDeptById(Integer id) throws  Exception{
        Optional<Student>optionalStudent=studentRepository.findById(id);
        if(optionalStudent.isPresent()==false)throw new Exception("Student with Id is not found");

        Student student=optionalStudent.get();
        return student.getDepartment();
    }
}
