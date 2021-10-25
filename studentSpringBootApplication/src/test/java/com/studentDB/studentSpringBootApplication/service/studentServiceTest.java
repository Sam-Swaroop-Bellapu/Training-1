package com.studentDB.studentSpringBootApplication.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class studentServiceTest {

    @InjectMocks
    private studentService studentService;



    @Test
    void getStudentsByFirstYear() {
        assertEquals(3,studentService.getStudentsByFirstYear().size());
    }

    @Test
    void getStudentsByName() {
        assertEquals("abc",studentService.getStudentsByRno("roll1"));
    }
}