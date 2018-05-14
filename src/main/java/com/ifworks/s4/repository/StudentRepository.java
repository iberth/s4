package com.ifworks.s4.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ifworks.s4.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

	List<Student> findByFirstName(@Param("firstName") String firstName);
	
	List<Student> findByLastName(@Param("lastName") String lastName);

}
