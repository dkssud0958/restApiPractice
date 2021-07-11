package com.example.demo.common;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.restdocs.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;

import com.fasterxml.jackson.databind.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
public class BaseTest {
	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected ModelMapper modelMapper;
}
