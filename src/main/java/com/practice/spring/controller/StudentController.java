package com.practice.spring.controller;

import com.practice.spring.dto.StudentDto;
import com.practice.spring.dto.StudentPartialUpdateDto;
import com.practice.spring.entity.UserdetailsBO;
import com.practice.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    //( DTO -> Entity ) => Student entity = mapper.map(StudentDto, Student.class);
    @PostMapping()
    public UserdetailsBO createStudent(@RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }

    //( Entity -> DTO ) => StudentDto dto = mapper.map(StudentEntity, StudentDto.class);
    @GetMapping()
    public List<StudentDto> getAllStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Integer id){
        return studentService.getStudentById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudentById(@RequestBody StudentDto studentDto,@PathVariable Integer id){
        String message = studentService.updateStudentById(studentDto,id);
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateStudentPartiallyById(@RequestBody StudentPartialUpdateDto studentPartialUpdateDto, @PathVariable Integer id) {
        String message = studentService.updateStudentPartiallyById(studentPartialUpdateDto, id);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
      String message= studentService.deleteStudentById(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/allStudents")
    public List<UserdetailsBO> saveStudents(@RequestBody List<StudentDto> studentDto){
        return studentService.saveStudents(studentDto);
    }
}
