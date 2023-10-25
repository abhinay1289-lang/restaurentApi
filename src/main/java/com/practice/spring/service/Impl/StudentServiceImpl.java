package com.practice.spring.service.Impl;

import com.practice.spring.dto.StudentDto;
import com.practice.spring.dto.StudentPartialUpdateDto;
import com.practice.spring.dto.UserCredentialsDto;
import com.practice.spring.entity.UserdetailsBO;
import com.practice.spring.exception.InvalidCredentialsException;
import com.practice.spring.repository.UserCredentialRepository;
import com.practice.spring.repository.UserRepository;
import com.practice.spring.service.StudentService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private UserRepository studentRepository;

    @Autowired
    UserCredentialRepository userCredentialsRepository;

    @Autowired
    private DozerBeanMapper mapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public List<StudentDto> getAllStudents() {
        Iterable<UserdetailsBO> itr = studentRepository.findAll();
        return StreamSupport.stream(itr.spliterator(), false).map(entity -> mapper.map(entity, StudentDto.class)).toList();
    }

    @Override
    public UserdetailsBO validateUserCredentials(UserCredentialsDto loginRequest) {
        Supplier<InvalidCredentialsException> expSupplier =
                () ->
                        new InvalidCredentialsException(
                                String.format("The requested user %s not found", loginRequest.getUsername()));
        return userCredentialsRepository
                .findByEmailId(loginRequest.getUsername())
                .orElseThrow(expSupplier);
    }

    @Override
    public StudentDto getStudentById(Integer id) {
        UserdetailsBO student=studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("student not found at id:"+id)));
        return  mapper.map(student, StudentDto.class);
    }
    @Override
    public UserdetailsBO saveStudent(StudentDto studentDto){
        UserdetailsBO student = mapper.map(studentDto, UserdetailsBO.class);
        return studentRepository.save(student);
    }
    @Override
    public List<UserdetailsBO> saveStudents(List<StudentDto> studentDto) {
        Iterable<UserdetailsBO> itr=studentRepository.saveAll(studentDto.stream().map(studentDto1 -> mapper.map(studentDto1, UserdetailsBO.class)).collect(Collectors.toList()));
        return StreamSupport.stream(itr.spliterator() , false).collect(Collectors.toList());
    }
    @Override
    public String updateStudentById(StudentDto studentDto, Integer id) {
        UserdetailsBO student= studentRepository.findById(id)
                                          .orElseThrow(() -> new NoSuchElementException(String.format("student not found at id:",id)));
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmailId(studentDto.getEmailId());

        studentRepository.save(student);
        mapper.map(studentDto, UserdetailsBO.class);
        return "one student record updated successfully!";
    }
    @Override
    public String updateStudentPartiallyById(StudentPartialUpdateDto studentPartialUpdateDto, Integer id) {
        UserdetailsBO student = studentRepository.findById(id)
                                         .orElseThrow(() -> new NoSuchElementException("Student not found at id:"+id));
        student.setEmailId(studentPartialUpdateDto.getEmailId());

       studentRepository.save(student);
       mapper.map(studentPartialUpdateDto, UserdetailsBO.class);
       return "one student record updated!";
    }
    @Override
    public String deleteStudentById(Integer id){
        if(studentRepository.existsById(id))
        {
            studentRepository.deleteById(id);
            return  "one student record deleted successfully!";
        }
        else {
            return "student record is not found";
        }
    }
}
