package com.ifworks.s4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.ifworks.s4.model.Class;
import com.ifworks.s4.model.Student;
import com.ifworks.s4.repository.ClassRepository;
import com.ifworks.s4.repository.StudentRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class StudentControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private Student student;
	private Class myClass;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		student = new Student();
		student.setFirstName("firstName01");
		student.setLastName("lastNAme01");

		student = studentRepository.save(student);

		myClass = new Class();
		myClass.setCode(1L);
		myClass.setTitle("class01");
		myClass.setDescription("class01 description");

		classRepository.save(myClass);
	}

	@Test
	public void givenStudentAndClass_subscribeStudent_thenStatus200() throws Exception {

		mockMvc.perform(post("/v1/students/" + student.getStudentId() + "/classes")
				.content("{\"code\":" + myClass.getCode() + "}").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	public void givenStudentAndClass_subscribeStudent_thenStatus500() throws Exception {

		thrown.expect(NestedServletException.class);
		thrown.expectMessage(containsString("Class to subscribe not found."));

		mockMvc.perform(post("/v1/students/" + student.getStudentId() + "/classes").content("{\"code\":2}")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));

	}
}