package com.ifworks.s4.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifworks.s4.exception.S4Exception;
import com.ifworks.s4.model.Class;
import com.ifworks.s4.model.Student;
import com.ifworks.s4.repository.ClassRepository;
import com.ifworks.s4.repository.StudentRepository;

@Component
public class StudentService {
	@Autowired
	private StudentRepository repository;

	@Autowired
	private ClassRepository classRepository;

	public Student subscribeStudent(Long id, Class classToSubscribe) throws S4Exception {
		Student student = repository.findById(id).get();

		Class classFromDB = null;

		try {
			classFromDB = classRepository.findById(classToSubscribe.getCode()).get();
		} catch (NoSuchElementException e) {
			throw new S4Exception("Class to subscribe not found.", e);
		}

		student.getClasses().add(classFromDB);

		repository.save(student);

		return student;
	}
}
