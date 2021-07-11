package com.example.demo.events;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.runner.*;
import org.springframework.test.context.junit4.*;

@RunWith(SpringRunner.class)
public class EventTest {

	 @Test
	    public void builder() {
	        Event event = Event.builder()
	                .name("Inflearn Spring REST API")
	                .description("REST API development with Spring")
	                .build();
	        assertThat(event).isNotNull();
	    }

}
