package com.example.LIbarayManagementSystem.Controller;

import com.example.LIbarayManagementSystem.Enums.Department;
import com.example.LIbarayManagementSystem.Models.Student;
import com.example.LIbarayManagementSystem.Service.StudentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController
{
    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<String> addStudent (@RequestBody Student student)
    {
        try
        {
            String resp =studentService.addStudent(student);
            return new ResponseEntity<>(resp,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            log.error("Student not add (){}"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/get-dept-by-id")
    public ResponseEntity<Department> getDepByID(@RequestParam("id") Integer id)
    {
        try
        {
            Department department=studentService.getDeptById(id);
            return new ResponseEntity<>(department,HttpStatus.OK);
        }
        catch(Exception e)
        {
            log.error("Not getting the Department{}"+e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }
}
