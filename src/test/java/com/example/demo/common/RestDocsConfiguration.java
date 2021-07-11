package com.example.demo.common;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

import org.springframework.boot.test.autoconfigure.restdocs.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.restdocs.mockmvc.*;

@TestConfiguration
public class RestDocsConfiguration {

	@Bean
	public RestDocsMockMvcConfigurationCustomizer mockMvcConfigurationCustomizer() {
		/*return new RestDocsMockMvcConfigurationCustomizer() {
			
			@Override
			public void customize(MockMvcRestDocumentationConfigurer configurer) {
				configurer.operationPreprocessors()
				.withRequestDefaults(prettyPrint())
				.withResponseDefaults(prettyPrint());
				
			}
		};*/
		//lamda 식으로
		return configurer -> configurer.operationPreprocessors()
				.withRequestDefaults(prettyPrint())
				.withResponseDefaults(prettyPrint());
		
	}
}
