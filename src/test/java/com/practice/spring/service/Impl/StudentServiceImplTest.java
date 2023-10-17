package com.practice.spring.service.Impl;
import com.practice.spring.dto.StudentDto;
import com.practice.spring.dto.StudentPartialUpdateDto;
import com.practice.spring.entity.UserdetailsBO;
import com.practice.spring.repository.UserRepository;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Mock
    private UserRepository studentRepository;

    @Mock
    private DozerBeanMapper mapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStudentById() {
        Integer studentId = 1;
        UserdetailsBO student = new UserdetailsBO();
        student.setId(1);
        StudentDto expectedDto = new StudentDto();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(mapper.map(student, StudentDto.class)).thenReturn(expectedDto);

        StudentDto actualDto = studentService.getStudentById(studentId);

        assertEquals(expectedDto, actualDto);
        verify(studentRepository, times(1)).findById(studentId);
        verify(mapper, times(1)).map(student, StudentDto.class);
    }

    @Test
    public void testGetStudentByIdNotFound() {
        when(studentRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.getStudentById(1));
        assertThrows(NoSuchElementException.class,()-> studentService.updateStudentById(new StudentDto(),1));
        assertThrows(NoSuchElementException.class,()-> studentService.updateStudentPartiallyById(new StudentPartialUpdateDto(),1));

        verify(studentRepository, times(3)).findById(1);
        verifyNoMoreInteractions(mapper);
    }
    @Test
    public void testSaveStudent() {
        UserdetailsBO student=new UserdetailsBO();
        student.setFirstName("dsregrge");
        StudentDto studentDto=new StudentDto();
        when(mapper.map(studentDto,UserdetailsBO.class)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);

        UserdetailsBO student1=studentService.saveStudent(studentDto);

        verify(mapper,times(1)).map(studentDto,UserdetailsBO.class);
        verify(studentRepository,times(1)).save(student);
        assertEquals(student1,student);
    }
    @Test
    public void testGetAllStudents() {
        UserdetailsBO student=new UserdetailsBO();
        UserdetailsBO student1=new UserdetailsBO();
        student1.setId(2);
        student.setId(1);
        StudentDto studentDto=new StudentDto();
        StudentDto studentDto1=new StudentDto();
        studentDto.setId(1);
        studentDto1.setId(2);
        List<UserdetailsBO> studentList= Arrays.asList(student,student1);
        when(studentRepository.findAll()).thenReturn(studentList);
        List<StudentDto> studentDtoList=Arrays.asList(studentDto,studentDto1);

        when(mapper.map(student,StudentDto.class)).thenReturn(studentDto);
        when(mapper.map(student1,StudentDto.class)).thenReturn(studentDto1);

        List<StudentDto> studentDtos=studentService.getAllStudents();

        assertEquals(studentDtoList,studentDtos);
        assertEquals(2,studentDtos.size());
        verify(studentRepository,times(1)).findAll();
        verify(mapper,times(1)).map(student, StudentDto.class);
        verify(mapper,times(1)).map(student1, StudentDto.class);
    }
    @Test
    public void testUpdateStudentById() {
        Integer id=1;
        StudentDto studentDto=new StudentDto();
        studentDto.setId(id);
        UserdetailsBO student=new UserdetailsBO();
        student.setId(id);
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);
        when(mapper.map(studentDto,UserdetailsBO.class)).thenReturn(student);

        String message=studentService.updateStudentById(studentDto,id);

        assertEquals(studentDto.getId(),student.getId());
       assertEquals("one student record updated successfully!",message);
       verify(studentRepository, times(1)).findById(id);
       verify(studentRepository,times(1)).save(student);
       verify(mapper,times(1)).map(studentDto,UserdetailsBO.class);
    }
    @Test
    public void testUpdateStudentPartiallyById() {
        Integer id=1;
        StudentPartialUpdateDto studentPartialUpdateDto=new StudentPartialUpdateDto();
        studentPartialUpdateDto.setId(1);
        UserdetailsBO student=new UserdetailsBO();
        student.setId(1);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);
        when(mapper.map(studentPartialUpdateDto,UserdetailsBO.class)).thenReturn(student);

        String message=studentService.updateStudentPartiallyById(studentPartialUpdateDto,id);

        assertEquals("one student record updated!",message);
        verify(studentRepository,times(1)).findById(id);
        verify(studentRepository,times(1)).save(student);
        verify(mapper,times(1)).map(studentPartialUpdateDto, UserdetailsBO.class);
    }
    @Test
    public void testDeleteById() {
        Integer id=1;
        UserdetailsBO student=new UserdetailsBO();
        student.setId(1);

        when(studentRepository.existsById(id)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(id);

        String message=studentService.deleteStudentById(id);

        assertEquals("one student record deleted successfully!",message);
        verify(studentRepository,times(1)).existsById(id);
        verify(studentRepository,times(1)).deleteById(id);
    }
}

