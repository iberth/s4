package com.ifworks.s4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifworks.s4.exception.S4Exception;
import com.ifworks.s4.model.Class;
import com.ifworks.s4.model.Student;
import com.ifworks.s4.service.StudentService;

@RepositoryRestController
public class StudentController {
	@Autowired
	private StudentService service;

	@RequestMapping(method = RequestMethod.POST, value = "/students/{id}/classes")
	public ResponseEntity<?> subscribeStudent(@PathVariable Long id, @RequestBody Resource<Class> classBody,
			PersistentEntityResourceAssembler persistentEntityResourceAssembler) throws S4Exception {
		Student student = service.subscribeStudent(id, classBody.getContent());

		return new ResponseEntity<>(persistentEntityResourceAssembler.toResource(student), HttpStatus.OK);
	}
}