package com.example.springBootbackend.controller;

import com.example.springBootbackend.exception.ResourceNotFoundException;
import com.example.springBootbackend.model.Student;
import com.example.springBootbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    //get all students
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    //create student
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    //find by id
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student student= studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student not exit with id "+id));
        return ResponseEntity.ok(student);
    }

    //update student
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudentDetails(@PathVariable Long id,@RequestBody Student studentDetail){
        Student student= studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student not exit with id "+id));
        student.setFirstName(studentDetail.getFirstName());
        student.setLastName(studentDetail.getLastName());
        student.setAge(studentDetail.getAge());
        student.setCity(studentDetail.getCity());
        student.setHeight(studentDetail.getHeight());
        student.setWeight(studentDetail.getWeight());
        student.setHobbies(studentDetail.getHobbies());

        Student updatedStudent=studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteStudent(@PathVariable Long id){
        Student student= studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student not exit with id "+id));

        studentRepository.delete(student);
        Map<String,Boolean> response=new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}
