package com.firstfewlines.service;

import com.firstfewlines.domain.Student;
import com.firstfewlines.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public Iterable<Student> getStudents(){
        return studentRepository.findAll();
    }
}
